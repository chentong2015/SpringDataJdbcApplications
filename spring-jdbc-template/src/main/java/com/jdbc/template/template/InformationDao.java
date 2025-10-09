package com.jdbc.template.template;

public interface InformationDao {

    boolean insertInformation(Information info);

    Information getInformation(int id);

    Information getInformationTest(int id);

    void cleanupTable();
}
