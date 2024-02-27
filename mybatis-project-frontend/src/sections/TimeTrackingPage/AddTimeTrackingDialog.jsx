import { AlertDialogHeader } from "@/components/ui/alert-dialog";
import { Button } from "@/components/ui/button";
import { Calendar } from "@/components/ui/calendar";
import { format } from "date-fns";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
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
import { CalendarIcon } from "lucide-react";
import { useEffect, useState } from "react";
import { useToast } from "@/components/ui/use-toast";
import { ToastAction } from "@/components/ui/toast";
import { doAddNewTimeTracking } from "@/services/TimeTrackingAPI";
import { Description } from "@radix-ui/react-dialog";
import { doGetAllEmployees } from "@/services/EmployeeAPI";

const parseTime = (value) => {
  const [hours, minutes] = value.split(":");
  const timeInMinutes = hours * 60 + minutes * 1;
  return timeInMinutes;
};

function AddTimeTrackingDialog({ loadTimeTrackingData }) {
  const { toast } = useToast();

  const [employees, setEmployees] = useState([]);
  const [currentTrackingData, setCurrentTrackingData] = useState({
    employeeId: "",
    dateTrack: "",
    clockIn: "",
    clockOut: "",
  });

  const [validateError, setValidateError] = useState(["basic"]);
  const [selectedDateTrack, setSelectedDateTrack] = useState();

  const { employeeId, dateTrack, clockIn, clockOut } = currentTrackingData;

  const handleLoadEmployeeData = async () => {
    const { data: response } = await doGetAllEmployees();
    setEmployees(response.data);
  };

  useEffect(() => {
    handleLoadEmployeeData();
  }, []);

  const handleChangeField = (e) => {
    setCurrentTrackingData({
      ...currentTrackingData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmitCreate = async () => {
    if (
      employeeId === "" ||
      dateTrack === null ||
      clockIn === "" ||
      clockOut === ""
    ) {
      toast({
        variant: "destructive",
        title: "Uh oh! Something went wrong.",
        description: "Please do not let any field empty.",
        action: <ToastAction altText="Try again">Try again</ToastAction>,
      });
    } else {
      const clockInTime = parseTime(clockIn);
      const clockOutTime = parseTime(clockOut);
      const hoursWorked = Math.floor((clockOutTime - clockInTime) / 60);
      const employeeExist = employees.some(
        (employee) => employee.id === parseInt(employeeId)
      );

      if (clockInTime >= clockOutTime || employeeExist === false) {
        if (clockInTime >= clockOutTime && employeeExist) {
          setValidateError([...validateError, "clockout", "employeeid"]);
        } else if (clockInTime >= clockOutTime) {
          setValidateError([...validateError, "clockout"]);
        } else if (employeeExist === false) {
          setValidateError([...validateError, "employeeid"]);
        }
      } else {
        const formattedDateTrack = selectedDateTrack
          ? format(selectedDateTrack, "yyyy-MM-dd")
          : null;

        let newTimeTracking = {
          ...currentTrackingData,
          dateTrack: formattedDateTrack,
          clockIn: clockIn + ":00",
          clockOut: clockOut + ":00",
          totalHours: hoursWorked,
        };

        const { data: response } = await doAddNewTimeTracking(newTimeTracking);

        if (response.code === 200) {
          setCurrentTrackingData({
            employeeId: "",
            dateTrack: "",
            clockIn: "",
            clockOut: "",
          });

          loadTimeTrackingData();
          setValidateError([]);
          toast({
            title: "Add tracking time successfully!",
            description: "Tracking time list has been changed.",
            action: <ToastAction altText="Nice">Nice</ToastAction>,
          });
        } else {
          toast({
            variant: "destructive",
            title: "Uh oh! Something went wrong.",
            description: "There was a problem with creating info.",
            action: <ToastAction altText="Try again">Try again</ToastAction>,
          });
        }
      }
    }
  };

  return (
    <>
      <Dialog>
        <DialogTrigger asChild>
          <Button onClick={() => setValidateError(["basic"])}>
            Add time tracking
          </Button>
        </DialogTrigger>
        {validateError.length > 0 && (
          <DialogContent className="sm:max-w-[580px]">
            <AlertDialogHeader>
              <DialogTitle className="text-green-800">
                Add new time tracking
              </DialogTitle>
              <DialogDescription>
                Add new tracking time here. Click save when you&apos;re done.
              </DialogDescription>
            </AlertDialogHeader>
            <div className="grid gap-4 py-4">
              {/* Employee Id */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="employeeId" className="text-right">
                  Employee Id
                </Label>
                <div className="col-span-3">
                  <Input
                    name="employeeId"
                    id="employeeId"
                    value={employeeId}
                    onChange={(e) => handleChangeField(e)}
                  />
                  {validateError.includes("employeeid") && (
                    <Description className="text-red-500 font-semibold text-[12px]">
                      Employee id do not exist.
                    </Description>
                  )}
                </div>
              </div>
              {/* Date Track */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="dateTrack" className="text-right">
                  Date Track
                </Label>
                <div className="col-span-3">
                  <Popover>
                    <PopoverTrigger asChild>
                      <Button
                        variant={"outline"}
                        className={cn(
                          "w-[240px] pl-3 text-left font-normal text-black",
                          !selectedDateTrack &&
                            "text-muted-foreground text-black"
                        )}
                      >
                        {selectedDateTrack ? (
                          format(selectedDateTrack, "PPP")
                        ) : (
                          <span>Pick a date</span>
                        )}
                        <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                      </Button>
                    </PopoverTrigger>
                    <PopoverContent className="w-auto p-0" align="start">
                      <Calendar
                        mode="single"
                        selected={selectedDateTrack}
                        onSelect={setSelectedDateTrack}
                        disabled={(date) =>
                          date > new Date() || date < new Date("1900-01-01")
                        }
                        initialFocus
                      />
                    </PopoverContent>
                  </Popover>
                </div>
              </div>
              {/* Clock In */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="clockIn" className="text-right">
                  Clock In
                </Label>
                <div className="col-span-3">
                  <Input
                    type="time"
                    className="font-semibold"
                    name="clockIn"
                    id="clockIn"
                    value={clockIn}
                    onChange={(e) => handleChangeField(e)}
                  />
                </div>
              </div>
              {/* Clock Out*/}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="clockOut" className="text-right">
                  Clock Out
                </Label>
                <div className="col-span-3">
                  <Input
                    type="time"
                    className="font-semibold"
                    name="clockOut"
                    id="clockOut"
                    value={clockOut}
                    onChange={(e) => handleChangeField(e)}
                  />
                  {validateError.includes("clockout") && (
                    <Description className="text-red-500 font-semibold text-[12px]">
                      Your clock out time is sooner than clock in time.
                    </Description>
                  )}
                </div>
              </div>
            </div>
            <DialogFooter>
              <Button type="submit" onClick={handleSubmitCreate}>
                Create
              </Button>
            </DialogFooter>
          </DialogContent>
        )}
      </Dialog>
    </>
  );
}

export default AddTimeTrackingDialog;
