package manager;

import db.DBConnectionProvider;
import model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AuthorManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public AuthorManager() {
    }

    public void addAuthor(Author author) {
        String sql = "insert into author(name,surname,email,age) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getEmail());
            ps.setInt(4, author.getAge());

            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                author.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Author> getAllAuthors() {
        String sql = "select * from author";
        List<Author> authors = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                authors.add(getAuthorFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    private Author getAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        return Author.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .age(resultSet.getInt("age"))
                .build();
    }

    public static void showAllAuthors() throws SQLException {
        AuthorManager authorManager = new AuthorManager();
        List<Author> allAuthors = authorManager.getAllAuthors();
        for (Author author : allAuthors) {
            System.out.println(author);
        }
    }


    public Author getById(int id) {
        String query = "select * from author where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void removeAuthorById(int Id) {
        String sql = "delete from author where id = " + Id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Author author) {
        String sql = "update author set name = ?, surname = ?, email = ?, age = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getEmail());
            ps.setInt(4, author.getAge());
            ps.setInt(5, author.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
