import { instance } from "../config/config.js";
const baseURL = instance.defaults.baseURL;
var api = baseURL + "v1/payroll";

const btnFilter =document.getElementById('btn-filter');
const monthFrom = document.getElementById('select-month-from');
const monthTo = document.getElementById('select-month-to');
const viewTable = document.getElementById("tbody-table-payroll");
const datalistEmployee = document.getElementById('employee-choices');
const containerChoiceEmployee = document.getElementById('container-employee');
const employeeChoice = document.getElementById('employee-choice');
var yearFrom = document.getElementById('filter-year-from');
var yearTo = document.getElementById('filter-year-to');
var listIdEmployeeChoice = [];
var listIdEmployee = [];

const getAllPayroll = async (month) => {
  try {
    const response = await axios.get(api, { params: { month: month } });
    const data = response.data.data;
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

function findAllEmployee() {
  return new Promise((resolve, reject) => {
    axios
      .get(baseURL + "v1/employees/list")
      .then((resp) => {
        var optionsElm = "";
        if (resp.data.success === true) {
          resp.data.data.forEach((e, index) => {
            listIdEmployee.push(e.id);
            optionsElm += `<option value="${e.id}">${e.lastName} ${e.firstName}</option>`;
            datalistEmployee.innerHTML = optionsElm;
          });
          resolve(); // Gọi resolve() khi hoàn thành việc lấy dữ liệu
        } else {
          optionsElm = `<option value="">No data</option>`;
          datalistEmployee.innerHTML = optionsElm;
          reject(); // Gọi reject() nếu có lỗi xảy ra
        }
      })
      .catch((err) => {
        reject(); // Gọi reject() nếu có lỗi xảy ra
      });
  });
}
findAllEmployee()

function viewMonth() {
    var selectMonth = document.getElementById("select-month");
    var selectMonthFrom = document.getElementById("select-month-from");
    var selectMonthTo = document.getElementById("select-month-to");
    selectMonth.className =""
    selectMonth.innerHTML = '';
    for (var i = 1; i <= 12; i++) {
        var option = document.createElement("option");
        option.value = i; 
        option.text = i + "";  
        var option1 = document.createElement("option");
        option1.value = i; 
        option1.text = i + ""; 
        var option2 = document.createElement("option");
        option2.value = i; 
        option2.text = i + ""; 
        selectMonth.appendChild(option);
        selectMonthFrom.appendChild(option1);
        selectMonthTo.appendChild(option2);
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

btnFilter.addEventListener('click', ()=>{
  const mFrom = monthFrom.value;
  const mTo = monthTo.value;
  yearFrom = document.getElementById('filter-year-from').value = '' ? new Date().getFullYear() : document.getElementById('filter-year-from').value;
  yearTo = document.getElementById('filter-year-to').value = '' ? new Date().getFullYear() : document.getElementById('filter-year-to').value;
  const list = listIdEmployeeChoice.length == 0 ? listIdEmployee : listIdEmployeeChoice;
  if(yearFrom > yearTo){
    swal("Something wrong!", {
      icon: "error",
    })
  }else if (mFrom > mTo){
    swal("Something wrong!", {
      icon: "error",
    })
  }else{
    axios.get(api+"/net-salary",{
      params:{
        list:list.join(","),
        monthFrom: mFrom,
        monthTo: mTo,
        yearFrom: yearFrom,
        yearTo: yearTo,
      }
    }).then(resp => {
      viewTable.innerHTML = ''
      document.getElementById('nameQuery').textContent ='Month - Year'
      resp.data.data.forEach((payroll) => {
        // Create the main row element
        const row = document.createElement("tr");
        row.className =
          "text-sm bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600";
  
        // Create the cells
        const cell1 = document.createElement("th");
        cell1.scope = "row";
        cell1.className =
          "px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white";
        cell1.textContent = `${payroll.id}`;
        row.appendChild(cell1);
  
        const cell2 = document.createElement("td");
        cell2.className = "px-6 py-4";
        cell2.textContent = `${payroll.employee_id} - ${payroll.name}`;
        row.appendChild(cell2);
  
        const cell3 = document.createElement("td");
        cell3.className = "px-6 py-4";
        cell3.textContent = `${payroll.salary} $`;
        row.appendChild(cell3);
  
        const cell4 = document.createElement("td");
        cell4.className = "px-6 py-4";
        cell4.textContent = `${payroll.month}/${payroll.year}`;
        row.appendChild(cell4);
  
        const cell5 = document.createElement("td");
        cell5.className = "px-6 py-4 net-salary-class";
  
        const spanElement = document.createElement("span");
        spanElement.className = "bg-green-200 text-green-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded dark:bg-gray-700 dark:text-green-400 border border-green-400";
        
        spanElement.textContent = `${payroll.Net} $`;
        cell5.appendChild(spanElement);
        row.appendChild(cell5 );
  
        viewTable.appendChild(row);
      });
    }).catch(err => {

    })
  }
 
})

employeeChoice.addEventListener('keydown', function (event) {
  if (event.key === 'Enter') {
    event.preventDefault();
  }
});

employeeChoice.addEventListener('change', (e) => {
  var selectedOption = e.target.value.trim();
  var options = document.querySelectorAll('#employee-choices option');
  var search = "";
  let check = false;
  for (var i = 0; i < options.length; i++) {
    if (options[i].value == selectedOption) {
      search = options[i].text;
      check = true;
      break;
    }
  }
  if (selectedOption && selectedOption != "" && !listIdEmployeeChoice.includes(selectedOption) && check) {
    listIdEmployeeChoice.push(selectedOption);

    var option = document.createElement('option');
    option.classList.add('mb-3', 'border-2', 'mx-2', 'rounded-lg', 'p-2');
    option.value = selectedOption;
    option.textContent = search;
    containerChoiceEmployee.appendChild(option);
    e.target.value = "";
    option.addEventListener('click', function () {
      containerChoiceEmployee.removeChild(option);
      listIdEmployeeChoice = listIdEmployeeChoice.filter(e => e !== selectedOption);
    });
  }
})

getAllPayroll(selectMonth.value);
viewMonth();