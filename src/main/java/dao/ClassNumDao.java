package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {
	
	public ClassNum  get(String class_num, School school) {
		
	}
	
	/*
	 * 学校からクラス番号を取得して
	 * 文字列型リストで返す
	 */
	public List<String> filter (School school) throws Exception{
		List<String> list = new ArrayList<>();
		Connection con = getConnection();
		
		String sql = "select class_num from class_num where school_cd=? order by class_num asc";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, school.getCd());
		
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			list.add(rs.getString("class_num"));
		}
		
		if (st != null) st.close();
		if (con != null) con.close();
		
		return list;
	}
	public boolean save(ClassNum classNum) {
		
	}
	public boolean save(ClassNum classNum, String newClassNum) {
		
	}
	
}
