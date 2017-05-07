package org.dimwits.requests;

/**
 * Created by farid on 3/22/17.
 */
public class RegistrationRequest {
    private String login;
    private String password;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
