package scoremanager.main;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
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
			res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
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
		
		//クラスが選択されてないとき
		if (classNum == null || classNum.equals("")) {
			req.setAttribute("errors", "クラスを選択してください");
			
			ClassNumDao cDao = new ClassNumDao();
			List<String> class_list = cDao.filter(teacher.getSchool());
			
			req.setAttribute("ent_year", entYear);
			req.setAttribute("no", no);
			req.setAttribute("name", name);
			req.setAttribute("class_num", classNum);
			req.setAttribute("is_attend", isAttend);
			req.setAttribute("class_list", class_list);
			
			req.getRequestDispatcher("/student_update.jsp").forward(req, res);
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
