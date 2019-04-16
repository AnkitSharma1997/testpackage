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
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.dao.ManagerDAO;
import com.cognizant.model.ManagerModel;
import com.cognizant.service.AdminServiceImpl;
import com.cognizant.service.ManagerServiceImpl;

public class TestManagerService {
	
	private MockMvc mockMvc;
	private ManagerModel managerModel;
	
	@Mock
	private ManagerDAO managerDAO;
	
	@InjectMocks
	private ManagerServiceImpl managerServiceImpl;

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(managerServiceImpl)
                .build();
        managerModel  = new ManagerModel();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testManagerRegister_positive() {
		managerModel.setManagerFirstName("dfd");
		managerModel.setManagerAge(25);
		managerModel.setManagerDob("06081997");
		managerModel.setManagerContactNo(259);
		managerModel.setManagerAltContactNo(189145);
		managerModel.setManagerEmailId("ankit@gmail.com");
		managerModel.setManagerGender("male");
		managerModel.setManagerLastName("XDvxdbvsdsdbv");
		managerModel.setManagerPassword("arushi");
		managerModel.setManagerStatus("Pending");
		
		try{
			boolean actual = managerServiceImpl.managerRegister(managerModel);
			boolean expected = false;
			assertEquals(expected, actual);
		}catch(Exception e){
		    fail("Not yet implemented");
		}
	}
	
	@Test
	public void testManagerRegister_negative() {
		try{
			boolean actual = managerServiceImpl.managerRegister(managerModel);
			
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
			fail("hey there now you looking at me");
			
		}
		
	}
	
	@Test
	public void testCheckCredentilas_positive() {
		managerModel.setManagerId("fdf");
		managerModel.setManagerPassword("adfdf");
		try{
			int actual = managerServiceImpl.checkCredentilas(managerModel);
			int expected  = 0;	
			assertEquals(expected, actual);
			}catch(Exception e){
				fail("wrong user");
			}
		
	}
	
	@Test
	public void testCheckCredentilas_negative() {
		
		try{
			int actual = managerServiceImpl.checkCredentilas(managerModel);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
			fail("you are lookin at me");
		}
	}
	
	@Test
	public void testCheckEmailAndContactNo_positive() {
		managerModel.setManagerFirstName("dfd");
		managerModel.setManagerAge(25);
		managerModel.setManagerDob("06081997");
		managerModel.setManagerContactNo(259);
		managerModel.setManagerAltContactNo(189145);
		managerModel.setManagerEmailId("ankit@gmail.com");
		managerModel.setManagerGender("male");
		managerModel.setManagerLastName("XDvxdbvsdsdbv");
		managerModel.setManagerPassword("arushi");
		try{
			
			int actual = managerServiceImpl.checkEmailAndContactNo(managerModel);
			int expected  = 0;
			assertEquals(expected, actual);
		}catch(Exception e){
			fail("making mistake in contact you are hopeless");
		}
		
	}
	
	@Test
	public void testCheckEmailAndContactNo_negative() {
		try{
			int actual = managerServiceImpl.checkEmailAndContactNo(managerModel);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
			fail("hey there");
		}
	}

}
