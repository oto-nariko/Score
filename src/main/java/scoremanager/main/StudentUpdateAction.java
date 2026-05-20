package scoremanager.main;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action{

	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res) throws Exception {
		//jspから学生番号を受け取る
		String no = req.getParameter("no");
		
		//学生の詳細データを取得
		StudentDao sDao = new StudentDao();
		Student student = sDao.get(no);
		School school = student.getSchool();
		
		//クラスの一覧を取得
		ClassNumDao cDao = new ClassNumDao();
		List<String> class_list = cDao.filter(school);
		
		//箱詰めしてフォワード
		req.setAttribute("class_list", class_list);
		req.getRequestDispatcher("/student_update.jsp").forward(req, res);
	}
}
