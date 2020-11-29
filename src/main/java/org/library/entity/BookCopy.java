package org.library.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCopy implements Comparable<BookCopy> {
    private int id;
    private Book book;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCopy bookCopy = (BookCopy) o;
        return Objects.equals(book, bookCopy.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book);
    }

    @Override
    public int compareTo(BookCopy bookCopy) {
        return Integer.compare(id, bookCopy.getId());
    }
}