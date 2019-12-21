package mini_webcrawler;

import mini_webcrawler.crawler.WebCrawler;
import mini_webcrawler.profile.Profile;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Class is used to read environment variables, set up URLs queue from input source and launch WebCrawler
 */
public class CrawlerLauncher {

    public void launch(List<Profile> profiles) {
        int maxParallelRequests = Integer.valueOf(System.getenv("crawler.maxParallelRequests"));
        int requestDelayMs = Integer.valueOf(System.getenv("crawler.requestDelayMs"));

        Queue<String> URLs = createURLsQueue();

        WebCrawler crawler = new WebCrawler(URLs, profiles, maxParallelRequests, requestDelayMs);
        crawler.start();
    }

    /*
    Creates queue of URLs using input file
     */
    private Queue<String> createURLsQueue() {
        String FileDirectory = System.getenv("crawler.fileDirectory");
        String FileName = System.getenv("crawler.fileName");
        Queue<String> URLs = new LinkedList<>();
        Path path = FileSystems.getDefault().getPath(FileDirectory, FileName);
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                URLs.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return URLs;
    }
}
