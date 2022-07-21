package ru.kisten.springlearn.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.kisten.springlearn.models.Book;
import ru.kisten.springlearn.models.Reader;

import java.util.List;

@Component
public class ReaderDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReaderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Reader> showAll(){
        return jdbcTemplate.query("SELECT * FROM Reader", new ReaderMapper());
    }

    public Reader showByIndex(int id) {

        return jdbcTemplate.query("SELECT * FROM Reader WHERE reader_id = ?", new Object[]{id}, new ReaderMapper()).stream().findAny().orElse(null);

    }

    public void insert(Reader reader) {

        jdbcTemplate.update("INSERT INTO Reader (name, age) VALUES (?, ?)", reader.getName(), reader.getAge());

    }

    public void update(int id, Reader reader) {

        jdbcTemplate.update("UPDATE Reader SET name = ?, age = ? WHERE reader_id = ?", reader.getName(), reader.getAge(), id);

    }

    public void delete(int id) {

        jdbcTemplate.update("DELETE FROM Reader WHERE reader_id = ?", id);

    }

    public Reader showByReferenceId(int readerId) {

        return jdbcTemplate.query("SELECT * FROM Reader WHERE reader_id = ?", new Object[]{readerId}, new ReaderMapper()).stream().findAny().orElse(null);

    }
}
