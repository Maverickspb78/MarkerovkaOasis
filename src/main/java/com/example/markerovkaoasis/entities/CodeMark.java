package com.example.markerovkaoasis.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "codemark")
public class CodeMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code", unique=true)
    private String code;
    @Column(name = "code_product")
    private String codeProduct;
    @Column(name = "is_print")
    private boolean isPrint;
    @Column(name = "is_oborot")
    private boolean isOborot;
    @Column(name = "is_out_oborot")
    private boolean isOutOborot;
    @Column(name = "date_of_manufacture")
    private LocalDate dateOfManufacture;
    @Column(name = "date_added")
    private LocalDate dataAdded;
    @Column(name = "date_print")
    private LocalDate dataPrint;
    @Column(name = "date_v_oborot")
    private LocalDate dateVOborot;
    @Column(name = "date_out_oborot")
    private LocalDate dateOutOborot;
    @Column(name = "filename_added")
    private String fileNameAdded;
    @Column(name = "filename")
    private String fileName;

}