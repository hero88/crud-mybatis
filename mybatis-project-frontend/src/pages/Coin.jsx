import PaginationCustom from "@/components/shared/PaginationCustom";
import { Button } from "@/components/ui/button";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { useEffect, useState } from "react";
import { PiStarThin } from "react-icons/pi";
import AddCoinDialog from "./dialogs/AddCoinDialog";

const fakeData = {
  data: {
    coinList: [
      {
        coinId: 1,
        name: "fake Coin",
        symbol: "BTC",
        marketPairCount: "1000",
        quantity: 10000,
        userId: 1,
      },
    ],
  },
};

function Coin() {
  const [data, setData] = useState([]);

  useEffect(() => {
    const callGetAllCoinsOfUserApi = async () => {
      try {
        // const response = await getAllCoins();
        // const newData = response.data.data.cryptoCurrencyList;
        setData(fakeData.data.coinList);
      } catch (error) {
        console.error("Error when calling table data:", error);
      }
    };

    callGetAllCoinsOfUserApi();

    const callGetAllCoinsApiEvery30s = setInterval(() => {
      callGetAllCoinsOfUserApi();
    }, 30000);

    return () => clearInterval(callGetAllCoinsApiEvery30s);
  }, []);

  return (
    <div className="centerSide w-7/12 px-8">
      <AddCoinDialog type="ADD" />
      {/* list coin of user */}
      <div className="min-h-svh">
        <Table className="px-12">
          <TableHeader>
            <TableRow>
              <TableHead className="w-8"></TableHead>
              <TableHead className="text-black p-0 min-w-6">#</TableHead>
              <TableHead className="text-black">Name</TableHead>
              <TableHead className="text-black">Market pair count</TableHead>
              <TableHead className="text-black">Quantity</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {data.map((coin, index) => (
              <TableRow key={index}>
                <TableCell>
                  <PiStarThin />
                </TableCell>
                <TableCell className="p-0 min-w-6">{index + 1}</TableCell>
                <TableCell>
                  <div className="flex items-center">
                    <div className="me-2">
                      <img
                        src={`https://s2.coinmarketcap.com/static/img/coins/32x32/${coin.coinId}.png`}
                        alt={coin.name}
                        className="coin-logo"
                        width={32}
                        height={32}
                      />
                    </div>
                    <div className="items-center">
                      <span className="me-2">{coin.name}</span>
                      <span className="">{coin.symbol}</span>
                    </div>
                  </div>
                </TableCell>
                <TableCell>{coin.marketPairCount}</TableCell>
                <TableCell>{coin.quantity}</TableCell>
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

export default Coin;
