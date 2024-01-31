import React from "react";

function Home() {
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await ApiCoin.getAllCoins();
        const newData = response.data.data.cryptoCurrencyList;
        setData(newData);
      } catch (error) {
        console.error("Error loading table data:", error);
      }
    };

    fetchData();

    const interval = setInterval(() => {
      callGetAllCoinsApi();
    }, 60000);

  
    return () => clearInterval(interval);
  }, []);

  return <p>Home</p>;
}

export default Home;
