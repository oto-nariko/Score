package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session=req.getSession();
		SubjectDao sDao=new SubjectDao();
		
		Teacher teacher=(Teacher) session.getAttribute("user");
		if (teacher==null) {
			res.sendRedirect(req.getContextPath()+"/scoremanager/main/Login.action");
			return;
			
		}
		String cd=req.getParameter("cd");
		School school=teacher.getSchool();
		//変更前の科目データを取得
		Subject subject=sDao.get(cd,school);
		
		
		if(subject !=null) {
			req.setAttribute("cd", subject.getCd());
			req.setAttribute("name", subject.getName());		
		}
		req.getRequestDispatcher("/subject_update.jsp").forward(req, res);
		
	}

}
