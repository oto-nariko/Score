package scoremanager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;

/*
 * ログイン画面を表示させるAction
 * http://localhost:8080/Score/scoremanager/Login.actionで起動
 */

public class LoginAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//login.jspにフォワード
		req.getRequestDispatcher("/login.jsp").forward(req, res);
		
	}

}
