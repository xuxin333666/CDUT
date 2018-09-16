package cn.kgc.model;

import java.util.Date;

public class Student {
	private String id;
	private String name;
	private String photoUrl = "http://127.0.0.1:8080/upload/unkown.jpg";
	private String gender;
	private String registrationNo;
	private String registeredResidence;
	private String national;
	private String idcardType;
	private String idcard;
	private Date birthday;
	private String birthplace;
	private String nativePlace;
	private String registeredType;
	private String bloodType;
	private String sourceSchool;
	private Date admissionDate;
	private String educationBackground;
	private String stadyStatus;
	
	private String nameEn;
	private String usedName;
	private String maritalStatus;
	private String healthStatus;
	private String nationality;
	private String phoneNum;
	private String politicalStatus;
	private String email;
	private String specialty;
	
	private String reportStatus = "02";
	private Date reportDate;
	private String residenceStatus;
	
	private String registStatus = "02";
	private Date registDate;
	
	
	private Group group;


	public Student() {
	}


	public Student(String id, String name, String photoUrl, String gender, String registrationNo,
			String registeredResidence, String national, String idcardType, String idcard, Date birthday,
			String birthplace, String nativePlace, String registeredType, String bloodType, String sourceSchool,
			Date admissionDate, String educationBackground, String stadyStatus, String nameEn, String usedName,
			String maritalStatus, String healthStatus, String nationality, String phoneNum, String politicalStatus,
			String email, String specialty, String reportStatus, Date reportDate, String residenceStatus,
			String registStatus, Date registDate, Group group) {
		this.id = id;
		this.name = name;
		this.photoUrl = photoUrl;
		this.gender = gender;
		this.registrationNo = registrationNo;
		this.registeredResidence = registeredResidence;
		this.national = national;
		this.idcardType = idcardType;
		this.idcard = idcard;
		this.birthday = birthday;
		this.birthplace = birthplace;
		this.nativePlace = nativePlace;
		this.registeredType = registeredType;
		this.bloodType = bloodType;
		this.sourceSchool = sourceSchool;
		this.admissionDate = admissionDate;
		this.educationBackground = educationBackground;
		this.stadyStatus = stadyStatus;
		this.nameEn = nameEn;
		this.usedName = usedName;
		this.maritalStatus = maritalStatus;
		this.healthStatus = healthStatus;
		this.nationality = nationality;
		this.phoneNum = phoneNum;
		this.politicalStatus = politicalStatus;
		this.email = email;
		this.specialty = specialty;
		this.reportStatus = reportStatus;
		this.reportDate = reportDate;
		this.residenceStatus = residenceStatus;
		this.registStatus = registStatus;
		this.registDate = registDate;
		this.group = group;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhotoUrl() {
		return photoUrl;
	}


	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getRegistrationNo() {
		return registrationNo;
	}


	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}


	public String getRegisteredResidence() {
		return registeredResidence;
	}


	public void setRegisteredResidence(String registeredResidence) {
		this.registeredResidence = registeredResidence;
	}


	public String getNational() {
		return national;
	}


	public void setNational(String national) {
		this.national = national;
	}


	public String getIdcardType() {
		return idcardType;
	}


	public void setIdcardType(String idcardType) {
		this.idcardType = idcardType;
	}


	public String getIdcard() {
		return idcard;
	}


	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public String getBirthplace() {
		return birthplace;
	}


	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}


	public String getNativePlace() {
		return nativePlace;
	}


	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}


	public String getRegisteredType() {
		return registeredType;
	}


	public void setRegisteredType(String registeredType) {
		this.registeredType = registeredType;
	}


	public String getBloodType() {
		return bloodType;
	}


	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}


	public String getSourceSchool() {
		return sourceSchool;
	}


	public void setSourceSchool(String sourceSchool) {
		this.sourceSchool = sourceSchool;
	}


	public Date getAdmissionDate() {
		return admissionDate;
	}


	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}


	public String getEducationBackground() {
		return educationBackground;
	}


	public void setEducationBackground(String educationBackground) {
		this.educationBackground = educationBackground;
	}


	public String getStadyStatus() {
		return stadyStatus;
	}


	public void setStadyStatus(String stadyStatus) {
		this.stadyStatus = stadyStatus;
	}


	public String getNameEn() {
		return nameEn;
	}


	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}


	public String getUsedName() {
		return usedName;
	}


	public void setUsedName(String usedName) {
		this.usedName = usedName;
	}


	public String getMaritalStatus() {
		return maritalStatus;
	}


	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	public String getHealthStatus() {
		return healthStatus;
	}


	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public String getPoliticalStatus() {
		return politicalStatus;
	}


	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSpecialty() {
		return specialty;
	}


	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}


	public String getReportStatus() {
		return reportStatus;
	}


	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}


	public Date getReportDate() {
		return reportDate;
	}


	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}


	public String getResidenceStatus() {
		return residenceStatus;
	}


	public void setResidenceStatus(String residenceStatus) {
		this.residenceStatus = residenceStatus;
	}


	public String getRegistStatus() {
		return registStatus;
	}


	public void setRegistStatus(String registStatus) {
		this.registStatus = registStatus;
	}


	public Date getRegistDate() {
		return registDate;
	}


	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}


	public Group getGroup() {
		return group;
	}


	public void setGroup(Group group) {
		this.group = group;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", group=" + group + "]";
	}
	
	
	
	
	
}
