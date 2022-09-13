package com.cherish.nasasearchapp.utils

import java.io.InputStreamReader

object TestUtils {
    fun getJsonContent(fileName: String): String {
        return InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(fileName)).use { it.readText() }
    }
}