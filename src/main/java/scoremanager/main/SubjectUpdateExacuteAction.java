package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExacuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session=req.getSession();
		SubjectDao sDao=new SubjectDao();
		
		//エラーメッセージ格納用
		Map<String,String> errors=new HashMap<>();
		
		//ログインチェック
		Teacher teacher = (Teacher) session.getAttribute("user");
		if (teacher == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/main/Login.action");
			return;
		}
		//画面の入力値を取得
		String cd=req.getParameter("cd");
		String name=req.getParameter("name");
		School school=teacher.getSchool();
		
		if(name==null||name.isEmpty()) {
			errors.put("name", "科目名を入力してください");
			
		}
		if(errors.isEmpty()) {
			Subject existSubject=sDao.get(cd, school);
			if(existSubject==null) {
				errors.put("name","科目データが存在していません");			
			}
		}
		
		if(!errors.isEmpty()) {
			req.setAttribute("errors", errors);
			req.setAttribute("cd", cd);
			req.setAttribute("name", name);
			req.getRequestDispatcher("/subject_update.jsp").forward(req, res);
		}
		else {
			Subject subject=new Subject();
			subject.setCd(cd);
			subject.setName(name);
			subject.setSchool(school);
			
			sDao.save(subject);
			
			req.getRequestDispatcher("/subject_update_done.jsp").forward(req, res);
		}	
		
	}

}
