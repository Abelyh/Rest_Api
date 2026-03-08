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
public class UtilMapper {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    public UtilMapper(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public User updateFromDto(User user, UserRequestDto requestDto) {

        if (requestDto.getFirstName() != null) {
            user.setFirstName(requestDto.getFirstName());
        }
        if (requestDto.getLastName() != null) {
            user.setLastName(requestDto.getLastName());
        }
        if (requestDto.getAge() != null) {
            user.setAge(requestDto.getAge());
        }
        if (requestDto.getEmail() != null && !requestDto.getEmail().equals(user.getEmail())) {
            if (!userRepository.existsByEmail(requestDto.getEmail())) {
                user.setEmail(requestDto.getEmail());
            }
        }
        if (requestDto.getPassword() != null && !requestDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        }
        if (requestDto.getRoleIds() != null && !requestDto.getRoleIds().isEmpty()) {
            Set<Role> allById = roleService.findAllById(requestDto.getRoleIds());
            user.setRoles(allById);
        }
        return user;
    }
}
