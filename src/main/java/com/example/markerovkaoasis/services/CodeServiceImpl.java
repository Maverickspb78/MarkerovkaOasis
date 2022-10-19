package com.example.markerovkaoasis.services;

import com.example.markerovkaoasis.Utils.FileUtils;
import com.example.markerovkaoasis.entities.CodeMark;
import com.example.markerovkaoasis.DTO.ProductDTO;
import com.example.markerovkaoasis.entities.Product;
import com.example.markerovkaoasis.repositories.CodeMarkRepository;
import com.example.markerovkaoasis.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {

    private final CodeMarkRepository codeMarkRepository;
    private final FileUtils fileUtils;
    private final ProductRepository productRepository;

    @Override
    public List<CodeMark> findByCodeProductAndIsPrintFalse(String codeProduct) {
        return codeMarkRepository.findAllByCodeProductAndIsPrintFalse(codeProduct);
    }

    @Override
    public List<String> getCodeOnProduct() {

        return null;
    }

    @Override
    public void save(File fileName) {
        try {
            codeMarkRepository.saveAll(fileUtils.readToWriteBD(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createPrintFile(String codeProduct, int countCodes) {
        List<CodeMark> codeMarkList = findByCodeProductAndIsPrintFalse(codeProduct);
        if (codeMarkList.size() >= countCodes) {
            codeMarkList.subList((countCodes), codeMarkList.size()).clear();
            CodeMark codeMarkTemp;
            String dateNowString = "" + LocalDate.now();
            String fileName = codeMarkList.get(0).getFileNameAdded().split("quantity_")[0];
            String pathName = "D:\\Test\\".concat(dateNowString.replace("-", "\\")).concat("\\");
            String fileNamePrintFile = pathName.concat(fileName).concat("quantity_").concat("" + codeMarkList.size()).concat(".csv");
            for (int i = 0; i <= codeMarkList.size() - 1; i++) {
                codeMarkTemp = codeMarkList.get(i);
                codeMarkTemp.setDataPrint(LocalDate.now());
                codeMarkTemp.setPrint(true);
                codeMarkTemp.setFileName(fileName);
            }
            codeMarkRepository.saveAll(codeMarkList);
            fileUtils.createPrintFile(codeMarkList, fileNamePrintFile);
        }
    }

    @Override
    public List<ProductDTO> listProductForList() {
        List<ProductDTO> lists = new ArrayList<>();
        ProductDTO productTemp;
        Product product;
        for (int i = 1; i <= productRepository.findAll().size(); i++) {
            productTemp = new ProductDTO();
            product = productRepository.findFirstById((long) i);
            lists.add(productToProductDTO(productTemp, product));
        }
        return lists;
    }

    @Override
    public List<ProductDTO> listProductForList(Long id) {
        List<ProductDTO> lists = new ArrayList<>();
        ProductDTO productTemp = new ProductDTO();
        Product product = productRepository.findFirstById(id);
        lists.add(productToProductDTO(productTemp, product));
        return lists;
    }

    public ProductDTO productToProductDTO(ProductDTO productTemp, Product product) {
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
        }
        return productTemp;
    }

    @Override
    public void addCodeFromFile(MultipartFile file) {
        String uploadDir = "C:\\TestMarkerovka\\1\\";
//        String uploadDir = "F:\\testMarkerovka\\1\\";
        String fileName = uploadDir + file.getOriginalFilename();
        File tempFile = new File(fileName);
        save(fileUtils.downloadFile(file, tempFile));
        tempFile.delete();
    }
}