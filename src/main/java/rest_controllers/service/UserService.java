package rest_controllers.service;

import rest_controllers.dto.UserRequestDto;
import rest_controllers.dto.UserResponseDto;
import rest_controllers.model.User;

import java.util.List;


public interface UserService {

    UserResponseDto create(UserRequestDto requestDto);

    List<UserResponseDto> getAll();

    UserResponseDto update(Long id, UserRequestDto requestDto);

    void delete(Long id);

    User findById(Long id);

    UserResponseDto getUserById(Long id);

}
