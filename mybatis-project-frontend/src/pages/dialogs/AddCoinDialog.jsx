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
import { cn } from "@/lib/utils";
import { addCoin, getMarketCapCoins } from "@/services/CoinAPI";
import { Check, ChevronsUpDown } from "lucide-react";
import { useEffect, useState } from "react";

function AddCoinDialog({ type }) {
  const [open, setOpen] = useState(false);
  const [selectedValue, setSelectedValue] = useState();
  const [openDialog, setOpenDialog] = useState(false);
  const [marketCoinList, setMarketCoinList] = useState();

  useEffect(() => {
    const callGetAllCoinsApi = async () => {
      try {
        const { data: response } = await getMarketCapCoins();
        setMarketCoinList(response.data.cryptoCurrencyList);
      } catch (error) {
        console.error(error);
      }
    };

    callGetAllCoinsApi();
  }, []);

  const handleSelectedCoin = (currentValue) => {
    setSelectedValue(currentValue === selectedValue ? "" : currentValue);
    setOpen(false);
  };

  const handleAddNewCoin = async (selectedCoin) => {

    const {name, } = selectedCoin;

    const newCoin = {
      name: "test",
      symbol: "test",
      coinMarketId: 1,
      quantity: 1,
      userId: 1,
    };

    const { data: response } = await addCoin(newCoin);

    console.log(selectedCoin);

    // setOpenDialog(false);
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
                    ? marketCoinList?.find(
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
                      {marketCoinList?.map((coin) => (
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
              defaultValue={1}
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
