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
  const navigate = useNavigate();

  const form = useForm({
    resolver: zodResolver(SignUpValidation),
    defaultValues: {
      username: "",
      email: "",
      password: "",
      confirmPassword: "",
    },
  });

  const onSubmit = async (values) => {
    const { username, email, password, confirmPassword } = values;

    setNewUser({
      username,
      email,
      password,
      confirmPassword,
    });

    if (password === confirmPassword) {
      const response = await register({ email, password });
      console.log(response);
    }

    // if (response.success === true) {
    //   navigate("/", { replace: true });
    // }

    console.log(values);
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
                <FormField
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
                  Sign up
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
