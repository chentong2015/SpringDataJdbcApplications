package com.jdbc.template.template.batch;

import com.jdbc.template.template.model.Information;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplateBatch {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 1. 设置PreparedStatement的参数
    // 2. 传递list列表序列的参数并批量执行相同的SQL
    public int[] batchUpdate(List<Information> infoList) {
        return this.jdbcTemplate.batchUpdate(
                "INSERT INTO information (id, name, place, year) VALUES (?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Information information = infoList.get(i);
                        ps.setInt(1, information.getId());
                        ps.setString(2, information.getName());
                        ps.setString(3, information.getPlace());
                        ps.setInt(4, information.getYear());
                    }

                    public int getBatchSize() {
                        return infoList.size();
                    }
                });
    }
}
