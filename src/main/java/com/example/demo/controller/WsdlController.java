package com.example.demo.controller;

import com.example.demo.domain.dto.output.NumberToDollarsRestResponse;
import com.example.demo.domain.dto.output.NumberToWordsRestResponse;
import com.example.demo.service.api.WsdlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;

@RestController
@RequestMapping("Wsdl")
public class WsdlController {

   private final WsdlService wsdlService ;

    public WsdlController(WsdlService wsdlService) {
        this.wsdlService = wsdlService;
    }

   @GetMapping("NumberToDollars")
   public ResponseEntity<NumberToDollarsRestResponse> numberToDollars (@RequestParam BigDecimal valueToConvert){
       return ResponseEntity.ok(wsdlService.numberToDollars(valueToConvert));
   }

    @GetMapping("NumberToWords")
    public ResponseEntity<NumberToWordsRestResponse> numberToWords (@RequestParam BigInteger valueToConvert){
        return ResponseEntity.ok(wsdlService.numberToWords(valueToConvert));
    }
}
