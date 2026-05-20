package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {  
		Teacher teacher=(Teacher) req.getSession().getAttribute("user");
		School school=teacher.getSchool();

		ClassNumDao cNumDao=new ClassNumDao();
		SubjectDao sDao=new SubjectDao();

		List<Integer> entYearSet=new ArrayList<>();

		int year=LocalDate.now().getYear();

		for (int i=year-10;i <=year; i++){
		            entYearSet.add(i);
		 }

		 List<String> classNumSet=cNumDao.filter(school);
		 List<Subject> subjects=sDao.filter(school);

		 req.setAttribute("ent_year_set", entYearSet);
		 req.setAttribute("class_num_set", classNumSet);
		 req.setAttribute("subjects", subjects);

		 req.getRequestDispatcher("test_list.jsp").forward(req, res);
		 }
	}
