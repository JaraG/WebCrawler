package mini_webcrawler.profile.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
Class is used to map response from https://api.github.com/users/:username using JACKSON ObjectMapper
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileOverview {
    private String login;
    private String name;
    private String location;
    private String company;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
