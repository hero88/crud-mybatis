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
import { CalendarIcon, Pencil } from "lucide-react";
import { useEffect, useState } from "react";
import { Description } from "@radix-ui/react-dialog";
import { useToast } from "@/components/ui/use-toast";
import { ToastAction } from "@/components/ui/toast";
import { doUpdateTimeTracking } from "@/services/TimeTrackingAPI";

function UpdateTrackingTimeDialog({ trackingTime, loadTimeTrackingData }) {
  const { toast } = useToast();

  const [currentTrackingTime, setCurrentTrackingTime] = useState({
    employeeId: "",
    dateTrack: "",
    clockIn: "",
    clockOut: "",
    totalHours: "",
  });

  const [validateError, setValidateError] = useState(["basic"]);

  const [selectedDateTrack, setSelectedDateTrack] = useState();

  const { employeeId, dateTrack, clockIn, clockOut, totalHours } =
    currentTrackingTime;

  useEffect(() => {
    setCurrentTrackingTime(trackingTime);
  }, []);

  const handleChangeField = (e) => {
    setCurrentTrackingTime({
      ...currentTrackingTime,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmitUpdate = async () => {
    if (employeeId === employeeId) {
      console.log("error");
    } else {
      setValidateError(["basic"]);
      const formattedDateTrack = selectedDateTrack
        ? Math.floor(selectedDateTrack.getTime() / 1000)
        : null;

      let newTimeTracking = {
        ...currentTrackingTime,
        dateTrack: formattedDateTrack,
      };

      const { data: response } = await doUpdateTimeTracking(newTimeTracking);

      console.log(response);

      if (response.code === 200) {
        setValidateError([]);
        loadTimeTrackingData();

        toast({
          title: "Update time tracking successfully!",
          description: "Time tracking list has been changed.",
          action: <ToastAction altText="Nice">Nice</ToastAction>,
        });
      } else {
        toast({
          variant: "destructive",
          title: "Uh oh! Something went wrong.",
          description: "There was a problem with updating.",
          action: <ToastAction altText="Try again">Try again</ToastAction>,
        });
      }
    }
  };

  return (
    <>
      <Dialog>
        <DialogTrigger asChild>
          <Button className="w-5 h-5 p-0 bg-transparent hover:bg-transparent">
            <Pencil
              width={17}
              height={17}
              className="text-blue-500"
              onClick={() => setValidateError(["basic"])}
            />
          </Button>
        </DialogTrigger>
        {validateError.length > 0 && (
          <DialogContent className="sm:max-w-[560px]">
            <AlertDialogHeader>
              <DialogTitle className="text-blue-500">
                Update tracking time
              </DialogTitle>
              <DialogDescription>
                Make changes to your tracking time info here. Click save when
                you&apos;re done.
              </DialogDescription>
            </AlertDialogHeader>
            <div className="grid gap-4 py-4">
              {/* Clock In */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="clockIn" className="text-right">
                  Clock In
                </Label>
                <div className="col-span-3">
                  <Input
                    name="clockIn"
                    id="clockIn"
                    value={clockIn}
                    onChange={(e) => handleChangeField(e)}
                  />
                </div>
              </div>
              {/* Clock Out */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="clockOut" className="text-right">
                  Clock Out
                </Label>
                <div className="col-span-3">
                  <Input
                    name="clockOut"
                    id="clockOut"
                    value={clockOut}
                    onChange={(e) => handleChangeField(e)}
                  />
                </div>
              </div>
              {/* Birthday
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="birthday" className="text-right">
                  Birthday
                </Label>
                <div className="col-span-3">
                  <Popover>
                    <PopoverTrigger asChild>
                      <Button
                        variant={"outline"}
                        className={cn(
                          "w-[240px] pl-3 text-left font-normal text-black",
                          !selectedBirthday &&
                            "text-muted-foreground text-black"
                        )}
                      >
                        {selectedBirthday && !birthday ? (
                          format(selectedBirthday, "PPP")
                        ) : !selectedBirthday && birthday ? (
                          format(birthday * 1000, "PPP")
                        ) : selectedBirthday && birthday ? (
                          format(selectedBirthday, "PPP")
                        ) : (
                          <span>Pick a date</span>
                        )}
                        <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                      </Button>
                    </PopoverTrigger>
                    <PopoverContent className="w-auto p-0" align="start">
                      <Calendar
                        mode="single"
                        selected={selectedBirthday}
                        onSelect={setSelectedBirthday}
                        disabled={(date) =>
                          date > new Date() || date < new Date("1900-01-01")
                        }
                        initialFocus
                      />
                    </PopoverContent>
                  </Popover>
                </div>
              </div> */}

              {/* Date Track */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="dateTrack" className="text-right">
                  Phone
                </Label>
                <div className="col-span-3">
                  <Input
                    name="dateTrack"
                    id="dateTrack"
                    value={dateTrack || ""}
                    onChange={(e) => handleChangeField(e)}
                  />
                </div>
              </div>
              {/* Total Hours */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="totalHours" className="text-right">
                  Total Hours
                </Label>
                <div className="col-span-3">
                  <Input
                    name="totalHours"
                    id="totalHours"
                    value={totalHours || ""}
                    onChange={(e) => handleChangeField(e)}
                  />
                </div>
              </div>
            </div>
            <DialogFooter>
              <Button type="submit" onClick={handleSubmitUpdate}>
                Save changes
              </Button>
            </DialogFooter>
          </DialogContent>
        )}
      </Dialog>
    </>
  );
}

export default UpdateTrackingTimeDialog;
