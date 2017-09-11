package com.mkalaimalai.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mkalaimalai.domain.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by kalaimam on 7/14/17.
 */

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
