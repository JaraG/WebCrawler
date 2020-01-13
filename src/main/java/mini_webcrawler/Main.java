package mini_webcrawler;

import mini_webcrawler.profile.Profile;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Profile> profiles = new LinkedList<>();

        CrawlerLauncher launcher = new CrawlerLauncher();
        launcher.launch(profiles);

        profiles.forEach(System.out::println);
    }
}
