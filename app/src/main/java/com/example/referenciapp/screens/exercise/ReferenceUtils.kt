package com.example.referenciapp.screens.exercise

import com.example.referenciapp.database.DigitalExercises
import com.example.referenciapp.database.PrintExercises
import java.util.*
import kotlin.reflect.typeOf

object ReferenceUtils {
    fun concatReference(reference: List<String>): String {
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

    fun extractDigitalFields(currentExercise: DigitalExercises): List<String> {
        var fields = emptyList<String>()

        when (currentExercise.resourceType) {
            1 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.year,
                    currentExercise.title,
                    currentExercise.url
                )
            }
            2 -> {
                fields = listOf<String>(
                    currentExercise.term,
                    currentExercise.year,
                    currentExercise.institution,
                    currentExercise.edition,
                    currentExercise.url
                )
            }
            3 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.year,
                    currentExercise.term,
                    currentExercise.editor,
                    if(currentExercise.hasEditor) currentExercise.editorString else "",
                    currentExercise.source,
                    currentExercise.edition,
                    currentExercise.city,
                    currentExercise.country,
                    currentExercise.publisher,
                    currentExercise.url
                )
            }
            4 -> {
                fields = listOf<String>(
                    currentExercise.corp,
                    currentExercise.year,
                    currentExercise.title,
                    currentExercise.edition,
                    currentExercise.city,
                    currentExercise.country,
                    currentExercise.publisher
                )
            }
            5 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.year,
                    currentExercise.title,
                    currentExercise.journal,
                    currentExercise.issue,
                    currentExercise.pages,
                    currentExercise.url
                )
            }
            6 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.date,
                    currentExercise.title,
                    currentExercise.blogtag,
                    currentExercise.url
                )
            }
            7 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.date,
                    currentExercise.tweet,
                    currentExercise.tweetTag,
                    currentExercise.url
                )
            }
            8 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.date,
                    currentExercise.title,
                    currentExercise.videoTag,
                    currentExercise.url
                )
            }
            9 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.date,
                    currentExercise.title,
                    currentExercise.imgTag,
                    currentExercise.url
                )
            }
        }

        return fields.filter { x -> x != "" }
    }

    fun extractPrintFields(currentExercise: PrintExercises): List<String> {
        var fields = emptyList<String>()

        when (currentExercise.resourceType) {
            1 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.year,
                    if (currentExercise.isEditor) currentExercise.editorString else "",
                    currentExercise.title,
                    currentExercise.city,
                    currentExercise.country,
                    currentExercise.publisher
                )
            }
            2 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.year,
                    currentExercise.chapterTitle,
                    currentExercise.editors,
                    currentExercise.title,
                    currentExercise.pages,
                    currentExercise.city,
                    currentExercise.country,
                    currentExercise.publisher
                )
            }
            3 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.year,
                    currentExercise.term,
                    currentExercise.source,
                    currentExercise.edition,
                    currentExercise.city,
                    currentExercise.country,
                    currentExercise.publisher
                )
            }
            4 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.year,
                    currentExercise.term,
                    currentExercise.source,
                    currentExercise.volumeAndPages,
                    currentExercise.city,
                    currentExercise.country,
                    currentExercise.publisher
                )
            }
            5 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.date,
                    currentExercise.title,
                    currentExercise.newspaper,
                    currentExercise.pages
                )
            }
            6 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.year,
                    currentExercise.title,
                    currentExercise.journal,
                    currentExercise.volume,
                    currentExercise.issue,
                    currentExercise.pages
                )
            }
        }

        return fields.filter { x -> x != "" }
    }
}