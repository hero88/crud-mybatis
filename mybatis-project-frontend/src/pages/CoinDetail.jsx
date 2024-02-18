import { useEffect } from "react";
import Chart from "chart.js/auto"; // Import Chart.js
import "chartjs-adapter-date-fns"; // Import Chart.js adapter for date-fns
import format from "date-fns/format"; // Import date-fns format function
import "tailwindcss/tailwind.css"; // Import Tailwind CSS

// Helper function to format numbers in K format
const formatThousands = (value) =>
  Intl.NumberFormat("en-US", {
    maximumSignificantDigits: 3,
    notation: "compact",
  }).format(value);

const btcData = {
  name: "Bitcoin",
  symbol: "BTC",
  price: 51593.61,
  change: -0.76,
  marketCap: 1012829835557,
  volume: 21396355264,
  circulatingSupply: 18930818,
  totalSupply: 19930818,
  maxSupply: 21000000,
  fullyDilutedMarketCap: 1083485131543,
};

const CoinDetail = () => {
  useEffect(() => {
    const ctx = document.getElementById("analytics-card-01");

    // Chart.js data
    const data = {
      labels: [
        "12-01-2020",
        "01-01-2021",
        "02-01-2021",
        "03-01-2021",
        "04-01-2021",
        "05-01-2021",
        "06-01-2021",
        "07-01-2021",
        "08-01-2021",
        "09-01-2021",
        "10-01-2021",
        "11-01-2021",
        "12-01-2021",
        "01-01-2022",
        "02-01-2022",
        "03-01-2022",
        "04-01-2022",
        "05-01-2022",
        "06-01-2022",
        "07-01-2022",
        "08-01-2022",
        "09-01-2022",
        "10-01-2022",
        "11-01-2022",
        "12-01-2022",
        "01-01-2023",
      ],
      datasets: [
        // Indigo line
        {
          label: "Current",
          data: [
            5000, 8700, 7500, 12000, 11000, 9500, 10500, 10000, 15000, 9000,
            10000, 7000, 22000, 7200, 9800, 9000, 10000, 8000, 15000, 12000,
            11000, 13000, 11000, 15000, 17000, 18000,
          ],
          fill: true,
          backgroundColor: "rgba(59, 130, 246, 0.08)",
          borderColor: "rgb(99, 102, 241)",
          borderWidth: 2,
          tension: 0,
          pointRadius: 0,
          pointHoverRadius: 3,
          pointBackgroundColor: "rgb(99, 102, 241)",
        },
        // Gray line
      ],
    };

    // Chart.js options
    const options = {
      layout: {
        padding: 20,
      },
      scales: {
        y: {
          beginAtZero: true,
          grid: {
            drawBorder: false,
          },
          ticks: {
            callback: (value) => formatThousands(value),
          },
        },
        x: {
          type: "time",
          time: {
            parser: "MM-dd-yyyy",
            unit: "month",
            displayFormats: {
              month: "MMM YY",
            },
          },
          grid: {
            display: false,
            drawBorder: false,
          },
          ticks: {
            autoSkipPadding: 48,
            maxRotation: 0,
            callback: (value) => format(new Date(value), "MMM yy"),
          },
        },
      },
      plugins: {
        legend: {
          display: false,
        },
        tooltip: {
          callbacks: {
            title: () => false, // Disable tooltip title
            label: (context) => formatThousands(context.parsed.y),
          },
        },
      },
      interaction: {
        intersect: false,
        mode: "nearest",
      },
      maintainAspectRatio: false,
    };

    // Create Chart.js instance
    const chart = new Chart(ctx, {
      type: "line",
      data: data,
      options: options,
    });

    return () => {
      // Clean up Chart.js instance
      chart.destroy();
    };
  }, []); // Run useEffect only once on component mount

  return (
    <section className="flex justify-center antialiased bg-gray-100 text-gray-600">
      <div className="p-4">
        <div className="p-6 bg-white shadow-md flex flex-col space-y-2">
          <div className=" text-black flex items-center space-x-1">
            <p className="text-xl font-medium">{btcData.name}</p>
            <p className="text-gray-500 font-normal text-sm">
              {btcData.symbol}
            </p>
          </div>
          <div className="text-black flex space-x-2 items-center">
            <p className="font-bold text-3xl">
              ${btcData.price.toLocaleString()}
            </p>{" "}
            <p className="font-semibold">{btcData.change}% (1d)</p>
          </div>
          <div className="text-gray-500 flex justify-between items-center">
            <p>Market Cap:</p> <p>${btcData.marketCap.toLocaleString()}</p>
          </div>
          <div className="text-gray-500 flex justify-between items-center">
            <p>Volume (24h):</p> <p>${btcData.volume.toLocaleString()}</p>
          </div>
          <div className="text-gray-500 flex justify-between items-center">
            <p>Circulating Supply:</p>
            <p>
              {btcData.circulatingSupply.toLocaleString()}
              {btcData.symbol}
            </p>
          </div>
          <div className="text-gray-500 flex justify-between items-center">
            <p>Total Supply:</p>
            <p>
              {btcData.totalSupply.toLocaleString()}
              {btcData.symbol}
            </p>
          </div>
          <div className="text-gray-500 flex justify-between items-center">
            <p>Max Supply:</p>{" "}
            <p>
              {btcData.maxSupply.toLocaleString()} {btcData.symbol}
            </p>
          </div>
          <div className="text-gray-500 flex justify-between items-center">
            <p>Fully Diluted Market Cap:</p>
            <p>${btcData.fullyDilutedMarketCap.toLocaleString()}</p>
          </div>
        </div>
      </div>
      <div className="max-w-3xl p-4 px-6">
        <div className="flex flex-col col-span-full xl:col-span-8 bg-white shadow-lg rounded-sm border border-gray-200">
          <header className="px-5 py-4 border-b border-gray-100 flex items-center">
            <h2 className="font-semibold text-gray-800">Analytics</h2>
          </header>
          <div className="px-5 py-1">
            <div className="flex flex-wrap">
              <div className="flex items-center py-2">
                <div className="mr-5">
                  <div className="flex items-center">
                    <div className="text-3xl font-bold text-gray-800 mr-2">
                      24.7K
                    </div>
                    <div className="text-sm font-medium text-green-500">
                      +49%
                    </div>
                  </div>
                  <div className="text-sm text-gray-500">Unique Visitors</div>
                </div>
                <div
                  className="hidden md:block w-px h-8 bg-gray-200 mr-5"
                  aria-hidden="true"
                ></div>
              </div>
            </div>
          </div>
          <div className="flex-grow">
            <canvas id="analytics-card-01" width="800" height="300"></canvas>
          </div>
        </div>
      </div>
    </section>
  );
};

export default CoinDetail;
