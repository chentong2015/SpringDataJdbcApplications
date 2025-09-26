package com.jdbc.template.template.batch;

import com.jdbc.template.template.model.Information;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import java.util.List;

public class JdbcTemplateNameParameterBatch {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcTemplateNameParameterBatch(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // sql参数表示具有名称的query模板
    public void batchInsert(List<Information> infoList, String sql) {
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(infoList);
        jdbcTemplate.batchUpdate(sql, params);
    }
}
