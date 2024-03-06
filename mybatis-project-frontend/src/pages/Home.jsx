import { useState, useEffect } from "react";
// import { getAllCoins } from "../services/ApiCoin";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

import { PiStarThin } from "react-icons/pi";
import { FaCaretDown, FaCaretUp } from "react-icons/fa";
import PaginationCustom from "@/components/shared/PaginationCustom";
import { getMarketCapCoins } from "@/services/CoinAPI";

function Home() {
  const [marketCoinList, setMarketCoinList] = useState([]);

  const callGetAllCoinsApi = async () => {
    try {
      const { data: response } = await getMarketCapCoins();
      setMarketCoinList(response.data.cryptoCurrencyList);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    callGetAllCoinsApi();

    const callGetAllCoinsApiEvery30s = setInterval(() => {
      callGetAllCoinsApi();
    }, 30000);

    return () => clearInterval(callGetAllCoinsApiEvery30s);
  }, []);

  const formatNumber = (numericData, fractionDigits) => {
    return numericData.toLocaleString("en-US", {
      minimumFractionDigits: fractionDigits,
      maximumFractionDigits: fractionDigits,
    });
  };

  const renderChange = (numericData, fractionDigits) => {
    const formattedNumber = Math.abs(numericData).toLocaleString("en-US", {
      minimumFractionDigits: fractionDigits,
      maximumFractionDigits: fractionDigits,
    });

    let textColor = "";
    let icon;

    if (numericData >= 0) {
      textColor = "text-green-400";
      icon = <FaCaretUp />;
    } else if (numericData < 0) {
      textColor = "text-red-400";
      icon = <FaCaretDown />;
    }

    return (
      <span className={`${textColor} flex items-center justify-end`}>
        {icon} {formattedNumber}%
      </span>
    );
  };

  return (
    <div>
      <div className="min-h-svh px-8 pt-7">
        <div className="pb-6">
          <p className="font-bold text-xl">
            Today&apos;s Cryptocurrency Prices by Market Cap
          </p>{" "}
          <div className="text-[15px] text-gray-600">
            The global crypto market cap is{" "}
            <span className="font-medium">$1.92T</span>, a 2.41% increase over
            the last day. <p className="underline">Read More</p>
          </div>
        </div>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead className="w-8 text-black"></TableHead>
              <TableHead className="p-0 min-w-6 text-black text-[12px] font-bold">
                #
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Name
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold text-end">
                Price
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold text-end">
                1h %
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold text-end">
                24h %
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold text-end">
                7d %
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold text-end">
                Market Cap
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold text-end">
                Volume(24h)
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold text-end">
                Circulating Supply
              </TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {marketCoinList?.map((item, index) => (
              <TableRow key={index}>
                <TableCell className="font-semibold">
                  <PiStarThin className="font-semibold text-gray-500" />
                </TableCell>
                <TableCell className="p-0 min-w-6 text-gray-500 font-semibold">
                  {index + 1}
                </TableCell>
                <TableCell>
                  <div className="flex items-center">
                    <div className="me-2">
                      <img
                        src={`https://s2.coinmarketcap.com/static/img/coins/32x32/${item.id}.png`}
                        alt={item.name}
                        className="coin-logo"
                        width={25}
                        height={25}
                      />
                    </div>
                    <div className="items-center">
                      <span className="me-2 font-semibold">{item.name}</span>
                      <span className="font-semibold text-gray-500">
                        {item.symbol}
                      </span>
                    </div>
                  </div>
                </TableCell>
                <TableCell className="font-semibold text-end">
                  {"$" + formatNumber(item.quotes[0].price)}
                </TableCell>
                <TableCell className="font-semibold text-end">
                  {renderChange(item.quotes[0].percentChange1h)}
                </TableCell>
                <TableCell className="font-semibold text-end">
                  {renderChange(item.quotes[0].percentChange24h)}
                </TableCell>
                <TableCell className="font-semibold text-end">
                  {renderChange(item.quotes[0].percentChange7d)}
                </TableCell>
                <TableCell className="font-semibold text-end">
                  {"$" + formatNumber(item.quotes[0].marketCap)}
                </TableCell>
                <TableCell className="font-semibold text-end">
                  {"$" + formatNumber(item.quotes[0].volume24h)}
                </TableCell>
                <TableCell className="font-semibold text-end">
                  {formatNumber(parseFloat(item.circulatingSupply), 0)}{" "}
                  {item.symbol}
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>
      <PaginationCustom
        currentPage={{ id: 1, serial: 1 }}
        listPage={[
          { id: 1, serial: 1 },
          { id: 2, serial: 2 },
          { id: 3, serial: 3 },
        ]}
      />
    </div>
  );
}

export default Home;
