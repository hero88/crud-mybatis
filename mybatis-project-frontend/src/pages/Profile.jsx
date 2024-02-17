import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { useEffect, useState } from "react";
import { useToast } from "@/components/ui/use-toast";
import { ToastAction } from "@/components/ui/toast";
import { getUserById, updateUser } from "@/services/UserAPI";
import axios from "axios";
import { Dialog } from "@radix-ui/react-dialog";
import {
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { AlertDialogHeader } from "@/components/ui/alert-dialog";
import { Label } from "@/components/ui/label";

function Profile() {
  const { toast } = useToast();
  const formSchema = z.object({
    // username: z.string(),
    // email: z.string().min(1),
    // name: z.string().min(1),
    // phone: z.string().min(1),
    // address: z.string().min(1),
    // age: z.number(),
    // gender: z.string().min(1),
  });

  const [user, setUser] = useState({
    name: "",
    email: "",
    phoneNumber: "",
    age: "",
    gender: "",
    address: "",
  });

  const { name, email, phoneNumber, age, gender, address } = user;

  const form = useForm({
    resolver: zodResolver(formSchema),
    defaultValues: {
      // username: "",
      email: email,
      name: name,
      phone: phoneNumber,
      address: address,
      age: age,
      gender: gender,
    },
  });

  useEffect(() => {
    setUser(JSON.parse(localStorage.getItem("profile")));

    console.log(JSON.parse(localStorage.getItem("profile")));
  }, []);

  const handleFieldChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleUpdateUser = async () => {
    const { id, email, name, phoneNumber, address, age, gender } = user;

    let updatedUser = {
      // username,
      id,
      name,
      email,
      phoneNumber,
      address,
      age: parseInt(age),
      gender: true,
    };

    console.log(updatedUser);
    const { data: response } = await axios.put(
      `http://localhost:5555/api/users/updateUser`,
      updatedUser
    );

    if (response.data) {
      const { data: newUserResponse } = await getUserById(user.id);

      console.log(newUserResponse);

      localStorage.setItem("profile", JSON.stringify(newUserResponse.data));

      setUser(newUserResponse.data);

      toast({
        title: "Update user successfully.",
        description: "Your profile has been updated",
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
  };

  const onSubmit = () => {
    handleUpdateUser();
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
          {/* <Button
            className=""
            onClick={() => {
              toast({
                variant: "destructive",
                title: "Uh oh! Something went wrong.",
                description: "There was a problem with updating.",
                action: (
                  <ToastAction altText="Try again">Try again</ToastAction>
                ),
              });
            }}
          >
            Change password
          </Button> */}
          <Dialog>
            <DialogTrigger asChild>
              <Button variant="outline">Edit Profile</Button>
            </DialogTrigger>
            <DialogContent className="sm:max-w-[425px]">
              <AlertDialogHeader>
                <DialogTitle>Edit profile</DialogTitle>
                <DialogDescription>
                  Make changes to your profile here. Click save when you're
                  done.
                </DialogDescription>
              </AlertDialogHeader>
              <div className="grid gap-4 py-4">
                <div className="grid grid-cols-4 items-center gap-4">
                  <Label htmlFor="name" className="text-right">
                    Name
                  </Label>
                  <Input
                    id="name"
                    value="Pedro Duarte"
                    className="col-span-3"
                  />
                </div>
                <div className="grid grid-cols-4 items-center gap-4">
                  <Label htmlFor="username" className="text-right">
                    Username
                  </Label>
                  <Input
                    id="username"
                    value="@peduarte"
                    className="col-span-3"
                  />
                </div>
              </div>
              <DialogFooter>
                <Button type="submit">Save changes</Button>
              </DialogFooter>
            </DialogContent>
          </Dialog>
        </div>
        <Form {...form}>
          <form
            onSubmit={form.handleSubmit(onSubmit)}
            className="mt-8 flex flex-col gap-4 "
          >
            {/* <FormField
              control={form.control}
              name="username"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Username</FormLabel>
                  <FormControl>
                    <Input
                      placeholder="Username"
                      {...field}
                      className="px-6 h-12 border-gray-400"
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            /> */}
            <FormField
              control={form.control}
              name="email"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Email</FormLabel>
                  <FormControl>
                    <Input
                      placeholder="Email"
                      {...field}
                      className="px-6 h-12 border-gray-400"
                      name="email"
                      onChange={(e) => handleFieldChange(e)}
                      value={email}
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="name"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Name</FormLabel>
                  <FormControl>
                    <Input
                      placeholder="Name"
                      {...field}
                      className="px-6 h-12 border-gray-400"
                      name="name"
                      onChange={(e) => handleFieldChange(e)}
                      value={name}
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="phone"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Phone number</FormLabel>
                  <FormControl>
                    <Input
                      placeholder="Phone number"
                      {...field}
                      className="px-6 h-12 border-gray-400"
                      name="phoneNumber"
                      onChange={(e) => handleFieldChange(e)}
                      value={phoneNumber}
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="address"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Address</FormLabel>
                  <FormControl>
                    <Input
                      placeholder="Address"
                      {...field}
                      className="px-6 h-12 border-gray-400"
                      name="address"
                      onChange={(e) => handleFieldChange(e)}
                      value={address}
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />

            <FormField
              control={form.control}
              name="age"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Age (max is 90)</FormLabel>
                  <FormControl>
                    <Input
                      placeholder="Age"
                      {...field}
                      type="number"
                      className="px-6 h-12 border-gray-400"
                      name="age"
                      onChange={(e) => handleFieldChange(e)}
                      value={age}
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />

            <FormField
              control={form.control}
              name="gender"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Gender:</FormLabel>
                  <FormControl>
                    <RadioGroup
                      onValueChange={field.onChange}
                      defaultValue={gender ? "male" : "female"}
                      className="flex space-x-2"
                    >
                      <FormItem className="flex items-center space-x-3 space-y-0">
                        <FormControl>
                          <RadioGroupItem value="male" id="male" />
                        </FormControl>
                        <FormLabel className="font-normal">Male</FormLabel>
                      </FormItem>
                      <FormItem className="flex items-center space-x-3 space-y-0">
                        <FormControl>
                          <RadioGroupItem value="female" id="female" />
                        </FormControl>
                        <FormLabel className="font-normal">Female</FormLabel>
                      </FormItem>
                    </RadioGroup>
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />

            <Button
              type="submit"
              className="mt-7 flex h-12 w-full rounded-3xl bg-black hover:bg-[#554739]"
            >
              Save
            </Button>
          </form>
        </Form>
      </div>
    </div>
  );
}

export default Profile;
