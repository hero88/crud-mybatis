function Footer() {
  return (
    <footer className="bg-gray-800 text-white px-6 py-4 mt-36">
      <div className="container mx-auto flex flex-wrap items-center justify-between">
        <div>
          <h3 className="text-lg">Products</h3>
          <ul>
            <li>CMC Labs</li>
            <li>ChatGPT Plugin</li>
            <li>Crypto API</li>
            {/* Add the rest of the items */}
          </ul>
        </div>
        {/* Repeat the above structure for other columns: Company, Support, Socials */}
        <div>
          <h3 className="text-lg">Company</h3>
          <ul>
            <li>About us</li>
            <li>Terms of use</li>
            <li>Privacy Policy</li>
            {/* Add the rest of the items */}
          </ul>
        </div>
        {/* Add the rest of the columns */}
      </div>
      <div className="mt-6 text-center text-sm">
        © 2024 CoinMarketCap. All rights reserved.
      </div>
    </footer>
  );
}

export default Footer;
