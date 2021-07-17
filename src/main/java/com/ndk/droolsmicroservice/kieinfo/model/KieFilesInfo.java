package com.ndk.droolsmicroservice.kieinfo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "kieinfo", name = "kiefilesinfo")
public class KieFilesInfo {
	@Id
	private int kiefilesinfoid;
	private int kieid;
	private String kiefilename;
	private String kiefiletype;
	private String kiefilepath;
	private String active;

	public KieFilesInfo() {

	}

	public KieFilesInfo(int kiefilesinfoid, int kieid, String kiefilename, String kiefiletype, String kiefilepath,
			String active) {
		super();
		this.kiefilesinfoid = kiefilesinfoid;
		this.kieid = kieid;
		this.kiefilename = kiefilename;
		this.kiefiletype = kiefiletype;
		this.kiefilepath = kiefilepath;
		this.active = active;
	}

	public int getKiefilesinfoid() {
		return kiefilesinfoid;
	}

	public void setKiefilesinfoid(int kiefilesinfoid) {
		this.kiefilesinfoid = kiefilesinfoid;
	}

	public int getKieid() {
		return kieid;
	}

	public void setKieid(int kieid) {
		this.kieid = kieid;
	}

	public String getKiefilename() {
		return kiefilename;
	}

	public void setKiefilename(String kiefilename) {
		this.kiefilename = kiefilename;
	}

	public String getKiefiletype() {
		return kiefiletype;
	}

	public void setKiefiletype(String kiefiletype) {
		this.kiefiletype = kiefiletype;
	}

	public String getKiefilepath() {
		return kiefilepath;
	}

	public void setKiefilepath(String kiefilepath) {
		this.kiefilepath = kiefilepath;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}
