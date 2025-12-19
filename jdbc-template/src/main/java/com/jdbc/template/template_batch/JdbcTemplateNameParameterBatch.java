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

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String query = "INSERT INTO information (id, name, place, year) " +
            "VALUES (:id, :name, :place, :year)";

    public JdbcTemplateNameParameterBatch(NamedParameterJdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = jdbcTemplate;
    }

    // TODO. 批量执行具名Query的更新操作: 使用object数组传递批量参数值
    public void batchInsert() {
        List<Information> infoList = new ArrayList<>();
        infoList.add(new Information(1, "name1", "place1", 100));
        infoList.add(new Information(2, "name2", "place2", 200));
        infoList.add(new Information(3, "name3", "place3", 300));

        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(infoList);
        namedParameterJdbcTemplate.batchUpdate(query, params);
    }

    // TODO. 批量执行具名Query的更新操作: 使用Map隐射传递批量参数值
    public void batchInsertByMap() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 1);
        map1.put("name", "name1");
        map1.put("place", "place1");
        map1.put("year", 100);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", 2);
        map2.put("name", "name2");
        map2.put("place", "place2");
        map2.put("year", 200);

        Map<String, ?>[] batchValues = new Map[2];
        batchValues[0] = map1;
        batchValues[1] = map2;

        String query = "INSERT INTO information (id, name, place, year) " +
                "VALUES (:id, :name, :place, :year)";
        namedParameterJdbcTemplate.batchUpdate(query, batchValues);
    }
}
