package org.dimwits.services;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by farid on 5/20/17.
 */
@Service
public class StorageService {

  private final Path rootLocation;
  private Random random = new SecureRandom();
  private static int BASE = 32;
  private static int BIT_NUM = 256;

  @Autowired
  public StorageService(StorageProperties properties) {
    this.rootLocation = Paths.get(properties.getLocation());
  }

  public String store(MultipartFile file) {
    String hash = generateHash();
    try {
      if (file.isEmpty()) {
        throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
      }

      Files.copy(file.getInputStream(), this.rootLocation.resolve(hash));
    } catch (IOException e) {
      throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
    }

    return hash;
  }

  private String generateHash() {
    return new BigInteger(BIT_NUM, random).toString(BASE);
  }

  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.rootLocation, 1)
          .filter(path -> !path.equals(this.rootLocation))
          .map(this.rootLocation::relativize);
    } catch (IOException e) {
      throw new StorageException("Failed to read stored files", e);
    }

  }

  public Path load(String filename) {
    return rootLocation.resolve(filename);
  }

  public Resource loadAsResource(String filename) {
    try {
      Path file = load(filename);
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new StorageFileNotFoundException("Could not read file: " + filename);
      }
    } catch (MalformedURLException e) {
      throw new StorageFileNotFoundException("Could not read file: " + filename, e);
    }
  }

  public void deleteAll() {
    FileSystemUtils.deleteRecursively(rootLocation.toFile());
  }

  public void init() {
    try {
      Files.createDirectory(rootLocation);
    } catch (IOException e) {
      throw new StorageException("Could not initialize storage", e);
    }
  }
}
