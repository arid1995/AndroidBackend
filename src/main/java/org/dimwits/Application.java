package org.dimwits;

import org.dimwits.services.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Created by farid on 3/17/17.
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }
}
