package techcr.learn.microservice.currencyexchangeservice.controller;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class ExchangeValue {

    @NonNull
    @Id
    private Long id;
    @NonNull
    @Column(name = "currency_from")
    private String from;
    @NonNull
    @Column(name = "currency_to")
    private String to;
    @NonNull
    private BigDecimal conversionRate;
    @Transient
    private Integer port;
}
