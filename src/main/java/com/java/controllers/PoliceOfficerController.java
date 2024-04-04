package com.java.controllers;

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

import com.java.beans.Crime;
import com.java.beans.PoliceOfficer;
import com.java.beans.Resident;
import com.java.model.SearchModel;
import com.java.beans.StateOrRegion;
import com.java.beans.Township;
import com.java.beans.WardOrVillage;
import com.java.dao.CrimeDAO;
import com.java.dao.PoliceOfficerDAO;
import com.java.dao.ResidentDAO;
import com.java.dao.StateOrRegionDAO;
import com.java.dao.TownshipDAO;
import com.java.dao.WardOrVillageDAO;
import com.java.model.ProfileModel;
import com.java.model.SearchedResidentModel;

@Controller 
@RequestMapping("/policeOfficer")
public class PoliceOfficerController {
	@Autowired 
	ResidentDAO dao;//will inject dao from XML file 
	@Autowired 
	CrimeDAO cdao;
	@Autowired 
	PoliceOfficerDAO pdao;
	@Autowired 
	StateOrRegionDAO srdao;
	@Autowired 
	TownshipDAO tdao;
	@Autowired 
	WardOrVillageDAO vdao;
	
	@RequestMapping("/home") 
	public String viewPoliceOfficerHomePage(Model m,HttpSession session) {
		if(session.getAttribute("policeOfficer")==null) {
	 		return "redirect:/staffLogin";
	 	}
		else {	
			PoliceOfficer policeOfficer = (PoliceOfficer) session.getAttribute("policeOfficer");
			 session.setAttribute("policeOfficer", policeOfficer);
			    m.addAttribute("policeOfficer",policeOfficer);
			    m.addAttribute("search",new SearchModel());
		    return "policeOfficer/PoliceOfficerHomePage";
		}
	}
	
	@RequestMapping("/logout") 
	 public String logout(Model m, HttpSession session){ 
		 session.invalidate();
		 return "redirect:/staffLogin"; 
	 } 
	//**************************************Edit Police Officer's Email and Password Start**********************************************//
		@RequestMapping("/getEditProfile")
		public String getEditProfile(Model m,HttpSession session) {
			if(session.getAttribute("policeOfficer")==null) {
		 		return "redirect:/staffLogin";
		 	}
			session.setAttribute("error", false);
			PoliceOfficer policeOfficer = (PoliceOfficer)session.getAttribute("policeOfficer");
			ProfileModel policeOfficerProfile = new ProfileModel(policeOfficer.getPoliceOfficerId(),policeOfficer.getPoliceOfficerEmail(),policeOfficer.getPoliceOfficerPassword());
			m.addAttribute("command",policeOfficerProfile);
			return "policeOfficer/PoliceOfficerEditProfilePage";
		}
		
