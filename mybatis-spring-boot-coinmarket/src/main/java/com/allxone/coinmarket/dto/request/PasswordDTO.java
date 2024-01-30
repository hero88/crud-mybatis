package com.allxone.coinmarket.dto.request;

import lombok.Data;

@Data
public class PasswordDTO
{
      private String currentPassword, newPassword, passwordConfirm;
}
