import { instance } from "../config/config.js";
const baseURL = instance.defaults.baseURL;
const ctx = document.getElementById("myChart");
var id = new URLSearchParams(window.location.search).get("id");
axios
  .get(baseURL + "v1/coins/history-coin", {
    params: {
      id: id,
    },
  })
  .then(function (resp) {
    var timestamps = resp.data.timestamps;
    var months = resp.data.months;
    var prices = [];
    for (let i = 0; i < timestamps.length - 1; i++) {
      var dataPoint = resp.data.data.points[timestamps[i]];
      prices.push(dataPoint["c"][0]);
    }
    new Chart(ctx, {
      type: "line",
      data: {
        labels: months,
        datasets: [
          {
            label: "Price",
            data: prices,
            borderWidth: 1,
          },
        ],
      },
      options: {
        scales: {
          y: {
            beginAtZero: true,
          },
        },
      },
    });
  })
  .catch(function (error) {
    console.log(error);
  });

  axios.get(baseURL + "v1/coins/home", {
  })
  .then(function (resp) {
     var coinDetail = resp.data.data.data.cryptoCurrencyList.find(e => e.id == id);
     $('#coin-name').text(coinDetail.name);
     $('#coin-symbol').text(coinDetail.symbol);
     $('#coin-price').text("$"+Number(coinDetail.quotes[0].price).toLocaleString('en-US'));
     $('#coin-price-marketcap').text(Number(coinDetail.quotes[0].marketCap).toLocaleString('en-US'));
     $('#coin-price-volume').text(Number(coinDetail.quotes[0].volume24h).toLocaleString('en-US'));
     $('#coin-price-avg').text(Number(coinDetail.quotes[0].volume24h/coinDetail.quotes[0].marketCap).toLocaleString('en-US'));
     $('#coin-supply').text(Number(coinDetail.circulatingSupply).toLocaleString('en-US'));
     $('#coin-price-total-supply').text(Number(coinDetail.totalSupply).toLocaleString('en-US'));
     $('#coin-price-max-supply').text(Number(coinDetail.maxSupply).toLocaleString('en-US'));
     $('#coin-price-fully').text(Number(coinDetail.quotes[0].fullyDilluttedMarketCap).toLocaleString('en-US'));
  })
  .catch(function (error) {
    console.log(error);
  });