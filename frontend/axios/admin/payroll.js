import { instance } from "../config/config.js";
const baseURL = instance.defaults.baseURL;
var api = baseURL + "v1/payroll";

const btnFilter = document.getElementById('btn-filter');
const monthFrom = document.getElementById('select-month-from');
const monthTo = document.getElementById('select-month-to');
const viewTable = document.getElementById("tbody-table-payroll");
const datalistEmployee = document.getElementById('employee-choices');
const containerChoiceEmployee = document.getElementById('container-employee');
const employeeChoice = document.getElementById('employee-choice');
var yearFrom = document.getElementById('filter-year-from');
var yearTo = document.getElementById('filter-year-to');
const containerModalDetail = document.getElementById('container-detail-payroll');
const btnUpdateDetailPayroll =document.getElementById('btn-update-detail-payroll');
const btnDetail = document.getElementById('btn-detail');
var listIdEmployeeChoice = [];
var listIdEmployee = [];
var payrollDetail = {};
var listHolidays = [];
var max=0
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
      row.appendChild(cell5);
      row.setAttribute("data-modal-target", "default-view-payroll");
      row.setAttribute("data-modal-toggle", "default-view-payroll");
      row.onclick = function() { 
         getDetail(payroll.id);
      };
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
  selectMonth.className = ""
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
selectMonth.addEventListener("change", function () {
  getAllPayroll(selectMonth.value);
})

window.searchEmployee = async () => {

  const firstName = document.getElementById("table-search").value;
  try {
    const response = await axios.get(api + `/${selectMonth.value}`, { params: { firstname: firstName } });
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
      row.appendChild(cell5);

      viewTable.appendChild(row);
    });
  } catch (error) {
    console.log(error.message);
  }
}

btnFilter.addEventListener('click', () => {
  const mFrom = monthFrom.value;
  const mTo = monthTo.value;
  yearFrom = document.getElementById('filter-year-from').value = '' ? new Date().getFullYear() : document.getElementById('filter-year-from').value;
  yearTo = document.getElementById('filter-year-to').value = '' ? new Date().getFullYear() : document.getElementById('filter-year-to').value;
  const list = listIdEmployeeChoice.length == 0 ? listIdEmployee : listIdEmployeeChoice;
  if (yearFrom > yearTo) {
    swal("Something wrong!", {
      icon: "error",
    })
  } else if (mFrom > mTo) {
    swal("Something wrong!", {
      icon: "error",
    })
  } else {
    axios.get(api + "/net-salary", {
      params: {
        list: list.join(","),
        monthFrom: mFrom,
        monthTo: mTo,
        yearFrom: yearFrom,
        yearTo: yearTo,
      }
    }).then(resp => {
      viewTable.innerHTML = ''
      document.getElementById('nameQuery').textContent = 'Month - Year'
      resp.data.data.forEach((payroll) => {
        // Create the main row element
        const row = document.createElement("tr");
        row.className = "text-sm bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600 btn";
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
        row.appendChild(cell5);

        row.setAttribute("data-modal-target", "default-view-payroll");
        row.setAttribute("data-modal-toggle", "default-view-payroll");
        row.onclick = function () {
          getDetail(payroll.id);
        };
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

function getAllHolidays() {
  return new Promise((resolve, reject) => {
    axios.get(baseURL + "v1/holidays")
      .then(resp => {
        listHolidays = resp.data;
        resolve(resp.data.data);
      })
      .catch(err => {
        reject(err);
      });
  });
}
getAllHolidays();
function getDetail(id) {
  btnDetail.click();
  axios.get(api + "/id", {
    params: {
      id: id
    }
  }).then(resp => {
    payrollDetail = resp.data.data;
    axios.get(api + "/employee/" + payrollDetail.employeeId).then(respE => {
      max = respE.data.leavePaidDays;
      containerModalDetail.innerHTML = `<form class="max-w-md mx-auto">
        <div class="relative z-0 w-full my-5 group">
            <div>
              <button id="dropdownCheckboxButton" data-dropdown-toggle="dropdownDefaultCheckbox"
                  class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                  type="button">Holidays <svg class="w-2.5 h-2.5 ms-3" aria-hidden="true"
                      xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 10 6">
                      <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                          d="m1 1 4 4 4-4" />
                  </svg>
              </button>
              <div id="dropdownDefaultCheckbox"
                  class="relative z-0 hidden bg-white divide-y divide-gray-100 rounded-lg shadow dark:bg-gray-700 dark:divide-gray-600 w-full">
                  <ul class="p-3 space-y-3 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="dropdownCheckboxButton">
                  ${listHolidays.map(e => `    
                  <li>
                          <div class="flex items-center">
                          <input value="${e.id}" type="checkbox" name="holidays" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500" placeholder=" " required  ${payrollDetail.holidayIds.includes(e.id) ? 'checked' : ''}/>
                          <label for="checkbox-item-1"
                                                class="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">${e.holidayName}</label>
                          </div>
                      </li>
                      `).join('')}
                  </ul>
              </div>
            </div>
        </div>
        <div class="grid md:grid-cols-2 md:gap-6">
          <div class="relative z-0 w-full mb-5 group">
              <input value="${payrollDetail.salary}" type="text" name="salary" id="salary" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
              <label for="salary" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Salary</label>
          </div>
          <div class="relative z-0 w-full mb-5 group">
              <input value="${payrollDetail.bonus}" type="text" name="bonus" id="bonus" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
              <label for="bonus" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Bonus</label>
          </div>
        </div>
        <div class="grid md:grid-cols-2 md:gap-6">
          <div class="relative z-0 w-full mb-5 group">
              <input value="${payrollDetail.deductions}" type="tel" name="deduction" id="deduction" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
              <label for="deduction" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Deduction</label>
          </div>
          <div class="relative z-0 w-full mb-5 group">
              <input value="${payrollDetail.leavePaidDays}" type="number" min="0" max="${max}" name="leave-paid-day" id="leave-paid-day" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
              <label for="leave-paid-day" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Leave paid day</label>
          </div>
        </div>
        
      </form>`
      const dropdownButton = document.getElementById('dropdownCheckboxButton');
      const dropdownContent = document.getElementById('dropdownDefaultCheckbox');

      dropdownButton.addEventListener('click', () => {
        dropdownContent.classList.toggle('hidden');
      });
    })
  }).catch(err => {
    console.log(err)
  })
}

btnUpdateDetailPayroll.addEventListener('click',()=>{
    const payroll = { ...payrollDetail};
    payroll.salary = document.getElementById('salary').value;
    let holidays =[];
    
    document.querySelectorAll('input[name="holidays"]:checked').forEach(e=>{
      holidays.push(e.value);
    });
    payroll.holidayIds=holidays.toString();
    payroll.bonus = document.getElementById('bonus').value;
    payroll.deductions =document.getElementById('deduction').value;
    payroll.leavePaidDays =document.getElementById('leave-paid-day').value;
    if(max < document.getElementById('leave-paid-day').value){
      swal("Something wrong!", {
        icon: "error",
      })
    }else{
      axios.put(api,payroll).then(resp =>{
        payrollDetail=resp.data.data;
        swal("Create success!", "", "success").then((resp) => {
          getAllPayroll(selectMonth.value);
        })
      }).catch(err =>{
        swal("Something wrong!", {
          icon: "error",
        })
      })
    }
})


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
