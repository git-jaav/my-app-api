package com.jaav.sys.myappapi.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessEntity {

    private String proccesName;

    private String status;

    private Integer countOk;

    private LocalDateTime currentTime;

}
