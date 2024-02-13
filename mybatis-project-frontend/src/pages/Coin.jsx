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
import axios from "axios";

function Coin() {
  const [userCoinList, setUserCoinList] = useState([]);

  useEffect(() => {
    const callGetAllCoinsOfUserApi = async () => {
      try {
        const { data: response } = await axios.get(
          `http://localhost:5555/api/v1/coins/getCoinsByUserId?userId=1`
        );

        setUserCoinList(response.data);
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
    <>
      <div className="centerSide w-8/12 px-8">
        <h2 className="pb-4 text-2xl font-bold">Your Coins</h2>
        <hr className="pb-8" />
        <AddCoinDialog type="ADD" />
        {/* list coin of user */}
        <div className="min-h-svh mt-2">
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
              {userCoinList?.map((coin, index) => (
                <TableRow key={index}>
                  <TableCell></TableCell>
                  <TableCell className="p-0 min-w-6 text-gray-500 font-semibold">
                    {index + 1}
                  </TableCell>
                  <TableCell>
                    <div className="flex items-center">
                      {/* <div className="me-2">
                        <img
                          src={`https://s2.coinmarketcap.com/static/img/coins/32x32/${coin.coinId}.png`}
                          alt={coin.name}
                          className="coin-logo"
                          width={32}
                          height={32}
                        />
                      </div> */}
                      <div className="items-center">
                        <span className="me-2 font-semibold">{coin.name}</span>
                        <span className="font-semibold text-gray-500">
                          {coin.symbol}
                        </span>
                      </div>
                    </div>
                  </TableCell>
                  <TableCell className="font-semibold">
                    {coin.marketPairCount || "Empty"}
                  </TableCell>
                  <TableCell className="font-semibold">
                    {coin.quantity}
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
      <div className="rightSide md:w-0 xl:w-2/12"></div>
    </>
  );
}

export default Coin;
