package mylab.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private final List<Book> books;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("도서가 추가되었습니다: " + book.getTitle());
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (title.equals(book.getTitle())) {
                return book;
            }
        }
        return null;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> matches = new ArrayList<>();

        for (Book book : books) {
            if (author.equals(book.getAuthor())) {
                matches.add(book);
            }
        }
        return matches;
    }

    public Book findBookByISBN(String isbn) {
        for (Book book : books) {
            if (isbn.equals(book.getIsbn())) {
                return book;
            }
        }
        return null;
    }

    // ISBN으로 도서 대출
    public boolean checkOutBook(String isbn) {
        Book book = findBookByISBN(isbn);
        return book != null && book.checkOut();
    }

    // ISBN으로 도서 반납
    public boolean returnBook(String isbn) {
        Book book = findBookByISBN(isbn);
        if (book == null || book.isAvailable()) {
            return false;
        }

        book.returnBook();
        return true;
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public int getTotalBooks() {
        return books.size();
    }

    public int getAvailableBooksCount() {
        int count = 0;

        for (Book book : books) {
            if (book.isAvailable()) {
                count++;
            }
        }
        return count;
    }

    public int getBorrowedBooksCount() {
        return getTotalBooks() - getAvailableBooksCount();
    }
}