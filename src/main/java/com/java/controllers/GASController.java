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
import com.java.beans.Resident;
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
@RequestMapping("/gas")
public class GASController {
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
	public String viewGASHomePage(Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		else {	
			GAS gas = (GAS) session.getAttribute("gas");
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
		    return "gas/GASHomePage";
		}
	}
	
	@RequestMapping("/logout") 
	 public String logout(Model m, HttpSession session){ 
		 session.invalidate();
		 return "redirect:/staffLogin"; 
	 } 
	//**************************************Edit GAS's Email and Password Start**********************************************//
			@RequestMapping("/getEditProfile")
			public String getEditProfile(Model m,HttpSession session) {
				if(session.getAttribute("gas")==null) {
			 		return "redirect:/staffLogin";
			 	}
				session.setAttribute("error", false);
				GAS gas = (GAS)session.getAttribute("gas");
				ProfileModel gasProfile = new ProfileModel(gas.getGasId(),gas.getGasEmail(),gas.getGasPassword());
				m.addAttribute("command",gasProfile);
				return "gas/GASEditProfilePage";
			}
			
			@RequestMapping(value="editProfile",method = RequestMethod.POST) 
			 public String editProfile( @ModelAttribute("r") @Valid ProfileModel gasProfile,BindingResult br,Model m,HttpSession session){ 
				GAS gas = (GAS)session.getAttribute("gas");
				if(session.getAttribute("gas")==null) {
			 		return "redirect:/staffLogin";
			 	}
				
				if(br.hasErrors()) { 
					session.setAttribute("error",true);
					ProfileModel gasProfile1 = new ProfileModel(gas.getGasId(),gas.getGasEmail(),gas.getGasPassword());
					m.addAttribute("command",gasProfile1);
					return "gas/GASEditProfilePage"; 
				}
				else {session.setAttribute("error", false);}

				if(gasProfile.getPassword().equals(gasProfile.getNewPassword())) {
					gas.setGasEmail(gasProfile.getEmail());
					gas.setGasPassword(gasProfile.getPassword());
					gdao.updateGAS(gas); 
					session.setAttribute("gas", gas);
					session.setAttribute("passwordNotMatch",false);
					return "redirect:/gas/home";
					
				}
				else {
					session.setAttribute("passwordNotMatch",true);
					return "redirect:/gas/getEditProfile";
				}
			 } 
			//**************************************Edit GAS's Email and Password Finish**********************************************//
	@RequestMapping("/viewStateOrRegion/{gasId}") 
	 public String viewStateOrRegion(@PathVariable int gasId, Model m,HttpSession session){
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<StateOrRegion> stateOrRegionList = srdao.getStateOrRegionList();
		 m.addAttribute("stateOrRegionList",stateOrRegionList); 
		 return "gas/GASViewStateOrRegionPage"; 
	 }
	 
	@RequestMapping("/viewTownship/{gasId}") 
	 public String viewTownship(@PathVariable int gasId, Model m,HttpSession session){ 
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<Township> townshipList = tdao.getTownshipListByAdmin();
		 m.addAttribute("townshipList",townshipList); 
		 return "gas/GASViewTownshipPage"; 
	 } 
	
	@RequestMapping("/viewWardOrVillage/{gasId}") 
	 public String viewWardOrVillage(@PathVariable int gasId,Model m,HttpSession session){ 
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByAdmin();
		 m.addAttribute("wardOrVillageList",wardOrVillageList); 
		 return "gas/GASViewWardOrVillagePage"; 
	 } 
	
	 @RequestMapping("/viewWard/{gasId}") 
	 public String viewWard(@PathVariable int gasId,Model m,HttpSession session){
		 if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<WardOrVillage> wardList = vdao.getWardListByAdmin();
		 m.addAttribute("wardList",wardList); 
		 return "gas/GASViewWardPage"; 
	 } 
	 
	 @RequestMapping("/viewVillage/{gasId}") 
	 public String viewVillage(@PathVariable int gasId,Model m,HttpSession session){ 
		 if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<WardOrVillage> villageList = vdao.getVillageListByAdmin();
		 m.addAttribute("villageList",villageList); 
		 return "gas/GASViewVillagePage"; 
	 }
	 
