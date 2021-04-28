package com.jaav.sys.myappapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TIPO_CAMBIO")
public class TipoCambioEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="id")
    private Integer id;

    @Column(name="tag_date")
    private String tagDate;

    @Column(name="exchange_rate")
    private BigDecimal exchangeRate;

    @Column(name="status")
    private String status;
}
