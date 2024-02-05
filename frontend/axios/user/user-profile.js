import { instance } from "../config/config.js";
const baseURL = instance.defaults.baseURL;
var api = baseURL+"v1/user";
// var data = new URLSearchParams(window.location.search).get('username');

const getOneUserByEmail = async () => {
    
    const config = {
        headers: {
            'Authorization': 'Bearer ' + getCookie('token')
        }
    };
  try {
    const response = await axios.get(api + "/own" ,config);
    if (response.status == 200) {
      $("#h5Username").html(response.data.data.username);
      $("#h5Email").html(response.data.data.username);
      $("#email").val(response.data.data.email);
      $("#username").val(response.data.data.username);
      $("#name").val(response.data.data.name);
      $("#phone").val(response.data.data.phoneNumber);
      $("#age").val(response.data.data.age);
      $("#gender").val(response.data.data.gender);
      $("#address").val(response.data.data.address);
      $("#id").val(response.data.data.id);
    }
  
  } catch (error) {
    console.log(error.message);
  }
};

$('#saveInfo').click(function () {
    const id = $("#id").val();
    const config = {
        headers: {
            'Authorization': 'Bearer ' + getCookie('token')
        }
    };
    const data = {
        email: $("#email").val(),
        username: $("#username").val(),
        name: $("#name").val(),
        phoneNumber: $("#phone").val(),
        gender: $("#gender").val(),
        age: $("#age").val(),
        address: $("#address").val(),
    };

    axios.put(api + '/' + id, data, config) // Corrected the order of parameters
        .then(function (response) {
            if (response.status === 200) {
                alert(response.data.message);
            } else if (response.status === 400) {
                alert(response.data.message);
            }
        })
        .catch(function (error) {
            console.log(error.message);
        });
});



$('#savePassword').click(function () {
    const config = {
        headers: {
            'Authorization': 'Bearer ' + getCookie('token')
        }
    };
    const data = {
        currentPassword: $("#password").val(),
        newPassword: $("#passwordNew").val(),
        passwordConfirm: $("#passwordConfirm").val(),
    };

    axios.patch(api, data, config)
        .then(function (response) {
            if (response.status === 200) {
                alert(response.data.message);
            } else if (response.status === 400) {
                alert(response.data.message);
            }
        })
        .catch(function (error) {
            console.error(error.message);
        });
});

getOneUserByEmail();