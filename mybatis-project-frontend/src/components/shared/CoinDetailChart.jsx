import Chart from "chart.js/auto";
import "chartjs-adapter-date-fns";
import { endOfMonth } from "date-fns";
import format from "date-fns/format";
import { useEffect } from "react";
import { FAKECHARTDATA } from "@/assets/FakeChartData";

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

const testData = {
  1706572800: 310.7080044517561,
  1706832000: 300.1639106166709,
  1707177600: 300.8583628383969,
  1707436800: 318.923358400946,
  1707782400: 327.9323988798823,
  1708041600: 354.68757214865866,
};

const CoinDetailChart = ({ chartCoinData }) => {
  useEffect(() => {
    const ctx = document.getElementById("analytics-card-01");
    const timestamps = FAKECHARTDATA.map((item) => parseInt(item.timestamp));
    const values = FAKECHARTDATA.map((item) => item.value);

    // const timestamps = Object.keys(FAKECHARTDATA.timestamp).map(Number);
    // const values = Object.values(FAKECHARTDATA.value);

    const dates = timestamps.map((timestamp) =>
      format(new Date(timestamp * 1000), "dd-MM-yyyy")
    );

    console.log(dates);
    console.log(values);

    // const processedData = groupByMonth(FAKECHARTDATA);

    // console.log(processedData);

    // const timestamps = processedData.map((item) => item.date.getTime() / 1000);
    // const values = processedData.map(
    //   (item) => item.values[item.values.length - 1]
    // );

    // const dates = timestamps.map((timestamp) =>
    //   format(new Date(timestamp * 1000), "dd-MM-yyyy")
    // );

    // console.log(dates);
    // console.log(values);

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
    <>
      <canvas id="analytics-card-01" width="800" height="300"></canvas>
    </>
  );
};

export default CoinDetailChart;
