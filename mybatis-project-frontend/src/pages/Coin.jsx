import PaginationCustom from "@/components/shared/PaginationCustom";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { useEffect, useState } from "react";
import AddCoinDialog from "./dialogs/AddCoinDialog";
import {
  deleteCoinById,
  getAllCoins,
  getMarketCapCoins,
} from "@/services/CoinAPI";
import { Button } from "@/components/ui/button";
import { Pencil, Trash } from "lucide-react";
import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
} from "@/components/ui/alert-dialog";
import axios from "axios";
import NumberCounter from "@/components/shared/NumberCounter";

function Coin() {
  const [user, setUser] = useState({});

  const [userCoinList, setUserCoinList] = useState([]);
  const [marketCoinList, setMarketCoinList] = useState();

  useEffect(() => {
    setUser(JSON.parse(localStorage.getItem("profile")));
  }, []);

  const getAllUserCoins = async () => {
    try {
      const { data: response } = await getAllCoins();

      setUserCoinList(response.data);
    } catch (error) {
      console.error("Error when calling table data:", error);
    }
  };

  const getMarketCoins = async () => {
    try {
      const { data: response } = await getMarketCapCoins();
      setMarketCoinList(response.data.cryptoCurrencyList);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    getAllUserCoins();
    getMarketCoins();

    const callGetAllCoinsApiEvery30s = setInterval(() => {
      getAllUserCoins();
      getMarketCoins();
    }, 30000);

    return () => clearInterval(callGetAllCoinsApiEvery30s);
  }, []);

  // const handleUpdateCoin = async () => {};

  const handleDeleteCoin = async (coinId) => {
    // const { data: response } = await deleteCoinById(coinId);
    const { data: response } = await axios.delete(
      `http://localhost:5555/api/coin/deleteCoinById/1`
    );

    getAllUserCoins();
    console.log(response);
  };

  return (
    <>
      <div className="centerSide w-8/12 px-8">
        <h2 className="pb-4 text-2xl font-bold">Your Assets</h2>
        <hr className="pb-8" />
        <AddCoinDialog
          type="ADD"
          user={user}
          userCoins={userCoinList}
          marketCoins={marketCoinList}
          recallCoins={getAllUserCoins}
        />
        {/* list coin of user */}
        <div className="min-h-svh mt-2">
          <Table className="px-12">
            <TableHeader>
              <TableRow>
                <TableHead className="w-8"></TableHead>
                <TableHead className="text-black p-0 min-w-6 font-bold text-[12px]">
                  #
                </TableHead>
                <TableHead className="text-black font-bold text-[12px]">
                  Name
                </TableHead>
                <TableHead className="text-black font-bold text-[12px]">
                  Market pair count
                </TableHead>
                <TableHead className="text-black font-bold text-[12px]">
                  Quantity
                </TableHead>
                <TableHead className=""></TableHead>
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
                    {
                      marketCoinList?.find((item) => item.name === coin.name)
                        ?.marketPairCount
                    }
                  </TableCell>
                  <TableCell className="font-semibold">
                    <NumberCounter coin={coin} />
                  </TableCell>
                  <TableCell className="flex items-center space-x-1">
                    {/* <Button className="w-12 h-12">
                      <Pencil width={20} height={20} />
                    </Button> */}
                    <AlertDialog>
                      <AlertDialogTrigger asChild>
                        <Button className="w-12 h-12">
                          <Trash width={20} height={20} />
                        </Button>
                      </AlertDialogTrigger>
                      <AlertDialogContent>
                        <AlertDialogHeader>
                          <AlertDialogTitle>
                            Are you absolutely sure?
                          </AlertDialogTitle>
                          <AlertDialogDescription>
                            This action cannot be undone. This coin will be
                            removed.
                          </AlertDialogDescription>
                        </AlertDialogHeader>
                        <AlertDialogFooter>
                          <AlertDialogCancel>Cancel</AlertDialogCancel>
                          <AlertDialogAction
                            onClick={() => handleDeleteCoin(coin.id)}
                          >
                            Continue
                          </AlertDialogAction>
                        </AlertDialogFooter>
                      </AlertDialogContent>
                    </AlertDialog>
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
