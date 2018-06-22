/**
 * 
 */

/**
 * @author Kobby
 *
 */
public class Person {
	
	private String occupation;
	private String gender;
	private String income;
	private String politicalAff;
	
	public Person(){
		
	}
	
	public Person(String occupation,String gender,String income,String politicalAff){
		this.occupation=occupation;
		this.gender=gender;
		this.income=income;
		this.politicalAff=politicalAff;
	}
	
	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}
	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the income
	 */
	public String getIncome() {
		return income;
	}
	/**
	 * @param income the income to set
	 */
	public void setIncome(String income) {
		this.income = income;
	}
	/**
	 * @return the politicalAff
	 */
	public String getPoliticalAff() {
		return politicalAff;
	}
	/**
	 * @param politicalAff the politicalAff to set
	 */
	public void setPoliticalAff(String politicalAff) {
		this.politicalAff = politicalAff;
	}
	
	public String toString(){
		String n=occupation+" "+gender+" "+income;
		return n;
	}

}
