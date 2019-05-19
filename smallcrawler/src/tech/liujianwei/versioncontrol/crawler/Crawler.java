package tech.liujianwei.versioncontrol.crawler;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Crawler {
    public Set<String> crawl(String url) throws IOException {
        String json = Jsoup.connect(url).execute().body();
        return versionValueOf(json);
    }

    private Set<String> versionValueOf(String json) {
        Set<String> results = new HashSet<String>();
        JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            results.add(jsonArray.get(i).getAsJsonObject().get("tag_name").getAsString());
        }
        return results;
    }
}
