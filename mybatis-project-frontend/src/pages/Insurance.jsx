import { useEffect, useState } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import AddNewInsuranceDialog from "@/sections/InsurancePage/AddInsuranceDialog";
import UpdateInsuranceDialog from "@/sections/InsurancePage/UpdateInsuranceDialog";
import DelInsuranceDialog from "@/sections/InsurancePage/DelInsuranceDialog";
import { doGetAllInsurances } from "@/services/InsuranceAPI";

function Insurance() {
  const [insurances, setInsurances] = useState([]);

  const handleGetInsurancesData = async () => {
    const { data: response } = await doGetAllInsurances();
    setInsurances(response.data);
  };

  useEffect(() => {
    handleGetInsurancesData();
  }, []);

  return (
    <div className="px-24 pt-4 pb-28 flex flex-col">
      <h2 className="text-3xl font-semibold text-gray-900 mt-8">Insurance</h2>

      <div className="flex justify-end mt-8">
        <AddNewInsuranceDialog loadInsurancesData={handleGetInsurancesData} />
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
                Name
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Description
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Rating
              </TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {insurances?.map((insurance, index) => (
              <TableRow key={index}>
                <TableCell className="font-semibold"></TableCell>
                <TableCell className="text-gray-500 font-semibold">
                  {insurance.id}
                </TableCell>
                <TableCell className="font-semibold">
                  {insurance.insuranceName}
                </TableCell>
                <TableCell className="font-semibold">
                  {insurance.insuranceDescription}
                </TableCell>
                <TableCell className="font-semibold">
                  {insurance.insuranceRate}
                </TableCell>
                <TableCell className="flex items-center space-x-4 justify-center">
                  <UpdateInsuranceDialog
                    insurance={insurance}
                    loadInsurancesData={handleGetInsurancesData}
                  />
                  <DelInsuranceDialog
                    insurance={insurance}
                    loadInsurancesData={handleGetInsurancesData}
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

export default Insurance;
