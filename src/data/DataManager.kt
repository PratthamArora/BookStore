package com.pratthamarora.data

import com.pratthamarora.data.model.Book

class DataManager {
    private var books = ArrayList<Book>()

    fun getId(): String = books.size.toString()

    init {
        books.add(Book(getId(), "How to grow Apples", "Mr. Appleton", 100.0f))
        books.add(Book(getId(), "How to grow Oranges", "Mr. Orange", 200.0f))
        books.add(Book(getId(), "How to grow Lemons", "Mr. Lemon", 300.0f))
        books.add(Book(getId(), "How to grow Pineapples", "Mr. Pineapple", 50.0f))
        books.add(Book(getId(), "How to grow Pears", "Mr. Pear", 250.0f))
        books.add(Book(getId(), "How to grow Coconuts", "Mr. Coconuts", 150.0f))
        books.add(Book(getId(), "How to grow Bananas", "Mr. Appleton", 100.0f))
    }

    fun addNewBook(book: Book): Book {
        books.add(book)
        return book
    }

    fun updateBook(book: Book): Book? {
        val newBook = books.find {
            it.id == book.id
        }
        newBook?.let {
            it.apply {
                title = book.title
                author = book.author
                price = book.price
            }
        }
        return newBook
    }

    fun deleteBook(book: Book): Book? {
        val foundBook = books.find {
            it.id == book.id
        }
        books.remove(foundBook)
        return foundBook
    }
    fun deleteBook(bookId: String): Book? {
        val foundBook = books.find {
            it.id == bookId
        }
        books.remove(foundBook)
        return foundBook
    }

    fun allBooks(): List<Book> = books
}