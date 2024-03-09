import { useEffect, useState } from "react";
import { ChevronsUpDown, XCircle } from "lucide-react";
import { Button } from "@/components/ui/button";
import { ScrollArea } from "@/components/ui/scroll-area";
import {
  Popover,
  PopoverTrigger,
  PopoverContent,
} from "@/components/ui/popover";
import {
  Command,
  CommandGroup,
  CommandInput,
  CommandItem,
} from "@/components/ui/command";

function HolidayTagsInput({ holidays, tags, setTags }) {
  const [filteredholidays, setFilteredholidays] = useState([]);
  const [open, setOpen] = useState(false);

  useEffect(() => {
    setFilteredholidays(
      tags.length > 0
        ? holidays?.filter(
            (item1) => !tags.map((item2) => item2.id)?.includes(item1.id)
          )
        : holidays
    );
  }, []);

  const handleRemoveTag = (insurance) => {
    setTags(tags.filter((item) => item.id !== insurance.id));
    setFilteredholidays([...filteredholidays, insurance]);
  };

  const handleSelectHoliday = (holiday) => {
    setTags([...tags, holiday]);
    setFilteredholidays(
      filteredholidays.filter((item) => item.id !== holiday.id)
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
              Add holiday...
              <ChevronsUpDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
            </Button>
          </PopoverTrigger>
          <PopoverContent className="w-[280px] p-0">
            <Command>
              <CommandInput placeholder="Search holiday..." />
              <ScrollArea className="h-60">
                <CommandGroup>
                  {filteredholidays?.length > 0
                    ? filteredholidays?.map((holiday, index) => (
                        <CommandItem
                          key={index}
                          value={holiday.holidayName}
                          onSelect={() => handleSelectHoliday(holiday)}
                        >
                          {holiday.holidayName}
                        </CommandItem>
                      ))
                    : tags.length > 0
                    ? holidays
                        ?.filter(
                          (item1) =>
                            !tags.map((item2) => item2.id)?.includes(item1.id)
                        )
                        .map((holiday, index) => (
                          <CommandItem
                            key={index}
                            value={holiday.holidayName}
                            onSelect={() => handleSelectHoliday(holiday)}
                          >
                            {holiday.holidayName}
                          </CommandItem>
                        ))
                    : holidays?.map((holiday, index) => (
                        <CommandItem
                          key={index}
                          value={holiday.holidayName}
                          onSelect={() => handleSelectHoliday(holiday)}
                        >
                          {holiday.holidayName}
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
              <p className="font-semibold">{tag.holidayName}</p>
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

export default HolidayTagsInput;
