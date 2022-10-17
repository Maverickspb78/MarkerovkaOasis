package com.example.markerovkaoasis.services;

import com.example.markerovkaoasis.DTO.ProductDTO;
import com.example.markerovkaoasis.entities.CodeMark;

import java.io.File;
import java.util.List;

public interface CodeService {
    List<CodeMark> findByCodeProductAndIsPrintFalse(String name);
    List<String> getCodeOnProduct();
    void save(File fileName);
    void createPrintFile(String codeProduct, int countCodes);
    List<ProductDTO> listProductForList();
    List<ProductDTO> listProductForList(Long id);
}
