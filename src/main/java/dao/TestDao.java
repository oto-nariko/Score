package dao;

import java.sql.ResultSet;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import scoremanager.main.Test;

public class TestDao extends Dao {
	
	private string baseSql;
	
	public Test get(
			Student student,
			Subject subject,
			School school,
			int no
			) {
			return null;
			}
	
	public List<Test> postFilter(
			ResultSet rs,
			School school
			){
		return null;
			}
	public List<Test> filter(
			int entYear,
			String classNum,
			Subject subject,
			int num,
			School school
			){
		return null;
			}
	public boolean save(List<Test> list) {
	       return false;
	    }
	public boolen save(
			Test test,
			Connectionn connection
			) {
				
			return false;
	}
	
	

}


