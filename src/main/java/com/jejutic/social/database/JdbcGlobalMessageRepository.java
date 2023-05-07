package com.jejutic.social.database;

import com.jejutic.social.database.data_objects.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcGlobalMessageRepository implements GlobalMessageRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcGlobalMessageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Message> findAll() {
        return jdbcTemplate.query(
                "select id, text, author from GlobalMessage order by id desc",
                this::mapRowToMessage);
    }

    @Override
    public boolean create(Message message) {
        String sqlQuery = "insert into GlobalMessage (text, author) values (?, ?)";
        return jdbcTemplate.update(sqlQuery,
                message.getText(),
                message.getAuthor()) > 0;
    }

    private Message mapRowToMessage(ResultSet row, int colNum) throws SQLException {
        return new Message(
                row.getLong("id"),
                row.getString("text"),
                row.getString("author")
        );
    }
}
