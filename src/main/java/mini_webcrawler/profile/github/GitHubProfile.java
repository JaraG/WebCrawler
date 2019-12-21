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
    private String ProgrammingLangInMostPopRepo;

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
        ProgrammingLangInMostPopRepo = programmingLangInMostPopRepo;
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
        return ProgrammingLangInMostPopRepo;
    }

    public Map<String, String> getProfileProperties() {
        Map<String, String> properties = new LinkedHashMap<>();
        properties.put("login", getLogin());
        properties.put("name", getName());
        properties.put("company", getCompany());
        properties.put("location", getLocation());
        properties.put("mostPopularLanguage", getMostPopularLanguage());
        properties.put("mostPopularRepository", getMostPopularRepository());
        properties.put("starsInMostPopRepository", String.valueOf(getStarsInMostPopRepo()));
        properties.put("ProgrammingLangInMostPopRepository", getProgrammingLangInMostPopRepo());
        return properties;
    }

    @Override
    public String toString() {
        return String.format("login=%s, name=%s, company=%s, location=%s, mostPopularLanguage=%s, mostPopularRepository=%s, " +
                "starsInMostPopRepository=%s, ProgrammingLangInMostPopRepository=%s", getLogin(), getName(), getCompany(), getLocation(), getMostPopularLanguage(),
                getMostPopularRepository(), getStarsInMostPopRepo(), getProgrammingLangInMostPopRepo());
    }
}
