package com.jdbc.template.template_utils;

public interface CustomSqlParameterSourceBuilder {

    CustomSqlParameterSourceBuilderImpl add(Column column, Object value);

}