package crudmybatis.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DeletionCoinRequest {
    @NotBlank
    private Long idCoins;

    @NotBlank
    private Long userID;

    public DeletionCoinRequest(Long idCoins) {

        this.idCoins = idCoins;
        this.userID = idCoins;
    }
}
