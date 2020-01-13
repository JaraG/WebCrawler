package mini_webcrawler.profile.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
Class is used to map response from https://api.github.com/users/:user/repos using JACKSON ObjectMapper
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryOverview {
    private String name;
    private String stargazers_count;
    private String language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(String stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
