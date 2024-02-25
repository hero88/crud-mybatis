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

function UpdatePayrollDialog({ payroll }) {
  const { toast } = useToast();

  const [currentPayroll, setCurrentPayroll] = useState({
    salary: "",
    bonus: "",
    deductions: "",
    netSalary: "",
  });

  const [validateError, setValidateError] = useState(["basic"]);

  const [selectedPeriodStart, setSelectedPeriodStart] = useState();
  const [selectedPeriodEnd, setSelectedPeriodEnd] = useState();

  const { salary, bonus, deductions, netSalary } = currentPayroll;

  // useEffect(() => {
  //   setCurrentPayroll(payroll);
  // }, []);

  const handleChangeField = (e) => {
    setCurrentPayroll({ ...currentPayroll, [e.target.name]: e.target.value });
  };

  const handleSubmitUpdate = () => {
    // if (firstname === "" || lastname === "") {
    //   if (firstname === "" && lastname === "") {
    //     setValidateError([...validateError, "firstname", "lastname"]);
    //   } else if (firstname === "") {
    //     setValidateError([...validateError, "firstname"]);
    //   } else {
    //     setValidateError([...validateError, "lastname"]);
    //   }
    // } else {
    //   const response = "success";
    //   if (response === "success") {
    //     setValidateError([]);
    //     const formattedPeriodStart = format(selectedPeriodStart, "dd/MM/yyyy");
    //     const formattedPeriodEnd = format(selectedPeriodEnd, "dd/MM/yyyy");
    //     toast({
    //       title: "Update payroll successfully!",
    //       description: "Payroll info has been changed.",
    //       action: <ToastAction altText="Nice">Nice</ToastAction>,
    //     });
    //     console.log({
    //       ...currentPayroll,
    //       formattedPeriodStart,
    //       formattedPeriodEnd,
    //     });
    //   } else {
    //     toast({
    //       variant: "destructive",
    //       title: "Uh oh! Something went wrong.",
    //       description: "There was a problem with updating.",
    //       action: <ToastAction altText="Try again">Try again</ToastAction>,
    //     });
    //   }
    // }
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
              <DialogTitle>Update payroll infomation</DialogTitle>
              <DialogDescription>
                Make changes to your employee payroll here. Click save when
                you&apos;re done.
              </DialogDescription>
            </AlertDialogHeader>
            <div className="grid gap-4 py-4">
              {/* Salary */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="salary" className="text-right">
                  Salary
                </Label>
                <div className="col-span-3">
                  <Input
                    name="salary"
                    id="salary"
                    value={salary}
                    onChange={(e) => handleChangeField(e)}
                  />
                  {validateError.includes("firstname") && (
                    <Description className="text-red-500 font-semibold text-[12px]">
                      Do not let firstname empty
                    </Description>
                  )}
                </div>
              </div>

              {/* Bonus */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="bonus" className="text-right">
                  Bonus
                </Label>
                <div className="col-span-3">
                  <Input
                    name="bonus"
                    id="bonus"
                    value={bonus}
                    onChange={(e) => handleChangeField(e)}
                  />
                  {validateError.includes("lastname") && (
                    <Description className="text-red-500 font-semibold text-[12px]">
                      Do not let lastname empty
                    </Description>
                  )}
                </div>
              </div>

              {/* Deductions */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="deductions" className="text-right">
                  Deductions
                </Label>
                <div className="col-span-3">
                  <Input
                    name="deductions"
                    id="deductions"
                    value={deductions}
                    onChange={(e) => handleChangeField(e)}
                  />
                </div>
              </div>

              {/* Position */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="netSal" className="text-right">
                  Position
                </Label>
                <div className="col-span-3">
                  <Input
                    name="netSalary"
                    id="netSalary"
                    value={netSalary}
                    onChange={(e) => handleChangeField(e)}
                  />
                </div>
              </div>

              {/* Period Start */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="hire-date" className="text-right">
                  Period Start
                </Label>
                <div className="col-span-3">
                  <Popover>
                    <PopoverTrigger asChild>
                      <Button
                        variant={"outline"}
                        className={cn(
                          "w-[240px] pl-3 text-left font-normal text-black",
                          !selectedPeriodStart &&
                            "text-muted-foreground text-black"
                        )}
                      >
                        {selectedPeriodStart ? (
                          format(selectedPeriodStart, "PPP")
                        ) : (
                          <span>Pick a date</span>
                        )}
                        <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                      </Button>
                    </PopoverTrigger>
                    <PopoverContent className="w-auto p-0" align="start">
                      <Calendar
                        mode="single"
                        selected={selectedPeriodStart}
                        onSelect={setSelectedPeriodStart}
                        disabled={(date) =>
                          date > new Date() || date < new Date("1900-01-01")
                        }
                        initialFocus
                      />
                    </PopoverContent>
                  </Popover>
                </div>
              </div>

              {/* Period End */}
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="termination-date" className="text-right">
                  Period End
                </Label>
                <div className="col-span-3">
                  <Popover>
                    <PopoverTrigger asChild>
                      <Button
                        variant={"outline"}
                        className={cn(
                          "w-[240px] pl-3 text-left font-normal text-black",
                          selectedPeriodEnd &&
                            "text-muted-foreground text-black"
                        )}
                      >
                        {selectedPeriodEnd ? (
                          format(selectedPeriodEnd, "PPP")
                        ) : (
                          <span>Pick a date</span>
                        )}
                        <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                      </Button>
                    </PopoverTrigger>
                    <PopoverContent className="w-auto p-0" align="start">
                      <Calendar
                        mode="single"
                        selected={selectedPeriodEnd}
                        onSelect={setSelectedPeriodEnd}
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

export default UpdatePayrollDialog;
