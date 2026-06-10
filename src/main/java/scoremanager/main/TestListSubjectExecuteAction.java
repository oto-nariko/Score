package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session=req.getSession();
		Teacher teacher=(Teacher) session.getAttribute("user");
		
		//ログインしていない場合はログイン画面に飛ばす
		if (teacher == null) {
			res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
			return;
		}

		String entYearStr=req.getParameter("f1");
		String classNum=req.getParameter("f2");
		String subjectCd=req.getParameter("f3");
		String numStr =req.getParameter("f4");


		int entYear=0;
		if (entYearStr!=null&&!entYearStr.isEmpty()){
			entYear=Integer.parseInt(entYearStr);
			}

		ClassNumDao cDao=new ClassNumDao();
		SubjectDao subDao=new SubjectDao();
		TestListSubjectDao tlsDao=new TestListSubjectDao();

		//クラス取得
		List<String> classList=cDao.filter(teacher.getSchool());
		//科目取得
		List<Subject> subjectList=subDao.filter(teacher.getSchool());
		//検索結果
		List<TestListSubject> tests=new ArrayList<>();
		//入学年度
		List<Integer> entYearSet=new ArrayList<>();
		int year=2026;
		//入学年度を追加
		for (int i=year-10;i<=year; i++) {
			entYearSet.add(i);
		}
		//未入力の場合
		if (entYear==0
		        || classNum==null || classNum.equals("")
		        || subjectCd==null || subjectCd.equals("")) {

		    req.setAttribute("error","入学年度とクラスと科目を選択してください");

		    req.setAttribute("f1",entYearStr);
		    req.setAttribute("f2",classNum);
		    req.setAttribute("f3",subjectCd);
		    req.setAttribute("f4",numStr);

		    req.setAttribute("ent_year_set",entYearSet);
		    req.setAttribute("class_num_set",classList);
		    req.setAttribute("subjects",subjectList);

		    req.getRequestDispatcher("/test_list.jsp").forward(req, res);
		    return;
		}
		
		//科目取得
		Subject subject=subDao.get(subjectCd,teacher.getSchool());
		//成績検索
		tests=tlsDao.filter(entYear,classNum,subject,teacher.getSchool());
		
		//検索結果なし
		if (tests==null || tests.size()==0){

		    req.setAttribute("error","学生情報が存在しませんでした");

		    req.setAttribute("f1",entYearStr);
		    req.setAttribute("f2",classNum);
		    req.setAttribute("f3",subjectCd);
		    req.setAttribute("f4",numStr);

		    req.setAttribute("ent_year_set",entYearSet);
		    req.setAttribute("class_num_set",classList);
		    req.setAttribute("subjects",subjectList);

		    req.getRequestDispatcher("/test_list.jsp").forward(req, res);
		    return;
		}
		
		//jspに渡す
		req.setAttribute("subject",subject);

		req.setAttribute("f1",entYearStr);
		req.setAttribute("f2",classNum);
		req.setAttribute("f3",subjectCd);
		req.setAttribute("f4",numStr);

		req.setAttribute("ent_year_set",entYearSet);
		req.setAttribute("class_num_set",classList);
		req.setAttribute("subjects",subjectList);
		req.setAttribute("list", tests);
		req.setAttribute("resultType", "subject");
		req.getRequestDispatcher("/test_list.jsp").forward(req, res);
		}
	}