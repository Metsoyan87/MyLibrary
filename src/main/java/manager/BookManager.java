package manager;

import db.DBConnectionProvider;
import model.Book;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class BookManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    private AuthorManager authorManager = new AuthorManager();


    public void addBook(Book book) {
        String sql = "insert into book(title,description,price,author_id) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getAuthor().getId());


            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                book.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBook() {
        String sql = "select * from book";
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                books.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book getById(int id) {
        String sql = "select * from book where id = " + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeBookById(int id) {
        String sql = "delete from book where id = " + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException, ParseException {

        return Book.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .description(resultSet.getString("description"))
                .price(resultSet.getDouble("price"))
                .author(authorManager.getById(resultSet.getInt("author_id")))
                .build();
    }

    public void editBook(Book book) {
        String sql = "update book set title = ?,description = ?,price = ?, author_Id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getAuthor().getId());
            ps.setInt(5, book.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}