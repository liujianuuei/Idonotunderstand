package tech.liujianwei.versioncontrol;

import tech.liujianwei.versioncontrol.crawler.Crawler;
import tech.liujianwei.versioncontrol.db.SQLite;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class VersionControlSystem {

    private Set<String> versions = new HashSet<>();

    public Set<String> getVersions() {
        try {
            if (this.versions.isEmpty()) {
                versions.addAll(SQLite.instance().query());
            }
            return versions;
        } catch (SQLException e) {
            // Error handling goes here
            e.printStackTrace();
            return null;
        }
    }

    public void saveVersions() {
        try {
            this.versions = new Crawler().crawl("https://api.github.com/repos/labstack/echo/releases");
            SQLite.instance().insertIfNotExist(versions);
        } catch (SQLException | IOException e) {
            // Error handling goes here
            e.printStackTrace();
        }
    }

    public boolean checkVersion(String version) {
        if (getVersions().contains(version)) {
            return true;
        }
        return false;
    }

    public void shutdown() {
        SQLite.instance().close();
    }
}
