package com.example.demo.mapper;

import com.example.demo.domain.dto.output.NumberToDollarsRestResponse;
import com.example.demo.domain.dto.output.NumberToWordsRestResponse;
import com.example.demo.wsdl.NumberToDollarsResponse;
import com.example.demo.wsdl.NumberToWordsResponse;

public class WsdlMapper <T> {

    public static  <T> Object XmlResponseToJsonResponse(T xmlResponse){
    if (xmlResponse instanceof NumberToDollarsResponse) {
      return  NumberToDollarsRestResponse.builder()
          .numberToDollarsResult(((NumberToDollarsResponse) xmlResponse).getNumberToDollarsResult())
          .build();
        }
    if(xmlResponse instanceof NumberToWordsResponse){
        return NumberToWordsRestResponse.builder().numberToWordsResult(((NumberToWordsResponse) xmlResponse).getNumberToWordsResult()).build();
    }
      return null;
    }
}
