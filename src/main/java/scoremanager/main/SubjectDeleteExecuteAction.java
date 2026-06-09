package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session =req.getSession();
		SubjectDao sDao=new SubjectDao();
		
		Teacher teacher = (Teacher) session.getAttribute("user");
		//ログインしていない場合はログイン画面に飛ばす
		if (teacher == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
			return;
		}
		String cd=req.getParameter("subject_cd");
		School school=teacher.getSchool();
		
		Subject subject=new Subject();
		subject.setCd(cd);
		subject.setSchool(school);
		
		sDao.delete(subject);
		
		req.getRequestDispatcher("/subject_delete_done.jsp").forward(req, res);
		
	}

}
