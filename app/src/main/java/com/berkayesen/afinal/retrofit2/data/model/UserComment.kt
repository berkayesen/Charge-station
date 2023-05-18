package com.berkayesen.afinal.retrofit2.data.model

data class UserComment(
    val ChargePointID: Int,
    val CheckinStatusType: CheckinStatusType,
    val CheckinStatusTypeID: Int,
    val Comment: String,
    val CommentType: CommentType,
    val CommentTypeID: Int,
    val DateCreated: String,
    val ID: Int,
    val IsActionedByEditor: Boolean,
    val Rating: Any,
    val RelatedURL: String,
    val User: UserX,
    val UserName: String
)