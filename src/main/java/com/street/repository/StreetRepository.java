package com.street.repository;

import com.street.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StreetRepository extends JpaRepository<Street, Integer> {
    public List<Street> findStreetByNameContainsAndDistrictId(String streetNamme, int districtId);

    public List<Street> findStreetByNameContains(String streetNamme);

    public List<Street> findStreetByDistrictId(int districtId);
}
