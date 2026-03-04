package rest_controllers.service;

import rest_controllers.dto.RoleResponseDto;
import rest_controllers.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {


    Set<Role> findAllById(List<Long> id);

    List<RoleResponseDto> findAll();
}
