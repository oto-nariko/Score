package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
		
		//クラスの一覧を取得
		ClassNumDao cDao = new ClassNumDao();
		ClassNum classNum = cDao.get()
	}
}
