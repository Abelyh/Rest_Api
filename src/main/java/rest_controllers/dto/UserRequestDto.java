package rest_controllers.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserRequestDto {

    private  String firstName;
    private  String lastName;
    private  Integer age;
    private  String email;
    private  String password;
    private  List<Long> roleIds;

    public UserRequestDto(String firstName, String lastName, Integer age, String email, String password, List<Long> roleIds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roleIds = roleIds;
    }

    public UserRequestDto() {
    }

}
