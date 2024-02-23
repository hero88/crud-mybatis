import Chart from "chart.js/auto";
import "chartjs-adapter-date-fns";
import { endOfMonth } from "date-fns";
import format from "date-fns/format";
import { useEffect } from "react";

const formatThousands = (value) =>
  Intl.NumberFormat("en-US", {
    maximumSignificantDigits: 3,
    notation: "compact",
  }).format(value);

const groupByMonth = (data) => {
  const groupedData = {};

  data?.forEach((item) => {
    const timestamp = parseInt(item.timestamp);
    const date = new Date(timestamp * 1000);
    const endOfMonthDate = endOfMonth(date);
    const monthKey = format(endOfMonthDate, "yyyy-MM");

    if (!groupedData[monthKey]) {
      groupedData[monthKey] = {
        date: endOfMonthDate,
        values: [],
      };
    }

    groupedData[monthKey].values.push(item.value);
  });

  return Object.values(groupedData);
};

const CoinDetailChart = ({ chartCoinData }) => {
  useEffect(() => {
    const ctx = document.getElementById("analytics-card-01");
    const timestamps = chartCoinData.map((item) => parseInt(item.timestamp));
    const values = chartCoinData.map((item) => item.value);

    const dates = timestamps.map((timestamp) =>
      format(new Date(timestamp * 1000), "dd-MM-yyyy")
    );
    // const processedData = groupByMonth(chartCoinData);

    // const timestamps = processedData.map((item) => item.date.getTime() / 1000);
    // const values = processedData.map(
    //   (item) => item.values[item.values.length - 1]
    // );

    // const dates = timestamps.map((timestamp) =>
    //   format(new Date(timestamp * 1000), "dd-MM-yyyy")
    // );

    const data = {
      labels: dates,
      datasets: [
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
      ],
    };

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
            parser: "dd-MM-yyyy",
            unit: "month",
            min: new Date("12-01-2023"),
            displayFormats: {
              month: "MMM YYYY",
            },
          },
          grid: {
            display: false,
            drawBorder: false,
          },
          ticks: {
            autoSkipPadding: 48,
            maxRotation: 0,
            callback: (value) => format(new Date(value), "MMM yyyy"),
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

    const chart = new Chart(ctx, {
      type: "line",
      data: data,
      options: options,
    });

    return () => {
      chart.destroy();
    };
  }, [chartCoinData]);

  return (
    <>
      <canvas id="analytics-card-01" width="800" height="300"></canvas>
    </>
  );
};

export default CoinDetailChart;
