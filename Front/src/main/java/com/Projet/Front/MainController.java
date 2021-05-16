package com.Projet.Front;

import com.Projet.Front.dto.ProductDto;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class MainController {
    @GetMapping(path = "/")
    public String home() {
        return "home";
    }
    @GetMapping(path = "/product")
    public String product() {
        return "payment";
    }

    @Autowired
    ProductFeignClient feignClient;

    public ProductDto feignProduct(HttpServletRequest request) {
        KeycloakSecurityContext context = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        String token = "Bearer " + context.getTokenString();

        return feignClient.product(token);

    }

    @GetMapping(path = "product/all")
    public String product(Model model, Principal principal, HttpServletRequest request) {
        model.addAttribute("status", feignProduct(request).getName());
        return "product";
    }

    @PostMapping(path = "product/set")
    public String setProduct(Model model, Principal principal, HttpServletRequest request) {
        model.addAttribute("status", feignProduct(request).getName());
        return "product";
    }
}
