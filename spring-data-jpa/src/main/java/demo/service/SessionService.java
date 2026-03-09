package demo.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class SessionService {

    private DataSource datasource1;
    private DataSource datasource2;

    // TODO. 使用@Qualifier名称来区别使用不同的DataSource
    public SessionService(@Qualifier("datasource-oracle") DataSource datasource1,
                          @Qualifier("datasource-oracle") DataSource datasource2) {
        this.datasource1 = datasource1;
        this.datasource2 = datasource2;
    }

    //  操作不同的Datasource数据库资源
    public void testDatasource1() throws SQLException {
        Connection connection = datasource1.getConnection();
    }

    public void testDatasource2() throws SQLException {
        Connection connection = datasource2.getConnection();
    }
}
