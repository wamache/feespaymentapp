package com.schoolapp.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="dependant")
public class Dependant {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int dependant_id;

    private Long student_id;

    private String reg_number;

    private String school_name;



}
