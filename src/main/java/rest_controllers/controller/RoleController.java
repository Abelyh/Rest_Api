package rest_controllers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest_controllers.dto.RoleResponseDto;
import rest_controllers.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {
private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping()
    public ResponseEntity<List<RoleResponseDto>> getRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }
}
