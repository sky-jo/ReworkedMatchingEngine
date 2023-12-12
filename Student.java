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


public class Student {
	
    private String name;
    private Hashtable<String, String> attributes = new Hashtable<String, String>(10);
    
    /**
     * @param studentName the name of the student
     * @param studentAttributes (indices - attribute: 0 - major, 1 - minor, 2 - graduation year, 3 - GPA, 4 - year of study,
     *  							                  5 - transfer student, 6 - units enrolled, 7 - gender, 8 - race)
     */
    public Student(String studentName, String[] studentAttributes) {
        name = studentName;
        
        attributes.put("major", studentAttributes[0]);
    	attributes.put("minor", studentAttributes[1]);
    	attributes.put("graduation year", studentAttributes[2]);
    	attributes.put("gpa", studentAttributes[3]);
    	attributes.put("year of study", studentAttributes[4]);
    	attributes.put("transfer student", studentAttributes[5]);
    	attributes.put("units enrolled", studentAttributes[6]);
    	attributes.put("gender", studentAttributes[7]);
    	attributes.put("race", studentAttributes[8]);
    }

    // all getters and setters required for both the Scholarship class and the Student class
    public String getName() {
        return name;
    }
    public Hashtable<String, String> getAttributes() {
    	return attributes;
    }
}
