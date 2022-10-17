package com.example.markerovkaoasis.controllers;

import com.example.markerovkaoasis.DTO.ProductDTO;
import com.example.markerovkaoasis.services.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cuterCode")
public class CuterCodeController {

    private CodeService codeService;

    @Autowired
    public CuterCodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping
    public String showCuterCopePage(Model model){
        model.addAttribute("listProd", codeService.listProductForList());
        return "cuterCode";
    }

    @GetMapping("/add")
    public String addCod(){
//        Home
        String fileName = "F:\\testMarkerovka\\order_99490942-8419-41c5-b121-f3429f650805_gtin_04603734326017_quantity_300_5000.csv";
//        Work
//        String fileName = "C:\\TestMarkerovka\\FileKM\\order_99490942-8419-41c5-b121-f3429f650805_gtin_04603734326017_quantity_300_5000.csv";
        codeService.save(new File(fileName));
//        return "redirect:/cuterCode";
        return "cuterCode";
    }

    @GetMapping("/findProductNotPrint")
    public String findByProductCodeAndNotPrint(){
        String codeProduct = "0104603734326017";
//        codeService.findByCodeProductAndIsPrintFalse(codeProduct).forEach(System.out::println);
        codeService.findByCodeProductAndIsPrintFalse(codeProduct);
        return "redirect:/cuterCode";
    }

    @GetMapping("/createFile")
    public String createFileAndWrite(@RequestParam("codeProduct") String codeProduct,
                                     @RequestParam("countCodes") int countCodes){
        codeService.createPrintFile(codeProduct, countCodes);
        return "redirect:/cuterCode";
    }
}
