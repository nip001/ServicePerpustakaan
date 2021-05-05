package com.juaracoding.perpustakaan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.juaracoding.perpustakaan.entity.Buku;

public interface BukuRepository extends JpaRepository<Buku, Long>{
	
}
