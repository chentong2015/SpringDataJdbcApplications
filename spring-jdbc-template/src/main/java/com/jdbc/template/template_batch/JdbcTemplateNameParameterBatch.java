package com.jdbc.template.template_batch;

import com.jdbc.template.template.Information;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateNameParameterBatch {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final String query = "INSERT INTO information (id, name, place, year) " +
            "VALUES (:id, :name, :place, :year)";

    public JdbcTemplateNameParameterBatch(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // TODO. 批量执行具名Query的更新操作, 传递批量的参数(数组)
    public void batchInsert() {
        List<Information> infoList = new ArrayList<>();
        infoList.add(new Information(1, "name1", "place1", 100));
        infoList.add(new Information(2, "name2", "place2", 200));
        infoList.add(new Information(3, "name3", "place3", 300));

        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(infoList);
        jdbcTemplate.batchUpdate(query, params);
    }

    // TODO. 批量执行具名Query的更新操作, 传递批量的参数(Map<>)
    public void batchInsertByMap() {
        Map<String, Object>[] batchValues = new Map[2];
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 1);
        map1.put("name", "name1");
        map1.put("place", "place1");
        map1.put("year", 100);

        jdbcTemplate.batchUpdate(query, batchValues);
    }
}
