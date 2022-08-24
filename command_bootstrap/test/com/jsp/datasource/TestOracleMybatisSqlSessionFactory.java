package com.jsp.datasource;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jsp.dto.MemberVO;

//Factory단위테스트
public class TestOracleMybatisSqlSessionFactory {
	private OracleMybatisSqlSessionFactory sqlSessionFactory = new OracleMybatisSqlSessionFactory();

	private SqlSession session;
	
	@Before
	public void init() throws Exception{
		session = sqlSessionFactory.openSession();
	}
	
	@Test
	public void testSqlSessionFactory() throws Exception{
		Assert.assertNotNull(sqlSessionFactory); //null테스트
	}
	
	@Test
	public void testSqlSession() throws SQLException{
		Collection<String> mapNames = (Collection<String>)session.getConfiguration().getMappedStatementNames();
		Assert.assertTrue(mapNames.contains("Member-Mapper.selectMemberList"));
	}
	
	@Test
	public void testMemberList() throws SQLException{
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectMemberList");
		Assert.assertEquals(7, memberList.size()); //리스트개수 비교
	}
	
	@After
	public void close() throws Exception{
		if(session != null) session.close();
	}
}
