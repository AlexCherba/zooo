package com.vidwel.zoo;

import java.sql.*;
import java.sql.Date;
import java.util.*;

final class DbTools {
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "zoo_db";
    private static final String DB_NAME_ROOT = "mysql";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?useSSL=false";
    private static final String DB_URL_ROOT = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME_ROOT + "?useSSL=false";

    static void init() {
        String str1 = "CREATE DATABASE IF NOT EXISTS `" + DB_NAME + "` CHARACTER SET 'utf8'";
        String str2 = "USE `" + DB_NAME + "`";
        String str3 = "CREATE TABLE IF NOT EXISTS `" + DB_NAME + "`.`tb_type` (" +
                "  `ID_TYPE` INT(11) NOT NULL AUTO_INCREMENT," +
                "  `NAME` VARCHAR(60) NOT NULL," +
                "  `MAX_AGE` INT(3) NULL DEFAULT NULL," +
                "  `LOCATION` VARCHAR(100) NULL DEFAULT NULL," +
                "  `PHOTO` BLOB NULL DEFAULT NULL," +
                "  `COMMENT` VARCHAR(1000) NULL DEFAULT NULL," +
                "  PRIMARY KEY (`ID_TYPE`)," +
                "  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC)) " +
                "ENGINE = InnoDB " +
                "DEFAULT CHARACTER SET = utf8;";
        String str4 = "CREATE TABLE IF NOT EXISTS `" + DB_NAME + "`.`tb_zoo` (" +
                "  `ID_ZOO` INT(11) NOT NULL AUTO_INCREMENT," +
                "  `NAME` VARCHAR(40) NOT NULL," +
                "  `ADDRESS` VARCHAR(100) NULL DEFAULT NULL," +
                "  PRIMARY KEY (`ID_ZOO`)," +
                "  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC)," +
                "  UNIQUE INDEX `ID_ZOO_UNIQUE` (`ID_ZOO` ASC)) " +
                "ENGINE = InnoDB " +
                "AUTO_INCREMENT = 3 " +
                "DEFAULT CHARACTER SET = utf8;";
        String str5 = "CREATE TABLE IF NOT EXISTS `" + DB_NAME + "`.`tb_animal` (" +
                "  `ID_ANIMAL` INT(11) NOT NULL AUTO_INCREMENT," +
                "  `ID_TYPE` INT(11) NULL DEFAULT NULL," +
                "  `ID_ZOO` INT(11) NULL DEFAULT NULL," +
                "  `NAME` VARCHAR(40) NULL DEFAULT NULL," +
                "  `AGE` SMALLINT(3) NULL DEFAULT NULL," +
                "  `DATE_ADD` DATE NULL DEFAULT NULL," +
                "  `PHOTO` BLOB NULL DEFAULT NULL," +
                "  `COMMENT` VARCHAR(1000) NULL DEFAULT NULL," +
                "  PRIMARY KEY (`ID_ANIMAL`)," +
                "  INDEX `ID_ZOO_idx` (`ID_ZOO` ASC)," +
                "  INDEX `ID_TYPE_idx` (`ID_TYPE` ASC)," +
                "  CONSTRAINT `ID_TYPE`" +
                "    FOREIGN KEY (`ID_TYPE`)" +
                "    REFERENCES `zoo_db`.`tb_type` (`ID_TYPE`)" +
                "    ON DELETE NO ACTION" +
                "    ON UPDATE NO ACTION," +
                "  CONSTRAINT `ID_ZOO`" +
                "    FOREIGN KEY (`ID_ZOO`)" +
                "    REFERENCES `zoo_db`.`tb_zoo` (`ID_ZOO`)" +
                "    ON DELETE NO ACTION" +
                "    ON UPDATE NO ACTION) " +
                "ENGINE = InnoDB " +
                "DEFAULT CHARACTER SET = utf8;";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection dbConnection = getConnection();
             Statement statement = dbConnection.createStatement()) {
        } catch (SQLException e) {
            try (Connection dbConnection = DriverManager.getConnection(DB_URL_ROOT, DB_USERNAME, DB_PASSWORD);
                 Statement statement = dbConnection.createStatement()) {
                statement.execute(str1);
                statement.execute(str2);
                statement.execute(str3);
                statement.execute(str4);
                statement.execute(str5);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }


    //preparedStatement.setTimestamp(4, getCurrentTimeStamp());


    static boolean isValue(Connection dbConnection, String table, String column, String value) throws SQLException {
        String str = "SELECT * FROM " + DB_NAME + "." + table + " WHERE " + column + " = ? ;";
        //String str = "SELECT * FROM zoo_db.tb_zoo WHERE ADDRESS ='Киев1';";
        //System.out.println(str);
        PreparedStatement ps = dbConnection.prepareStatement(str);
        ps.setString(1, value);
        if (ps.executeQuery().next()) return true;
        return false;
    }

    static List<Map<Integer, String>> getSelect(String str) {
        //String str = "SELECT * FROM " + DB_NAME + ".tb_zoo ;";
        List<Map<Integer, String>> resultMapList = new ArrayList<Map<Integer, String>>();
        try (Connection dbConnection = getConnection(); Statement statement = dbConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(str);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numberOfColumns = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                Map<Integer, String> map = new TreeMap<Integer, String>();
                for (int column = 1; column <= numberOfColumns; column++) {
                    map.put(column, resultSet.getString(column));
                    //if (column == numberOfColumns) resultMapList.add(map);
                }
                resultMapList.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultMapList;
    }

    static List<Map<Integer, String>> getAllZoo() {
        String str = "SELECT * FROM " + DB_NAME + ".tb_zoo ;";
        return getSelect(str);
    }

    static boolean addZoo(String name, String address) {
        String str = "INSERT INTO `" + DB_NAME + "`.`tb_zoo` (NAME,ADDRESS) VALUES(?,?);";
        try (Connection dbConnection = getConnection()) {
            boolean valueExist = isValue(dbConnection, "tb_zoo", "NAME", name);
            System.out.println("checkValue: " + valueExist);
            if (!valueExist) {
                PreparedStatement ps = dbConnection.prepareStatement(str);
                ps.setString(1, name);
                ps.setString(2, address);
                ps.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    static boolean addType(String table, Map<String, String> map) {
        String str = "INSERT INTO `" + DB_NAME + "`.`" + table + "` (";
        for (String column : map.keySet()) {
            str += column;
        }
        str += ") VALUES(";
        //String str = "INSERT INTO `" + DB_NAME + "`.`tb_type` (NAME,MAX_AGE,LOCATION,PHOTO,COMMENT) VALUES(?,?,?,?,?);";
        //String str = "INSERT INTO `" + DB_NAME + "`.`tb_type` (NAME,MAX_AGE,LOCATION,PHOTO,COMMENT) VALUES(?,?,?,?,?);";
        try (Connection dbConnection = getConnection()) {
            boolean valueExist = isValue(dbConnection, "tb_type", "NAME", typeMap.get("NAME"));
            System.out.println("checkValue: " + valueExist);
            if (!valueExist) {
                PreparedStatement ps = dbConnection.prepareStatement(str);
                ps.setString(1, typeMap.get("NAME"));
                ps.setString(2, typeMap.get("MAX_AGE"));
                ps.setString(3, typeMap.get("LOCATION"));
                ps.setString(4, typeMap.get("PHOTO"));
                ps.setString(5, typeMap.get("COMMENT"));
                ps.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    static boolean addTypeAnimal(String type, String location, String maxAge, Object photo, String comment) {
        return false;
    }

    static boolean addAnimal(int idZoo, int idType, String name, String age, Date date, String address) {
        return false;
    }

}