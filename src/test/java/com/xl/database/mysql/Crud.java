package com.xl.database.mysql;

import com.xl.util.MysqlJdbcUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;

//基于JDBC的CURD操作
//exceute(sql);除了select操作返回true之外，其他操作都返回false。
//Statement没有安全性检测。s
public class Crud {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    @Test
    public void create() {
        String sql = "insert into user(name,gender,salary) values('张三','男',3000)";
        try {
            conn = MysqlJdbcUtil.getMySqlConnection();
            stmt = conn.createStatement();
            int i = stmt.executeUpdate(sql);
            System.out.println(i);
            System.out.println(i > 0 ? "成功" : "失败");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MysqlJdbcUtil.close(rs);
            MysqlJdbcUtil.close(stmt);
            MysqlJdbcUtil.close(conn);
        }
    }

    @Test
    public void read() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select * from user where gender='male'";
        try {
            conn = MysqlJdbcUtil.getMySqlConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                System.out.println(name + ":" + gender);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MysqlJdbcUtil.close(rs);
            MysqlJdbcUtil.close(stmt);
            MysqlJdbcUtil.close(conn);
        }
    }

    @Test
    public void update() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "update user set gender='female' where id = 8";
        try {
            conn = MysqlJdbcUtil.getMySqlConnection();
            stmt = conn.createStatement();
            int i = stmt.executeUpdate(sql);
            System.out.println(i > 0 ? "成功" : "失败");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MysqlJdbcUtil.close(rs);
            MysqlJdbcUtil.close(stmt);
            MysqlJdbcUtil.close(conn);
        }
    }

    @Test
    public void delete() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "delete from user where name = '丝丝'";
        try {
            conn = MysqlJdbcUtil.getMySqlConnection();
            stmt = conn.createStatement();
            int i = stmt.executeUpdate(sql);
            System.out.println(i > 0 ? "成功" : "失败");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MysqlJdbcUtil.close(rs);
            MysqlJdbcUtil.close(stmt);
            MysqlJdbcUtil.close(conn);
        }
    }

    @Test
    public void read(String name) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select * from user where name='" + name + "'";
        System.out.println("sql=" + sql);
        try {
            conn = MysqlJdbcUtil.getMySqlConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                // while(rs!=null)
                name = rs.getString("name");
                String gender = rs.getString("gender");
                System.out.println(name + ":" + gender);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MysqlJdbcUtil.close(rs);
            MysqlJdbcUtil.close(stmt);
            MysqlJdbcUtil.close(conn);
        }
    }

    @Test
    public void createTable() {
        String tableName = "xl";
        String sql = "create table if not exists " + tableName + "(id varchar(40) primary key)";
        try {
            conn = MysqlJdbcUtil.getMySqlConnection();
            stmt = conn.createStatement();
            System.out.println(sql);
            // int i= stmt.executeUpdate(sql); //创建表并没有影响结果所以i的值是0
            boolean i = stmt.equals(sql);
            System.out.println(i); // false
            System.out.println("创建成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlJdbcUtil.close(rs);
            MysqlJdbcUtil.close(stmt);
            MysqlJdbcUtil.close(conn);
        }
    }

    @Test
    public void dropTable() {
        String tableName = "xl";
        String sql = "drop table if exists " + "xl";
        try {
            conn = MysqlJdbcUtil.getMySqlConnection();
            stmt = conn.createStatement();
            boolean b = stmt.execute(sql);
            System.out.println(b); // false
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // public static void callstored(String[] args) {
    // Crud crud = new Crud();
    // // crud.read(" 'or true or' ");
    // crud.createTable("system");
    // crud.dropTable("system");
    // }
}
