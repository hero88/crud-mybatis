import { useEffect, useState } from "react";
import Chart from "chart.js/auto";
import "chartjs-adapter-date-fns";
import format from "date-fns/format";
import { getChartDetailCoin, getMarketCapCoins } from "@/services/CoinAPI";
import { useParams } from "react-router-dom";
import { endOfMonth } from "date-fns";
import CoinDetailChart from "@/components/shared/CoinDetailChart";

// Helper function to format numbers in K format
const formatThousands = (value) =>
  Intl.NumberFormat("en-US", {
    maximumSignificantDigits: 3,
    notation: "compact",
  }).format(value);

const CoinDetail = () => {
  const param = useParams();
  const [currentMarketCoin, setCurrentMarketCoin] = useState();
  const [chartCoinData, setChartCoinData] = useState([]);

  const findMarketCoin = async () => {
    try {
      const { data: response } = await getMarketCapCoins();
      setCurrentMarketCoin(
        response.data.cryptoCurrencyList.find(
          (item) => item.id === parseInt(param.coinMarketId)
        )
      );
    } catch (error) {
      console.error(error);
    }
  };

  const hanldeLoadChartData = async () => {
    const { data: coinDetailResponse } = await getChartDetailCoin(
      parseInt(param.coinMarketId),
      "1676792555~1708332976"
    );
    setChartCoinData(coinDetailResponse.data);
    console.log(coinDetailResponse.data);
  };

  useEffect(() => {
    findMarketCoin();
    hanldeLoadChartData();

    // const callGetAllCoinsApiEvery30s = setInterval(() => {
    //   hanldeLoadChartData();
    //   findMarketCoin();
    // }, 1000);

    // return () => clearInterval(callGetAllCoinsApiEvery30s);
  }, []);

  return (
    <section className="flex justify-center">
      <div className="p-4 w-[390px] border-r-[1px]">
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
              <p className="text-black font-medium">
                ${currentMarketCoin?.quotes[0]?.marketCap}
              </p>
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
                      ${currentMarketCoin?.quotes[0]?.price.toFixed(3)}
                    </div>
                    {/* <div className="text-sm font-medium text-green-500">
                      +49%
                    </div> */}
                  </div>
                  <div className="text-sm text-gray-500">Price</div>
                </div>
                <div
                  className="hidden md:block w-px h-8 bg-gray-200 mr-5"
                  aria-hidden="true"
                ></div>
              </div>
            </div>
          </div>
          <div className="flex-grow">
            <CoinDetailChart chartCoinData={chartCoinData} />
          </div>
        </div>
      </div>
    </section>
  );
};

export default CoinDetail;
