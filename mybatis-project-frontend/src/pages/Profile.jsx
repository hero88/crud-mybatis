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
    firstname: "",
    email: "",
    phoneNumber: "",
    age: "",
    gender: "",
    address: "",
  });

  const { firstname, email, phoneNumber, age, gender, address } = user;

  const form = useForm({
    resolver: zodResolver(formSchema),
    defaultValues: {
      // username: "",
      email: email,
      fullname: firstname,
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

  const onSubmit = async () => {
    const { email, firstname, phoneNumber, address, age, gender } = user;

    let updatedUser = {
      // username,
      email,
      firstname,
      phoneNumber,
      address,
      age: parseInt(age),
      gender,
    };

    console.log(updatedUser);
    // const { data: response } = await updatedUser(updatedUser);

    if (user) {
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
              <AvatarFallback className="font-semibold">
                {user?.firstname}
              </AvatarFallback>
            </Avatar>
          </div>
          <Button
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
          </Button>
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
                      value={firstname}
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
