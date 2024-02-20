import { Button } from "@/components/ui/button";
import { DefaultLayout } from ".";
import { useLocation, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { CiUser, CiBitcoin } from "react-icons/ci";
import { HiOutlineUsers } from "react-icons/hi";

function ProfileLayout({ children }) {
  // let isAdmin = window.localStorage.getItem("isAdmin");
  let isAdmin = true;
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
    <DefaultLayout>
      <div id="profile" className="flex justify-between min-h-svh py-4">
        {/* Sidebar */}
        <div className="leftSide xl:w-2/12 md:w-5/12 px-12 text-lg">
          <aside className="flex flex-col gap-1 ">
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
            {isAdmin && (
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
            )}
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
        {children}
        {/* <div className="rightSide md:w-0 xl:w-3/12"></div> */}
      </div>
    </DefaultLayout>
  );
}

export default ProfileLayout;
