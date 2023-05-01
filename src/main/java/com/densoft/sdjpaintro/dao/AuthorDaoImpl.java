package com.densoft.sdjpaintro.dao;

import com.densoft.sdjpaintro.domain.Author;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final EntityManagerFactory entityManagerFactory;

    public AuthorDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Author> listAuthorByLastNameLike(String lastName) {
        EntityManager entityManager = getEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT a FROM Author a WHERE a.lastName LIKE :last_name");
            query.setParameter("last_name", lastName + "%");
            return (List<Author>) query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Author getById(Long id) {
        try {
            return getEntityManager().find(Author.class, id);
        } catch (NoResultException resultException) {
            return null;
        }
    }


    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        try {
            TypedQuery<Author> query = getEntityManager().createNamedQuery("find_by_name", Author.class);
            query.setParameter("first_name", firstName);
            query.setParameter("last_name", lastName);
            return query.getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

    @Override
    public Author saveNewAuthor(Author author) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.joinTransaction();
        entityManager.persist(author);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        EntityManager entityManager = getEntityManager();
        entityManager.joinTransaction();
        entityManager.merge(author);
        entityManager.flush();
        entityManager.clear();
        return author;
    }

    @Override
    public void deleteAuthorById(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, id);
        entityManager.remove(author);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Author> findAll() {
        try (EntityManager entityManager = getEntityManager()) {
            TypedQuery<Author> typedQuery = entityManager.createNamedQuery("author_find_all", Author.class);
            return typedQuery.getResultList();
        }
    }

    @Override
    public Author findAuthorByNameCriteria(String firstName, String lastName) {

        try (EntityManager entityManager = getEntityManager()) {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
            Root<Author> root = criteriaQuery.from(Author.class);
            ParameterExpression<String> firstNameParam = criteriaBuilder.parameter(String.class);
            ParameterExpression<String> lastNameParam = criteriaBuilder.parameter(String.class);
            Predicate firstNamePred = criteriaBuilder.equal(root.get("first_name"), firstNameParam);
            Predicate lastNamePred = criteriaBuilder.equal(root.get("last_name"), lastNameParam);
            criteriaQuery.select(root).where(criteriaBuilder.and(firstNamePred, lastNamePred));

            TypedQuery<Author> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setParameter(firstNameParam, firstName);
            typedQuery.setParameter(lastNameParam, lastName);

            return typedQuery.getSingleResult();
        }
    }

    @Override
    public Author findAuthorByNameNative(String firstName, String lastName) {
        try (EntityManager entityManager = getEntityManager()) {
            Query query = entityManager.createNativeQuery("SELECT * FROM author WHERE first_name = ? AND last_name = ?", Author.class);
            query.setParameter(1, firstName);
            query.setParameter(2, lastName);
            return (Author) query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }


    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}
