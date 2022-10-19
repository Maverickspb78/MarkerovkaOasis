package com.example.markerovkaoasis.services;

import com.example.markerovkaoasis.DTO.ProductDTO;
import com.example.markerovkaoasis.entities.CodeMark;

import java.util.List;

public interface ProductService {

    List<CodeMark> listCodes(Long id);
    List<ProductDTO> getListDTO(Long id);
}
