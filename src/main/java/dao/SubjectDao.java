package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {
	public Subject get(String cd, School school) throws Exception {
		;
		
	}
	
	public List<Subject> filter(School school) throws Exception{
		List<Subject> list=new ArrayList<>();
		Connection connection=getConnection();
		PreparedStatement statement=null;
		try {
	        // SCHOOL_CDを使って絞り込む
	        statement = connection.prepareStatement("select * from subject where SCHOOL_CD=?");
	        statement.setString(1, school.getCd());
	        ResultSet rSet = statement.executeQuery();

	        while (rSet.next()) {
	            Subject subject = new Subject();
	            //「CD」「NAME」を使って値を取得
	            subject.setCd(rSet.getString("CD"));
	            subject.setName(rSet.getString("NAME"));
	            subject.setSchool(school);
	            list.add(subject);
	        }
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        if (statement != null) statement.close();
	        if (connection != null) connection.close();
	    }
	    return list;
	}
	
	public boolean save(Subject subject) {
		
	}
	public boolean delete(Subject subject) {
		
	}
}
