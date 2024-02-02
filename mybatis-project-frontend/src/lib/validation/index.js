import * as z from "zod";

export const SignInValidation = z.object({
  username: z.string().min(2, {
    message: "Username must be at least 2 characters.",
  }),
  password: z.string().min(2, {
    message: "Password must be at least 2 characters.",
  }),
});

export const SignUpValidation = z.object({
  email: z.string().min(10, { message: "Email must be at least 2 characters" }),
  password: z
    .string()
    .min(8, { message: "Password must  be at least 8 characters" }),
});
