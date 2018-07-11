package com.xl.database.oracle;

import com.xl.util.ResourceUtil;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class OracleTest {
    private static String driver;
    private static String name;
    private static String url;
    private static String password;
    static {
        try {
            Properties props = new Properties();
            InputStream is = ResourceUtil.getResourceInputStream("oracle/oracle.properties");
            props.load(is);
            driver = props.getProperty("driver");
            url = props.getProperty("url");
            name = props.getProperty("user");
            password = props.getProperty("password");
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            Connection ct = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", name, password);
            Statement sm = ct.createStatement();
            ResultSet rs = sm.executeQuery("select '测试' from dual");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            rs.close();
            sm.close();
            ct.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void callstored(String[] args) {
        Connection ct = null;
        CallableStatement cs = null;
        try {
            ct = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL", "xl", "xl");
            cs = ct.prepareCall("{call sp_pro8(?,?,?,?)}");
            //?????????setProperty
            cs.setInt(1, 7788);
            //?????????setProperty?????????,??????��???OracleTypes.CURSOR
            cs.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            cs.registerOutParameter(3, oracle.jdbc.OracleTypes.DOUBLE);
            cs.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            // ???
            cs.execute();
            //			????????,?????????
            String name = cs.getString(2);
            double sal = cs.getDouble(3);
            String job = cs.getString(4);
            System.out.println("???????" + name + "��????" + sal + "??????????" + job);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                cs.close();
                ct.close();
                System.out.println("???????");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void callstored2(String[] args) {
        Connection ct = null;
        CallableStatement cs = null;
        try {
            // 1.????????
            Class.forName("oracle.jdbc.driver.OracleDriver");
            ct = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL", "xl", "xl");
            System.out.println("??????");
            // 2.????CallableStatement,?��???????????????
            cs = ct.prepareCall("{call sp_pro9(?,?)}");
            // ?????????setProperty??????10?????
            cs.setInt(1, 10);
            // ?????????setProperty?????????
            cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            // ???
            cs.execute();
            // ????????,?????????
            String name = cs.getString(2);
            // ????????
            ResultSet rs = (ResultSet) cs.getObject(2);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                cs.close();
                ct.close();
                System.out.println("???????");
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }
}
