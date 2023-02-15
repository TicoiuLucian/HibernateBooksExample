package ro.itschool.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.itschool.dao.AuthorDAO;
import ro.itschool.entity.Author;
import ro.itschool.utils.HibernateUtil;

public class AuthorDAOImpl implements AuthorDAO {

    private final SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public AuthorDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public Author insertAuthor(Author author) {
        openSessionAndTransaction();
        Author persistedAuthor = session.merge(author);
        commitTransactionAndCloseSession();
        return persistedAuthor;
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
