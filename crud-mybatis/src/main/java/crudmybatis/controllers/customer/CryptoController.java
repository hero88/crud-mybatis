package crudmybatis.controllers.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class CryptoController {

    private final String apiUrl = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/listing";
    private final String apiUrlId = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/detail/chart";

    @GetMapping("/coinmarketcap")
    public ResponseEntity<String> getCoinMarketCapData(@RequestParam("start") int start,
                                                       @RequestParam("limit") int limit,
                                                       @RequestParam("sortBy") String sortBy,
                                                       @RequestParam("sortType") String sortType,
                                                       @RequestParam("convert") String convert) {

        String fullUrl = String.format("%s?start=%d&limit=%d&sortBy=%s&sortType=%s&convert=%s",
                apiUrl, start, limit, sortBy, sortType, convert);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(fullUrl, String.class);

        return ResponseEntity.ok(response.getBody());
    }

  

}

