import { useEffect, useState } from "react";
import { ChevronsUpDown, XCircle } from "lucide-react";
import { Popover, PopoverContent, PopoverTrigger } from "../ui/popover";
import { Button } from "../ui/button";
import {
  Command,
  CommandGroup,
  CommandInput,
  CommandItem,
} from "../ui/command";
import { ScrollArea } from "../ui/scroll-area";

function TagsInput({ insurances, tags, setTags }) {
  const [filteredInsurances, setFilteredInsurances] = useState([]);
  const [open, setOpen] = useState(false);

  useEffect(() => {
    setFilteredInsurances(
      tags.length > 0
        ? insurances?.filter((item1) =>
            !tags.map((item2) => item2.id)?.includes(item1.id)
          )
        : insurances
    );
  }, []);

  const handleRemoveTag = (insurance) => {
    setTags(tags.filter((item) => item.id !== insurance.id));
    setFilteredInsurances([...filteredInsurances, insurance]);
  };

  const handleSelectInsurance = (insurance) => {
    setTags([...tags, insurance]);
    setFilteredInsurances(
      filteredInsurances.filter((item) => item.id !== insurance.id)
    );
    setOpen(false);
  };

  return (
    <div className="">
      <div className="flex">
        <Popover open={open} onOpenChange={setOpen}>
          <PopoverTrigger asChild>
            <Button
              variant="outline"
              role="combobox"
              aria-expanded={open}
              className="w-[300px] justify-between"
            >
              Add insurance...
              <ChevronsUpDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
            </Button>
          </PopoverTrigger>
          <PopoverContent className="w-[280px] p-0">
            <Command>
              <CommandInput placeholder="Search department..." />
              <ScrollArea className="h-72">
                <CommandGroup>
                  {filteredInsurances?.length > 0
                    ? filteredInsurances?.map((insurance, index) => (
                        <CommandItem
                          key={index}
                          value={insurance.insuranceName}
                          onSelect={() => handleSelectInsurance(insurance)}
                        >
                          {insurance.insuranceName}
                        </CommandItem>
                      ))
                    : insurances
                        ?.filter(
                          (item1) =>
                            !tags.map((item2) => item2.id)?.includes(item1.id)
                        )
                        .map((insurance, index) => (
                          <CommandItem
                            key={index}
                            value={insurance.insuranceName}
                            onSelect={() => handleSelectInsurance(insurance)}
                          >
                            {insurance.insuranceName}
                          </CommandItem>
                        ))}
                </CommandGroup>
              </ScrollArea>
            </Command>
          </PopoverContent>
        </Popover>
      </div>
      {tags.length > 0 && (
        <div className="flex flex-wrap gap-2 mt-2">
          {tags.map((tag, index) => (
            <div
              key={index}
              className="bg-gray-200  px-5 py-2 rounded-3xl flex items-center"
            >
              <p className="font-semibold">{tag.insuranceName}</p>
              <button
                onClick={() => handleRemoveTag(tag)}
                className="ml-2 focus:outline-none"
              >
                <XCircle className="w-5 h-5 text-gray-600 hover:text-black" />
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default TagsInput;
