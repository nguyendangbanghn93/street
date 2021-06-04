package com.street.repository;

import com.street.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StreetRepository extends JpaRepository<Street, Integer> , JpaSpecificationExecutor<Street> {
    public List<Street> findStreetByNameContainsAndDistrictId(String streetNamme, int districtId);

    public List<Street> findStreetByNameContains(String streetNamme);

    public List<Street> findStreetByDistrictId(int districtId);
}

