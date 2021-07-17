package com.ndk.droolsmicroservice.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import com.ndk.droolsmicroservice.kieinfo.model.KieFilesInfo;

@Service
public class KieService {

	public HashMap<String, Object> processKie(ArrayList<KieFilesInfo> filesInfoList, String processId) {
		HashMap<String, Object> responseMap = new HashMap<String, Object>();
		KieBase kieBase;
		KieServices kieServices;
		KieBuilder kieBuilder;
		KieRepository kieRepository;
		KieFileSystem kieFileSystem;
		KieContainer kieContainer;
		KieSession kieSession;
		Resource resource;

		String filePath = "";
		String fileType = "";
		try {
			// Loading KieBase with necessary files
			kieServices = KieServices.Factory.get();
			kieRepository = kieServices.getRepository();
			kieFileSystem = kieServices.newKieFileSystem();

			for (KieFilesInfo fileInfo : filesInfoList) {
				filePath = fileInfo.getKiefilepath();
				fileType = fileInfo.getKiefiletype();
				File file = new File(filePath);
				resource = kieServices.getResources().newFileSystemResource(file)
						.setResourceType(ResourceType.determineResourceType(fileType));

				kieFileSystem.write(resource);
			}

			kieBuilder = kieServices.newKieBuilder(kieFileSystem);
			kieBuilder.buildAll();

			kieContainer = kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
			kieBase = kieContainer.getKieBase();
			kieSession = kieBase.newKieSession();

			kieSession.insert(responseMap);
			kieSession.startProcess(processId);
			kieSession.fireAllRules();

		} catch (Exception exception) {
			exception.printStackTrace();
			responseMap.put("ExceptionMessage", exception.getMessage());
		}

		return responseMap;
	}
}