		@RequestMapping(value="editProfile",method = RequestMethod.POST) 
		 public String editProfile( @ModelAttribute("r") @Valid ProfileModel policeOfficerProfile,BindingResult br,Model m,HttpSession session){ 
			PoliceOfficer policeOfficer = (PoliceOfficer)session.getAttribute("policeOfficer");
			if(session.getAttribute("policeOfficer")==null) {
		 		return "redirect:/staffLogin";
		 	}
			
			if(br.hasErrors()) { 
				session.setAttribute("error",true);
				ProfileModel policeOfficerProfile1 = new ProfileModel(policeOfficer.getPoliceOfficerId(),policeOfficer.getPoliceOfficerEmail(),policeOfficer.getPoliceOfficerPassword());
				m.addAttribute("command",policeOfficerProfile1);
				return "policeOfficer/PoliceOfficerEditProfilePage"; 
			} 
			else {session.setAttribute("error", false);}

			if(policeOfficerProfile.getPassword().equals(policeOfficerProfile.getNewPassword())) {
				policeOfficer.setPoliceOfficerEmail(policeOfficerProfile.getEmail());
				policeOfficer.setPoliceOfficerPassword(policeOfficerProfile.getPassword());
				pdao.updatePoliceOfficer(policeOfficer); 
				session.setAttribute("policeOfficer", policeOfficer);
				session.setAttribute("passwordNotMatch",false);
				m.addAttribute("search",new SearchModel());
				return "redirect:/policeOfficer/home";
				
			}
			else {
				session.setAttribute("passwordNotMatch",true);
				return "redirect:/policeOfficer/getEditProfile";
			}
		 } 
	//**************************************Edit Police Officer's Email and Password Finish**********************************************//
	@RequestMapping("/viewStateOrRegion/{policeOfficerId}") 
	 public String viewStateOrRegion(@PathVariable int policeOfficerId, Model m,HttpSession session){ 
		if(session.getAttribute("policeOfficer")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 PoliceOfficer p = pdao.getPoliceOfficer(policeOfficerId);// To use police officer in the next page
		 m.addAttribute("policeOfficer",p);// To use police officer in the next page
		 List<StateOrRegion> stateOrRegionList = srdao.getStateOrRegionList();
		 m.addAttribute("stateOrRegionList",stateOrRegionList); 
		 return "policeOfficer/PoliceOfficerViewStateOrRegionPage"; 
	 }
	 
	@RequestMapping("/viewTownship/{policeOfficerId}") 
	 public String viewTownship(@PathVariable int policeOfficerId, Model m,HttpSession session){ 
		if(session.getAttribute("policeOfficer")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 PoliceOfficer p = pdao.getPoliceOfficer(policeOfficerId);// To use police officer in the next page
		 m.addAttribute("policeOfficer",p);// To use police officer in the next page
		 List<Township> townshipList = tdao.getTownshipListByAdmin();
		 m.addAttribute("townshipList",townshipList); 
		 return "policeOfficer/PoliceOfficerViewTownshipPage"; 
	 } 
	
	@RequestMapping("/viewWardOrVillage/{policeOfficerId}") 
	 public String viewWardOrVillage(@PathVariable int policeOfficerId,Model m,HttpSession session){ 
		if(session.getAttribute("policeOfficer")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 PoliceOfficer p = pdao.getPoliceOfficer(policeOfficerId);// To use police officer in the next page
		 m.addAttribute("policeOfficer",p);// To use police officer in the next page
		 List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByAdmin();
		 m.addAttribute("wardOrVillageList",wardOrVillageList); 
		 return "policeOfficer/PoliceOfficerViewWardOrVillagePage"; 
	 } 
	
	 @RequestMapping("/viewWard/{policeOfficerId}") 
	 public String viewWard(@PathVariable int policeOfficerId,Model m,HttpSession session){ 
		 if(session.getAttribute("policeOfficer")==null) {
		 		return "redirect:/staffLogin";
		 	}
		 PoliceOfficer p = pdao.getPoliceOfficer(policeOfficerId);// To use police officer in the next page
		 m.addAttribute("policeOfficer",p);// To use police officer in the next page
		 List<WardOrVillage> wardList = vdao.getWardListByAdmin();
		 m.addAttribute("wardList",wardList); 
		 return "policeOfficer/PoliceOfficerViewWardPage"; 
	 } 
	 
	 @RequestMapping("/viewVillage/{policeOfficerId}") 
	 public String viewVillage(@PathVariable int policeOfficerId,Model m,HttpSession session){ 
		 if(session.getAttribute("policeOfficer")==null) {
		 		return "redirect:/staffLogin";
		 	}
		 PoliceOfficer p = pdao.getPoliceOfficer(policeOfficerId);// To use police officer in the next page
		 m.addAttribute("policeOfficer",p);// To use police officer in the next page
		 List<WardOrVillage> villageList = vdao.getVillageListByAdmin();
		 m.addAttribute("villageList",villageList); 
		 return "policeOfficer/PoliceOfficerViewVillagePage"; 
	 }
	 
	
	
	//**************************************Search Start******************************************************************//
	@RequestMapping(value="search/{policeOfficerId}")
	 public String searchResident(@PathVariable int policeOfficerId,@ModelAttribute("search") SearchModel s, Model m,HttpSession session){
		if(session.getAttribute("policeOfficer")==null) {
	 		return "redirect:/staffLogin";
	 	}
		PoliceOfficer p = pdao.getPoliceOfficer(policeOfficerId);// To use police officer in the next page
		 m.addAttribute("policeOfficer",p);// To use police officer in the next page
		 String searchType = s.getSearchType();
		 List<Resident> r;
		 List<SearchedResidentModel> sr = new ArrayList<>();
		 if(searchType.equals("nrc")) {
			  r = dao.getResidentByNrc(s.getNameOrNrcValue(),p.getTownshipId());
		 }
		 else { r = dao.getResidentByName(s.getNameOrNrcValue(),p.getTownshipId());}
		 if(r.isEmpty()){
			 m.addAttribute("noResident",true); 
			 return "redirect:/policeOfficer/home";
		 }
		 else {
			 for(Resident resident:r) {
				 if(cdao.getCrimeByResidentId(resident.getResidentId())!=null) {
						 sr.add(new SearchedResidentModel(resident.getResidentId(),resident.getResidentName(),resident.getGender(),resident.getDob(),resident.getNrcNo(),resident.getFatherName(),resident.getMotherName(),resident.getEthnicity(),resident.getWardOrVillage().getWardOrVillageName(),cdao.getCrimeByResidentId(resident.getResidentId()).getCrimeName(),true));
					 }
				 else {
						 sr.add(new SearchedResidentModel(resident.getResidentId(),resident.getResidentName(),resident.getGender(),resident.getDob(),resident.getNrcNo(),resident.getFatherName(),resident.getMotherName(),resident.getEthnicity(),resident.getWardOrVillage().getWardOrVillageName(),false));
				 }
			 }
			 session.setAttribute("searchedResidents", sr);
			 m.addAttribute("searchedResidents",sr);
			 return "policeOfficer/PoliceOfficerViewSearchedResidentPage"; 
		 }
	} 
	
	@RequestMapping(value="getSearchedResidentDetails/{policeOfficerId}/{residentId}")
	public String getSearchedResidentDetails(@PathVariable int policeOfficerId,@PathVariable int residentId,Model m,HttpSession session){
		if(session.getAttribute("policeOfficer")==null) {
	 		return "redirect:/staffLogin";
	 	}
		PoliceOfficer p = pdao.getPoliceOfficer(policeOfficerId);// To use police officer in the next page
		 m.addAttribute("policeOfficer",p);// To use police officer in the next page
		@SuppressWarnings("unchecked")
		List<SearchedResidentModel> searchedResidents = (List<SearchedResidentModel>) session.getAttribute("searchedResidents");
		for(SearchedResidentModel s:searchedResidents) {
			if(s.getResidentId()==residentId) {
				m.addAttribute("searchedResident",s);
			}
		}
		return "policeOfficer/PoliceOfficerViewSearchedResidentDetailsPage";
	}
	//**************************************Search Finish******************************************************************//
	//*********************************Criminal Records Management Start******************************************//
	@RequestMapping(value="manageCriminalRecordList/{policeOfficerId}")
	 public String viewCriminalRecordList(@PathVariable int policeOfficerId, Model m,HttpSession session){ 
		if(session.getAttribute("policeOfficer")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 PoliceOfficer p = pdao.getPoliceOfficer(policeOfficerId);// To use police officer in the next page
		 m.addAttribute("policeOfficer",p);// To use police officer in the next page
		 String position = p.getPoliceOfficerPosition();
		 if(position.equalsIgnoreCase("Township Officer")) {
			 List<Crime> crimeList = cdao.getCriminalRecordListByPoliceOfficer(p.getTownshipId());
			 m.addAttribute("crimeList",crimeList); 
			 return "policeOfficer/PoliceOfficerManageCriminalRecordPage"; 
		 }
		 else { return "redirect:/policeOfficer/home"; }
	} 
	/*@RequestMapping(value="deleteCriminalRecord/{crimeId}/{policeOfficerId}",method = RequestMethod.GET)
	public String deleteCriminalRecord(@PathVariable int policeOfficerId,@PathVariable int crimeId, Model m,HttpSession session) {
		if(session.getAttribute("policeOfficer")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 PoliceOfficer p = pdao.getPoliceOfficer(policeOfficerId);// To use police officer in the next page
		 m.addAttribute("policeOfficer",p);// To use police officer in the next page
		 cdao.deleteCrime(crimeId);
		return "redirect:/policeOfficer/manageCriminalRecordList/{policeOfficerId}";
	}*/
	
	@RequestMapping("/getAddCriminalRecord/{policeOfficerId}")
	public String getAddCriminalRecord(@PathVariable int policeOfficerId,Model m,HttpSession session) {
		if(session.getAttribute("policeOfficer")==null) {
	 		return "redirect:/staffLogin";
	 	}
		m.addAttribute("crime",new Crime());
		 PoliceOfficer p = pdao.getPoliceOfficer(policeOfficerId);// To use police officer in the next page
		 m.addAttribute("policeOfficer",p);// To use police officer in the next page
		 List<Resident> residentList = dao.getResidentListByGAS(p.getTownshipId());
		 m.addAttribute("residentList",residentList); 
		return "policeOfficer/PoliceOfficerAddCriminalRecordPage";
	}
	
	@RequestMapping(value="addCriminalRecord/{policeOfficerId}", method = RequestMethod.POST)
	public String addCriminalRecord(@ModelAttribute("crime") Crime c,@PathVariable int policeOfficerId,Model m,HttpSession session) {
		if(session.getAttribute("policeOfficer")==null) {
	 		return "redirect:/staffLogin";
	 	}
		cdao.saveCrime(c);
		 PoliceOfficer p = pdao.getPoliceOfficer(policeOfficerId);// To use police officer in the next page
		 m.addAttribute("policeOfficer",p);// To use police officer in the next page
		 return "redirect:/policeOfficer/manageCriminalRecordList/{policeOfficerId}";
	}
	
	@RequestMapping("/getEditCriminalRecord/{crimeId}/{policeOfficerId}")
	public String getEditCriminalRecord(@PathVariable int crimeId,@PathVariable int policeOfficerId,Model m,HttpSession session) {
		if(session.getAttribute("policeOfficer")==null) {
	 		return "redirect:/staffLogin";
	 	}
		Crime c = cdao.getCrime(crimeId);
		m.addAttribute("command",c);
		 PoliceOfficer p = pdao.getPoliceOfficer(policeOfficerId);// To use police officer in the next page
		 m.addAttribute("policeOfficer",p);// To use police officer in the next page
		 List<Resident> residentList = dao.getResidentListByGAS(p.getTownshipId());
		 m.addAttribute("residentList",residentList); 
		return "policeOfficer/PoliceOfficerEditCriminalRecordPage";
	}
	
	@RequestMapping(value="editCriminalRecord/{policeOfficerId}",method = RequestMethod.POST) 
	 public String editCriminalRecord(@ModelAttribute("c") Crime c,@PathVariable int policeOfficerId,Model m,HttpSession session){ 
		if(session.getAttribute("policeOfficer")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 cdao.updateCrime(c); 
		 PoliceOfficer p = pdao.getPoliceOfficer(policeOfficerId);// To use police officer in the next page
		 m.addAttribute("policeOfficer",p);// To use police officer in the next page
		 return "redirect:/policeOfficer/manageCriminalRecordList/{policeOfficerId}";
	 } 
	//*********************************Criminal Records Management Finish******************************************//
}
