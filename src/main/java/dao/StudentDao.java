package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {
	
	private String baseSql = "select * from student where school_cd=?";
	
	
	
	public Student get(String no) throws Exception {

	}
	
	private List<Student> postFilter(ResultSet resultSet, School school) throws Exception {
		
		List<Student> list = new ArrayList<>();
		
		while (resultSet.next()) {
			Student student = new Student();
			
			student.setNo(resultSet.getString("no"));
			student.setName(resultSet.getString("name"));
			student.setEntYear(resultSet.getInt("ent_year"));
			student.setClassNum(resultSet.getString("class_num"));
			student.setAttend(resultSet.getBoolean("is_attend"));
			
			student.setSchool(school);
			
			list.add(student);
		}
		
		return list;
	}
	
	//入学年度、クラス番号、在籍フラグで絞り込み
	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
		List<Student> list = new ArrayList<>();
		Connection con = getConnection();
		
		//baseSqlの続きを組み立てる
		String sql = baseSql;
		if (entYear != 0) {
			sql += " and ent_year=?";
		}
		if (classNum != null) {
			sql += " and class_num=?";
		}
		if (isAttend) {
			sql += " and is_attend=true";
		} else {
			sql += " and is_attend=false";
		}
		
		//学生番号順に並べ替え
		sql += " order by no asc";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		//値のセット
		int index = 1;
		st.setString(index++, school.getCd());
		
		if (entYear != 0) {
			st.setInt(index++, entYear);
		}
		if (classNum != null) {
			st.setString(index++, classNum);
		}
		
		//実行してpostFilterに渡す
		ResultSet re = st.executeQuery();
		list = postFilter(re, school);
		
		if (st != null) st.close();
		if (con != null) con.close();
		
		return list;
	}
	
	//入学年度、在籍フラグで絞り込み
	public List<Student> filter(School school, int entYear,boolean isAttend) throws Exception {
		return filter(school, entYear, null, isAttend);
	}
	
	//在籍フラグで絞り込み
	public List<Student> filter(School school, boolean isAttend) throws Exception {
		return filter(school, 0, null, isAttend);
	}
	
	public boolean save(Student student) throws Exception {
		
	}
}
