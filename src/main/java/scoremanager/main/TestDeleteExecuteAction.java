package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestDeleteExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//セッションからユーザーデータを取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		
		//ログインしていない場合はログイン画面に飛ばす
		if (teacher == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
			return;
		}
		
		//jspから受け取る
		String studentNo = req.getParameter("student_no");
		String subjectCd = req.getParameter("subject_cd");
		int no = Integer.parseInt(req.getParameter("no"));
		
		//生徒情報と科目情報をセット
		Student student= new Student();
		student.setNo(studentNo);
		Subject subject = new Subject();
		subject.setCd(subjectCd);
		
		//テスト情報をセット
		Test test = new Test();
		test.setStudent(student);
		test.setSubject(subject);
		test.setNo(no);
		test.setSchool(teacher.getSchool());
		
		TestDao tDao = new TestDao();
		tDao.delete(test);
		
		req.getRequestDispatcher("/test_delete_done.jsp").forward(req, res);

	}

}
