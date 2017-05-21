package org.dimwits.services;

/**
 * Created by farid on 5/21/17.
 */
public class StorageFileNotFoundException extends StorageException {

  public StorageFileNotFoundException(String message) {
    super(message);
  }

  public StorageFileNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
