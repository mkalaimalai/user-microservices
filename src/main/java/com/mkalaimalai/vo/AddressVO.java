package com.mkalaimalai.vo;

import com.mkalaimalai.domain.AddressType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by kalaimam on 7/14/17.
 */

@ApiModel(value="address", description="address object")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressVO extends BaseVO{

    private String line1;

    private String line2;

    @NotNull(message = "city can not be null")
    private String city;

    @NotNull(message = "zip can not be null")
    private String zip;

    @NotNull(message = "country can not be null")
    private String country;

    private AddressType type;

}
