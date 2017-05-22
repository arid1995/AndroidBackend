package org.dimwits.controllers;

import org.dimwits.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by farid on 5/20/17.
 */
@RestController
@RequestMapping(path = "/api/file")
public class FileController {

  private final StorageService storageService;

  @Autowired
  public FileController(StorageService storageService) {
    this.storageService = storageService;
  }

  @RequestMapping(path = "", method = RequestMethod.POST)
  public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
    int a = 2;
    return ResponseEntity.ok().body(storageService.store(file));
  }

  @RequestMapping(value = "/{filename:.+}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

    Resource file = storageService.loadAsResource(filename);
    return ResponseEntity
        .ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
        .body(file);
  }

}
