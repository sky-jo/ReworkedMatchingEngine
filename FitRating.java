/*
 *	For: SFWE 301 
 * 	Purpose: This Java class contains the generateFitRating method that takes
 * 			 a student and a scholarship and determines how well they fit, i.e.
 * 			 how many attributes the students and scholarships share.
 */
import java.time.LocalDateTime;

public class FitRating {
	final private static float MAX_FIT_RATING = (float) 100.0;
	final private static float PRIORITY_ATTRIBUTE_VALUE = (float) 30.0;
	
	/**
	 * @param student of type Student with attributes
	 * @param scholarship of type Scholarship with attributes and other info about the scholarship
	 * @return returns a float representing how well a student and a scholarship match. A higher fit rating means a better match. 
	 */
	public static float generateFitRating(Student student, Scholarship scholarship) {
		
		// check for an old scholarship
		String overDate = scholarship.getDueDate();
		if (isLate(overDate)) { return -1; }
		String openDate = scholarship.getOpenDate();
		if (notOpen(openDate)) { return -1; }
		
		
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
				if (isMajorMatch(studentValue, studentAttributes.get("minor"), scholarshipValue)) {
					fitRating += toAdd;
				}
			}
			// if GPA, cast to float then compare
			else if (scholarshipAttribute.equals("gpa")) {
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
	
	/**
	 * @param date a string containing the date in the form MM/DD/YYYY
	 * @return true if the date given is past the current date, false otherwise
	 */
	private static boolean isLate(String date) {
		// get components of date string
		String[] dateComponents = date.split("/");
		String monthStr = dateComponents[0];
		String dayStr = dateComponents[1];
		String yearStr = dateComponents[2];

		// get the current date
		LocalDateTime now = LocalDateTime.now();

		//System.out.println(date + " < " + now.toString());
		
		if (now.getYear() > Integer.valueOf(yearStr)) {
			return true;
		}
		// need to check months if years are the same
		else if (now.getYear() == Integer.valueOf(yearStr)) {
			if (now.getMonthValue() > Integer.valueOf(monthStr)) {
				return true;
			}
			// need to check days if months and years are the same
			else if (now.getMonthValue() == Integer.valueOf(monthStr)) {
				if (now.getDayOfMonth() > Integer.valueOf(dayStr)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param date a string represeing the date in the form MM/DD/YY
	 * @return fasle if the date passed is less than the current date, true if its greater than or equal to
	 */
	private static boolean notOpen(String date) {
		// get components of date string
		String[] dateComponents = date.split("/");
		String monthStr = dateComponents[0];
		String dayStr = dateComponents[1];
		String yearStr = dateComponents[2];
		
		// get the current date
		LocalDateTime now = LocalDateTime.now();

		// if the current year is >, the shcolarship has been opened
		if (now.getYear() > Integer.parseInt(yearStr)) {
			return false;
		}
		// if the years are equal, check month, then day
		else if (now.getYear() == Integer.parseInt(yearStr)) {
			if (now.getMonthValue() > Integer.parseInt(monthStr)) {
				return false;
			}
			else  if (now.getMonthValue() == Integer.parseInt(monthStr)) {
				if (now.getDayOfMonth() >= Integer.parseInt(dayStr)) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isMajorMatch(String studentMajor, String studentMinor, String scholarshipMajor) {
		studentMajor = studentMajor.toLowerCase();
		studentMinor = studentMinor.toLowerCase();
		scholarshipMajor = scholarshipMajor.toLowerCase();

		if (studentMajor.equals(scholarshipMajor) || studentMinor.equals(scholarshipMajor)) {
			return true;
		}
		
		String[] majorArray = studentMajor.split(" ");
		String[] minorArray = studentMinor.split(" ");

		for (String majorKeyword : majorArray) {
			if (majorKeyword.equals(scholarshipMajor)) {
				return true;
			}
		}
		for (String minorKeyword : minorArray) {
			if (minorKeyword.equals(scholarshipMajor)) {
				return true;
			}
		}
		return false;
	} 
}
