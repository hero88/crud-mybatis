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
  getCoinsByUserId,
  getMarketCapCoins,
} from "@/services/CoinAPI";
import { Button } from "@/components/ui/button";
import { Trash } from "lucide-react";
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
import NumberCounter from "@/components/shared/NumberCounter";
import { useToast } from "@/components/ui/use-toast";
import { ToastAction } from "@/components/ui/toast";
import { useNavigate } from "react-router-dom";

function Coin() {
  const navigate = useNavigate();
  const { toast } = useToast();
  const [user, setUser] = useState({});
  const [userCoinList, setUserCoinList] = useState([]);
  const [marketCoinList, setMarketCoinList] = useState([]);

  useEffect(() => {
    setUser(JSON.parse(localStorage.getItem("profile")));
  }, []);

  const getAllUserCoins = async () => {
    try {
      const initUser = JSON.parse(localStorage.getItem("profile"));

      const { data: response } = await getCoinsByUserId(initUser.id);

      console.log(response);

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
    const { data: response } = await deleteCoinById(coinId);

    if (response.code === 200) {
      toast({
        title: "Delete coin successfully.",
        description: "Coin list has been changed.",
        action: <ToastAction altText="Nice">Nice</ToastAction>,
      });
      getAllUserCoins();
    } else {
      toast({
        variant: "destructive",
        title: "Uh oh! Something went wrong.",
        description: "There was a problem with deleting.",
        action: <ToastAction altText="Try again">Try again</ToastAction>,
      });
    }

    console.log(response);
  };

  return (
    <>
      <div className="centerSide w-8/12 px-8">
        <h2 className="pb-4 text-4xl font-bold">Dashboard</h2>
        <hr className="pb-8" />
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
                    <div
                      className="flex items-center cursor-pointer"
                      onClick={() =>
                        navigate(`/coin-detail/${coin.coinMarketId}`)
                      }
                    >
                      <div className="me-2">
                        <img
                          src={`https://s2.coinmarketcap.com/static/img/coins/32x32/${coin.coinMarketId}.png`}
                          alt={coin.name}
                          className="coin-logo"
                          width={32}
                          height={32}
                        />
                      </div>
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
