package com.qualitychemicals.qcipayments.user.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Role {
    @Id
    private int id;
    private String role;
}
