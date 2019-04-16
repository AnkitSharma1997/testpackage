package com.cognizant.test;

import static org.junit.Assert.*;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.controller.AdminController;
import com.cognizant.dao.AdminDAO;
import com.cognizant.exception.AdminRegisterParamException;
import com.cognizant.model.AdminModel;
import com.cognizant.service.AdminService;
import com.cognizant.validation.AdminLoginValidator;
import com.cognizant.validation.AdminRegistrationValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/config/applicationContext.xml"})

public class TestAdminController {

	private MockMvc mockMvc;
	private AdminModel adminModel;
	
	
	@Mock
	private AdminDAO adminDAO;
	
	@Mock
	private AdminService adminService;
	
	@Autowired
	@Spy
	private AdminLoginValidator loginValidator;
	
	@Autowired
	@Spy
	private AdminRegistrationValidator registerValidator;
	
	@InjectMocks
    private AdminController adminController;
	
	
	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(adminController)
                .build();
        adminModel = new AdminModel();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegisterAdmin_positive() {
		try{
		
		adminModel.setAdminFirstName("ARU");
		adminModel.setAdminLastName("RASTOGI");
		adminModel.setAdminId("ADMINBBA");
		adminModel.setAdminAge(7);
		adminModel.setAdminContactNo(989183965);
		adminModel.setAdminAltContactNo(46);
		adminModel.setAdminEmailId("vsdgdg");
		adminModel.setAdminDob("4545464");
		adminModel.setAdminGender("Male");
		adminModel.setAdminPassword("arushi");
		
		Errors errors = new BeanPropertyBindingResult(adminModel,"adminModel");
		
		ModelAndView mv =adminController.registerAdmin(adminModel,errors);
		String actual = mv.getViewName();
		String expected = "adminRegister";
		assertEquals(expected, actual);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("never Fails");
		}
	}
	
	@Test
	public void testRegisterAdmin_negative() {
			try {
				Errors errors = new BeanPropertyBindingResult(adminModel,"adminModel");

			ModelAndView mv = adminController.registerAdmin(adminModel, errors);
			assertTrue(true);
			} catch(Exception e){
				assertTrue(false);
				assertEquals("parameters are null", new AdminRegisterParamException("sorry parameters are null").getMessage());
			}
	}
	
	@Test
	public void testAdminLogin_positive(){
		
		try{
			HttpSession session = null;
		
		Errors errors = new BeanPropertyBindingResult(adminModel,"adminModel");
		adminModel.setAdminId("ADMINBBB");
		adminModel.setAdminPassword("aa");
		ModelAndView mv= adminController.adminLogin(adminModel, errors, session);
		String actual  = mv.getViewName();
		String expected ="adminLogin";
		assertEquals(expected, actual);
		}catch(Exception e){
			fail("invalid credentials");
		}
		
		
	}
	
	@Test
	public void testAdminLogin_negative(){
		try{
			HttpSession session = null;
		
		Errors errors = new BeanPropertyBindingResult(adminModel,"adminModel");
		
		ModelAndView mv= adminController.adminLogin(adminModel, errors, session);
		assertTrue(true);
		
		}catch(Exception e){
			assertTrue(false);
			fail("admin Login Negative");
		}
	}
		

	@Test
	public void testChangeRequestStatus_positive(){
		try{
			ModelAndView mv = adminController.changeRequestStatus("MANAGERBBE" , "approve");
			String actual = mv.getViewName();
			String expected = "managerRequest";
			assertEquals(expected, actual);
		}catch(Exception e){
			e.printStackTrace();
			fail("he y");
		}
	}
	
	
	@Test
	public void testChangeRequestStatus_negative(){
		try{
			ModelAndView mv = adminController.changeRequestStatus("MANAGERBBE" , "approve");
			
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
			e.printStackTrace();
			assertEquals("null value exception", e.getMessage());
		}
	}

}
