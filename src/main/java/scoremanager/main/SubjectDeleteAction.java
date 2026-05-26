package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session=req.getSession();
		SubjectDao sDao=new SubjectDao();
		
		Teacher teacher=(Teacher) session.getAttribute("user");
		if(teacher==null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/main/Login.action");
			return;
		}
		String cd=req.getParameter("cd");
		School school=teacher.getSchool();
		
		Subject subject =sDao.get(cd, school);
		if(subject != null) {
			req.setAttribute("subject_cd", subject.getCd());
			req.setAttribute("subject_name", subject.getName());
		}
		
		req.getRequestDispatcher("/subject_delete.jsp").forward(req, res);
	}
	

}
