package com.mkalaimalai.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by kalaimam on 7/14/17.
 */

@Setter
@Getter
@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LINE1")
    private String line1;

    @Column(name = "LINE2")
    private String line2;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ZIP")
    private String zip;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "TYPE")
    private AddressType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_FK", nullable = false)
    @JsonIgnore
    private User user;

}
