package com.jdbc.template.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

// TODO. 使用具名参数替代传统的placeholder'?', 解决可读性和参数顺序问题
@Repository("baseNamedParameterJdbcTemplate")
public class JdbcTemplateNameParameter {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    // TODO. BeanPropertySqlParameterSource 从指定Java对象中获取具名参数值, 属性和参数名称必须一致
    public boolean insertInformation(Information info) {
        SqlParameterSource beanParams = new BeanPropertySqlParameterSource(info);
        String sqlQuery = "INSERT INTO information (id, name, place, year) " +
                "VALUES (:id, :name, :place, :year)";
        return namedParameterJdbcTemplate.update(sqlQuery, beanParams) == 1;
    }

    // TODO. MapSqlParameterSource 根据映射名称获取具名参数值
    public Information getInformation(int id) {
        SqlParameterSource params = new MapSqlParameterSource("ID", id);
        String sqlQuery = "SELECT * FROM information where id = :ID";
        return namedParameterJdbcTemplate.queryForObject(sqlQuery, params, new InfoRowMapper());
    }

    // TODO. MapSqlParameterSource 从Map映射表中获取具名参数值
    // Map<String, Object> 都提供了与 SQL 中命名参数对应的键
    public Information getInformationTest(int id) {
        Map<String, Object> values = new HashMap<>();
        values.put("ID", id);
        SqlParameterSource params = new MapSqlParameterSource(values);
        String sqlQuery = "SELECT * FROM information where id = :ID";
        return namedParameterJdbcTemplate.queryForObject(sqlQuery, params, new InfoRowMapper());
    }

    public void cleanupTable() {
        String sqlQuery = "TRUNCATE TABLE information";
        namedParameterJdbcTemplate.getJdbcOperations().execute(sqlQuery);
    }
}
