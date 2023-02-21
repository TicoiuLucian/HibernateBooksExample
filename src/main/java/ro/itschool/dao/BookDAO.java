package ro.itschool.dao;

//DAO = Data Access Object

import ro.itschool.entity.Book;

import java.util.List;
import java.util.Optional;

//Obiectul care face legatura intre Java si DB
//Aici vor fi toate metodele prin care vom interactiona cu DB-ul
public interface BookDAO {
    /**
     * Insert {@link Book} into database
     *
     * @param book
     */
    void insertBook(Book book);
//comentariu Galan bogdan
    void insertMultipleBooks(List<Book> book);

    /** This method returns a list of all {@link Book}
     *
     * @return List<Book>
     */
    List<Book> getAllBooks();

    Optional<Book> getBookById(Integer id);

    void deleteBookById(Integer id);

    void updateBook(Book bookFromFrontend);

    Book getBookByIdWithCriteriaBuilder(Integer id);

}
