package ro.itschool.dao.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.itschool.dao.AuthorDAO;
import ro.itschool.dao.BookDAO;
import ro.itschool.entity.Author;
import ro.itschool.entity.Book;
import ro.itschool.utils.HibernateUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Log
public class BookDAOImpl implements BookDAO {

    private final SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    private final AuthorDAO authorDAO = new AuthorDAOImpl();

    public BookDAOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void insertBook(Book book) {
        openSessionAndTransaction();
        Set<Author> persistedAuthors = new HashSet<>();
        for (Author author : book.getAuthors()) {
            Author persistedAuthor = authorDAO.insertAuthor(author);
            persistedAuthors.add(persistedAuthor);
        }
        book.setAuthors(persistedAuthors);
        try {
            session.persist(book);
            commitTransactionAndCloseSession();
        } catch (Exception e) {
            log.severe("Could not save book. Duplicate title");
            transaction.rollback();
            closeSession();
        }
    }

    @Override
    public void insertMultipleBooks(List<Book> books) {
        for (Book book : books) {
            insertBook(book);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Book> getAllBooksQuery = criteriaBuilder.createQuery(Book.class);
        getAllBooksQuery.from(Book.class);
        List<Book> bookList = session.createQuery(getAllBooksQuery).getResultList();
        closeSession();
        return bookList;
    }

    @Override
    public Book getBookByIdWithCriteriaBuilder(Integer id) {
        openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Book> getAllBooksQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> from = getAllBooksQuery.from(Book.class);
        getAllBooksQuery.where(criteriaBuilder.equal(from.get("id"), id));
        Book book = session.createQuery(getAllBooksQuery).getSingleResult();
        closeSession();
        return book;
    }


    @Override
    public Optional<Book> getBookById(Integer id) {
        openSession();
        Optional<Book> book = Optional.ofNullable(session.find(Book.class, id));
        closeSession();
        return book;
    }

    @Override
    public void deleteBookById(Integer id) {
        Optional<Book> optionalBook = getBookById(id);
        if (optionalBook.isPresent()) {
            openSessionAndTransaction();
            session.remove(optionalBook.get());
            commitTransactionAndCloseSession();
        } else
            log.info("Cannot find entity with id=" + id);
    }

    @Override
    public void updateBook(Book bookFromFrontend) {
        openSessionAndTransaction();
        session.merge(bookFromFrontend);
        commitTransactionAndCloseSession();
    }


    private void openSessionAndTransaction() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    private void openSession() {
        session = sessionFactory.openSession();
    }

    private void commitTransactionAndCloseSession() {
        transaction.commit();
        session.close();
    }

    private void closeSession() {
        session.close();
    }
}
