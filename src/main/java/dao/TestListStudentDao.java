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
	private String baseSql =
	        "select t.*, sub.name as subject_name "
	        + "from test t "
	        + "left join subject sub "
	        + "on t.subject_cd = sub.cd and t.school_cd = sub.school_cd "
	        + "where t.student_no = ?";
	
	//ResultSetをlistに変換するメソッド
	public List<TestListStudent> postFIlter(
			ResultSet rSet
			)throws Exception{
		
		//検索結果を入れるリスト
		List<TestListStudent> list=new ArrayList<>();
		
		while (rSet.next()){
			TestListStudent test=new TestListStudent();
			
			test.setSubjectName(rSet.getString("subject_name"));
			test.setSubjectCd(rSet.getString("SUBJECT_CD"));
			test.setNum(rSet.getInt("NO"));
			test.setPoint(rSet.getInt("POINT"));
			
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
