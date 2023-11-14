/*
 *	For: SFWE 301 
 * 	Purpose: This Java class contains the generateFitRating method that takes
 * 			 a student and a scholarship and determines how well they fit, i.e.
 * 			 how many attributes the students and scholarships share.
 */
import java.util.HashMap;
import java.util.Map;

public class FitRating {
	final private static float MAX_FIT_RATING = (float) 100.0;
	
	/**
	 * @param student of type Student with attributes
	 * @param scholarship of type Scholarship with attributes and other info about the scholarship
	 * @return returns a float representing how well a student and a scholarship match. A higher fit rating means a better match. 
	 */
	public static float generateFitRating(Student student, Scholarship scholarship) {
		HashMap<String, String> studentAttributes = student.getAttributes();
		HashMap<String, String> scholarshipAttributes = scholarship.getAttributes();
		String priorityAttribute = scholarship.getPriorityAttribute();
		
		
		// calculate the value of one attribute
		float attributeValue;
		// if there is not priority attribute, each attribute has the same value
		if (priorityAttribute.equals("none")) 
			attributeValue = MAX_FIT_RATING / (float) scholarshipAttributes.size();
		
		// if there is a priority attribute, the priority attribute is worth 30 pts
		// and all other attributes are worth the same
		else 
			attributeValue = (float) (MAX_FIT_RATING - 30.0) / (float) scholarshipAttributes.size();
		
		
		float fitRating = 0;
		float toAdd = attributeValue;
		// iterate over every attribute in the scholarship and student to check if they match
		for (Map.Entry<String, String> entry: scholarshipAttributes.entrySet()) {
			String scholarshipAttribute = entry.getKey();
			String scholarshipValue = entry.getValue();
			String studentValue = studentAttributes.get(scholarshipAttribute);
			
			// check for priority attribute and set toAdd appropriately
			if (scholarshipAttribute.equals(priorityAttribute)) {
				toAdd = (float) 30;
			}			
			else {
				toAdd = attributeValue;
			}
			
			// if the scholarship does not specify a value for an attribute, 
			// add points to the fit rating
			if (scholarshipValue.equals("none")) {
				fitRating += toAdd;
			}
			
			// if GPA, cast to float then compare
			if (scholarshipAttribute.equals("GPA")) {
				float scholarshipGPA = Float.parseFloat(scholarshipValue);
				float studentGPA = Float.parseFloat(studentValue);
				if (studentGPA >= scholarshipGPA) {
					fitRating += toAdd;
				}
			}
			else if (studentValue.equals(scholarshipValue)) {
				fitRating += toAdd;
			}
		}
		return fitRating;
	}
}
