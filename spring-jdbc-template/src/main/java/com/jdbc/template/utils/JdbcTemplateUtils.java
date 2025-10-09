package com.jdbc.template.utils;

import com.jdbc.template.utils.model.Column;
import com.jdbc.template.utils.model.MapSqlParameterSourceBuilder;
import com.jdbc.template.utils.model.CustomSqlParameterSourceBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JdbcTemplateUtils {

    private static final String OR = " OR ";
    private static final String DOUBLE_HYPHEN = "=";
    private static final String UNDERSCORE = "_";
    private static final String COLON = ":";

    // TODO. 实现的效果: 返回构建好的MapSqlParameterSource对象
    // JdbcTemplateUtils.getSqlParameterSourceBuilder(getNamedParameterMap())
    //    .add(FmmPatchListColumn.ID, item.getId())
    //    .add(FmmPatchListColumn.TYPE, item.getType().num())
    //    .add(column, object)
    //    .build()
    public static CustomSqlParameterSourceBuilder getSqlParameterSourceBuilder(Map<String, String> namedParameterMap) {
        return new MapSqlParameterSourceBuilder(namedParameterMap);
    }

    /**
     * This function appends the corresponding named parameter to the provided column name.
     *
     * If the provided column name is "NAME_ID" for instance, "NAME_ID=:nameId" is returned.
     *
     * @param column a table column name
     * @return formatted named parameter
     * @throws NullPointerException if argument is null
     */
    public static String setNamedParameter(Column column) {
        return column.name() + DOUBLE_HYPHEN + toNamedParameter(column);
    }

    /**
     * This function transforms column names to JDBC template named parameters.
     *
     * If the provided column name is "COLNAME", the returned named parameter will be ":colname".
     * If the column name is "COL_NAME", the returned named parameter is ":colName" (notice the camel case).
     *
     * @param column a table column
     * @return corresponding named parameter
     * @throws NullPointerException if argument is null
     */
    public static String toNamedParameter(Column column) {
        return COLON + toParameterName(column);
    }

    /**
     * This function transforms column names to JDBC template conventional parameter names.
     *
     * If the provided column name is "COLNAME", the returned named parameter will be "colname".
     * If the column name is "COL_NAME", the returned named parameter is "colName" (notice the camel case).
     *
     * @param column a table column name
     * @return conventional parameter name
     * @throws NullPointerException if argument is null
     */
    public static String toParameterName(Column column) {
        String[] tokens = column.name().split(UNDERSCORE);
        StringBuilder sb = new StringBuilder();
        sb.append(tokens[0].toLowerCase());
        if (tokens.length == 1) {
            return sb.toString();
        }
        for (int i = 1; i < tokens.length; i++) {
            sb.append(tokens[i].charAt(0));
            if (tokens[i].length() >= 2) {
                sb.append(tokens[i].substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    public static Map<String, String> getParameterSource(List<String> sourceList, Column column) {
        Map<String, String> paramSourceMap = new HashMap<>();
        String parameterName = toParameterName(column);
        for (int i = 0; i < sourceList.size(); i++) {
            paramSourceMap.put(parameterName + i, sourceList.get(i));
        }
        return paramSourceMap;
    }

    public static String disjunction(Column column, int count) {
        return Stream.iterate(0, i -> i + 1)
                .limit(count)
                .map(i -> setNamedParameter(column) + i)
                .collect(Collectors.joining(OR));
    }
}
