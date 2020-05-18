package com.example.referenciapp.database.data

import com.example.referenciapp.database.Book

class BookData {
    fun populateBookData(): List<Book> {
       return listOf(
           Book(
               bookId = 0,
               title = "Didáctica de la lengua y la literatura para primaria",
               year = 2003,
               publisher = "Pearson Educación",
               city = "Madrid"
           ),
           Book(
               bookId = 1,
               title = "Fenómenos de transporte en semiconductores",
               year = 2007,
               publisher = "Fondo de Cultura Económica",
               city = "México"
           ),
           Book(
               bookId = 2,
               title = "El primer libro impreso en Murcia: Margarita poética de Albrecht von Eyb",
               year = 2010,
               publisher = "Morpi",
               city = "Morpi"
           )
       )
    }
}