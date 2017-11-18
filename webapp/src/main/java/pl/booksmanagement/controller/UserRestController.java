package pl.booksmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.booksmanagement.model.User;
import pl.booksmanagement.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/availableUsername", method = RequestMethod.POST)
    public ResponseEntity<Void> isLoginAvailable(String login) {
        boolean available = userService.isUserLoginAvailable(login);
        return available ? new ResponseEntity<>(HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@RequestBody User newUser) {
        boolean result = userService.createNewUser(newUser);
        return result ? new ResponseEntity<>("created", HttpStatus.OK) : new ResponseEntity<>("failed", HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/createExample", method = RequestMethod.GET)
    public ResponseEntity<Void> createExampleUser() {

        userService.createExampleUser();

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/me")
    public Principal user(Principal principal) {
//        ((OAuth2Authentication) principal).getCredentials().get
        return principal;
    }

}
