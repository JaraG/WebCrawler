package mini_webcrawler.profile.github;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import mini_webcrawler.profile.Profile;
import mini_webcrawler.profile.ProfileBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GitHubProfileBuilder implements ProfileBuilder {

    private GitHubProfile GitHubProfile;
    private final String profileURLString;
    private final ObjectMapper mapper;
    private ProfileOverview profileOverview;
    private RepositoryOverview[] repos;

    public GitHubProfileBuilder(String profileURLString) {
        this.profileURLString = profileURLString;
        this.mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
    }

    @Override
    public Profile getProfile() {
        if (GitHubProfile == null)
            build();
        return GitHubProfile;
    }

    private void build() {
        GitHubProfile = new GitHubProfile();

        fillProfileOverview(profileURLString);
        fillArrayOfRepositories();
        setMostPopularLanguage();
        setMostPopularRepoProperties();
        mergeProfileOverview();
    }

    /*
       Initializes ProfileOverview class
     */
    private void fillProfileOverview(String profileURLString) {
        String[] parts = profileURLString.split("/");
        String login = parts[parts.length - 1];
        ProfileOverview profileOverview = null;
        try {
            URL profileURL = new URL("https://api.github.com/users/" + login);
            profileOverview = mapper.readValue(profileURL, ProfileOverview.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.profileOverview = profileOverview;
    }

    /*
    Initializes array of repositories of current GitHub profile
     */
    private void fillArrayOfRepositories() {
        RepositoryOverview[] repos = null;

        try {
            URL reposURLs = new URL("https://api.github.com/users/" + profileOverview.getLogin() + "/repos");
            repos = mapper.readValue(reposURLs, RepositoryOverview[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.repos = repos;
    }

    /*
    Searches and sets up the most popular language among main languages of all user repositories
     */
    private void setMostPopularLanguage() {
        Map<String, Integer> eachLangOccur = new HashMap<>();

        for (RepositoryOverview repo :
                repos) {
            String lang = repo.getLanguage();
            if (eachLangOccur.containsKey(lang))
                eachLangOccur.put(lang, eachLangOccur.get(lang) + 1);
            else
                eachLangOccur.put(lang, 1);
        }

        String mostPopLang = null;
        int langCounter = 0;
        for (String lang :
                eachLangOccur.keySet()) {
            if (eachLangOccur.get(lang) > langCounter) {
                langCounter = eachLangOccur.get(lang);
                mostPopLang = lang;
            }
        }
        GitHubProfile.setMostPopularLanguage(mostPopLang);
    }

    /*
    Searches and sets up the most popular repository of current gitHub profile,
    stars in that repository and main language of that repository
     */
    private void setMostPopularRepoProperties() {
        String language = null;
        String mostPopRepo = null;
        int starsMax = 0;
        for (RepositoryOverview repo :
                repos) {
            int currentStars = Integer.parseInt(repo.getStargazers_count());
            if (currentStars > starsMax) {
                starsMax = currentStars;
                mostPopRepo = repo.getName();
                language = repo.getLanguage();
            }
        }
        GitHubProfile.setMostPopularRepository(mostPopRepo);
        GitHubProfile.setStarsInMostPopRepo(starsMax);
        GitHubProfile.setProgrammingLangInMostPopRepo(language);
    }

    /*
    merging profileOverview's properties to GitHubProfile's properties
     */
    private void mergeProfileOverview() {
        GitHubProfile.setLogin(profileOverview.getLogin());
        GitHubProfile.setCompany(profileOverview.getCompany());
        GitHubProfile.setName(profileOverview.getName());
        GitHubProfile.setLocation(profileOverview.getLocation());
    }


}

    /*
    Нахождение наиболее популярного среди всех языков, встречающихся во всех репозиториях пользователя
     */

//    private void setMostPopularLanguage(String login) {
//        Map<String, Integer> eachLangOccur = new HashMap<>();
//        for (RepositoryOverview repo :
//                repos) {
//            try {
//                URL langURL = new URL("https://api.github.com/repos/" + login + "/" + repo.getName() + "/languages");
//                Map<String, String> JSONmap = mapper.readValue(langURL, new TypeReference<Map<String, String>>() {
//                });
//                for (String lang :
//                        JSONmap.keySet()) {
//                    if (eachLangOccur.containsKey(lang))
//                        eachLangOccur.put(lang, eachLangOccur.get(lang) + 1);
//                    else
//                        eachLangOccur.put(lang, 1);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        String mostPopLang = null;
//        int langCounter = 0;
//        for (String lang :
//                eachLangOccur.keySet()) {
//            if (eachLangOccur.get(lang) > langCounter) {
//                langCounter = eachLangOccur.get(lang);
//                mostPopLang = lang;
//            }
//        }
//        this.mostPopularLanguage = mostPopLang;
//    }
