import { instance } from "../config/config.js";

const baseURL = instance.defaults.baseURL;
var api = baseURL + "v1/time-tracking";

const tableContainer = document.querySelector("#tbl-time-tracking");
const cardTemplate = document.getElementById("card-template");
const changeDate = document.getElementById("change-date");
const btnCreate = document.getElementById("btn-create-new-tracking");
const selectBoxEmployee = document.getElementById("select-box-employee");
const btnConfirmAdd = document.getElementById("btn-confirm-add");
const clockIn = document.getElementById("clock_in");
const clockOut = document.getElementById("clock_out");
const containerDetail = document.getElementById("container-detail");
const clockInUpdate = document.getElementById("clock_in_update");
const clockOutUpdate = document.getElementById("clock_out_update");
const identify = document.getElementById('identify');
const btnMore = document.getElementById('btn-more');
const btnViewAll = document.getElementById('btn-view-all-tracking');
const btnMoreAll = document.getElementById('btn-more-all');
const containerViewAll = document.getElementById('container-view-all');
const datalistEmployee = document.getElementById('employee-choices');
const containerChoiceEmployee = document.getElementById('container-employee');
const employeeChoice = document.getElementById('employee-choice');
const filterFromDate = document.getElementById('filter-from-date');
const filterToDate = document.getElementById('filter-to-date');
const btnFilter =document.getElementById('btn-filter');

var limit = 10;
var date = new Date();
var sizeList = 0;
var listIdEmployeeChoice = [];
var listIdEmployee = [];
var limitViewAll = 10;
var sizeAll = 0;
var from = new Date(-1);
var to = new Date();
var detail = {};

for (let i = 0; i < 5; i++) {
  tableContainer.append(cardTemplate.content.cloneNode(true));
}

function findListTimeTrackingByDate(date, limited) {

  axios
    .get(api + "/list-working", {
      params: {
        date: convertDate(date),
        limit: limited
      },
    })
    .then((resp) => {
      var trElm = "";
      if (resp.data.success === true) {
        const div = cardTemplate.content.cloneNode(true);
        if (sizeList < resp.data.data.length) {
          btnMore.classList.add('show');
          btnMore.classList.remove('hidden');
          sizeList = resp.data.data.length;
        } else {
          btnMore.classList.add('hidden');
          btnMore.classList.remove('show');
        }
        resp.data.data.map((e, index) => {
          const nameId = `${e.employee_id} - ${e.last_name} ${e.first_name}`;
          trElm += `<tr class="border-b border-dashed last:border-b-0">
                <td class=""><span>${index + 1}</span></td>
                <td class="p-3 pl-0 text-start">
                    <span class="font-semibold text-light-inverse text-md/normal">${nameId}</span>
                </td>
                <td class="p-3 pr-0 text-end">
                    <span
                        class="text-center align-baseline inline-flex px-2 py-1 mr-auto items-center font-semibold text-base/none text-success bg-success-light rounded-lg">
                        ${e.clock_in}
                    </span>
                </td>
                <td class="p-3 pr-0 text-end">
                    <span
                        class="text-center align-baseline inline-flex px-2 py-1 mr-auto items-center font-semibold text-base/none text-warning bg-success-light rounded-lg">
                        ${e.clock_out}
                    </span>
                </td>
                <td class="p-3 text-center">
                    <span
                        class="text-center align-baseline inline-flex px-2 py-1 mr-auto items-center font-semibold text-base/none text-warning bg-danger-light rounded-lg">
                        ${e.total_hours} hours
                    </span>
                </td>
                <td class="p-3 pr-0 text-end">
                    <button onclick="getDetail(${e.id},'${nameId}','detail')"
                        class="btn-detail ml-auto relative text-secondary-dark bg-light-dark hover:text-primary flex items-center h-[25px] w-[25px] text-base font-medium leading-normal text-center align-middle cursor-pointer rounded-2xl transition-colors duration-200 ease-in-out shadow-none border-0 justify-center">
                        <span
                            class="flex items-center justify-center p-0 m-0 leading-none shrink-0">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none"
                                viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
                                class="w-4 h-4">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                    d="M8.25 4.5l7.5 7.5-7.5 7.5" />
                            </svg>
                        </span>
                    </button>
                </td>
            </tr>`;
        });
        tableContainer.innerHTML = trElm;
      } else {
        const div = cardTemplate.content.cloneNode(true);
        trElm = `<tr class="border-b border-dashed last:border-b-0">
                <td colspan="6"  class="text-center"><span>No Data</span></td>
            </tr>`;
        tableContainer.innerHTML = trElm;
      }
    })
    .catch((err) => { });
}

