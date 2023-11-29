/*
 * For: SFWE 301 Final Project
 * Purpose: This class contains the information about Students in the 
 * 	        UASAMS. There is a string containing the name and attributes 
 * 			containing information about the student outlined in the 
 * 			requirements. This class acts as a container for data to be
 * 			used by the Match Engine to generate a fit rating.
 * Setters are not provided as this information should not change with regards
 * to the matching engine.
 */
public class Scholarship {
    private String name, openDate, dueDate, priorityAttribute;
    private int amount; // potentially unnecessary
    private Hashtable<String, String> attributes = new Hashtable<String, String>();
    
    /**
     * @param scholarshipName the name of the scholarship
     * @param scholarshipOpenDate open date of the scholarship in the form MM/DD/YYYY
     * @param scholarshipDueDate due date of the scholarship in the form MM/DD/YYYY    
     * @param scholarshipPriorityAttribute the priority attribute of the scholarship.
     * @param scholarshipAmount the value of the scholarship in dollars.
     * @param scholarshipAttributes info about the scholarship (indices - attribute: 0 - major/minor, 1 - graduation year, 2 - GPA, 3 - year of study, 
     * 															4 - transfer student, 5 - units enrolled, 6 - gender, 7 - race)
     */
    public Scholarship(String scholarshipName,  String scholarshipOpenDate, 
    			       String scholarshipDueDate, String scholarshipPriorityAttribute,
    			       int scholarshipAmount, String[] scholarshipAttributes) { 
    	name = scholarshipName;
    	openDate = scholarshipOpenDate;
    	dueDate = scholarshipDueDate;
    	priorityAttribute = scholarshipPriorityAttribute;
    	//amount = scholarshipAmount;
    	
    	attributes.put("major", scholarshipAttributes[0]);
    	attributes.put("graduation year", scholarshipAttributes[1]);
    	attributes.put("gpa", scholarshipAttributes[2]);
    	attributes.put("year of study", scholarshipAttributes[3]);
    	attributes.put("transfer student", scholarshipAttributes[4]);
    	attributes.put("units enrolled", scholarshipAttributes[5]);
    	attributes.put("gender", scholarshipAttributes[6]);
    	attributes.put("race", scholarshipAttributes[7]);
    }

    public String getName(){
        return name;
    }
    
    public String getOpenDate(){
        return openDate;
    }
    
    public String getDueDate(){
        return dueDate;
    }
    
    public String getPriorityAttribute() {
    	return priorityAttribute;
    }
    
    public Hashtable<String, String> getAttributes() {
    	return attributes;
    }

}
