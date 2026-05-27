package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
	private String baseSql=
			"select t.*,s.name as student_name,s.ent_year as ent_year,s.class_num as class_num "
			+ "from test t "
			+ "join student s on t.student_no=s.no and t.school_cd=s.school_cd "
			+"where s.ent_year=? "
			+"and s.class_num=? "
			+"and t.subject_cd=? "
			+"and t.school_cd=?";
	
	public List<TestListSubject> postFilter(
			ResultSet rSet)throws Exception{
		List<TestListSubject> list=new ArrayList<>();

		while (rSet.next()){

			TestListSubject test=new TestListSubject();

			test.setEntYear(rSet.getInt("ent_year"));
			test.setStudentNo(rSet.getString("student_no"));
			test.setStudentName(rSet.getString("student_name"));
			test.setClassNum(rSet.getString("class_num"));
			test.putPoint(rSet.getInt("no"), rSet.getInt("point"));

			list.add(test);
		}

		return list;
	}

	public List<TestListSubject> filter(
			int entYear,
			String classNum,
			Subject subject,
			School school
			)throws Exception{
		
		List<TestListSubject> list=new ArrayList<>();
		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement(baseSql);

		st.setInt(1,entYear);
		st.setString(2,classNum);
		st.setString(3,subject.getCd());
		st.setString(4,school.getCd());
		
		ResultSet rs=st.executeQuery();
		list=postFilter(rs);

		st.close();
		con.close();
		
		return list;
	}
}	