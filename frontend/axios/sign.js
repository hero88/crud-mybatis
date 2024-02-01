$("#container-register").hide();
$(".open-ui-register").click(function () {
    $("#container-login").hide();
    $("#container-register").show(800);
});

$(".open-ui-login").click(function () {
    $("#container-register").hide();
    $("#container-login").show(800);
});

const baseURL = instance.defaults.baseURL;

$(".btn-login").click(function () {
    var mailLogin = $('.mailLogin').val();
    var passLogin = $('.passLogin').val();
    axios.post(baseURL+'v1/login', {
        email: mailLogin,
        password: passLogin
    }).then(function (resp) {
        console.log(resp.data)
        //window.location.href = "";
    }).catch(function (error) {
        console.log(error);
    });
});

$(".btn-register").click(function () {
    var usernameRegister = $('.username-register').val();
    var fullnameRegister = $('.fullname-register').val();
    var mailRegister = $('.email-register').val();
    var ageRegister = $('.age-register').val();
    var passRegister = $('.pass-register').val();
    var confirmPass = $('.confirm-pass-register').val();
    var genderRegister =  $('input[name="gender"]:checked').val();
    var phoneNumberRegister = $('.phone-register').val();
    var AddressRegister = $('.address-register').val();

    if (confirmPass !== passRegister) {
        alert('Password is not match')
    } else {
        axios.post(baseURL+'v1/register', {
            username: usernameRegister,
            password: passRegister,
            name: fullnameRegister,
            gender: genderRegister,
            age: ageRegister,
            email: mailRegister,
            phoneNumber: phoneNumberRegister,
            address: AddressRegister
        }).then(function (resp) {
            alert("Check your email");
        }).catch(function (error) {
            console.log(error);
        });
    }
});

$(".btn-send-mail-forgot").click(function(){
    axios.get(baseURL+'v1/sendmail-forgot-password', {
        params: {
            email: $('#mail-forgot').val(),
        },
    }).then(function (resp) {
        alert("Check your email")
    }).catch(function (error) {
        console.log(error);
    });
})