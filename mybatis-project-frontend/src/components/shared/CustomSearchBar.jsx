import { Input } from "@/components/ui/input";

export default function CustomSearchBar() {
  return (
    <div className="relative w-72">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        className="absolute top-0 bottom-0 w-5 h-5 my-auto text-gray-400 font-semibold left-3"
        fill="none"
        viewBox="0 0 21 24"
        stroke="currentColor"
      >
        <path
          strokeLinecap="round"
          strokeLinejoin="round"
          strokeWidth={3}
          d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
        />
      </svg>
      <Input
        type="text"
        placeholder="Search"
        className="pl-12 pr-4 font-semibold bg-gray-100 placeholder:text-gray-400 border-0 "
      />
    </div>
  );
}
