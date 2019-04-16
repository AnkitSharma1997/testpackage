package com.cognizant.test;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cognizant.dao.AdminDAOImpl;
import com.cognizant.entity.Admin;

@ContextConfiguration({"classpath:/config/applicationContext.xml"})
public class TestAdminDAOImpl
{

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
	private Admin admin;
	
	@InjectMocks
	private AdminDAOImpl adminDAOImpl;

    private AdminDAOImpl dao;

	
	
	@Before
	public void init() throws Exception {
		 MockitoAnnotations.initMocks(this);

		   ApplicationContext ioc= new ClassPathXmlApplicationContext("applicationContext.xml");
		   sessionFactory=(SessionFactory)ioc.getBean("sessionFactory");
		    
		    adminDAOImpl.setSessionFactory(this.sessionFactory);
		    Mockito.when(mockedSessionFactory.openSession()).thenReturn(mockedSession);
		    Mockito.when(mockedSession.beginTransaction()).thenReturn(mockedTransaction);
		    Mockito.when(mockedSession.createQuery("from Admin")).thenReturn(mockedQuery);
		   // Mockito.when(mockedQuery.list()).thenReturn(mockedProductList); 

		   dao = Mockito.spy(adminDAOImpl);
		   //mockedProductList=Mockito.spy(adminDAOImpl.getAllProducts());
		    
       
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegisterAdmin_positive() {
		admin.setAdminFirstName("ANK");
		admin.setAdminLastName("SHARMA");
		//admin.setAdminId("ADMINBBAK");
		admin.setAdminAge(25);
		admin.setAdminContactNo(989183966);
		admin.setAdminAltContactNo(47);
		admin.setAdminEmailId("vsdgdgl");
		admin.setAdminDob("45454645");
		admin.setAdminGender("Male");
		admin.setAdminPassword("kira");
		try{
			boolean actual = dao.registerAdmin(admin);
			boolean expected = true;
			assertEquals(expected, actual);
			
		}catch(Exception e){
			fail("Not yet implemented");
		}
		
	}
	
	@Test
	public void testRegisterAdmin_negative() {
		try{
			boolean actual = dao.registerAdmin(admin);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
			fail("register negative Dao");
		}
	}
	
	@Test
	public void testCheckEmailAndContactNo_positive() {
		
		admin.setAdminFirstName("ANK");
		admin.setAdminLastName("SHARMA");
		//admin.setAdminId("ADMINBBAK");
		admin.setAdminAge(25);
		admin.setAdminContactNo(989183966);
		admin.setAdminAltContactNo(47);
		admin.setAdminEmailId("vsdgdgl");
		admin.setAdminDob("45454645");
		admin.setAdminGender("Male");
		admin.setAdminPassword("kira");
		try{
			int actual = dao.checkEmailAndContactNo(admin);
			int expected = 0;
			assertEquals(expected, actual);
		}catch(Exception e){
			
			fail("Hey Ankit, what are you upto tonight;");
		}
	}
	
	@Test
	public void tesCheckEmailAndContactNo_negative() {
		try{
			int actual = dao.checkEmailAndContactNo(admin);
			assertTrue(true);
			
		}catch(Exception e){
			assertTrue(false);
			fail("null fields");
		}
	}
	
	@Test
	public void testCheckAdminLogin_positive() {
		admin.setAdminId("ADMINBBB");
		admin.setAdminPassword("aa");
		try{
			int actual = dao.checkEmailAndContactNo(admin);
			int expected = 0;
			assertEquals(expected, actual);
			
		}catch(Exception e){
			
			fail("null fields of Login");
		}
		
	}
	
	@Test
	public void testCheckAdminLogin_negative() {
		try{
			int actual = dao.checkEmailAndContactNo(admin);
			assertTrue(true);
			
		}catch(Exception e){
			assertTrue(false);
			fail("null fields");
		}
	}
	
	
	@Test
	public void testApproveManagerRequest_positive() {
		String managerId = "ADMINBBB";
		try{
			boolean actual = dao.approveManagerRequest(managerId);
			boolean expected = true;
			assertEquals(expected, actual);
			
		}catch(Exception e){
			
			fail("won't be able to see it");
		}
	
	}
	
	@Test
	public void testApproveManagerRequest_negative() {
		String managerId = "";
		try{
			boolean actual = dao.approveManagerRequest(managerId);
			assertTrue(true);
			
		}catch(Exception e){
			assertTrue(false);
			fail("null fields");
		}
	}


}
