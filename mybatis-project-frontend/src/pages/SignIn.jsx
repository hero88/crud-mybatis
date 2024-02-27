import { useEffect, useRef, useState } from "react";
import Autoplay from "embla-carousel-autoplay";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
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
import { FaGoogle, FaApple, FaFacebook } from "react-icons/fa";

import {
  Carousel,
  CarouselContent,
  CarouselItem,
} from "@/components/ui/carousel";
import { Card, CardContent } from "@/components/ui/card";
import { Link, useNavigate } from "react-router-dom";
import {
  FacebookAuthProvider,
  GoogleAuthProvider,
  getAuth,
  signInWithPopup,
} from "firebase/auth";
import { app } from "@/config/firebase.config";
import PasswordInput from "@/components/shared/PasswordInput";
import { SignInValidation } from "@/lib/validation";
import { login } from "@/services/UserAPI";
import { useToast } from "@/components/ui/use-toast";
import { ToastAction } from "@/components/ui/toast";

const imagesInit = [
  "/src/assets/side-img-1.jpg",
  "/src/assets/side-img-2.png",
  "/src/assets/side-img-3.png",
];

const SignIn = () => {
  const { toast } = useToast();
  const navigate = useNavigate();

  const form = useForm({
    resolver: zodResolver(SignInValidation),
    defaultValues: {
      email: "",
      password: "",
    },
  });

  const [error, setError] = useState("");

  const plugin = useRef(Autoplay({ delay: 3000, stopOnInteraction: true }));

  const firebaseAuth = getAuth(app);
  const googleProvider = new GoogleAuthProvider();
  const facebookProvider = new FacebookAuthProvider();

  useEffect(() => {
    if (localStorage.getItem("token")) {
      navigate("/");
    }
  }, []);

  const handleLoginWithGoogle = async () => {
    await signInWithPopup(firebaseAuth, googleProvider).then((userCred) => {
      if (userCred) {
        firebaseAuth.onAuthStateChanged((userCred) => {
          if (userCred) {
            userCred.getIdToken().then((token) => {
              const userProfile = {
                address: "address",
                age: 23,
                createdAt: 1707961060,
                email: userCred.email,
                gender: true,
                id: 1,
                isActive: true,
                name: userCred.displayName,
                password: null,
                phoneNumber: userCred.phoneNumber,
                updatedAt: 1707961060,
              };

              localStorage.setItem("token", JSON.stringify(token));
              localStorage.setItem("profile", JSON.stringify(userProfile));
            });

            navigate("/", { replace: true });
          } else {
            toast({
              variant: "destructive",
              title: "Uh oh! Something went wrong.",
              description: "You have apply a wrong email or password.",
              action: <ToastAction altText="Try again">Try again</ToastAction>,
            });
            navigate("/login");
          }
        });
      }
    });
  };

  const handleLoginWithFacebook = async () => {
    await signInWithPopup(firebaseAuth, facebookProvider).then((userCred) => {
      if (userCred) {
        firebaseAuth.onAuthStateChanged((userCred) => {
          if (userCred) {
            userCred.getIdToken().then((token) => {
              const userProfile = {
                address: "address",
                age: 23,
                createdAt: 1707961060,
                email: userCred.email,
                gender: true,
                id: 1,
                isActive: true,
                name: userCred.displayName,
                password: null,
                phoneNumber: userCred.phoneNumber,
                updatedAt: 1707961060,
              };

              localStorage.setItem("token", JSON.stringify(token));
              localStorage.setItem("profile", JSON.stringify(userProfile));
            });
            navigate("/", { replace: true });
          } else {
            toast({
              variant: "destructive",
              title: "Uh oh! Something went wrong.",
              description: "You have apply a wrong email or password.",
              action: <ToastAction altText="Try again">Try again</ToastAction>,
            });
            navigate("/login");
          }
        });
      }
    });
  };

  const handleLoginNormally = async (userForm) => {
    const { data: response } = await login(userForm);

    console.log(response);

    if (response.data) {
      localStorage.setItem("token", JSON.stringify(response.data.accessToken));
      localStorage.setItem("profile", JSON.stringify(response.data.user));
      navigate("/", { replace: true });
    } else {
      toast({
        variant: "destructive",
        title: "Uh oh! Something went wrong.",
        description: "You have apply a wrong email or password.",
        action: <ToastAction altText="Try again">Try again</ToastAction>,
      });
      navigate("/sign-in");
      setError("Your username or password is incorrect!");
    }
  };

  const onSubmit = (values) => {
    handleLoginNormally(values);
  };

  return (
    <>
      <div className="flex justify-center">
        <div className="flex flex-col md:flex-row xl:flex-row justify-center mt-8 xl:space-x-20 md:space-x-16">
          {/* Left site */}
          <div className="flex flex-1 flex-col justify-center xl:max-w-[430px] md:max-w-[430px] pt-6">
            <div className="flex flex-col flex-1 text-center">
              <h2 className="text-black font-semibold text-5xl">
                Welcome back!
              </h2>

              <p className="text-black mt-4">
                Simplify digital coin and NFTs content with <b> Our App</b>. Get
                started for free
              </p>
            </div>

            <div>
              <Form {...form}>
                <form onSubmit={form.handleSubmit(onSubmit)} className="mt-10">
                  <FormField
                    control={form.control}
                    name="email"
                    render={({ field }) => (
                      <FormItem>
                        {/* {error && (
                          <FormLabel className="font-semibold text-red-500">
                            {error}
                          </FormLabel>
                        )} */}
                        <FormControl>
                          <Input
                            placeholder="Email"
                            type="email"
                            {...field}
                            className="rounded-3xl px-8 h-12 border-gray-400"
                          />
                        </FormControl>
                        <FormMessage />
                      </FormItem>
                    )}
                  />
                  <FormField
                    control={form.control}
                    name="password"
                    render={({ field }) => (
                      <FormItem>
                        <FormControl>
                          <PasswordInput
                            {...field}
                            placeholder="Password"
                            className="rounded-3xl px-8 h-12 mt-3 border-gray-400"
                          />
                        </FormControl>
                        <FormMessage />
                      </FormItem>
                    )}
                  />
                  <Link to={"/forgot-password"}>
                    <p className="flex justify-end font-semibold text-sm mt-3 hover:text-[#554739]">
                      Forgot Password?
                    </p>
                  </Link>
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

            {/* Other Sign In Method */}
            <div className="flex justify-center items-center mt-7">
              <div
                onClick={handleLoginWithGoogle}
                className="mx-3 w-14 h-14 flex items-center justify-center bg-black rounded-full cursor-pointer hover:bg-[#554739]"
              >
                <FaGoogle className="text-white text-2xl" />
              </div>
              <div className="mx-3 w-14 h-14 flex items-center justify-center bg-black rounded-full cursor-pointer hover:bg-[#554739]">
                <FaApple className="text-white text-2xl" />
              </div>
              <div
                onClick={handleLoginWithFacebook}
                className="mx-3 w-14 h-14 flex items-center justify-center bg-black rounded-full cursor-pointer hover:bg-[#554739]"
              >
                <FaFacebook className="text-white text-2xl" />
              </div>
            </div>

            {/* Choose Register */}
            <div className="flex flex-1 justify-center mt-20">
              <span className="font-medium">
                Not a member?{" "}
                <Link to={"/sign-up"}>
                  <span className="text-[#6e4e3a] hover:text-white">
                    Register now
                  </span>
                </Link>
              </span>
            </div>
          </div>
          {/* Right site */}
          <div className="w-1/2 xl:flex md:flex sm:hidden">
            <Carousel
              plugins={[plugin.current]}
              className="flex items-center md:w-[500px] xl:w-[600px]"
              onMouseEnter={plugin.current.stop}
              onMouseLeave={plugin.current.play}
            >
              <CarouselContent>
                {imagesInit.map((item, index) => (
                  <CarouselItem key={index}>
                    <div>
                      <Card className="rounded-2xl">
                        <CardContent className="h-[700px] p-0 md:w-[500px] xl:w-[600px]">
                          <img
                            src={item}
                            className="w-full h-full rounded-2xl"
                            alt="side-img"
                          />
                        </CardContent>
                      </Card>
                    </div>
                  </CarouselItem>
                ))}
              </CarouselContent>
              {/* <CarouselPrevious />
              <CarouselNext /> */}
            </Carousel>
          </div>
        </div>
      </div>
    </>
  );
};

export default SignIn;
