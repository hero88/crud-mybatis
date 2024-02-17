import { Button } from "@/components/ui/button";
import {
  Command,
  CommandGroup,
  CommandInput,
  CommandItem,
} from "@/components/ui/command";
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";
import { ScrollArea } from "@/components/ui/scroll-area";
import { ToastAction } from "@/components/ui/toast";
import { useToast } from "@/components/ui/use-toast";
import { cn } from "@/lib/utils";
import { addCoin, getAllCoins } from "@/services/CoinAPI";
import { Check, ChevronsUpDown } from "lucide-react";
import { useEffect, useState } from "react";

function AddCoinDialog({ type, user, userCoins, marketCoins, recallCoins }) {
  const [open, setOpen] = useState(false);
  const [selectedValue, setSelectedValue] = useState();
  const [openDialog, setOpenDialog] = useState(false);
  const [localCoinList, setLocalCoinList] = useState([]);
  const [addQuantity, setAddQuantity] = useState(1);

  const { toast } = useToast();

  useEffect(() => {
    const getAllLocalCoins = async () => {
      try {
        const { data: response } = await getAllCoins();
        setLocalCoinList(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    getAllLocalCoins();
  }, []);

  const handleSelectedCoin = (currentValue) => {
    setSelectedValue(currentValue === selectedValue ? "" : currentValue);
    setOpen(false);
  };

  const handleAddNewCoin = async (selectedCoin) => {
    if (selectedCoin === null || selectedCoin === undefined) {
      toast({
        variant: "destructive",
        title: "Uh oh! You can not let the coin empty",
        description: "You can adding coin again.",
        action: <ToastAction altText="Try again">Try again</ToastAction>,
      });
    } else if (addQuantity === "0") {
      toast({
        variant: "destructive",
        title: "Uh oh! You can not set the quantity at zero.",
        description: "You can adding coin again.",
        action: <ToastAction altText="Try again">Try again</ToastAction>,
      });
    } else {
      const { id, name, symbol } = selectedCoin;

      const newCoin = {
        id: localCoinList.length,
        name,
        symbol,
        coinMarketId: id,
        quantity: addQuantity,
        userId: user.id,
      };

      console.log(newCoin.quantity);

      const { data: response } = await addCoin(newCoin);

      if (response.status === "ok") {
        recallCoins();
      } else {
        console.log("fail");
      }

      setSelectedValue();
      setAddQuantity(1);
    }

    setOpenDialog(false);
  };

  return (
    <Dialog open={openDialog} onOpenChange={setOpenDialog}>
      <div className="flex space-x-3">
        <Input placeholder="search..." />
        <DialogTrigger asChild>
          <Button variant="outline" className="bg-blue-400">
            {type == "ADD" ? "Add Coin" : "Update Coin"}
          </Button>
        </DialogTrigger>
      </div>
      <DialogContent className="sm:max-w-[425px]">
        <DialogHeader>
          <DialogTitle>
            {type == "ADD" ? "Add Coin" : "Update Coin"}
          </DialogTitle>
        </DialogHeader>
        <div className="grid gap-4 py-4">
          <div className="grid grid-cols-4 items-center gap-4">
            <Label htmlFor="name" className="text-right">
              Name
            </Label>
            <Popover open={open} onOpenChange={setOpen}>
              <PopoverTrigger asChild>
                <Button
                  variant="outline"
                  role="combobox"
                  aria-expanded={open}
                  className="w-[250px] justify-between"
                >
                  {selectedValue
                    ? marketCoins?.find(
                        (coin) => coin.name === selectedValue.name
                      )?.name
                    : "Select coin..."}
                  <ChevronsUpDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
                </Button>
              </PopoverTrigger>
              <PopoverContent className="w-[250px] p-0">
                <Command>
                  <CommandInput placeholder="Search coin..." />
                  <ScrollArea className="h-72">
                    <CommandGroup>
                      {marketCoins
                        ?.filter((o1) => {
                          return !userCoins.some((o2) => o1.name === o2.name);
                        })
                        .map((coin) => (
                          <CommandItem
                            key={coin.id}
                            value={coin.name}
                            onSelect={() => handleSelectedCoin(coin)}
                          >
                            <Check
                              className={cn(
                                "mr-2 h-4 w-4",
                                selectedValue === coin
                                  ? "opacity-100"
                                  : "opacity-0"
                              )}
                            />
                            {coin.name}
                          </CommandItem>
                        ))}
                    </CommandGroup>
                  </ScrollArea>
                </Command>
              </PopoverContent>
            </Popover>
          </div>
          <div className="grid grid-cols-4 items-center gap-4">
            <Label htmlFor="username" className="text-right">
              Quantity
            </Label>
            <Input
              id="quantity"
              className="col-span-3"
              type="number"
              onChange={(e) => setAddQuantity(e.target.value)}
              value={addQuantity}
            />
          </div>
        </div>
        <DialogFooter>
          <Button onClick={() => handleAddNewCoin(selectedValue)}>
            Save changes
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}

export default AddCoinDialog;
