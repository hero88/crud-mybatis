
import { instance } from "../config/config.js";
const baseURL = instance.defaults.baseURL;
var api = baseURL+"v1/user";

window.onload = function () {
    getAllUser();
};

const getAllUser = async () => {
    const config = {
        headers: {
            'Authorization': 'Bearer ' + getCookie('token')
        }
    };

    try {
        const response = await axios.get(api);
        const data = response.data.data;
        const tableBody = document.getElementById('userTable').getElementsByTagName('tbody')[0];
        tableBody.innerHTML = '';
        data.forEach(user => {
            const row = tableBody.insertRow();
            const cell1 = row.insertCell(0);
            const cell2 = row.insertCell(1);
            const cell3 = row.insertCell(2);
            const cell4 = row.insertCell(3);
            const cell5 = row.insertCell(4);
            const cell6 = row.insertCell(5);
            const cell7 = row.insertCell(6); // Action cell

            cell1.textContent = user.username || '-';
            cell2.textContent = user.email || '-';
            cell3.textContent = user.name || '-';
            cell4.textContent = user.phoneNumber || '-';
            cell5.textContent = user.gender || '-';
            cell6.textContent = user.age || '-';

            // Create Edit button
            const editButton = document.createElement('a');
            editButton.className = 'btn btn-info btn-xs mx-2';
            editButton.type = 'button';
            editButton.setAttribute('data-bs-toggle', 'modal');
            editButton.setAttribute('data-bs-target', '#modalEdit');
            editButton.setAttribute('id', 'modalView');
            editButton.textContent = 'View';
            editButton.onclick = function () {
                getOneUser(user.id); 
            };
            cell7.appendChild(editButton);

            // Create Delete button
            const deleteButton = document.createElement('a');
            deleteButton.className = 'btn btn-danger btn-xs';
            deleteButton.textContent = 'Del';
            deleteButton.onclick = function () {
                deleteUser(user.id); 
            };
            cell7.appendChild(deleteButton);
        });
    } catch (error) {
        console.log(error.message);
    }
};



const deleteUser = async(id)=>{
    const config = {
        headers: {
            'Authorization': 'Bearer ' + getCookie('token')
        }
    };
    console.log('cookie: '+getCookie('token'));
    try {
      const del = await  axios.delete(api+'/'+id, config);
      if(del.status == 200){
        alert(del.data.message);
        getAllUser();
      }
      else if(response.status == 400) {
        alert(del.data.message);
    }
    } catch (error) {
        console.log(error.message);
    }
}

const getOneUser = async (id) => {
    const config = {
        headers: {
            'Authorization': 'Bearer ' + getCookie('token')
        }
    };
  try {
    const response = await axios.get(api + "/" + id,config);
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

$('#modelUser').click(function () {

    $('#div-password').css('display', 'block');
    $('#createUser').css('display', 'block');
    $("#email").val(''),
    $("#username").val(''),
    $("#password").val(''),
    $("#name").val(''),
    $("#phone").val(''),
    $("#gender").val(''),
    $("#age").val(''),
    $("#address").val('')
});


$('#createUser').click(function () {

    const config = {
        headers: {
            'Authorization': 'Bearer ' + getCookie('token')
        }
    };
    const data = {
        email: $("#email").val(),
        username: $("#username").val(),
        password: $("#password").val(),
        name: $("#name").val(),
        phoneNumber: $("#phone").val(),
        gender: $("#gender").val(),
        age: $("#age").val(),
        address: $("#address").val(),
    };

    axios.post(api , data, config) 
        .then(function (response) {
            if (response.status === 200) {
                alert("Created successfully");
                getAllUser();
            } else if (response.status === 400) {
                alert("Email exist");
            }
        })
        .catch(function (error) {
            console.log(error.message);
        });
});
