package com.example.markerovkaoasis.controllers;

import com.example.markerovkaoasis.formClasses.FileKMForm;
import com.example.markerovkaoasis.services.CodeService;
import com.example.markerovkaoasis.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CodeService codeService;

    @Autowired
    public ProductController(ProductService productService, CodeService codeService) {
        this.productService = productService;
        this.codeService = codeService;
    }

//    Страница просмотра и печати кодов по товару.
    @GetMapping("/{id}")
    public String showProduct(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("listProd", codeService.listProductForList(id));
        model.addAttribute("KmForm", new FileKMForm(id));
        return "productPage";
    }

}
