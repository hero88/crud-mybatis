import PaginationCustom from "@/components/shared/PaginationCustom";
import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
} from "@/components/ui/alert-dialog";
import { Button } from "@/components/ui/button";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { ToastAction } from "@/components/ui/toast";
import { useToast } from "@/components/ui/use-toast";
import { deleteUserById, getAllUsers } from "@/services/UserAPI";
import { Trash } from "lucide-react";
import { useEffect, useState } from "react";

function ListUser() {
  const { toast } = useToast();
  const [userList, setUserList] = useState([]);

  const handleGetAllUsers = async () => {
    try {
      const { data: response } = await getAllUsers();
      setUserList(response.data);
    } catch (error) {
      console.error("Error when calling table data:", error);
    }
  };

  useEffect(() => {
    handleGetAllUsers();
  }, []);

  const handleDeleteUser = async (userId) => {
    const { data: response } = await deleteUserById(userId);

    if (response.code === 200) {
      toast({
        title: "Delete user successfully.",
        description: "User list has been changed.",
        action: <ToastAction altText="Nice">Nice</ToastAction>,
      });
      handleGetAllUsers();
    } else {
      toast({
        variant: "destructive",
        title: "Uh oh! Something went wrong.",
        description: "There was a problem with deleting.",
        action: <ToastAction altText="Try again">Try again</ToastAction>,
      });
    }
  };

  const convertToAge = (dob) => {
    var birthdate = new Date(dob);
    var currentDate = new Date();

    var ageInMilliseconds = currentDate - birthdate;

    return Math.floor(ageInMilliseconds / (365.25 * 24 * 60 * 60 * 1000));
  };

  return (
    <div className="w-full px-8">
      <div className="min-h-svh">
        <h2 className="pb-4 text-2xl font-bold">User List</h2>
        <hr />
        <Table className="px-12">
          <TableHeader>
            <TableRow>
              <TableHead className="p-0 min-w-6 text-black text-[12px] font-bold">
                #
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Email
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Name
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Gender
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Address
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Age
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Phone number
              </TableHead>
              {/* <TableHead className="text-black text-[12px] font-bold">
                Role
              </TableHead> */}
              <TableHead></TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {userList?.map((user, index) => (
              <TableRow key={index}>
                <TableCell className="p-0 min-w-6 font-semibold text-gray-800">
                  {index + 1}
                </TableCell>
                <TableCell className="font-semibold text-gray-800">
                  {user.email}
                </TableCell>
                <TableCell className="font-semibold text-gray-800">
                  {user.name}
                </TableCell>
                <TableCell className="font-semibold text-gray-800">
                  {user.gender ? "male" : "female"}
                </TableCell>
                <TableCell className="font-semibold text-gray-800">
                  {user.address}
                </TableCell>
                <TableCell className="font-semibold text-gray-800">
                  {convertToAge(user.age)}
                </TableCell>
                <TableCell className="font-semibold text-gray-800">
                  {user.phoneNumber}
                </TableCell>
                {/* <TableCell className="font-semibold text-gray-800">
                  {user.role}
                </TableCell> */}
                <TableCell className="flex items-center space-x-1">
                  <AlertDialog>
                    <AlertDialogTrigger asChild>
                      <Button className="w-12 h-12">
                        <Trash width={20} height={20} />
                      </Button>
                    </AlertDialogTrigger>
                    <AlertDialogContent>
                      <AlertDialogHeader>
                        <AlertDialogTitle>
                          Are you absolutely sure?
                        </AlertDialogTitle>
                        <AlertDialogDescription>
                          This action cannot be undone. This will permanently
                          delete this account and remove this person data from
                          servers.
                        </AlertDialogDescription>
                      </AlertDialogHeader>
                      <AlertDialogFooter>
                        <AlertDialogCancel>Cancel</AlertDialogCancel>
                        <AlertDialogAction
                          onClick={() => handleDeleteUser(user.id)}
                        >
                          Continue
                        </AlertDialogAction>
                      </AlertDialogFooter>
                    </AlertDialogContent>
                  </AlertDialog>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>
      <PaginationCustom
        currentPage={{ id: 1, serial: 1 }}
        listPage={[
          { id: 1, serial: 1 },
          { id: 2, serial: 2 },
          { id: 3, serial: 3 },
        ]}
      />
    </div>
  );
}

export default ListUser;
