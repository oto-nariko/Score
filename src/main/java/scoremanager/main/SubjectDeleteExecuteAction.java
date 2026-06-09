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
		//ログインチェック
		Teacher teacher = (Teacher) session.getAttribute("user");
		//ログインしていない場合はログイン画面に飛ばす
		if (teacher == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
			return;
		}
		
		//削除画面から送信されてきた削除対象の科目コードを取得
		String cd=req.getParameter("subject_cd");
		//ログイン中のティーチャーが所属する学校情報を取得
		School school=teacher.getSchool();
		//削除用のSubject型のBeanオブジェクトを作成して特定に必要なデータをセット
		Subject subject=new Subject();
		subject.setCd(cd);			//消したい科目コードセット
		subject.setSchool(school);	//学校情報せっと
		//データベースから該当する科目のレコードを削除する
		sDao.delete(subject);
		//削除完了画面へ画面遷移
		req.getRequestDispatcher("/subject_delete_done.jsp").forward(req, res);
		
	}

}
