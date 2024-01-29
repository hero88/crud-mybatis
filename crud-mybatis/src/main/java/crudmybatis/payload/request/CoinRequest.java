package crudmybatis.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoinRequest {
    @NotBlank
    private int coinmarketId;
    @NotBlank
    @Min(0)
    private int quantity;
    @NotBlank
    private String name;
    @NotBlank
    private String symbol;

    public CoinRequest(int coinmarketId, int quantity, String name,String symbol) {
        this.coinmarketId = coinmarketId;
        this.quantity = quantity;
        this.name = name;
        this.symbol = symbol;
    }
}
