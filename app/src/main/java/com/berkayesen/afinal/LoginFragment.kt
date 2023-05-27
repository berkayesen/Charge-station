package com.berkayesen.afinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.berkayesen.afinal.databinding.FragmentLoginBinding
import com.huawei.hms.common.ApiException
import com.huawei.hms.support.account.AccountAuthManager
import com.huawei.hms.support.account.request.AccountAuthParams
import com.huawei.hms.support.account.request.AccountAuthParamsHelper
import com.huawei.hms.support.account.service.AccountAuthService


class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    companion object{
        private const val TAG = "LoginFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.buttonSignIn.setOnClickListener{
            signInWithHuaweiId()
        }
        return binding.root
    }

    fun signInWithHuaweiId(){
        val authParams : AccountAuthParams =  AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM).setAuthorizationCode().createParams()
        val service : AccountAuthService = AccountAuthManager.getService(requireActivity(), authParams)
        startActivityForResult(service.signInIntent, 8888)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // Process the authorization result to obtain the authorization code from AuthAccount.
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 8888) {
            val authAccountTask = AccountAuthManager.parseAuthResultFromIntent(data)
            if (authAccountTask.isSuccessful) {
                // The sign-in is successful, and the user's ID information and authorization code are obtained.
                Toast.makeText(requireContext(),"Signed In Successfully",Toast.LENGTH_LONG).show()
                navigateMapPage()
                val authAccount = authAccountTask.result
                Log.i(TAG, "serverAuthCode:" + authAccount.authorizationCode)
            } else {
                // The sign-in failed.
                Log.e(TAG, "sign in failed:" + (authAccountTask.exception as ApiException).statusCode)
            }
        }
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = "Berkay"
        binding.firstBtn.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("username",username)
            findNavController().navigate(R.id.action_loginFragment_to_searchFragment,bundle)
        }
    }*/

    private fun navigateMapPage(){
        findNavController().navigate(R.id.action_loginFragment_to_searchFragment)
    }
}