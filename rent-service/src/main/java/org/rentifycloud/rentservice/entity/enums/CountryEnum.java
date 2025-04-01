package org.rentifycloud.rentservice.entity.enums;

import lombok.Getter;

@Getter
public enum CountryEnum {
    RU(CurrencyEnum.RUB),
    BY(CurrencyEnum.BYR),
    KZ(CurrencyEnum.KZT),
    OTHER(CurrencyEnum.USD);

    private final CurrencyEnum currencyCode;
    CountryEnum(CurrencyEnum currencyCode) {
        this.currencyCode = currencyCode;
    }

}
