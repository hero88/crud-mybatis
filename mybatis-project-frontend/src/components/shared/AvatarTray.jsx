import { Link, useNavigate } from "react-router-dom";
import { Avatar, AvatarFallback, AvatarImage } from "../ui/avatar";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "../ui/dropdown-menu";
import { Button } from "../ui/button";

function AvatarTray({ user, handleLogout }) {
  const navigate = useNavigate();

  return (
    <>
      <div className="flex space-x-2">
        {user?.id ? (
          <>
            <DropdownMenu>
              <DropdownMenuTrigger>
                <Avatar
                  className="h-9 w-9 cursor-pointer"
                  onClick={() => navigate(`/profile/detail/${user?.id}`)}
                >
                  <AvatarImage src="" alt="A" />
                  <AvatarFallback className="bg-blue-300 font-semibold">
                    {user?.name.charAt(0).toUpperCase()}
                  </AvatarFallback>
                </Avatar>
              </DropdownMenuTrigger>
              <DropdownMenuContent className="mr-9 min-w-56 px-3 pt-3 pb-4">
                <DropdownMenuLabel className="flex items-center space-x-3">
                  <div>
                    <Avatar
                      className="h-12 w-12 cursor-pointer"
                      onClick={() => navigate(`/profile/detail/${user?.id}`)}
                    >
                      <AvatarImage src="" alt="A" />
                      <AvatarFallback>
                        {user?.name.charAt(0).toUpperCase()}
                      </AvatarFallback>
                    </Avatar>
                  </div>
                  <div>
                    <p className="text-base">Hi, {user?.name}</p>
                    <p className="text-sm text-gray-500 font-semibold">
                      {user?.email}
                    </p>
                  </div>
                </DropdownMenuLabel>
                <DropdownMenuSeparator />
                <Link to={`/profile/detail/${user?.id}`}>
                  <DropdownMenuItem className="py-2 font-medium text-sm">
                    Profile
                  </DropdownMenuItem>
                </Link>
                <Link to={`/profile/coins`}>
                  <DropdownMenuItem className="py-2 font-medium text-sm">
                    Dashboard
                  </DropdownMenuItem>
                </Link>
                <Link to={`/company`}>
                  <DropdownMenuItem className="py-2 font-medium text-sm">
                    Company mode
                  </DropdownMenuItem>
                </Link>

                <DropdownMenuItem
                  className="py-2 font-medium text-sm"
                  onClick={() => handleLogout()}
                >
                  Log out
                </DropdownMenuItem>
              </DropdownMenuContent>
            </DropdownMenu>
          </>
        ) : (
          <>
            <Button
              variant="outline"
              className="b-1 border-blue-500 text-blue-500 rounded-lg px-4"
              size="sm"
            >
              <Link to={`/sign-in`}>Log in</Link>
            </Button>
            <Button
              className="b-1 bg-blue-500 text-white rounded-lg px-4"
              size="sm"
            >
              <Link to={`/sign-up`}>Sign up</Link>
            </Button>
          </>
        )}
      </div>
    </>
  );
}

export default AvatarTray;
