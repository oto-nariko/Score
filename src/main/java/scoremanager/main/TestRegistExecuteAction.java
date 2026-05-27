package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();
		
		String subjectCd = req.getParameter("subject");
		int count = Integer.parseInt(req.getParameter("count"));
		String classNum = req.getParameter("classNum");
		
		String[] studentNoArray = req.getParameterValues("regist");
		List<Test> testList = new ArrayList<>();
		
		Subject subject = new Subject();
		subject.setCd(subjectCd);
		
		if (studentNoArray != null) {
			for (String studentNo : studentNoArray) {
				
				// 各学生の点数入力欄（point_学生番号）から値を取得する
				String pointStr = req.getParameter("point_" + studentNo);
				int point = -1; // 入力されていなければ -1
				
				if (pointStr != null && !pointStr.equals("")) {
					point = Integer.parseInt(pointStr);
				}
				
				// Student Bean を作成
				Student student = new Student();
				student.setNo(studentNo);
				student.setClassNum(classNum);
				
				// Test Bean に情報を詰め込む
				Test test = new Test();
				test.setStudent(student);
				test.setSubject(subject);
				test.setNo(count);
				test.setPoint(point);
				test.setSchool(school);
				
				// リストに追加
				testList.add(test);
			}
		}
		
		TestDao tDao = new TestDao();
		tDao.save(testList);
		
		req.getRequestDispatcher("/test_regist_done.jsp").forward(req, res);
	}

}
