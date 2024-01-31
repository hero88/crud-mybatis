package com.allxone.coinmarket.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoinApiReponse {
    private Data data;
    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }
    public static class Data {
        private List<CryptoCurrency> cryptoCurrencyList;

        public List<CryptoCurrency> getCryptoCurrencyList() {
            return cryptoCurrencyList;
        }

        public void setCryptoCurrencyList(List<CryptoCurrency> cryptoCurrencyList) {
            this.cryptoCurrencyList = cryptoCurrencyList;
        }
    }
}
