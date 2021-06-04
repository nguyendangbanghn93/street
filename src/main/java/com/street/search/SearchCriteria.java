package com.street.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchCriteria {
    private String key; //teen truong
    private String operation; //>= == <= join
    private Object value;//gia tri
}
