package com.jaav.sys.myappapi.model.api;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProcessEntity {

    private String proccesName;

    private String status;

    private Integer countOk;

    private LocalDateTime currentTime;

}
