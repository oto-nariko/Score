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
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();
		if (session == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
			return;
		}

		Teacher teacher = (Teacher) session.getAttribute("user");
		if (teacher == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
			return;
			}

		String studentNo = req.getParameter("f4");

		ClassNumDao cDao = new ClassNumDao();
		SubjectDao subDao = new SubjectDao();
		TestListStudentDao tlsDao = new TestListStudentDao();

		List<String> classList = cDao.filter(teacher.getSchool());
		List<Subject> subjectList = subDao.filter(teacher.getSchool());
		List<TestListStudent> tests = new ArrayList<>();

		if (studentNo == null || studentNo.isEmpty()) {
			req.setAttribute("error", "このフィールドを入力してください。");
			} else {
				Student student = new Student();
				student.setNo(studentNo);
				student.setSchool(teacher.getSchool());

				tests = tlsDao.filter(student);
				if (tests == null || tests.size() == 0) {
					req.setAttribute("error", "学生情報が存在しませんでした");
					}
				}

		req.setAttribute("f4", studentNo);
		req.setAttribute("class_num_set", classList);
		req.setAttribute("subjects", subjectList);
		req.setAttribute("list", tests);

		req.getRequestDispatcher("/test_list_student.jsp")
		.forward(req, res);
	}

}
