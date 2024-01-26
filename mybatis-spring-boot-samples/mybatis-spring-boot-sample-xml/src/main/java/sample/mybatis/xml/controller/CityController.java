package sample.mybatis.xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.mybatis.xml.dao.CityDao;
import sample.mybatis.xml.domain.City;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private final CityDao cityDao;

    @Autowired
    public CityController(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @GetMapping("/{id}")
    public City getCitById(@PathVariable Long id) {
        return cityDao.selectCityById(id);
    }
}
