package com.java.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.beans.Birth;
import com.java.beans.Death;
import com.java.beans.GAS;
import com.java.beans.PoliceOfficer;
import com.java.beans.Resident;
import com.java.beans.SAdmin;
import com.java.beans.StateOrRegion;
import com.java.beans.Township;
import com.java.beans.WardAS;
import com.java.beans.WardOrVillage;
import com.java.dao.AdminDAO;
import com.java.dao.BirthDAO;
import com.java.dao.DeathDAO;
import com.java.dao.GASDAO;
import com.java.dao.PoliceOfficerDAO;
import com.java.dao.ResidentDAO;
import com.java.dao.SAdminDAO;
import com.java.dao.StateOrRegionDAO;
import com.java.dao.TownshipDAO;
import com.java.dao.WardASDAO;
import com.java.dao.WardOrVillageDAO;
import com.java.model.ProfileModel;

@Controller 
@RequestMapping("/sadmin")
public class SAdminController {
	@Autowired 
	AdminDAO adao;
	@Autowired 
	WardOrVillageDAO vdao;
	@Autowired 
	ResidentDAO rdao;//will inject dao from XML file 
	@Autowired 
	WardASDAO wdao;//will inject dao from XML file 
	@Autowired 
	GASDAO gdao;
	@Autowired 
	PoliceOfficerDAO pdao;
	@Autowired 
	SAdminDAO sdao;
	@Autowired 
	StateOrRegionDAO srdao;
	@Autowired 
	TownshipDAO tdao;
	@Autowired 
	BirthDAO bdao;
	@Autowired 
	DeathDAO ddao;
	
	@RequestMapping("/home") 
	public String viewSAdminHomePage(Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		else {	
			SAdmin sadmin = (SAdmin) session.getAttribute("sadmin");
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
		    return "sadmin/SAdminHomePage";
		}
	}
	
	@RequestMapping("/logout") 
	 public String logout(Model m, HttpSession session){ 
		 session.invalidate();
		 return "redirect:/staffLogin"; 
	 } 
	//**************************************Edit SAdmin's Email and Password Start**********************************************//
		@RequestMapping("/getEditProfile")
		public String getEditProfile(Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			session.setAttribute("error", false);
			SAdmin authSAdmin = (SAdmin)session.getAttribute("sadmin");
			ProfileModel sadminProfile = new ProfileModel(authSAdmin.getSadminId(),authSAdmin.getSadminEmail(),authSAdmin.getSadminPassword());
			m.addAttribute("command",sadminProfile);
			return "sadmin/SAdminEditProfilePage";
		}
		
		@RequestMapping(value="editProfile",method = RequestMethod.POST) 
		 public String editProfile( @ModelAttribute("r") @Valid ProfileModel sadminProfile,BindingResult br,Model m,HttpSession session){ 
			SAdmin authSAdmin = (SAdmin)session.getAttribute("sadmin");
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			
			if(br.hasErrors()) { 
				session.setAttribute("error",true);
				ProfileModel sadminProfile1 = new ProfileModel(authSAdmin.getSadminId(),authSAdmin.getSadminEmail(),authSAdmin.getSadminPassword());
				m.addAttribute("command",sadminProfile1);
				return "sadmin/SAdminEditProfilePage"; } 
			else {session.setAttribute("error", false);}

			if(sadminProfile.getPassword().equals(sadminProfile.getNewPassword())) {
				authSAdmin.setSadminEmail(sadminProfile.getEmail());
				authSAdmin.setSadminPassword(sadminProfile.getPassword());
				sdao.updateSAdmin(authSAdmin); 
				session.setAttribute("sadmin", authSAdmin);
				session.setAttribute("passwordNotMatch",false);
				return "redirect:/sadmin/home";
				
			}
			else {
				session.setAttribute("passwordNotMatch",true);
				return "redirect:/sadmin/getEditProfile";
			}
		 } 
		//**************************************Edit SAdmin's Email and Password Finish**********************************************//
	@RequestMapping("/viewStateOrRegion/{sadminId}") 
	 public String viewStateOrRegion(@PathVariable int sadminId, Model m,HttpSession session){ 
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<StateOrRegion> stateOrRegionList = srdao.getStateOrRegionList();
		 m.addAttribute("stateOrRegionList",stateOrRegionList); 
		 return "sadmin/SAdminViewStateOrRegionPage"; 
	 }
	 
	@RequestMapping("/viewTownship/{sadminId}") 
	 public String viewTownship(@PathVariable int sadminId, Model m,HttpSession session){ 
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<Township> townshipList = tdao.getTownshipListByAdmin();
		 m.addAttribute("townshipList",townshipList); 
		 return "sadmin/SAdminViewTownshipPage"; 
	 } 
	
	@RequestMapping("/viewWardOrVillage/{sadminId}") 
	 public String viewWardOrVillage(@PathVariable int sadminId,Model m,HttpSession session){ 
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByAdmin();
		 m.addAttribute("wardOrVillageList",wardOrVillageList); 
		 return "sadmin/SAdminViewWardOrVillagePage"; 
	 } 
	
	 @RequestMapping("/viewWard/{sadminId}") 
	 public String viewWard(@PathVariable int sadminId,Model m,HttpSession session){ 
		 if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<WardOrVillage> wardList = vdao.getWardListByAdmin();
		 m.addAttribute("wardList",wardList); 
		 return "sadmin/SAdminViewWardPage"; 
	 } 
	 
	 @RequestMapping("/viewVillage/{sadminId}") 
	 public String viewVillage(@PathVariable int sadminId,Model m,HttpSession session){ 
		 if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<WardOrVillage> villageList = vdao.getVillageListByAdmin();
		 m.addAttribute("villageList",villageList); 
		 return "sadmin/SAdminViewVillagePage"; 
	 }
	 
