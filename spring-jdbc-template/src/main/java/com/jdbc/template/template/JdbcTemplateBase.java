package com.jdbc.template.template;

import com.jdbc.template.template.mapper.InfoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

// TODO. JdbcTemplate标准用法
// jdbcTemplate.query(query, RowMapper, arg1, arg2)
@Repository("baseJdbcTemplate")
public class JdbcTemplateBase implements InformationDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // TODO. 更新数据时提供Object依次替换占位符的值
    @Override
    public boolean insertInformation(Information info) {
        String query = "INSERT INTO information (id, name, place, year) VALUES (?, ?, ?, ?)";
        Object[] args = new Object[]{info.getId(), info.getName(), info.getPlace(), info.getYear()};
        return jdbcTemplate.update(query, args) == 1;
    }

    // TODO. 查询数据时需要提供自定义的RowMapper: 将查询ResultSet映射成指定类型的实例
    @Override
    public Information getInformation(int id) {
        String query = "SELECT * FROM information where id = ?";
        Object[] args = new Object[]{ id };
        return jdbcTemplate.queryForObject(query, new InfoRowMapper(), args);
    }

    // TODO. 直接适用默认类型的RowMapper<>: 默认将查询结果映射生成Object对象
    @Override
    public Information getInformationTest(int id) {
        String query = "SELECT * FROM information where id = ?";
        Object[] args = new Object[]{ id };
        return jdbcTemplate.queryForObject(query, args, new BeanPropertyRowMapper<>(Information.class));
    }

    // 如果查询返回是Java Types则不需要使用RowMapper
    public long countInformation() {
        String query = "SELECT count(*) FROM information";
        Long count = jdbcTemplate.queryForObject(query, Long.class);
        return count == null ? 0: count;
    }

    @Override
    public void cleanupTable() {
        // 只清空table中所有信息，只写一次日志记录
        String query = "TRUNCATE TABLE information";
        jdbcTemplate.execute(query);
    }
}
