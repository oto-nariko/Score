package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//セッションから先生情報を取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		
		//ログインしていない場合はログイン画面に飛ばす
		if (teacher == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/main/Login.action");
			return;
		}
		
		//jspから値を受け取る
		int entYear = Integer.parseInt(req.getParameter("ent_year"));
		String no = req.getParameter("no");
		String name = req.getParameter("name");
		String classNum = req.getParameter("class_num");
		String isAttendStr = req.getParameter("is_attend");
		boolean isAttend = false;
		if (isAttendStr != null) {
			isAttend = true;
		}
		
		Student student = new Student();
		student.setEntYear(entYear);
		student.setNo(no);
		student.setName(name);
		student.setClassNum(classNum);
		student.setAttend(isAttend);
		student.setSchool(teacher.getSchool());
		
		//DBを更新
		StudentDao sDao = new StudentDao();
		sDao.save(student);
		
		req.getRequestDispatcher("/student_update_done.jsp").forward(req, res);
	}

}
