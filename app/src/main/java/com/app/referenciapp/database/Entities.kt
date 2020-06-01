package com.app.referenciapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

// Resource type 1: Books and Anthology
// We distinguish between books and chapters because the latter have different
// typographic conventions (e.g. the chapter title is not in italics)
// PRINT
// Resources
// PRINT
// 1: Book
// 2: Chapter
// 3: Dictionary
// 4: Encyclopedia
// 5: Newspaper
// 6: Journal

// DIGITAL
// 1: Digital Book
// 2: Digital Dictionary
// 3: Digital Encyclopedia
// 4: Webpage
// 5: Journal
// 6: Blog
// 7: Twitter
// 8: Video/Videocall
// 9: Images

// Exercise types:
// 1: Drag and Drop
// 2: True or false
@Entity
data class PrintExercises(
    @PrimaryKey(autoGenerate = false) val id: Long = 0L,

    // book
    val authors: String = "",
    val isEditor: Boolean = false,
    val editorString: String = "(Ed.).",
    val year: String = "",
    val title: String = "",
    val city: String = "",
    val country: String = "",
    val publisher: String = "",

    // chapter
    val chapterTitle: String = "",
    val editors: String = "",
    val pages: String = "",

    // dictionary
    val term: String = "",
    val source: String = "",
    val edition: String = "",

    // Encyclopedia
    val volumeAndPages: String = "",

    // Newspaper
    val date: String = "",
    val newspaper: String = "",

    // Journal
    val journal: String = "",
    val volume: String = "",
    val issue: String = "",

    // Exercise related info
    val resourceType: Int = 0,
    val exerciseType: Int = 1,
    val description: String = "",
    val feedback: String = "",
    var completed: Boolean = false
)

// DIGITAL
@Entity
data class DigitalExercises(
    @PrimaryKey(autoGenerate = false) val id: Long = 0L,

    // Digital Book
    val authors: String = "",
    val year: String = "",
    val title: String = "",
    val url: String = "",

    // Digital Dictionary
    val term: String = "",
    val date: String = "",
    val institution: String = "",
    val edition: String = "",

    // Digital Encyclopedia
    val hasEditor: Boolean = false,
    val editorString: String = "(Ed.), ",
    val editor: String = "",
    val source: String = "",
    val city: String = "",
    val country: String = "",
    val publisher: String = "",

    // Webpage
    val corp: String = "",

    // Digital Journal
    val journal: String = "",
    val volume: String = "",
    val issue: String = "",
    val pages: String = "",

    // Blog
    val blogtag: String = "[Mensaje en un blog].",

    // Tweet
    val tweet: String = "",
    val tweetTag: String = "[Tweet].",

    // Video, videocall
    val videoTag: String = "[Archivo de video].",

    // Images
    val imgTag: String = "[Fotografía].",

    // Exercise related info
    val resourceType: Int = 0,
    val exerciseType: Int = 1,
    val description: String = "",
    val feedback: String = "",
    var completed: Boolean = false
)
