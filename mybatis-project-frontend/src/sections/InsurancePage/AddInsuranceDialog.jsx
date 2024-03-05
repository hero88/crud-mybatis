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
import { doAddNewInsurance } from "@/services/InsuranceAPI";

function AddNewInsuranceDialog({ loadInsurancesData }) {
  const { toast } = useToast();

  const [currentInsurance, setCurrentInsurance] = useState({
    name: "",
    description: "",
    rate: "",
  });

  const [validateError, setValidateError] = useState([]);
  const [openDialog, setOpenDialog] = useState(false);

  const { name, description, rate } = currentInsurance;

  const handleChangeField = (e) => {
    setCurrentInsurance({
      ...currentInsurance,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmitCreate = async () => {
    if (name === "" || description === "" || rate === "") {
      if (name === "" && description === "" && rate === "") {
        setValidateError(["name", "description", "rate"]);
      } else if (name === "" && description == "") {
        setValidateError(["name", "description"]);
      } else if (description === "" && rate === "") {
        setValidateError(["description", "rate"]);
      } else if (name === "") {
        setValidateError(["name"]);
      } else if (description === "") {
        setValidateError(["description"]);
      } else if (rate === "") {
        setValidateError(["rate"]);
      }
    } else {
      if (isNaN(parseFloat(rate))) {
        setValidateError(["invalid"]);
      } else {
        let newInsurance = {
          ...currentInsurance,
          rate: parseFloat(rate),
        };

        console.log(newInsurance);

        const { data: response } = await doAddNewInsurance(newInsurance);

        console.log(response);

        if (response.code === 200) {
          setCurrentInsurance({
            name: "",
            description: "",
            rate: "",
          });

          setValidateError([]);
          loadInsurancesData();
          setOpenDialog(false);
          toast({
            title: "Add Insurance successfully!",
            description: "Insurance list has been changed.",
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
          <Button>Add new insurance</Button>
        </DialogTrigger>
        <DialogContent className="sm:max-w-[580px]">
          <AlertDialogHeader>
            <DialogTitle className="text-green-500">
              Add new insurance
            </DialogTitle>
            <DialogDescription>
              Add new insurance info here. Click save when you&apos;re done.
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
                  name="name"
                  id="name"
                  value={name}
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
              <Label htmlFor="lastname" className="text-right">
                Description
              </Label>
              <div className="col-span-3">
                <Input
                  name="description"
                  id="description"
                  value={description}
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
              <Label htmlFor="userId" className="text-right">
                Rate
              </Label>
              <div className="col-span-3">
                <Input
                  name="rate"
                  id="rate"
                  value={rate}
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
            <Button type="submit" onClick={handleSubmitCreate}>
              Create
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </>
  );
}

export default AddNewInsuranceDialog;
