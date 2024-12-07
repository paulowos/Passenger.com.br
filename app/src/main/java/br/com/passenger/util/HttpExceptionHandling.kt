package br.com.passenger.util

import br.com.passenger.data.dto.ErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException

fun httpExceptionHandling(e: HttpException): ErrorResponse = Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)
