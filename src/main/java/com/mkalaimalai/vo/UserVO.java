package com.mkalaimalai.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by kalaimam on 7/14/17.
 */
@ApiModel(value="user", description="user object")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserVO extends BaseVO{

    private Long id;

    private String userName;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String middleName;

    private List<AddressVO> addresses;
}
