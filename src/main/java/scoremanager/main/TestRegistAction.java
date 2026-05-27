package scoremanager.main;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

<<<<<<< HEAD
import bean.Teacher;
=======
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
>>>>>>> branch 'master' of https://github.com/oto-nariko/Score
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
<<<<<<< HEAD
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		
=======
		//セッションからユーザーデータを取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		//jspから値を受け取る
		String entYearStr = req.getParameter("f1");
		String classNum = req.getParameter("f2");
		String subject = req.getParameter("f3");
		String count = req.getParameter("f4");
		
		//nullではなく空文字の時(一度入力したことがあるとき)
		if (entYearStr != null) {
			
			req.setAttribute("f1", entYearStr);
			req.setAttribute("f2", classNum);
			req.setAttribute("f3", subject);
			req.setAttribute("f4", count);
			
			//入学年度、クラス、科目、回数のいずれかが未入力の場合
			if (entYearStr.equals("") || classNum.equals("") || 
					subject.equals("") || count.equals("")) {
				
				//エラーメッセージをセット
				req.setAttribute("errors", "入学年度とクラスと科目と回数を選択してください");
				
				req.getRequestDispatcher("/test_regist.jsp").forward(req, res);
				return;
			}
			
			int entYear = Integer.parseInt(entYearStr);
			int num = Integer.parseInt(count);
			
			SubjectDao subDao = new SubjectDao();
			Subject subObj = subDao.get(subject, teacher.getSchool());
			
			TestDao tDao = new TestDao();
			List<Test> testList = tDao.filter(entYear, classNum, subObj, num, teacher.getSchool())
			
			req.setAttribute("students", testList);
			req.setAttribute("subject_name", subObj.getName());
		}
		
		//DBから取得
		ClassNumDao cDao = new ClassNumDao();
		SubjectDao sDao = new SubjectDao();
		//ユーザーデータから学校のクラスと科目データを取得
		List<String> classList = cDao.filter(teacher.getSchool());
		List<Subject> subjectList = sDao.filter(teacher.getSchool());
		
		//jspに送るためのセット
		req.setAttribute("class_list", classList);
		req.setAttribute("subject_list", subjectList);
		
		//フォワード
		req.getRequestDispatcher("/test_regist.jsp").forward(req, res);
>>>>>>> branch 'master' of https://github.com/oto-nariko/Score
	}

}
