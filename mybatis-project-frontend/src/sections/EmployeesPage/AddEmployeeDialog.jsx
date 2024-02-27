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
import { CalendarIcon, Check, ChevronsUpDown } from "lucide-react";
import { useEffect, useState } from "react";
import { Description } from "@radix-ui/react-dialog";
import { useToast } from "@/components/ui/use-toast";
import { ToastAction } from "@/components/ui/toast";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { doAddNewEmployee } from "@/services/EmployeeAPI";
import { doAddNewPayroll } from "@/services/PayrollAPI";
import { getAllUsers } from "@/services/UserAPI";
import { doGetAllDepartments } from "@/services/Department";
import {
  Command,
  CommandEmpty,
  CommandGroup,
  CommandInput,
  CommandItem,
} from "@/components/ui/command";
import { CaretSortIcon, CheckIcon } from "@radix-ui/react-icons";
import { ScrollArea } from "@/components/ui/scroll-area";

const frameworks = [
  {
    value: "next.js",
    label: "Next.js",
  },
  {
    value: "sveltekit",
    label: "SvelteKit",
  },
  {
    value: "nuxt.js",
    label: "Nuxt.js",
  },
  {
    value: "remix",
    label: "Remix",
  },
  {
    value: "astro",
    label: "Astro",
  },
];

function AddNewEmployeeDialog({ loadEmployeesData }) {
  const { toast } = useToast();

  const [currentEmployee, setCurrentEmployee] = useState({
    firstname: "",
    lastname: "",
    gender: "",
    contactNumber: "",
    position: "",
    departmentId: 1,
    userId: "",
  });

  const [open, setOpen] = useState(false);

  const [users, setUsers] = useState([]);
  const [departments, setDepartments] = useState([]);
  const [validateError, setValidateError] = useState(["basic"]);
  const [selectedHireDate, setSelectedHireDate] = useState();
  const [selectedTerminationDate, setSelectedTerminationDate] = useState();
  const [selectedBirthday, setSelectedBirthday] = useState();
  const [selectedDepartment, setSelectedDepartment] = useState();

  const {
    firstname,
    lastname,
    gender,
    contactNumber,
    position,
    departmentId,
    userId,
  } = currentEmployee;

  const handleGetUsersData = async () => {
    const { data: response } = await getAllUsers();
    setUsers(response.data);
  };

  const handleGetDepartmentsData = async () => {
    const { data: response } = await doGetAllDepartments();
    console.log(response.data);
    setDepartments(response.data);
  };

  useEffect(() => {
    handleGetDepartmentsData();
    handleGetUsersData();
  }, []);

  const handleChangeField = (e) => {
    setCurrentEmployee({ ...currentEmployee, [e.target.name]: e.target.value });
  };

  const handleChangeGender = (e) => {
    setCurrentEmployee({ ...currentEmployee, gender: e });
  };

  const handleSelectedCoin = (currentValue) => {
    setSelectedDepartment(
      currentValue === selectedDepartment ? "" : currentValue
    );
    setOpen(false);
  };

  const handleSubmitCreate = async () => {
    if (firstname === "" || lastname === "" || userId === "") {
      if (firstname === "" && lastname === "" && userId === "") {
        setValidateError(["firstname", "lastname", "userid"]);
      } else if (firstname === "" && lastname == "") {
        setValidateError(["firstname", "lastname"]);
      } else if (lastname === "" && userId === "") {
        setValidateError(["lastname", "userid"]);
      } else if (firstname === "") {
        setValidateError(["firstname"]);
      } else if (lastname === "") {
        setValidateError(["lastname"]);
      } else if (userId === "") {
        setValidateError(["userid"]);
      }
    } else {
      const userExist = users.some((user) => user.id === userId);

      if (userExist === false) {
        setValidateError(["userexist"]);
      } else {
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

        const { data: response } = await doAddNewEmployee(newEmployee);

        console.log(newEmployee);
        console.log(response);
        setValidateError([]);
        if (response.code === 200) {
          const employeeData = response.data;

          let newPayroll = {
            employeeId: employeeData.id,
            salary: 0,
            bonus: 0,
            deductions: 0,
            netSalary: 0,
            periodStart: null,
            periodEnd: null,
          };

          const { data: payrollResponse } = await doAddNewPayroll(newPayroll);

          console.log(payrollResponse);

          setCurrentEmployee({
            firstname: "",
            lastname: "",
            gender: "",
            contactNumber: "",
            position: "",
            departmentId: 1,
            userId: "",
          });

          loadEmployeesData();

          toast({
            title: "Add employee successfully!",
            description: "Employee list has been changed.",
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
            Add new employee
          </Button>
        </DialogTrigger>
        {validateError.length > 0 && (
          <DialogContent className="sm:max-w-[580px]">
            <AlertDialogHeader>
              <DialogTitle className="text-green-500">
                Add new employee
              </DialogTitle>
              <DialogDescription>
                Add new employee info here. Click save when you&apos;re done.
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
              {/* User Id */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="userId" className="text-right">
                  User Id
                </Label>
                <div className="col-span-3">
                  <Input
                    name="userId"
                    id="userId"
                    value={userId}
                    onChange={(e) => handleChangeField(e)}
                  />
                  {validateError.includes("userid") && (
                    <Description className="text-red-500 font-semibold text-[12px]">
                      Do not let user id empty
                    </Description>
                  )}
                  {validateError.includes("userexist") && (
                    <Description className="text-red-500 font-semibold text-[12px]">
                      User id not exist
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
              {/* Department */}
              {/* <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="position" className="text-right">
                  Department Id
                </Label>
                <div className="col-span-3">
                  <Input
                    name="departmentId"
                    id="position"
                    value={departmentId || ""}
                    onChange={(e) => handleChangeField(e)}
                  />
                </div>
              </div> */}
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
                      className="w-[250px] justify-between"
                    >
                      {selectedDepartment
                        ? departments?.find(
                            (department) =>
                              department.name === selectedDepartment.name
                          )?.name
                        : "Select coin..."}
                      <ChevronsUpDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
                    </Button>
                  </PopoverTrigger>
                  <PopoverContent className="w-[250px] p-0">
                    <Command>
                      <CommandInput placeholder="Search coin..." />
                      <ScrollArea className="h-72">
                        <CommandGroup>
                          {departments?.map((department) => (
                            <CommandItem
                              key={department.id}
                              value={department.name}
                              onSelect={() => handleSelectedCoin(department)}
                            >
                              <Check
                                className={cn(
                                  "mr-2 h-4 w-4",
                                  selectedDepartment === department
                                    ? "opacity-100"
                                    : "opacity-0"
                                )}
                              />
                              {department.name}
                            </CommandItem>
                          ))}
                        </CommandGroup>
                      </ScrollArea>
                    </Command>
                  </PopoverContent>
                </Popover>
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
                          !selectedTerminationDate &&
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

export default AddNewEmployeeDialog;
