package com.allxone.coinmarket.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allxone.coinmarket.exception.common.ParamInvalidException;
import com.allxone.coinmarket.model.Holidays;
import com.allxone.coinmarket.service.HolidaysService;

@RestController
@RequestMapping("/api/v1/holidays")
@CrossOrigin("*")
public class HolidaysRestApi {

	@Autowired
	HolidaysService holidaysService;

	@PostMapping
	public ResponseEntity<?> addToHoliday(@RequestBody Holidays holidays) throws ParamInvalidException {
		holidaysService.addToHoliday(holidays);

		return ResponseEntity.ok("Save to holiday succes!");

	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateHoliday(@PathVariable Integer id, @RequestBody Holidays holidays)
			throws ParamInvalidException {

		holidays.setId(id);

		holidaysService.updateHoliday(holidays);

		return ResponseEntity.ok("Update to holidays succes!");

	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteHoliday(@PathVariable Integer id) throws ParamInvalidException {
		holidaysService.delete(id);

		return ResponseEntity.ok("Delete to holiday succes!");

	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) throws ParamInvalidException {
		return ResponseEntity.ok(holidaysService.edit(id));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() {

		return ResponseEntity.ok(holidaysService.getAll());
	}
}
