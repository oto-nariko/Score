package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {  
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school=teacher.getSchool();

		ClassNumDao cNumDao=new ClassNumDao();
		SubjectDao sDao=new SubjectDao();

		List<Integer> entYearSet=new ArrayList<>();

		int year=LocalDate.now().getYear();

		for (int i=year-10;i <=year; i++){
		            entYearSet.add(i);
		            }
		
		 }
	}
