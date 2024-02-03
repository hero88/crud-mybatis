import { instance } from "./config/config.js";
const baseURL = instance.defaults.baseURL;
$(".btn-changepass").click(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');
    var newPass = $('.password').val();
    axios.get(baseURL+'v1/recovery-password', {
        params: {
            id: id,
            newPass: newPass
        },
    }).then(function (resp) {
        console.log(response);
    }).catch(function (error) {
        console.log(error);
    });
});