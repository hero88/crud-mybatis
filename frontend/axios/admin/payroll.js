import { instance } from "../config/config.js";
const baseURL = instance.defaults.baseURL;
var api = baseURL + "v1/payroll";

const getAllPayroll = async (month) => {
  try {
    const response = await axios.get(api, { params: { month: month } });

    const data = response.data.data;
    const viewTable = document.getElementById("tbody-table-payroll");
    viewTable.innerHTML = ''
    data.forEach((payroll) => {
      // Create the main row element
      const row = document.createElement("tr");
      row.className =
        "text-sm bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600";

      // Create the cells
      const cell1 = document.createElement("th");
      cell1.scope = "row";
      cell1.className =
        "px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white";
      cell1.textContent = `${payroll.last_name} ${payroll.first_name}`;
      row.appendChild(cell1);

      const cell2 = document.createElement("td");
      cell2.className = "px-6 py-4";
      cell2.textContent = `${payroll.email}`;
      row.appendChild(cell2);

      const cell3 = document.createElement("td");
      cell3.className = "px-6 py-4";
      cell3.textContent = `${payroll.salary} $`;
      row.appendChild(cell3);

      const cell4 = document.createElement("td");
      cell4.className = "px-6 py-4";
      cell4.textContent = `${payroll.hire_date}`;
      row.appendChild(cell4);

      const cell5 = document.createElement("td");
      cell5.className = "px-6 py-4 net-salary-class";

      const spanElement = document.createElement("span");
      spanElement.className = "bg-green-200 text-green-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded dark:bg-gray-700 dark:text-green-400 border border-green-400";
      
      spanElement.textContent = `${payroll.net_salary} $`;
      cell5.appendChild(spanElement);
      row.appendChild(cell5 );

      viewTable.appendChild(row);
    });
  } catch (error) {
    console.log(error.message);
  }
};


function viewMonth() {
    var selectMonth = document.getElementById("select-month");
    selectMonth.className =""
    selectMonth.innerHTML = '';

    for (var i = 1; i <= 12; i++) {
        var option = document.createElement("option");
        option.value = i; 
        option.text = i + "";  
        selectMonth.appendChild(option);
    }
}

var selectMonth = document.getElementById("select-month");
selectMonth.addEventListener("change", function() {
    getAllPayroll(selectMonth.value);
})

window.searchEmployee = async () =>{
 
  const firstName = document.getElementById("table-search").value;
  try {
    const response = await axios.get(api +`/${selectMonth.value}`, { params: { firstname: firstName } });
    const data = response.data.data;
    const viewTable = document.getElementById("tbody-table-payroll");
    viewTable.innerHTML = ''
    
    data.forEach((payroll) => {
      // Create the main row element
      const row = document.createElement("tr");
      row.className =
        "text-sm bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600";

      // Create the cells
      const cell1 = document.createElement("th");
      cell1.scope = "row";
      cell1.className =
        "px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white";
      cell1.textContent = `${payroll.last_name} ${payroll.first_name}`;
      row.appendChild(cell1);

      const cell2 = document.createElement("td");
      cell2.className = "px-6 py-4";
      cell2.textContent = `${payroll.email}`;
      row.appendChild(cell2);

      const cell3 = document.createElement("td");
      cell3.className = "px-6 py-4";
      cell3.textContent = `${payroll.salary} $`;
      row.appendChild(cell3);

      const cell4 = document.createElement("td");
      cell4.className = "px-6 py-4";
      cell4.textContent = `${payroll.hire_date}`;
      row.appendChild(cell4);

      const cell5 = document.createElement("td");
      cell5.className = "px-6 py-4 net-salary-class";

      const spanElement = document.createElement("span");
      spanElement.className = "bg-green-200 text-green-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded dark:bg-gray-700 dark:text-green-400 border border-green-400";
      
      spanElement.textContent = `${payroll.net_salary} $`;
      cell5.appendChild(spanElement);
      row.appendChild(cell5 );

      viewTable.appendChild(row);
    });
  } catch (error) { 
    console.log(error.message);
  }
}


getAllPayroll(selectMonth.value);
viewMonth();