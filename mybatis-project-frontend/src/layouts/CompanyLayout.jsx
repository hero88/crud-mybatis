import { Button } from "@/components/ui/button";
import CompanyHeader from "@/sections/CompanyPage/CompanyHeader";
import { useEffect, useState } from "react";
import { CiBitcoin, CiUser } from "react-icons/ci";
import { HiOutlineUsers } from "react-icons/hi";
import { useLocation, useNavigate } from "react-router-dom";

function CompanyLayout({ children }) {
  const navigate = useNavigate();
  const location = useLocation();
  const [selectedTab, setSelectedTab] = useState("detail");

  useEffect(() => {
    if (location.pathname.includes("detail")) {
      setSelectedTab("detail");
    } else if (location.pathname.includes("list-user")) {
      setSelectedTab("list-user");
    } else if (location.pathname.includes("coins")) {
      setSelectedTab("coins");
    }
  }, [location.pathname]);

  return (
    <div className="w-screen h-screen">
      <CompanyHeader />
      {/* <div className="flex px-2">
        <div className="w-2/12 text-lg">
          <aside className="flex flex-col gap-1 border-r-2">
            <Button
              onClick={() => navigate("../profile/detail/1")}
              className={`w-48 hover:bg-gray-200 h-12 text-md font-normal flex justify-start text-base rounded-lg ${
                selectedTab === "detail" && "bg-gray-200 font-semibold"
              }`}
              variant="ghost"
            >
              <CiUser className="text-2xl" strokeWidth={0.7} />
              <p className="ml-2">Profile</p>
            </Button>
            <Button
              onClick={() => navigate("../profile/list-user")}
              className={`w-48 hover:bg-gray-200 h-12 text-md font-normal flex justify-start text-base rounded-lg ${
                selectedTab === "list-user" && "bg-gray-200 font-semibold"
              }`}
              variant="ghost"
            >
              <HiOutlineUsers className="text-2xl" strokeWidth={1.8} />
              <p className="ml-2">List user</p>
            </Button>

            <Button
              onClick={() => navigate("../profile/coins")}
              className={`w-48 hover:bg-gray-200 h-12 text-md font-normal flex justify-start text-base rounded-lg ${
                selectedTab === "coins" && "bg-gray-200 font-semibold"
              }`}
              variant="ghost"
            >
              <CiBitcoin className="text-2xl" strokeWidth={0.7} />
              <p className="ml-2">Dashboard</p>
            </Button>
          </aside>
        </div>
      </div> */}
      {children}
    </div>
  );
}

export default CompanyLayout;
