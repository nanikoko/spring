package com.jsp.dataSource;

import java.io.Reader;
import java.sql.Connection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;

public class OracleMybatisSqlSessionFactory implements SqlSessionFactory {

	private SqlSessionFactory sqlSessionFactory;
	
	{
		String config = "com/jsp/mybatis/config/sqlConfig.xml";
		
		try {
			Reader reader = Resources.getResourceAsReader(config);
			
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
			
			System.out.println("SqlSessionFactory 성공했습니다.");
			
		}catch(Exception e) {
			System.out.println("SqlSessionFactory 실패했습니다.");
			e.printStackTrace();
		}
		
	}
	
	//만들고 싶은 대로 Factory를 생성
	@Override
	public Configuration getConfiguration() {
		return sqlSessionFactory.getConfiguration();
	}

	@Override
	public SqlSession openSession() {
		return sqlSessionFactory.openSession(true); //자동으로 커밋하기 위해 true
	}

	@Override
	public SqlSession openSession(boolean arg0) {
		return sqlSessionFactory.openSession(arg0);
	}

	@Override
	public SqlSession openSession(Connection arg0) {
		return sqlSessionFactory.openSession(arg0);
	}

	@Override
	public SqlSession openSession(TransactionIsolationLevel arg0) {
		return sqlSessionFactory.openSession(arg0);
	}

	@Override
	public SqlSession openSession(ExecutorType arg0) {
		return sqlSessionFactory.openSession(arg0);
	}

	@Override
	public SqlSession openSession(ExecutorType arg0, boolean arg1) {
		return sqlSessionFactory.openSession(arg0, arg1);
	}

	@Override
	public SqlSession openSession(ExecutorType arg0, TransactionIsolationLevel arg1) {
		return sqlSessionFactory.openSession(arg0, arg1);
	}

	@Override
	public SqlSession openSession(ExecutorType arg0, Connection arg1) {
		return sqlSessionFactory.openSession(arg0, arg1);
	}

}
