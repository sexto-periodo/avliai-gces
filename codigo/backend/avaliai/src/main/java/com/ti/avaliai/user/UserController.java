package com.ti.avaliai.user;

//import com.ti.avaliai.user.dto.UserCreateRequestDTO;
import com.ti.avaliai.user.dto.UserDeleteRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }


    @Transactional
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody UserDeleteRequestDTO userDeleteRequest) {
        this.userService.deleteUser(userDeleteRequest);
        //return ResponseEntity.ok(user);
    }
}
