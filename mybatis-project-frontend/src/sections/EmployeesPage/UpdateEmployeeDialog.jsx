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
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { doUpdateEmployee } from "@/services/EmployeeAPI";

function UpdateEmployeeDialog({ employee, loadEmployeesData }) {
  const { toast } = useToast();

  const [currentEmployee, setCurrentEmployee] = useState({
    firstname: "",
    lastname: "",
    gender: "",
    contactNumber: "",
    position: "",
    // departmentId: 1,
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
    // departmentId,
  } = currentEmployee;

  useEffect(() => {
    setCurrentEmployee(employee);
  }, []);

  const handleChangeField = (e) => {
    setCurrentEmployee({ ...currentEmployee, [e.target.name]: e.target.value });
  };

  const handleChangeGender = (e) => {
    setCurrentEmployee({ ...currentEmployee, gender: e });
  };

  const handleSubmitUpdate = async () => {
    if (firstname === "" || lastname === "") {
      if (firstname === "" && lastname === "") {
        setValidateError([...validateError, "firstname", "lastname"]);
      } else if (firstname === "") {
        setValidateError([...validateError, "firstname"]);
      } else {
        setValidateError([...validateError, "lastname"]);
      }
    } else {
      setValidateError(["basic"]);
      const formattedBirthday = selectedBirthday
        ? Math.floor(selectedBirthday.getTime() / 1000)
        : null;
      const formattedHireDate = selectedHireDate
        ? Math.floor(selectedHireDate.getTime() / 1000)
        : null;
      const formattedTerminationDate = selectedTerminationDate
        ? Math.floor(selectedTerminationDate.getTime() / 1000)
        : null;

      let newEmployee = {
        ...currentEmployee,
        birthday: formattedBirthday,
        hireDate: formattedHireDate,
        terminationDate: formattedTerminationDate,
      };

      const { data: response } = await doUpdateEmployee(newEmployee);

      console.log(response);

      if (response.code === 200) {
        loadEmployeesData();

        setValidateError([]);
        toast({
          title: "Update employee successfully!",
          description: "Employee info has been changed.",
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
                Update employee infomation
              </DialogTitle>
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
              </div>
              {/* Gender */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="gender" className="text-right">
                  Gender
                </Label>
                <div className="col-span-3">
                  <RadioGroup
                    onValueChange={(e) => handleChangeGender(e)}
                    defaultValue={gender || "Male"}
                    name="gender"
                    className="flex space-x-2"
                  >
                    <div className="flex items-center space-x-3 space-y-0">
                      <RadioGroupItem value="Male" id="male" />
                      <Label className="font-semibold">Male</Label>
                    </div>
                    <div className="flex items-center space-x-3 space-y-0">
                      <RadioGroupItem value="Female" id="female" />
                      <Label className="font-semibold">Female</Label>
                    </div>
                  </RadioGroup>
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
                    value={contactNumber || ""}
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
                    value={position || ""}
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
                        {selectedHireDate && !hireDate ? (
                          format(selectedHireDate, "PPP")
                        ) : !selectedHireDate && hireDate ? (
                          format(hireDate * 1000, "PPP")
                        ) : selectedHireDate && hireDate ? (
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
                          !selectedTerminationDate &&
                            "text-muted-foreground text-black"
                        )}
                      >
                        {selectedTerminationDate && !terminationDate ? (
                          format(selectedTerminationDate, "PPP")
                        ) : !selectedTerminationDate && terminationDate ? (
                          format(terminationDate * 1000, "PPP")
                        ) : selectedTerminationDate && terminationDate ? (
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
                Save changes
              </Button>
            </DialogFooter>
          </DialogContent>
        )}
      </Dialog>
    </>
  );
}

export default UpdateEmployeeDialog;
