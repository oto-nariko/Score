package dao;
import java.sql.ResultSet;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
	private String baseSql;
	
	public List<TestListSubject> postFilter(
			ResultSet rSet){
		return null;
	}
	public List<TestListSubject> filter(
			int entYear,
			String calssNum,
			Subject subject,
			School school
			){
		return null;
	}
	
	
}