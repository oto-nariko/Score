package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {  
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		//ログインしていない場合はログイン画面に飛ばす
		if (teacher == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/main/Login.action");
			return;
		}

		ClassNumDao cDao=new ClassNumDao();
		SubjectDao subDao=new SubjectDao();

		List<String> classList = cDao.filter(teacher.getSchool());

		List<Subject> subjectList = subDao.filter(teacher.getSchool());

		List<Integer> entYearSet = new ArrayList<>();

		int year = 2026;

		for (int i = year - 10; i <= year; i++) {
		            entYearSet.add(i);
		            }
		
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("class_num_set", classList);
		req.setAttribute("subjects", subjectList);

		req.getRequestDispatcher("/test_list.jsp").forward(req, res);
		}
	}