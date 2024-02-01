import PaginationCustom from "@/components/shared/PaginationCustom";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { useEffect, useState } from "react";
const fakeData = {
  data: {
    listUser: [
      {
        id: 1,
        email: "bqhuu130375@gmail.com",
        firstName: "Huu",
        lastName: "Bui Quang",
        gender: "male",
        address: "Address",
        age: "2001-05-14T00:00:00.000Z",
        phoneNumber: "0975247624",
        role: "user",
      },
    ],
  },
};
function ListUser() {
  const [data, seTableCellData] = useState([]);

  useEffect(() => {
    const callGetAllUsersApi = async () => {
      try {
        // const response = await getAllUsersApi();
        // const newData = response.data.data.listUser;
        seTableCellData(fakeData.data.listUser);
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
    <div className="centerSide w-7/12 px-8">
      <div className="min-h-svh">
        <Table className="px-12">
          <TableHeader>
            <TableRow>
              <TableHead className="p-0 min-w-6">#</TableHead>
              <TableHead>Email</TableHead>
              <TableHead>First Name</TableHead>
              <TableHead>Last Name</TableHead>
              <TableHead>Gender</TableHead>
              <TableHead>Address</TableHead>
              <TableHead>Age</TableHead>
              <TableHead>Phone number</TableHead>
              <TableHead>Role</TableHead>
              <TableHead></TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {data.map((user, index) => (
              <TableRow key={index}>
                <TableCell className="p-0 min-w-6">{index + 1}</TableCell>
                <TableCell>{user.email}</TableCell>
                <TableCell>{user.firstName}</TableCell>
                <TableCell>{user.lastName}</TableCell>
                <TableCell>{user.gender}</TableCell>
                <TableCell>{user.address}</TableCell>
                <TableCell>{convertToAge(user.age)}</TableCell>
                <TableCell>{user.phoneNumber}</TableCell>
                <TableCell>{user.role}</TableCell>
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
