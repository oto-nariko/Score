package scoremanager.main;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//セッションから先生情報を取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		
		//ログインしていない場合はログイン画面に飛ばす
		if (teacher == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/main/Login.action");
			return;
		}
		
		//jspから値を受け取る
		String entYearStr = req.getParameter("ent_year");
		String no = req.getParameter("no");
		String name = req.getParameter("name");
		String classNum = req.getParameter("class_num");
		
		//入学年度が選択されてないとき
		if (entYearStr == null || entYearStr.equals("")) {
			
			req.setAttribute("error_message", "入学年度を選択してください");
			
			req.setAttribute("no", no);
			req.setAttribute("name", name);
			req.setAttribute("class_num", classNum);
			
			ClassNumDao cDao =new ClassNumDao();
			List<String> classList = cDao.filter(teacher.getSchool());
			req.setAttribute("class_list", classList);
			
			req.getRequestDispatcher("/student_create.jsp").forward(req, res);
			return;
		}
		
		int entYear = Integer.parseInt(entYearStr);
		
		//
		Student student = new Student();
		student.setEntYear(entYear);
		student.setNo(no);
		student.setName(name);
		student.setClassNum(classNum);
		student.setAttend(true);
		student.setSchool(teacher.getSchool());
		
		//DBに保存
		StudentDao sDao = new StudentDao();
		try {
			sDao.save(student);
			
			req.getRequestDispatcher("StudentList.action").forward(req, res);
		} catch (Exception e){
			req.setAttribute("error_message", "学生番号が重複しています");
			
			req.setAttribute("ent_year", entYear);
			req.setAttribute("no", no);
			req.setAttribute("name", name);
			req.setAttribute("class_num", classNum);
			
			ClassNumDao cDao = new ClassNumDao();
		    List<String> classList = cDao.filter(teacher.getSchool());
		    req.setAttribute("class_list", classList);
		    
		    req.getRequestDispatcher("/student_create_done.jsp").forward(req, res);
		}


	}

}
