package scoremanager.main;

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

public class TestDeleteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//セッションからユーザーデータを取得
		HttpSession session=req.getSession();
		//セッションが切れているときはログイン画面に
		Teacher teacher=(Teacher) session.getAttribute("user");
		if(teacher==null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/main/Login.action");
			return;
		}
		
		TestDao tDao= new TestDao();
		
		//jspから値を受け取る
		//String entYearStr = req.getParameter("f1");
		//String classNum = req.getParameter("f2");
		String subjectCd = req.getParameter("f3");
		String count = req.getParameter("f4");
		String studentNo = req.getParameter("student_no");
		
		//int entYear = Integer.parseInt(entYearStr);
		int num = Integer.parseInt(count);
		School school=teacher.getSchool();
		
		//学生情報と科目情報をセット
		Student student = new Student();
		student.setNo(studentNo);
		Subject subject = new Subject();
		subject.setCd(subjectCd);
		
		//テスト情報を取得
		Test test = tDao.get(student, subject, school, num);
		
		//箱詰めしてフォワード
		req.setAttribute("student_no", test.getStudent().getNo());
		req.setAttribute("student_name", test.getStudent().getName());
		req.setAttribute("subject_cd", test.getSubject().getCd());
		req.setAttribute("subject_name", test.getSubject().getName());
		req.setAttribute("no", test.getNo());
		req.getRequestDispatcher("/test_delete.jsp").forward(req, res);
	}

}
