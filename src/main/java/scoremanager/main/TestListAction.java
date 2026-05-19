package scoremanager.main;

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
		School school=teacher.getschool();
		
		String entYear=req.getParameter("f1");
		String classNum=req.getParameter("f2");
		String subjectCd=req.getParameter("f3");
		String count=req.getParameter("f4");
		
		int entYear = 0;
		if (entYearStr != null && !entYearStr.isEmpty()) {
			entYear = Integer.parseInt(entYearStr);
		}
		
		ClassNumDao classNumDao=new ClassNumDao();
		SubjectDao subjectDao=new SubjectDao();
		
		
        

		
	}

}
