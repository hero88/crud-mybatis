package com.allxone.coinmarket.utilities;

import com.allxone.coinmarket.exception.common.ParamInvalidException;

import java.util.Objects;
import java.util.regex.Pattern;

public class ValidatorUtils {
    public static void checkNullParam(Object... object) throws ParamInvalidException {
        for (Object obj : object) {
            if (obj == null || Objects.equals(obj, "")) {
                throw new ParamInvalidException("Please enter complete information!");
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
    
    public static void checkEmail(String email) throws ParamInvalidException {
        checkNullParam(email); 

       
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new ParamInvalidException("Invalid email !");
        }
    }

	public static void checkPhone(String phone) throws ParamInvalidException {
		checkNullParam(phone);

		String phoneRegex = "\\d{10}";
		if (!phone.matches(phoneRegex)) {
			throw new ParamInvalidException("Invalid phone number!");
		}
	}
}
