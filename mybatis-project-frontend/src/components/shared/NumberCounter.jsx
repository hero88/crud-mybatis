import { updateCoin } from "@/services/CoinAPI";
import { useState } from "react";

const NumberCounter = ({ coin }) => {
  const { coinMarketId, id, name, quantity, symbol, userId } = coin;

  const handleUpdateCoin = async (quantityValue) => {
    let newUpdatedCoin = {
      id,
      userId,
      name,
      symbol,
      coinMarketId,
      quantity: quantityValue,
    };

    const { data: response } = await updateCoin(newUpdatedCoin);

  };

  const [count, setCount] = useState(quantity);

  const handleIncrement = () => {
    setCount(count + 1);
    handleUpdateCoin(count + 1);
  };

  const handleDecrement = () => {
    setCount(count - 1);
    handleUpdateCoin(count - 1);
  };

  return (
    <div className="flex flex-col">
      <div className="flex items-center space-x-4">
        <button
          className="w-9 h-9 rounded-md bg-gray-200 hover:bg-gray-300 focus:outline-none font-bold"
          onClick={handleDecrement}
        >
          -
        </button>
        <span className="text-xl">{count}</span>
        <button
          className="w-9 h-9 rounded-md bg-gray-200 hover:bg-gray-300 focus:outline-none font-bold"
          onClick={handleIncrement}
        >
          +
        </button>
      </div>
    </div>
  );
};

export default NumberCounter;
