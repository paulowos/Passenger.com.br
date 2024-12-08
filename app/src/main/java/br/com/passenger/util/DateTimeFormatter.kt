package br.com.passenger.util

import android.icu.text.SimpleDateFormat

fun dateTimeFormatter(date: String): String {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date)
    return SimpleDateFormat("dd/MM/yyyy • HH:mm").format(date)
}
