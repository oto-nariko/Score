package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
	
	//共通のsql
	private String baseSql=
			"select t.*,s.name as student_name,s.ent_year as ent_year,s.class_num as class_num "
			+ "from test t "
			+ "join student s on t.student_no=s.no and t.school_cd=s.school_cd "
			+"where s.ent_year=? "
			+"and s.class_num=? "
			+"and t.subject_cd=? "
			+"and t.school_cd=?";
	
	//ResultSetをリストに変換するメソッド
	public List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
	    List<TestListSubject> list = new ArrayList<>();
	    Map<String, TestListSubject> map = new LinkedHashMap<>();

	    while (rSet.next()) {
	        String studentNo = rSet.getString("student_no");

	        TestListSubject test = map.get(studentNo);
	        if (test == null) {
	            test = new TestListSubject();
	            test.setEntYear(rSet.getInt("ent_year"));
	            test.setStudentNo(studentNo);
	            test.setStudentName(rSet.getString("student_name"));
	            test.setClassNum(rSet.getString("class_num"));
	            map.put(studentNo, test);
	        }

	        test.putPoint(rSet.getInt("no"), rSet.getInt("point"));
	    }

	    list.addAll(map.values());
	    return list;
	}

	//入学年度、クラス、科目、、学校コードで絞り込むメソッド
	public List<TestListSubject> filter(
			int entYear,
			String classNum,
			Subject subject,
			School school
			)throws Exception{
		
		//検索結果を入れるリスト
		List<TestListSubject> list=new ArrayList<>();
		//データベース接続
		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement(baseSql);

		st.setInt(1,entYear);
		st.setString(2,classNum);
		st.setString(3,subject.getCd());
		st.setString(4,school.getCd());
		
		//sql実行
		ResultSet rs=st.executeQuery();
		list=postFilter(rs);

		st.close();
		con.close();
		
		//検索結果を返す
		return list;
	}
}	