package techcr.learn.microservice.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import techcr.learn.microservice.currencyconversionservice.CurrencyExchangeServiceAPIGatewayProxy;
import techcr.learn.microservice.currencyconversionservice.CurrencyExchangeServiceProxy;

@RestController
@RequestMapping("/currency-converter")
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @Autowired
    private CurrencyExchangeServiceAPIGatewayProxy currencyExchangeServiceGatewayProxy;

    @GetMapping("/template/{from}/{to}/quantity/{quantity}")
    private CurrencyConversionBean convertCurrencyRestTemplate(@PathVariable String from, @PathVariable String to,
                                                   @PathVariable BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate()
            .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionBean.class, uriVariables);
        CurrencyConversionBean bean = responseEntity.getBody();
        bean.setQuantity(quantity);
        bean.setCalculatedAmount(bean.getConversionRate().multiply(quantity));
        return bean;
    }

    @GetMapping("/feign/{from}/{to}/quantity/{quantity}")
    private CurrencyConversionBean convertCurrencyFeignClient(@PathVariable String from, @PathVariable String to,
                                                   @PathVariable BigDecimal quantity) {
        CurrencyConversionBean bean = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);
        bean.setQuantity(quantity);
        bean.setCalculatedAmount(bean.getConversionRate().multiply(quantity));
        return bean;
    }

    @GetMapping("/gateway/{from}/{to}/quantity/{quantity}")
    private CurrencyConversionBean convertCurrencyApiGateway(@PathVariable String from, @PathVariable String to,
                                                   @PathVariable BigDecimal quantity) {
        CurrencyConversionBean bean = currencyExchangeServiceGatewayProxy.retrieveExchangeValue(from, to);
        bean.setQuantity(quantity);
        bean.setCalculatedAmount(bean.getConversionRate().multiply(quantity));
        return bean;
    }
}
