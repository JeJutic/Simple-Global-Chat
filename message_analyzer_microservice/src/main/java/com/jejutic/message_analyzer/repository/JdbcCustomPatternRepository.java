package com.jejutic.message_analyzer.repository;

import com.jejutic.message_analyzer.dto.CustomPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcCustomPatternRepository implements CustomPatternRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcCustomPatternRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CustomPattern> findAll() {
        return jdbcTemplate.query(
                "select regexp from CustomPattern",
                this::mapCustomPatternToMessage);
    }

    private CustomPattern mapCustomPatternToMessage(ResultSet row, int colNum) throws SQLException {
        return new CustomPattern(
                row.getString("regexp")
        );
    }
}
