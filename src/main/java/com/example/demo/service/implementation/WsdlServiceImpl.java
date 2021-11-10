package com.example.demo.service.implementation;

import com.example.demo.domain.dto.output.NumberToDollarsRestResponse;
import com.example.demo.domain.dto.output.NumberToWordsRestResponse;
import com.example.demo.mapper.WsdlMapper;
import com.example.demo.service.api.WsdlService;
import com.example.demo.wsdl.NumberToDollars;
import com.example.demo.wsdl.NumberToDollarsResponse;
import com.example.demo.wsdl.NumberToWords;
import com.example.demo.wsdl.NumberToWordsResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class WsdlServiceImpl implements WsdlService {

    public NumberToDollarsRestResponse numberToDollars (BigDecimal value){
        WebService wsdlClient = new WebService();
        wsdlClient.wsdlConfig();
        NumberToDollars request = new NumberToDollars();
        request.setDNum(value);

        NumberToDollarsResponse response = (NumberToDollarsResponse) wsdlClient.getWebServiceTemplate()
            .marshalSendAndReceive(request);

        return (NumberToDollarsRestResponse) WsdlMapper.XmlResponseToJsonResponse(response);
    }

    public NumberToWordsRestResponse numberToWords (BigInteger value){
        WebService wsdlClient = new WebService();
        wsdlClient.wsdlConfig();
        NumberToWords request = new NumberToWords();
        request.setUbiNum(value);

        NumberToWordsResponse response = (NumberToWordsResponse) wsdlClient.getWebServiceTemplate()
            .marshalSendAndReceive(request);

        return (NumberToWordsRestResponse) WsdlMapper.XmlResponseToJsonResponse(response);
    }

}

class WebService extends WebServiceGatewaySupport {
  public void wsdlConfig() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath("com.example.demo.wsdl");
    this.setDefaultUri("https://www.dataaccess.com/webservicesserver/NumberConversion.wso");
    this.setMarshaller(marshaller);
    this.setUnmarshaller(marshaller);
  }
}

