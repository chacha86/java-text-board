package org.example.base.dbms;

import org.example.domain.article.entity.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void execute(String sql, Object... params) {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;

        assert conn != null;
        assert sql != null;

        try {
            pstmt = conn.prepareStatement(sql);
            setParam(pstmt, params);
            pstmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public <T> T getOne(String sql, MyMapper<T> mapper, Object... params) {
        List<T> resultList = getList(sql, mapper, params);
        if (resultList.isEmpty()) {
            return null;
        }

        return resultList.get(0);
    }

    public <T> List<T> getList(String sql, MyMapper<T> mapper, Object... params) {

        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<T> resultList = new ArrayList<>();

        assert conn != null;
        assert sql != null;

        try {

            pstmt = conn.prepareStatement(sql);
            setParam(pstmt, params);
            rs = pstmt.executeQuery();
            resultList = mapper.getList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(pstmt);
            close(conn);
        }

        return resultList;
    }

    private void setParam(PreparedStatement pstmt, Object[] params) throws SQLException {

        for (int i = 1; i <= params.length; i++) {
            if (params[i - 1] instanceof String) {
                pstmt.setString(i, (String) params[i - 1]);
            }
            else  {
                pstmt.setInt(i, (int) params[i - 1]);
            }
        }
    }

    public void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
