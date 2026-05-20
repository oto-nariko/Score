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
		Subject subject = null;
		Connection connection = getConnection();
		PreparedStatement statement = null;
		
		try {
			// 主キー（CD）と学校（SCHOOL_CD）で科目を特定して検索するSQL
			statement = connection.prepareStatement("select*from subject where CD=? and SCHOOL_CD=?");
			statement.setString(1, cd);
			statement.setString(2, school.getCd());
			ResultSet rSet = statement.executeQuery();
			
			// 該当するデータが見つかった場合、Subjectオブジェクトに値をセット
			if (rSet.next()) {
				subject = new Subject();
				subject.setCd(rSet.getString("CD"));
				subject.setName(rSet.getString("NAME"));
				subject.setSchool(school);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();	
		}
		
		return subject;
	}
	
	public List<Subject> filter(School school) throws Exception {
		List<Subject> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
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
	
	public boolean save(Subject subject) throws Exception {
		boolean result = false;
		Connection con = getConnection();
		PreparedStatement st = null;
		
		try {
			// 同じ主キーのデータが既に存在するか get メソッドで確認する
			Subject exist = get(subject.getCd(), subject.getSchool());
			
			String sql;
			if (exist == null) {
				// データがなければ新規登録（INSERT）
				sql = "insert into subject(cd, name, school_cd) values(?,?,?)";
				st = con.prepareStatement(sql);
				st.setString(1, subject.getCd());
				st.setString(2, subject.getName());
				st.setString(3, subject.getSchool().getCd());
			} else {
				// データが既に存在していれば更新（UPDATE）
				sql = "update subject set name=? where cd=? and school_cd=?";
				st = con.prepareStatement(sql);
				st.setString(1, subject.getName());
				st.setString(2, subject.getCd());
				st.setString(3, subject.getSchool().getCd());
			}
			
			int line = st.executeUpdate();
			if (line > 0) {
				result = true;
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (st != null) st.close();
			if (con != null) con.close();
		}
		return result;
	}

	public boolean delete(Subject subject) {
		return false;
	}
}