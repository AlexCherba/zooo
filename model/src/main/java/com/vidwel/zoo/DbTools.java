package com.vidwel.zoo;

import java.sql.*;
import java.util.List;

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
        String str3 = "CREATE TABLE IF NOT EXISTS `" + DB_NAME + "`.`tb_animal` (" +
                "  `ID_ANIMAL` INT(11) NOT NULL AUTO_INCREMENT," +
                "  `TYPE` VARCHAR(60) NOT NULL," +
                "  `LOCATION` VARCHAR(100) NULL DEFAULT NULL," +
                "  `MAX_AGE` INT(3) NULL DEFAULT NULL," +
                "  `PHOTO` BLOB NULL DEFAULT NULL," +
                "  `COMMENT` VARCHAR(1000) NULL DEFAULT NULL," +
                "  PRIMARY KEY (`ID_ANIMAL`)," +
                "  UNIQUE INDEX `TYPE_UNIQUE` (`TYPE` ASC)) " +
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
        String str5 = "CREATE TABLE IF NOT EXISTS `" + DB_NAME + "`.`tb_animals` (" +
                "  `ID_ANIMALS` INT(11) NOT NULL AUTO_INCREMENT," +
                "  `ID_ZOO` INT(11) NULL DEFAULT NULL," +
                "  `ID_ANIMAL` INT(11) NULL DEFAULT NULL," +
                "  `NAME` VARCHAR(40) NULL DEFAULT NULL," +
                "  `AGE` SMALLINT(3) NULL DEFAULT NULL," +
                "  `DATE_ADD` DATE NULL DEFAULT NULL," +
                "  `PHOTO` BLOB NULL DEFAULT NULL," +
                "  `COMMENT` VARCHAR(1000) NULL DEFAULT NULL," +
                "  PRIMARY KEY (`ID_ANIMALS`)," +
                "  INDEX `ID_ZOO_idx` (`ID_ZOO` ASC)," +
                "  INDEX `ID_ANIMAL_idx` (`ID_ANIMAL` ASC)," +
                "  CONSTRAINT `ID_ANIMAL`" +
                "    FOREIGN KEY (`ID_ANIMAL`)" +
                "    REFERENCES `zoo_db`.`tb_animal` (`ID_ANIMAL`)" +
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

    static List getAllZoo() throws SQLException {
        String str = "SELECT * FROM " + DB_NAME + ".tb_zoo ;";
        try (Connection dbConnection = getConnection()) {
            PreparedStatement ps = dbConnection.prepareStatement(str);
            ps.execute();
            return true;
        }
    } catch(
    SQLException e)

    {
        e.printStackTrace();
    }

        return;
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

    //INSERT INTO `zoo_db`.`tb_zoo` (NAME,ADDRESS) VALUES('Харьковский зоопарк','Харьков');

    static boolean addTypeAnimal(String type, String location, String maxAge, Object photo, String comment) {
        return false;
    }

    static boolean addAnimal(int idZoo, int idType, String name, String age, Date date, String address) {
        return false;
    }

}