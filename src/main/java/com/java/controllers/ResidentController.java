package com.java.controllers;

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
import org.springframework.web.servlet.ModelAndView;

import com.java.beans.History;
import com.java.beans.PoliceOfficer;
import com.java.beans.Resident;
import com.java.beans.Township;
import com.java.beans.WardAS;
import com.java.dao.AdminDAO;
import com.java.dao.BirthDAO;
import com.java.dao.CrimeDAO;
import com.java.dao.DeathDAO;
import com.java.dao.GASDAO;
import com.java.dao.HistoryDAO;
import com.java.dao.PoliceOfficerDAO;
import com.java.dao.ResidentDAO;
import com.java.dao.SAdminDAO;
import com.java.dao.StateOrRegionDAO;
import com.java.dao.TownshipDAO;
import com.java.dao.WardASDAO;
import com.java.dao.WardOrVillageDAO;
import com.java.model.LetterModel;
import com.java.model.ProfileModel;
import com.java.model.ResidentProfileModel;


@Controller 
@RequestMapping("/resident")
public class ResidentController {
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
	@Autowired 
	CrimeDAO cdao;
	@Autowired 
	HistoryDAO hdao;

	@RequestMapping("/loginForm") 
	 public String loginForm(Model m){ 
		 m.addAttribute("resident", new Resident()); 
		 return "resident/ResidentLogin"; 
	 } 
	
	@RequestMapping("/home") 
	public String viewResidentHomePage(Model m,HttpSession session) {
		if(session.getAttribute("resident")==null) {
	 		return "redirect:/staffLogin";
	 	}
		else {
			Resident resident = (Resident)session.getAttribute("resident");
			m.addAttribute("resident",resident);//to use resident obj in next page)
			if(cdao.getCrimeByResidentId(resident.getResidentId())==null) {
				m.addAttribute("criminalRecord",false);
			}
			else m.addAttribute("criminalRecord",true);
		    return "resident/ResidentHomePage";
		}
	}
	
	@RequestMapping("/myProfile") 
	 public String myProfile(Model m,HttpSession session){ 
		 if(session.getAttribute("resident")==null) {
			 return "redirect:/resident/loginForm";
		 }
		 Resident resident = (Resident)session.getAttribute("resident");
		 if(cdao.getCrimeByResidentId(resident.getResidentId())!=null) {
		 ResidentProfileModel rp = new ResidentProfileModel(resident.getResidentName(),resident.getGender(),resident.getDob(),resident.getNrcNo(),resident.getFatherName(),resident.getMotherName(),resident.getEthnicity(),resident.getResidentEmail(),resident.getResidentPassword(),resident.getWardOrVillage().getWardOrVillageName(),
		 resident.getWardOrVillage().getTownship().getTownshipName(),resident.getWardOrVillage().getTownship().getStateOrRegion().getStateOrRegionName(),cdao.getCrimeByResidentId(resident.getResidentId()).getCrimeName(),true);
		 m.addAttribute("resident",rp);
		 }
		 else { 
			 ResidentProfileModel rp = new ResidentProfileModel(resident.getResidentName(),resident.getGender(),resident.getDob(),resident.getNrcNo(),resident.getFatherName(),resident.getMotherName(),resident.getEthnicity(),resident.getResidentEmail(),resident.getResidentPassword(),resident.getWardOrVillage().getWardOrVillageName(),
					 resident.getWardOrVillage().getTownship().getTownshipName(),resident.getWardOrVillage().getTownship().getStateOrRegion().getStateOrRegionName(),false);
			 m.addAttribute("resident",rp);
		 }
		 return "resident/ResidentProfilePage"; 
	 } 
	
	@RequestMapping("/logout") 
	 public String logout(Model m, HttpSession session){ 
		 session.invalidate();
		 return "redirect:/resident/loginForm"; 
	 } 
	
	//**************************************Edit Resident's Email and Password Start**********************************************//
	@RequestMapping("/getEditProfile")
	public String getEditProfile(Model m,HttpSession session) {
		if(session.getAttribute("resident")==null) {
	 		return "redirect:/resident/loginForm";
	 	}
		session.setAttribute("error", false);
		Resident resident = (Resident)session.getAttribute("resident");
		ProfileModel residentProfile = new ProfileModel(resident.getResidentId(),resident.getResidentEmail(),resident.getResidentPassword());
		m.addAttribute("command",residentProfile);
		return "resident/ResidentEditProfilePage";
	}
	
