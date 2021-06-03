package com.street.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.street.dto.DistrictDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    //    @JsonIgnore
    //    @JsonManagedReference
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Street> streets;
    public void addStreet(Street street) {
        if (this.streets == null) {
            this.streets = new HashSet<>();
        }
        this.streets.add(street);
    }
    public District(String name) {
        this.name = name;
    }
    public DistrictDTO toDistrictDTO(){
        DistrictDTO districtDTO = new DistrictDTO();
        districtDTO.setId(this.id);
        districtDTO.setName(this.name);
        districtDTO.setStreets(this.streets.stream().map(x->x.toStreetDTO()).collect(Collectors.toList()));
        return districtDTO;
    }
}
