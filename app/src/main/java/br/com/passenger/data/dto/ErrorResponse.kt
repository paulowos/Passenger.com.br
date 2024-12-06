package br.com.passenger.data.dto


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error_code")
    val errorCode: String,
    @SerializedName("error_description")
    val errorDescription: String
)