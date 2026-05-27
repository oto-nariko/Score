package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao{
	
	//共通sql
	private String baseSql= 
			"select t.*,s.name as subject_name "
			+"from test t "
			+"join subject s on t.subject_cd=s.cd and t.school_cd=s.school_cd "
			+"where t.student_no=?";
	
	//ResultSetをlistに変換するメソッド
	public List<TestListStudent> postFIlter(
			ResultSet rSet
			)throws Exception{
		
		//検索結果を入れるリスト
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
	
	//学籍番号で絞込みするメソッド
	public List<TestListStudent> filter(
			Student student
			)throws Exception{
		
		//検索結果を入れるリスト
		List<TestListStudent> list=new ArrayList<>();
		//データベース接続
		Connection con=getConnection();
		//sql準備
		PreparedStatement st;
		st=con.prepareStatement(baseSql);
		st.setString(1, student.getNo());
		//sql実行
		ResultSet rs=st.executeQuery();
		
		list=postFIlter(rs);
		
		st.close();
		con.close();
		//検索結果を返す
		return list;
	}
}
