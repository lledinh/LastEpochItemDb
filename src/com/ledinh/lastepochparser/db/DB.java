package com.ledinh.lastepochparser.db;

import com.ledinh.lastepochparser.conf.MySQLConfReader;
import com.ledinh.lastepochparser.conf.MySQLDatabaseInfo;
import com.ledinh.lastepochparser.parser.objects.Item;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class DB {
    MySQLDatabaseInfo mySQLDatabaseInfo;
    Connection connection;

    public DB() {

    }

    private void read() throws IOException {
        mySQLDatabaseInfo = MySQLConfReader.read();
    }

    public void connect() throws ClassNotFoundException, SQLException, IOException {
        read();

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://" + mySQLDatabaseInfo.getHost() + ":" + mySQLDatabaseInfo.getPort() + "/" + mySQLDatabaseInfo.getDatabase() + "?" + mySQLDatabaseInfo.getQuery(),mySQLDatabaseInfo.getUser(),mySQLDatabaseInfo.getPassword());
    }

    public void close() throws SQLException {
        connection.close();
    }

    private void populateItems(List<Item> items) {

    }

    public void populate() throws SQLException, IOException, ClassNotFoundException {
        connect();

        // the mysql insert statement
        String query = " insert into users (first_name, last_name, date_created, is_admin, num_points)"
                + " values (?, ?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, "Barney");
        preparedStmt.setString (2, "Rubble");
        preparedStmt.setDate   (3, startDate);
        preparedStmt.setBoolean(4, false);
        preparedStmt.setInt    (5, 5000);

        // execute the preparedstatement
        preparedStmt.execute();


        close();
    }
}