	 @RequestMapping("/viewWardOrVillageASList/{sadminId}") 
	 public String viewWardOrVillageASList(@PathVariable int sadminId, Model m,HttpSession session){
		 if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<WardAS> wardOrVillageASListBySAdmin = wdao.getWardOrVillageASListBySAdmin(sadmin.getStateOrRegionId());		
		 m.addAttribute("wardOrVillageASListBySAdmin",wardOrVillageASListBySAdmin); 
		 return "sadmin/SAdminViewWardOrVillageASListPage"; 
	 } 
	 
	 @RequestMapping("/viewFemaleResidentList/{sadminId}") 
	 public String viewFemaleResidentList(@PathVariable int sadminId,Model m,HttpSession session){ 
		 if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
		SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<Resident> femaleResidentListBySAdmin = rdao.getFemaleResidentListBySAdmin(sadmin.getStateOrRegionId());		
		 m.addAttribute("femaleResidentListBySAdmin",femaleResidentListBySAdmin); 
		 return "sadmin/SAdminViewFemaleResidentListPage"; 
	 }
	
	@RequestMapping("/viewMaleResidentList/{sadminId}") 
	 public String viewMaleResidentList(@PathVariable int sadminId,Model m,HttpSession session){ 
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<Resident> maleResidentListBySAdmin = rdao.getMaleResidentListBySAdmin(sadmin.getStateOrRegionId());		
		 m.addAttribute("maleResidentListBySAdmin",maleResidentListBySAdmin); 
		 return "sadmin/SAdminViewMaleResidentListPage"; 
	 }
	
	@RequestMapping("/viewOver18List/{sadminId}") 
	 public String viewOver18List(@PathVariable int sadminId,Model m,HttpSession session){ 
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<Resident> residentListBySAdmin = rdao.getResidentListBySAdmin(sadmin.getStateOrRegionId());	
		 List<Resident> over18ListBySAdmin = new ArrayList<Resident>();
		 int size = residentListBySAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentListBySAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=18) {
				 over18ListBySAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over18ListBySAdmin",over18ListBySAdmin); 
		 return "sadmin/SAdminViewOver18ListPage"; 
	 }
	
	@RequestMapping("/viewOver18MaleList/{sadminId}") 
	 public String viewOver18MaleList(@PathVariable int sadminId,Model m,HttpSession session){ 
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<Resident> maleResidentListBySAdmin = rdao.getMaleResidentListBySAdmin(sadmin.getStateOrRegionId());
		 List<Resident> over18MaleListBySAdmin = new ArrayList<Resident>();
		 int size = maleResidentListBySAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = maleResidentListBySAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=18) {
				 over18MaleListBySAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over18MaleListBySAdmin",over18MaleListBySAdmin); 
		 return "sadmin/SAdminViewOver18MaleListPage"; 
	 }
	
	@RequestMapping("/viewOver18FemaleList/{sadminId}") 
	 public String viewOver18FemaleList(@PathVariable int sadminId,Model m,HttpSession session){ 
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		List<Resident> femaleResidentListBySAdmin = rdao.getFemaleResidentListBySAdmin(sadmin.getStateOrRegionId());		
		 List<Resident> over18FemaleListBySAdmin = new ArrayList<Resident>();
		 int size = femaleResidentListBySAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = femaleResidentListBySAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=18) {
				 over18FemaleListBySAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over18FemaleListBySAdmin",over18FemaleListBySAdmin); 
		 return "sadmin/SAdminViewOver18FemaleListPage"; 
	 }
	
	@RequestMapping("/viewOver85List/{sadminId}") 
	 public String viewOver85List(@PathVariable int sadminId,Model m,HttpSession session){ 
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<Resident> residentListBySAdmin = rdao.getResidentListBySAdmin(sadmin.getStateOrRegionId());	
		 List<Resident> over85ListBySAdmin = new ArrayList<Resident>();
		 int size = residentListBySAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentListBySAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=85) {
				 over85ListBySAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over85ListBySAdmin",over85ListBySAdmin); 
		 return "sadmin/SAdminViewOver85ListPage"; 
	 }
	
	@RequestMapping("/viewOver85MaleList/{sadminId}") 
	 public String viewOver85MaleList(@PathVariable int sadminId,Model m,HttpSession session){ 
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<Resident> maleResidentListBySAdmin = rdao.getMaleResidentListBySAdmin(sadmin.getStateOrRegionId());
		 List<Resident> over85MaleListBySAdmin = new ArrayList<Resident>();
		 int size = maleResidentListBySAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = maleResidentListBySAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=85) {
				 over85MaleListBySAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over85MaleListBySAdmin",over85MaleListBySAdmin); 
		 return "sadmin/SAdminViewOver85MaleListPage"; 
	 }
	
	@RequestMapping("/viewOver85FemaleList/{sadminId}") 
	 public String viewOver85FemaleList(@PathVariable int sadminId,Model m,HttpSession session){ 
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		List<Resident> femaleResidentListBySAdmin = rdao.getFemaleResidentListBySAdmin(sadmin.getStateOrRegionId());		
		 List<Resident> over85FemaleListBySAdmin = new ArrayList<Resident>();
		 int size = femaleResidentListBySAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = femaleResidentListBySAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=85) {
				 over85FemaleListBySAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over85FemaleListBySAdmin",over85FemaleListBySAdmin); 
		 return "sadmin/SAdminViewOver85FemaleListPage"; 
	 }
	@RequestMapping("/viewFemaleBirthList/{sadminId}")
	public String viewFemaleBirthList(@PathVariable int sadminId, Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		List<Birth> birthList = bdao.getFemaleBirthListBySAdmin(sadmin.getStateOrRegionId());
		m.addAttribute("birthList",birthList);
		return "sadmin/SAdminViewFemaleBirthListPage";
	}
	
