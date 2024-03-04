import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTrigger,
} from "@/components/ui/alert-dialog";
import { Button } from "@/components/ui/button";
import { ToastAction } from "@/components/ui/toast";
import { useToast } from "@/components/ui/use-toast";
import { doDeleteInsurance } from "@/services/InsuranceAPI";
import { AlertDialogTitle } from "@radix-ui/react-alert-dialog";
import { Trash } from "lucide-react";

function DelInsuranceDialog({ insurance, loadInsurancesData }) {
  const { toast } = useToast();

  const handleDeleteInsurance = async () => {
    const { data: response } = await doDeleteInsurance(insurance.id);

    console.log(response);

    if (response.code === 200) {
      loadInsurancesData();
      toast({
        title: "Delete employee successfully!",
        description: "Employee list has been changed.",
        action: <ToastAction altText="Nice">Nice</ToastAction>,
      });
    } else {
      toast({
        variant: "destructive",
        title: "Uh oh! Something went wrong.",
        description: "There was a problem with deleting",
        action: <ToastAction altText="Try again">Try again</ToastAction>,
      });
    }
  };

  return (
    <>
      <AlertDialog>
        <AlertDialogTrigger asChild>
          <Button className="w-5 h-5 p-0 bg-transparent hover:bg-transparent">
            <Trash width={17} height={17} className="text-red-500" />
          </Button>
        </AlertDialogTrigger>
        <AlertDialogContent>
          <AlertDialogHeader>
            <AlertDialogTitle>Are you absolutely sure?</AlertDialogTitle>
            <AlertDialogDescription>
              This action cannot be undone. This will permanently delete this
              insurance info and remove this insurance data from servers.
            </AlertDialogDescription>
          </AlertDialogHeader>
          <AlertDialogFooter>
            <AlertDialogCancel>Cancel</AlertDialogCancel>
            <AlertDialogAction onClick={handleDeleteInsurance}>
              Continue
            </AlertDialogAction>
          </AlertDialogFooter>
        </AlertDialogContent>
      </AlertDialog>
    </>
  );
}

export default DelInsuranceDialog;
