package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhoneController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PhoneController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("controller");

		// 수행 할 기능 정의
		String act = request.getParameter("action");
		System.out.println(act);

		if ("list".equals(act)) {
			System.out.println("리스트 출력");
			// 리스트 출력
			PhoneDao pDao = new PhoneDao();
			List<PersonVo> pList = pDao.dbList();

			// 리스트 데이터를 jsp에 전달
			request.setAttribute("PersonList", pList);

			// html >> jsp코딩이 유리 >> jsp forword
			WebUtil.forword(request, response, "./WEB-INF/list.jsp");
		} else if ("wform".equals(act)) {
			System.out.println("등록 폼 출력");
			// 등록 폼 forward
			WebUtil.forword(request, response, "./WEB-INF/writeForm.jsp");
		} else if ("insert".equals(act)) {
			System.out.println("등록화면 출력");
			// 데이터 등록
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");

			PersonVo pVo = new PersonVo(name, hp, company);

			PhoneDao pDao = new PhoneDao();
			pDao.dbIsrt(pVo);

			WebUtil.redirect(request, response, "/phonebook2/pbc?action=list");
		} else if ("uform".equals(act)) {
			System.out.println("수정 폼 출력");
			// 수정 폼에 필요한 PhoneDao(기능), PersonVo(데이터) 준비
			int pId = Integer.parseInt(request.getParameter("id"));

			PhoneDao pDao = new PhoneDao();
			PersonVo pVo = pDao.getPerson(pId);

			// pVo 데이터를 jsp에 전달
			request.setAttribute("PersonVo", pVo);

			// 수정 폼 forword
			WebUtil.forword(request, response, "./WEB-INF/updateForm.jsp");
		} else if ("update".equals(act)) {
			System.out.println("수정화면 출력");
			// 데이터 수정
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			int personId = Integer.parseInt(request.getParameter("id"));

			PersonVo pVo = new PersonVo(personId, name, hp, company);

			PhoneDao pDao = new PhoneDao();
			pDao.dbUpd(pVo);

			WebUtil.redirect(request, response, "/phonebook2/pbc?action=list");
		} else if ("delete".equals(act)) {
			System.out.println("삭제");

			// 데이터 삭제
			int pId = Integer.parseInt(request.getParameter("id"));

			PhoneDao pDao = new PhoneDao();
			pDao.dbDle(pId);

			WebUtil.redirect(request, response, "/phonebook2/pbc?action=list");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