	 @RequestMapping("/viewWardOrVillageASList/{gasId}") 
	 public String viewWardOrVillageASList(@PathVariable int gasId, Model m,HttpSession session){
		 if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<WardAS> wardOrVillageASList = wdao.getWardOrVillageASListByGAS(g.getTownshipId());		
		 m.addAttribute("wardOrVillageASList",wardOrVillageASList); 
		 return "gas/GASViewWardOrVillageASListPage"; 
	 } 
	 
	 @RequestMapping("/viewFemaleResidentList/{gasId}") 
	 public String viewFemaleResidentList(@PathVariable int gasId,Model m,HttpSession session){ 
		 if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<Resident> femaleResidentList = rdao.getFemaleResidentListByGAS(g.getTownshipId());		
		 m.addAttribute("femaleResidentList",femaleResidentList); 
		 return "gas/GASViewFemaleResidentListPage"; 
	 }
	
	@RequestMapping("/viewMaleResidentList/{gasId}") 
	 public String viewMaleResidentList(@PathVariable int gasId,Model m,HttpSession session){
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<Resident> maleResidentList = rdao.getMaleResidentListByGAS(g.getTownshipId());		
		 m.addAttribute("maleResidentList",maleResidentList); 
		 return "gas/GASViewMaleResidentListPage"; 
	 }
	
	@RequestMapping("/viewOver18List/{gasId}") 
	 public String viewOver18List(@PathVariable int gasId,Model m,HttpSession session){ 
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<Resident> residentList = rdao.getResidentListByGAS(g.getTownshipId());	
		 List<Resident> over18List = new ArrayList<Resident>();
		 int size = residentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentList.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=18) {
				 over18List.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over18List",over18List); 
		 return "gas/GASViewOver18ListPage"; 
	 }
	
	@RequestMapping("/viewOver18MaleList/{gasId}") 
	 public String viewOver18MaleList(@PathVariable int gasId,Model m,HttpSession session){ 
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<Resident> maleResidentList = rdao.getMaleResidentListByGAS(g.getTownshipId());
		 List<Resident> over18MaleList = new ArrayList<Resident>();
		 int size = maleResidentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = maleResidentList.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=18) {
				 over18MaleList.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over18MaleList",over18MaleList); 
		 return "gas/GASViewOver18MaleListPage"; 
	 }
	
	@RequestMapping("/viewOver18FemaleList/{gasId}") 
	 public String viewOver18FemaleList(@PathVariable int gasId,Model m,HttpSession session){ 
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		List<Resident> femaleResidentList = rdao.getFemaleResidentListByGAS(g.getTownshipId());		
		 List<Resident> over18FemaleList = new ArrayList<Resident>();
		 int size = femaleResidentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = femaleResidentList.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=18) {
				 over18FemaleList.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over18FemaleList",over18FemaleList); 
		 return "gas/GASViewOver18FemaleListPage"; 
	 }
	
	@RequestMapping("/viewOver85List/{gasId}") 
	 public String viewOver85List(@PathVariable int gasId,Model m,HttpSession session){ 
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<Resident> residentList = rdao.getResidentListByGAS(g.getTownshipId());	
		 List<Resident> over85List = new ArrayList<Resident>();
		 int size = residentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentList.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=85) {
				 over85List.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over85List",over85List); 
		 return "gas/GASViewOver85ListPage"; 
	 }
	
	@RequestMapping("/viewOver85MaleList/{gasId}") 
	 public String viewOver85MaleList(@PathVariable int gasId,Model m,HttpSession session){
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<Resident> maleResidentList = rdao.getMaleResidentListByGAS(g.getTownshipId());
		 List<Resident> over85MaleList = new ArrayList<Resident>();
		 int size = maleResidentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = maleResidentList.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=85) {
				 over85MaleList.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over85MaleList",over85MaleList); 
		 return "gas/GASViewOver85MaleListPage"; 
	 }
	
