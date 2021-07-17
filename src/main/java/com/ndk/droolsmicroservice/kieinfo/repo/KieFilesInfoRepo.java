package com.ndk.droolsmicroservice.kieinfo.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndk.droolsmicroservice.kieinfo.model.KieFilesInfo;

public interface KieFilesInfoRepo extends JpaRepository<KieFilesInfo, Integer> {
	ArrayList<KieFilesInfo> findByKieidAndActive(int kieId, String active);
}