function findListTimeTrackingByFilter(list, from, to, limited) {
  axios
    .get(api + "/list-working/filter", {
      params: {
        listId: list.join(','),
        from: convertDate(from),
        to: convertDate(to),
        limit: limited
      },
    })
    .then((resp) => {
      var trElm = "";
      if (resp.data.success === true) {
        if (sizeAll < resp.data.data.length) {
          btnMoreAll.classList.add('show');
          btnMoreAll.classList.remove('hidden');
          sizeAll = resp.data.data.length;
        } else {
          btnMoreAll.classList.add('hidden');
          btnMoreAll.classList.remove('show');
        }
        resp.data.data.map((e, index) => {
          const nameId = `${e.employee_id} - ${e.last_name} ${e.first_name}`;
          trElm += `<tr ondblclick="getDetail(${e.employee_id},'${nameId}','all')" class="border-b border-dashed last:border-b-0">
              <td class=""><span>${index + 1}</span></td>
              <td class="p-3 pl-0 text-start">
                  <span class="font-semibold text-light-inverse text-md/normal">${nameId}</span>
              </td>
              <td class="p-3 pr-0 text-end">
                  <span
                      class="text-center align-baseline inline-flex px-2 py-1 mr-auto items-center font-semibold text-base/none text-success bg-success-light rounded-lg">
                      ${e.clock_in}
                  </span>
              </td>
              <td class="p-3 pr-0 text-end">
                  <span
                      class="text-center align-baseline inline-flex px-2 py-1 mr-auto items-center font-semibold text-base/none text-warning bg-success-light rounded-lg">
                      ${e.clock_out}
                  </span>
              </td>
              <td class="p-3 text-center">
                  <span
                      class="text-center align-baseline inline-flex px-2 py-1 mr-auto items-center font-semibold text-base/none text-warning bg-danger-light rounded-lg">
                      ${e.total_hours} hours
                  </span>
              </td>
              <td class="p-3 text-center">
                  <span
                      class="text-center align-baseline inline-flex px-2 py-1 mr-auto items-center font-semibold text-base/none text-primary bg-primary-light rounded-lg">
                      ${e.date_track}
                  </span>
              </td>
          </tr>`;
        });
        containerViewAll.innerHTML = trElm;
      } else {
        trElm = `<tr class="border-b border-dashed last:border-b-0">
              <td colspan="6"  class="text-center"><span>No Data</span></td>
          </tr>`;
        containerViewAll.innerHTML = trElm;
      }
    })
    .catch((err) => { });
}

findListTimeTrackingByDate(new Date(), limit);

changeDate.addEventListener("change", (e) => {
  limit = 10;
  sizeList = 0;
  date = new Date(e.target.value);
  findListTimeTrackingByDate(new Date(e.target.value), limit);
});

findAllEmployee();

function findAllEmployee() {
  axios
    .get(baseURL + "v1/employees/list")
    .then((resp) => {
      var optionsElm = "";
      if (resp.data.success === true) {
        
        resp.data.data.map((e, index) => {
          listIdEmployee.push(e.id);
          optionsElm += `<option value="${e.id}">${e.lastName} ${e.firstName}</option>`;
          datalistEmployee.innerHTML = optionsElm;

        });
      } else {
        optionsElm = `<option value="">No data</option>`;
        datalistEmployee.innerHTML = optionsElm;
      }
    })
    .catch((err) => { });
}


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
    option.classList.add('mb-3', 'border-2','mx-2','rounded-lg','p-2');
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

btnCreate.addEventListener("click", () => {
  axios
    .get(api + "/list-employee")
    .then((resp) => {
      var optionsElm = "";
      if (resp.data.success === true) {
        resp.data.data.map((e, index) => {
          optionsElm += `<option value="${e.id}">${e.id} - ${e.last_name} ${e.first_name}</option>`;
          selectBoxEmployee.innerHTML = optionsElm;
        });

      } else {
        optionsElm = `<option value="">No data</option>`;
        selectBoxEmployee.innerHTML = optionsElm;
      }
    })
    .catch((err) => { });
})

