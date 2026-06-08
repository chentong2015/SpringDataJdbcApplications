package org.example.listener;

import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.QueryExecutionListener;

import java.util.List;

// TODO. 定义Query执行前后的监听器: 无法显示SQL的完整数据
public class MyQueryExecutionListener implements QueryExecutionListener {

    @Override
    public void beforeQuery(ExecutionInfo executionInfo, List<QueryInfo> queryInfoList) {
        System.out.println("Query Started.");
        System.out.println("Execution info: " + executionInfo);
    }

    @Override
    public void afterQuery(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
        long time = execInfo.getElapsedTime();
        if (time > 200) {
            System.out.println("SLOW SQL: " + time + "ms");
        } else {
            System.out.println("Normal SQL");
        }
        for (QueryInfo queryInfo: queryInfoList) {
            System.out.println(queryInfo.getQuery());
        }
    }
}