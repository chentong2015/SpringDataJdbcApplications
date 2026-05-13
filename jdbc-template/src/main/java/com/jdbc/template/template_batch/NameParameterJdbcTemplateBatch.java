package com.jdbc.template.template_batch;

import com.jdbc.template.model.Information;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO. 测试批量执行具名Query的更新操作
@Service
public class NameParameterJdbcTemplateBatch {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public NameParameterJdbcTemplateBatch(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    // 使用object数组传递批量参数值
    public void batchInsert() {
        String query = "INSERT INTO information (id, name, place, info) VALUES (:id, :name, :place, :info)";

        List<Information> infoList = new ArrayList<>();
        infoList.add(new Information(1, "name1", "place1", "100"));
        infoList.add(new Information(2, "name2", "place2", "200"));
        infoList.add(new Information(3, "name3", "place3", "300"));

        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(infoList);
        namedParameterJdbcTemplate.batchUpdate(query, params);
    }

    // TODO. 自动根据具名参数名找到对应getRefreshedId()方法获取字段值, 完成数据更新
    public void patchUpdate() {
        String query = "UPDATE information SET id = :refreshedId, name = :name WHERE id = :id";

        Information information1 = new Information(1, "name1-test", "place1-test", "100");
        information1.setRefreshedId(123);
        Information information2 = new Information(2, "name2-test", "place2-test", "200");
        information2.setRefreshedId(234);
        List<Information> infoList = List.of(information1, information2);

        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(infoList);
        namedParameterJdbcTemplate.batchUpdate(query, params);
    }

    // 使用Map隐射传递批量参数值
    public void batchInsertByMap() {
        String query = "INSERT INTO information (id, name, place, info) VALUES (:id, :name, :place, :info)";

        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 1);
        map1.put("name", "name1");
        map1.put("place", "place1");
        map1.put("info", "100");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", 2);
        map2.put("name", "name2");
        map2.put("place", "place2");
        map2.put("info", "200");

        Map<String, ?>[] batchValues = new Map[2];
        batchValues[0] = map1;
        batchValues[1] = map2;

        namedParameterJdbcTemplate.batchUpdate(query, batchValues);
    }
}
