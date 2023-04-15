package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true) // this is from jackson

@Getter
@Setter
@ToString
public class Regions {

    private List<Region> items;
    private int count;

}
