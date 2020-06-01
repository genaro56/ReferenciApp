package com.app.referenciapp.screens.exercise

import java.util.*

object ReferenceUtils {
    fun concatReference(reference: MutableList<String>): String {
        return reference.joinToString("") { it }
    }

    fun shuffleList (list: MutableList<String>): MutableList<String> {
        // Fisher-Yates shuffle algorithm
        val rand = Random()
        for (i in list.size - 1 downTo 1) {
            val j = rand.nextInt(i + 1)
            val temp = list[i]
            list[i] = list[j]
            list[j] = temp
        }
        return list
    }
}