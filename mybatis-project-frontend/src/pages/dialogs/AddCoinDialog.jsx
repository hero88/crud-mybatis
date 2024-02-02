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
import { cn } from "@/lib/utils";
import { Check, ChevronsUpDown } from "lucide-react";
import { useState } from "react";

const coins = [
  { label: "Bitcoin", value: 1 },
  { label: "Ethereum", value: 1027 },
  { label: "Tether USDt", value: 825 },
  { label: "BNB", value: 1839 },
  { label: "Solana", value: 5426 },
  { label: "USDC", value: 3408 },
];

function AddCoinDialog({ type }) {
  const [open, setOpen] = useState(false);
  const [value, setValue] = useState();
  const [openDialog, setOpenDialog] = useState(false);

  const handleSelectedCoin = (currentValue) => {
    setValue(currentValue === value ? "" : currentValue);
    setOpen(false);
  };

  const handleSubmit = () =>{
    setOpenDialog(false);
  }


  return (
    <Dialog open={openDialog} onOpenChange={setOpenDialog}>
      <DialogTrigger asChild>
        <Button variant="outline">
          {type == "ADD" ? "Add Coin" : "Update Coin"}
        </Button>
      </DialogTrigger>
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
                  className="w-[200px] justify-between"
                >
                  {value
                    ? coins.find((coin) => coin.value === value)?.label
                    : "Select coin..."}
                  <ChevronsUpDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
                </Button>
              </PopoverTrigger>
              <PopoverContent className="w-[200px] p-0">
                <Command>
                  <CommandInput placeholder="Search coin..." />
                  <CommandGroup>
                    {coins.map((coin) => (
                      <CommandItem
                        key={coin.value}
                        value={coin.value}
                        onSelect={() => handleSelectedCoin(coin.value)}
                      >
                        <Check
                          className={cn(
                            "mr-2 h-4 w-4",
                            value === coin.value ? "opacity-100" : "opacity-0"
                          )}
                        />
                        {coin.label}
                      </CommandItem>
                    ))}
                  </CommandGroup>
                </Command>
              </PopoverContent>
            </Popover>
          </div>
          <div className="grid grid-cols-4 items-center gap-4">
            <Label htmlFor="username" className="text-right">
              Quantity
            </Label>
            <Input id="quantity" className="col-span-3" />
          </div>
        </div>
        <DialogFooter>
          <Button onClick={() => handleSubmit()}>Save changes</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}

export default AddCoinDialog;
