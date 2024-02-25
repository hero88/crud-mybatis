import format from "date-fns/format";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import DelEmployeeDialog from "@/sections/EmployeesPage/DelEmployeeDialog";
import UpdateEmployeeDialog from "@/sections/EmployeesPage/UpdateEmployeeDialog";
import { doGetAllEmployees } from "@/services/EmployeeAPI";
import { useEffect, useState } from "react";
import AddNewEmployeeDialog from "@/sections/EmployeesPage/AddEmployeeDialog";

function Employees() {
  const [employees, setEmployees] = useState([]);
  const [departments, setDepartments] = useState([]);

  const handleGetEmployeesData = async () => {
    const { data: response } = await doGetAllEmployees();
    console.log(response.data);
    setEmployees(response.data);
  };

  // const handleGetDepartmentData  = async () => {
  //   const {data: response};

  // }

  useEffect(() => {
    handleGetEmployeesData();
  }, []);

  return (
    <div className="px-28 py-4 flex flex-col">
      <h2 className="text-3xl font-semibold text-gray-900 mt-8">Employees</h2>

      <div className="flex justify-end mt-8">
        <AddNewEmployeeDialog loadEmployeesData={handleGetEmployeesData} />
      </div>

      <div className="mt-3 border rounded-md">
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead className="w-8 text-black"></TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                #
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Firstname
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Lastname
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Birthday
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Gender
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Phone
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Position
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Department
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Hire date
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Termination date
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold"></TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {employees?.map((employee, index) => (
              <TableRow key={index}>
                <TableCell className="font-semibold"></TableCell>
                <TableCell className="text-gray-500 font-semibold">
                  {employee.id}
                </TableCell>
                <TableCell className="font-semibold">
                  {employee.firstname}
                </TableCell>
                <TableCell className="font-semibold">
                  {employee.lastname}
                </TableCell>
                <TableCell className="font-semibold">
                  {employee.birthday
                    ? format(new Date(employee.birthday * 1000), "dd-MM-yyyy")
                    : "Not set yet"}
                </TableCell>
                <TableCell className="font-semibold">
                  {employee.gender || "Male"}
                </TableCell>
                <TableCell className="font-semibold">
                  {employee.contactNumber || ""}
                </TableCell>
                <TableCell className="font-semibold">
                  {employee.position || ""}
                </TableCell>
                <TableCell className="font-semibold">
                  {employee.departmentId || ""}
                </TableCell>
                <TableCell className="font-semibold">
                  {employee.hireDate
                    ? format(new Date(employee.hireDate * 1000), "dd-MM-yyyy")
                    : "Not set yet"}
                </TableCell>
                <TableCell className="font-semibold">
                  {employee.terminationDate
                    ? format(
                        new Date(employee.terminationDate * 1000),
                        "dd-MM-yyyy"
                      )
                    : "Not set yet"}
                </TableCell>
                <TableCell className="flex items-center space-x-4 justify-center">
                  <UpdateEmployeeDialog
                    employee={employee}
                    loadEmployeesData={handleGetEmployeesData}
                  />
                  <DelEmployeeDialog
                    employee={employee}
                    loadEmployeesData={handleGetEmployeesData}
                  />
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>
    </div>
  );
}

export default Employees;
