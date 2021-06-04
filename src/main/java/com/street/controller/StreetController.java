package com.street.controller;

import com.street.dto.StreetDTO;
import com.street.entity.Street;
import com.street.repository.StreetRepository;
import com.street.search.SearchCriteria;
import com.street.search.StreetSpecification;
import com.street.service.DistrictService;
import com.street.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/streets")
public class StreetController {
    @Autowired
    StreetService streetService;
    @Autowired
    DistrictService districtService;
    @Autowired
    StreetRepository streetRepository;

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

    @RequestMapping(method = RequestMethod.GET, value = "/searchold")
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

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public Iterable<StreetDTO> test(@RequestParam(required = false, name = "keyword") Optional<String> keyword,
                                    @RequestParam(required = false, name = "status") Optional<Integer> status,
                                    @RequestParam(required = false, name = "districtId") Optional<Integer> districtId
    ) {
        Specification<Street> specification = Specification.where(null);
        if (keyword.isPresent() && keyword.get().length() > 0) {
            specification = specification.and(new StreetSpecification(new SearchCriteria("name", "~", keyword.get())));
        }
        if (status.isPresent()) {
            specification = specification.and(new StreetSpecification(new SearchCriteria("status", "==", status.get())));
        }
        if (districtId.isPresent()) {
            specification = specification.and(new StreetSpecification(new SearchCriteria("districtId", "==", districtId.get())));
        }
        return streetRepository.findAll(specification).stream().map(x -> x.toStreetDTO()).collect(Collectors.toList());

//        streetSpecification.and(new StreetSpecification(new SearchCriteria("name",":",keyword)));
//        Specification<Street> s1 = Specification.where(null);
//        Specification<Street> s2 = new StreetSpecification(new SearchCriteria("name","~",keyword.get()));
//        Specification<Street> s3 = new StreetSpecification(new SearchCriteria("status","=",status.get()));
//        return  streetRepository.findAll(s1.and(s2).and(s3)).stream().map(x->x.toStreetDTO()).collect(Collectors.toList());
//        return streetRepository.findAll(new StreetSpecification(new SearchCriteria("name",":",keyword.get()))).stream().map(x->x.toStreetDTO()).collect(Collectors.toList());
//        return streetRepository.findAll(new StreetSpecification(new SearchCriteria("status","==",status.get()))).stream().map(x->x.toStreetDTO()).collect(Collectors.toList());
//        return streetRepository.findAll(specification).stream().map(x->x.toStreetDTO()).collect(Collectors.toList());
    }

}
