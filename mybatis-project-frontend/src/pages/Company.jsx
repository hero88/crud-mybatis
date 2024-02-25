import { doGetAllEmployees } from "@/services/EmployeeAPI";
import { useEffect, useState } from "react";
import { BsPersonStanding } from "react-icons/bs";
import { FaHandHoldingUsd, FaMoneyCheckAlt } from "react-icons/fa";

function Company() {
  const [employees, setEmployees] = useState([]);

  const handleGetEmployeesData = async () => {
    const { data: response } = await doGetAllEmployees();
    console.log(response.data);
    setEmployees(response.data);
  };

  useEffect(() => {
    handleGetEmployeesData();
  }, []);

  return (
    <div className="px-28 py-4">
      <div className="mt-8">
        <h2 className="text-2xl font-bold text-gray-800">Hi, Welcome back</h2>

        <div className="mt-10 flex space-x-6">
          <div className="bg-blue-100 w-64 h-56 rounded-xl flex flex-col items-center justify-center">
            <BsPersonStanding className="w-10 h-10 text-blue-900" />
            <h2 className="font-bold text-4xl text-blue-950 mt-5">
              {employees.length}
            </h2>
            <h2 className="text-blue-900 text-sm font-semibold font-sans mt-2">
              Total Employees
            </h2>
          </div>
          <div className="bg-yellow-100 w-64 h-56 rounded-xl flex flex-col items-center justify-center">
            <FaHandHoldingUsd className="w-10 h-10 text-yellow-700" />
            <h2 className="font-bold text-4xl text-yellow-900 mt-5">74 $</h2>
            <h2 className="text-yellow-600 text-sm font-semibold font-sans mt-2">
              Total Hodings
            </h2>
          </div>
          <div className="bg-red-100 w-64 h-56 rounded-xl flex flex-col items-center justify-center">
            <FaMoneyCheckAlt className="w-10 h-10 text-red-600" />
            <h2 className="font-bold text-4xl text-red-900 mt-5">74</h2>
            <h2 className="text-red-700 text-sm font-semibold font-sans mt-2">
              Total Payroll
            </h2>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Company;
