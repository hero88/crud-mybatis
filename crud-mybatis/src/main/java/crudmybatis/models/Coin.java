package crudmybatis.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class Coin {

    private Long id;

    private String name;

    private String symbol;

    private int coinmarketId;

    private int quantity;

    private Long userId;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;

    public Coin(String name, String symbol, int coinmarketId, int quantity, Long userId, Date createdAt ) {
       // this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.coinmarketId = coinmarketId;
        this.quantity = quantity;
        this.userId = userId;
        this.createdAt = createdAt;
        //this.updatedAt = updatedAt;
    }
}
