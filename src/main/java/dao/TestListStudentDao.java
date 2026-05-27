package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao{
	private String baseSql= 
			"select t.*,s.name as subject_name "
			+"from test t "
			+"join subject s on t.subject_cd=s.cd and t.school_cd=s.school_cd "
			+"where t.student_no=?";
	
	public List<TestListStudent> postFIlter(
			ResultSet rSet
			)throws Exception{
		List<TestListStudent> list=new ArrayList<>();
		
		while (rSet.next()){
			TestListStudent test=new TestListStudent();
			
			test.setSubjectCd(rSet.getString("subject_cd"));
			test.setSubjectName(rSet.getString("subject_name"));
			test.setNum(rSet.getInt("no"));
			test.setPoint(rSet.getInt("point"));
			
			list.add(test);
		}
		
		return list;
	}
	
	public List<TestListStudent> filter(
			Student student
			)throws Exception{
		List<TestListStudent> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement(baseSql);
		st.setString(1, student.getNo());
		
		ResultSet rs=st.executeQuery();
		
		list=postFIlter(rs);
		
		st.close();
		con.close();
		
		return list;
	}
}
