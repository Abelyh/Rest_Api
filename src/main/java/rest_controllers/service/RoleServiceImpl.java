package rest_controllers.service;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest_controllers.dto.RoleResponseDto;
import rest_controllers.model.Role;
import rest_controllers.repository.RoleRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Set<Role> findAllById(List<Long> id) {
        return roleRepository.findByIdIn(id);
    }

    @Override
    public List<RoleResponseDto> findAll() {
        List<Role> allRoles = roleRepository.findAll();
        return allRoles.stream().map(role -> modelMapper.map(role, RoleResponseDto.class)).collect(Collectors.toList());

    }
}
