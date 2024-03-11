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
import { useEffect, useState } from "react";
import { Description } from "@radix-ui/react-dialog";
import { useToast } from "@/components/ui/use-toast";
import { ToastAction } from "@/components/ui/toast";
import { doUpdateInsurance } from "@/services/InsuranceAPI";
import { Pencil } from "lucide-react";
import { Textarea } from "@/components/ui/textarea";

function UpdateInsuranceDialog({ insurance, loadInsurancesData }) {
  const { toast } = useToast();

  const [currentInsurance, setCurrentInsurance] = useState({
    insuranceName: "",
    insuranceDescription: "",
    insuranceRate: "",
  });

  const [validateError, setValidateError] = useState([]);
  const [openDialog, setOpenDialog] = useState(false);

  const { insuranceName, insuranceDescription, insuranceRate } =
    currentInsurance;

  useEffect(() => {
    setValidateError([]);
    setCurrentInsurance(insurance);
  }, []);

  const handleChangeField = (e) => {
    setCurrentInsurance({
      ...currentInsurance,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmitUpdate = async () => {
    if (
      insuranceName === "" ||
      insuranceDescription === "" ||
      insuranceRate === ""
    ) {
      if (
        insuranceName === "" &&
        insuranceDescription === "" &&
        insuranceRate === ""
      ) {
        setValidateError(["name", "description", "rate"]);
      } else if (insuranceName === "" && insuranceDescription == "") {
        setValidateError(["name", "description"]);
      } else if (insuranceDescription === "" && insuranceRate === "") {
        setValidateError(["description", "rate"]);
      } else if (insuranceName === "") {
        setValidateError(["name"]);
      } else if (insuranceDescription === "") {
        setValidateError(["description"]);
      } else if (insuranceRate === "") {
        setValidateError(["rate"]);
      }
    } else {
      if (isNaN(parseFloat(insuranceRate))) {
        setValidateError(["invalid"]);
      } else {
        let newInsurance = {
          ...currentInsurance,
          rate: parseFloat(insuranceRate),
        };

        console.log(newInsurance);

        const { data: response } = await doUpdateInsurance(newInsurance);

        console.log(response);

        if (response.code === 200) {
          setValidateError([]);
          loadInsurancesData();
          setOpenDialog(false);
          toast({
            title: "Add Insurance Successfully!",
            description: "Insurance info has been updated.",
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
          <Button className="w-5 h-5 p-0 bg-transparent hover:bg-transparent">
            <Pencil width={17} height={17} className="text-blue-500" />
          </Button>
        </DialogTrigger>
        <DialogContent className="sm:max-w-[560px]">
          <AlertDialogHeader>
            <DialogTitle className="text-blue-500">
              Update employee infomation
            </DialogTitle>
            <DialogDescription>
              Make changes to insurance info here. Click save when you&apos;re
              done.
            </DialogDescription>
          </AlertDialogHeader>
          <div className="grid gap-4 py-4">
            {/* Name */}
            <div className="grid grid-cols-4 items-center gap-4">
              <Label htmlFor="insuranceName" className="text-right">
                Name
              </Label>
              <div className="col-span-3">
                <Input
                  name="insuranceName"
                  id="insuranceName"
                  value={insuranceName}
                  onChange={(e) => handleChangeField(e)}
                />
                {validateError.includes("name") && (
                  <Description className="text-red-500 font-semibold text-[12px]">
                    Do not let insurance name empty
                  </Description>
                )}
              </div>
            </div>
            {/* Description */}
            <div className="grid grid-cols-4 items-center gap-4">
              <Label htmlFor="insuranceDescription" className="text-right">
                Description
              </Label>
              <div className="col-span-3">
                <Textarea
                  name="insuranceDescription"
                  id="insuranceDescription"
                  placeholder="Type insurance description here."
                  className="min-h-40"
                  value={insuranceDescription}
                  onChange={(e) => handleChangeField(e)}
                />
                {validateError.includes("description") && (
                  <Description className="text-red-500 font-semibold text-[12px]">
                    Do not let description empty
                  </Description>
                )}
              </div>
            </div>
            {/* Rate */}
            <div className="grid grid-cols-4 items-center gap-4">
              <Label htmlFor="insuranceRate" className="text-right">
                Rate
              </Label>
              <div className="col-span-3">
                <Input
                  name="insuranceRate"
                  id="insuranceRate"
                  value={insuranceRate}
                  onChange={(e) => handleChangeField(e)}
                />
                {validateError.includes("rate") && (
                  <Description className="text-red-500 font-semibold text-[12px]">
                    Do not let rate empty
                  </Description>
                )}
                {validateError.includes("invalid") && (
                  <Description className="text-red-500 font-semibold text-[12px]">
                    Please enter a valid rate number value
                  </Description>
                )}
              </div>
            </div>
          </div>
          <DialogFooter>
            <Button type="submit" onClick={handleSubmitUpdate}>
              Save changes
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </>
  );
}

export default UpdateInsuranceDialog;
