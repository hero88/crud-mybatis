import { Button } from "@/components/ui/button";
import { DefaultLayout } from ".";
import { useNavigate } from "react-router-dom";

function ProfileLayout({ children }) {
      // let isAdmin = window.localStorage.getItem("isAdmin");
  let isAdmin = true;
  let navigate = useNavigate();

  return (
    <DefaultLayout>
      <div id="profile" className="flex justify-between min-h-svh">
        <div className="leftSide bg-sky-50 w-2/12 px-12 text-lg">
          <aside className="flex flex-col gap-1 ">
            <Button onClick={() => navigate("../profile/detail/1")}>My profile</Button>
            {isAdmin && (
              <Button onClick={() => navigate("../profile/list-user")}>
                List User
              </Button>
            )}
          </aside>
        </div>
        {children}
        <div className="rightSide w-3/12"></div>
      </div>
    </DefaultLayout>
  );
}

export default ProfileLayout;
