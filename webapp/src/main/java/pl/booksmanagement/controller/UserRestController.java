package pl.booksmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.booksmanagement.service.UserService;

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

    @RequestMapping(value = "/createExample", method = RequestMethod.GET)
    public ResponseEntity<Void> createExampleUser() {

        userService.createExampleUser();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
