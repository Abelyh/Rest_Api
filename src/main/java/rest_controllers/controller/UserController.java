package rest_controllers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import rest_controllers.dto.UserResponseDto;
import rest_controllers.model.User;
import rest_controllers.service.UserService;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<UserResponseDto> getUserProfile(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(userService.convertToDto(currentUser));
    }


}
