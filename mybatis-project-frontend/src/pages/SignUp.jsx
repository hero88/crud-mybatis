import PasswordInput from "@/components/shared/PasswordInput";
import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { SignInValidation } from "@/lib/validation";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { Link } from "react-router-dom";

const SignUp = () => {
  const form = useForm({
    resolver: zodResolver(SignInValidation),
    defaultValues: {
      username: "",
      email: "",
      fullName: "",
      gender: "",
      age: 0,
      password: "",
      confirmPassword: "",
    },
  });

  const onSubmit = (values) => {
    // Do something with the form values.
    // ✅ This will be type-safe and validated.
    console.log(values);
  };

  return (
    <>
      <div className="flex justify-center">
        <div className="flex flex-col md:flex-row xl:flex-row justify-center p-8 xl:space-x-20">
          {/* Left site */}
          <div className="flex flex-1 flex-col justify-center xl:max-w-[450px] md:max-w-[450px] pt-8">
            <div className="flex flex-col flex-1 text-center">
              <h2 className="text-black font-semibold text-5xl">
                Create your account
              </h2>

              <p className="text-black mt-6">
                Simplify digital coin and NFTs content with <b> Our App</b>. Get
                started for free
              </p>
            </div>

            <div>
              <Form {...form}>
                <form onSubmit={form.handleSubmit(onSubmit)} className="mt-12">
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
                  <FormField
                    control={form.control}
                    name="username"
                    render={({ field }) => (
                      <FormItem>
                        <FormControl>
                          <Input
                            placeholder="Username"
                            {...field}
                            className="rounded-3xl px-8 h-12 mt-3 border-gray-400"
                          />
                        </FormControl>
                        <FormMessage />
                      </FormItem>
                    )}
                  />
                  <FormField
                    control={form.control}
                    name="username"
                    render={({ field }) => (
                      <FormItem>
                        <FormControl>
                          <Input
                            placeholder="Username"
                            {...field}
                            className="rounded-3xl px-8 h-12 mt-3 border-gray-400"
                          />
                        </FormControl>
                        <FormMessage />
                      </FormItem>
                    )}
                  />
                  <FormField
                    control={form.control}
                    name="username"
                    render={({ field }) => (
                      <FormItem>
                        <FormControl>
                          <Input
                            placeholder="Username"
                            {...field}
                            className="rounded-3xl px-8 h-12 mt-3 border-gray-400"
                          />
                        </FormControl>
                        <FormMessage />
                      </FormItem>
                    )}
                  />

                  <Button
                    type="submit"
                    className="mt-7 flex h-12 w-full rounded-3xl bg-black hover:bg-[#554739]"
                  >
                    Login
                  </Button>
                </form>
              </Form>
            </div>

            <div className="flex justify-center items-center mt-9">
              <div className="w-1/3 border-t-2 border-[#2d241d]"></div>

              <span className="px-3 text-black font-medium">
                or continue with
              </span>

              <div className="w-1/3 border-t-2 border-[#2d241d]"></div>
            </div>

            {/* Choose Register */}
            <div className="flex flex-1 justify-center mt-24 pb-6">
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
      </div>
    </>
  );
};

export default SignUp;
