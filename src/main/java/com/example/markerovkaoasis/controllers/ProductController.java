package com.example.markerovkaoasis.controllers;

import com.example.markerovkaoasis.services.CodeService;
import com.example.markerovkaoasis.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CodeService codeService;

    @Autowired
    public ProductController(ProductService productService, CodeService codeService) {
        this.productService = productService;
        this.codeService = codeService;
    }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable(value = "id") Long id, Model model){
        System.out.println(id);
        System.out.println("size " + codeService.listProductForList(id).size());
//        System.out.println(codeService.listProductForList(id).get(0).getId());
//        System.out.println(productService.listCodes(id).get(0).getNa);

        model.addAttribute("listProd", codeService.listProductForList(id));
//        model.addAttribute("product", productService.listCodes(id));

        return "productPage";
    }
}
