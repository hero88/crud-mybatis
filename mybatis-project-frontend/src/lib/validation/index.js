import * as z from "zod";

export const SignInValidation = z.object({
  email: z.string().min(1, {
    message: "Email is missing",
  }),
  password: z.string().min(1, {
    message: "Password is missing",
  }),
});

export const SignUpValidation = z.object({
  // username: z
  //   .string()
  //   .min(4, { message: "Username must be at least 4 characters" }),
  name: z.string().min(4, { message: "Name must be at least 4 characters" }),
  email: z
    .string()
    .min(10, { message: "Email must be at least 10 characters" }),
  password: z
    .string()
    .min(6, { message: "Password must  be at least 6 characters" }),
  confirmPassword: z.string().min(1, { message: "Do not leave this empty" }),
});
