import { useRef } from "react";
import Autoplay from "embla-carousel-autoplay";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
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
import { GoogleAuthProvider, getAuth, signInWithPopup } from "firebase/auth";
import { app } from "@/config/firebase.config";
import PasswordInput from "@/components/shared/PasswordInput";
import { SignInValidation } from "@/lib/validation";

const imagesInit = [
  "/src/assets/side-img-1.jpg",
  "/src/assets/side-img-2.png",
  "/src/assets/side-img-3.png",
];

const SignIn = () => {
  const navigate = useNavigate();

  const form = useForm({
    resolver: zodResolver(SignInValidation),
    defaultValues: {
      username: "",
      password: "",
    },
  });

  const plugin = useRef(Autoplay({ delay: 3000, stopOnInteraction: true }));

  const firebaseAuth = getAuth(app);
  const provider = new GoogleAuthProvider();

  const handleLoginWithGoogle = async () => {
    await signInWithPopup(firebaseAuth, provider).then((userCred) => {
      if (userCred) {
        localStorage.setItem("auth", "true");

        firebaseAuth.onAuthStateChanged((userCred) => {
          if (userCred) {
            userCred.getIdToken().then((token) => {
              console.log(token);
            });
            // navigate("/", { replace: true });
          } else {
            navigate("/login");
          }
        });
      }
    });
  };

  const onSubmit = (values) => {
    // Do something with the form values.
    // ✅ This will be type-safe and validated.
    console.log(values);
  };

  return (
    <>
      <div className="flex justify-center">
        <div className="flex flex-col md:flex-row xl:flex-row justify-center p-8 xl:space-x-20 md:space-x-16">
          {/* Left site */}
          <div className="flex flex-1 flex-col justify-center xl:max-w-[430px] md:max-w-[430px] pt-8">
            <div className="flex flex-col flex-1 text-center">
              <h2 className="text-black font-semibold text-5xl">
                Welcome back!
              </h2>

              <p className="text-black mt-6">
                Simplify digital coin and NFTs content with <b> Our App</b>. Get
                started for free
              </p>
            </div>

            <div>
              <Form {...form}>
                <form onSubmit={form.handleSubmit(onSubmit)} className="mt-12">
                  <FormField
                    control={form.control}
                    name="username"
                    render={({ field }) => (
                      <FormItem>
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
                  <FormField
                    control={form.control}
                    name="password"
                    render={({ field }) => (
                      <FormItem>
                        <FormControl>
                          {/* <Input
                            placeholder="Password"
                            {...field}
                            className="rounded-3xl px-8 h-12 mt-3 border-gray-400"
                            type="password"
                          /> */}
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
              <div className="mx-3 w-14 h-14 flex items-center justify-center bg-black rounded-full cursor-pointer hover:bg-[#554739]">
                <FaFacebook className="text-white text-2xl" />
              </div>
            </div>

            {/* Choose Register */}
            <div className="flex flex-1 justify-center mt-24 pb-6">
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
              className="flex items-center md:w-[500px] xl:w-[570px]"
              onMouseEnter={plugin.current.stop}
              onMouseLeave={plugin.current.play}
            >
              <CarouselContent>
                {imagesInit.map((item, index) => (
                  <CarouselItem key={index}>
                    <div>
                      <Card className="rounded-2xl">
                        <CardContent className="h-[700px] p-0 md:w-[500px] xl:w-[570px]">
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
