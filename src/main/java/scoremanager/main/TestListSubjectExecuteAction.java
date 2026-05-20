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
import dao.TestDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session=req.getSession();
		Teacher teacher=(Teacher) session.getAttribute("user");

		String entYearStr=req.getParameter("f1");
		String classNum=req.getParameter("f2");
		String subjectCd=req.getParameter("f3");
		String numStr = req.getParameter("f4");


		int entYear=0;
		if (entYearStr!=null&&!entYearStr.isEmpty()){
			entYear=Integer.parseInt(entYearStr);
			}
		
		int num=0;
		if (numStr!=null&&!numStr.isEmpty()){
		    num=Integer.parseInt(numStr);
		}

		ClassNumDao cDao=new ClassNumDao();
		SubjectDao subDao=new SubjectDao();
		TestDao tDao=new TestDao();

		List<String> classList=cDao.filter(teacher.getSchool());
		List<Subject> subjectList=subDao.filter(teacher.getSchool());
		List<Test> tests=new ArrayList<>();
		List<Integer> entYearSet=new ArrayList<>();
		int year=2026;
		for (int i=year-10;i<=year; i++) {
			entYearSet.add(i);
		}

		if (entYear==0
				|| classNum==null || classNum.equals("")
				|| subjectCd==null || subjectCd.equals("")){
			req.setAttribute("error","入学年度とクラスと科目を選択してください");
			}else{

			Subject subject=subDao.get(subjectCd,teacher.getSchool());

			tests=tDao.filter(entYear,classNum,subject,num,teacher.getSchool());

			if (tests==null || tests.size()==0){
				req.setAttribute("error","学生情報が存在しませんでした");
				}
			req.setAttribute("subject",subject);
			}

		req.setAttribute("f1",entYearStr);
		req.setAttribute("f2",classNum);
		req.setAttribute("f3",subjectCd);
		req.setAttribute("f4",numStr);

		req.setAttribute("ent_year_set",entYearSet);
		req.setAttribute("class_num_set",classList);
		req.setAttribute("subjects",subjectList);
		req.setAttribute("tests",tests);
		req.getRequestDispatcher("/test_list.jsp").forward(req,res);
		}
	}