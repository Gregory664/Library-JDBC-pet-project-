package org.library.services;

import org.library.entity.Book;
import org.library.entity.BookCopy;
import org.library.exceptions.newExc.BookCopyNotFoundByIdException;
import org.library.interfaces.BookCopyRepository;
import org.library.interfaces.BookRepository;

import java.util.List;

public class BookCopyService {
    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;

    public BookCopyService(BookRepository bookRepository, BookCopyRepository bookCopyRepository) {
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
    }

    public List<BookCopy> findAll() throws BookCopyNotFoundByIdException {
        List<BookCopy> bookCopies = bookCopyRepository.findAll();
        for (BookCopy bookCopy : bookCopies) {
            int bookId = bookCopy.getId();
            Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookCopyNotFoundByIdException(bookId));
            bookCopy.setBook(book);
        }
        return bookCopies;
    }

    public BookCopy findById(Integer id) throws BookCopyNotFoundByIdException {
        BookCopy bookCopy = bookCopyRepository.findById(id).orElseThrow(() -> new BookCopyNotFoundByIdException(id));
        Book book = bookRepository.findById(bookCopy.getBook().getId()).orElseThrow(() -> new BookCopyNotFoundByIdException(id));
        bookCopy.setBook(book);
        return bookCopy;
    }

    public boolean existsById(Integer id) {
        return bookCopyRepository.existsById(id);
    }

    public void deleteAll() {
        bookCopyRepository.deleteAll();
    }

    public boolean deleteById(Integer id) {
        return bookCopyRepository.deleteById(id);
    }

    public boolean save(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public long count() {
        return bookCopyRepository.count();
    }
}
