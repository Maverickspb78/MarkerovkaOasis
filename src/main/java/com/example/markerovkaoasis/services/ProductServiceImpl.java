package com.example.markerovkaoasis.services;

import com.example.markerovkaoasis.DTO.ProductDTO;
import com.example.markerovkaoasis.entities.CodeMark;
import com.example.markerovkaoasis.entities.Product;
import com.example.markerovkaoasis.repositories.CodeMarkRepository;
import com.example.markerovkaoasis.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CodeMarkRepository codeMarkRepository;

    @Override
    public List<CodeMark> listCodes(Long id) {

        return codeMarkRepository.findAllByCodeProductAndIsPrintFalse(productRepository.findFirstById(id).getCodeProduct());
    }

    @Override
    public List<ProductDTO> getListDTO(Long id) {
        List<ProductDTO> lists = new ArrayList<>();
        ProductDTO productTemp;
        Product product;
        productTemp = new ProductDTO();
        product = productRepository.findFirstById(id);
        productTemp.setId(product.getId());
        productTemp.setCodeProduct(product.getCodeProduct());
        productTemp.setName(product.getName());
        if (codeMarkRepository.findFirstByCodeProduct(product.getCodeProduct()) != null) {
            productTemp.setDataOrder(codeMarkRepository.findFirstByCodeProduct(product.getCodeProduct()).getDataAdded());
            productTemp.setNumberKm(codeMarkRepository.findAllByCodeProductAndIsPrintFalse(product.getCodeProduct()).size());
            productTemp.setNumberPrintToDay(codeMarkRepository.findAllByCodeProductAndIsPrintTrueAndDataPrint(product.getCodeProduct(), LocalDate.now()).size());
        } else {
            productTemp.setNumberKm(0);
            productTemp.setNumberPrintToDay(codeMarkRepository.findAllByCodeProductAndIsPrintTrueAndDataPrint(product.getCodeProduct(), LocalDate.now()).size());
            lists.add(productTemp);
        }
        return lists;
    }
}
