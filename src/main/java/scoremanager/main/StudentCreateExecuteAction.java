package scoremanager.main;

import java.time.LocalDate;
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
			res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
			return;
		}
		
		//jspから値を受け取る
		String entYearStr = req.getParameter("ent_year");
		String no = req.getParameter("no");
		String name = req.getParameter("name");
		String classNum = req.getParameter("class_num");
		int currentYear = LocalDate.now().getYear();
		
		//入学年度が選択されてないとき
		if (entYearStr == null || entYearStr.equals("")) {
			
			req.setAttribute("error_message", "入学年度を選択してください");
			
			req.setAttribute("no", no);
			req.setAttribute("name", name);
			req.setAttribute("class_num", classNum);
			req.setAttribute("startYear", currentYear - 10);
			req.setAttribute("endYear", currentYear + 10);
			
			ClassNumDao cDao =new ClassNumDao();
			List<String> classList = cDao.filter(teacher.getSchool());
			req.setAttribute("class_list", classList);
			
			req.getRequestDispatcher("/student_create.jsp").forward(req, res);
			return;
		}
		
		int entYear = Integer.parseInt(entYearStr);
		
		//保存する前に、同じ学生番号のデータが既にDBにないかチェックする
		StudentDao sDao = new StudentDao();
		Student existStudent = sDao.get(no); // ※StudentDao内の1件取得メソッドを呼び出す

		//すでに同じ学生番号が存在していたら、完了画面にいかせずエラー画面に戻す
		if (existStudent != null) {
			req.setAttribute("error_message", "学生番号が重複しています");
			
			req.setAttribute("ent_year", entYear);
			req.setAttribute("no", no);
			req.setAttribute("name", name);				req.setAttribute("class_num", classNum);
			
			req.setAttribute("startYear", currentYear - 10);
			req.setAttribute("endYear", currentYear + 10);
			
			ClassNumDao cDao = new ClassNumDao();
			List<String> classList = cDao.filter(teacher.getSchool());
			req.setAttribute("class_list", classList);
			
			req.getRequestDispatcher("/student_create.jsp").forward(req, res);
			return;
				}
		
		//
		Student student = new Student();
		student.setEntYear(entYear);
		student.setNo(no);
		student.setName(name);
		student.setClassNum(classNum);
		student.setAttend(true);
		student.setSchool(teacher.getSchool());
		

		try {
			sDao.save(student);
			
			req.getRequestDispatcher("/student_create_done.jsp").forward(req, res);
		} catch (Exception e){
			req.setAttribute("error_message", "学生番号が重複しています");
			
			req.setAttribute("ent_year", entYear);
			req.setAttribute("no", no);
			req.setAttribute("name", name);
			req.setAttribute("class_num", classNum);
			req.setAttribute("startYear", currentYear - 10);
			req.setAttribute("endYear", currentYear + 10);
			
			ClassNumDao cDao = new ClassNumDao();
		    List<String> classList = cDao.filter(teacher.getSchool());
		    req.setAttribute("class_list", classList);
		    
		    req.getRequestDispatcher("/student_create.jsp").forward(req, res);
		}


	}

}
