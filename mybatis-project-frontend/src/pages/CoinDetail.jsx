import { useEffect, useState } from "react";
import { getChartDetailCoin, getMarketCapCoins } from "@/services/CoinAPI";
import { useParams } from "react-router-dom";
import CoinDetailChart from "@/components/shared/CoinDetailChart";

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
  };

  useEffect(() => {
    findMarketCoin();
    hanldeLoadChartData();

    const callGetAllCoinsApiEvery90s = setInterval(() => {
      hanldeLoadChartData();
      findMarketCoin();
    }, 90000);

    return () => clearInterval(callGetAllCoinsApiEvery90s);
  }, []);

  return (
    <section className="flex justify-center mt-4">
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
              $
              {currentMarketCoin?.quotes[0]?.price.toLocaleString("en-US", {
                maximumFractionDigits: 3,
              })}
            </p>{" "}
            <p className="font-semibold">{currentMarketCoin?.change}% (1d)</p>
          </div>
          <div className="mt-4 space-y-4">
            <div className="text-gray-500 flex justify-between items-center">
              <p className="font-semibold text-sm">Market Cap</p>{" "}
              <p className="text-black font-medium">
                $
                {currentMarketCoin?.quotes[0]?.marketCap.toLocaleString(
                  "en-US",
                  {
                    maximumFractionDigits: 3,
                  }
                )}
              </p>
            </div>
            <div className="text-gray-500 flex justify-between items-center">
              <p className="font-semibold text-sm">Volume (24h)</p>{" "}
              <p className="text-black font-medium">
                $
                {currentMarketCoin?.quotes[0]?.volume24h.toLocaleString(
                  "en-US",
                  {
                    maximumFractionDigits: 3,
                  }
                )}
              </p>
            </div>
            <div className="text-gray-500 flex justify-between items-center">
              <p className="font-semibold text-sm">Circulating Supply</p>
              <p className="text-black font-medium">
                {currentMarketCoin?.circulatingSupply.toLocaleString("en-US", {
                  maximumFractionDigits: 3,
                })}{" "}
                {currentMarketCoin?.symbol}
              </p>
            </div>
            <div className="text-gray-500 flex justify-between items-center">
              <p className="font-semibold text-sm">Total Supply</p>
              <p className="text-black font-medium">
                {currentMarketCoin?.totalSupply.toLocaleString("en-US", {
                  maximumFractionDigits: 3,
                })}{" "}
                {currentMarketCoin?.symbol}
              </p>
            </div>
            <div className="text-gray-500 flex justify-between items-center">
              <p className="font-semibold text-sm">Max Supply:</p>{" "}
              <p className="text-black font-medium">
                {currentMarketCoin?.maxSupply?.toLocaleString("en-US", {
                  maximumFractionDigits: 3,
                }) || "Unnkown"}{" "}
                {currentMarketCoin?.symbol}
              </p>
            </div>
            <div className="text-gray-500 flex justify-between items-center">
              <p className="font-semibold text-sm">Fully Diluted Market Cap:</p>
              <p className="text-black font-medium">
                $
                {currentMarketCoin?.quotes[0]?.fullyDilluttedMarketCap.toLocaleString(
                  "en-US",
                  {
                    maximumFractionDigits: 3,
                  }
                )}
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
                      $
                      {currentMarketCoin?.quotes[0]?.price.toLocaleString(
                        "en-US",
                        {
                          maximumFractionDigits: 3,
                        }
                      )}
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
