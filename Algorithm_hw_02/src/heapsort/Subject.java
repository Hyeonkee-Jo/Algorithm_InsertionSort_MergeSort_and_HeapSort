package heapsort;

public class Subject {
	
	int subject_no;
	String subject_name;
	
	public Subject() {
		this.subject_no = 0;
		this.subject_name = null;
	}
	
	public Subject(int subject_no, String subject_name) {
		this.subject_no = subject_no;
		this.subject_name = subject_name;
	}
	
	public int getSubjectNo() {
		return this.subject_no;
	}
	
	public void setSubjectNo(int subject_no) {
		this.subject_no =  subject_no;
	}
	
	public String getSubjectName() {
		return this.subject_name;
	}
	
	public void setSubjectName(String subject_name) {
		this.subject_name = subject_name;
	}
	
	public String toString() {
		return Integer.toString(this.subject_no)+ ", " + this.subject_name;
	}

}
