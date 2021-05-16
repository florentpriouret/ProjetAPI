package com.Projet.Front;

import com.Projet.Front.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "PRODUCT")
public interface ProductFeignClient {
    String AUTH_TOKEN = "Authorization";


    @GetMapping("/products/all")
    ProductDto product(@RequestHeader(AUTH_TOKEN) String token);

    @PostMapping("/products")
    ProductDto setProduct(@RequestHeader(AUTH_TOKEN) String token);
}
