package com.jaav.sys.myappapi.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessEntity implements Serializable {

    private String proccesName;

    private String status;

    private Integer countOk;

    private Integer countError;

}
