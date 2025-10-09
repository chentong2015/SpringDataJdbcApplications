package com.jdbc.template.utils.model;

public interface CustomSqlParameterSourceBuilder {

    MapSqlParameterSourceBuilder add(Column column, Object value);

}