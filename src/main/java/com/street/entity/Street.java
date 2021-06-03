package com.street.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.street.dto.StreetDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private long founding;
    private String description;
    private int status; //1:Đang sử dụng; 0:Đang thi công; 2:Đang tu sửa
    @ManyToOne(cascade = {CascadeType.MERGE,  CascadeType.REFRESH},fetch = FetchType.LAZY)//CascadeType.PERSIST,
    @JoinColumn(name = "districtId", referencedColumnName = "id")

//    @JsonBackReference
    private District district;
    @Column(insertable = false, updatable = false)
    private int districtId;
    public Street(String name, long founding, String description, int status) {
        this.name = name;
        this.founding = founding;
        this.description = description;
        this.status = status;
    }
    public Street(String name, long founding, String description, int status,District district) {
        this.name = name;
        this.founding = founding;
        this.description = description;
        this.status = status;
        this.district = district;
    }
    public StreetDTO toStreetDTO(){
        StreetDTO streetDTO = new StreetDTO();
        streetDTO.setId(this.id);
        streetDTO.setName(this.name);
        streetDTO.setFounding(this.founding);
        streetDTO.setDescription(this.description);
        streetDTO.setStatus(this.status);
        streetDTO.setDistrictId(this.district.getId());
        streetDTO.setDistrictName(this.district.getName());
        return streetDTO;
    }
}
