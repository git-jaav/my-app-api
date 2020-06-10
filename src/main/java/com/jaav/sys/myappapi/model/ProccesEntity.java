package com.jaav.sys.myappapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProccesEntity {

    private String proccesName;

    private String status;

    private Integer countOk;

    private Integer countError;

}
