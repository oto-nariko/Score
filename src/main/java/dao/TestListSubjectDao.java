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
	private String baseSql;
	
	public List<TestListSubject> postFilter(
			ResultSet rSet)throws Exception{
		List<TestListSubject> list=new ArrayList<>();

		while (rSet.next()){

			TestListSubject test=new TestListSubject();

			test.setEntYear(rSet.getInt("ent_year"));
			test.setStudentNo(rSet.getString("student_no"));
			test.setStudentName(rSet.getString("student_name"));
			test.setClassNum(rSet.getString("class_num"));
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
		
		//検索結果を入れる
		List<TestListSubject> list=new ArrayList<>();
		//データベース連携
		Connection con = getConnection();
		
		//sql
		PreparedStatement st;
		st=con.prepareStatement(
				"select t.*, s.name as student_name "
				+ "from test t "
				+ "join student s on t.student_no = s.no "
				+ "where t.ent_year=? "
				+ "and t.class_num=? "
				+ "and t.subject_cd=? "
				+ "and t.school_cd=?"
			);
		st.setInt(1, entYear);
		st.setString(2, classNum);
		st.setString(3, subject.getCd());
		st.setString(4, school.getCd());
		
		//sql実行
		ResultSet rs=st.executeQuery();
		list=postFilter(rs);

		st.close();
		con.close();
		
		//検索結果を返す
		return list;
	}
}
		