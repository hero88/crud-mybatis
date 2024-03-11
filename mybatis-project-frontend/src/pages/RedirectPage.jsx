import { Button } from "@/components/ui/button";
import { useNavigate } from "react-router-dom";

function RedirectPage() {
  const navigate = useNavigate();

  return (
    <div className="bg-white rounded-lg shadow-lg h-[26rem] px-8 py-8 ">
      <h2 className="font-bold text-3xl font-sans text-gray-800 text-center">
        Welcome to crypto app
      </h2>
      <p className="font-semibold text-lg font-sans text-blue-400 mt-3">
        Check your verify mail at your inbox 📬
      </p>
      <p className="font-semibold text-lg font-sans text-blue-400 mt-1">
        Thank you for using our app
      </p>
      <Button className="mt-10" onClick={() => navigate(`/sign-in`)}>
        Go to 
      </Button>

      <Button className="mt-10" onClick={() => navigate(`/sign-in`)}>
        Back to login page
      </Button>

      <div>
        <p className="text-[250px]">📧</p>
      </div>
    </div>
  );
}

export default RedirectPage;
