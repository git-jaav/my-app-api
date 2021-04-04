package com.jaav.sys.myappapi.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataCacheEntity implements Serializable {

    private String name;
    private String email;
    private String status;
    private String formatedDateRegister;

}
