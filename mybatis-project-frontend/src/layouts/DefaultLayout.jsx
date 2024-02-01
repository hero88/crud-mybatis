import Footer from "@/components/shared/Footer";
import Header from "@/components/shared/Header";

const DefaultLayout = ({ children }) => {
  return (
    <div className="w-screen">
      <Header />
      <div className="mx-12">{children}</div>
      <Footer />
    </div>
  );
};

export default DefaultLayout;
