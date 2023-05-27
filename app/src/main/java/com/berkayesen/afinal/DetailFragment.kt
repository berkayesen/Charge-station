package com.berkayesen.afinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.berkayesen.afinal.databinding.FragmentDetailBinding
import com.berkayesen.afinal.retrofit2.data.model.AddressInfo


class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding :  FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString("title")

            val longitude = arguments?.getDouble("longitude")

            val latitude = arguments?.getDouble("latitude")

            binding.textView13.text = "${latitude.toString()} ,${longitude.toString()} "



    }
    companion object {

    }
}