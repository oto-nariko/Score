package scoremanager.main;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//セッションからユーザーデータを取得
		HttpSession session = req.getSession();
		//セッションが切れているときはログイン画面に
		Teacher teacher=(Teacher) session.getAttribute("user");
		if(teacher==null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/main/Login.action");
			return;
		}
		
		
		/*
		 * 入力された値を受け取り、処理する
		 */
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
				
				//選択肢を表示させるためにもう一度セット
				ClassNumDao cDao = new ClassNumDao(); 
			    SubjectDao sDao = new SubjectDao();
			    
			    List<String> classList = cDao.filter(teacher.getSchool()); 
			    List<Subject> subjectList = sDao.filter(teacher.getSchool());
			    
			    req.setAttribute("class_list", classList);
			    req.setAttribute("subject_list", subjectList);
			    
			    // 入力途中の値も残してあげる
			    req.setAttribute("f1", entYearStr);
			    req.setAttribute("f2", classNum);
			    req.setAttribute("f3", subject);
			    req.setAttribute("f4", count);
				
				req.getRequestDispatcher("/test_regist.jsp").forward(req, res);
				return;
			}
			
			int entYear = Integer.parseInt(entYearStr);
			int num = Integer.parseInt(count);
			
			//リクエストパラメータに指定されたデータから、科目名を取得する
			SubjectDao subDao = new SubjectDao();
			Subject subObj = subDao.get(subject, teacher.getSchool());
			//入力された入学年度、クラス、科目、回数の成績データを取得する
			TestDao tDao = new TestDao();
			List<Test> testList = tDao.filter(entYear, classNum, subObj, num, teacher.getSchool());
			
			//jspに送るためにセット
			req.setAttribute("students", testList);
			req.setAttribute("subject_name", subObj.getName());
		}
		
		
		/*
		 * トグルの選択肢を格納する
		 */
		//DBから取得
		ClassNumDao cDao = new ClassNumDao();
		SubjectDao subDao = new SubjectDao();
		//ユーザーデータから学校のクラスと科目データを取得
		List<String> classList = cDao.filter(teacher.getSchool());
		List<Subject> subjectList = subDao.filter(teacher.getSchool());
		
		//jspに送るためのセット
		req.setAttribute("class_list", classList);
		req.setAttribute("subject_list", subjectList);
		
		//フォワード
		req.getRequestDispatcher("/test_regist.jsp").forward(req, res);
	}

}
