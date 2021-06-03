package com.street.controller;

import com.street.entity.District;
import com.street.entity.Street;
import com.street.helper.Helper;
import com.street.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/seed/generate")
public class SeedController {
    @Autowired
    DistrictService districtService;
    @RequestMapping(method = RequestMethod.POST)
    public String seed(){
        District district1 = new District("Long biên");
        district1.addStreet(new Street("Cổ Linh", Calendar.getInstance().getTimeInMillis()- Helper.random(10,20)*365*86400000,"Con đường dài đẹp",Helper.random(0,2),district1));
        district1.addStreet(new Street("Nguyễn Văn Cừ", Calendar.getInstance().getTimeInMillis()- Helper.random(10,20)*365*86400000,"Con đường dài đẹp",Helper.random(0,2),district1));
        District district2 = new District("Hoàn Kiếm");
        district2.addStreet(new Street("Hàng Ngang", Calendar.getInstance().getTimeInMillis()- Helper.random(10,20)*365*86400000,"Con đường dài đẹp",Helper.random(0,2),district2));
        district2.addStreet(new Street("Hàng đào", Calendar.getInstance().getTimeInMillis()- Helper.random(10,20)*365*86400000,"Con đường dài đẹp",Helper.random(0,2),district2));
        District district3 = new District("Ba Đình");
        district3.addStreet(new Street("Hoàng Diệu", Calendar.getInstance().getTimeInMillis()- Helper.random(10,20)*365*86400000,"Con đường dài đẹp",Helper.random(0,2),district3));
        district3.addStreet(new Street("Liễu Giai", Calendar.getInstance().getTimeInMillis()- Helper.random(10,20)*365*86400000,"Con đường dài đẹp",Helper.random(0,2),district3));
        District district4 = new District("Cầu Giấy");
        district4.addStreet(new Street("Nguyễn Phong Sắc", Calendar.getInstance().getTimeInMillis()- Helper.random(10,20)*365*86400000,"Con đường dài đẹp",Helper.random(0,2),district4));
        district4.addStreet(new Street("Trần Duy Hưng", Calendar.getInstance().getTimeInMillis()- Helper.random(10,20)*365*86400000,"Con đường dài đẹp",Helper.random(0,2),district4));
        District district5 = new District("Đống Đa");
        district5.addStreet(new Street("Giảng võ", Calendar.getInstance().getTimeInMillis()- Helper.random(10,20)*365*86400000,"Con đường dài đẹp",Helper.random(0,2),district5));
        district5.addStreet(new Street("Xã Đàn", Calendar.getInstance().getTimeInMillis()- Helper.random(10,20)*365*86400000,"Con đường dài đẹp",Helper.random(0,2),district5));
        List<District> districts = Arrays.asList(district1, district2, district3, district4,district5);
        districtService.saveAll(districts);
        return   "ok";
    }

}
