package com.street.controller;

import com.street.dto.StreetDTO;
import com.street.service.DistrictService;
import com.street.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/streets")
public class StreetController {
    @Autowired
    StreetService streetService;
    @Autowired
    DistrictService districtService;

    @RequestMapping(method = RequestMethod.POST)
    public StreetDTO create(@RequestBody StreetDTO streetDTO) {
        return streetService.create(streetDTO.toStreet()).toStreetDTO();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<StreetDTO> list() {
        return streetService.list().stream().map(x -> x.toStreetDTO()).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public StreetDTO findById(@PathVariable(value = "id") Integer id) {
        return streetService.findById(id).toStreetDTO();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public List<StreetDTO> findAllByDistrictIdAndName(@RequestParam(defaultValue = "") String streetName, @RequestParam(defaultValue = "-1") int districtId) {
        List<StreetDTO> list = null;
        if (districtId != -1 && !streetName.equals("")) { //có cả 2
            list = streetService.findStreetByNameContainsAndDistrictId(streetName, districtId).stream().map(x -> x.toStreetDTO()).collect(Collectors.toList());
        } else if (districtId == -1 && !streetName.equals("")) { //có tên ko id
            list = streetService.findStreetByNameContains(streetName).stream().map(x -> x.toStreetDTO()).collect(Collectors.toList());
        } else if (districtId != -1 && streetName.equals("")) {//có id ko tên
            list = streetService.findStreetByDistrictId(districtId).stream().map(x -> x.toStreetDTO()).collect(Collectors.toList());
        } else {//ko có cả 2 lấy tất
            list = streetService.list().stream().map(x -> x.toStreetDTO()).collect(Collectors.toList());
        }
        return list;
    }

}
