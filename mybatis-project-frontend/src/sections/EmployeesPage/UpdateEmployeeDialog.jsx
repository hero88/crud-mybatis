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

function UpdateEmployeeDialog({ employee }) {
  const toast = useToast();

  const [currentEmployee, setCurrentEmployee] = useState({
    firstname: "",
    lastname: "",
    gender: "",
    contactNumber: "",
    position: "",
  });

  const [validateError, setValidateError] = useState(["basic"]);

  const [selectedHireDate, setSelectedHireDate] = useState();
  const [selectedTerminationDate, setSelectedTerminationDate] = useState();
  const [selectedBirthday, setSelectedBirthday] = useState();

  const {
    firstname,
    lastname,
    birthday,
    gender,
    contactNumber,
    position,
    hireDate,
    terminationDate,
  } = currentEmployee;

  // useEffect(() => {
  //   setCurrentEmployee(employee);
  // }, []);

  const handleChangeField = (e) => {
    setCurrentEmployee({ ...currentEmployee, [e.target.name]: e.target.value });
  };

  const handleSubmitUpdate = () => {
    if (firstname === "" || lastname === "") {
      if (firstname === "" && lastname === "") {
        setValidateError([...validateError, "firstname", "lastname"]);
      } else if (firstname === "") {
        setValidateError([...validateError, "firstname"]);
      } else {
        setValidateError([...validateError, "lastname"]);
      }
    } else {
      const response = "success";

      if (response === "success") {
        setValidateError([]);

        const formattedBirthday = format(selectedBirthday, "dd/MM/yyyy");
        const formattedHireDate = format(selectedHireDate, "dd/MM/yyyy");
        const formattedTerminationDate = format(
          selectedTerminationDate,
          "dd/MM/yyyy"
        );

        toast({
          title: "Update employee successfully!",
          description: "Employee info has been changed.",
          action: <ToastAction altText="Nice">Nice</ToastAction>,
        });

        console.log({
          ...currentEmployee,
          formattedBirthday,
          formattedHireDate,
          formattedTerminationDate,
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
              <DialogTitle>Update employee infomation</DialogTitle>
              <DialogDescription>
                Make changes to your employee info here. Click save when
                you&apos;re done.
              </DialogDescription>
            </AlertDialogHeader>
            <div className="grid gap-4 py-4">
              {/* Firstname */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="firstname" className="text-right">
                  Firstname
                </Label>
                <div className="col-span-3">
                  <Input
                    name="firstname"
                    id="firstname"
                    value={firstname}
                    onChange={(e) => handleChangeField(e)}
                  />
                  {validateError.includes("firstname") && (
                    <Description className="text-red-500 font-semibold text-[12px]">
                      Do not let firstname empty
                    </Description>
                  )}
                </div>
              </div>
              {/* Lastname */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="lastname" className="text-right">
                  Lastname
                </Label>
                <div className="col-span-3">
                  <Input
                    name="lastname"
                    id="lastname"
                    value={lastname}
                    onChange={(e) => handleChangeField(e)}
                  />
                  {validateError.includes("lastname") && (
                    <Description className="text-red-500 font-semibold text-[12px]">
                      Do not let lastname empty
                    </Description>
                  )}
                </div>
              </div>
              {/* Birthday */}
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
                        {selectedBirthday ? (
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
              </div>
              {/* Gender */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="gender" className="text-right">
                  Gender
                </Label>
                <div className="col-span-3">
                  <Input name="gender" id="gender" />
                </div>
              </div>
              {/* Phone */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="phone" className="text-right">
                  Phone
                </Label>
                <div className="col-span-3">
                  <Input
                    name="contactNumber"
                    id="phone"
                    value={contactNumber}
                    onChange={(e) => handleChangeField(e)}
                  />
                </div>
              </div>
              {/* Position */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="position" className="text-right">
                  Position
                </Label>
                <div className="col-span-3">
                  <Input
                    name="position"
                    id="position"
                    value={position}
                    onChange={(e) => handleChangeField(e)}
                  />
                </div>
              </div>
              {/* Hire date */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="hire-date" className="text-right">
                  Hire date
                </Label>
                <div className="col-span-3">
                  <Popover>
                    <PopoverTrigger asChild>
                      <Button
                        variant={"outline"}
                        className={cn(
                          "w-[240px] pl-3 text-left font-normal text-black",
                          !selectedHireDate &&
                            "text-muted-foreground text-black"
                        )}
                      >
                        {selectedHireDate ? (
                          format(selectedHireDate, "PPP")
                        ) : (
                          <span>Pick a date</span>
                        )}
                        <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                      </Button>
                    </PopoverTrigger>
                    <PopoverContent className="w-auto p-0" align="start">
                      <Calendar
                        mode="single"
                        selected={selectedHireDate}
                        onSelect={setSelectedHireDate}
                        disabled={(date) =>
                          date > new Date() || date < new Date("1900-01-01")
                        }
                        initialFocus
                      />
                    </PopoverContent>
                  </Popover>
                </div>
              </div>
              {/* Termination date */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="termination-date" className="text-right">
                  Termination date
                </Label>
                <div className="col-span-3">
                  <Popover>
                    <PopoverTrigger asChild>
                      <Button
                        variant={"outline"}
                        className={cn(
                          "w-[240px] pl-3 text-left font-normal text-black",
                          selectedTerminationDate &&
                            "text-muted-foreground text-black"
                        )}
                      >
                        {selectedTerminationDate ? (
                          format(selectedTerminationDate, "PPP")
                        ) : (
                          <span>Pick a date</span>
                        )}
                        <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                      </Button>
                    </PopoverTrigger>
                    <PopoverContent className="w-auto p-0" align="start">
                      <Calendar
                        mode="single"
                        selected={selectedTerminationDate}
                        onSelect={setSelectedTerminationDate}
                        disabled={(date) =>
                          date > new Date() || date < new Date("1900-01-01")
                        }
                        initialFocus
                      />
                    </PopoverContent>
                  </Popover>
                </div>
              </div>
            </div>
            <DialogFooter>
              <Button type="submit" onClick={handleSubmitUpdate}>
                Update
              </Button>
            </DialogFooter>
          </DialogContent>
        )}
      </Dialog>
    </>
  );
}

export default UpdateEmployeeDialog;