	@RequestMapping(value="editProfile",method = RequestMethod.POST) 
	 public String editProfile( @ModelAttribute("r") @Valid ProfileModel residentProfile,BindingResult br,Model m,HttpSession session){ 
		Resident resident = (Resident)session.getAttribute("resident");
		if(session.getAttribute("resident")==null) {
	 		return "redirect:/resident/loginForm";
	 	}
		
		if(br.hasErrors()) { 
			session.setAttribute("error",true);
			ProfileModel residentProfile1 = new ProfileModel(resident.getResidentId(),resident.getResidentEmail(),resident.getResidentPassword());
			m.addAttribute("command",residentProfile1);
			return "resident/ResidentEditProfilePage"; 
		} 
		else {session.setAttribute("error", false);}

		if(residentProfile.getPassword().equals(residentProfile.getNewPassword())) {
			resident.setResidentEmail(residentProfile.getEmail());
			resident.setResidentPassword(residentProfile.getPassword());
			rdao.updateResident(resident); 
			session.setAttribute("resident",resident);
			session.setAttribute("passwordNotMatch",false);
			 if(cdao.getCrimeByResidentId(resident.getResidentId())!=null) {
				 ResidentProfileModel rp = new ResidentProfileModel(resident.getResidentName(),resident.getGender(),resident.getDob(),resident.getNrcNo(),resident.getFatherName(),resident.getMotherName(),resident.getEthnicity(),resident.getResidentEmail(),resident.getResidentPassword(),resident.getWardOrVillage().getWardOrVillageName(),
				 resident.getWardOrVillage().getTownship().getTownshipName(),resident.getWardOrVillage().getTownship().getStateOrRegion().getStateOrRegionName(),cdao.getCrimeByResidentId(resident.getResidentId()).getCrimeName(),true);
				 m.addAttribute("resident",rp);
				 }
				 else { 
					 ResidentProfileModel rp = new ResidentProfileModel(resident.getResidentName(),resident.getGender(),resident.getDob(),resident.getNrcNo(),resident.getFatherName(),resident.getMotherName(),resident.getEthnicity(),resident.getResidentEmail(),resident.getResidentPassword(),resident.getWardOrVillage().getWardOrVillageName(),
							 resident.getWardOrVillage().getTownship().getTownshipName(),resident.getWardOrVillage().getTownship().getStateOrRegion().getStateOrRegionName(),false);
					 m.addAttribute("resident",rp);
				 }
			return "redirect:/resident/myProfile";
			
		}
		else {
			session.setAttribute("passwordNotMatch",true);
			return "redirect:/resident/getEditProfile";
		}
	 } 
	//**************************************Edit Resident's Email and Password Finish**********************************************//
	
	@RequestMapping("/validate") 
	 public String validateResident(@ModelAttribute("resident") Resident r, Model m,HttpSession session){ 
		String email= r.getResidentEmail();
		String password = r.getResidentPassword();
		int count = rdao.validateResident(email,password);
		
		if(count==1) {
			Resident resident = rdao.getResident(email, password); //to use resident obj in next page
			session.setAttribute("resident", resident);
			m.addAttribute("resident",resident);//to use resident obj in next page)
			if(cdao.getCrimeByResidentId(resident.getResidentId())==null || cdao.getCrimeByResidentId(resident.getResidentId()).getResultOfTheTrial()==3) {
				m.addAttribute("criminalRecord",false);
			}
			else m.addAttribute("criminalRecord",true);
			return "resident/ResidentHomePage"; 
		}
		else 
			return "NotValidateUser";
	 } 

