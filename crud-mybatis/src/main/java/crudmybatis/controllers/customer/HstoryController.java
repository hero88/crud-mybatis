package crudmybatis.controllers.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class HstoryController {
    private final String apiUrlId = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/detail/chart";
    @GetMapping("/coinmarketdetail")
  //  @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<String> getCoinMarketDetail(@RequestParam("id") int id,
                                                      @RequestParam("dateStart") int prevOneYear,
                                                      @RequestParam("dateEnd") int secondsToday) {

        String fullUrl = String.format("%s?id=%d&range=%d~%d",
                apiUrlId, id, prevOneYear, secondsToday);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(fullUrl, String.class);

        return ResponseEntity.ok(response.getBody());
    }
}
