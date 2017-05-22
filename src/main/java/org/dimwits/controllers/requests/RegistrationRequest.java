package org.dimwits.controllers.requests;

/**
 * Created by farid on 3/22/17.
 */
public class RegistrationRequest {

  private String login;
  private String password;
  private String email;
  private String firstName;
  private String lastName;
  private String avatar;
  private String about;

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAvatar() {
    return avatar;
  }

  public String getAbout() {
    return about;
  }
}
