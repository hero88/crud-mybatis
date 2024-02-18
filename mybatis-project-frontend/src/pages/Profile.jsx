import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import { useEffect, useState } from "react";
import { useToast } from "@/components/ui/use-toast";
import { ToastAction } from "@/components/ui/toast";
import { changeUserPassword } from "@/services/UserAPI";
import { Dialog } from "@radix-ui/react-dialog";
import {
  DialogClose,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { AlertDialogHeader } from "@/components/ui/alert-dialog";
import { Label } from "@/components/ui/label";
import PasswordInput from "@/components/shared/PasswordInput";
import UserProfileForm from "@/components/shared/UserProfileForm";

function Profile() {
  const { toast } = useToast();

  const [user, setUser] = useState({
    name: "",
    email: "",
    phoneNumber: "",
    age: "",
    gender: true,
    address: "",
  });

  const [passwordForm, setPasswordForm] = useState({
    currentPassword: "",
    newPassword: "",
    confirmationPassword: "",
  });

  // const { name, email, phoneNumber, age, gender, address } = user;

  const { currentPassword, newPassword, confirmationPassword } = passwordForm;

  useEffect(() => {
    setUser(JSON.parse(localStorage.getItem("profile")));

    console.log(JSON.parse(localStorage.getItem("profile")));
  }, []);

  const handlePasswordFieldChange = (e) => {
    setPasswordForm({ ...passwordForm, [e.target.name]: e.target.value });
  };

  const handleChangePassword = async () => {
    if (
      currentPassword === "" ||
      newPassword === "" ||
      confirmationPassword === ""
    ) {
      toast({
        variant: "destructive",
        title: "Uh oh! Something went wrong.",
        description: "Please don't let any field empty.",
        action: <ToastAction altText="Try again">Try again</ToastAction>,
      });
      return;
    } else if (newPassword !== confirmationPassword) {
      toast({
        variant: "destructive",
        title: "Uh oh! Something went wrong.",
        description: "Your confirm password not match the new password.",
        action: <ToastAction altText="Try again">Try again</ToastAction>,
      });
      return;
    } else {
      let newPasswordUpdated = {
        currentPassword,
        newPassword,
        confirmationPassword,
      };

      console.log(newPasswordUpdated);

      try {
        const { data: response } = await changeUserPassword(newPasswordUpdated);

        if (response.code === 200) {
          toast({
            title: "Update user successfully.",
            description: "Your profile has been updated",
            action: <ToastAction altText="Nice">Nice</ToastAction>,
          });
        }
      } catch (error) {
        console.log(error);
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
    <div className="w-full px-8">
      <h2 className="pb-4 text-2xl font-bold">Profile</h2>
      <hr />
      <h2 className="pb-4 text-xl font-semibold py-5">About me</h2>
      <hr />
      <div className="w-7/12">
        <div className="flex gap-8 py-6 justify-between">
          <div>
            <Avatar className="h-32 w-32 cursor-pointer">
              <AvatarImage src="" alt="@shadcn" />
              <AvatarFallback className="font-semibold text-4xl">
                {user?.name.charAt(0).toUpperCase()}
              </AvatarFallback>
            </Avatar>
          </div>
          <Dialog>
            <DialogTrigger asChild>
              <Button>Change password</Button>
            </DialogTrigger>
            <DialogContent className="sm:max-w-[560px]">
              <AlertDialogHeader>
                <DialogTitle>Change password</DialogTitle>
                <DialogDescription>
                  Make changes to your password here. Click save when
                  you&apos;re done.
                </DialogDescription>
              </AlertDialogHeader>
              <div className="grid gap-4 py-4">
                <div className="grid grid-cols-4 items-center gap-4">
                  <Label htmlFor="current-password" className="text-right">
                    Current password
                  </Label>

                  <div className="col-span-3">
                    <PasswordInput
                      id="current-password"
                      value={currentPassword}
                      name="currentPassword"
                      onChange={(e) => handlePasswordFieldChange(e)}
                    />
                  </div>
                </div>
                <div className="grid grid-cols-4 items-center gap-4">
                  <Label htmlFor="new-password" className="text-right">
                    New passowrd
                  </Label>
                  <div className="col-span-3">
                    <PasswordInput
                      id="new-password"
                      value={newPassword}
                      name="newPassword"
                      onChange={(e) => handlePasswordFieldChange(e)}
                    />
                  </div>
                </div>
                <div className="grid grid-cols-4 items-center gap-4">
                  <Label htmlFor="confirm-password" className="text-right">
                    Confirm password
                  </Label>
                  <div className="col-span-3">
                    <PasswordInput
                      id="confirm-password"
                      value={confirmationPassword}
                      name="confirmationPassword"
                      onChange={(e) => handlePasswordFieldChange(e)}
                    />
                  </div>
                </div>
              </div>
              <DialogFooter>
                <DialogClose asChild>
                  <Button type="submit" onClick={handleChangePassword}>
                    Save changes
                  </Button>
                </DialogClose>
              </DialogFooter>
            </DialogContent>
          </Dialog>
        </div>
        <UserProfileForm user={user} setUser={setUser} />
      </div>
    </div>
  );
}

export default Profile;
