package rest_controllers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest_controllers.dto.RoleResponseDto;
import rest_controllers.dto.UserRequestDto;
import rest_controllers.dto.UserResponseDto;
import rest_controllers.service.RoleService;
import rest_controllers.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUserAccount(@RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(userService.create(requestDto, requestDto.getRoleIds()));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") Long id, @RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(userService.update(id, requestDto));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}