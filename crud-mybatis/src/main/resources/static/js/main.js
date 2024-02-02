"use strict";

import { HOST_API } from "../config/config";

const gBASE_URL = HOST_API + '/api';

console.log(gBASE_URL);


//Hàm setCookie
function setCookie(cname, cvalue, exdays, remember) {
    "use strict";
    const d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    if (remember) {
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    } else {
        document.cookie = cname + "=" + cvalue + ";path=/";
    }
}

//Hàm get Cookie
function getCookie(cname) {
    "use strict";
    const name = cname + "=";
    const decodedCookie = decodeURIComponent(document.cookie);
    const ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

const accessToken = getCookie("token");
var userInfo = "";
if (accessToken) {
    $.ajax({
        url: gBASE_URL + "/auth/me",
        method: "GET",
        headers: {
            "Authorization": "Bearer " + accessToken
        },
        async: false,
        dataType: 'json',
        success: function (res) {
            userInfo = res;
            if (userInfo.roles.includes("ROLE_ADMIN")) {
                $("#link-user-manager").css("display", "block");
            }
        },
        error: function (err) {
            console.log(err.responseText);
        }
    });
}

export { accessToken, gBASE_URL };
