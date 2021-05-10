package com.juaracoding.perpustakaan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.perpustakaan.entity.Buku;
import com.juaracoding.perpustakaan.entity.DataUser;
import com.juaracoding.perpustakaan.repository.DataUserRepository;

@RestController
@RequestMapping("/user")
public class DataUserController {
	@Autowired
	DataUserRepository dataRepo;

	@GetMapping("/")
	public List<DataUser> getAll() {
		return dataRepo.findAll();
	}

	@GetMapping("/searchby/{type}/{value}")
	public List<DataUser> getSearchBy(@PathVariable("type")String type, @PathVariable("value") String value) {
		return dataRepo.findBySearchBy(type, value);
	}

	@PostMapping("/register/")
	public String addUser(@RequestBody DataUser user) {
		dataRepo.save(user);
		return "Insert Berhasil";
	}
	
	@PostMapping("/register/{id}")
	public String updateUser(@PathVariable String id, @RequestBody DataUser user) {
		user.setId(Long.parseLong(id));
		dataRepo.save(user);
		return "Update Berhasil";
	}
}