	@RequestMapping("/viewOver85FemaleList/{gasId}") 
	 public String viewOver85FemaleList(@PathVariable int gasId,Model m,HttpSession session){ 
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		List<Resident> femaleResidentList = rdao.getFemaleResidentListByGAS(g.getTownshipId());		
		 List<Resident> over85FemaleList = new ArrayList<Resident>();
		 int size = femaleResidentList.size();
		 int i =0;
		 while(i<size) {
			 Resident r = femaleResidentList.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=85) {
				 over85FemaleList.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over85FemaleList",over85FemaleList); 
		 return "gas/GASViewOver85FemaleListPage"; 
	 }
	@RequestMapping("/viewFemaleBirthList/{gasId}")
	public String viewFemaleBirthList(@PathVariable int gasId, Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		List<Birth> birthList = bdao.getFemaleBirthListByGAS(g.getTownshipId());
		m.addAttribute("birthList",birthList);
		return "gas/GASViewFemaleBirthListPage";
	}
	
	@RequestMapping("/viewMaleBirthList/{gasId}")
	public String viewMaleBirthList(@PathVariable int gasId, Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		List<Birth> birthList = bdao.getMaleBirthListByGAS(g.getTownshipId());
		m.addAttribute("birthList",birthList);
		return "gas/GASViewMaleBirthListPage";
	}
	@RequestMapping("/viewOver10NonNRC/{gasId}")
	public String viewOver10NonNRC(@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<Resident> residentList = rdao. getResidentListByGAS(g.getTownshipId());
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
		 return "gas/GASViewOver10NonNRCPage";
	}
	
	@RequestMapping("/viewOver10MaleNonNRC/{gasId}")
	public String viewMaleOver10NonNRC(@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		List<Resident> maleResidentList = rdao. getMaleResidentListByGAS(g.getTownshipId());
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
		 return "gas/GASViewOver10MaleNonNRCPage";
	}
	
	@RequestMapping("/viewOver10FemaleNonNRC/{gasId}")
	public String viewFemaleOver10NonNRC(@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		List<Resident> femaleResidentList = rdao. getFemaleResidentListByGAS(g.getTownshipId());
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
		 return "gas/GASViewOver10FemaleNonNRCPage";
	}
	
	@RequestMapping("/viewBelow10NonNRC/{gasId}")
	public String viewBelow10NonNRC(@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<Resident> residentList = rdao. getResidentListByGAS(g.getTownshipId());
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
		 return "gas/GASViewBelow10NonNRCPage";
	}
	
	@RequestMapping("/viewBelow10MaleNonNRC/{gasId}")
	public String viewMaleBelow10NonNRC(@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		List<Resident> maleResidentList = rdao. getMaleResidentListByGAS(g.getTownshipId());
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
		 return "gas/GASViewBelow10MaleNonNRCPage";
	}
	
	@RequestMapping("/viewBelow10FemaleNonNRC/{gasId}")
	public String viewFemaleBelow10NonNRC(@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		List<Resident> femaleResidentList = rdao. getFemaleResidentListByGAS(g.getTownshipId());
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
		 return "gas/GASViewBelow10FemaleNonNRCPage";
	}
	@RequestMapping("/viewBCGDone/{gasId}")
	public String viewBCGDone(@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		List<Resident> residentList = rdao. getResidentListByGAS(g.getTownshipId());
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
		 return "gas/GASviewBCGDonePage";
	}
	
	@RequestMapping("/viewBCGNotYet/{gasId}")
	public String viewBCGNotYet(@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		List<Resident> residentList = rdao. getResidentListByGAS(g.getTownshipId());
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
		 return "gas/GASviewBCGNotYetPage";
	}
	
	@RequestMapping("/viewFiveVaccinesDone/{gasId}")
	public String viewFiveVaccinesDone(@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		List<Resident> residentList = rdao. getResidentListByGAS(g.getTownshipId());
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
		 return "gas/GASviewFiveVaccinesDonePage";
	}
	
	@RequestMapping("/viewFiveVaccinesNotYet/{gasId}")
	public String viewFiveVaccinesNotYet(@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		List<Resident> residentList = rdao. getResidentListByGAS(g.getTownshipId());
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
		 return "gas/GASviewFiveVaccinesNotYetPage";
	}

	@RequestMapping("/viewVitaminADone/{gasId}")
	public String viewVitaminADone(@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		List<Resident> residentList = rdao. getResidentListByGAS(g.getTownshipId());
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
		 return "gas/GASviewVitaminADonePage";
	}
	
	@RequestMapping("/viewVitaminANotYet/{gasId}")
	public String viewVitaminANotYet(@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<Resident> residentList = rdao. getResidentListByGAS(g.getTownshipId());
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
		 return "gas/GASviewVitaminANotYetPage";
	}
	
	@RequestMapping("/view9MonthsList/{gasId}") 
	 public String view9MonthsList(@PathVariable int gasId,Model m,HttpSession session){ 
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<Resident> residentList = rdao. getResidentListByGAS(g.getTownshipId());
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
		 return "gas/GASView9MonthsListPage"; 
	 }
	
	@RequestMapping("/viewHPVVaccineDone/{gasId}")
	public String viewHPVVaccineDone(@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<Resident> residentList = rdao. getFemaleResidentListByGAS(g.getTownshipId());
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
		 return "gas/GASviewHPVVaccineDonePage";
	}
	
	@RequestMapping("/viewHPVVaccineNotYet/{gasId}")
	public String viewHPVVaccineNotYet(@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<Resident> residentList = rdao. getFemaleResidentListByGAS(g.getTownshipId());
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
		 return "gas/GASviewHPVVaccineNotYetPage";
	}
	//*************************Village AS Management Start*********************************************//
	 @RequestMapping("/manageVillageASList/{gasId}") 
	 public String viewVillageASList(@PathVariable int gasId, Model m,HttpSession session){ 
		 if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<WardAS> villageASList = wdao.getVillageASListByGAS(g.getTownshipId());		
		 m.addAttribute("villageASList",villageASList); 
		 return "gas/GASManageVillageASListPage"; 
	 } 
	 
	 /*@RequestMapping(value="deleteVillageAS/{wardasId}/{gasId}",method = RequestMethod.GET)
		public String deleteVillageAS(@PathVariable int wardasId,@PathVariable int gasId, Model m,HttpSession session) {
		 if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 wdao.deleteWardAS(wardasId);
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			return "redirect:/gas/manageVillageASList/{gasId}";
		}*/
		
		@RequestMapping("/getAddVillageAS/{gasId}")
		public String getAddVillageAS(@PathVariable int gasId,Model m,HttpSession session) {
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			session.setAttribute("error", false);
			m.addAttribute("wardas",new WardAS());
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			 List<WardOrVillage> villageList = vdao.getVillageListByGAS(g.getTownshipId());
			 m.addAttribute("villageList",villageList); 
			return "gas/GASAddVillageASPage";
		}
		
		@RequestMapping(value="addVillageAS/{gasId}", method = RequestMethod.POST)
		public String addVillageAS(@ModelAttribute("wardas") @Valid WardAS w,BindingResult br,@PathVariable int gasId,Model m,HttpSession session) {
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			if(br.hasErrors()) { 
				m.addAttribute("wardas",new WardAS());
				 GAS g = gdao.getGAS(gasId); // To use gas in the next page
				 m.addAttribute("gas",g); // To use gas in the next page
				 List<WardOrVillage> villageList = vdao.getVillageListByGAS(g.getTownshipId());
				 m.addAttribute("villageList",villageList); 
				session.setAttribute("error", true);
				return "gas/GASAddVillageASPage"; 
			} 
			wdao.saveWardAS(w);
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			 return "redirect:/gas/manageVillageASList/{gasId}";
		}
		
		@RequestMapping("/getEditVillageAS/{wardasId}/{gasId}")
		public String getEditVillageAS(@PathVariable int wardasId,@PathVariable int gasId,Model m,HttpSession session) {
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			session.setAttribute("error", false);
			WardAS w = wdao.getWardAS(wardasId);
			m.addAttribute("command",w);
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			 List<WardOrVillage> villageList = vdao.getVillageListByGAS(g.getTownshipId());
			 m.addAttribute("villageList",villageList); 
			return "gas/GASEditVillageASPage";
		}
		
		@RequestMapping(value="editVillageAS/{gasId}",method = RequestMethod.POST) 
		 public String editVillageAS(@ModelAttribute("w") @Valid WardAS w, BindingResult br, @PathVariable int gasId,Model m,HttpSession session){ 
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			if(br.hasErrors()) { 
				WardAS w1 = wdao.getWardAS(w.getWardASId());
				m.addAttribute("command",w1);
				 GAS g = gdao.getGAS(gasId); // To use gas in the next page
				 m.addAttribute("gas",g); // To use gas in the next page
				 List<WardOrVillage> villageList = vdao.getVillageListByGAS(g.getTownshipId());
				 m.addAttribute("villageList",villageList); 
				session.setAttribute("error", true);
				return "gas/GASEditVillageASPage"; 
			} 
			wdao.updateWardAS(w); 
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			 return "redirect:/gas/manageVillageASList/{gasId}";
		 } 
		
	//*************************Village AS Management Finish*********************************************//
	
	//*************************Ward AS Management Start*********************************************//
	@RequestMapping("/manageWardASList/{gasId}") 
	 public String viewWardASList(@PathVariable int gasId, Model m,HttpSession session){ 
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<WardAS> wardASList = wdao.getWardASListByGAS(g.getTownshipId());		
		 m.addAttribute("wardASList",wardASList); 
		 return "gas/GASManageWardASListPage"; 
	 } 
	/*@RequestMapping(value="deleteWardAS/{wardasId}/{gasId}",method = RequestMethod.GET)
	public String deleteWardAS(@PathVariable int wardasId,@PathVariable int gasId, Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 wdao.deleteWardAS(wardasId);
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		return "redirect:/gas/manageWardASList/{gasId}";
	}*/
	
	@RequestMapping("/getAddWardAS/{gasId}")
	public String getAddWardAS(@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		m.addAttribute("wardas",new WardAS());
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<WardOrVillage> wardList = vdao.getWardListByGAS(g.getTownshipId());
		 m.addAttribute("wardList",wardList); 
		return "gas/GASAddWardASPage";
	}
	
	@RequestMapping(value="addWardAS/{gasId}", method = RequestMethod.POST)
	public String addWardAS(@ModelAttribute("wardas") @Valid WardAS w,BindingResult br,@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		if(br.hasErrors()) { 
			m.addAttribute("wardas",new WardAS());
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			 List<WardOrVillage> wardList = vdao.getWardListByGAS(g.getTownshipId());
			 m.addAttribute("wardList",wardList); 
			session.setAttribute("error", true);
			return "gas/GASAddWardASPage"; 
		} 
		wdao.saveWardAS(w);
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 return "redirect:/gas/manageWardASList/{gasId}";
	}
	
	@RequestMapping("/getEditWardAS/{wardasId}/{gasId}")
	public String getEditWardAS(@PathVariable int wardasId,@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		WardAS w = wdao.getWardAS(wardasId);
		m.addAttribute("command",w);
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 List<WardOrVillage> wardList = vdao.getWardListByGAS(g.getTownshipId());
		 m.addAttribute("wardList",wardList); 
		return "gas/GASEditWardASPage";
	}
	
	@RequestMapping(value="editWardAS/{gasId}",method = RequestMethod.POST) 
	 public String editWardAS(@ModelAttribute("w") @Valid WardAS w,BindingResult br,@PathVariable int gasId,Model m,HttpSession session){ 
		if(session.getAttribute("gas")==null) {
	 		return "redirect:/staffLogin";
	 	}
		if(br.hasErrors()) { 
			WardAS w1 = wdao.getWardAS(w.getWardASId());
			m.addAttribute("command",w1);
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			 List<WardOrVillage> wardList = vdao.getWardListByGAS(g.getTownshipId());
			 m.addAttribute("wardList",wardList); 
			session.setAttribute("error", true);
			return "gas/GASEditWardASPage"; 
		} 
		wdao.updateWardAS(w); 
		 GAS g = gdao.getGAS(gasId); // To use gas in the next page
		 m.addAttribute("gas",g); // To use gas in the next page
		 return "redirect:/gas/manageWardASList/{gasId}";
	 } 
	
	
	//*************************Ward AS Management Finish*********************************************//

		//**********************Resident Management Start**************************************//
		@RequestMapping("/manageResidentList/{gasId}") 
		 public String viewResidentList(@PathVariable int gasId,Model m,HttpSession session){ 
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			 List<Resident> residentList = rdao.getResidentListByGAS(g.getTownshipId());		
			 m.addAttribute("residentList",residentList); 
			 return "gas/GASManageResidentPage"; 
		 }
		
		/*@RequestMapping(value="deleteResident/{residentId}/{gasId}",method = RequestMethod.GET)
		public String deleteResident(@PathVariable int residentId,@PathVariable int gasId, Model m,HttpSession session) {
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			 rdao.deleteResident(residentId);
			return "redirect:/gas/manageResidentList/{gasId}";
		}*/
		
		@RequestMapping("/getAddResident/{gasId}")
		public String getAddResident(@PathVariable int gasId,Model m,HttpSession session) {
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			session.setAttribute("error", false);
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByGAS(g.getTownshipId());
			m.addAttribute("wardOrVillageList",wardOrVillageList);
			m.addAttribute("resident",new Resident());
			return "gas/GASAddResidentPage";
		}
		
		@RequestMapping(value="addResident/{gasId}", method = RequestMethod.POST)
		public String addResident(@PathVariable int gasId, @ModelAttribute("resident") @Valid Resident r,BindingResult br,Model m,HttpSession session) {
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			if(br.hasErrors()) { 
				GAS g = gdao.getGAS(gasId); // To use gas in the next page
				 m.addAttribute("gas",g); // To use gas in the next page
				List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByGAS(g.getTownshipId());
				m.addAttribute("wardOrVillageList",wardOrVillageList);
				m.addAttribute("resident",new Resident());
				session.setAttribute("error", true);
				return "gas/GASAddResidentPage"; 
			}
			
			if(rdao.checkNrcDuplication(r.getNrcNo())==1) {
				m.addAttribute("nrcDuplicateError",true);
				GAS g = gdao.getGAS(gasId); // To use gas in the next page
				 m.addAttribute("gas",g); // To use gas in the next page
				List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByGAS(g.getTownshipId());
				m.addAttribute("wardOrVillageList",wardOrVillageList);
				m.addAttribute("resident",new Resident());
				return "gas/GASAddResidentPage"; 
			}
			
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			 r.setDeceased(false);
			rdao.saveResident(r);
			return "redirect:/gas/manageResidentList/{gasId}";
		}
		
		@RequestMapping("/getEditResident/{residentId}/{gasId}")
		public String getEditResident(@PathVariable int gasId, @PathVariable int residentId,Model m,HttpSession session) {
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			session.setAttribute("error", false);
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByGAS(g.getTownshipId());
			m.addAttribute("wardOrVillageList",wardOrVillageList);
			Resident r = rdao.getResident(residentId);
			m.addAttribute("command",r);
			return "gas/GASEditResidentPage";
		}
		
		@RequestMapping(value="editResident/{gasId}",method = RequestMethod.POST) 
		 public String editResident(@PathVariable int gasId, @ModelAttribute("r") @Valid Resident r,BindingResult br,Model m,HttpSession session){ 
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			Resident resident = rdao.getResident(r.getResidentId());
			if(br.hasErrors()) { 
				 GAS g = gdao.getGAS(gasId); // To use gas in the next page
				 m.addAttribute("gas",g); // To use gas in the next page
				List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByGAS(g.getTownshipId());
				m.addAttribute("wardOrVillageList",wardOrVillageList);
				m.addAttribute("command",resident);
				session.setAttribute("error", true);
				return "gas/GASEditResidentPage"; 
			} 
			
			if(rdao.checkNrcDuplication(r.getNrcNo())==1 && (r.getNrcNo().equals(resident.getNrcNo())==false)) {
				m.addAttribute("nrcDuplicateError",true);
				 GAS g = gdao.getGAS(gasId); // To use gas in the next page
				 m.addAttribute("gas",g); // To use gas in the next page
				List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByGAS(g.getTownshipId());
				m.addAttribute("wardOrVillageList",wardOrVillageList);
				m.addAttribute("command",resident);
				return "gas/GASEditResidentPage";
			}
			
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			rdao.updateResident(r); 
			return "redirect:/gas/manageResidentList/{gasId}";
		 } 
		//**********************Resident Management Finish**************************************//
		
		//*******************************Birth List Management Start*************************************//
		@RequestMapping("/manageBirthList/{gasId}")
		public String viewBirthList(@PathVariable int gasId,Model m,HttpSession session) {
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			List<Birth> birthList = bdao.getBirthListByGAS(g.getTownshipId());
			m.addAttribute("birthList",birthList);
			return "gas/GASManageBirthPage";
		}
		
		/*@RequestMapping(value="deleteBirth/{birthId}/{gasId}",method = RequestMethod.GET)
		public String deleteBirth(@PathVariable int birthId,@PathVariable int gasId, Model m,HttpSession session) {
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			bdao.deleteBirth(birthId);
			return "redirect:/gas/manageBirthList/{gasId}";
		}*/
		
		@RequestMapping("/getAddBirth/{gasId}")
		public String getAddBirth(@PathVariable int gasId,Model m,HttpSession session) {
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByGAS(g.getTownshipId());
			m.addAttribute("wardOrVillageList",wardOrVillageList);
			m.addAttribute("birth",new Birth());
			return "gas/GASAddBirthPage";
		}
		
		@RequestMapping(value="addBirth/{gasId}", method = RequestMethod.POST)
		public String addBirth(@PathVariable int gasId, @ModelAttribute("birth") Birth b,Model m,HttpSession session) {
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
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
			return "redirect:/gas/manageBirthList/{gasId}";
		}
		
		@RequestMapping("/getEditBirth/{birthId}/{gasId}")
		public String getEditBirth(@PathVariable int gasId, @PathVariable int birthId,Model m,HttpSession session) {
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByGAS(g.getTownshipId());
			m.addAttribute("wardOrVillageList",wardOrVillageList);
			Birth b= bdao.getBirth(birthId);
			m.addAttribute("command",b);
			return "gas/GASEditBirthPage";
		}
		
		@RequestMapping(value="editBirth/{gasId}",method = RequestMethod.POST) 
		 public String editBirth(@PathVariable int gasId, @ModelAttribute("b") Birth b,Model m,HttpSession session){ 
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			bdao.updateBirth(b); 
			return "redirect:/gas/manageBirthList/{gasId}";
		 } 
		
		//*******************************Birth List Management Finish*************************************//
		//*******************************Death List Management Start*************************************//
		@RequestMapping("/manageDeathList/{gasId}")
		public String viewDeathList(@PathVariable int gasId, Model m,HttpSession session) {
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			List<Death> deathList = ddao.getDeathListByGAS(g.getTownshipId());
			m.addAttribute("deathList",deathList);
			return "gas/GASManageDeathPage";
		}
		
		/*@RequestMapping(value="deleteDeath/{deathId}/{gasId}",method = RequestMethod.GET)
		public String deleteDeath(@PathVariable int gasId, @PathVariable int deathId, Model m,HttpSession session) {
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			ddao.deleteDeath(deathId);
			return "redirect:/gas/manageDeathList/{gasId}";
		}*/
		
		@RequestMapping("/getAddDeath/{gasId}")
		public String getAddDeath(@PathVariable int gasId, Model m,HttpSession session) {
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			 List<Resident> residentList = rdao.getResidentListByGAS(g.getTownshipId());
			 m.addAttribute("residentList",residentList);
			m.addAttribute("death",new Death());
			return "gas/GASAddDeathPage";
		}
		
		@RequestMapping(value="addDeath/{gasId}", method = RequestMethod.POST)
		public String addDeath(@PathVariable int gasId, @ModelAttribute("death") Death d,Model m,HttpSession session) {
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			ddao.saveDeath(d);
			Resident r = rdao.getResident(d.getResidentId());
			r.setDeceased(true);
			rdao.updateResident(r);
			return "redirect:/gas/manageDeathList/{gasId}";
		}
		
		@RequestMapping("/getEditDeath/{deathId}/{gasId}")
		public String getEditDeath(@PathVariable int gasId, @PathVariable int deathId,Model m,HttpSession session) {
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			 List<Resident> residentList = rdao.getDeceasedAliveResidentListByGAS(g.getTownshipId());
				m.addAttribute("residentList",residentList);
				Death d= ddao.getDeath(deathId);
				int residentId = d.getResidentId();
				Resident r = rdao.getDeceasedAliveResident(residentId);
				r.setDeceased(false);
				rdao.updateResident(r);
			m.addAttribute("command",d);
			return "gas/GASEditDeathPage";
		}
		
		@RequestMapping(value="editDeath/{gasId}",method = RequestMethod.POST) 
		 public String editDeath(@PathVariable int gasId, @ModelAttribute("d") Death d,Model m,HttpSession session){ 
			if(session.getAttribute("gas")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 GAS g = gdao.getGAS(gasId); // To use gas in the next page
			 m.addAttribute("gas",g); // To use gas in the next page
			ddao.updateDeath(d); 
			Resident r = rdao.getResident(d.getResidentId());
			r.setDeceased(true);
			rdao.updateResident(r);
			return "redirect:/gas/manageDeathList/{gasId}";
		 } 
		
		//*******************************Death List Management Finish************************************//
}
