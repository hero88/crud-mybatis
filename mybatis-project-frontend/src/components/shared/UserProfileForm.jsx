import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { RadioGroup, RadioGroupItem } from "../ui/radio-group";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { useToast } from "../ui/use-toast";
import { ToastAction } from "../ui/toast";
import { getUserById, updateUser } from "@/services/UserAPI";
import { Input } from "../ui/input";
import { Button } from "../ui/button";

const UserProfileForm = ({ user, setUser }) => {
  const { toast } = useToast();

  const { name, email, phoneNumber, age, gender, address } = user;

  const formSchema = z.object({
    // username: z.string(),
    // email: z.string().min(1),
    // name: z.string().min(1),
    // phone: z.string().min(1),
    // address: z.string().min(1),
    // age: z.number(),
    // gender: z.string().min(1),
  });

  const form = useForm({
    resolver: zodResolver(formSchema),
    defaultValues: {
      // username: "",
      email: email,
      name: name,
      phone: phoneNumber,
      address: address,
      age: age,
      gender: "",
    },
  });

  const handleUserFieldChange = (e) => {
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
      gender: gender === "male" ? true : false,
    };

    console.log(updatedUser);
    const { data: response } = await updateUser(updatedUser);

    console.log(response);

    if (response.code === 200) {
      const { data: newUserResponse } = await getUserById(user.id);
      localStorage.setItem("profile", JSON.stringify(newUserResponse.data));
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
    <>
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
                    onChange={(e) => handleUserFieldChange(e)}
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
                    onChange={(e) => handleUserFieldChange(e)}
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
                    onChange={(e) => handleUserFieldChange(e)}
                    value={phoneNumber || ""}
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
                    onChange={(e) => handleUserFieldChange(e)}
                    value={address || ""}
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
                    onChange={(e) => handleUserFieldChange(e)}
                    value={age || 1}
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
                    onChange={(e) => handleUserFieldChange(e)}
                    defaultValue={gender ? "male" : "female"}
                    name="gender"
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
    </>
  );
};

export default UserProfileForm;
