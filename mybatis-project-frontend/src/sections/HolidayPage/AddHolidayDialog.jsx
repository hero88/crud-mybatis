import { AlertDialogHeader } from "@/components/ui/alert-dialog";
import { Button } from "@/components/ui/button";
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
import { useState } from "react";
import { Description } from "@radix-ui/react-dialog";
import { useToast } from "@/components/ui/use-toast";
import { ToastAction } from "@/components/ui/toast";
import { doAddNewHoliday } from "@/services/HolidayAPI";

function AddNewHolidayDialog({ loadHolidaysData }) {
  const { toast } = useToast();

  const [currentHoliday, setCurrentHoliday] = useState({
    holidayName: "",
    holidayDescription: "",
    durationDays: 1,
  });

  const [validateError, setValidateError] = useState([]);
  const [openDialog, setOpenDialog] = useState(false);

  const { holidayName, holidayDescription, durationDays } = currentHoliday;

  const handleChangeField = (e) => {
    setCurrentHoliday({
      ...currentHoliday,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmitCreate = async () => {
    if (holidayName === "" || holidayDescription === "") {
      if (holidayName === "" && holidayDescription == "") {
        setValidateError(["name", "description"]);
      } else if (holidayName === "") {
        setValidateError(["name"]);
      } else if (holidayDescription === "") {
        setValidateError(["description"]);
      }
    } else {
      if (parseInt(durationDays) < 0) {
        setValidateError(["duration"]);
      } else {
        let newHoliday = {
          ...currentHoliday,
          durationDays: parseInt(durationDays),
        };

        const { data: response } = await doAddNewHoliday(newHoliday);

        if (response.code === 200) {
          setCurrentHoliday({
            holidayName: "",
            holidaydescription: "",
            durationDays: 1,
          });

          setValidateError([]);
          loadHolidaysData();
          setOpenDialog(false);
          toast({
            title: "Add holiday successfully!",
            description: "Holiday list has been changed.",
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
      <Dialog open={openDialog} onOpenChange={setOpenDialog}>
        <DialogTrigger asChild>
          <Button>Add new holiday</Button>
        </DialogTrigger>
        <DialogContent className="sm:max-w-[580px]">
          <AlertDialogHeader>
            <DialogTitle className="text-green-500">
              Add new holiday
            </DialogTitle>
            <DialogDescription>
              Add new holiday info here. Click save when you&apos;re done.
            </DialogDescription>
          </AlertDialogHeader>
          <div className="grid gap-4 py-4">
            {/* Name */}
            <div className="grid grid-cols-4 items-center gap-4">
              <Label htmlFor="firstname" className="text-right">
                Name
              </Label>
              <div className="col-span-3">
                <Input
                  name="holidayName"
                  id="holidayName"
                  value={holidayName}
                  onChange={(e) => handleChangeField(e)}
                />
                {validateError.includes("name") && (
                  <Description className="text-red-500 font-semibold text-[12px]">
                    Do not let holiday name empty
                  </Description>
                )}
              </div>
            </div>
            {/* Description */}
            <div className="grid grid-cols-4 items-center gap-4">
              <Label htmlFor="lastname" className="text-right">
                Description
              </Label>
              <div className="col-span-3">
                <Input
                  name="holidayDescription"
                  id="holidayDescription"
                  value={holidayDescription}
                  onChange={(e) => handleChangeField(e)}
                />
                {validateError.includes("description") && (
                  <Description className="text-red-500 font-semibold text-[12px]">
                    Do not let description empty
                  </Description>
                )}
              </div>
            </div>
            {/* Duration */}
            <div className="grid grid-cols-4 items-center gap-4">
              <Label htmlFor="userId" className="text-right">
                Duration days
              </Label>
              <div className="col-span-3">
                <Input
                  type="number"
                  name="durationDays"
                  id="durationDays"
                  value={durationDays}
                  onChange={(e) => handleChangeField(e)}
                />
                {validateError.includes("duration") && (
                  <Description className="text-red-500 font-semibold text-[12px]">
                    Do not let the value of duration less than zero
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
      </Dialog>
    </>
  );
}

export default AddNewHolidayDialog;
