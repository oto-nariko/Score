package scoremanager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

/*
 * データベースを取得、照合し、画面を遷移させる
 * 現在はログイン後headerに飛ぶようになっている
 * menu完成後書き換える
 * */

public class LoginExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//セッションを取得
		HttpSession session = req.getSession();
		
		
		try {
			//入力されたidとpwを取得
			String id = req.getParameter("id");
			String password = req.getParameter("password");
			
			//入力されたid,pwを元に認証
			TeacherDao dao = new TeacherDao();
			Teacher teacher = dao.login(id, password);
			
			if (teacher!=null) {
				session.setAttribute("user", teacher);
				req.getRequestDispatcher("/header.jsp").forward(req, res);//後で/menu.jspに変更してください
			} else {
				req.setAttribute("errors", "IDまたはパスワードが間違っています");
				req.getRequestDispatcher("/login.jsp").forward(req, res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("error.jsp").forward(req, res);
		}

	}
}
