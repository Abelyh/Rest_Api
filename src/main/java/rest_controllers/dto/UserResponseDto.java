package rest_controllers.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private Set<RoleResponseDto> roles;

    public UserResponseDto(Long id, String firstName, String lastName, Integer age, String email, Set<RoleResponseDto> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.roles = roles;
    }

    public UserResponseDto() {
    }

}