	@RequestMapping(value = "/residencyLetter/{residentId}")
    public ModelAndView residencyLetter(@PathVariable int residentId,Model m,HttpSession session) {
		if(session.getAttribute("resident")!=null) {
			//Recording the history of resident viewing the letter
			History h = new History();
			h.setLetterType("Certification of Residence");
			long millis=System.currentTimeMillis(); 
			java.sql.Date date = new java.sql.Date(millis); 
			h.setResidentViewLetterDate(date);
			h.setResidentId(residentId);
			hdao.saveHistory(h);
			
			Resident rd = rdao.getResident(residentId);
			WardAS wardas = wdao.getWardASForLetter(rd.getWardOrVillageId());
			LetterModel rl = new LetterModel();
			rl.setResidentName(rd.getResidentName());
			rl.setGender(rd.getGender());
			rl.setDob(rd.getDob());
			rl.setNrcNo(rd.getNrcNo());
			rl.setFatherName(rd.getFatherName());
			rl.setWardOrVillageName(wardas.getWardOrVillage().getWardOrVillageName());
			rl.setTownshipName(wardas.getWardOrVillage().getTownship().getTownshipName());
			rl.setStateOrRegionName(wardas.getWardOrVillage().getTownship().getStateOrRegion().getStateOrRegionName());
			rl.setWardASName(wardas.getWardASName());
			m.addAttribute("residencyLetter",rl);
			return new ModelAndView("pdfView", "residencyLetter", rl);
		}
		else return null;
	}
	
	@RequestMapping(value = "/goodCharacterLetter/{residentId}")
    public ModelAndView goodCharacterLetter(@PathVariable int residentId,Model m,HttpSession session) {
		if(session.getAttribute("resident")!=null) {
			//Recording the history of resident viewing the letter
			History h = new History();
			h.setLetterType("Certification of Residence and Good Character");
			long millis=System.currentTimeMillis(); 
			java.sql.Date date = new java.sql.Date(millis); 
			h.setResidentViewLetterDate(date);
			h.setResidentId(residentId);
			hdao.saveHistory(h);
			
			Resident rd = rdao.getResident(residentId);
			WardAS wardas = wdao.getWardASForLetter(rd.getWardOrVillageId());
			LetterModel rl = new LetterModel();
			rl.setResidentName(rd.getResidentName());
			rl.setGender(rd.getGender());
			rl.setDob(rd.getDob());
			rl.setNrcNo(rd.getNrcNo());
			rl.setFatherName(rd.getFatherName());
			rl.setWardOrVillageName(wardas.getWardOrVillage().getWardOrVillageName());
			rl.setTownshipName(wardas.getWardOrVillage().getTownship().getTownshipName());
			rl.setStateOrRegionName(wardas.getWardOrVillage().getTownship().getStateOrRegion().getStateOrRegionName());
			rl.setWardASName(wardas.getWardASName());
			m.addAttribute("goodCharacterLetter",rl);
			return new ModelAndView("pdfView2", "goodCharacterLetter", rl);
		}
		else return null;
	}
	
	@RequestMapping(value = "/clearanceLetter/{residentId}")
    public ModelAndView clearanceLetter(@PathVariable int residentId,Model m,HttpSession session) {
		if(session.getAttribute("resident")!=null) {
			//Recording the history of resident viewing the letter
			History h = new History();
			h.setLetterType("Certification of Clearance from Criminal Offences");
			long millis=System.currentTimeMillis(); 
			java.sql.Date date = new java.sql.Date(millis); 
			h.setResidentViewLetterDate(date);
			h.setResidentId(residentId);
			hdao.saveHistory(h);
			
			Resident rd = rdao.getResident(residentId);
		    Township township= tdao.getTownshipByResident(residentId);
		    PoliceOfficer policeOfficer = pdao.getPoliceOfficerByTownshipId(township.getTownshipId());
		    LetterModel rl = new LetterModel();
			rl.setResidentName(rd.getResidentName());
			rl.setGender(rd.getGender());
			rl.setDob(rd.getDob());
			rl.setNrcNo(rd.getNrcNo());
			rl.setFatherName(rd.getFatherName());
			rl.setTownshipName(policeOfficer.getTownship().getTownshipName());
			rl.setWardOrVillageName(rd.getWardOrVillage().getWardOrVillageName());
			rl.setStateOrRegionName(policeOfficer.getTownship().getStateOrRegion().getStateOrRegionName());
			rl.setPoliceOfficerName(policeOfficer.getPoliceOfficerName());
			m.addAttribute("clearanceLetter",rl);
			return new ModelAndView("pdfView3", "clearanceLetter", rl);
		}
		else return null;
	}
}
