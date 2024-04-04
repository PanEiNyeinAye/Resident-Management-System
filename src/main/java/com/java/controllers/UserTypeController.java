package com.java.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.beans.*;
import com.java.dao.AdminDAO;
import com.java.dao.GASDAO;
import com.java.dao.PoliceOfficerDAO;
import com.java.dao.ResidentDAO;
import com.java.dao.SAdminDAO;
import com.java.dao.WardASDAO;
import com.java.model.SearchModel;
import com.java.model.UserTypeModel;

@Controller 
public class UserTypeController {
	@Autowired 
	WardASDAO wdao;//will inject dao from XML file 
	@Autowired 
	ResidentDAO rdao;
	@Autowired 
	GASDAO gdao;
	@Autowired 
	PoliceOfficerDAO pdao;
	@Autowired 
	SAdminDAO sdao;
	@Autowired 
	AdminDAO adao;
	
	@RequestMapping("/staffLogin") 
	 public String userType(Model m){ 
		 m.addAttribute("userType", new UserTypeModel()); 
		 return "StaffLogin"; 
	 }
	
	@RequestMapping("/userTypeSelector") 
	 public String showHomePageByUserType(@ModelAttribute("userType") UserTypeModel u,Model m,HttpSession session){ 
		String user = u.getUserType();
		String page=null;
		 switch(user) {
		    case("wardOrVillageAdministrator"):
		    	int i = wdao.validateWardAS(u.getEmail(),u.getPassword()); //validate ward administrator
		    	if(i!=1) {
		    		return "NotValidateUser";
		    	}
		    	WardAS wardas = wdao.getWardAS(u.getEmail(),u.getPassword()); //get ward administrator's info
		    	session.setAttribute("wardAS", wardas);
		    	m.addAttribute("wardAS",wardas); 
		    	int residentCountByWardAS = rdao.getResidentCountByWardAS(wardas.getWardOrVillageId());
		    	m.addAttribute("residentCountByWardAS",residentCountByWardAS);
		    	int femaleResidentCountByWardAS = rdao.getFemaleResidentCountByWardAS(wardas.getWardOrVillageId());
		    	m.addAttribute("femaleResidentCountByWardAS",femaleResidentCountByWardAS);
		    	int maleResidentCountByWardAS = rdao.getMaleResidentCountByWardAS(wardas.getWardOrVillageId());
		    	m.addAttribute("maleResidentCountByWardAS",maleResidentCountByWardAS);
		    	page="wardas/WardASHomePage";
		    	break;
		    case("generalAdministrativeStaff"):
		    	int j = gdao.validateGAS(u.getEmail(),u.getPassword()); //validate gas
			    if(j!=1) {
		    		return "NotValidateUser";
		    	}
			    GAS gas = gdao.getGAS(u.getEmail(),u.getPassword()); ////get general administrative staff's info
			    session.setAttribute("gas", gas);// to authenticate user when url link are copied from an intruder
			    m.addAttribute("gas",gas);
			    int residentCountByGAS = rdao.getResidentCountByGAS(gas.getTownshipId()); //get resident count in a township 
			    m.addAttribute("residentCountByGAS",residentCountByGAS);
			    int femaleResidentCountByGAS = rdao.getFemaleResidentCountByGAS(gas.getTownshipId()); //get female resident count in a township 
			    m.addAttribute("femaleResidentCountByGAS",femaleResidentCountByGAS);
			    int maleResidentCountByGAS = rdao.getMaleResidentCountByGAS(gas.getTownshipId()); //get male resident count in a township 
			    m.addAttribute("maleResidentCountByGAS",maleResidentCountByGAS);
			    int wardOrVillageASCountByGAS = wdao.getWardOrVillageASCountByGAS(gas.getTownshipId()); //get ward/village as count in a township
			    m.addAttribute("wardOrVillageASCountByGAS",wardOrVillageASCountByGAS);
			    int wardASCountByGAS = wdao.getWardASCountByGAS(gas.getTownshipId()); //get ward as count in a township
			    m.addAttribute("wardASCountByGAS",wardASCountByGAS);
			    int villageASCountByGAS = wdao.getVillageASCountByGAS(gas.getTownshipId()); //get ward/village as count in a township
			    m.addAttribute("villageASCountByGAS",villageASCountByGAS);
			    
		    	page="gas/GASHomePage";
		    	break;
		    case("policeOfficer"):
		    	int k = pdao.validatePoliceOfficer(u.getEmail(),u.getPassword()); //validate police officer
			    if(k!=1) {
		    		return "NotValidateUser";
		    	}
			    PoliceOfficer policeOfficer = pdao.getPoliceOfficer(u.getEmail(),u.getPassword()); //get police officer's info
			    session.setAttribute("policeOfficer", policeOfficer);
			    m.addAttribute("policeOfficer",policeOfficer);
			    m.addAttribute("search",new SearchModel());
		    	page="policeOfficer/PoliceOfficerHomePage";
		    	break;
		    case("subAdmin"): 
		    	int l = sdao.validateSAdmin(u.getEmail(),u.getPassword()); //validate sub admin
			    if(l!=1) {
		    		return "NotValidateUser";
		    	}
			    SAdmin sadmin = sdao.getSAdmin(u.getEmail(),u.getPassword());//get sub admin's info
			    session.setAttribute("sadmin", sadmin);
			    m.addAttribute("sadmin",sadmin);
			    int gasCountBySAdmin = gdao.getGASCountBySAdmin(sadmin.getStateOrRegionId());//get general administrative staff count in a state/region
			    m.addAttribute("gasCountBySAdmin",gasCountBySAdmin);
			    int policeOfficerCountBySAdmin = pdao.getPoliceOfficerCountBySAdmin(sadmin.getStateOrRegionId()); //get police officer count in a state/region
			    m.addAttribute("policeOfficerCountBySAdmin",policeOfficerCountBySAdmin);
			    int wardOrVillageASCountBySAdmin = wdao.getWardOrVillageASCountBySAdmin(sadmin.getStateOrRegionId());//get ward/village administrator count in a state/region
			    m.addAttribute("wardOrVillageASCountBySAdmin",wardOrVillageASCountBySAdmin);
			    int wardASCountBySAdmin = wdao.getWardASCountBySAdmin(sadmin.getStateOrRegionId());//get ward administrator count in a state/region
			    m.addAttribute("wardASCountBySAdmin",wardASCountBySAdmin);
			    int villageASCountBySAdmin = wdao.getVillageASCountBySAdmin(sadmin.getStateOrRegionId());//get village administrator count in a state/region
			    m.addAttribute("villageASCountBySAdmin",villageASCountBySAdmin);
			    int residentCountBySAdmin = rdao.getResidentCountBySAdmin(sadmin.getStateOrRegionId());//get resident count in a state/region
			    m.addAttribute("residentCountBySAdmin",residentCountBySAdmin);
			    int femaleResidentCountBySAdmin = rdao.getFemaleResidentCountBySAdmin(sadmin.getStateOrRegionId());//get female resident count in a state/region
			    m.addAttribute("femaleResidentCountBySAdmin",femaleResidentCountBySAdmin);
			    int maleResidentCountBySAdmin = rdao.getMaleResidentCountBySAdmin(sadmin.getStateOrRegionId());//get male resident count in a state/region
			    m.addAttribute("maleResidentCountBySAdmin",maleResidentCountBySAdmin);
			    page="sadmin/SAdminHomePage";
		    	break;
		    case("admin"): 
		    	int c = adao.validateAdmin(u.getEmail(),u.getPassword()); //validate admin
			    if(c!=1) {
		    		return "NotValidateUser";
		    	}
			    Admin admin = adao.getAdmin(u.getEmail(),u.getPassword()); //get admin's info
			    session.setAttribute("admin", admin);
			    m.addAttribute("admin",admin);
			    int sadminCountByAdmin = sdao.getSAdminCountByAdmin(); //get sadmin count in Myanmar
			    m.addAttribute("sadminCountByAdmin",sadminCountByAdmin);
			    int gasCountByAdmin = gdao.getGASCountByAdmin();//get general administrative staff count in Myanmar
			    m.addAttribute("gasCountByAdmin",gasCountByAdmin);
			    int policeOfficerCountByAdmin = pdao.getPoliceOfficerCountByAdmin(); //get police officer count in Myanmar
			    m.addAttribute("policeOfficerCountByAdmin",policeOfficerCountByAdmin);
			    int wardOrVillageASCountByAdmin = wdao.getWardOrVillageASCountByAdmin();//get ward/village administrator count in Myanmar
			    m.addAttribute("wardOrVillageASCountByAdmin",wardOrVillageASCountByAdmin);
			    int wardASCountByAdmin = wdao.getWardASCountByAdmin();//get ward administrator count in Myanmar
			    m.addAttribute("wardASCountByAdmin",wardASCountByAdmin);
			    int villageASCountByAdmin = wdao.getVillageASCountByAdmin();//get village administrator count in Myanmar
			    m.addAttribute("villageASCountByAdmin",villageASCountByAdmin);
			    int residentCountByAdmin = rdao.getResidentCountByAdmin();//get resident count in Myanmar
			    m.addAttribute("residentCountByAdmin",residentCountByAdmin);
			    int femaleResidentCountByAdmin = rdao.getFemaleResidentCountByAdmin();//get female resident count in Myanmar
			    m.addAttribute("femaleResidentCountByAdmin",femaleResidentCountByAdmin);
			    int maleResidentCountByAdmin = rdao.getMaleResidentCountByAdmin();//get male resident count in Myanmar
			    m.addAttribute("maleResidentCountByAdmin",maleResidentCountByAdmin);
			    session.setAttribute("profile",false);
		    	page="admin/AdminHomePage";
		    	break;
		    default: return "redirect:/staffLogin" ;
		 }
		 return page;
	 }
}
