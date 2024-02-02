const AuthLayout = ({ children }) => {
  return (
    <section className="w-screen min-h-screen bg-gradient-to-b from-[#f0eee5] to-[#dfdac9] bg-fixed">
      {children}
    </section>
  );
};

export default AuthLayout;
