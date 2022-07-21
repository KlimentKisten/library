package ru.kisten.springlearn.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;
import ru.kisten.springlearn.models.Book;
import ru.kisten.springlearn.models.Reader;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> showAll(){

        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());

    }

    public Book showByIndex(int id) {

        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id = ?", new Object[]{id}, new BookMapper()).stream().findAny().orElse(null);

    }

    public void insert(Book book) {
        jdbcTemplate.update("INSERT INTO Book (name, author, year) VALUES (?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book book) {

        jdbcTemplate.update("UPDATE Book SET name = ?, author = ?, year = ? WHERE book_id = ?", book.getName(), book.getAuthor(), book.getYear(), id);

    }

    public void delete(int id) {

        jdbcTemplate.update("DELETE FROM Book WHERE book_id = ?", id);

    }

    public Book showByReferenseId(int id) {

        return jdbcTemplate.query("SELECT * FROM Book WHERE reader_id = ?", new Object[]{id}, new BookMapper()).stream().findAny().orElse(null);

    }

    public List<Book> showAllBooksByReferenceId(int id) {

        return jdbcTemplate.query("SELECT * FROM Book WHERE reader_id = ?", new Object[]{id}, new BookMapper());

    }

    public void setBookToReader(int readerId, int bookId) {

        jdbcTemplate.update("UPDATE Book SET reader_id = ? WHERE book_id = ?", readerId, bookId);

    }

    public void removeBookFromReader(int id) {
        jdbcTemplate.update("UPDATE Book SET reader_id = NULL  WHERE book_id = ?", id);
    }
}
