package ro.itschool;

import com.github.javafaker.Faker;
import ro.itschool.dao.BookDAO;
import ro.itschool.dao.impl.BookDAOImpl;
import ro.itschool.entity.Book;

import java.util.List;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAOImpl();

//        System.out.println(bookDAO.getAllBooks());
//        System.out.println(bookDAO.getBookByIdWithCriteriaBuilder(1));
//        bookDAO.insertBook(getRandomBook());
//        System.out.println(bookDAO.getBookById(2));
//        bookDAO.deleteBookById(99);

//        Optional<Book> b1 = bookDAO.getBookById(1);
//        if (b1.isPresent()) {
//            b1.get().setAuthor("Autor actualizat din main");
//            bookDAO.updateBook(b1.get());
//        }

//        Book book2 = getRandomBook();
//        bookDAO.updateBook(book2);

        bookDAO.insertMultipleBooks(get50RandomBooks());


    }

    private static Book getRandomBook() {
        Faker faker = new Faker();
        com.github.javafaker.Book fakerBook = faker.book();

        Book book = new Book();
        book.setAuthor(fakerBook.author());
        book.setPrice((float) faker.number().numberBetween(5, 99));
        book.setTitle(fakerBook.title());
        book.setPublishingHouse(fakerBook.publisher());
        book.setPublishingYear(faker.number().numberBetween(1910, 2023));

        return book;
    }

    private static List<Book> get50RandomBooks() {
        return Stream.generate(Main::getRandomBook).limit(50).toList();
    }
}