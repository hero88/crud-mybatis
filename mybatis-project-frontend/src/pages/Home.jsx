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
import { Button } from "@/components/ui/button";
import { Link } from "react-router-dom";
import PaginationCustom from "@/components/shared/PaginationCustom";

const fakeData = {
  data: {
    cryptoCurrencyList: [
      {
        id: 1,
        name: "Bitcoin",
        symbol: "BTC",
        slug: "bitcoin",
        cmcRank: 1,
        marketPairCount: 10802,
        circulatingSupply: 19613681,
        selfReportedCirculatingSupply: 0,
        totalSupply: 19613681,
        maxSupply: 21000000,
        ath: 68789.62593892214,
        atl: 0.04864654,
        high24h: 43838.946844106074,
        low24h: 42691.06527138428,
        isActive: 1,
        lastUpdated: "2024-01-31T03:21:00.000Z",
        dateAdded: "2010-07-13T00:00:00.000Z",
        quotes: [
          {
            name: "USD",
            price: 42908.72592128142,
            volume24h: 23196978931.11611,
            volume7d: 156145021761.53314,
            volume30d: 761154014236.3904,
            marketCap: 841598062336.4448,
            selfReportedMarketCap: 0,
            percentChange1h: 0.4723818,
            percentChange24h: -1.48161478,
            percentChange7d: 7.94910944,
            lastUpdated: "2024-01-31T03:21:00.000Z",
            percentChange30d: 1.04424785,
            percentChange60d: 10.69923164,
            percentChange90d: 19.75202018,
            fullyDilluttedMarketCap: 901083244346.91,
            marketCapByTotalSupply: 841598062336.4448,
            dominance: 50.9483,
            turnover: 0.02756301,
            yTableCellPriceChangePercentage: -2.8496,
            percentChange1y: 87.69554836,
          },
        ],
        isAudited: false,
        badges: [1],
      },
    ],
    totalCount: 8815,
  },
  status: {
    timestamp: "2024-01-31T03:22:19.945Z",
    error_code: 0,
    error_message: "SUCCESS",
    elapsed: 21,
    credit_count: 0,
  },
};

function Home() {
  const [data, setData] = useState([]);

  useEffect(() => {
    const callGetAllCoinsApi = async () => {
      try {
        // const response = await getAllCoins();
        // const newData = response.data.data.cryptoCurrencyList;
        setData(fakeData.data.cryptoCurrencyList);
      } catch (error) {
        console.error("Error when calling table data:", error);
      }
    };

    callGetAllCoinsApi();

    const callGetAllCoinsApiEvery30s = setInterval(() => {
      callGetAllCoinsApi();
    }, 30000);

    return () => clearInterval(callGetAllCoinsApiEvery30s);
  }, []);

  const formatNumber = (numericData, fractionDigits) => {
    return (
      "$" +
      numericData.toLocaleString("en-US", {
        minimumFractionDigits: fractionDigits,
        maximumFractionDigits: fractionDigits,
      })
    );
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
      <span className={`${textColor} flex items-center`}>
        {icon} {formattedNumber}%
      </span>
    );
  };

  return (
    <div>
      <div className="mx-6 min-h-svh">
      <Table className="px-12">
        <TableHeader>
          <TableRow>
            <TableHead className="w-8"></TableHead>
            <TableHead className="p-0 min-w-6">#</TableHead>
            <TableHead>Name</TableHead>
            <TableHead>Price</TableHead>
            <TableHead>1h %</TableHead>
            <TableHead>24h %</TableHead>
            <TableHead>7d %</TableHead>
            <TableHead>Market Cap</TableHead>
            <TableHead>Volume(24h)</TableHead>
            <TableHead>Circulating Supply</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {data.map((item, index) => (
            <TableRow key={index}>
              <TableCell>
                <PiStarThin />
              </TableCell>
              <TableCell className="p-0 min-w-6">{index + 1}</TableCell>
              <TableCell>
                <div className="flex items-center">
                  <div className="me-2">
                    <img
                      src={`https://s2.coinmarketcap.com/static/img/coins/32x32/${item.id}.png`}
                      alt={item.name}
                      className="coin-logo"
                      width={32}
                      height={32}
                    />
                  </div>
                  <div className="items-center">
                    <span className="me-2">{item.name}</span>
                    <span className="">{item.symbol}</span>
                  </div>
                </div>
              </TableCell>
              <TableCell>{formatNumber(item.quotes[0].price)}</TableCell>
              <TableCell>
                {renderChange(item.quotes[0].percentChange1h)}
              </TableCell>
              <TableCell>
                {renderChange(item.quotes[0].percentChange24h)}
              </TableCell>
              <TableCell>
                {renderChange(item.quotes[0].percentChange7d)}
              </TableCell>
              <TableCell>{formatNumber(item.quotes[0].marketCap)}</TableCell>
              <TableCell>{formatNumber(item.quotes[0].volume24h)}</TableCell>
              <TableCell>
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
