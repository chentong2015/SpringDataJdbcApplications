package com.jdbc.template.template;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcTemplateMapper {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcTemplateMapper(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    // TODO. 使用Lambda表达式实现RowMapper列的映射(取值到List列表)
    public boolean hasFoundRecordsByOrigin(String origin) {
        String sql = "SELECT 1 FROM MAIN WHERE ORIGIN = :origin";
        MapSqlParameterSource params = new MapSqlParameterSource("origin", origin);
        List<Integer> results = this.jdbcTemplate.query(sql, params,
                (rs, rowNum) -> rs.getInt(1));
        return !results.isEmpty();
    }
}
