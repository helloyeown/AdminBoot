package org.zerock;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class BoardApplicationTests {

	@Autowired
	DataSource dataSource;

	@Test
	void testConnection(){
		
		try {
			Connection con = dataSource.getConnection();
			log.info(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	void contextLoads() {
	}

}
