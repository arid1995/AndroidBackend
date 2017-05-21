package org.dimwits.services;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by farid on 5/21/17.
 */

@ConfigurationProperties("storage")
public class StorageProperties {

  private String location = "/home/farid/android-backend-files";

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

}