package org.feisoft.common.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3P0Util {

    private static ComboPooledDataSource ds = new ComboPooledDataSource();

    private static Connection conn;

    /*
     * 获取数据库连接对象
     */
    public static Connection getConnection(String driverClass, String url, String user, String pass) {
        try {
            ds.setDriverClass(driverClass);
            ds.setJdbcUrl(url);
            ds.setUser(user);
            ds.setPassword(pass);
            ds.setInitialPoolSize(5);
            ds.setMinPoolSize(2);
            ds.setMaxPoolSize(20);
            conn = ds.getConnection();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    /*
     * 封装数据库相关资源的关闭工作
     */
    public static void close(ResultSet rs, Statement st, Connection conn) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (st != null) {
            st.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
