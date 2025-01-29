package com.paritoshpal.ecommerce_yt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "t_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String mobile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(firstName, address.firstName) &&
                Objects.equals(lastName, address.lastName) &&
                Objects.equals(streetAddress, address.streetAddress) &&
                Objects.equals(city, address.city) &&
                Objects.equals(state, address.state) &&
                Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(mobile, address.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, streetAddress, city, state, zipCode, mobile);
    }

}
