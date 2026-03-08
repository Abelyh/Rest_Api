package rest_controllers.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest_controllers.dto.UserRequestDto;
import rest_controllers.dto.UserResponseDto;
import rest_controllers.model.Role;
import rest_controllers.model.User;
import rest_controllers.repository.UserRepository;
import rest_controllers.util.UtilMapper;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UtilMapper utilMapper;
    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository, RoleService roleService,
                           PasswordEncoder passwordEncoder, UtilMapper utilMapper, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.utilMapper = utilMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public UserResponseDto create(UserRequestDto requestDto) {
        User user = modelMapper.map(requestDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Long> roleIds = requestDto.getRoleIds();
        Set<Role> allById = roleService.findAllById(roleIds);
        user.setRoles(allById);
        userRepository.save(user);
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAll() {
        List<User> all = userRepository.findAll();
        return all.stream().map(user -> modelMapper.map(user, UserResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponseDto update(Long id, UserRequestDto requestDto) {
        User userById = findById(id);
        User updateUser = utilMapper.updateFromDto(userById, requestDto);
        User saveUser = userRepository.save(updateUser);
        return modelMapper.map(saveUser, UserResponseDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
       userRepository.findById(id).ifPresent(userRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long id) {
        User byId = findById(id);
        return modelMapper.map(byId, UserResponseDto.class);
    }

}
