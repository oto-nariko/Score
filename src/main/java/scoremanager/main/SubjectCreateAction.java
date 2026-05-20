package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class SubjectCreateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		// 1. セッションからログインユーザー情報を取得
				HttpSession session = req.getSession();
				Teacher teacher = (Teacher) session.getAttribute("user");
				
				// 2. ログインしていない場合はログイン画面に飛ばす
				if (teacher == null) {
					res.sendRedirect(req.getContextPath() + "/scoremanager/main/Login.action");
					return;
				}

				// 3. 科目登録画面（JSP）を呼び出す
				req.getRequestDispatcher("/subject_create.jsp").forward(req, res);
	}

}
