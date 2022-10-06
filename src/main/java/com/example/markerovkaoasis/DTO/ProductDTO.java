package com.example.markerovkaoasis.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ProductDTO {

    private Long id;
    private String name;
    private String CodeProduct;
    private int numberKm;
    private int numberPrintToDay;
    private LocalDate dataOrder;

}
