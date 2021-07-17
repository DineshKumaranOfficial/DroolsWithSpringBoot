package com.ndk.droolsmicroservice.kiemain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "kiemain", name = "kie")
public class Kie {
	@Id
	private int kieid;
	private String kiename;
	private String active;
	private String processid;

	public Kie() {

	}

	public Kie(int kieid, String kiename, String active, String processid) {
		super();
		this.kieid = kieid;
		this.kiename = kiename;
		this.active = active;
		this.processid = processid;
	}

	public int getKieid() {
		return kieid;
	}

	public void setKieid(int kieid) {
		this.kieid = kieid;
	}

	public String getKiename() {
		return kiename;
	}

	public void setKiename(String kiename) {
		this.kiename = kiename;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getProcessid() {
		return processid;
	}

	public void setProcessid(String processid) {
		this.processid = processid;
	}
}
