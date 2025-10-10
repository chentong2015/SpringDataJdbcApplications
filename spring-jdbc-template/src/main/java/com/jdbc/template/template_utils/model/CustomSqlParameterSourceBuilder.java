package com.jdbc.template.template_utils.model;

public interface CustomSqlParameterSourceBuilder {

    MapSqlParameterSourceBuilder add(Column column, Object value);

}