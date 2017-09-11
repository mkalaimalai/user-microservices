package com.mkalaimalai.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

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
public class UserVO extends BaseVO{

    private Long id;

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
