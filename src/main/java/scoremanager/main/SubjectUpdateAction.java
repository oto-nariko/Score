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
		}else {
			// 【原因特定用の仕込み】
			// もし画面が空っぽになるなら、こっちのブロックが動いています！
			req.setAttribute("cd", "未取得:" + (cd != null ? cd : "URLのcd引数がnullです"));
			req.setAttribute("name", "学校コード「" + (school != null ? school.getCd() : "null") + "」の中にこの科目は存在しません");
		}
		
		req.getRequestDispatcher("/subject_update.jsp").forward(req, res);
		
	}

}
