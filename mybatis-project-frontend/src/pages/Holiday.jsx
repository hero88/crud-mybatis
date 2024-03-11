import { useEffect, useState } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { doGetAllHoliday } from "@/services/HolidayAPI";
import UpdateHolidayDialog from "@/sections/HolidayPage/UpdateHolidayDialog";
import AddNewHolidayDialog from "@/sections/HolidayPage/AddHolidayDialog";
import DelHolidayDialog from "@/sections/HolidayPage/DelHolidayDialog";

function Holiday() {
  const [holidays, setHolidays] = useState([]);

  const handleGetHolidaysData = async () => {
    const { data: response } = await doGetAllHoliday();
    setHolidays(response.data);
  };

  useEffect(() => {
    handleGetHolidaysData();
  }, []);

  return (
    <div className="px-24 pt-4 pb-28 flex flex-col">
      <h2 className="text-3xl font-semibold text-gray-900 mt-8">Holiday</h2>

      <div className="flex justify-end mt-8">
        <AddNewHolidayDialog loadHolidaysData={handleGetHolidaysData} />
      </div>

      <div className="mt-3 border rounded-md">
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead className="w-8 text-black"></TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                #
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Name
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Description
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Rating
              </TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {holidays?.map((holiday, index) => (
              <TableRow key={index}>
                <TableCell className="font-semibold"></TableCell>
                <TableCell className="text-gray-500 font-semibold">
                  {holiday.id}
                </TableCell>
                <TableCell className="font-semibold">
                  {holiday.holidayName}
                </TableCell>
                <TableCell className="font-semibold">
                  {holiday.holidayDescription}
                </TableCell>
                <TableCell className="font-semibold">
                  {holiday.durationDays}
                </TableCell>
                <TableCell className="flex items-center space-x-4 justify-center">
                  <UpdateHolidayDialog
                    holiday={holiday}
                    loadHolidaysData={handleGetHolidaysData}
                  />
                  <DelHolidayDialog
                    holiday={holiday}
                    loadHolidaysData={handleGetHolidaysData}
                  />
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>
    </div>
  );
}

export default Holiday;
