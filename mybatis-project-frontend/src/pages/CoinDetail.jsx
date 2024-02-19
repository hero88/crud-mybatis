import { useEffect, useState } from "react";
import Chart from "chart.js/auto"; // Import Chart.js
import "chartjs-adapter-date-fns"; // Import Chart.js adapter for date-fns
import format from "date-fns/format"; // Import date-fns format function
import "tailwindcss/tailwind.css"; // Import Tailwind CSS
import { getMarketCapCoins } from "@/services/CoinAPI";
import { useParams } from "react-router-dom";

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

const coinChartData = {
  points: {
    1609459200: 37.37457234,
    1609545600: 37.91710592,
    1609632000: 38.25372654,
    1609718400: 1,
  },
};

const CoinDetail = () => {
  const param = useParams();
  const [currentMarketCoin, setCurrentMarketCoin] = useState();

  const findMarketCoin = async () => {
    try {
      const { data: response } = await getMarketCapCoins();
      console.log(
        response.data.cryptoCurrencyList.find(
          (item) => item.id === parseInt(param.coinMarketId)
        )
      );
      setCurrentMarketCoin(
        response.data.cryptoCurrencyList.find(
          (item) => item.id === parseInt(param.coinMarketId)
        )
      );
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    findMarketCoin();
  }, []);

  useEffect(() => {
    const ctx = document.getElementById("analytics-card-01");

    const timestamps = Object.keys(coinChartData.points).map(Number);
    const values = Object.values(coinChartData.points);

    const dates = timestamps.map((timestamp) =>
      format(new Date(timestamp * 1000), "dd-MM-yyyy")
    );

    console.log(values);
    console.log(dates);

    // Chart.js data
    const data = {
      labels: dates,
      datasets: [
        // Indigo line
        {
          label: "Current",
          data: values,
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

    console.log(data);

    return () => {
      // Clean up Chart.js instance
      chart.destroy();
    };
  }, []); // Run useEffect only once on component mount

  return (
    <section className="flex justify-center">
      <div className="p-4 w-[400px] border-r-[1px]">
        <div className=" bg-white  flex flex-col">
          <div className=" text-black flex items-center space-x-1">
            <div className="me-2">
              <img
                src={`https://s2.coinmarketcap.com/static/img/coins/32x32/${param.coinMarketId}.png`}
                alt={param.coinMarketId}
                className="coin-logo"
                width={32}
                height={32}
              />
            </div>
            <p className="text-xl font-medium">{currentMarketCoin?.name}</p>
            <p className="text-gray-500 font-normal text-sm">
              {currentMarketCoin?.symbol}
            </p>
          </div>
          <div className="text-black flex space-x-2 items-center mt-3">
            <p className="font-bold text-3xl">
              ${currentMarketCoin?.quotes[0]?.price.toFixed(3)}
            </p>{" "}
            <p className="font-semibold">{currentMarketCoin?.change}% (1d)</p>
          </div>
          <div className="mt-4 space-y-4">
            <div className="text-gray-500 flex justify-between items-center">
              <p className="font-semibold text-sm">Market Cap</p>{" "}
              <p className="text-black font-medium">${btcData.marketCap}</p>
            </div>
            <div className="text-gray-500 flex justify-between items-center">
              <p className="font-semibold text-sm">Volume (24h)</p>{" "}
              <p className="text-black font-medium">
                ${currentMarketCoin?.quotes[0]?.volume24h}
              </p>
            </div>
            <div className="text-gray-500 flex justify-between items-center">
              <p className="font-semibold text-sm">Circulating Supply</p>
              <p className="text-black font-medium">
                {currentMarketCoin?.circulatingSupply}{" "}
                {currentMarketCoin?.symbol}
              </p>
            </div>
            <div className="text-gray-500 flex justify-between items-center">
              <p className="font-semibold text-sm">Total Supply</p>
              <p className="text-black font-medium">
                {currentMarketCoin?.totalSupply} {currentMarketCoin?.symbol}
              </p>
            </div>
            <div className="text-gray-500 flex justify-between items-center">
              <p className="font-semibold text-sm">Max Supply:</p>{" "}
              <p className="text-black font-medium">
                {currentMarketCoin?.maxSupply || "Unnkown"}{" "}
                {currentMarketCoin?.symbol}
              </p>
            </div>
            <div className="text-gray-500 flex justify-between items-center">
              <p className="font-semibold text-sm">Fully Diluted Market Cap:</p>
              <p className="text-black font-medium">
                ${currentMarketCoin?.quotes[0]?.fullyDilluttedMarketCap}
              </p>
            </div>
          </div>
        </div>
      </div>
      <div className="p-4">
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
