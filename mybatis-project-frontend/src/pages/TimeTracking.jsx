import format from "date-fns/format";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

import { useEffect, useState } from "react";
import { doGetAllTimeTracking } from "@/services/TimeTrackingAPI";
import UpdateTrackingTimeDialog from "@/sections/TimeTrackingPage/UpdateTrackingTimeDialog";
import AddTimeTrackingDialog from "@/sections/TimeTrackingPage/AddTimeTrackingDialog";

function TimeTracking() {
  const [timeTrackingData, setTimeTrackingData] = useState([]);

  const handleGetTimeTrackingData = async () => {
    const { data: response } = await doGetAllTimeTracking();
    console.log(response.data);
    setTimeTrackingData(response.data);
  };

  useEffect(() => {
    handleGetTimeTrackingData();
  }, []);

  return (
    <div className="px-24 pb-28 flex flex-col">
      <h2 className="text-3xl font-semibold text-gray-900 mt-8">
        Employee Time Tracking
      </h2>

      <div className="flex justify-end mt-8">
        <AddTimeTrackingDialog
          loadTimeTrackingData={handleGetTimeTrackingData}
        />
      </div>

      <div className="mt-8 border rounded-md">
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead className="w-8 text-black"></TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                #
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Date Track
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Clock In
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Clock Out
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Total Hours
              </TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {timeTrackingData?.map((tracking, index) => (
              <TableRow key={index}>
                <TableCell className="font-semibold"></TableCell>
                <TableCell className="text-gray-500 font-semibold">
                  {tracking.id}
                </TableCell>
                <TableCell className="font-semibold">
                  {tracking.dateTrack
                    ? format(new Date(tracking.dateTrack), "dd-MM-yyyy")
                    : "Not set yet"}
                </TableCell>
                <TableCell className="font-semibold">
                  {tracking.clockIn}
                </TableCell>
                <TableCell className="font-semibold">
                  {tracking.clockOut}
                </TableCell>
                <TableCell className="font-semibold">
                  {tracking.totalHours}
                </TableCell>

                <TableCell className="flex items-center space-x-4 justify-center">
                  <UpdateTrackingTimeDialog
                    trackingTime={tracking}
                    loadTimeTrackingData={handleGetTimeTrackingData}
                  />
                  {/* <DelTrackingTimeDialog
                    loadEmployeesData={handleGetTimeTrackingData}
                  /> */}
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>
    </div>
  );
}

export default TimeTracking;
