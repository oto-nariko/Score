package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session=req.getSession();
		Teacher teacher =(Teacher) session.getAttribute("user");
		
		if (teacher == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/main/Login.action");
			return;
		}
		
		String cd =req.getParameter("cd");
		String name=req.getParameter("name");
		//エラーメッセージを格納
		Map<String, String> errors=new HashMap<>();
		SubjectDao sDao=new SubjectDao();
		
		//科目コードが３文字であるかチェック
		if (cd==null || cd.length() !=3) {
			//エラー文言の設定
			errors.put("cd", "科目コードは3文字で入力してください");
		}else {
			//文字コードが重複してないか
			Subject existSubject=sDao.get(cd, teacher.getSchool());
			if(existSubject !=null) {
				errors.put("cd", "科目コードが重複しています");
			}
		}
		// エラーが1つでもある場合は、入力値を残したまま登録画面（JSP）に戻る
		
		if(!errors.isEmpty()) {
			req.setAttribute("cd", cd);
			req.setAttribute("name", name);
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("/subject_create.jsp").forward(req, res);
			return;
		}
		//エラーなければ、あたらしくSubjectオブジェクトを使って値をセット
		Subject subject=new Subject();
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(teacher.getSchool());
		
		//dbに保存
		sDao.save(subject);
		
		//フォワード
		req.getRequestDispatcher("/subject_create_done.jsp").forward(req, res);
		
	}

}
