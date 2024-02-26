import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import DeletePayrollDialog from "@/sections/PayrollPage/DeletePayrollDialog";
import UpdatePayrollDialog from "@/sections/PayrollPage/UpdatePayrollDialog";
import { doGetAllPayroll } from "@/services/PayrollAPI";
import { format } from "date-fns";
import { useEffect, useState } from "react";

function Payroll() {
  const [payrolls, setPayrolls] = useState([]);

  const handleGetPayrollData = async () => {
    const { data: response } = await doGetAllPayroll();
    console.log(response.data);
    setPayrolls(response.data);
  };

  useEffect(() => {
    handleGetPayrollData();
  }, []);

  return (
    <div className="px-28 py-4">
      <h2 className="text-3xl font-semibold text-gray-900 mt-8">Payroll</h2>

      <div className="mt-8 border rounded-md">
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead className="w-8 text-black"></TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                #
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Salary
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Bonus
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Deduction
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Net Salary
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Period Start
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Period End
              </TableHead>

              <TableHead className="text-black text-[12px] font-bold"></TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {payrolls?.map((payroll, index) => (
              <TableRow key={index}>
                <TableCell className="font-semibold"></TableCell>
                <TableCell className="text-gray-500 font-semibold">
                  {payroll.id}
                </TableCell>
                <TableCell className="font-semibold">
                  {payroll.salary && <>{payroll.salary}$</>}
                </TableCell>
                <TableCell className="font-semibold">
                  {payroll.bonus && <>{payroll.bonus}%</>}
                </TableCell>
                <TableCell className="font-semibold">
                  {payroll.deduction && <>{payroll.deduction}$</>}
                </TableCell>
                <TableCell className="font-semibold">
                  {payroll.netSalary && <>{payroll.netSalary}$</>}
                </TableCell>
                <TableCell className="font-semibold">
                  {payroll.periodStart
                    ? format(new Date(payroll.periodStart), "dd-MM-yyyy")
                    : "Not set yet"}
                </TableCell>
                <TableCell className="font-semibold">
                  {payroll.periodEnd
                    ? format(new Date(payroll.periodEnd), "dd-MM-yyyy")
                    : "Not set yet"}
                </TableCell>
                <TableCell className="flex items-center space-x-4 justify-center"></TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>
    </div>
  );
}

export default Payroll;
