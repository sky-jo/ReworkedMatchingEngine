import java.util.Scanner;
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;

public class Reader {
    // Variables for Scholarships
    static String scholarshipName;
    static String scholarshipOpenDate;
    static String scholarshipDueDate;
    static int scholarshipAmount;
    static String scholarshipPriority;
    static String amount;

    // VARIABLES FOR STUDENTS
    static String studentName;

    public static void main(String[] args) throws IOException {
        
        Scholarship[] listScholarships=new Scholarship[100];
        // -----------------SCHOLARSHIP READER---------------------------------------
        //System.out.println("Opening file Scholarships.csv");
        FileInputStream scholarshipData = new FileInputStream("Scholarships.csv");
             Scanner inFs = new Scanner(scholarshipData); 

            // Reading the scholarships
           // System.out.println("Reading Scholarships.");

            
            int i = 0;
            while (inFs.hasNextLine()) {
                // For each comma, it makes it a slot in the array
                String line = inFs.nextLine();
                String[] sData = line.split(",");

                
                //for(int m=0; m <sData.length;m++ ){
                //    System.out.print(sData[m]+",");
               // }
               // System.out.println();

    

                scholarshipName = sData[0];
                //atributes array: 0-Major&Minor 1-Grad year 2-GPA 3-Year of Study 4-Transfer Student 5-Units Enrolled 6-Gender 7-Race
                String[] attributes = {sData[1], sData[2], sData[3], sData[4], sData[5], sData[6], sData[7], sData[8]};
                scholarshipOpenDate = sData[9]; 
                scholarshipDueDate = sData[10]; 
                scholarshipPriority = sData[11];


                // Creates scholarship
                Scholarship s = new Scholarship(scholarshipName, scholarshipOpenDate, scholarshipDueDate, scholarshipPriority, 0, attributes);
                listScholarships[i] = s;
                i++;
            }
            inFs.close();
            scholarshipData.close();

        Student[] listStudents=new Student[100];
        int j=0;
        // ----------STUDENT READER------------------------
       // System.out.println("Opening file Students.csv");
        FileInputStream studentData = new FileInputStream("Students.csv");
             Scanner inFS = new Scanner(studentData);

            // Reading Students CSV
           // System.out.println("Reading Students.");

        
            while (inFS.hasNextLine()) {
                // For each comma, it makes it a slot in the array
                String line = inFS.nextLine();
                String[] stData = line.split(",");

                //for (String s : stData) {
                 //   System.out.print(s + ",");
                //}
                //System.out.println();

                studentName = stData[0];
                String[] Sattributes = {stData[1], stData[2], stData[3], stData[4], stData[5], stData[6], stData[7], stData[8], stData[9]};

                // Creates an instance of the Student object
                Student stu = new Student(studentName, Sattributes);
                listStudents[j]=stu;
                j++;
            }
            inFS.close();
            studentData.close();
        
        
        for (Student student : listStudents) {
            if (student == null) {continue;}
            for (Scholarship scholarship : listScholarships) {
                if (scholarship == null) {continue;}
                System.out.println("Student: " + student.getName() + " FitRaiting for " + scholarship.getName()+" Sholarship: " + FitRating.generateFitRating(student, scholarship));
            }
            System.out.println();
        }
    }
}
