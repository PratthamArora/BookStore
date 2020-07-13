package com.pratthamarora.data

import com.pratthamarora.data.model.Book
import org.slf4j.LoggerFactory
import kotlin.reflect.full.declaredMemberProperties

class DataManager {
    val log = LoggerFactory.getLogger(DataManager::class.java)
    private var books = ArrayList<Book>()

    fun getId(): String = books.size.toString()

    init {
        books.add(Book(getId(), "How to grow Apples", "Mr. Appleton", 100.0f))
        books.add(Book(getId(), "How to grow Oranges", "Mr. Orange", 200.0f))
        books.add(Book(getId(), "How to grow Lemons", "Mr. Lemon", 300.0f))
        books.add(Book(getId(), "How to grow Pineapples", "Mr. Pineapple", 450.0f))
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

    fun sortedBooks(sortBy: String, asc: Boolean): List<Book> {
        val member = Book::class.declaredMemberProperties.find {
            it.name == sortBy
        }
        if (member == null) {
            log.info("The field to sort does not exist")
            return allBooks()
        }
        return if (asc)
            allBooks().sortedBy {
                member.get(it).toString()
            }
        else
            allBooks().sortedByDescending {
                member.get(it).toString()
            }
    }
}