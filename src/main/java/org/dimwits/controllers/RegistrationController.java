package org.dimwits.controllers;

import org.dimwits.requests.RegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by farid on 3/17/17.
 */

@RestController
public class RegistrationController {

    @RequestMapping(path="/api/user", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegistrationRequest body, HttpSession session) {
        return ResponseEntity.ok().body("OK");
    }

    @RequestMapping(path="/api/session", method = RequestMethod.POST)
    public ResponseEntity authenticate(@RequestBody RegistrationRequest body, HttpSession session) {
        return ResponseEntity.ok().body("OK");
    }

}
