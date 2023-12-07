import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class Reader {
	
	
	public static void main(String[] args) throws IOException {
		
		
		while (true) { 
				Scanner sc = new Scanner(System.in);
			Scholarship[] listScholarships = new Scholarship[100];	
			System.out.print("Enter the name of the csv file containg information about the scholarships:");
			String scholarshipFile = sc.nextLine();
			System.out.println();
			System.out.print("Enter the name of the csv file containg information about the students:");
			String studentFile = sc.nextLine();
			System.out.println();
			
			
			// -----------------SCHOLARSHIP READER---------------------------------------
			// System.out.println("Opening file Scholarships.csv");
			FileInputStream scholarshipData = new FileInputStream("csvs/"+scholarshipFile);
			Scanner inFs = new Scanner(scholarshipData);
	
			// Variables for Scholarship
			String scholarshipName;
			String scholarshipOpenDate;
			String scholarshipDueDate;
			String scholarshipPriority;
			

			// VARIABLES FOR STUDENTS
			String studentName;

	
			int i = 0;
			while (inFs.hasNextLine()) {
				// For each comma, it makes it a slot in the array
				String line = inFs.nextLine();
				String[] sData = line.split(",");
				
				// skip the first line
				//if (i == 0) { i++; continue; }
				scholarshipName = sData[0];
				
				// attributes array: 0-Major&Minor 1-graduation year 2-GPA 3-Year of Study 4-Transfer
				// Student 5-Units Enrolled 6-Gender 7-Race
				String[] attributes = { sData[1], sData[2], sData[3], sData[4], sData[5], sData[6], sData[7], sData[8] };
				scholarshipOpenDate = sData[9];
				scholarshipDueDate = sData[10];
				scholarshipPriority = sData[11];
	
				// Creates scholarship
				Scholarship s = new Scholarship(scholarshipName, scholarshipOpenDate, scholarshipDueDate,
						scholarshipPriority, 0, attributes);
				listScholarships[i] = s;
				i++;
			}

			inFs.close();
			scholarshipData.close();
	
			Student[] listStudents = new Student[100];
			int j = 0;
			
			// ----------STUDENT READER------------------------
			FileInputStream studentData = new FileInputStream("csvs/"+studentFile);
			Scanner inFS = new Scanner(studentData);
	
			// Reading Students CSV
			while (inFS.hasNextLine()) {
				// For each comma, it makes it a slot in the array
				String line = inFS.nextLine();
				String[] stData = line.split(",");
	
		
	
				studentName = stData[0];
				String[] Sattributes = { stData[1], stData[2], stData[3], stData[4], stData[5], 
									     stData[6], stData[7], stData[8], stData[9] };
	
				// Creates an instance of the Student object
				Student stu = new Student(studentName, Sattributes);
				listStudents[j] = stu;
				j++;
			}
			
			inFS.close();
			studentData.close();
			
			System.out.println("#######################################BEGIN TESTCASE#######################################");
			for (Student student : listStudents) {
				if (student == null) {
					continue;
				}
				else {
					System.out.println("--------running fit rating for " + student.getName() + " on " + i + " scholarships--------");
				}
				for (Scholarship scholarship : listScholarships) {
					if (scholarship == null || scholarship.getDueDate().equals("duedate")) {
						continue;
					}
					System.out.println(student.getName() + " FitRating for " + scholarship.getName()
	                    + " Scholarship: " + FitRating.generateFitRating(student, scholarship));
				}
				System.out.println("------------------------------------------------------------------------------");
			}
			System.out.println("##########################################DONE##########################################");
			System.out.println();
		}
	}
	
}
