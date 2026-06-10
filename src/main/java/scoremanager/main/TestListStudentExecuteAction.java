package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//セッション確認
		HttpSession session=req.getSession();
		if (session == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
			return;
		}
		
		//ログイン確認
		Teacher teacher=(Teacher) session.getAttribute("user");
		if (teacher == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
			return;
			}

		//学籍番号取得
		String studentNo=req.getParameter("f4");

		//DAO生成
		ClassNumDao cDao=new ClassNumDao();
		SubjectDao subDao=new SubjectDao();
		TestListStudentDao tlsDao=new TestListStudentDao();
		StudentDao stuDao=new StudentDao();

		//一覧取得
		List<String> classList=cDao.filter(teacher.getSchool());
		List<Subject> subjectList=subDao.filter(teacher.getSchool());
		List<TestListStudent> tests=new ArrayList<>();
		
		//未入力チェック
		if (studentNo == null || studentNo.isEmpty()) {
		    req.setAttribute("studentError", "このフィールドを入力してください。");

		    req.setAttribute("f4", studentNo);
		    req.setAttribute("class_num_set", classList);
		    req.setAttribute("subjects", subjectList);

		    req.getRequestDispatcher("/test_list.jsp").forward(req, res);
		    return;
		}
		
		//学生取得
		Student student = stuDao.get(studentNo);
		//学生なしの場合
		if (student == null) {
		    req.setAttribute("studentError", "学生情報が存在しませんでした");

		    req.setAttribute("f4", studentNo);
		    req.setAttribute("class_num_set", classList);
		    req.setAttribute("subjects", subjectList);

		    req.getRequestDispatcher("/test_list.jsp").forward(req, res);
		    return;
		}

		//成績検索
		tests = tlsDao.filter(student);
		
		//jspへ渡す
		req.setAttribute("student", student);
		req.setAttribute("f4", studentNo);
		req.setAttribute("class_num_set", classList);
		req.setAttribute("subjects", subjectList);
		req.setAttribute("list", tests);
		req.setAttribute("resultType", "student");
		//成績なしの場合
		if (tests == null || tests.size() == 0) {
		    req.setAttribute("studentResultError", "成績情報が存在しませんでした");
		    req.getRequestDispatcher("/test_list.jsp").forward(req, res);
		    return;
		}
		//画面遷移
		req.getRequestDispatcher("/test_list.jsp").forward(req, res);
		return;
	}
}