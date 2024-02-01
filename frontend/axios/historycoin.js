const baseURL = instance.defaults.baseURL;
const ctx = document.getElementById("myChart");
var id = new URLSearchParams(window.location.search).get("id");
axios
  .get(baseURL + "v1/history-coin", {
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
