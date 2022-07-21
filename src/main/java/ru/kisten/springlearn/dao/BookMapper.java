package ru.kisten.springlearn.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.kisten.springlearn.models.Book;
import ru.kisten.springlearn.models.Reader;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();

        book.setId(resultSet.getInt("book_id"));
        book.setName(resultSet.getString("name"));
        book.setYear(resultSet.getInt("year"));
        book.setAuthor(resultSet.getString("author"));
        book.setReaderID(resultSet.getInt("reader_id"));

        return book;
    }
}
