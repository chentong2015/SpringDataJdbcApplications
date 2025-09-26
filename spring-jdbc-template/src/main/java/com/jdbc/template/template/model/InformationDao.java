package com.jdbc.template.template.model;

public interface InformationDao {

    boolean insertInformation(Information info);

    Information getInformation(int id);

    void cleanupTable();
}
