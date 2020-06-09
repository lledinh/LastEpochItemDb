package com.ledinh.lastepochparser.db;

import com.ledinh.lastepochparser.conf.MySQLConfReader;
import com.ledinh.lastepochparser.conf.MySQLDatabaseInfo;
import com.ledinh.lastepochparser.parser.objects.Affix;
import com.ledinh.lastepochparser.parser.objects.Item;
import com.ledinh.lastepochparser.parser.objects.Property;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DB {
    MySQLDatabaseInfo mySQLDatabaseInfo;
    Connection connection;

    public DB() {

    }

    public void delete() throws SQLException, IOException, ClassNotFoundException {
        connect();

        String query = "DELETE FROM `LastEpochItemDB`.`Affix`";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.execute();

        query = "DELETE FROM `LastEpochItemDB`.`BaseItem`";
        preparedStmt = connection.prepareStatement(query);
        preparedStmt.execute();

        query = "DELETE FROM `LastEpochItemDB`.`Item`";
        preparedStmt = connection.prepareStatement(query);
        preparedStmt.execute();

        query = "DELETE FROM `LastEpochItemDB`.`ItemProperty`";
        preparedStmt = connection.prepareStatement(query);
        preparedStmt.execute();

        query = "DELETE FROM `LastEpochItemDB`.`Property`";
        preparedStmt = connection.prepareStatement(query);
        preparedStmt.execute();

        query = "DELETE FROM `LastEpochItemDB`.`Tier`";
        preparedStmt = connection.prepareStatement(query);
        preparedStmt.execute();

        close();
    }

    private void read() throws IOException {
        mySQLDatabaseInfo = MySQLConfReader.read();
    }

    public void connect() throws ClassNotFoundException, SQLException, IOException {
        read();

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://" + mySQLDatabaseInfo.getHost() + ":" + mySQLDatabaseInfo.getPort() + "/" + mySQLDatabaseInfo.getDatabase() + "?" + mySQLDatabaseInfo.getQuery();
        connection = DriverManager.getConnection(url,mySQLDatabaseInfo.getUser(),mySQLDatabaseInfo.getPassword());
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
            PreparedStatement preparedStmt = connection.prepareStatement(query, new String[]{ "idItem" });
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

            boolean ok = preparedStmt.execute();

            ResultSet generatedKeys = preparedStmt.getGeneratedKeys();

            int itemId = -1;
            if (generatedKeys.next()) {
                itemId = generatedKeys.getInt(1);
            }

            for (Item.Property property : item.getItemProperties()) {
                String query2 = "INSERT INTO `ItemProperty`\n" +
                        "(`idItem`,\n" +
                        "`idProperty`,\n" +
                        "`specialTag`,\n" +
                        "`tags`,\n" +
                        "`type`,\n" +
                        "`implicitValue`,\n" +
                        "`implicitMaxValue`)\n" +
                        " values (?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStmt2 = connection.prepareStatement(query2);

                preparedStmt2.setInt(1, itemId);
                preparedStmt2.setInt(2, property.getProperty());
                preparedStmt2.setInt(3, property.getSpecialTag());
                preparedStmt2.setInt(4, property.getTags());
                preparedStmt2.setInt(5,property.getType());
                preparedStmt2.setFloat(6, property.getImplicitValue());
                preparedStmt2.setFloat(7, property.getImplicitMaxValue());

                preparedStmt2.execute();
            }

        }

    }

    private void populateBaseItem(List<Item> items) throws SQLException, IOException, ClassNotFoundException {
        Map<String, Map<String, String>> baseItems = new HashMap<>();

        for (Item item : items) {
            Map<String, String> baseItemAttributes = baseItems.get(item.getBaseTypeName());

            if (baseItemAttributes == null) {
                Map<String, String> newMap = new HashMap<>();
                newMap.put("baseTypeName", item.getBaseTypeName());
                newMap.put("baseDisplayName", item.getBaseDisplayName());
                newMap.put("baseTypeID", String.valueOf(item.getBaseTypeID()));
                newMap.put("maximumAffixes", String.valueOf(item.getMaximumAffixes()));
                newMap.put("maxSockets", String.valueOf(item.getMaxSockets()));
                newMap.put("affixEffectModifier", String.valueOf(item.getAffixEffectModifier()));
                newMap.put("gridSizeX", String.valueOf(item.getGridSizeX()));
                newMap.put("gridSizeY", String.valueOf(item.getGridSizeY()));
                newMap.put("type", String.valueOf(item.getType()));
                newMap.put("isWeapon", String.valueOf(item.getIsWeapon()));
                newMap.put("minimumDropLevel", String.valueOf(item.getMinimumDropLevel()));

                baseItems.put(item.getBaseTypeName(), newMap);

                String query = "INSERT INTO `BaseItem`\n" +
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
                        "`minimumDropLevel`)\n" +
                        " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


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

                preparedStmt.execute();
            }
        }
    }

    private void populateProperties(List<Property> properties) throws SQLException, IOException, ClassNotFoundException {
        for (Property property : properties) {
            String query = "INSERT INTO `Property`\n" +
            "(`propertyName`,\n" +
                    "`defaultAltText`,\n" +
                    "`roundAddedToInt`,\n" +
                    "`displayAddedAsPercentage`,\n" +
                    "`displayAddedAsTenthOfValue`,\n" +
                    "`displayAsPercentageOf`,\n" +
                    "`displayAsAddedTo`,\n" +
                    "`dontDisplayPlus`,\n" +
                    "`lessIsBetter`,\n" +
                    "`property`,\n" +
                    "`specialTag`,\n" +
                    "`tags`,\n" +
                    "`modType`,\n" +
                    "`overrideAltText`)\n" +
                    " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, property.getPropertyName());
            preparedStmt.setString(2, property.getDefaultAltText());
            preparedStmt.setInt(3, property.getRoundAddedToInt());
            preparedStmt.setInt(4, property.getDisplayAddedAsPercentage());
            preparedStmt.setInt(5, property.getDisplayAddedAsTenthOfValue());
            preparedStmt.setInt(6, property.getDisplayAsPercentageOf());
            preparedStmt.setInt(7, property.getDisplayAsAddedTo());
            preparedStmt.setInt(8, property.getDontDisplayPlus());
            preparedStmt.setInt(9, property.getLessIsBetter());
            preparedStmt.setInt(10, property.getProperty());
            preparedStmt.setInt(11, property.getSpecialTag());
            preparedStmt.setInt(12, property.getTags());
            preparedStmt.setInt(13, property.getModType());
            preparedStmt.setString(14, property.getOverrideAltText());

            preparedStmt.execute();
        }
    }

    private void populateAffixes(List<Affix> affixes) throws SQLException, IOException, ClassNotFoundException {
        for (Affix affix: affixes) {
            String query = "INSERT INTO `Affix`\n" +
                    "(`affixName`,\n" +
                    "`affixDisplayName`,\n" +
                    "`affixTitle`,\n" +
                    "`affixId`,\n" +
                    "`levelRequirement`,\n" +
                    "`rollsOn`,\n" +
                    "`classSpecificity`,\n" +
                    "`type`,\n" +
                    "`standardAffixEffectModifier`,\n" +
                    "`rerollChance`,\n" +
                    "`weaponEffect`,\n" +
                    "`group`,\n" +
                    "`shardHueShift`,\n" +
                    "`shardSaturationModifier`,\n" +
                    "`canRollOn`,\n" +
                    "`property`,\n" +
                    "`specialTag`,\n" +
                    "`tags`,\n" +
                    "`modifierType`,\n" +
                    "`setProperty`)\n" +
                    " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query, new String[]{ "idAffix" });
            preparedStmt.setString(1, affix.getAffixName());
            preparedStmt.setString(2, affix.getAffixDisplayName());
            preparedStmt.setString(3, affix.getAffixTitle());
            preparedStmt.setInt(4, affix.getAffixId());
            preparedStmt.setInt(5, affix.getLevelRequirement());
            preparedStmt.setInt(6, affix.getRollsOn());
            preparedStmt.setInt(7, affix.getClassSpecificity());
            preparedStmt.setInt(8, affix.getType());
            preparedStmt.setFloat(9, affix.getStandardAffixEffectModifier());
            preparedStmt.setFloat(10, affix.getRerollChance());
            preparedStmt.setInt(11, affix.getWeaponEffect());
            preparedStmt.setInt(12, affix.getGroup());
            preparedStmt.setFloat(13, affix.getShardHueShift());
            preparedStmt.setFloat(14, affix.getShardSaturationModifier());
            preparedStmt.setString(15, affix.getCanRollOn());
            preparedStmt.setInt(16, affix.getProperty());
            preparedStmt.setInt(17, affix.getSpecialTag());
            preparedStmt.setInt(18, affix.getTags());
            preparedStmt.setInt(19, affix.getModifierType());
            preparedStmt.setInt(20, affix.getSetProperty());

            preparedStmt.execute();

            ResultSet generatedKeys = preparedStmt.getGeneratedKeys();

            int idAffix = -1;
            if (generatedKeys.next()) {
                idAffix = generatedKeys.getInt(1);
            }

            for (Affix.Tier tier: affix.getTiers()) {
                query = "INSERT INTO `Tier`\n" +
                        "(`idAffix`,\n" +
                        "`tierLevel`,\n" +
                        "`requiredLevel`,\n" +
                        "`minRoll`,\n" +
                        "`maxRoll`,\n" +
                        "`extraRollMinRoll`,\n" +
                        "`extraRollMaxRoll`)\n" +
                        " values (?, ?, ?, ?, ?, ?, ?)";

                preparedStmt = connection.prepareStatement(query);

                preparedStmt.setInt(1, idAffix);
                preparedStmt.setInt(2, tier.getTierLevel());
                preparedStmt.setInt(3, tier.getRequiredLevel());
                preparedStmt.setFloat(4, tier.getMinRoll());
                preparedStmt.setFloat(5, tier.getMaxRoll());
                preparedStmt.setFloat(6, tier.getExtraRollMinRoll());
                preparedStmt.setFloat(7, tier.getExtraRollMaxRoll());
                preparedStmt.execute();
            }
        }
    }

    public void populate(List<Item> items, List<Property> properties, List<Affix> affixes) throws SQLException, IOException, ClassNotFoundException {
        connect();

        populateBaseItem(items);
        populateItems(items);
        populateProperties(properties);
        populateAffixes(affixes);

        close();
    }
}
