package crudmybatis.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotRequest {
    @NotBlank
    private String email;
}
