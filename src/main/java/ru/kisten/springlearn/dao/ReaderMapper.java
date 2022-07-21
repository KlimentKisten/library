package ru.kisten.springlearn.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.kisten.springlearn.models.Reader;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderMapper implements RowMapper<Reader> {
    @Override
    public Reader mapRow(ResultSet resultSet, int i) throws SQLException {
        Reader reader = new Reader();

        reader.setId(resultSet.getInt("reader_id"));
        reader.setName(resultSet.getString("name"));
        reader.setAge(resultSet.getInt("age"));

        return reader;
    }
}
