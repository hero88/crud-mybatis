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
import com.allxone.coinmarket.model.InsuranceType;
import com.allxone.coinmarket.service.InsuranceTypeService;

@RestController
@RequestMapping("/api/v1/insurance")
@CrossOrigin("*")
public class InsuranceTypeRestApi {

	@Autowired
	InsuranceTypeService insuranceTypeService;

	@PostMapping
	public ResponseEntity<?> addToInsuranceType(@RequestBody InsuranceType insuranceType) throws ParamInvalidException {
		insuranceTypeService.addToInsuranceType(insuranceType);

		return ResponseEntity.ok("Save to insurance type succes!");

	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateInsuranceType(@PathVariable Integer id, @RequestBody InsuranceType insuranceType)
			throws ParamInvalidException {

		insuranceType.setId(id);

		insuranceTypeService.updateInsuranceType(insuranceType);

		return ResponseEntity.ok("Update to insurance type succes!");

	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteInsuranceType(@PathVariable Integer id) throws ParamInvalidException {
		insuranceTypeService.deleteInsuranceType(id);

		return ResponseEntity.ok("Delete to insurance type succes!");

	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) throws ParamInvalidException {
		return ResponseEntity.ok(insuranceTypeService.findById(id));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() {

		return ResponseEntity.ok(insuranceTypeService.findAll());
	}

}
