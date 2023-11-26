/*
 *	For: SFWE 301 
 * 	Purpose: This Java class contains the generateFitRating method that takes
 * 			 a student and a scholarship and determines how well they fit, i.e.
 * 			 how many attributes the students and scholarships share.
 */

public class FitRating {
	final private static float MAX_FIT_RATING = (float) 100.0;
	final private static float PRIORITY_ATTRIBUTE_VALUE = (float) 30.0;
	
	/**
	 * @param student of type Student with attributes
	 * @param scholarship of type Scholarship with attributes and other info about the scholarship
	 * @return returns a float representing how well a student and a scholarship match. A higher fit rating means a better match. 
	 */
	public static float generateFitRating(Student student, Scholarship scholarship) {
		Hashtable<String, String> studentAttributes = student.getAttributes();
		Hashtable<String, String> scholarshipAttributes = scholarship.getAttributes();
		String priorityAttribute = scholarship.getPriorityAttribute();
		
		
		// calculate the value of one attribute
		float attributeValue;
		// if there is not priority attribute, each attribute has the same value
		if (priorityAttribute.equals("none")) 
			attributeValue = MAX_FIT_RATING / (float) scholarshipAttributes.size();
		// if there is a priority attribute, the priority attribute is worth 30 pts
		// and all other attributes are worth the same
		else 
			attributeValue = (float) (MAX_FIT_RATING - PRIORITY_ATTRIBUTE_VALUE) / (float) scholarshipAttributes.size();
		
		
		float fitRating = 0;
		float toAdd = attributeValue;
		// iterate over every attribute in the scholarship and student to check if they match
		for (Pair<String, String> entry: scholarshipAttributes.entryArray()) {
			String scholarshipAttribute = entry.getKey();
			String scholarshipValue = entry.getValue();
			String studentValue = studentAttributes.get(scholarshipAttribute);
			
			// check for priority attribute and set toAdd appropriately
			if (scholarshipAttribute.equals(priorityAttribute)) 
				toAdd = PRIORITY_ATTRIBUTE_VALUE;				
			else 
				toAdd = attributeValue;
			
			
			// if the scholarship does not specify a value for an attribute, 
			// add points to the fit rating
			if (scholarshipValue.equals("none"))
				fitRating += toAdd;
			
			// if checking scholarships desired major, check the students major and minor for a match
			if (scholarshipAttribute.equals("major")) {
				// if the students major and minor match, add to the fit rating
				if (scholarshipValue.equals(studentValue) ||
					scholarshipValue.equals(studentAttributes.get("minor"))) {
					fitRating += toAdd;
				}
			}
			// if GPA, cast to float then compare
			else if (scholarshipAttribute.equals("GPA")) {
				float scholarshipGPA = Float.parseFloat(scholarshipValue);
				float studentGPA = Float.parseFloat(studentValue);
				if (studentGPA >= scholarshipGPA) {
					fitRating += toAdd;
				}
			}
			// only add to the fit rating if attributes match
			else if (studentValue.equals(scholarshipValue)) {
				fitRating += toAdd;
			}
		}
		return fitRating;
	}
}
