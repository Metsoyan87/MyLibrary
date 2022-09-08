package servlet;

import manager.AuthorManager;
import manager.BookManager;
import model.Author;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/books/add")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class AddBookServlet extends HttpServlet {

    private BookManager bookManager = new BookManager();
    private AuthorManager authorManager = new AuthorManager();
    private static final String IMAGE_PATH = "C:\\Users\\Mnac\\IdeaProjects\\myLibrary\\myLibraryImages/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> all = authorManager.getAllAuthors();
        req.setAttribute("authors", all);
        req.getRequestDispatcher("/WEB-INF/addBook.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String price1 = req.getParameter("price");
        double price = Double.parseDouble(price1);
        Part profilePicPart = req.getPart("profilePic");
        int authorId = Integer.parseInt(req.getParameter("authorId"));


        String fileName = null;
        if (profilePicPart != null) {
            long nanoTime = System.nanoTime();
            fileName = nanoTime + "_" + profilePicPart.getSubmittedFileName();
            profilePicPart.write(IMAGE_PATH + fileName);
        }
        Book book = Book.builder()
                .title(title)
                .description(description)
                .price(price)
                .author(authorManager.getById(authorId))
                .profilePic(fileName)
                .build();
        bookManager.addBook(book);
        resp.sendRedirect("/books");
    }
}
