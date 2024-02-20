import { Button } from "@/components/ui/button";
import { useNavigate } from "react-router-dom";

function VerifyEmail() {
  const navigate = useNavigate();

  return (
    <div className="bg-white rounded-lg shadow-lg h-96 px-8 py-8 flex justify-between items-center">
      <div className="">
        <h2 className="font-bold text-3xl font-sans text-gray-800">
          Verify Email
        </h2>
        <p className="font-semibold text-lg font-sans text-blue-400 mt-3">
          Check your verify mail at your inbox 📬
        </p>
        <p className="font-semibold text-lg font-sans text-blue-400 mt-1">
          Thank you for using our app
        </p>
        <Button className="mt-10" onClick={() => navigate(`/sign-in`)}>
          Back to login page
        </Button>
      </div>
      <div>
        <p className="text-[250px]">📧</p>
      </div>
    </div>
  );
}

export default VerifyEmail;
