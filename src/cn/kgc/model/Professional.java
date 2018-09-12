package cn.kgc.model;

import java.util.Date;

public class Professional {
	private String id;
	private String code;
	private String name;
	private String nameEn;
	private Date date;
	private String eductionalSystme;
	private String totalScore;
	private String teatherCount;
	private String status;
	
	
	public Professional() {
	}
	public Professional(String id, String code, String name, String nameEn, Date date, String eductionalSystme,
			String totalScore, String teatherCount,String status) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.nameEn = nameEn;
		this.date = date;
		this.eductionalSystme = eductionalSystme;
		this.totalScore = totalScore;
		this.teatherCount = teatherCount;
		this.status = status;
	}
	public Professional(String id) {
		super();
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getEductionalSystme() {
		return eductionalSystme;
	}
	public void setEductionalSystme(String eductionalSystme) {
		this.eductionalSystme = eductionalSystme;
	}
	public String getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}
	public String getTeatherCount() {
		return teatherCount;
	}
	public void setTeatherCount(String teatherCount) {
		this.teatherCount = teatherCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Professional [id=" + id + ", code=" + code + ", name=" + name + ", nameEn=" + nameEn + ", date=" + date
				+ ", eductionalSystme=" + eductionalSystme + ", totalScore=" + totalScore + ", teatherCount="
				+ teatherCount + "]";
	}
	
	
}
