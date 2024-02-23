import PasswordInput from "@/components/shared/PasswordInput";
import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { SignUpValidation } from "@/lib/validation";
import { register } from "@/services/UserAPI";
import { zodResolver } from "@hookform/resolvers/zod";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { Link, useNavigate } from "react-router-dom";

const SignUp = () => {
  const [newUser, setNewUser] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const form = useForm({
    resolver: zodResolver(SignUpValidation),
    defaultValues: {
      // username: "",
      name: "",
      email: "",
      password: "",
      confirmPassword: "",
    },
  });

  const onSubmit = async (values) => {
    const { name, email, password, confirmPassword } = values;

    setNewUser({
      name,
      email,
      password,
      confirmPassword,
    });

    if (password === confirmPassword) {
      setIsLoading(true);
      const registedUser = {
        name,
        email,
        password,
        gender: true,
        address: "empty",
        age: 1,
        phoneNumber: "empty",
      };

      const response = await register(registedUser);
      if (response.status === 200) {
        // localStorage.setItem("profile", JSON.stringify(response.data.user));
        // localStorage.setItem(
        //   "token",
        //   JSON.stringify(response.data.accessToken)
        // );
        setIsLoading(false);
        navigate("/verify", { replace: true });
      }
    }
  };

  return (
    <>
      <div className="flex justify-center">
        <div className="flex flex-col justify-center xl:w-[450px] md:w-[450px] pt-8">
          <div className="flex flex-col text-center">
            <h2 className="text-black font-semibold text-5xl">
              Create your account
            </h2>

            <p className="text-black mt-6">
              Simplify digital coin and NFTs content with <b> Our App</b>. Get
              started for free 🪙
            </p>
          </div>

          <div className="flex justify-center">
            <Form {...form}>
              <form
                onSubmit={form.handleSubmit(onSubmit)}
                className="mt-12 flex flex-col justify-center w-[420px] space-y-4"
              >
                {/* Username */}
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
                          className="rounded-3xl px-8 h-12 border-gray-400"
                        />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                /> */}

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
                          className="rounded-3xl px-8 h-12 border-gray-400"
                        />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />

                {/* Email */}
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
                          className="rounded-3xl px-8 h-12 border-gray-400"
                        />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />

                {/* Password */}
                <FormField
                  control={form.control}
                  name="password"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>Password</FormLabel>
                      <FormControl>
                        <PasswordInput
                          {...field}
                          placeholder="Password"
                          className="rounded-3xl px-8 h-12 border-gray-400"
                        />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />

                {/* Confirm Password */}
                <FormField
                  control={form.control}
                  name="confirmPassword"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>Confirm Password</FormLabel>
                      <FormControl>
                        <PasswordInput
                          {...field}
                          placeholder="Confirm Password"
                          className="rounded-3xl px-8 h-12 border-gray-400"
                        />
                      </FormControl>
                      {newUser.password !== newUser.confirmPassword && (
                        <FormDescription className="text-red-600 font-semibold">
                          This not match the above password
                        </FormDescription>
                      )}
                      <FormMessage />
                    </FormItem>
                  )}
                />

                <Button
                  type="submit"
                  className="mt-7 h-12 w-full rounded-3xl bg-black hover:bg-[#554739]"
                >
                  {isLoading ? (
                    <svg
                      aria-hidden="true"
                      className="w-6 h-6 text-gray-200 animate-spin dark:text-gray-600 fill-blue-600"
                      viewBox="0 0 100 101"
                      fill="none"
                      xmlns="http://www.w3.org/2000/svg"
                    >
                      <path
                        d="M100 50.5908C100 78.2051 77.6142 100.591 50 100.591C22.3858 100.591 0 78.2051 0 50.5908C0 22.9766 22.3858 0.59082 50 0.59082C77.6142 0.59082 100 22.9766 100 50.5908ZM9.08144 50.5908C9.08144 73.1895 27.4013 91.5094 50 91.5094C72.5987 91.5094 90.9186 73.1895 90.9186 50.5908C90.9186 27.9921 72.5987 9.67226 50 9.67226C27.4013 9.67226 9.08144 27.9921 9.08144 50.5908Z"
                        fill="currentColor"
                      />
                      <path
                        d="M93.9676 39.0409C96.393 38.4038 97.8624 35.9116 97.0079 33.5539C95.2932 28.8227 92.871 24.3692 89.8167 20.348C85.8452 15.1192 80.8826 10.7238 75.2124 7.41289C69.5422 4.10194 63.2754 1.94025 56.7698 1.05124C51.7666 0.367541 46.6976 0.446843 41.7345 1.27873C39.2613 1.69328 37.813 4.19778 38.4501 6.62326C39.0873 9.04874 41.5694 10.4717 44.0505 10.1071C47.8511 9.54855 51.7191 9.52689 55.5402 10.0491C60.8642 10.7766 65.9928 12.5457 70.6331 15.2552C75.2735 17.9648 79.3347 21.5619 82.5849 25.841C84.9175 28.9121 86.7997 32.2913 88.1811 35.8758C89.083 38.2158 91.5421 39.6781 93.9676 39.0409Z"
                        fill="currentFill"
                      />
                    </svg>
                  ) : (
                    "Sign up"
                  )}
                </Button>
              </form>
            </Form>
          </div>

          {/* Choose Register */}
          <div className="flex justify-center mt-20 pb-6">
            <span className="font-medium">
              Already have an account?
              <Link to={"/sign-in"}>
                <span className="text-[#6e4e3a] hover:text-white">
                  {" "}
                  Sign in now
                </span>
              </Link>
            </span>
          </div>
        </div>
      </div>
    </>
  );
};

export default SignUp;
