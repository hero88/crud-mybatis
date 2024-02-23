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

function Payroll() {
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
                Hire date
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold">
                Termination date
              </TableHead>
              <TableHead className="text-black text-[12px] font-bold"></TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {/* {marketCoinList?.map((item, index) => (
              <TableRow key={index}>
                <TableCell className="font-semibold"></TableCell>
                <TableCell className="text-gray-500 font-semibold">
                  {index + 1}
                </TableCell>
                <TableCell className="font-semibold text-end"></TableCell>
                <TableCell className="font-semibold text-end"></TableCell>
                <TableCell className="font-semibold text-end"></TableCell>
                <TableCell className="font-semibold text-end"></TableCell>
                <TableCell className="font-semibold text-end"></TableCell>
                <TableCell className="font-semibold text-end"></TableCell>
                <TableCell className="font-semibold text-end"></TableCell>
                <TableCell className="font-semibold text-end"></TableCell>
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
                </TableCell>{" "}
              </TableRow>
            ))} */}
            <TableRow>
              <TableCell className="font-semibold"></TableCell>
              <TableCell className="text-gray-500 font-semibold">1</TableCell>
              <TableCell className="font-semibold">Kenny</TableCell>
              <TableCell className="font-semibold">Will</TableCell>
              <TableCell className="font-semibold">22-1-2002</TableCell>
              <TableCell className="font-semibold">Male</TableCell>
              <TableCell className="font-semibold">0339991443</TableCell>
              <TableCell className="font-semibold">Designer</TableCell>
              <TableCell className="font-semibold">06-04-2017</TableCell>
              <TableCell className="font-semibold">06-04-2024</TableCell>
              <TableCell className="flex items-center space-x-4 justify-center">
                <UpdateEmployeeDialog />
                <DelEmployeeDialog />
              </TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </div>
    </div>
  );
}

export default Payroll;
