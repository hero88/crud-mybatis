import { instance } from "../config/config.js";
const apiProfile = instance.defaults.baseURL + 'v1/user/own';
// hiển thị menu và nut login
document.addEventListener("DOMContentLoaded", function () {
    var buttonLogin = document.getElementById("buttonLogin");
    var menu = document.getElementById("menu");
    // Kiểm tra xem cookie có tồn tại không
    var cookieExists = getCookie('token');
    // Nếu cookie không tồn tại, hiển thị phần tử loginContainer
    if (!cookieExists) {
        buttonLogin.style.display = "block";
    } else {
        menu.style.display = "block";
    }
});

// hàm get cookies
function getCookie(cookieName) {
    var name = cookieName + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var cookieArray = decodedCookie.split(';');
    for (var i = 0; i < cookieArray.length; i++) {
        var cookie = cookieArray[i].trim();
        if (cookie.indexOf(name) == 0) {
            return cookie.substring(name.length, cookie.length);
        }
    }
    return null;
}

function profile(apiUrl) {
    var authToken = getCookie('token');
    var name = document.getElementById('name');
    const requestConfig = {
        headers: {
            'Authorization': `Bearer ${authToken}`,
        }
    };
    axios.get(apiUrl, requestConfig)
        .then(response => {
            if (response.status !== 200) {
                throw new Error(`Error: ${response.status}`);
            }
            return response.data;
        })
        .then(data => {
            var profile = data;
            name.textContent = profile.data.name;
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}

function logout() {
    var cookies = document.cookie.split(";");
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/";
    }
    window.location.href = window.location.origin + "/html/page/SignInSignUp.html"; // Thay đổi đường dẫn nếu cần
}

document.getElementById("logoutButton").addEventListener("click", function () {
    logout();
});
if (getCookie('token') != null) {
    profile(apiProfile);
}