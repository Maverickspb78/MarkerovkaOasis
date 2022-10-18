package com.example.markerovkaoasis.controllers;

import com.example.markerovkaoasis.DTO.ProductDTO;
import com.example.markerovkaoasis.services.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

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
    public String addCod(Model model){
//        Home
//        String fileName = "F:\\testMarkerovka\\order_99490942-8419-41c5-b121-f3429f650805_gtin_04603734326017_quantity_300_5000.csv";
//        Work
        String fileName = "C:\\TestMarkerovka\\FileKM\\order_99490942-8419-41c5-b121-f3429f650805_gtin_04603734326017_quantity_300_5000.csv";
        codeService.save(new File(fileName));
//        return "redirect:/cuterCode";
        return "cuterCode";
    }

    @PostMapping("/add")
    public String postAdd(@RequestParam("file") MultipartFile file, Model model){
        String uploadDir = "C:\\TestMarkerovka\\";
//        model.addAttribute("path", new String());
        try {
            // Get the file and save it somewhere
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
//        codeService.save(new File("C:\\TestMarkerovka\\FileKM\\" + file.getOriginalFilename()));
        return "";
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
