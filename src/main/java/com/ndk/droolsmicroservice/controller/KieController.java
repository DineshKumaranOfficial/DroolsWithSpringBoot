package com.ndk.droolsmicroservice.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ndk.droolsmicroservice.kieinfo.model.KieFilesInfo;
import com.ndk.droolsmicroservice.kieinfo.repo.KieFilesInfoRepo;
import com.ndk.droolsmicroservice.kiemain.model.Kie;
import com.ndk.droolsmicroservice.kiemain.repo.KieRepo;
import com.ndk.droolsmicroservice.service.KieService;

/**
 * @author dineshkumaran_n
 *
 */
@RestController
public class KieController {
	@Autowired
	KieRepo kieRepo;
	@Autowired
	KieFilesInfoRepo filesInfoRepo;
	@Autowired
	KieService kieService;

	@GetMapping(path = "ndk", produces = {"application/json","application/xml"})
	public HashMap<String, Object> executeKie(@RequestBody HashMap<String, Object> requestMap) {
		HashMap<String, Object> responseMap = new HashMap<>();

		Kie kie = new Kie();
		ArrayList<KieFilesInfo> filesInfo = new ArrayList<>();
		
		String kieName = "";
		int kieId = 0;
		String processId = "";
		try {
			if (requestMap.get("KieName").getClass().equals(String.class)) {
				kieName = (String) requestMap.get("KieName");
			}
			kie = kieRepo.findByKienameAndActive(kieName, "Y");
			kieId = kie.getKieid();
			processId = kie.getProcessid();
			filesInfo = filesInfoRepo.findByKieidAndActive(kieId, "Y");
			responseMap = kieService.processKie(filesInfo, processId);
			
		} catch (Exception exception) {
			exception.printStackTrace();
			responseMap.put("ExceptionMessage", exception.getMessage());

		}

		return responseMap;
	}
}
