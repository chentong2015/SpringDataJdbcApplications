package com.jdbc.template.template_utils.model;

import com.jdbc.template.template_utils.JdbcTemplateUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.HashMap;
import java.util.Map;

public class MapSqlParameterSourceBuilder implements CustomSqlParameterSourceBuilder {

    private final Map<String, Object> paramSourceMap = new HashMap<>();
    private final Map<String, String> paramNameMap;

    public MapSqlParameterSourceBuilder(Map<String, String> paramNameMap) {
        this.paramNameMap = paramNameMap;
    }

    // TODO. 通过返回this来实现Colum列名称在自定义添加
    //
    @Override
    public MapSqlParameterSourceBuilder add(Column column, Object value) {
        String parameterName = paramNameMap.get(column.name());
        parameterName = parameterName != null ? parameterName.substring(1)
                : JdbcTemplateUtils.toParameterName(column);
        paramSourceMap.put(parameterName, value);
        return this;
    }

    public MapSqlParameterSource build() {
        return new MapSqlParameterSource(paramSourceMap);
    }
}