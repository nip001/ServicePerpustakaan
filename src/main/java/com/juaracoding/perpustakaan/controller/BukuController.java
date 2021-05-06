package com.juaracoding.perpustakaan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.perpustakaan.entity.Buku;
import com.juaracoding.perpustakaan.repository.BukuRepository;

@RestController
@RequestMapping("/buku")
public class BukuController {
	@Autowired
	BukuRepository bukuRepo;

	@GetMapping("/")
	public List<Buku> getAll() {
		return bukuRepo.findAll();
	}

	@GetMapping("/{title}")
	public List<Buku> getAllByJudulBuku(@PathVariable String title) {
		System.out.println(title);
		return bukuRepo.findByJudulBuku(title);
	}

	@PostMapping("/addBuku")
	public String addBuku(@RequestBody Buku buku) {
		bukuRepo.save(buku);
		return "Insert Berhasil";
	}

}
