package com.jdbc.template.template_batch;

import com.jdbc.template.template.Information;
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

    // TODO. 使用StatementSetter构造Query并批量执行
    public int[] batchUpdate(List<Information> infoList) {
        String query = "INSERT INTO information (id, name, place, year) " +
                "VALUES (?, ?, ?, ?)";
        BatchPreparedStatementSetter statementSetter = new BatchPreparedStatementSetter() {
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
        };

        return this.jdbcTemplate.batchUpdate(query, statementSetter);
    }
}
