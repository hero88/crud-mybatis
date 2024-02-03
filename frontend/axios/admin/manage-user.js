import { instance } from "./config/config.js";
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
            editButton.className = 'btn btn-info btn-xs';
            editButton.type = 'button';
            editButton.setAttribute('data-bs-toggle', 'modal');
            editButton.setAttribute('data-bs-target', '#modalEdit');
            editButton.textContent = 'View';
            editButton.onclick = function () {
                getOneUser(user.username); 
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
      }
      else if(response.status == 400) {
        alert(del.data.message);
    }
    } catch (error) {
        console.log(error.message);
    }
}