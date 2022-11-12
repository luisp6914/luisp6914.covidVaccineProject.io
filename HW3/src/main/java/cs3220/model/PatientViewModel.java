package cs3220.model;

public class PatientViewModel {
	public boolean isRecievedSecondDose() {
		return recievedSecondDose;
	}
	public void setRecievedSecondDose(boolean recievedSecondDose) {
		this.recievedSecondDose = recievedSecondDose;
	}
	public String getVaccineName() {
		return vaccineName;
	}
	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int id) {
		this.patientId = id;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String name) {
		this.patientName = name;
	}
	public int getVaccineId() {
		return vaccineId;
	}
	public void setVaccineId(int vaccineId) {
		this.vaccineId = vaccineId;
	}
	public String getVaccineDosesRequired() {
		return vaccineDosesRequired;
	}
	public void setVaccineDosesRequired(String vaccineDosesRequired) {
		this.vaccineDosesRequired = vaccineDosesRequired;
	}
	public int getVaccineDoseLeft() {
		return vaccineDoseLeft;
	}
	public void setVaccineDoseLeft(int vaccineDoseLeft) {
		this.vaccineDoseLeft = vaccineDoseLeft;
	}
	public String getFirstDoseDate() {
		return firstDoseDate;
	}
	public void setFirstDoseDate(String firstDoseDate) {
		this.firstDoseDate = firstDoseDate;
	}
	public String getSecondDoseDate() {
		return secondDoseDate;
	}
	public void setSecondDoseDate(String secondDoseDate) {
		this.secondDoseDate = secondDoseDate;
	}
	//Default constructor
	public PatientViewModel() {
		
	}
	
	private int patientId;
	private String patientName;
	private int vaccineId;
	private String vaccineName;
	private String vaccineDosesRequired;
	private int vaccineDoseLeft;
	private String firstDoseDate;
	private String secondDoseDate;
	private boolean recievedSecondDose;
}
