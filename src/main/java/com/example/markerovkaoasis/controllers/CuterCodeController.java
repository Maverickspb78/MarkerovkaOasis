package com.example.markerovkaoasis.controllers;

import com.example.markerovkaoasis.formClasses.FileKMForm;
import com.example.markerovkaoasis.services.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cuterCode")
public class CuterCodeController {

    private CodeService codeService;

    @Autowired
    public CuterCodeController(CodeService codeService) {
        this.codeService = codeService;
    }

//    Страница работы с кодами маркеровки.
    @GetMapping
    public String showCuterCopePage(Model model) {
        model.addAttribute("listProd", codeService.listProductForList());
        return "cuterCode";
    }

//    Добавление КМ.
    @PostMapping("/add")
    public String postAdd(@RequestParam(value = "file") MultipartFile file) {
        codeService.addCodeFromFile(file);
        return "redirect:/cuterCode";
    }

//    Поиск КМ по коду товара.
    @GetMapping("/findProductNotPrint")
    public String findByProductCodeAndNotPrint() {
        String codeProduct = "0104603734326017";
        codeService.findByCodeProductAndIsPrintFalse(codeProduct);
        return "redirect:/cuterCode";
    }

//    Создание файла .CSV КМ для печати.
    @GetMapping("/createFile")
    public String createFileAndWrite(@RequestParam("codeProduct") String codeProduct,
                                     @RequestParam("countCodes") int countCodes) {
        codeService.createPrintFile(codeProduct, countCodes);
        return "redirect:/cuterCode";
    }

    //    Кнопка печать
    @PostMapping()
    public String printKInToFile(@ModelAttribute ("KmForm") FileKMForm KmForm, Model model){


        return "redirect:/cuterCode";
    }
}
