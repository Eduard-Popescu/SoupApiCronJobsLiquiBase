package com.example.demo.service.api;

import com.example.demo.domain.dto.output.NumberToDollarsRestResponse;
import com.example.demo.domain.dto.output.NumberToWordsRestResponse;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface WsdlService {

    NumberToDollarsRestResponse numberToDollars (BigDecimal value);
    NumberToWordsRestResponse numberToWords (BigInteger value);
}
