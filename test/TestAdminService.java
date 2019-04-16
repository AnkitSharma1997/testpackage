package com.cognizant.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cognizant.dao.AdminDAO;
import com.cognizant.model.AdminModel;
import com.cognizant.model.ManagerModel;
import com.cognizant.service.AdminServiceImpl;

public class TestAdminService {

	private MockMvc mockMvc;
	private AdminModel adminModel;
	
	@Mock
	private AdminDAO adminDAO;
	@InjectMocks
	private AdminServiceImpl adminServiceImpl;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(adminServiceImpl)
                .build();
        adminModel = new AdminModel();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegisterAdmin_positive() {
		adminModel.setAdminFirstName("ARU");
		adminModel.setAdminLastName("RASTOGI");
		//adminModel.setAdminId("ADMINBBA");
		adminModel.setAdminAge(7);
		adminModel.setAdminContactNo(989183965);
		adminModel.setAdminAltContactNo(46);
		adminModel.setAdminEmailId("vsdgdg");
		adminModel.setAdminDob("4545464");
		adminModel.setAdminGender("Male");
		adminModel.setAdminPassword("arushi");
		try{
			boolean actual = adminServiceImpl.registerAdmin(adminModel);
			boolean expected = false;
			assertEquals(expected, actual);
		}catch(Exception e){
		fail("Not yet implemented");
		}
	}
	
	@Test
	public void testRegisterAdmin_negative() {
		boolean actual = false;
		try{
			actual = adminServiceImpl.registerAdmin(adminModel);
			assertFalse(actual);
		}catch(Exception e){
			assertFalse(actual);
			fail("fdf");
			
		}
	}
	
	@Test
	public void testCheckAdminLogin_positive() {
		adminModel.setAdminId("dd");
		adminModel.setAdminPassword("dfdf");
		try{
			int actual = adminServiceImpl.checkAdminLogin(adminModel);
			int expected = 0;
			assertEquals(expected, actual);
		}catch(Exception e){
			fail("doesn't exist");
		}
	}

}
