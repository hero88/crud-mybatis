package com.allxone.coinmarket.utilities;

import com.allxone.coinmarket.exception.common.ParamInvalidException;

import java.util.Objects;

public class ValidatorUtils {
    public static void checkNullParam(Object... object) throws ParamInvalidException {
        for (Object obj : object) {
            if (obj == null || Objects.equals(obj, "")) {
                throw new ParamInvalidException("Vui lòng nhập đầy đủ thông tin");
            }

        }
    }

    public static boolean checkNull(Object... object) {
        for (Object obj : object) {
            if (obj == null || Objects.equals(obj, "")) {
                return true;
            }

        }
        return false;
    }
}
