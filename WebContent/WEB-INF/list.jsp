<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.javaex.vo.PersonVo"%>
<%@ page import="java.util.List"%>

<!-- 포워드 -->
<%
	List<PersonVo> pList = (List<PersonVo>) request.getAttribute("PersonList");// object에 담겨있는 데이터를 꺼내기 위해 형 변환
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>전화번호 리스트</h1>

	<p>입력한 전화번호 내역입니다.</p>


	<%
		for (int i = 0; i < pList.size(); i++) {
	%>
	<table border="1">

		<tr>
			<td>이름(name)</td>
			<td><%=pList.get(i).getName()%></td>
		</tr>

		<tr>
			<td>핸드폰(hp)</td>
			<td><%=pList.get(i).getHp()%></td>
		</tr>

		<tr>
			<td>회사(company)</td>
			<td><%=pList.get(i).getCompany()%></td>
		</tr>

		<tr>
			<td><a href="/phonebook2/pbc?action=uform&id=<%=pList.get(i).getPerson_id()%>">[수정]</a></td>
			<td><a href="/phonebook2/pbc?action=delete&id=<%=pList.get(i).getPerson_id()%>">[삭제]</a></td>
		</tr>

	</table>

	<br>

	<%
		}
	%>

	<a href="/phonebook2/pbc?action=wform">전화번호 추가등록</a>

</body>
</html>