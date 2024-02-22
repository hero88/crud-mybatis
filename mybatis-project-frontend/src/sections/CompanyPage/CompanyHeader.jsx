import AvatarTray from "@/components/shared/AvatarTray";
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

function CompanyHeader() {
  const navigate = useNavigate();
  const [user, setUser] = useState({});

  useEffect(() => {
    setUser(JSON.parse(localStorage.getItem("profile")));
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("profile");
    localStorage.removeItem("token");
    localStorage.removeItem("auth");
    navigate("/sign-in");
  };

  return (
    <div className="w-full">
      <div className="px-6 py-4 flex justify-between">
        <div className="flex items-center space-x-8">
          <Link to={"/company"}>
            <img src="/src/assets/logo.svg" alt="logo" />
          </Link>
          {/* Employees */}
          <div>
            <Link to="/company/employees" className="font-semibold hover:text-blue-500">
              Employees
            </Link>
          </div>
          {/* Payroll */}
          <div>
            <Link to="/" className="font-semibold hover:text-blue-500">
              Payroll
            </Link>
          </div>
          {/* TimeTracking */}
          <div>
            <Link to="/" className="font-semibold hover:text-blue-500">
              TimeTracking
            </Link>
          </div>
        </div>
        <div className="flex items-center justify-end">
          <AvatarTray user={user} handleLogout={handleLogout} />
        </div>
      </div>
      <hr />
    </div>
  );
}

export default CompanyHeader;
