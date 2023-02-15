package ro.itschool;

import com.github.javafaker.Faker;
import ro.itschool.dao.BookDAO;
import ro.itschool.dao.impl.BookDAOImpl;
import ro.itschool.entity.Author;
import ro.itschool.entity.Book;
import ro.itschool.entity.PublishingHouse;
import ro.itschool.entity.Title;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {


    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAOImpl();


        //------ insert complete book --------------
        bookDAO.insertBook(getRandomBook());

        //----- read complete book -----------------
        bookDAO.getAllBooks().forEach(System.out::println);

        //---- delete book -------------------------
        bookDAO.deleteBookById(1);


    }

    private static Book getRandomBook() {
        Faker faker = new Faker();
        com.github.javafaker.Book fakerBook = faker.book();

        Book book = new Book();
        book.setPrice((float) faker.number().numberBetween(5, 99));
        book.setTitle(new Title(fakerBook.title()));
        book.setPublishingHouse(new PublishingHouse(fakerBook.publisher(), faker.address().fullAddress()));
        book.setPublishingYear(faker.number().numberBetween(1910, 2023));
        book.setAuthors(generateRandomAuthors(book));

        return book;
    }


    private static Set<Author> generateRandomAuthors(Book book) {
        Faker faker = new Faker();
        Random random = new Random();
        Set<Author> authors = new HashSet<>();
        for (int i = 0; i < random.nextInt(5); i++) {
            Author author = new Author(faker.book().author(), faker.number().numberBetween(14, 99));
            author.addBookToAuthor(book);
            authors.add(author);
        }
        return authors;
    }

//    private static List<Book> get50RandomBooks() {
//        return Stream.generate(Main::getRandomBook).limit(50).toList();
//    }
}