package com.bermanfaat.formbuilder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bermanfaat.formbuilder.model.Response;
import com.bermanfaat.formbuilder.service.DataService;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("data")
public class DataController {
	@Autowired
	private DataService dataService;

	@PostMapping("/")
	public Response addData(@RequestBody String data) {
		try {
			Response result = dataService.addData(data);
			return result;
		} catch (Exception e) {
			Response result = new Response(0, null);
			return result;
		}

	}

	@PutMapping("/")
	public Response updateData(@RequestBody String data) {
		try {
			Response result = dataService.updateData(data);
			return result;
		} catch (Exception e) {
			Response result = new Response(0, null);
			return result;
		}
	}

	@DeleteMapping("/{aplicationid}/{rowid}")
	public Response deleteData(@PathVariable("aplicationid") String aplicationid, @PathVariable("rowid") String rowid) {

		try {
			Response result = dataService.deleteData(aplicationid, rowid);
			return result;
		} catch (Exception e) {
			Response result = new Response(0, null);
			return result;
		}
	}

}