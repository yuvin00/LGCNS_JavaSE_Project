package mylab.library.control;

import mylab.library.entity.Book;
import mylab.library.entity.Library;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library("중앙 도서관");
        String isbn = "978-89-01-14077-4";

        addSampleBooks(library);

        System.out.println("\n===== " + library.getName() + " =====");
        printLibraryStatus(library);

        testFindBook(library, isbn);
        testCheckOut(library, isbn);
        testReturn(library, isbn);
        displayAvailableBooks(library);
    }

    private static void addSampleBooks(Library library) {
        library.addBook(new Book("자바 프로그래밍", "김자바", "978-89-01-12345-6", 2022));
        library.addBook(new Book("객체지향의 사실과 오해", "조영호", "978-89-01-67890-1", 2015));
        library.addBook(new Book("Clean Code", "Robert C. Martin", "978-0-13-235088-4", 2008));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "978-0-13-468599-1", 2018));
        library.addBook(new Book("Head First Java", "Kathy Sierra", "978-0-596-00920-5", 2005));
        library.addBook(new Book("자바의 정석", "남궁성", "978-89-01-14077-4", 2019));
    }

    private static void testFindBook(Library library, String isbn) {
        System.out.println("\n===== 도서 검색 테스트 =====");

        System.out.println("제목으로 검색 결과:");
        System.out.println(library.findBookByTitle("자바의 정석"));

        System.out.println("\n저자로 검색 결과:");
        for (Book book : library.findBooksByAuthor("Robert C. Martin")) {
            System.out.println(book);
        }

        System.out.println("\nISBN으로 검색 결과:");
        System.out.println(library.findBookByISBN(isbn));
    }

    private static void testCheckOut(Library library, String isbn) {
        System.out.println("\n===== 도서 대출 테스트 =====");
        if (library.checkOutBook(isbn)) {
            System.out.println("도서 대출 성공!");
            System.out.println(library.findBookByISBN(isbn));
        } else {
            System.out.println("도서 대출 실패");
        }

        printLibraryStatus(library);
    }

    private static void testReturn(Library library, String isbn) {
        System.out.println("\n===== 도서 반납 테스트 =====");
        if (library.returnBook(isbn)) {
            System.out.println("도서 반납 성공!");
            System.out.println(library.findBookByISBN(isbn));
        } else {
            System.out.println("도서 반납 실패");
        }

        printLibraryStatus(library);
    }

    private static void displayAvailableBooks(Library library) {
        System.out.println("\n===== 대출 가능한 도서 목록 =====");
        for (Book book : library.getAvailableBooks()) {
            System.out.println(book);
            System.out.println("------------------------");
        }
    }

    private static void printLibraryStatus(Library library) {
        System.out.println("\n도서관 현재 상태:");
        System.out.println("전체 도서 수: " + library.getTotalBooks());
        System.out.println("대출 가능 도서 수: " + library.getAvailableBooksCount());
        System.out.println("대출 중인 도서 수: " + library.getBorrowedBooksCount());
    }
}