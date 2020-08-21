package techcr.learn.microservice.currencyconversionservice.controller;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CurrencyConversionBean {
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionRate;
    private BigDecimal quantity;
    private BigDecimal calculatedAmount;
    private int port;
}
