import { instance } from "../config/config.js";
const baseURL = instance.defaults.baseURL;
var api = baseURL + "v1/coins";

var limit = 10;
var currentPage = 1;
var start = 1;
var item = 1;
var totalcount = 0;

const getCoin = async (start, limit) => {
  try {
    const response = await axios.get(
      api + `/top-coin?start=${start}&limit=${limit}`
    );
    const data = response.data.data;
    const tableBody = document
      .getElementById("table-coin")
      .getElementsByTagName("tbody")[0];
    tableBody.innerHTML = "";
    data.cryptoCurrencyList.forEach((coin, index) => {
      const row = tableBody.insertRow();
      const cell1 = row.insertCell(0);
      const cell2 = row.insertCell(1);
      const cell3 = row.insertCell(2);
      const cell4 = row.insertCell(3);
      const cell5 = row.insertCell(4);
      const cell6 = row.insertCell(5);

      cell1.textContent = index + 1;
      if (coin.detail && coin.detail.logo) {
        cell2.innerHTML = `<div><img  style="height: 1.7rem;" src="${coin.detail.logo}" alt=""></div>`;
      } else {
        cell2.textContent = "-";
      }
      if (coin.name) {
        cell3.innerHTML = `<div><img  style="height: 1.7rem;" src="${coin.detail.logo}" alt=""></div>`;
        cell3.innerHTML = `<div style="font-weight: 500;">${coin.name}</div>
                               <div style="font-size: 15px; color: rgb(142, 142, 142);">${coin.symbol}</div>`;
      } else {
        cell3.textContent = "-";
      }
      cell4.textContent = coin.id || "-";

      if (coin.detail.contract_address.length > 0) {
        cell5.innerHTML = `
            <div class="d-flex">
                <div>
                    <div>
                        <img style="height: 1.7rem;" src="https://s2.coinmarketcap.com/static/img/coins/64x64/${coin.detail.contract_address[0].platform.coin.id}.png" alt="">
                        <span class="fs-6"><b>${coin.detail.contract_address[0].platform.coin.name}</b></span>
                    </div>
                    <div class="">
                        <span class="mx-2">${coin.detail.contract_address[0].contract_address}</span>
                    </div>
                </div>
              
            </div>`;
        cell6.innerHTML = `  <div class="dropdown">
            <button style="padding: 5px;" class=" mx-2 btn btn-outline-info dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                more
            </button>
            <ul class="dropdown-menu">
                ${coin.detail.contract_address
                  .map(
                    (coins) => `
                    <li onclick="copyText(this)">
                        <a class="dropdown-item">
                            <div class="d-flex">
                                <div  style="font-weight: 500; margin-left: 5px;">
                                    <img style="height: 1.4rem;" src="https://s2.coinmarketcap.com/static/img/coins/64x64/${coins.platform.coin.id}.png" alt="">
                                    <span>${coins.platform.name}</span>
                                </div>
                               
                                <div  class="d-inline-block text-truncate" style="width: 150px; margin-left: 5px;">
                                    <span>${coins.contract_address}</span>
                                </div>
                                <div class="mx-2">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-copy" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd" d="M4 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2zm2-1a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM2 5a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1v-1h1v1a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h1v1z"/>
                                    </svg>
                                </div>
                            </div>
                        </a>
                    </li>`
                  )
                  .join("")}
            </ul>
        </div>`;
      } else {
        cell5.textContent = coin.contract_address || "";
      }
    });
    totalcount = data.totalCount;
    rebuildPagination(start,data.totalCount);
  } catch (error) {
    console.log(error.message);
  }
};

window.copyText = function (e) {
  var divElement = document.querySelector(".d-inline-block.text-truncate");
  var spanElement = divElement.querySelector("span");
  navigator.clipboard
    .writeText(spanElement.textContent)
    .then(function () {
      alert("The element is copied");
    })
    .catch(function (err) {
      console.error("Error copying to clipboard: ", err);
    });
};


function rebuildPagination(currentPage, totalcount) {

  var HTML = "";

  // If there are only 6 pages or less in total, just display all pages without "..."
  if (totalcount <= 6) {
    for (i = 1; i <= totalcount; i++) {
      HTML += addButton(i, currentPage);
    }
  }
  // ELSE display with ...
  else {

    // Always print first page button
    HTML += addButton("1", currentPage);

    // Print "..." if currentPage is > 3
    if (currentPage > 3) {
      HTML += "...";
    }

    // special case where last page is selected...
    // Without this special case, only 2 buttons would be shown after ... if you were on last page
    if (currentPage == totalcount) {
      HTML += addButton(currentPage - 2, currentPage);
    }


    // Print previous number button if currentPage > 2
    if (currentPage > 2) {
      HTML += addButton(currentPage - 1, currentPage);
    }

    //Print current page number button as long as it not the first or last page
    if (currentPage != 1 && currentPage != totalcount) {
      HTML += addButton(currentPage, currentPage);
    }

    //print next page number button if currentPage < lastPage - 1
    if (currentPage < totalcount - 1) {
      HTML += addButton(currentPage + 1, currentPage);
    }

    // special case where first page is selected...
    // Without this special case, only 2 buttons would be shown before ... if you were on first page    
    if (currentPage == 1) {
      HTML += addButton(currentPage + 2, currentPage);
    }

    //print "..." if currentPage is < lastPage -2
    if (currentPage < totalcount - 2) {
      HTML += "...";
    }

    //Always print last page button if there is more than 1 page
    if (totalcount > 1) {
      HTML += addButton(totalcount, currentPage);
    }
  }
  //
  // HTML variable contains all the above buttons, 
  //
  document.getElementById("page").innerHTML = HTML;
}
function addButton(number, currentPage) {
  var HTML = "<a class='page-link'";

 
  if (number == currentPage) {
    HTML += " style='background-color:#6495ED' ";
  }

  HTML += " onclick=\"handlevent(" + number + ")\">";

  HTML += number;

  HTML += "</a>";

  return HTML;
}



window.handlevent = function (value) {
    start = value;
    rebuildPagination(start, totalcount);
    getCoin(start,limit);
}

window.goToPage = function (pageNumber) {

    currentPage = pageNumber;
    start = (pageNumber - 1) * limit;
  
  getCoin(start, limit);
};


getCoin(item,limit);
