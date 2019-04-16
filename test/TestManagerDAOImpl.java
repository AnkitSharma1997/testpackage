package com.cognizant.test;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.dao.ManagerDAOImpl;
import com.cognizant.entity.Manager;
import com.cognizant.model.ManagerModel;

public class TestManagerDAOImpl {

	 private SessionFactory sessionFactory;
		
		@Mock
		private ApplicationContext ioc;
		
		@Mock
		private SessionFactory mockedSessionFactory;
		
		@Mock
		private Session mockedSession;
		
		@Mock
		private Transaction mockedTransaction;
		@Mock
		private Query mockedQuery;
		
		@Mock
		private Manager manager;
		
		@InjectMocks
		private ManagerDAOImpl managerDAOImpl;

	    private ManagerDAOImpl dao;
		
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		   ApplicationContext ioc= new ClassPathXmlApplicationContext("applicationContext.xml");
		   sessionFactory=(SessionFactory)ioc.getBean("sessionFactory");
		    
		    managerDAOImpl.setSessionFactory(this.sessionFactory);
		    Mockito.when(mockedSessionFactory.openSession()).thenReturn(mockedSession);
		    Mockito.when(mockedSession.beginTransaction()).thenReturn(mockedTransaction);
		    Mockito.when(mockedSession.createQuery("from Manager")).thenReturn(mockedQuery);
		   // Mockito.when(mockedQuery.list()).thenReturn(mockedProductList); 

		   dao = Mockito.spy(managerDAOImpl);
		   //mockedProductList=Mockito.spy(adminDAOImpl.getAllProducts());
		   
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testManagerRegister_positive() {
		 
			manager.setManagerAge(23);
			manager.setManagerAltContactNo(5454754);
			manager.setManagerContactNo(446456);
			manager.setManagerDob("5456565");
			manager.setManagerEmailId("fgfrefr");
			manager.setManagerFirstName("rakesh");
			manager.setManagerGender("dfdfd");
			//managerModel.setManagerId("142");
			manager.setManagerLastName("fdfdfd");
			manager.setManagerPassword("kira");
			manager.setManagerStatus("frd");
			try{
				boolean actual = dao.managerRegister(manager);
				boolean expected = true;
				assertEquals(expected, actual);
			}catch(Exception e){
				fail("you are awesome");
			}
		
	}
	
	@Test
	public void testManagerRegister_negative() {
		try{
			boolean actual = dao.managerRegister(manager);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
			fail("you are awesome");
		}
	}
	
	@Test
	public void testCheckEmailAndContactNo_positive() {
		manager.setManagerAge(23);
		manager.setManagerAltContactNo(5454754);
		manager.setManagerContactNo(446456);
		manager.setManagerDob("5456565");
		manager.setManagerEmailId("fgfrefr");
		manager.setManagerFirstName("rakesh");
		manager.setManagerGender("dfdfd");
		//managerModel.setManagerId("142");
		manager.setManagerLastName("fdfdfd");
		manager.setManagerPassword("kira");
		manager.setManagerStatus("frd");
		try{
			int actual = dao.checkEmailAndContactNo(manager);
			int expected = 0;
			assertEquals(expected, actual);
		}catch(Exception e){
			
			fail("Hey Ankit, what are you upto tonight;");
		}
	}
	
	@Test
	public void tesCheckEmailAndContactNo_negative() {
		try{
			int actual = dao.checkEmailAndContactNo(manager);
			assertTrue(true);
			
		}catch(Exception e){
			assertTrue(false);
			fail("null fields");
		}
		
	}
	
	@Test
	public void testManagerCredentials_positive() {
		manager.setManagerId("MANAGERBBE");
		manager.setManagerPassword("aru");
		try{
			int actual = dao.checkEmailAndContactNo(manager);
			int expected = 0;
			assertEquals(expected, actual);
			
		}catch(Exception e){
			
			fail("null fields of Login");
		}
	}
	
	@Test
	public void testManagerCredentials_negative() {
		try{
			int actual = dao.checkEmailAndContactNo(manager);
			assertTrue(true);
			
		}catch(Exception e){
			assertTrue(false);
			fail("null fields");
		}
	}
}
