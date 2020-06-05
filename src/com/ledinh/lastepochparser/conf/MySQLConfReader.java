package com.ledinh.lastepochparser.conf;

import java.io.IOException;
import java.util.Map;

public class MySQLConfReader {

    public static MySQLDatabaseInfo read() throws IOException {
        String confFile = "conf/database.conf";
        Map<String, String> map = ConfReader.read(confFile);

        MySQLDatabaseInfo mySQLDatabaseInfo = new MySQLDatabaseInfo();

        mySQLDatabaseInfo.setHost(map.get("MYSQL_HOST"));
        mySQLDatabaseInfo.setPort(Integer.parseInt(map.get("MYSQL_PORT")));
        mySQLDatabaseInfo.setDatabase(map.get("MYSQL_DATABASE"));
        mySQLDatabaseInfo.setQuery(map.get("MYSQL_QUERY"));
        mySQLDatabaseInfo.setUser(map.get("MYSQL_USER"));
        mySQLDatabaseInfo.setPassword(map.get("MYSQL_PASSWORD"));

        return mySQLDatabaseInfo;
    }

}
