package mini_webcrawler;

import mini_webcrawler.profile.Profile;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Profile> profiles = new ArrayList<>();

        CrawlerLauncher launcher = new CrawlerLauncher();
        launcher.launch(profiles);

        for (Profile profile :
                profiles) {
            System.out.println(profile);
        };
    }
}
