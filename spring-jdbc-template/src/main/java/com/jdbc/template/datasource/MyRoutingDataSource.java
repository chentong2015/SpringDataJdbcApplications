package com.jdbc.template.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

// TODO: Spring使用路由来切换多个数据源
public class MyRoutingDataSource extends AbstractRoutingDataSource {

    // @Bean
    public void dataSource() {
        // 使用map来承载所有的数据源，方便做切换
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put("dataSource01", "dataSource01()");
        targetDataSource.put("dataSource02", "dataSource02()");
        targetDataSource.put("dataSource03", "dataSource03()");

        // 映射关系的mapping<求模之后的index，keyDataSource>
        Map<Integer, String> setMapping = new HashMap<>();
        setMapping.put(0, "dataSource01");
        setMapping.put(1, "dataSource02");
        setMapping.put(2, "dataSource03");
    }

    // 从Thread Local中拿到设置好的target data source信息(数据库的下标)
    @Override
    protected Object determineCurrentLookupKey() {
        return MultiDataSourceHolder.getDataSource();
    }

    // 持有当前线程上下文
    public class MultiDataSourceHolder {

        private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<>();
        private static final ThreadLocal<String> tableIndexHolder = new ThreadLocal<>();

        public static void setDataSourceHolder(String key) {
            dataSourceHolder.set(key);
        }

        public static String getDataSource() {
            return dataSourceHolder.get();
        }

        public static void clearDataSourceKey() {
            dataSourceHolder.remove();
        }

        public static void setTableIndex(String key) {
            tableIndexHolder.set(key);
        }

        public static String getTableIndex() {
            return tableIndexHolder.get();
        }

        public static void clearTableIndex() {
            tableIndexHolder.remove();
        }
    }

}
