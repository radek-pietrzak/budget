package com.home.budget.requests.postput;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PostMultiTransactionRequest {

    @ApiModelProperty(value = "Object of multi-transaction", required = true, example = "Radek^Kosmetyki i higieniczne^kosmetyki rossman^19,47^PLN^2022-09-01^Karta^Tak&Radek^Jedzenie pozostałe^orzechy zwrot^-33,10^PLN^2022-09-02^Karta^Tak&Radek^Jedzenie pozostałe^chleb^11,00^PLN^2022-09-02^Karta^Tak&Radek^Jedzenie pozostałe^jaja^22,00^PLN^2022-09-03^Gotówka^Nie&Radek^Owoce i warzywa^warzywniak^42,00^PLN^2022-09-03^Gotówka^Nie&Radek^Inne (fryzjer)^Fryzjer^45,00^PLN^2022-09-05^Karta^Tak&Radek^Jedzenie pozostałe^orzechy^82,18^PLN^2022-09-06^Karta^Tak&Radek^Jedzenie pozostałe^tempeh^84,00^PLN^2022-09-06^Karta^Tak&Radek^Owoce i warzywa^warzywniak^52,95^PLN^2022-09-06^Karta^Tak&Radek^Jedzenie pozostałe^chleb^11,00^PLN^2022-09-06^Karta^Tak&Radek^Owoce i warzywa^warzywniak^48,55^PLN^2022-09-09^Karta^Tak&Radek^Jedzenie pozostałe^chleb^11,00^PLN^2022-09-09^Karta^Tak")
    @NotNull
    @Valid
    private String multiTransaction;
}
