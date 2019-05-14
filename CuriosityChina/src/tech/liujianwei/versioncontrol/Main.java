package tech.liujianwei.versioncontrol;

public class Main {
    public static void main(String[] args) {
        VersionControlSystem vcs = new VersionControlSystem();
        vcs.saveVersions();

        String v = "v3.3.3";
        boolean ifExist = vcs.checkVersion(v);
        System.out.println(ifExist ? v + " exists" : v + " not exists");

        vcs.shutdown();
    }
}
