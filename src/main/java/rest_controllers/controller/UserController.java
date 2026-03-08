package rest_controllers.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import rest_controllers.dto.UserResponseDto;
import rest_controllers.model.User;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final ModelMapper modelMapper;

    public UserController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<UserResponseDto> getUserProfile(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(modelMapper.map(currentUser, UserResponseDto.class));
    }


}
