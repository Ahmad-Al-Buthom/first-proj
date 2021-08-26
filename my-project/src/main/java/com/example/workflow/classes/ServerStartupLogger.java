package com.example.workflow.classes;

import java.sql.*;

public class ServerStartupLogger {
    public ServerStartupLogger() {
    }

    public void serverLogTimestamp() {
        printLines(2);
        try {

            Connection con = getConObj();

            insertIntoTimestamp(con);

            getTimestampData(con);

        } catch (Exception e) {
            System.out.println(e);
        }
        printLines(2);
    }

    private void insertIntoTimestamp(Connection con) throws SQLException {
        printLines(1);
        System.out.println("insertIntoTimestamp");
        Statement stmt = con.createStatement();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String sql = "INSERT INTO `startuplog` (`timestamp`) VALUES ( '" + timestamp + "')";

        stmt.executeUpdate(sql);

        System.out.println("Inserted records into the table...");

        printLines(1);

    }

    private void getTimestampData(Connection con) throws SQLException {
        printLines(1);
        System.out.println("getTimestampData");

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("select * from startuplog");

        int[] indexArr = new int[]{1, 2};

        printQueeryOutput(con, rs, indexArr);

        printLines(1);

    }


    private void printQueeryOutput(Connection con, ResultSet rs, int[] columnIndexes) throws SQLException {
        while (rs.next()) {
            for (int i = 0; i < columnIndexes.length; i++) {
                System.out.print(rs.getString(columnIndexes[i]) + "  ");
            }
            System.out.println();
        }
        con.close();
    }

    private void printLines(int numOfLines) {
        for (int i = numOfLines; i > 0; i--) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        }
        System.out.println();
    }

    private Connection getConObj() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/camundadb", "root", "");
        return con;
    }

}