	@RequestMapping("/viewMaleBirthList/{sadminId}")
	public String viewMaleBirthList(@PathVariable int sadminId, Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		List<Birth> birthList = bdao.getMaleBirthListBySAdmin(sadmin.getStateOrRegionId());
		m.addAttribute("birthList",birthList);
		return "sadmin/SAdminViewMaleBirthListPage";
	}
	@RequestMapping("/viewOver10NonNRC/{sadminId}")
	public String viewOver10NonNRC(@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<Resident> residentList = rdao. getResidentListBySAdmin(sadmin.getStateOrRegionId());
		 List<Resident> over10List = new ArrayList<Resident>();
		 int size = residentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentList.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=10) {
				 over10List.add(r);
			 }
			 i++;
		 }
		 List<Resident> over10NonNrcList = new ArrayList<Resident>();
		 int size1 = over10List.size();
		 int j =0;
		 while(j<size1) {
			 Resident r = over10List.get(j);
			 String nrc = r.getNrcNo();
			 if(nrc.equals(null) || nrc.equals("-")) {
				 over10NonNrcList.add(r);
			 }
			 j++;
		 }
		 m.addAttribute("over10NonNrcList",over10NonNrcList);
		 return "sadmin/SAdminViewOver10NonNRCPage";
	}
	
	@RequestMapping("/viewOver10MaleNonNRC/{sadminId}")
	public String viewMaleOver10NonNRC(@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		List<Resident> maleResidentList = rdao. getMaleResidentListBySAdmin(sadmin.getStateOrRegionId());
		 List<Resident> over10MaleList = new ArrayList<Resident>();
		 int size = maleResidentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = maleResidentList.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=10) {
				 over10MaleList.add(r);
			 }
			 i++;
		 }
		 List<Resident> over10MaleNonNrcList = new ArrayList<Resident>();
		 int size1 = over10MaleList.size();
		 int j =0;
		 while(j<size1) {
			 Resident r = over10MaleList.get(j);
			 String nrc = r.getNrcNo();
			 if(nrc.equals(null) || nrc.equals("-")) {
				 over10MaleNonNrcList.add(r);
			 }
			 j++;
		 }
		 m.addAttribute("over10MaleNonNrcList",over10MaleNonNrcList);
		 return "sadmin/SAdminViewOver10MaleNonNRCPage";
	}
	
	@RequestMapping("/viewOver10FemaleNonNRC/{sadminId}")
	public String viewFemaleOver10NonNRC(@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		List<Resident> femaleResidentList = rdao. getFemaleResidentListBySAdmin(sadmin.getStateOrRegionId());
		 List<Resident> over10FemaleList = new ArrayList<Resident>();
		 int size = femaleResidentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = femaleResidentList.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=10) {
				 over10FemaleList.add(r);
			 }
			 i++;
		 }
		 List<Resident> over10FemaleNonNrcList = new ArrayList<Resident>();
		 int size1 = over10FemaleList.size();
		 int j =0;
		 while(j<size1) {
			 Resident r = over10FemaleList.get(j);
			 String nrc = r.getNrcNo();
			 if(nrc.equals(null) || nrc.equals("-")) {
				 over10FemaleNonNrcList.add(r);
			 }
			 j++;
		 }
		 m.addAttribute("over10FemaleNonNrcList",over10FemaleNonNrcList);
		 return "sadmin/SAdminViewOver10FemaleNonNRCPage";
	}
	
	@RequestMapping("/viewBelow10NonNRC/{sadminId}")
	public String viewBelow10NonNRC(@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<Resident> residentList = rdao. getResidentListBySAdmin(sadmin.getStateOrRegionId());
		 List<Resident> below10List = new ArrayList<Resident>();
		 int size = residentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentList.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age<10) {
				 below10List.add(r);
			 }
			 i++;
		 }
		 List<Resident> below10NonNrcList = new ArrayList<Resident>();
		 int size1 = below10List.size();
		 int j =0;
		 while(j<size1) {
			 Resident r = below10List.get(j);
			 String nrc = r.getNrcNo();
			 if(nrc.equals(null) || nrc.equals("-")) {
				 below10NonNrcList.add(r);
			 }
			 j++;
		 }
		 m.addAttribute("below10NonNrcList",below10NonNrcList);
		 return "sadmin/SAdminViewBelow10NonNRCPage";
	}
	
	@RequestMapping("/viewBelow10MaleNonNRC/{sadminId}")
	public String viewMaleBelow10NonNRC(@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		List<Resident> maleResidentList = rdao. getMaleResidentListBySAdmin(sadmin.getStateOrRegionId());
		 List<Resident> below10MaleList = new ArrayList<Resident>();
		 int size = maleResidentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = maleResidentList.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age<10) {
				 below10MaleList.add(r);
			 }
			 i++;
		 }
		 List<Resident> below10MaleNonNrcList = new ArrayList<Resident>();
		 int size1 = below10MaleList.size();
		 int j =0;
		 while(j<size1) {
			 Resident r = below10MaleList.get(j);
			 String nrc = r.getNrcNo();
			 if(nrc.equals(null) || nrc.equals("-")) {
				 below10MaleNonNrcList.add(r);
			 }
			 j++;
		 }
		 m.addAttribute("below10MaleNonNrcList",below10MaleNonNrcList);
		 return "sadmin/SAdminViewBelow10MaleNonNRCPage";
	}
	
	@RequestMapping("/viewBelow10FemaleNonNRC/{sadminId}")
	public String viewFemaleBelow10NonNRC(@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		List<Resident> femaleResidentList = rdao. getFemaleResidentListBySAdmin(sadmin.getStateOrRegionId());
		 List<Resident> below10FemaleList = new ArrayList<Resident>();
		 int size = femaleResidentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = femaleResidentList.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age<10) {
				 below10FemaleList.add(r);
			 }
			 i++;
		 }
		 List<Resident> below10FemaleNonNrcList = new ArrayList<Resident>();
		 int size1 = below10FemaleList.size();
		 int j =0;
		 while(j<size1) {
			 Resident r = below10FemaleList.get(j);
			 String nrc = r.getNrcNo();
			 if(nrc.equals(null) || nrc.equals("-")) {
				 below10FemaleNonNrcList.add(r);
			 }
			 j++;
		 }
		 m.addAttribute("below10FemaleNonNrcList",below10FemaleNonNrcList);
		 return "sadmin/SAdminViewBelow10FemaleNonNRCPage";
	}
	@RequestMapping("/viewBCGDone/{sadminId}")
	public String viewBCGDone(@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		List<Resident> residentList = rdao. getResidentListBySAdmin(sadmin.getStateOrRegionId());
		List<Resident> bcgDoneResidentList = new ArrayList<Resident>();
		 int size = residentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentList.get(i);
			 boolean bcg = r.getBcg();
			
			 if(bcg==true) {
				 bcgDoneResidentList.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("bcgDoneResidentList",bcgDoneResidentList);
		 return "sadmin/SAdminviewBCGDonePage";
	}
	
	@RequestMapping("/viewBCGNotYet/{sadminId}")
	public String viewBCGNotYet(@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		List<Resident> residentList = rdao. getResidentListBySAdmin(sadmin.getStateOrRegionId());
		List<Resident> bcgNotYetResidentList = new ArrayList<Resident>();
		 int size = residentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentList.get(i);
			 boolean bcg = r.getBcg();
			
			 if(bcg==false) {
				 bcgNotYetResidentList.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("bcgNotYetResidentList",bcgNotYetResidentList);
		 return "sadmin/SAdminviewBCGNotYetPage";
	}
	
	@RequestMapping("/viewFiveVaccinesDone/{sadminId}")
	public String viewFiveVaccinesDone(@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		List<Resident> residentList = rdao. getResidentListBySAdmin(sadmin.getStateOrRegionId());
		List<Resident> fiveVaccinesDoneResidentList = new ArrayList<Resident>();
		 int size = residentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentList.get(i);
			 boolean fiveVaccines1 = r.getFiveVaccines1();
			 boolean fiveVaccines2 = r.getFiveVaccines2();
			 boolean fiveVaccines3 = r.getFiveVaccines3();
			
			 if(fiveVaccines1==true && fiveVaccines2==true && fiveVaccines3==true ) {
				 fiveVaccinesDoneResidentList.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("fiveVaccinesDoneResidentList",fiveVaccinesDoneResidentList);
		 return "sadmin/SAdminviewFiveVaccinesDonePage";
	}
	
	@RequestMapping("/viewFiveVaccinesNotYet/{sadminId}")
	public String viewFiveVaccinesNotYet(@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		List<Resident> residentList = rdao. getResidentListBySAdmin(sadmin.getStateOrRegionId());
		List<Resident> fiveVaccinesNotYetResidentList = new ArrayList<Resident>();
		 int size = residentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentList.get(i);
			 boolean fiveVaccines1 = r.getFiveVaccines1();
			 boolean fiveVaccines2 = r.getFiveVaccines2();
			 boolean fiveVaccines3 = r.getFiveVaccines3();
			
			 if(fiveVaccines1==false || fiveVaccines2==false || fiveVaccines3==false ) {
				 fiveVaccinesNotYetResidentList.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("fiveVaccinesNotYetResidentList",fiveVaccinesNotYetResidentList);
		 return "sadmin/SAdminviewFiveVaccinesNotYetPage";
	}

	@RequestMapping("/viewVitaminADone/{sadminId}")
	public String viewVitaminADone(@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		List<Resident> residentList = rdao. getResidentListBySAdmin(sadmin.getStateOrRegionId());
		List<Resident> vitaminADoneResidentList = new ArrayList<Resident>();
		 int size = residentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentList.get(i);
			 boolean vitaminA = r.getVitaminA();
			 
			 if(vitaminA == true) {
				 vitaminADoneResidentList.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("vitaminADoneResidentList",vitaminADoneResidentList);
		 return "sadmin/SAdminviewVitaminADonePage";
	}
	
	@RequestMapping("/viewVitaminANotYet/{sadminId}")
	public String viewVitaminANotYet(@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		List<Resident> residentList = rdao. getResidentListBySAdmin(sadmin.getStateOrRegionId());
		List<Resident> vitaminANotYetResidentList = new ArrayList<Resident>();
		 int size = residentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentList.get(i);
			 boolean vitaminA = r.getVitaminA();
			 
			 if(vitaminA == false) {
				 vitaminANotYetResidentList.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("vitaminANotYetResidentList",vitaminANotYetResidentList);
		 return "sadmin/SAdminviewVitaminANotYetPage";
	}
	
	@RequestMapping("/view9MonthsList/{sadminId}") 
	 public String view9MonthsList(@PathVariable int sadminId,Model m,HttpSession session){ 
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<Resident> residentList = rdao. getResidentListBySAdmin(sadmin.getStateOrRegionId());
		 List<Resident> nineMonthsList = new ArrayList<Resident>();
		 int size = residentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentList.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int ageInMonth = r.calculateAgeInMonth(dob1);
			 if(ageInMonth==9) {
				 nineMonthsList.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("nineMonthsList",nineMonthsList); 
		 return "sadmin/SAdminView9MonthsListPage"; 
	 }
	
	@RequestMapping("/viewHPVVaccineDone/{sadminId}")
	public String viewHPVVaccineDone(@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		List<Resident> residentList = rdao. getFemaleResidentListBySAdmin(sadmin.getStateOrRegionId());
		List<Resident> hpvVaccineDoneResidentList = new ArrayList<Resident>();
		 int size = residentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentList.get(i);
			 boolean hpv = r.getHpvVaccine();
			 
			 if(hpv == true) {
				 hpvVaccineDoneResidentList.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("hpvVaccineDoneResidentList",hpvVaccineDoneResidentList);
		 return "sadmin/SAdminviewHPVVaccineDonePage";
	}
	
	@RequestMapping("/viewHPVVaccineNotYet/{sadminId}")
	public String viewHPVVaccineNotYet(@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		List<Resident> residentList = rdao. getFemaleResidentListBySAdmin(sadmin.getStateOrRegionId());
		List<Resident> hpvVaccineNotYetResidentList = new ArrayList<Resident>();
		 int size = residentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentList.get(i);
			 boolean hpv = r.getHpvVaccine();
			 
			 if(hpv == false) {
				 hpvVaccineNotYetResidentList.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("hpvVaccineNotYetResidentList",hpvVaccineNotYetResidentList);
		 return "sadmin/SAdminviewHPVVaccineNotYetPage";
	}
	//*************************Village AS Management Start*********************************************//
	 @RequestMapping("/manageVillageASList/{sadminId}") 
	 public String viewVillageASList(@PathVariable int sadminId, Model m,HttpSession session){ 
		 if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<WardAS> villageASListBySAdmin = wdao.getVillageASListBySAdmin(sadmin.getStateOrRegionId());		
		 m.addAttribute("villageASListBySAdmin",villageASListBySAdmin); 
		 return "sadmin/SAdminManageVillageASListPage"; 
	 } 
	 
	 /*@RequestMapping(value="deleteVillageAS/{wardasId}/{sadminId}",method = RequestMethod.GET)
		public String deleteVillageAS(@PathVariable int wardasId,@PathVariable int sadminId, Model m,HttpSession session) {
		 if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 wdao.deleteWardAS(wardasId);
			 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			return "redirect:/sadmin/manageVillageASList/{sadminId}";
		}*/
		
		@RequestMapping("/getAddVillageAS/{sadminId}")
		public String getAddVillageAS(@PathVariable int sadminId,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			session.setAttribute("error", false);
			m.addAttribute("wardas",new WardAS());
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 List<WardOrVillage> villageList = vdao.getVillageListBySAdmin(sadmin.getStateOrRegionId());
			 m.addAttribute("villageList",villageList); 
			return "sadmin/SAdminAddVillageASPage";
		}
		
		@RequestMapping(value="addVillageAS/{sadminId}", method = RequestMethod.POST)
		public String addVillageAS(@ModelAttribute("wardas") @Valid WardAS w,BindingResult br,@PathVariable int sadminId,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			if(br.hasErrors()) { 
				m.addAttribute("wardas",new WardAS());
				SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
				 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
				 List<WardOrVillage> villageList = vdao.getVillageListBySAdmin(sadmin.getStateOrRegionId());
				 m.addAttribute("villageList",villageList); 
				session.setAttribute("error", true);
				return "admin/AdminEditResidentPage"; 
			} 
			wdao.saveWardAS(w);
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 return "redirect:/sadmin/manageVillageASList/{sadminId}";
		}
		
		@RequestMapping("/getEditVillageAS/{wardasId}/{sadminId}")
		public String getEditVillageAS(@PathVariable int wardasId,@PathVariable int sadminId,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			session.setAttribute("error", false);
			WardAS w = wdao.getWardAS(wardasId);
			m.addAttribute("command",w);
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 List<WardOrVillage> villageList = vdao.getVillageListBySAdmin(sadmin.getStateOrRegionId());
			 m.addAttribute("villageList",villageList); 
			return "sadmin/SAdminEditVillageASPage";
		}
		
		@RequestMapping(value="editVillageAS/{sadminId}",method = RequestMethod.POST) 
		 public String editVillageAS(@ModelAttribute("w") @Valid WardAS w,BindingResult br,@PathVariable int sadminId,Model m,HttpSession session){ 
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			if(br.hasErrors()) { 
				WardAS w1 = wdao.getWardAS(w.getWardASId());
				m.addAttribute("command",w1);
				SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
				 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
				 List<WardOrVillage> villageList = vdao.getVillageListBySAdmin(sadmin.getStateOrRegionId());
				 m.addAttribute("villageList",villageList); 
				session.setAttribute("error", true);
				return "admin/AdminEditResidentPage"; 
			} 
			wdao.updateWardAS(w); 
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 return "redirect:/sadmin/manageVillageASList/{sadminId}";
		 } 
		
	//*************************Village AS Management Finish*********************************************//
	
	//*************************Ward AS Management Start*********************************************//
	@RequestMapping("/manageWardASList/{sadminId}") 
	 public String viewWardASList(@PathVariable int sadminId, Model m,HttpSession session){ 
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<WardAS> wardASListBySAdmin = wdao.getWardASListBySAdmin(sadmin.getStateOrRegionId());		
		 m.addAttribute("wardASListBySAdmin",wardASListBySAdmin); 
		 return "sadmin/SAdminManageWardASListPage"; 
	 } 
	/*@RequestMapping(value="deleteWardAS/{wardasId}/{sadminId}",method = RequestMethod.GET)
	public String deleteWardAS(@PathVariable int wardasId,@PathVariable int sadminId, Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 wdao.deleteWardAS(wardasId);
		 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		return "redirect:/sadmin/manageWardASList/{sadminId}";
	}*/
	
	@RequestMapping("/getAddWardAS/{sadminId}")
	public String getAddWardAS(@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		m.addAttribute("wardas",new WardAS());
		SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<WardOrVillage> wardList = vdao.getWardListBySAdmin(sadmin.getStateOrRegionId());
		 m.addAttribute("wardList",wardList); 
		return "sadmin/SAdminAddWardASPage";
	}
	
	@RequestMapping(value="addWardAS/{sadminId}", method = RequestMethod.POST)
	public String addWardAS(@ModelAttribute("wardas") @Valid WardAS w,BindingResult br,@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		if(br.hasErrors()) { 
			m.addAttribute("wardas",new WardAS());
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 List<WardOrVillage> wardList = vdao.getWardListBySAdmin(sadmin.getStateOrRegionId());
			 m.addAttribute("wardList",wardList); 
			session.setAttribute("error", true);
			return "admin/AdminEditResidentPage"; 
		} 
		wdao.saveWardAS(w);
		SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 return "redirect:/sadmin/manageWardASList/{sadminId}";
	}
	
	@RequestMapping("/getEditWardAS/{wardasId}/{sadminId}")
	public String getEditWardAS(@PathVariable int wardasId,@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		WardAS w = wdao.getWardAS(wardasId);
		m.addAttribute("command",w);
		SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 List<WardOrVillage> wardList = vdao.getWardListBySAdmin(sadmin.getStateOrRegionId());
		 m.addAttribute("wardList",wardList); 
		return "sadmin/SAdminEditWardASPage";
	}
	
	@RequestMapping(value="editWardAS/{sadminId}",method = RequestMethod.POST) 
	 public String editWardAS(@ModelAttribute("w") @Valid WardAS w,BindingResult br,@PathVariable int sadminId,Model m,HttpSession session){
		if(session.getAttribute("sadmin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		if(br.hasErrors()) { 
			WardAS w1 = wdao.getWardAS(w.getWardASId());
			m.addAttribute("command",w1);
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 List<WardOrVillage> wardList = vdao.getWardListBySAdmin(sadmin.getStateOrRegionId());
			 m.addAttribute("wardList",wardList); 
			session.setAttribute("error", true);
			return "admin/AdminEditResidentPage"; 
		} 
		wdao.updateWardAS(w); 
		SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
		 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
		 return "redirect:/sadmin/manageWardASList/{sadminId}";
	 } 
	
	
	//*************************Ward AS Management Finish*********************************************//
	
	//*******************Manage police officer Start********************************************//
		@RequestMapping(value="managePoliceOfficerList/{sadminId}")
		 public String viewPoliceOfficerList(@PathVariable int sadminId, Model m,HttpSession session){ 
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 List<PoliceOfficer> policeOfficerListBySAdmin = pdao.getPoliceOfficerListBySAdmin(sadmin.getStateOrRegionId());
			 m.addAttribute("policeOfficerListBySAdmin",policeOfficerListBySAdmin); 
			 return "sadmin/SAdminManagePoliceOfficerPage"; 
	   } 
		
		/*@RequestMapping(value="deletePoliceOfficer/{policeOfficerId}/{sadminId}",method = RequestMethod.GET)
		public String deletePoliceOfficer(@PathVariable int policeOfficerId,@PathVariable int sadminId, Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			pdao.deletePoliceOfficer(policeOfficerId);
			 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			return "redirect:/sadmin/managePoliceOfficerList/{sadminId}";
		}*/
		
		@RequestMapping("/getAddPoliceOfficer/{sadminId}")
		public String getAddPoliceOfficer(@PathVariable int sadminId,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			session.setAttribute("error", false);
			m.addAttribute("policeOfficer",new PoliceOfficer());
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 List<Township> townshipList = tdao.getTownshipListBySAdmin(sadmin.getStateOrRegionId());
			 m.addAttribute("townshipList",townshipList); 
			return "sadmin/SAdminAddPoliceOfficerPage";
		}
		
		@RequestMapping(value="addPoliceOfficer/{sadminId}", method = RequestMethod.POST)
		public String addPoliceOfficer(@ModelAttribute("policeOfficer") @Valid PoliceOfficer p,BindingResult br,@PathVariable int sadminId,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			if(br.hasErrors()) { 
				m.addAttribute("policeOfficer",new PoliceOfficer());
				SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
				 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
				 List<Township> townshipList = tdao.getTownshipListBySAdmin(sadmin.getStateOrRegionId());
				 m.addAttribute("townshipList",townshipList); 
				session.setAttribute("error", true);
				return "sadmin/SAdminAddPoliceOfficerPage"; 
			} 
			pdao.savePoliceOfficer(p);
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 return "redirect:/sadmin/managePoliceOfficerList/{sadminId}";
		}
		
		@RequestMapping("/getEditPoliceOfficer/{policeOfficerId}/{sadminId}")
		public String getEditPoliceOfficer(@PathVariable int policeOfficerId,@PathVariable int sadminId,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			session.setAttribute("error", false);
			PoliceOfficer p = pdao.getPoliceOfficer(policeOfficerId);
			m.addAttribute("command",p);
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 List<Township> townshipList = tdao.getTownshipListBySAdmin(sadmin.getStateOrRegionId());
			 m.addAttribute("townshipList",townshipList);
			return "sadmin/SAdminEditPoliceOfficerPage";
		}
		
		@RequestMapping(value="editPoliceOfficer/{sadminId}",method = RequestMethod.POST) 
		 public String editPoliceOfficer(@ModelAttribute("p") @Valid PoliceOfficer p,BindingResult br,@PathVariable int sadminId,Model m,HttpSession session){ 
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			if(br.hasErrors()) { 
				PoliceOfficer p1 = pdao.getPoliceOfficer(p.getPoliceOfficerId());
				m.addAttribute("command",p1);
				SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
				 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
				 List<Township> townshipList = tdao.getTownshipListBySAdmin(sadmin.getStateOrRegionId());
				 m.addAttribute("townshipList",townshipList);
				session.setAttribute("error", true);
				return "sadmin/SAdminEditPoliceOfficerPage"; 
			} 
			pdao.updatePoliceOfficer(p); 
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 return "redirect:/sadmin/managePoliceOfficerList/{sadminId}";
		 } 
		//**************************Manage police officer Finish*******************************************/
		
		//************************Manage general administrative staff Start***************************/
		@RequestMapping(value="manageGASList/{sadminId}")
		 public String viewGASList(@PathVariable int sadminId, Model m,HttpSession session){ 
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 List<GAS> gasListBySAdmin = gdao.getGASListBySAdmin(sadmin.getStateOrRegionId());
			 m.addAttribute("gasListBySAdmin",gasListBySAdmin); 
			 return "sadmin/SAdminManageGASPage"; 
	   } 
		
		/*@RequestMapping(value="deleteGAS/{gasId}/{sadminId}",method = RequestMethod.GET)
		public String deleteGAS(@PathVariable int gasId,@PathVariable int sadminId, Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 gdao.deleteGAS(gasId);
			 SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			return "redirect:/sadmin/manageGASList/{sadminId}";
		}*/
		
		@RequestMapping("/getAddGAS/{sadminId}")
		public String getAddGAS(@PathVariable int sadminId,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			session.setAttribute("error", false);
			m.addAttribute("gas",new GAS());
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 List<Township> townshipList = tdao.getTownshipListBySAdmin(sadmin.getStateOrRegionId());
			 m.addAttribute("townshipList",townshipList); 
			return "sadmin/SAdminAddGASPage";
		}
		
		@RequestMapping(value="addGAS/{sadminId}", method = RequestMethod.POST)
		public String addGAS(@ModelAttribute("gas") @Valid GAS g,BindingResult br,@PathVariable int sadminId,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			if(br.hasErrors()) { 
				m.addAttribute("gas",new GAS());
				SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
				 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
				 List<Township> townshipList = tdao.getTownshipListBySAdmin(sadmin.getStateOrRegionId());
				 m.addAttribute("townshipList",townshipList); 
				session.setAttribute("error", true);
				return "sadmin/SAdminAddGASPage"; 
			} 
			gdao.saveGAS(g);
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 return "redirect:/sadmin/manageGASList/{sadminId}";
		}
		
		@RequestMapping("/getEditGAS/{gasId}/{sadminId}")
		public String getEditGAS(@PathVariable int gasId,@PathVariable int sadminId,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			session.setAttribute("error", false);
			GAS g = gdao.getGAS(gasId);
			m.addAttribute("command",g);
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 List<Township> townshipList = tdao.getTownshipListBySAdmin(sadmin.getStateOrRegionId());
			 m.addAttribute("townshipList",townshipList);
			return "sadmin/SAdminEditGASPage";
		}
		
		@RequestMapping(value="editGAS/{sadminId}",method = RequestMethod.POST) 
		 public String editGAS(@ModelAttribute("g") @Valid GAS g,BindingResult br,@PathVariable int sadminId,Model m,HttpSession session){ 
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			if(br.hasErrors()) { 
				GAS g1 = gdao.getGAS(g.getGasId());
				m.addAttribute("command",g1);
				SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
				 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
				 List<Township> townshipList = tdao.getTownshipListBySAdmin(sadmin.getStateOrRegionId());
				 m.addAttribute("townshipList",townshipList);
				session.setAttribute("error", true);
				return "sadmin/SAdminEditGASPage"; 
			} 
			gdao.updateGAS(g); 
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 return "redirect:/sadmin/manageGASList/{sadminId}";
		 } 
		//*********************Manage general administrative staff Finish***********************//
		//**********************Resident Management Start**************************************//
		@RequestMapping("/manageResidentList/{sadminId}") 
		 public String viewResidentList(@PathVariable int sadminId,Model m,HttpSession session){ 
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 List<Resident> residentListBySAdmin = rdao.getResidentListBySAdmin(sadmin.getStateOrRegionId());		
			 m.addAttribute("residentListBySAdmin",residentListBySAdmin); 
			 return "sadmin/SAdminManageResidentPage"; 
		 }
		
	/*	@RequestMapping(value="deleteResident/{residentId}/{sadminId}",method = RequestMethod.GET)
		public String deleteResident(@PathVariable int residentId,@PathVariable int sadminId, Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 rdao.deleteResident(residentId);
			return "redirect:/sadmin/manageResidentList/{sadminId}";
		}*/
		
		@RequestMapping("/getAddResident/{sadminId}")
		public String getAddResident(@PathVariable int sadminId,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			session.setAttribute("error", false);
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListBySAdmin(sadmin.getStateOrRegionId());
			m.addAttribute("wardOrVillageList",wardOrVillageList);
			m.addAttribute("resident",new Resident());
			return "sadmin/SAdminAddResidentPage";
		}
		
		@RequestMapping(value="addResident/{sadminId}", method = RequestMethod.POST)
		public String addResident(@PathVariable int sadminId, @ModelAttribute("resident") @Valid Resident r,BindingResult br,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			if(br.hasErrors()) { 
				SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
				 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
				List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListBySAdmin(sadmin.getStateOrRegionId());
				m.addAttribute("wardOrVillageList",wardOrVillageList);
				m.addAttribute("resident",new Resident());
				session.setAttribute("error", true);
				return "sadmin/SAdminAddResidentPage"; 
			} 
			
			if(rdao.checkNrcDuplication(r.getNrcNo())==1) {
				m.addAttribute("nrcDuplicateError",true);
				SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
				 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
				List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListBySAdmin(sadmin.getStateOrRegionId());
				m.addAttribute("wardOrVillageList",wardOrVillageList);
				m.addAttribute("resident",new Resident());
				return "sadmin/SAdminAddResidentPage";
			}
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 r.setDeceased(false);
			rdao.saveResident(r);
			return "redirect:/sadmin/manageResidentList/{sadminId}";
		}
		
		@RequestMapping("/getEditResident/{residentId}/{sadminId}")
		public String getEditResident(@PathVariable int sadminId, @PathVariable int residentId,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			session.setAttribute("error", false);
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListBySAdmin(sadmin.getStateOrRegionId());
			m.addAttribute("wardOrVillageList",wardOrVillageList);
			Resident r = rdao.getResident(residentId);
			m.addAttribute("command",r);
			return "sadmin/SAdminEditResidentPage";
		}
		
		@RequestMapping(value="editResident/{sadminId}",method = RequestMethod.POST) 
		 public String editResident(@PathVariable int sadminId, @ModelAttribute("r") @Valid Resident r,BindingResult br,Model m,HttpSession session){ 
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			Resident resident = rdao.getResident(r.getResidentId());
			if(br.hasErrors()) { 
				SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
				 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
				List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListBySAdmin(sadmin.getStateOrRegionId());
				m.addAttribute("wardOrVillageList",wardOrVillageList);
				m.addAttribute("command",resident);
				session.setAttribute("error", true);
				return "sadmin/SAdminEditResidentPage"; 
			} 
			if(rdao.checkNrcDuplication(r.getNrcNo())==1 && (r.getNrcNo().equals(resident.getNrcNo())==false)) {
				m.addAttribute("nrcDuplicateError",true);
				SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
				 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
				List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListBySAdmin(sadmin.getStateOrRegionId());
				m.addAttribute("wardOrVillageList",wardOrVillageList);
				m.addAttribute("command",resident);
				return "sadmin/SAdminEditResidentPage";
			}
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			rdao.updateResident(r); 
			return "redirect:/sadmin/manageResidentList/{sadminId}";
		 } 
		//**********************Resident Management Finish**************************************//
		
		//*******************************Birth List Management Start*************************************//
		@RequestMapping("/manageBirthList/{sadminId}")
		public String viewBirthList(@PathVariable int sadminId,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			List<Birth> birthList = bdao.getBirthListBySAdmin(sadmin.getStateOrRegionId());
			m.addAttribute("birthList",birthList);
			return "sadmin/SAdminManageBirthPage";
		}
		
		/*@RequestMapping(value="deleteBirth/{birthId}/{sadminId}",method = RequestMethod.GET)
		public String deleteBirth(@PathVariable int birthId,@PathVariable int sadminId, Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			bdao.deleteBirth(birthId);
			return "redirect:/sadmin/manageBirthList/{sadminId}";
		}*/
		
		@RequestMapping("/getAddBirth/{sadminId}")
		public String getAddBirth(@PathVariable int sadminId,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListBySAdmin(sadmin.getStateOrRegionId());
			m.addAttribute("wardOrVillageList",wardOrVillageList);
			m.addAttribute("birth",new Birth());
			return "sadmin/SAdminAddBirthPage";
		}
		
		@RequestMapping(value="addBirth/{sadminId}", method = RequestMethod.POST)
		public String addBirth(@PathVariable int sadminId, @ModelAttribute("birth") Birth b,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			bdao.saveBirth(b);
			Resident r = new Resident();
			r.setGender(b.getChildGender());
			r.setFatherName(b.getChildFatherName());
			r.setMotherName(b.getChildMotherName());
			r.setDob(b.getDateOfBirth());
			r.setWardOrVillageId(b.getWardOrVillageId());
			r.setResidentName("-");
			r.setNrcNo("-");
			r.setEthnicity("-");
			r.setNote("-");
			r.setResidentEmail("-");
			r.setResidentPassword("-");
			rdao.saveResident(r);
			return "redirect:/sadmin/manageBirthList/{sadminId}";
		}
		
		@RequestMapping("/getEditBirth/{birthId}/{sadminId}")
		public String getEditBirth(@PathVariable int sadminId, @PathVariable int birthId,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListBySAdmin(sadmin.getStateOrRegionId());
			m.addAttribute("wardOrVillageList",wardOrVillageList);
			Birth b= bdao.getBirth(birthId);
			m.addAttribute("command",b);
			return "sadmin/SAdminEditBirthPage";
		}
		
		@RequestMapping(value="editBirth/{sadminId}",method = RequestMethod.POST) 
		 public String editBirth(@PathVariable int sadminId, @ModelAttribute("b") Birth b,Model m,HttpSession session){ 
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			bdao.updateBirth(b); 
			return "redirect:/sadmin/manageBirthList/{sadminId}";
		 } 
		
		//*******************************Birth List Management Finish*************************************//
		//*******************************Death List Management Start*************************************//
		@RequestMapping("/manageDeathList/{sadminId}")
		public String viewDeathList(@PathVariable int sadminId, Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			List<Death> deathList = ddao.getDeathListBySAdmin(sadmin.getStateOrRegionId());
			m.addAttribute("deathList",deathList);
			return "sadmin/SAdminManageDeathPage";
		}
		
		/*@RequestMapping(value="deleteDeath/{deathId}/{sadminId}",method = RequestMethod.GET)
		public String deleteDeath(@PathVariable int sadminId, @PathVariable int deathId, Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			ddao.deleteDeath(deathId);
			return "redirect:/sadmin/manageDeathList/{sadminId}";
		}*/
		
		@RequestMapping("/getAddDeath/{sadminId}")
		public String getAddDeath(@PathVariable int sadminId, Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 List<Resident> residentList = rdao.getResidentListBySAdmin(sadmin.getStateOrRegionId());
			 m.addAttribute("residentList",residentList);
			m.addAttribute("death",new Death());
			return "sadmin/SAdminAddDeathPage";
		}
		
		@RequestMapping(value="addDeath/{sadminId}", method = RequestMethod.POST)
		public String addDeath(@PathVariable int sadminId, @ModelAttribute("death") Death d,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			ddao.saveDeath(d);
			Resident r = rdao.getResident(d.getResidentId());
			r.setDeceased(true);
			rdao.updateResident(r);
			return "redirect:/sadmin/manageDeathList/{sadminId}";
		}
		
		@RequestMapping("/getEditDeath/{deathId}/{sadminId}")
		public String getEditDeath(@PathVariable int sadminId, @PathVariable int deathId,Model m,HttpSession session) {
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			 List<Resident> residentList = rdao.getDeceasedAliveResidentListBySAdmin(sadmin.getStateOrRegionId());
			m.addAttribute("residentList",residentList);
			Death d= ddao.getDeath(deathId);
			int residentId = d.getResidentId();
			Resident r = rdao.getDeceasedAliveResident(residentId);
			r.setDeceased(false);
			rdao.updateResident(r);
			m.addAttribute("command",d);
			return "sadmin/SAdminEditDeathPage";
		}
		
		@RequestMapping(value="editDeath/{sadminId}",method = RequestMethod.POST) 
		 public String editDeath(@PathVariable int sadminId, @ModelAttribute("d") Death d,Model m,HttpSession session){ 
			if(session.getAttribute("sadmin")==null) {
		 		return "redirect:/staffLogin";
		 	}
			SAdmin sadmin = sdao.getSAdmin(sadminId); // To use sadmin in the next page
			 m.addAttribute("sadmin",sadmin); // To use sadmin in the next page
			ddao.updateDeath(d); 
			Resident r = rdao.getResident(d.getResidentId());
			r.setDeceased(true);
			rdao.updateResident(r);
			return "redirect:/sadmin/manageDeathList/{sadminId}";
		 } 
		
		//*******************************Death List Management Finish************************************//
}
