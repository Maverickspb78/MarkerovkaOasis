package com.example.markerovkaoasis.services;

import com.example.markerovkaoasis.DTO.ProductDTO;
import com.example.markerovkaoasis.entities.CodeMark;

import java.io.File;
import java.util.List;

public interface CodeService {
    public List<CodeMark> findByCodeProductAndIsPrintFalse(String name);
    public List<String> getCodeOnProduct();
    public void save(File fileName);
    public void createPrintFile(String codeProduct, int countCodes);
    public List<ProductDTO> listProductForList();
}
