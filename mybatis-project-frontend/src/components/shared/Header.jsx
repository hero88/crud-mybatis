import { Link, useNavigate } from "react-router-dom";
import { Avatar, AvatarFallback, AvatarImage } from "../ui/avatar";

function Header() {
  const navigate = useNavigate();
  return (
    <div className="px-6 py-4 flex justify-between">
      <div className="flex content-center">
        <span className="text-xs items-center inline-flex font-semibold mr-2 ">
          Cryptos: <Link className="text-blue-600">2.2M+</Link>
        </span>
        <span className="text-xs items-center inline-flex font-semibold mr-2 ">
          Exchanges: <Link className="text-blue-600">706</Link>
        </span>
        <span className="text-xs items-center inline-flex font-semibold mr-2 flex-row">
          Market Cap: <Link className="text-blue-600">$1.65T 0.71%</Link>
        </span>
        <span className="text-xs items-center inline-flex font-semibold mr-2 flex-row">
          24h Vol: <Link className="text-blue-600">$57.5B 8.22%</Link>
        </span>
        <span className="text-xs items-center inline-flex font-semibold mr-2 ">
          Dominance: <Link className="text-blue-600">BTC: 51.0% </Link>
        </span>
        <span className="text-xs items-center inline-flex font-semibold mr-2 ">
          ETH: <Link className="text-blue-600">17.0% </Link>
        </span>
        <span className="text-xs items-center inline-flex font-semibold mr-2 ">
          ETHGas: <Link className="text-blue-600">20 Gwei </Link>
        </span>
        <span className="text-xs items-center inline-flex font-semibold mr-2 ">
          Fear & Greed: <Link className="text-blue-600">57/100</Link>
        </span>
      </div>
      <div>
        <Avatar className="h-8 w-8" onClick={() => navigate("/profile")}>
          <AvatarImage src="https://github.com/shadcn.png" alt="@shadcn" />
          <AvatarFallback>CN</AvatarFallback>
        </Avatar>
      </div>
    </div>
  );
}

export default Header;
