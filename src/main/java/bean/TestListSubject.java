package bean;

import java.util.Map;

public class TestListSubject {
	private int entYear;
	private String no;
	private String name;
	private String classNum;
	private Map<Integer,Integer> points;
	
	
	public int getEntYear() {
		return entYear;
	}
	public void setEntYear(int entYear) {
		this.entYear=entYear;
	}
	public String getNo() {
		return no;
	}
	public void setStudentNo(String No) {
		this.no=no;
	}
	public String getName() {
		return name;
	}
	public void setStudentName(String name) {
		this.name=name;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum=classNum;
	}
	public Map<Integer,Integer> getPoints(){
		return points;
	}
	public void setPoints(Map<Integer,Integer> points) {
		this.points=points;
	}
	public String getPoint(int key) {
		return String.valueOf(points.get(key));
	}
	public void putPoint(int key, int value) {
		points.put(key,value);
	}
}
