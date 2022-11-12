package cs3220.model;

public class VaccineEntry {
	/**
	 * Constructor for full vaccine information
	 * @param vaccine
	 * @param dosesRequired
	 * @param daysBetweenDoses
	 * @param totalDosesReceived
	 * @param totalDosesLeft
	 */
	
	public VaccineEntry() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVaccine() {
		return vaccine;
	}
	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}
	public int getDosesRequired() {
		return dosesRequired;
	}
	public void setDosesRequired(int dosesRequired) {
		this.dosesRequired = dosesRequired;
	}
	public String getDaysBetweenDoses() {
		return daysBetweenDoses;
	}
	public void setDaysBetweenDoses(String daysBetweenDoses) {
		this.daysBetweenDoses = daysBetweenDoses;
	}
	public int getTotalDosesReceived() {
		return totalDosesReceived;
	}
	public void setTotalDosesReceived(int totalDosesReceived) {
		this.totalDosesReceived = totalDosesReceived;
	}
	public int getTotalDosesLeft() {
		return totalDosesLeft;
	}
	public void setTotalDosesLeft(int totalDosesLeft) {
		this.totalDosesLeft = totalDosesLeft;
	}
	private int id;
	private String vaccine;
	private int dosesRequired;
	private String daysBetweenDoses;
	private int totalDosesReceived;
	private int totalDosesLeft;

}
