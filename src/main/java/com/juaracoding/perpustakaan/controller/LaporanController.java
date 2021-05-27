package com.juaracoding.perpustakaan.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.juaracoding.perpustakaan.entity.Laporan;
import com.juaracoding.perpustakaan.repository.LaporanRepository;
import com.juaracoding.perpustakaan.utility.FileUtility;

@RestController
@RequestMapping("/laporan")
public class LaporanController {
	@Autowired
	LaporanRepository laporanRepo;
	
	@GetMapping("/")
	public List<Laporan> getAll(){
		return laporanRepo.findAll();
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("/")
	public String addLaporan (@RequestParam(value="file")MultipartFile images, @ModelAttribute(value="data") String dataJson) throws IOException {
		String fileName = StringUtils.cleanPath(images.getOriginalFilename());
		
		String uploadDir = "user-photo/";
		FileUtility.saveFile(uploadDir, fileName, images);
		Laporan lapor = new Gson().fromJson(dataJson, Laporan.class);
		
		if(lapor.getKejadian().equalsIgnoreCase("bencana")) {
			lapor.setStatus("bencana");
		}else {
			lapor.setStatus("kriminal");
		}
		lapor.setImage("/" + uploadDir + fileName);
		Date date = new Date();
		lapor.setJam(String.valueOf(date.getHours())+":"+String.valueOf(date.getMinutes()));
		laporanRepo.save(lapor);
		return "Berhasil memasukan data";
	}
}
