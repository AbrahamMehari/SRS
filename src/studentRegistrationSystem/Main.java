package studentRegistrationSystem;

import java.util.ArrayList;
import java.util.List;

public class Main { // We are not in Kansas anymore, This is Pandora!
	Student recon = new Student("Reconov", 613787);
	Student alice = new Student("Alice", 234544);
	Student malcom = new Student("MalcomX", 325934);
	Student[] students = {recon, alice, malcom};
	
	Section fppA = new Section();
	Section mppA = new Section();
	Section mppB = new Section();
	Section[] sections = {fppA, mppA, mppB};
	
	public static void main(String[] args) {
		Main m = new Main();
		m.fetchTestingData();
		
		for(int s = 0; s < m.students.length; s++)
			System.out.println(m.getTranscript(m.students[s]));
		
		System.out.println("Grades for fpp section : " + m.getGrades(m.fppA));
		System.out.println("Grades for Mpp section : " + m.getGrades(m.mppA) + " "+ m.getGrades(m.mppB));
		
		System.out.println("Courses that recon took: " + m.getCourseNames(m.recon));
		
		System.out.println(m.studentsWithGrade("B+"));
		
		System.out.println("Students with A " + studentsWithGradeFromSection(m.mppA, "A"));
		System.out.println("Students with B " + studentsWithGradeFromSection(m.mppA, "B"));
	}
	
	private void fetchTestingData() {
		
		fppA.setCourseName("Fundamental of programming prac Z   ");
		mppA.setCourseName("Modern Programming practices By Y   ");
		mppB.setCourseName("Modern Programming practices By X   ");
		
		fppA.setSectionNumber(6001);
		mppA.setSectionNumber(8293);
		mppB.setSectionNumber(2345);
		
		
		TranscriptEntry[] entries =
			{new TranscriptEntry(),new TranscriptEntry(),new TranscriptEntry(),
					new TranscriptEntry(),new TranscriptEntry(),new TranscriptEntry()};
		
		entries[0] = new TranscriptEntry(recon, fppA, "A+");
		entries[1] = new TranscriptEntry(recon, mppA, "A");
		entries[2] = new TranscriptEntry(alice, mppB, "B+");
		entries[3] = new TranscriptEntry(malcom, mppA, "B");
		
	}
	
	private List<String> studentsWithGrade(String thisGrade){
		List<String> queriedStudents = new ArrayList<>();
		
		for(Student eachStudent : students)
			for(TranscriptEntry eachCourse : eachStudent.getGrades())
				if(eachCourse.getGrade().equals(thisGrade))
					queriedStudents.add(eachStudent.getName());
		
		return queriedStudents;
	}
	
	private static List<String> studentsWithGradeFromSection(Section thisSection, String thisGrade){
		List<String> queriedStudents = new ArrayList<>();
		
		for(TranscriptEntry eachStudent : thisSection.getGradesheet())
			if(eachStudent.getGrade().equals(thisGrade))
				queriedStudents.add(eachStudent.getStudent().getName());
				
		return queriedStudents;
	}
	
	private Transcript getTranscript(Student s) { return s.getTranscript();	}
	
	private List<String> getCourseNames(Student s) {
		List<TranscriptEntry> all = s.getGrades();
		List<String> courseNames = new ArrayList<>();
		
		for(TranscriptEntry t : all)
			courseNames.add(t.getSection().getName());
		
		return courseNames;
	}
	
	private List<String> getGrades(Section s){
		List<String> gradeList = new ArrayList<>();
		
		for(TranscriptEntry t : s.getGradesheet())
			gradeList.add(t.getGrade());
		
		return gradeList;
	}
}
