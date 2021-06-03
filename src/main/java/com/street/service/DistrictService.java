package com.street.service;

import com.street.entity.District;
import com.street.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Configurable
public class DistrictService {
    @Autowired
    DistrictRepository districtRepository;
    public List<District> saveAll(List<District> list){
        return districtRepository.saveAll(list);
    }
    public List<District> list(){
        return districtRepository.findAll();
    }
}
