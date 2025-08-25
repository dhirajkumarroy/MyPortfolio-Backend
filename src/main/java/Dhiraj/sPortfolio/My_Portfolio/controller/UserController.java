package Dhiraj.sPortfolio.My_Portfolio.controller;

import Dhiraj.sPortfolio.My_Portfolio.entity.User;
import Dhiraj.sPortfolio.My_Portfolio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create-admin")
    public ResponseEntity<User> createAdmin(@RequestParam String username,
                                            @RequestParam String password) {
        return ResponseEntity.ok(userService.createAdmin(username, password));
    }
}
