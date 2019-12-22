package mini_webcrawler.crawler;

import mini_webcrawler.profile.Profile;
import mini_webcrawler.profile.ProfileBuilder;
import mini_webcrawler.profile.github.GitHubProfileBuilder;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WebCrawler {
    private Queue<String> URLs;
    private List<Profile> profiles;
    private final int maxParallelRequests;
    private final int requestDelayMs;

    public WebCrawler(Queue<String> URLs, List<Profile> profiles, int maxParallelRequests, int requestDelayMs) {
        this.URLs = URLs;
        this.profiles = profiles;
        this.maxParallelRequests = maxParallelRequests;
        this.requestDelayMs = requestDelayMs;
    }

    /*
    Initializes ExecutorService and awaits Termination
     */
    public void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(maxParallelRequests);

        for (int i = 0; i < maxParallelRequests; i++) {
            executorService.submit(new CrawlThread());
        }
        executorService.shutdown();

        boolean isWaiting = true;
        while (isWaiting) {
            try {
                executorService.awaitTermination(10, TimeUnit.MINUTES);
                isWaiting = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /*
    Represents a single crawl thread
     */
    class CrawlThread extends Thread {

        /*
        While URls queue is not empty reads URL string from queue, builds Profile and adds it to List of profiles.
        After each iteration sleeps @requestDelayMs milliseconds.
         */
        @Override
        public void run() {
            String url = null;
            boolean urlIsRead = false;

            while (!URLs.isEmpty()) {
                synchronized (URLs) {
                    if (!URLs.isEmpty()){
                        url = URLs.remove();
                        urlIsRead = true;
                    }
                }
                if (urlIsRead) {
                    ProfileBuilder profileBuilder = new GitHubProfileBuilder(url);
                    Profile profile = profileBuilder.getProfile();
                    synchronized (profiles) {
                        profiles.add(profile);
                    }
                    urlIsRead = false;
                }

                boolean isSleeping = true;
                while (isSleeping) {
                    try {
                        Thread.sleep(requestDelayMs);
                        isSleeping = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
