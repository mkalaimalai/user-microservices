package com.mkalaimalai.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by kalaimam on 7/14/17.
 */
@ApiModel(value="user", description="user object")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO extends ResourceSupport{

    private Long userId;

    private String userName;

    @NotNull(message = "email not be null")
    @Email
    private String email;

    @NotNull(message = "password can not be null")
    private String password;

    @NotNull(message = "first name can not be null")
    private String firstName;

    @NotNull(message = "last name can not be null")
    private String lastName;

    private String middleName;

    private List<AddressVO> addresses;
}
