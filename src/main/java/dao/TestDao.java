package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {
	
	private String baseSql = """
		    select s.no as student_no, s.name as student_name, s.ent_year, s.class_num, s.is_attend, t.point, t.no as test_num, t.subject_cd
		    from student s
		    left join test t on s.no = t.student_no
		      and t.subject_cd = ?
		      and t.no = ?
		    where s.school_cd = ?
		    """;
	
	public Test get(Student student, Subject subject, School school, int no) {
			return null;
			}
	
	public List<Test> postFilter(ResultSet rSet, School school) throws Exception {
		List<Test> list = new ArrayList<>();
		
		while (rSet.next()) {
			Student student = new Student();
			student.setNo(rSet.getString("student_no"));
			student.setName(rSet.getString("student_name"));
			student.setEntYear(rSet.getInt("ent_year"));
			student.setClassNum(rSet.getString("class_num"));
			student.setAttend(rSet.getBoolean("is_attend"));
			student.setSchool(school);
			
			Subject subject =new Subject();
			subject.setCd(rSet.getString("subject_cd"));
			
			Test test = new Test();
			test.setStudent(student);
			test.setSubject(subject);
			test.setSchool(school);
			test.setNo(rSet.getInt("test_num"));
			int point = rSet.getInt("point");
			if (rSet.wasNull()) {
				test.setPoint(-1);
			} else {
				test.setPoint(point);
			}
			
			list.add(test);
		}
		
		return list;
	}
	
	public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
		List<Test> list = new ArrayList<>();
		Connection con = getConnection();
		
		String sql = baseSql + "and s.ent_year = ? and s.class_num = ? order by s.no asc";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		int index = 1;
		st.setString(index++,subject.getCd());
		st.setInt(index++, num);
		st.setString(index++, school.getCd());
		st.setInt(index++, entYear);
		st.setString(index++, classNum);
		
		ResultSet rSet = st.executeQuery();
		
		list = postFilter(rSet, school);
		
		if (rSet != null) rSet.close();
		if (st != null) st.close();
		if (con != null) con.close();
		
		return list;
	}
	
	/*
	 * 成績データをまとめて保存するメソッド
	 */
	public boolean save(List<Test> list) throws Exception {
		boolean result = true;
		Connection con = getConnection();
		
		try {
			for (Test test : list) {
				boolean line = save(test, con);
				if (!line) {
					result = false;
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (con != null) con.close();
		}
		
		return result;
	}
	
	/*
	 * 1人分の成績データを保存するメソッド
	 */
	public boolean save(Test test, Connection connection) throws Exception {
		PreparedStatement stUpdate = null;
		PreparedStatement stInsert = null;
		boolean result = false;
		
		try {
			//まずはUPDATEを試みる
			String updateSql = "update test set point = ? where student_no = ? and subject_cd = ? and no = ? and school_cd = ?";
			stUpdate = connection.prepareStatement(updateSql);
			stUpdate.setInt(1, test.getPoint());
			stUpdate.setString(2, test.getStudent().getNo());
			stUpdate.setString(3, test.getSubject().getCd());
			stUpdate.setInt(4, test.getNo());
			stUpdate.setString(5, test.getSchool().getCd());
			
			int rowCount = stUpdate.executeUpdate();
			
			//もしUPDATEされた件数が0件なら、まだデータがない人なのでINSERTする
			if (rowCount == 0) {
				String insertSql = "insert into test (student_no, subject_cd, no, point, school_cd, class_num) values (?, ?, ?, ?, ?, ?)";
				stInsert = connection.prepareStatement(insertSql);
				stInsert.setString(1, test.getStudent().getNo());
				stInsert.setString(2, test.getSubject().getCd());
				stInsert.setInt(3, test.getNo());
				stInsert.setInt(4, test.getPoint());
				stInsert.setString(5, test.getSchool().getCd());
				stInsert.setString(6, test.getStudent().getClassNum()); // クラス番号も完璧！
				
				int line = stInsert.executeUpdate();
				if (line > 0) {
					result = true;
				}
			} else {
				// UPDATEが成功した場合
				result = true;
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (stUpdate != null) stUpdate.close();
			if (stInsert != null) stInsert.close();
		}
		return result;
	}
	
	

}


