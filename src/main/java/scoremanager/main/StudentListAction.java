package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

/*
 * 抽出条件を画面から受け取って
 * DAOでデータを受け取って
 * JSPに渡すクラス
 */
public class StudentListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//セッションからユーザーデータを取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		//ログインしていない場合はログイン画面に飛ばす
		if (teacher == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
			return;
		}
		
		//検索条件の取得
		String entYearStr = req.getParameter("f1"); //入学年度
		String classNum = req.getParameter("f2"); //クラス番号
		String isAttendStr = req.getParameter("f3"); //在学状況
		//型変換と初期化
		int entYear = 0;
		if (entYearStr != null && !entYearStr.isEmpty()) {
			entYear = Integer.parseInt(entYearStr);
		}
		//在学状況の判定
		boolean isAttend = false;
		if (isAttendStr != null) {
			isAttend = true;
		}
		
		//DBから取得
		StudentDao sDao = new StudentDao();
		ClassNumDao cDao = new ClassNumDao();
		
		List<String> classList = cDao.filter(teacher.getSchool());
		
		List<Student> students = new ArrayList<>();
		if (entYear != 0 && !classNum.equals("0")) { //入学年度とクラスが指定されたとき
			students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
		} else if (entYear != 0 && classNum.equals("0")) { //入学年度のみ指定されたとき
			students = sDao.filter(teacher.getSchool(), entYear, isAttend);
		} else if (entYear == 0 && (classNum == null || classNum.equals("0"))) { //なんも指定されんやったとき
			students = sDao.filter(teacher.getSchool(), isAttend);
		} else { //念のため
			students = sDao.filter(teacher.getSchool(), isAttend);
		}
		
		//JSPに送るためにデータをセット
		req.setAttribute("f1", entYear);
		req.setAttribute("f2", classNum);
		req.setAttribute("f3", isAttend);
		req.setAttribute("class_list", classList);
		req.setAttribute("students", students);
		
		//フォワード
		req.getRequestDispatcher("/student_list.jsp").forward(req, res);
	}

}
