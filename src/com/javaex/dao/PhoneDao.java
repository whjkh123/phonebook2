package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PersonVo;

public class PhoneDao {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void dbCnt() {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	public void close() {

		try {

			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	public int dbIsrt(PersonVo pVo) {

		dbCnt();

		int count = 0;

		try {

			String query = "INSERT INTO person VALUES(seq_person_id.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pVo.getName());
			pstmt.setString(2, pVo.getHp());
			pstmt.setString(3, pVo.getCompany());

			count = pstmt.executeUpdate();

			System.out.println("[DAO]: " + count + "건이 저장되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;

	}

	public int dbUpd(PersonVo pVo) {

		dbCnt();

		int count = 0;

		try {

			String query = "";
			query += " update person ";
			query += " SET name = ?, ";
			query += "     hp = ?, ";
			query += "     company = ? ";
			query += " WHERE person_id = ? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pVo.getName());
			pstmt.setString(2, pVo.getHp());
			pstmt.setString(3, pVo.getCompany());
			pstmt.setInt(4, pVo.getPerson_id());

			count = pstmt.executeUpdate();

			System.out.println("[DAO]: " + count + "건이 수정되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;

	}

	public int dbDle(int person_id) {

		dbCnt();

		int count = 0;

		try {

			String query = "DELETE FROM person WHERE person_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, person_id);

			count = pstmt.executeUpdate();

			System.out.println("[DAO]: " + count + "건이 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;

	}

	public List<PersonVo> dbList() {

		List<PersonVo> pList = new ArrayList<PersonVo>();

		dbCnt();

		try {

			String query = "";
			query += " select person_id, ";
			query += " 		  name, ";
			query += "        hp, ";
			query += "        company ";
			query += " from   person ";
			query += " order by person_id asc ";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				int person_id = rs.getInt("person_id");// int person_id = rs.getInt(1);
				String name = rs.getString("name");// String name = rs.getInt(2);
				String hp = rs.getString("hp");// String hp = rs.getInt(3);
				String company = rs.getString("company");// String company = rs.getInt(4);

				PersonVo pVo = new PersonVo(person_id, name, hp, company);

				pList.add(pVo);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return pList;

	}

	public List<PersonVo> dbSearch(String str) {

		List<PersonVo> pList = new ArrayList<PersonVo>();

		dbCnt();

		try {

			String query = "";
			query += " select	person_id, ";
			query += " 			name, ";
			query += " 			hp, ";
			query += " 			company ";
			query += " from		person ";
			query += " where	name like ? ";
			query += " 			or hp like ? ";
			query += " 			or company like ? ";
			query += " order by person_id asc ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + str + "%");
			pstmt.setString(2, "%" + str + "%");
			pstmt.setString(3, "%" + str + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {

				int person_id = rs.getInt(1);// int person_id = rs.getInt("person_id");
				String name = rs.getString(2);// String name = rs.getInt("name");
				String hp = rs.getString(3);// String hp = rs.getInt("hp");
				String company = rs.getString(4);// String company = rs.getInt("company");

				PersonVo pVo = new PersonVo(person_id, name, hp, company);

				pList.add(pVo);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return pList;

	}

	public PersonVo getPerson(int person_id) {

		PersonVo personVo = null;

		dbCnt();

		try {
			String query = "";
			query += " SELECT 	person_id, ";
			query += " 			name, ";
			query += " 			hp, ";
			query += " 			company ";
			query += " FROM 	person ";
			query += " WHERE 	person_id = ? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, person_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getNString("name");
				String hp = rs.getNString("hp");
				String company = rs.getNString("company");

				personVo = new PersonVo(person_id, name, hp, company);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return personVo;

	}

}
