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
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		//ログインしていない場合はログイン画面に飛ばす
		if (teacher == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
			return;
		}
		
		School school = teacher.getSchool();
		
		String subjectCd = req.getParameter("subject");
		String countStr = req.getParameter("count");
		String classNum = req.getParameter("classNum");
		
		if (subjectCd == null || subjectCd.equals("") || 
				countStr == null || countStr.equals("") || 
				classNum == null || classNum.equals("")) {
				
				//エラーメッセージをセット
				req.setAttribute("errors", "科目、クラス、テスト回数をすべて選択してください");
				
				//検索前の状態に戻すため、検索用プルダウンの中身を再準備してセット
				SubjectDao subDao = new SubjectDao();
				ClassNumDao cDao = new ClassNumDao();
				req.setAttribute("subject_list", subDao.filter(school)); // 科目リスト
				req.setAttribute("class_list", cDao.filter(school)); // クラスリスト
				
				//選択されていた値をキープして戻す
				req.setAttribute("f2", classNum);
				req.setAttribute("f3", subjectCd);
				req.setAttribute("f4", countStr);
				
				// 元の画面にフォワードして
				req.getRequestDispatcher("/test_regist.jsp").forward(req, res);
				return;
		}
		
		//intにする
		int count = Integer.parseInt(countStr);
		
		String[] studentNoArray = req.getParameterValues("regist");
		List<Test> testList = new ArrayList<>();
		
		Subject subject = new Subject();
		subject.setCd(subjectCd);
		
		boolean hasError = false;
		
		if (studentNoArray != null) {
			for (String studentNo : studentNoArray) {
				
				// 各学生の点数入力欄（point_学生番号）から値を取得する
				String pointStr = req.getParameter("point_" + studentNo);
				int point = -1; // 入力されていなければ -1
				
				if (pointStr != null && !pointStr.equals("")) {
					point = Integer.parseInt(pointStr);
					
					if (point < 0 || point > 100) {
						hasError = true;
					}
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
		
		if (hasError) {
			req.setAttribute("errors", "0~100の範囲で入力してください");
			
			
			req.setAttribute("students", testList);
			
			req.setAttribute("f2", classNum);
			req.setAttribute("f3", subjectCd);
			req.setAttribute("f4", count);
			
			req.getRequestDispatcher("/test_regist.jsp").forward(req, res);
			return;
		}
		
		TestDao tDao = new TestDao();
		tDao.save(testList);
		
		req.getRequestDispatcher("/test_regist_done.jsp").forward(req, res);
	}

}
