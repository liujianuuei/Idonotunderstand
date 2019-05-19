package tech.liujianwei.versioncontrol.db;

import java.sql.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SQLite {

    private static volatile SQLite instance;

    private SQLite() throws Throwable {
        if (instance != null) {
            throw new AssertionError("single instance already exits");
        } else {
            synchronized (SQLite.class) {
                if (instance != null) {
                    throw new AssertionError("single instance already exits");
                }
                createTableIfNotExist();
                instance = this;
            }
        }
    }

    public static SQLite instance() {
        if (instance == null) {
            synchronized (SQLite.class) {
                if (instance == null) {
                    try {
                        instance = new SQLite();
                    } catch (Throwable willNotHappen) {
                        willNotHappen.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }

    private Connection conn;

    private Connection connect() throws SQLException { // should use pool
        String url = "jdbc:sqlite:C:/sqlite/db/vcs.db";
        if (this.conn == null) {
            this.conn = DriverManager.getConnection(url);
        }
        return this.conn;
    }

    public void close() { // should release pool
        try {
            if (this.conn != null) {
                this.conn.close();
            }
        } catch (SQLException e) {
            // do nothing
        }
    }

    private void createTableIfNotExist() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS versions(id INTEGER PRIMARY KEY AUTOINCREMENT, repo text NOT NULL, version text NOT NULL UNIQUE);";
        try (Statement stmt = connect().createStatement()) {
            stmt.execute(sql);
        }
    }

    public void insert(String version) throws SQLException {
        String sql = "INSERT INTO versions(repo, version) VALUES(?, ?)";
        try (PreparedStatement pstmt = connect().prepareStatement(sql)) {
            pstmt.setString(1, "echo");
            pstmt.setString(2, version);
            pstmt.executeUpdate();
        }
    }

    public void insertIfNotExist(Set<String> values) throws SQLException {
        String sql = "INSERT INTO versions(repo, version) VALUES(?, ?)";
        values.removeAll(query()); // should compare with `REPLACE INTO` for high performance
        try (PreparedStatement pstmt = connect().prepareStatement(sql)) {
            Iterator<String> it = values.iterator();
            while (it.hasNext()) {
                pstmt.setString(1, "echo");
                pstmt.setString(2, it.next());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
    }

    public Set<String> query() throws SQLException {
        Set<String> results = new HashSet<>();
        String sql = "SELECT version FROM versions;";
        try (Statement stmt = connect().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                results.add(rs.getString("version"));
            }
        }
        return results;
    }

    private void dump(Set<String> values) {
        Iterator<String> it = values.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
