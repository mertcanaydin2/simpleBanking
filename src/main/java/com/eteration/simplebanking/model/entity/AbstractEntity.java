package com.eteration.simplebanking.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class AbstractEntity {

    @Column(name = "IS_ACTV")
    @JsonIgnore
    private int isActv = 1;

    @Column(name = "CREATE_DATE")
    @JsonIgnore
    private String createDate = LocalDateTime.now().toString();
}
