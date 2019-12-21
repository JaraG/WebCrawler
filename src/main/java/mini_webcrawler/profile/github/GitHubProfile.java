package mini_webcrawler.profile.github;

import mini_webcrawler.profile.Profile;

import java.util.LinkedHashMap;
import java.util.Map;

public class GitHubProfile implements Profile {
    private String login;
    private String name;
    private String company;
    private String location;
    private String mostPopularLanguage;
    private String mostPopularRepository;
    private int starsInMostPopRepo;
    private String programmingLangInMostPopRepo;

    GitHubProfile() {
    }

    void setLogin(String login) {
        this.login = login;
    }

    void setName(String name) {
        this.name = name;
    }

    void setCompany(String company) {
        this.company = company;
    }

    void setLocation(String location) {
        this.location = location;
    }

    void setMostPopularLanguage(String mostPopularLanguage) {
        this.mostPopularLanguage = mostPopularLanguage;
    }

    void setMostPopularRepository(String mostPopularRepository) {
        this.mostPopularRepository = mostPopularRepository;
    }

    void setStarsInMostPopRepo(int starsInMostPopRepo) {
        this.starsInMostPopRepo = starsInMostPopRepo;
    }

    void setProgrammingLangInMostPopRepo(String programmingLangInMostPopRepo) {
        this.programmingLangInMostPopRepo = programmingLangInMostPopRepo;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getMostPopularLanguage() {
        return mostPopularLanguage;
    }

    public String getMostPopularRepository() {
        return mostPopularRepository;
    }

    public int getStarsInMostPopRepo() {
        return starsInMostPopRepo;
    }

    public String getProgrammingLangInMostPopRepo() {
        return programmingLangInMostPopRepo;
    }

    public Map<String, String> getProfileProperties() {
        Map<String, String> properties = new LinkedHashMap<>();
        properties.put("login", login);
        properties.put("name", name);
        properties.put("company", company);
        properties.put("location", location);
        properties.put("mostPopularLanguage", mostPopularLanguage);
        properties.put("mostPopularRepository", mostPopularRepository);
        properties.put("starsInMostPopRepository", String.valueOf(starsInMostPopRepo));
        properties.put("ProgrammingLangInMostPopRepository", programmingLangInMostPopRepo);
        return properties;
    }

    @Override
    public String toString() {
        return String.format("login=%s; name=%s; company=%s; location=%s; mostPopularLanguage=%s; mostPopularRepository=%s; " +
                "starsInMostPopRepository=%s; ProgrammingLangInMostPopRepository=%s", login, name, company, location, mostPopularLanguage,
                mostPopularRepository, starsInMostPopRepo, programmingLangInMostPopRepo);
    }
}
