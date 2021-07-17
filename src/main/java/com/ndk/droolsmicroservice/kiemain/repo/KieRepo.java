package com.ndk.droolsmicroservice.kiemain.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndk.droolsmicroservice.kiemain.model.Kie;

public interface KieRepo extends JpaRepository<Kie, Integer>{
	Kie findByKienameAndActive(String name, String active);
}
