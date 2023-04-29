package com.densoft.sdjpaintro.dao;

import com.densoft.sdjpaintro.domain.Author;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class AuthorDaoImpl implements AuthorDao {
    private final DataSource dataSource;

    public AuthorDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Author getById(Long id) {

        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM author WHERE id = ?");
//              ResultSet resultSet = statement.executeQuery("SELECT * FROM author WHERE id = " + id);
        ) {
            preparedStatement.setLong(1, id);

            Author author = getAuthor(preparedStatement);
            if (author != null) return author;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private Author getAuthor(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery();) {
            if (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getLong("id"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
                return author;
            }
        }
        return null;
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM author WHERE first_name = ? AND last_name = ?");
//              ResultSet resultSet = statement.executeQuery("SELECT * FROM author WHERE id = " + id);
        ) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            Author author = getAuthor(preparedStatement);
            if (author != null) return author;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Author saveNewAuthor(Author author) {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO author (first_name, last_name) VALUES (?,?)");
        ) {
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.execute();

            try (ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");) {

                if (resultSet.next()) {
                    Long savedId = resultSet.getLong(1);
                    return this.getById(savedId);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE author SET first_name = ? , last_name = ? WHERE author.id = ?");
        ) {
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.setLong(3, author.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return this.getById(author.getId());
    }

    @Override
    public Author deleteAuthorById(Long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM author WHERE author.id = ?");
        ) {;
            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this.getById(id);
    }
}
