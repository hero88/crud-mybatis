import { Link, useNavigate } from "react-router-dom";
import { Avatar, AvatarFallback, AvatarImage } from "../ui/avatar";
import { Button } from "../ui/button";

import { ArrowUpRightFromSquare, CalendarIcon } from "lucide-react";
import {
  HoverCard,
  HoverCardContent,
  HoverCardTrigger,
} from "../ui/hover-card";
import CustomSearchBar from "./CustomSearchBar";

function Header() {
  const navigate = useNavigate();

  // let userId = window.localStorage.getItem("userId");
  let userId = 1;
  return (
    <>
      <div className="px-6 pt-3 pb-2 flex justify-between">
        <div className="flex content-center">
          <span className="text-xs items-center inline-flex font-semibold mr-2  text-gray-500">
            Cryptos: <Link className="text-blue-600 ml-1">2.2M+</Link>
          </span>
          <span className="text-xs items-center inline-flex font-semibold mr-2  text-gray-500">
            Exchanges: <Link className="text-blue-600 ml-1">706</Link>
          </span>
          <span className="text-xs items-center inline-flex font-semibold mr-2 flex-row text-gray-500">
            Market Cap: <Link className="text-blue-600 ml-1">$1.65T 0.71%</Link>
          </span>
          <span className="text-xs items-center inline-flex font-semibold mr-2 flex-row text-gray-500">
            24h Vol: <Link className="text-blue-600 ml-1">$57.5B 8.22%</Link>
          </span>
          <span className="text-xs items-center inline-flex font-semibold mr-2  text-gray-500">
            Dominance: <Link className="text-blue-600 ml-1">BTC: 51.0% </Link>
          </span>
          <span className="text-xs items-center inline-flex font-semibold mr-2  text-gray-500">
            ETH: <Link className="text-blue-600 ml-1">17.0% </Link>
          </span>
          <span className="text-xs items-center inline-flex font-semibold mr-2  text-gray-500">
            ETHGas: <Link className="text-blue-600 ml-1">20 Gwei </Link>
          </span>
          <span className="text-xs items-center inline-flex font-semibold mr-2  text-gray-500">
            Fear & Greed: <Link className="text-blue-600 ml-1">57/100</Link>
          </span>
        </div>
        <div className="flex space-x-2">
          {userId ? (
            <Avatar
              className="h-8 w-8"
              onClick={() => navigate(`/profile/detail/${userId}`)}
            >
              <AvatarImage src="https://github.com/shadcn.png" alt="@shadcn" />
              <AvatarFallback>CN</AvatarFallback>
            </Avatar>
          ) : (
            <>
              <Button
                variant="outline"
                className="b-1 border-blue-500 text-blue-500 rounded-lg px-4"
                size="sm"
              >
                Log In
              </Button>
              <Button
                className="b-1 bg-blue-500 text-white rounded-lg px-4"
                size="sm"
              >
                Sign up
              </Button>
            </>
          )}
        </div>
      </div>
      <hr />
      <div className="px-6 py-4 flex justify-between">
        <div className="flex items-center space-x-8">
          <Link to={"/"}>
            <img src="/src/assets/logo.svg" alt="logo" />
          </Link>
          {/* Cryptocurrencies */}
          <div>
            <HoverCard>
              <HoverCardTrigger asChild>
                <Link to="/" className="font-semibold hover:text-blue-500">
                  Cryptocurrencies
                </Link>
              </HoverCardTrigger>
              <HoverCardContent className="w-80 mt-4 rounded-xl">
                <div className="flex justify-between space-x-4">
                  <Avatar>
                    <AvatarImage src="https://github.com/vercel.png" />
                    <AvatarFallback>VC</AvatarFallback>
                  </Avatar>
                  <div className="space-y-1">
                    <h4 className="text-sm font-semibold">@nextjs</h4>
                    <p className="text-sm">
                      The React Framework – created and maintained by @vercel.
                    </p>
                    <div className="flex items-center pt-2">
                      <CalendarIcon className="mr-2 h-4 w-4 opacity-70" />{" "}
                      <span className="text-xs text-muted-foreground">
                        Joined December 2021
                      </span>
                    </div>
                  </div>
                </div>
              </HoverCardContent>
            </HoverCard>
          </div>
          {/* Exchanges */}
          <div>
            <HoverCard>
              <HoverCardTrigger asChild>
                <Link to="/" className="font-semibold hover:text-blue-500">
                  Exchanges
                </Link>
              </HoverCardTrigger>
              <HoverCardContent className="w-48 mt-4 rounded-xl">
                <div className="flex flex-col">
                  <Button
                    variant="ghost"
                    className="flex justify-start text-[16px] h-12 py-1"
                  >
                    <img
                      src="https://s2.coinmarketcap.com/static/cloud/img/menu/MenuSpotIcon.svg"
                      width={30}
                      height={30}
                      alt=""
                    />
                    <p className="ml-3">Spot</p>
                  </Button>
                  <Button
                    variant="ghost"
                    className="flex justify-start text-[16px] h-12 py-1"
                  >
                    <img
                      src="https://s2.coinmarketcap.com/static/cloud/img/menu/MenuDerivativesIcon.svg"
                      width={30}
                      height={30}
                      alt=""
                    />
                    <p className="ml-3">Derivatives</p>
                  </Button>
                  <Button
                    variant="ghost"
                    className="flex justify-start text-[16px] h-12 py-1"
                  >
                    <img
                      src="https://s2.coinmarketcap.com/static/cloud/img/menu/MenuDexIcon.svg"
                      width={30}
                      height={30}
                      alt=""
                    />
                    <p className="ml-3">DEX</p>
                  </Button>
                </div>
              </HoverCardContent>
            </HoverCard>
          </div>
          {/* Community */}
          <div>
            <HoverCard>
              <HoverCardTrigger asChild>
                <Link to="/" className="font-semibold hover:text-blue-500">
                  Community
                </Link>
              </HoverCardTrigger>
              <HoverCardContent className="w-44 mt-4 rounded-xl">
                <div className="flex flex-col">
                  <Button
                    variant="ghost"
                    className="flex justify-start text-[16px] h-12 py-1"
                  >
                    <img
                      src="https://s2.coinmarketcap.com/static/cloud/img/menu/feed.svg"
                      width={30}
                      height={30}
                      alt=""
                    />
                    <p className="ml-3">Feeds</p>
                  </Button>
                  <Button
                    variant="ghost"
                    className="flex justify-start text-[16px] h-12 py-1"
                  >
                    <img
                      src="https://s2.coinmarketcap.com/static/cloud/img/menu/topics.svg"
                      width={30}
                      height={30}
                      alt=""
                    />
                    <p className="ml-3">Topic</p>
                  </Button>
                  <Button
                    variant="ghost"
                    className="flex justify-start text-[16px] h-12 py-1"
                  >
                    <img
                      src="https://s2.coinmarketcap.com/static/cloud/img/menu/lives.svg"
                      width={30}
                      height={30}
                      alt=""
                    />
                    <p className="ml-3">Lives</p>
                  </Button>
                  <Button
                    variant="ghost"
                    className="flex justify-start text-[16px] h-12 py-1"
                  >
                    <img
                      src="https://s2.coinmarketcap.com/static/cloud/img/menu/articles.svg"
                      width={30}
                      height={30}
                      alt=""
                    />
                    <p className="ml-3">Articles</p>
                  </Button>
                </div>
              </HoverCardContent>
            </HoverCard>
          </div>
          {/* Products */}
          <div>
            <HoverCard>
              <HoverCardTrigger asChild>
                <Link to="/" className="font-semibold hover:text-blue-500">
                  Products
                </Link>
              </HoverCardTrigger>
              <HoverCardContent className="w-80 mt-4 rounded-xl">
                <div className="flex justify-between space-x-4">
                  <Avatar>
                    <AvatarImage src="https://github.com/vercel.png" />
                    <AvatarFallback>VC</AvatarFallback>
                  </Avatar>
                  <div className="space-y-1">
                    <h4 className="text-sm font-semibold">@nextjs</h4>
                    <p className="text-sm">
                      The React Framework – created and maintained by @vercel.
                    </p>
                    <div className="flex items-center pt-2">
                      <CalendarIcon className="mr-2 h-4 w-4 opacity-70" />{" "}
                      <span className="text-xs text-muted-foreground">
                        Joined December 2021
                      </span>
                    </div>
                  </div>
                </div>
              </HoverCardContent>
            </HoverCard>
          </div>
          {/* Learn */}
          <div>
            <HoverCard>
              <HoverCardTrigger asChild>
                <Link to="/" className="font-semibold hover:text-blue-500">
                  Learn
                </Link>
              </HoverCardTrigger>
              <HoverCardContent className="w-[200px] mt-4 rounded-xl">
                <div className="flex flex-col">
                  <Button
                    variant="ghost"
                    className="flex justify-start text-[16px] h-12 py-1"
                  >
                    <img
                      src="https://s2.coinmarketcap.com/static/cloud/img/menu/MenuNewsIcon.svg"
                      width={30}
                      height={30}
                      alt=""
                    />
                    <p className="ml-3">News</p>
                  </Button>
                  <Button
                    variant="ghost"
                    className="flex justify-start text-[16px] h-12 py-1"
                  >
                    <img
                      src="https://s2.coinmarketcap.com/static/cloud/img/menu/MenuAlexandriaIcon.svg"
                      width={30}
                      height={30}
                      alt=""
                    />
                    <p className="ml-3">Academy</p>
                    <ArrowUpRightFromSquare
                      className="text-gray-400 ml-2"
                      strokeWidth={2.5}
                      width={20}
                      height={17}
                    />
                  </Button>
                  <Button
                    variant="ghost"
                    className="flex justify-start text-[16px] h-12 py-1"
                  >
                    <img
                      src="https://s2.coinmarketcap.com/static/cloud/img/menu/MenuCMCResearch.svg"
                      width={30}
                      height={30}
                      alt=""
                    />
                    <p className="ml-3">Research</p>
                    <ArrowUpRightFromSquare
                      className="text-gray-400 ml-2"
                      strokeWidth={2.5}
                      width={20}
                      height={17}
                    />
                  </Button>
                  <Button
                    variant="ghost"
                    className="flex justify-start text-[16px] h-12 py-1"
                  >
                    <img
                      src="https://s2.coinmarketcap.com/static/cloud/img/menu/MenuVideosIcon.svg"
                      width={30}
                      height={30}
                      alt=""
                    />
                    <p className="ml-3">Videos</p>
                    <ArrowUpRightFromSquare
                      className="text-gray-400 ml-2"
                      strokeWidth={2.5}
                      width={20}
                      height={17}
                    />
                  </Button>
                  <Button
                    variant="ghost"
                    className="flex justify-start text-[16px] h-12 py-1"
                  >
                    <img
                      src="https://s2.coinmarketcap.com/static/cloud/img/menu/MenuGlossaryIcon.svg"
                      width={30}
                      height={30}
                      alt=""
                    />
                    <p className="ml-3">Glossary</p>
                    <ArrowUpRightFromSquare
                      className="text-gray-400 ml-2"
                      strokeWidth={2.5}
                      width={20}
                      height={17}
                    />
                  </Button>
                </div>
              </HoverCardContent>
            </HoverCard>
          </div>
        </div>
        <div className="flex items-center justify-end">
          <div></div>
          <div></div>
          <div></div>
          <div>
            <CustomSearchBar />
          </div>
        </div>
      </div>
      <hr />
    </>
  );
}

export default Header;