btnConfirmAdd.addEventListener("click", () => {
  const employeeId = selectBoxEmployee.value;
  const timeIn = convertHour(clockIn.value);
  const timeOut = convertHour(clockOut.value);
  if (timeIn > timeOut) {
    swal("Something wrong!", "", "error");
  } else {
    axios
      .post(api, {
        employeeId: employeeId,
        clockIn: timeIn,
        clockOut: timeOut,
        dateTrack: convertDate(date)
      })
      .then((resp) => {
        if (resp.data.success === true) {
          swal("Create success!", "", "success").then(() => {
            findListTimeTrackingByDate(date, limit);
          })
        } else {
          swal("Create failure!", "Check information again", "error");
        }
      })
      .catch((err) => {
        swal("Something wrong!", "", "error");
      });
  }

})


window.getDetail = function (id, iden,type) {
  axios
    .get(api, {
      params: {
        id: id
      }
    })
    .then((resp) => {
      detail = resp.data.data;
      detail.identify = iden;
      identify.textContent = iden + " : " + detail.totalHours + " hours";
      clockInUpdate.value = detail.clockIn;
      clockOutUpdate.value = detail.clockOut;
      date = detail.dateTrack;
      containerDetail.classList.add("show");
      containerDetail.classList.remove("hidden");
      if(type ==='all'){
        document.getElementById('btn-close-all').click();
        findListTimeTrackingByDate(date, limit);
      }
    })
    .catch((err) => { });
}

window.upgrade = function () {
  const timeIn = convertHour(clockInUpdate.value);
  const timeOut = convertHour(clockOutUpdate.value);
  if (timeIn > timeOut) {
    swal("Something wrong!", "", "error");
  } else {
    axios
      .post(api, {
        id: detail.id,
        clockIn: timeIn,
        clockOut: timeOut,
      })
      .then((resp) => {
        swal("Poof! Your time tracking has been updated!", {
          icon: "success",
        }).then(() => {
          findListTimeTrackingByDate(new Date(resp.data.data.dateTrack), limit);
        })

      })
      .catch((err) => { });
  }
}

window.elimidate = function () {
  const id = detail.id;
  const date = detail.dateTrack;
  swal({
    title: "Are you sure?",
    text: "Once deleted, you will not be able to recover this data!",
    icon: "warning",
    buttons: true,
    dangerMode: true,
  })
    .then((willDelete) => {
      if (willDelete) {
        detail = {}
        axios
          .delete(api, {
            params: {
              id: id
            }
          })
          .then((resp) => {
            swal("Poof! Your time tracking has been deleted!", {
              icon: "success",
            }).then(() => {
              findListTimeTrackingByDate(new Date(date), limit);
              document.getElementById('container-detail').classList.add('hidden');
            });

          })
          .catch((err) => { });

      }
    });

}

btnMore.addEventListener('click', () => {
  limit += 10;
  findListTimeTrackingByDate(date, limit);
})


btnViewAll.addEventListener('click', () => {
  limitViewAll = 10;
  sizeAll = 0;
  findListTimeTrackingByFilter(listIdEmployee, from, to, limitViewAll);
})

btnMoreAll.addEventListener('click', () => {
  limitViewAll += 10;
  findListTimeTrackingByFilter(listIdEmployee, from, to, limitViewAll);
})

btnFilter.addEventListener('click',()=>{
    const list = listIdEmployeeChoice.length===0 ? listIdEmployee : listIdEmployeeChoice;
    from = filterFromDate.value === '' ? new Date(-1) : new Date(filterFromDate.value);
    to = filterToDate.value  === '' ? new Date() : new Date(filterToDate.value);
    if(from > to){
      swal("Something wrong!", {
        icon: "error",
      })
    }else{
      findListTimeTrackingByFilter(list, from, to, limitViewAll);
    }
})

function convertHour(time) {
  const currentDate = new Date();

  const [hours, minutes] = time.split(':');
  currentDate.setHours(hours);
  currentDate.setMinutes(minutes);
  const hour = String(currentDate.getHours()).padStart(2, '0');
  const minute = String(currentDate.getMinutes()).padStart(2, '0');
  const seconds = String(currentDate.getSeconds()).padStart(2, '0');

  return `${hour}:${minute}:${seconds}`;
}


function convertDate(time) {
  const month = String(time.getMonth() + 1).padStart(2, "0"); // Month start from 0 so add 1
  const day = String(time.getDate()).padStart(2, "0");
  const year = time.getFullYear();

  const formattedDate = `${month}/${day}/${year}`;
  return formattedDate;
}
