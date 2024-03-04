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
import { doDeleteEmployee } from "@/services/EmployeeAPI";
import { AlertDialogTitle } from "@radix-ui/react-alert-dialog";
import { Trash } from "lucide-react";

function DelEmployeeDialog({ employee, loadEmployeesData }) {
  const { toast } = useToast();

  const handleDeleteEmployee = async () => {
    const { data: response } = await doDeleteEmployee(employee.id);

    console.log(response);

    if (response.code === 200) {
      loadEmployeesData();
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
              employee account and remove this employee data from servers.
            </AlertDialogDescription>
          </AlertDialogHeader>
          <AlertDialogFooter>
            <AlertDialogCancel>Cancel</AlertDialogCancel>
            <AlertDialogAction onClick={handleDeleteEmployee}>
              Continue
            </AlertDialogAction>
          </AlertDialogFooter>
        </AlertDialogContent>
      </AlertDialog>
    </>
  );
}

export default DelEmployeeDialog;
