import PaginationCustom from "@/components/shared/PaginationCustom";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { getAllUsers } from "@/services/UserAPI";
import { useEffect, useState } from "react";

function ListUser() {
  const [userList, setUserList] = useState([]);

  useEffect(() => {
    const callGetAllUsersApi = async () => {
      try {
        const response = await getAllUsers();

        console.log(response);
        // setUserList(response.data);
      } catch (error) {
        console.error("Error when calling table data:", error);
      }
    };
    callGetAllUsersApi();
  }, []);

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
              <TableHead className="text-black text-[12px] font-bold">
                Role
              </TableHead>
              <TableHead></TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {userList.map((user, index) => (
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
                  {user.gender}
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
                <TableCell className="font-semibold text-gray-800">
                  {user.role}
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
