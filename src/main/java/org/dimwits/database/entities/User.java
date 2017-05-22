package org.dimwits.database.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by farid on 5/7/17.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty("id")
  @Column(name = "user_id")
  private Integer userId;

  @Column(unique = true, nullable = false, name = "login")
  private String login;

  @Column(unique = true, nullable = false, name = "email")
  private String email;

  @JsonIgnore
  @Column(nullable = false, name = "password")
  private String password;

  @Column(nullable = false, name = "first_name")
  private String firstName;

  @Column(nullable = false, name = "last_name")
  private String lastName;

  @Column(nullable = false, name = "about")
  private String about;

  @Column(nullable = false, name = "avatar")
  private String avatar;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> images = new ArrayList<>();

  @ManyToMany(fetch = FetchType.EAGER)
  private List<User> friends = new ArrayList<>();

  public User() {
  }

  public User(String login, String email, String password, String firstName,
      String lastName, String about, String avatar) {
    this.login = login;
    this.email = email;
    final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    this.password = encoder.encode(password);
    this.firstName = firstName;
    this.lastName = lastName;
    this.about = about;
    this.avatar = avatar;
  }

  public Integer getUserId() {
    return userId;
  }

  public String getLogin() {
    return login;
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

  public String getAbout() {
    return about;
  }

  public String getAvatar() {
    return avatar;
  }

  public ArrayList<String> getImages() {
    return new ArrayList<>(images);
  }

  public List<User> getFriends() {
    return new ArrayList<>(friends);
  }

  public boolean matchPassword(String plainPassword) {
    final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder.matches(plainPassword, password);
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    this.password = encoder.encode(password);
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public void addImage(String imagePath) {
    images.add(imagePath);
  }

  public void deleteImage(String imagePath) {
    images.remove(imagePath);
  }

  public void addFriend(User friend) {
    friends.add(friend);
  }

  public void removeFriend(User friend) {
    friends.remove(friend);
  }

  public String toJSON() {
    String json;
    try {
      final ObjectMapper mapper = new ObjectMapper();
      json = mapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      json = "{}";
    }

    return json;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (!User.class.isAssignableFrom(obj.getClass())) {
      return false;
    }

    final User user = (User) obj;

    return this.login.equals(user.login) &&
        this.password.equals(user.password) &&
        this.email.equals(user.email);
  }
}
