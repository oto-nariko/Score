package scoremanager.main;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action{

	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res) throws Exception {
		HttpSession session=req.getSession();
		Teacher teacher=(Teacher) session.getAttribute("user");
		//ログインしていない場合はログイン画面に飛ばす
		if (teacher == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
			return;
		}
		
		//jspから学生番号を受け取る
		String no = req.getParameter("no");
		
		//学生の詳細データを取得
		StudentDao sDao = new StudentDao();
		Student student = sDao.get(no);
		School school=teacher.getSchool();
		
		//クラスの一覧を取得
		ClassNumDao cDao = new ClassNumDao();
		List<String> class_list = cDao.filter(school);
		
		//箱詰めしてフォワード
		req.setAttribute("ent_year", student.getEntYear());
		req.setAttribute("no", student.getNo());
		req.setAttribute("name", student.getName());
		req.setAttribute("class_num", student.getClassNum());
		req.setAttribute("is_attend", student.isAttend());
		req.setAttribute("class_list", class_list);
		
		req.getRequestDispatcher("/student_update.jsp").forward(req, res);
	}
}
