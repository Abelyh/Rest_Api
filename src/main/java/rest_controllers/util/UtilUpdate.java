package rest_controllers.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rest_controllers.dto.UserRequestDto;
import rest_controllers.model.Role;
import rest_controllers.model.User;
import rest_controllers.repository.UserRepository;
import rest_controllers.service.RoleService;

import java.util.Set;

/**
 вспомогательный класс для реализации обновления юзера, чтобы в сервисном классе update метод не был таким громоздким
 */
@Component
public class UtilUpdate {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    public UtilUpdate(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public User updateFromDto(User userById, UserRequestDto requestDto) {

        if (requestDto.getFirstName() != null) {
            userById.setFirstName(requestDto.getFirstName());
        }
        if (requestDto.getLastName() != null) {
            userById.setLastName(requestDto.getLastName());
        }
        if (requestDto.getAge() != null) {
            userById.setAge(requestDto.getAge());
        }
        if (requestDto.getEmail() != null && !requestDto.getEmail().equals(userById.getEmail())) {
            if (!userRepository.existsByEmail(requestDto.getEmail())) {
                userById.setEmail(requestDto.getEmail());
            }
        }
        if (requestDto.getPassword() != null && !requestDto.getPassword().isEmpty()) {
            userById.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        }
        if (requestDto.getRoleIds() != null && !requestDto.getRoleIds().isEmpty()) {
            Set<Role> allById = roleService.findAllById(requestDto.getRoleIds());
            userById.setRoles(allById);
        }
        return userById;
    }
}
