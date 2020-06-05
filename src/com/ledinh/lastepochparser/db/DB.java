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

    private void populateItems(List<Item> items) throws SQLException {
        for (Item item: items) {
            String query = "INSERT INTO `Item`\n" +
                    "(`baseTypeName`,\n" +
                    "`baseDisplayName`,\n" +
                    "`baseTypeID`,\n" +
                    "`maximumAffixes`,\n" +
                    "`maxSockets`,\n" +
                    "`affixEffectModifier`,\n" +
                    "`gridSizeX`,\n" +
                    "`gridSizeY`,\n" +
                    "`type`,\n" +
                    "`isWeapon`,\n" +
                    "`minimumDropLevel`,\n" +
                    "`name`,\n" +
                    "`displayName`,\n" +
                    "`subTypeID`,\n" +
                    "`levelRequirement`,\n" +
                    "`cannotDrop`,\n" +
                    "`itemTags`,\n" +
                    "`classRequirement`,\n" +
                    "`subClassRequirement`,\n" +
                    "`attackRate`)\n" +
                    " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, item.getBaseTypeName());
            preparedStmt.setString(2, item.getBaseDisplayName());
            preparedStmt.setInt(3, item.getBaseTypeID());
            preparedStmt.setInt(4, item.getMaximumAffixes());
            preparedStmt.setInt(5, item.getMaxSockets());
            preparedStmt.setFloat(6, item.getAffixEffectModifier());
            preparedStmt.setInt(7, item.getGridSizeX());
            preparedStmt.setInt(8, item.getGridSizeY());
            preparedStmt.setInt(9, item.getType());
            preparedStmt.setInt(10, item.getIsWeapon());
            preparedStmt.setInt(11, item.getMinimumDropLevel());
            preparedStmt.setString(12, item.getName());
            preparedStmt.setString(13, item.getDisplayName());
            preparedStmt.setInt(14, item.getSubTypeID());
            preparedStmt.setInt(15, item.getLevelRequirement());
            preparedStmt.setInt(16, item.getCannotDrop());
            preparedStmt.setInt(17, item.getItemTags());
            preparedStmt.setInt(18, item.getClassRequirement());
            preparedStmt.setInt(19, item.getSubClassRequirement());
            preparedStmt.setFloat(20, item.getAttackRate());

            // execute the preparedstatement
            preparedStmt.execute();
        }

    }

    public void populate(List<Item> items) throws SQLException, IOException, ClassNotFoundException {
        connect();

        populateItems(items);

        close();
    }
}
