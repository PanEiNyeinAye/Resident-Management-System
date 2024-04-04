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

import com.java.beans.Admin;
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
@RequestMapping("/admin")
public class AdminController {
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
	public String viewAdminHomePage(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
			
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
		return "admin/AdminHomePage";
	}
 //**************************************Edit Admin's Email and Password Start**********************************************//
	@RequestMapping("/getEditProfile")
	public String getEditProfile(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		Admin admin = (Admin)session.getAttribute("admin");
		ProfileModel adminProfile = new ProfileModel(admin.getAdminId(),admin.getAdminEmail(),admin.getAdminPassword());
		m.addAttribute("command",adminProfile);
		return "admin/AdminEditProfilePage";
	}
	
	@RequestMapping(value="editProfile",method = RequestMethod.POST) 
	 public String editProfile( @ModelAttribute("r") @Valid ProfileModel adminProfile,BindingResult br,Model m,HttpSession session){ 
		Admin authAdmin = (Admin)session.getAttribute("admin");
		if(br.hasErrors()) { 
			session.setAttribute("error",true);
			Admin admin = (Admin)session.getAttribute("admin");
			ProfileModel adminProfile1 = new ProfileModel(admin.getAdminId(),admin.getAdminEmail(),admin.getAdminPassword());
			m.addAttribute("command",adminProfile1);
			return "admin/AdminEditProfilePage"; } 
		else {session.setAttribute("error", false);}
		
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		
		if(adminProfile.getPassword().equals(adminProfile.getNewPassword())) {
			authAdmin.setAdminEmail(adminProfile.getEmail());
			authAdmin.setAdminPassword(adminProfile.getNewPassword());
			adao.updateAdmin(authAdmin); 
			Admin newAdmin = adao.getAdmin(authAdmin.getAdminId());
			session.setAttribute("admin", newAdmin);
			session.setAttribute("passwordNotMatch",false);
			return "redirect:/admin/home";
			
		}
		else {
			session.setAttribute("passwordNotMatch",true);
			return "redirect:/admin/getEditProfile";
		}
	 } 
	//**************************************Edit Admin's Email and Password Finish**********************************************//
	
	@RequestMapping("/viewStateOrRegion") 
	 public String viewStateOrRegion(Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<StateOrRegion> stateOrRegionList = srdao.getStateOrRegionList();
		 m.addAttribute("stateOrRegionList",stateOrRegionList); 
		 return "admin/AdminViewStateOrRegionPage"; 
	 } 
	
	@RequestMapping("/viewTownship") 
	 public String viewTownship(Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<Township> townshipList = tdao.getTownshipListByAdmin();
		 m.addAttribute("townshipList",townshipList); 
		 return "admin/AdminViewTownshipPage"; 
	 } 
	
	 @RequestMapping("/viewWardOrVillage") 
	 public String viewWardOrVillage(Model m,HttpSession session){ 
		 if(session.getAttribute("admin")==null) {
		 		return "redirect:/staffLogin";
		 	}
		 List<WardOrVillage> wardOrVillageListByAdmin = vdao.getWardOrVillageListByAdmin();
		 m.addAttribute("wardOrVillageListByAdmin",wardOrVillageListByAdmin); 
		 return "admin/AdminViewWardOrVillagePage"; 
	 } 
	
	 @RequestMapping("/viewWard") 
	 public String viewWard(Model m,HttpSession session){ 
		 List<WardOrVillage> wardListByAdmin = vdao.getWardListByAdmin();
		 m.addAttribute("wardListByAdmin",wardListByAdmin); 
		 return "admin/AdminViewWardPage"; 
	 } 
	 
	 @RequestMapping("/viewVillage") 
	 public String viewVillage(Model m,HttpSession session){ 
		 if(session.getAttribute("admin")==null) {
		 		return "redirect:/staffLogin";
		 	}
		 List<WardOrVillage> villageListByAdmin = vdao.getVillageListByAdmin();
		 m.addAttribute("villageListByAdmin",villageListByAdmin); 
		 return "admin/AdminViewVillagePage"; 
	 }
	 
		@RequestMapping("/logout") 
		 public String logout(Model m, HttpSession session){ 
			 session.invalidate();
			 return "redirect:/staffLogin"; 
		 } 
	//********************************Resident Management Start*************************************//
	@RequestMapping("/manageResidentList") 
	 public String viewResidentList(Model m,HttpSession session){
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<Resident> residentListByAdmin = rdao.getResidentListByAdmin();		
		 m.addAttribute("residentListByAdmin",residentListByAdmin); 
		 return "admin/AdminManageResidentPage"; 
	 }
	/*@RequestMapping(value="deleteResident/{residentId}",method = RequestMethod.GET)
	public String deleteResident(@PathVariable int residentId, Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 rdao.deleteResident(residentId);
		return "redirect:/admin/manageResidentList";
	}*/
	
	@RequestMapping("/getAddResident")
	public String getAddResident(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByAdmin();
		m.addAttribute("wardOrVillageList",wardOrVillageList);
		m.addAttribute("resident",new Resident());
		return "admin/AdminAddResidentPage";
	}
	
	@RequestMapping(value="addResident", method = RequestMethod.POST)
	public String addResident(@ModelAttribute("resident") @Valid Resident r,BindingResult br,Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		if(br.hasErrors()) { 
			List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByAdmin();
			m.addAttribute("wardOrVillageList",wardOrVillageList);
			m.addAttribute("resident",new Resident());
			session.setAttribute("error", true);
			return "admin/AdminAddResidentPage"; 
		} 
		
		if(rdao.checkNrcDuplication(r.getNrcNo())==1) {
			m.addAttribute("nrcDuplicateError",true);
			List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByAdmin();
			m.addAttribute("wardOrVillageList",wardOrVillageList);
			m.addAttribute("resident",new Resident());
			return "admin/AdminAddResidentPage";
		}
		r.setDeceased(false);
		rdao.saveResident(r);
		return "redirect:/admin/manageResidentList";
	}
	
	@RequestMapping("/getEditResident/{residentId}")
	public String getEditResident(@PathVariable int residentId,Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByAdmin();
		m.addAttribute("wardOrVillageList",wardOrVillageList);
		Resident r = rdao.getResident(residentId);
		m.addAttribute("command",r);
		return "admin/AdminEditResidentPage";
	}
	
	@RequestMapping(value="editResident",method = RequestMethod.POST) 
	 public String editResident(@ModelAttribute("r") @Valid Resident r,BindingResult br,Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		Resident resident = rdao.getResident(r.getResidentId());
		if(br.hasErrors()) { 
			List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByAdmin();
			m.addAttribute("wardOrVillageList",wardOrVillageList);
			m.addAttribute("command",resident);
			session.setAttribute("error", true);
			return "admin/AdminEditResidentPage"; 
		} 
		if(rdao.checkNrcDuplication(r.getNrcNo())==1 && (r.getNrcNo().equals(resident.getNrcNo())==false)) {
			m.addAttribute("nrcDuplicateError",true);
			List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByAdmin();
			m.addAttribute("wardOrVillageList",wardOrVillageList);
			m.addAttribute("command",resident);
			return "admin/AdminEditResidentPage";
		}
		rdao.updateResident(r); 
		return "redirect:/admin/manageResidentList";
	 } 
	//********************************Resident Management Finish*************************************//
	@RequestMapping("/viewFemaleResidentList") 
	 public String viewFemaleResidentList(Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<Resident> femaleResidentListByAdmin = rdao.getFemaleResidentListByAdmin();		
		 m.addAttribute("femaleResidentListByAdmin",femaleResidentListByAdmin); 
		 return "admin/AdminViewFemaleResidentListPage"; 
	 }
	
	@RequestMapping("/viewMaleResidentList") 
	 public String viewMaleResidentList(Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<Resident> maleResidentListByAdmin = rdao.getMaleResidentListByAdmin();		
		 m.addAttribute("maleResidentListByAdmin",maleResidentListByAdmin); 
		 return "admin/AdminViewMaleResidentListPage"; 
	 }
	
	@RequestMapping("/viewOver18List") 
	 public String viewOver18List(Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<Resident> residentListByAdmin = rdao. getResidentListByAdmin();
		 List<Resident> over18ListByAdmin = new ArrayList<Resident>();
		 int size = residentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentListByAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=18) {
				 over18ListByAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over18ListByAdmin",over18ListByAdmin); 
		 return "admin/AdminViewOver18ListPage"; 
	 }
	
	@RequestMapping("/viewOver18MaleList") 
	 public String viewOver18MaleList(Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<Resident> maleResidentListByAdmin = rdao. getMaleResidentListByAdmin();
		 List<Resident> over18MaleListByAdmin = new ArrayList<Resident>();
		 int size = maleResidentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = maleResidentListByAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=18) {
				 over18MaleListByAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over18MaleListByAdmin",over18MaleListByAdmin); 
		 return "admin/AdminViewOver18MaleListPage"; 
	 }
	
	@RequestMapping("/viewOver18FemaleList") 
	 public String viewOver18FemaleList(Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<Resident> femaleResidentListByAdmin = rdao. getFemaleResidentListByAdmin();
		 List<Resident> over18FemaleListByAdmin = new ArrayList<Resident>();
		 int size = femaleResidentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = femaleResidentListByAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=18) {
				 over18FemaleListByAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over18FemaleListByAdmin",over18FemaleListByAdmin); 
		 return "admin/AdminViewOver18FemaleListPage"; 
	 }
	
	@RequestMapping("/viewOver85List") 
	 public String viewOver85List(Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<Resident> residentListByAdmin = rdao. getResidentListByAdmin();
		 List<Resident> over85ListByAdmin = new ArrayList<Resident>();
		 int size = residentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentListByAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=85) {
				 over85ListByAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over85ListByAdmin",over85ListByAdmin); 
		 return "admin/AdminViewOver85ListPage"; 
	 }
	
	@RequestMapping("/viewOver85MaleList") 
	 public String viewOver85MaleList(Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<Resident> maleResidentListByAdmin = rdao. getMaleResidentListByAdmin();
		 List<Resident> over85MaleListByAdmin = new ArrayList<Resident>();
		 int size = maleResidentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = maleResidentListByAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=85) {
				 over85MaleListByAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over85MaleListByAdmin",over85MaleListByAdmin); 
		 return "admin/AdminViewOver85MaleListPage"; 
	 }
	
	@RequestMapping("/viewOver85FemaleList") 
	 public String viewOver85FemaleList(Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<Resident> femaleResidentListByAdmin = rdao. getFemaleResidentListByAdmin();
		 List<Resident> over85FemaleListByAdmin = new ArrayList<Resident>();
		 int size = femaleResidentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = femaleResidentListByAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=85) {
				 over85FemaleListByAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("over85FemaleListByAdmin",over85FemaleListByAdmin); 
		 return "admin/AdminViewOver85FemaleListPage"; 
	 }
	
	@RequestMapping("/viewWardOrVillageASList") 
	 public String viewWardOrVillageASList(Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<WardAS> wardOrVillageASListByAdmin = wdao.getWardOrVillageASListByAdmin();		
		 m.addAttribute("wardOrVillageASListByAdmin",wardOrVillageASListByAdmin); 
		 return "admin/AdminViewWardOrVillageASListPage"; 
	 } 
	
	//******************Ward AS Management Start******************//
	@RequestMapping("/manageWardASList") 
	 public String viewWardASList(Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<WardAS> wardASListByAdmin = wdao.getWardASListByAdmin();		
		 m.addAttribute("wardASListByAdmin",wardASListByAdmin); 
		 return "admin/AdminManageWardASPage"; 
	 } 
	
	/*@RequestMapping(value="deleteWardAS/{wardasId}",method = RequestMethod.GET)
	public String deleteWardAS(@PathVariable int wardasId, Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 wdao.deleteWardAS(wardasId);
		return "redirect:/admin/manageWardASList";
	}*/
	
	@RequestMapping("/getAddWardAS")
	public String getAddWardAS(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		List<WardOrVillage> wardList = vdao.getWardListByAdmin();
		m.addAttribute("wardList",wardList);
		m.addAttribute("wardas",new WardAS());
		return "admin/AdminAddWardASPage";
	}
	
	@RequestMapping(value="addWardAS", method = RequestMethod.POST)
	public String addWardAS(@ModelAttribute("wardas") @Valid WardAS w, BindingResult br, Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		if(br.hasErrors()) { 
			List<WardOrVillage> wardList = vdao.getWardListByAdmin();
			m.addAttribute("wardList",wardList);
			m.addAttribute("wardas",new WardAS());
			session.setAttribute("error", true);
			return "admin/AdminAddWardASPage"; 
		} 
		wdao.saveWardAS(w);
		return "redirect:/admin/manageWardASList";
	}
	
	@RequestMapping("/getEditWardAS/{wardasId}")
	public String getEditWardAS(@PathVariable int wardasId,Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		List<WardOrVillage> wardList = vdao.getWardListByAdmin();
		m.addAttribute("wardList",wardList);
		WardAS w = wdao.getWardAS(wardasId);
		m.addAttribute("command",w);
		return "admin/AdminEditWardASPage";
	}
	
	@RequestMapping(value="editWardAS",method = RequestMethod.POST) 
	 public String editWardAS(@ModelAttribute("w") @Valid WardAS w,BindingResult br,Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		if(br.hasErrors()) { 
			List<WardOrVillage> wardList = vdao.getWardListByAdmin();
			m.addAttribute("wardList",wardList);
			WardAS w1 = wdao.getWardAS(w.getWardASId());
			m.addAttribute("command",w1);
			session.setAttribute("error", true);
			return "admin/AdminEditWardASPage"; 
		} 
		wdao.updateWardAS(w); 
		return "redirect:/admin/manageWardASList";
	 } 
	
	//******************Ward AS Management Finish******************//
	
	
	//******************Village AS Management Start******************//
	@RequestMapping("/manageVillageASList") 
	 public String viewVillageASList(Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<WardAS> villageASListByAdmin = wdao.getVillageASListByAdmin();		
		 m.addAttribute("villageASListByAdmin",villageASListByAdmin); 
		 return "admin/AdminManageVillageASPage"; 
	 } 
	
	/*@RequestMapping(value="deleteVillageAS/{wardasId}",method = RequestMethod.GET)
	public String deleteVillageAS(@PathVariable int wardasId, Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 wdao.deleteWardAS(wardasId);
		return "redirect:/admin/manageVillageASList";
	}*/
	
	@RequestMapping("/getAddVillageAS")
	public String getAddVillageAS(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		List<WardOrVillage> villageList = vdao.getVillageListByAdmin();
		m.addAttribute("villageList",villageList);
		m.addAttribute("wardas",new WardAS());
		return "admin/AdminAddVillageASPage";
	}
	
	@RequestMapping(value="addVillageAS", method = RequestMethod.POST)
	public String addVillageAS(@ModelAttribute("wardas") @Valid WardAS w,BindingResult br,Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		if(br.hasErrors()) { 
			List<WardOrVillage> villageList = vdao.getVillageListByAdmin();
			m.addAttribute("villageList",villageList);
			m.addAttribute("wardas",new WardAS());
			session.setAttribute("error", true);
			return "admin/AdminAddVillageASPage"; 
		} 
		wdao.saveWardAS(w);
		return "redirect:/admin/manageVillageASList";
	}
	
	@RequestMapping("/getEditVillageAS/{wardasId}")
	public String getEditVillageAS(@PathVariable int wardasId,Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		List<WardOrVillage> villageList = vdao.getVillageListByAdmin();
		m.addAttribute("villageList",villageList);
		WardAS w = wdao.getWardAS(wardasId);
		m.addAttribute("command",w);
		return "admin/AdminEditVillageASPage";
	}
	
	@RequestMapping(value="editVillageAS",method = RequestMethod.POST) 
	 public String editVillageAS(@ModelAttribute("w") @Valid WardAS w,BindingResult br,Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		if(br.hasErrors()) { 
			List<WardOrVillage> villageList = vdao.getVillageListByAdmin();
			m.addAttribute("villageList",villageList);
			WardAS w1 = wdao.getWardAS(w.getWardASId());
			m.addAttribute("command",w1);
			session.setAttribute("error", true);
			return "admin/AdminEditVillageASPage"; 
		} 
		wdao.updateWardAS(w); 
		return "redirect:/admin/manageVillageASList";
	 } 
	
	//******************Village AS Management Finish******************//
	//************************************GAS Management Start**********************************//
	@RequestMapping("/manageGASList") 
	 public String viewGASList(Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<GAS> gasListByAdmin = gdao.getGASListByAdmin();		
		 m.addAttribute("gasListByAdmin",gasListByAdmin); 
		 return "admin/AdminManageGASPage"; 
	 } 
	/*@RequestMapping(value="deleteGAS/{gasId}",method = RequestMethod.GET)
	public String deleteGAS(@PathVariable int gasId, Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 gdao.deleteGAS(gasId);
		return "redirect:/admin/manageGASList";
	}*/
	
	@RequestMapping("/getAddGAS")
	public String getAddGAS(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		List<Township> townshipList = tdao.getTownshipListByAdmin();
		m.addAttribute("townshipList",townshipList);
		m.addAttribute("gas",new GAS());
		return "admin/AdminAddGASPage";
	}
	
	@RequestMapping(value="addGAS", method = RequestMethod.POST)
	public String addGAS(@ModelAttribute("gas") @Valid GAS g,BindingResult br,Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		if(br.hasErrors()) { 
			List<Township> townshipList = tdao.getTownshipListByAdmin();
			m.addAttribute("townshipList",townshipList);
			m.addAttribute("gas",new GAS());
			session.setAttribute("error", true);
			return "admin/AdminAddGASPage"; 
		} 
		gdao.saveGAS(g);
		return "redirect:/admin/manageGASList";
	}
	
	@RequestMapping("/getEditGAS/{gasId}")
	public String getEditGAS(@PathVariable int gasId,Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		List<Township> townshipList = tdao.getTownshipListByAdmin();
		m.addAttribute("townshipList",townshipList);
		GAS g = gdao.getGAS(gasId);
		System.out.println(g.getGasPassword());
		m.addAttribute("command",g);
		return "admin/AdminEditGASPage";
	}
	
	@RequestMapping(value="editGAS",method = RequestMethod.POST) 
	 public String editGAS(@ModelAttribute("g") @Valid GAS g,Model m,BindingResult br,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		if(br.hasErrors()) { 
			List<Township> townshipList = tdao.getTownshipListByAdmin();
			m.addAttribute("townshipList",townshipList);
			GAS g1 = gdao.getGAS(g.getGasId());
			m.addAttribute("command",g1);
			session.setAttribute("error", true);
			return "admin/AdminEditGASPage"; 
		} 
		gdao.updateGAS(g); 
		return "redirect:/admin/manageGASList";
	 } 
	//************************************GAS Management Finish**********************************//
	//******************************Police Officer Management Start***************************//
	@RequestMapping("/managePoliceOfficerList") 
	 public String viewPoliceOfficerList(Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<PoliceOfficer> policeOfficerListByAdmin = pdao.getPoliceOfficerListByAdmin();		
		 m.addAttribute("policeOfficerListByAdmin",policeOfficerListByAdmin); 
		 return "admin/AdminManagePoliceOfficerPage"; 
	 } 
	/*@RequestMapping(value="deletePoliceOfficer/{policeOfficerId}",method = RequestMethod.GET)
	public String deletePoliceOfficer(@PathVariable int policeOfficerId, Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 pdao.deletePoliceOfficer(policeOfficerId);
		return "redirect:/admin/managePoliceOfficerList";
	}*/
	
	@RequestMapping("/getAddPoliceOfficer")
	public String getAddPoliceOfficer(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		List<Township> townshipList = tdao.getTownshipListByAdmin();
		m.addAttribute("townshipList",townshipList);
		m.addAttribute("policeOfficer",new PoliceOfficer());
		return "admin/AdminAddPoliceOfficerPage";
	}
	
	@RequestMapping(value="addPoliceOfficer", method = RequestMethod.POST)
	public String addPoliceOfficer(@ModelAttribute("policeOfficer") @Valid PoliceOfficer p,BindingResult br,Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		if(br.hasErrors()) { 
			List<Township> townshipList = tdao.getTownshipListByAdmin();
			m.addAttribute("townshipList",townshipList);
			m.addAttribute("policeOfficer",new PoliceOfficer());
			session.setAttribute("error", true);
			return "admin/AdminAddPoliceOfficerPage"; 
		} 
		pdao.savePoliceOfficer(p);
		return "redirect:/admin/managePoliceOfficerList";
	}
	
	@RequestMapping("/getEditPoliceOfficer/{policeOfficerId}")
	public String getEditPoliceOfficer(@PathVariable int policeOfficerId,Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		List<Township> townshipList = tdao.getTownshipListByAdmin();
		m.addAttribute("townshipList",townshipList);
		PoliceOfficer p= pdao.getPoliceOfficer(policeOfficerId);
		m.addAttribute("command",p);
		return "admin/AdminEditPoliceOfficerPage";
	}
	
	@RequestMapping(value="editPoliceOfficer",method = RequestMethod.POST) 
	 public String editPoliceOfficer(@ModelAttribute("p") @Valid PoliceOfficer p,BindingResult br,Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		if(br.hasErrors()) { 
			List<Township> townshipList = tdao.getTownshipListByAdmin();
			m.addAttribute("townshipList",townshipList);
			PoliceOfficer p1= pdao.getPoliceOfficer(p.getPoliceOfficerId());
			m.addAttribute("command",p1);
			session.setAttribute("error", true);
			return "admin/AdminEditPoliceOfficerPage"; 
		} 
		pdao.updatePoliceOfficer(p); 
		return "redirect:/admin/managePoliceOfficerList";
	 } 
	
	//********************************Police Officer Management Finish***********************//
	//*************************Mange Sub Admin Start*******************//
	@RequestMapping("/manageSAdminList") 
	 public String viewSAdminList(Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<SAdmin> sadminListByAdmin = sdao.getSAdminListByAdmin();		
		 m.addAttribute("sadminListByAdmin",sadminListByAdmin); 
		 return "admin/AdminManageSAdminPage"; 
	 } 
	
	/*@RequestMapping(value="deleteSAdmin/{sadminId}",method = RequestMethod.GET)
	public String deleteSAdmin(@PathVariable int sadminId, Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 sdao.deleteSAdmin(sadminId);
		return "redirect:/admin/manageSAdminList";
	}*/
	
	@RequestMapping("/getAddSAdmin")
	public String getAddSAdmin(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		List<StateOrRegion> stateOrRegionList = srdao.getStateOrRegionList();
		m.addAttribute("stateOrRegionList",stateOrRegionList);
		m.addAttribute("sadmin",new SAdmin());
		return "admin/AdminAddSAdminPage";
	}
	
	@RequestMapping(value="addSAdmin", method = RequestMethod.POST)
	public String addSAdmin(@ModelAttribute("sadmin") @Valid SAdmin s,BindingResult br,Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		if(br.hasErrors()) { 
			List<StateOrRegion> stateOrRegionList = srdao.getStateOrRegionList();
			m.addAttribute("stateOrRegionList",stateOrRegionList);
			m.addAttribute("sadmin",new SAdmin());
			session.setAttribute("error", true);
			return "admin/AdminAddSAdminPage"; 
		} 
		sdao.saveSAdmin(s);
		return "redirect:/admin/manageSAdminList";
	}
	
	@RequestMapping("/getEditSAdmin/{sadminId}")
	public String getEditSAdmin(@PathVariable int sadminId,Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		List<StateOrRegion> stateOrRegionList = srdao.getStateOrRegionList();
		m.addAttribute("stateOrRegionList",stateOrRegionList);
		SAdmin s = sdao.getSAdmin(sadminId);
		m.addAttribute("command",s);
		return "admin/AdminEditSAdminPage";
	}
	
	@RequestMapping(value="editSAdmin",method = RequestMethod.POST) 
	 public String editSAdmin(@ModelAttribute("s") @Valid SAdmin s,BindingResult br,Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		if(br.hasErrors()) { 
			List<StateOrRegion> stateOrRegionList = srdao.getStateOrRegionList();
			m.addAttribute("stateOrRegionList",stateOrRegionList);
			SAdmin s1 = sdao.getSAdmin(s.getSadminId());
			m.addAttribute("command",s1);
			session.setAttribute("error", true);
			return "admin/AdminEditSAdminPage"; 
		} 
		sdao.updateSAdmin(s); 
		return "redirect:/admin/manageSAdminList";
	 } 
	//************************Manage Sub Admin Finish********************//
	//*******************************Birth List Management Start*************************************//
	@RequestMapping("/manageBirthList")
	public String viewBirthList(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Birth> birthList = bdao.getBirthListByAdmin();
		m.addAttribute("birthList",birthList);
		return "admin/AdminManageBirthPage";
	}
	
	/*@RequestMapping(value="deleteBirth/{birthId}",method = RequestMethod.GET)
	public String deleteBirth(@PathVariable int birthId, Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		bdao.deleteBirth(birthId);
		return "redirect:/admin/manageBirthList";
	}*/
	
	@RequestMapping("/getAddBirth")
	public String getAddBirth(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByAdmin();
		m.addAttribute("wardOrVillageList",wardOrVillageList);
		m.addAttribute("birth",new Birth());
		return "admin/AdminAddBirthPage";
	}
	
	@RequestMapping(value="addBirth", method = RequestMethod.POST)
	public String addBirth(@ModelAttribute("birth") Birth b,Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
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
		return "redirect:/admin/manageBirthList";
	}
	
	@RequestMapping("/getEditBirth/{birthId}")
	public String getEditBirth(@PathVariable int birthId,Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByAdmin();
		m.addAttribute("wardOrVillageList",wardOrVillageList);
		Birth b= bdao.getBirth(birthId);
		m.addAttribute("command",b);
		return "admin/AdminEditBirthPage";
	}
	
	@RequestMapping(value="editBirth",method = RequestMethod.POST) 
	 public String editBirth(@ModelAttribute("b") Birth b,Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		bdao.updateBirth(b); 
		return "redirect:/admin/manageBirthList";
	 } 
	
	//*******************************Birth List Management Finish*************************************//
	@RequestMapping("/viewFemaleBirthList")
	public String viewFemaleBirthList(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Birth> birthList = bdao.getFemaleBirthListByAdmin();
		m.addAttribute("birthList",birthList);
		return "admin/AdminViewFemaleBirthListPage";
	}
	
	@RequestMapping("/viewMaleBirthList")
	public String viewMaleBirthList(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Birth> birthList = bdao.getMaleBirthListByAdmin();
		m.addAttribute("birthList",birthList);
		return "admin/AdminViewMaleBirthListPage";
	}
	
	//*******************************Death List Management Start*************************************//
	@RequestMapping("/manageDeathList")
	public String viewDeathList(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Death> deathList = ddao.getDeathListByAdmin();
		m.addAttribute("deathList",deathList);
		return "admin/AdminManageDeathPage";
	}
	
	/*@RequestMapping(value="deleteDeath/{deathId}",method = RequestMethod.GET)
	public String deleteDeath(@PathVariable int deathId, Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		ddao.deleteDeath(deathId);
		return "redirect:/admin/manageDeathList";
	}*/
	
	@RequestMapping("/getAddDeath")
	public String getAddDeath(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Resident> residentList = rdao.getResidentListByAdmin();
		m.addAttribute("residentList",residentList);
		m.addAttribute("death",new Death());
		return "admin/AdminAddDeathPage";
	}
	
	@RequestMapping(value="addDeath", method = RequestMethod.POST)
	public String addDeath(@ModelAttribute("death") Death d,Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		ddao.saveDeath(d);
		Resident r = rdao.getResident(d.getResidentId());
		r.setDeceased(true);
		rdao.updateResident(r);
		return "redirect:/admin/manageDeathList";
	}
	
	@RequestMapping("/getEditDeath/{deathId}")
	public String getEditDeath(@PathVariable int deathId,Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Resident> residentList = rdao.getDeceasedAliveResidentListByAdmin();
		m.addAttribute("residentList",residentList);
		Death d= ddao.getDeath(deathId);
		int residentId = d.getResidentId();
		Resident r = rdao.getDeceasedAliveResident(residentId);
		r.setDeceased(false);
		rdao.updateResident(r);
		m.addAttribute("command",d);
		return "admin/AdminEditDeathPage";
	}
	
	@RequestMapping(value="editDeath",method = RequestMethod.POST) 
	 public String editDeath(@ModelAttribute("d") Death d,Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		ddao.updateDeath(d); 
		Resident r = rdao.getResident(d.getResidentId());
		r.setDeceased(true);
		rdao.updateResident(r);
		return "redirect:/admin/manageDeathList";
	 } 
	
	//*******************************Death List Management Finish************************************//
	
	
	@RequestMapping("/viewOver10NonNRC")
	public String viewOver10NonNRC(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<Resident> residentListByAdmin = rdao. getResidentListByAdmin();
		 List<Resident> over10ListByAdmin = new ArrayList<Resident>();
		 int size = residentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentListByAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=10) {
				 over10ListByAdmin.add(r);
			 }
			 i++;
		 }
		 List<Resident> over10NonNrcListByAdmin = new ArrayList<Resident>();
		 int size1 = over10ListByAdmin.size();
		 int j =0;
		 while(j<size1) {
			 Resident r = over10ListByAdmin.get(j);
			 String nrc = r.getNrcNo();
			 if(nrc.equals(null) || nrc.equals("-")) {
				 over10NonNrcListByAdmin.add(r);
			 }
			 j++;
		 }
		 m.addAttribute("over10NonNrcListByAdmin",over10NonNrcListByAdmin);
		 return "admin/AdminViewOver10NonNRCPage";
	}
	
	@RequestMapping("/viewOver10MaleNonNRC")
	public String viewMaleOver10NonNRC(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Resident> maleResidentListByAdmin = rdao. getMaleResidentListByAdmin();
		 List<Resident> over10MaleListByAdmin = new ArrayList<Resident>();
		 int size = maleResidentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = maleResidentListByAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=10) {
				 over10MaleListByAdmin.add(r);
			 }
			 i++;
		 }
		 List<Resident> over10MaleNonNrcListByAdmin = new ArrayList<Resident>();
		 int size1 = over10MaleListByAdmin.size();
		 int j =0;
		 while(j<size1) {
			 Resident r = over10MaleListByAdmin.get(j);
			 String nrc = r.getNrcNo();
			 if(nrc.equals(null) || nrc.equals("-")) {
				 over10MaleNonNrcListByAdmin.add(r);
			 }
			 j++;
		 }
		 m.addAttribute("over10MaleNonNrcListByAdmin",over10MaleNonNrcListByAdmin);
		 return "admin/AdminViewOver10MaleNonNRCPage";
	}
	
	@RequestMapping("/viewOver10FemaleNonNRC")
	public String viewFemaleOver10NonNRC(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Resident> femaleResidentListByAdmin = rdao. getFemaleResidentListByAdmin();
		 List<Resident> over10FemaleListByAdmin = new ArrayList<Resident>();
		 int size = femaleResidentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = femaleResidentListByAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age>=10) {
				 over10FemaleListByAdmin.add(r);
			 }
			 i++;
		 }
		 List<Resident> over10FemaleNonNrcListByAdmin = new ArrayList<Resident>();
		 int size1 = over10FemaleListByAdmin.size();
		 int j =0;
		 while(j<size1) {
			 Resident r = over10FemaleListByAdmin.get(j);
			 String nrc = r.getNrcNo();
			 if(nrc.equals(null) || nrc.equals("-")) {
				 over10FemaleNonNrcListByAdmin.add(r);
			 }
			 j++;
		 }
		 m.addAttribute("over10FemaleNonNrcListByAdmin",over10FemaleNonNrcListByAdmin);
		 return "admin/AdminViewOver10FemaleNonNRCPage";
	}
	
	@RequestMapping("/viewBelow10NonNRC")
	public String viewBelow10NonNRC(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<Resident> residentListByAdmin = rdao. getResidentListByAdmin();
		 List<Resident> below10ListByAdmin = new ArrayList<Resident>();
		 int size = residentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentListByAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age<10) {
				 below10ListByAdmin.add(r);
			 }
			 i++;
		 }
		 List<Resident> below10NonNrcListByAdmin = new ArrayList<Resident>();
		 int size1 = below10ListByAdmin.size();
		 int j =0;
		 while(j<size1) {
			 Resident r = below10ListByAdmin.get(j);
			 String nrc = r.getNrcNo();
			 if(nrc.equals(null) || nrc.equals("-")) {
				 below10NonNrcListByAdmin.add(r);
			 }
			 j++;
		 }
		 m.addAttribute("below10NonNrcListByAdmin",below10NonNrcListByAdmin);
		 return "admin/AdminViewBelow10NonNRCPage";
	}
	
	@RequestMapping("/viewBelow10MaleNonNRC")
	public String viewMaleBelow10NonNRC(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Resident> maleResidentListByAdmin = rdao. getMaleResidentListByAdmin();
		 List<Resident> below10MaleListByAdmin = new ArrayList<Resident>();
		 int size = maleResidentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = maleResidentListByAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age<10) {
				 below10MaleListByAdmin.add(r);
			 }
			 i++;
		 }
		 List<Resident> below10MaleNonNrcListByAdmin = new ArrayList<Resident>();
		 int size1 = below10MaleListByAdmin.size();
		 int j =0;
		 while(j<size1) {
			 Resident r = below10MaleListByAdmin.get(j);
			 String nrc = r.getNrcNo();
			 if(nrc.equals(null) || nrc.equals("-")) {
				 below10MaleNonNrcListByAdmin.add(r);
			 }
			 j++;
		 }
		 m.addAttribute("below10MaleNonNrcListByAdmin",below10MaleNonNrcListByAdmin);
		 return "admin/AdminViewBelow10MaleNonNRCPage";
	}
	
	@RequestMapping("/viewBelow10FemaleNonNRC")
	public String viewFemaleBelow10NonNRC(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Resident> femaleResidentListByAdmin = rdao. getFemaleResidentListByAdmin();
		 List<Resident> below10FemaleListByAdmin = new ArrayList<Resident>();
		 int size = femaleResidentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = femaleResidentListByAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int age = r.calculateAge(dob1);
			 if(age<10) {
				 below10FemaleListByAdmin.add(r);
			 }
			 i++;
		 }
		 List<Resident> below10FemaleNonNrcListByAdmin = new ArrayList<Resident>();
		 int size1 = below10FemaleListByAdmin.size();
		 int j =0;
		 while(j<size1) {
			 Resident r = below10FemaleListByAdmin.get(j);
			 String nrc = r.getNrcNo();
			 if(nrc.equals(null) || nrc.equals("-")) {
				 below10FemaleNonNrcListByAdmin.add(r);
			 }
			 j++;
		 }
		 m.addAttribute("below10FemaleNonNrcListByAdmin",below10FemaleNonNrcListByAdmin);
		 return "admin/AdminViewBelow10FemaleNonNRCPage";
	}
	
	@RequestMapping("/viewBCGDone")
	public String viewBCGDone(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Resident> residentListByAdmin = rdao. getResidentListByAdmin();
		List<Resident> bcgDoneResidentListByAdmin = new ArrayList<Resident>();
		 int size = residentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentListByAdmin.get(i);
			 boolean bcg = r.getBcg();
			
			 if(bcg==true) {
				 bcgDoneResidentListByAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("bcgDoneResidentListByAdmin",bcgDoneResidentListByAdmin);
		 return "admin/AdminviewBCGDonePage";
	}
	
	@RequestMapping("/viewBCGNotYet")
	public String viewBCGNotYet(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Resident> residentListByAdmin = rdao. getResidentListByAdmin();
		List<Resident> bcgNotYetResidentListByAdmin = new ArrayList<Resident>();
		 int size = residentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentListByAdmin.get(i);
			 boolean bcg = r.getBcg();
			
			 if(bcg==false) {
				 bcgNotYetResidentListByAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("bcgNotYetResidentListByAdmin",bcgNotYetResidentListByAdmin);
		 return "admin/AdminviewBCGNotYetPage";
	}
	
	@RequestMapping("/viewFiveVaccinesDone")
	public String viewFiveVaccinesDone(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Resident> residentListByAdmin = rdao. getResidentListByAdmin();
		List<Resident> fiveVaccinesDoneResidentListByAdmin = new ArrayList<Resident>();
		 int size = residentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentListByAdmin.get(i);
			 boolean fiveVaccines1 = r.getFiveVaccines1();
			 boolean fiveVaccines2 = r.getFiveVaccines2();
			 boolean fiveVaccines3 = r.getFiveVaccines3();
			
			 if(fiveVaccines1==true && fiveVaccines2==true && fiveVaccines3==true ) {
				 fiveVaccinesDoneResidentListByAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("fiveVaccinesDoneResidentListByAdmin",fiveVaccinesDoneResidentListByAdmin);
		 return "admin/AdminviewFiveVaccinesDonePage";
	}
	
	@RequestMapping("/viewFiveVaccinesNotYet")
	public String viewFiveVaccinesNotYet(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Resident> residentListByAdmin = rdao. getResidentListByAdmin();
		List<Resident> fiveVaccinesNotYetResidentListByAdmin = new ArrayList<Resident>();
		 int size = residentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentListByAdmin.get(i);
			 boolean fiveVaccines1 = r.getFiveVaccines1();
			 boolean fiveVaccines2 = r.getFiveVaccines2();
			 boolean fiveVaccines3 = r.getFiveVaccines3();
			
			 if(fiveVaccines1==false || fiveVaccines2==false || fiveVaccines3==false ) {
				 fiveVaccinesNotYetResidentListByAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("fiveVaccinesNotYetResidentListByAdmin",fiveVaccinesNotYetResidentListByAdmin);
		 return "admin/AdminviewFiveVaccinesNotYetPage";
	}

	@RequestMapping("/viewVitaminADone")
	public String viewVitaminADone(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Resident> residentListByAdmin = rdao. getResidentListByAdmin();
		List<Resident> vitaminADoneResidentListByAdmin = new ArrayList<Resident>();
		 int size = residentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentListByAdmin.get(i);
			 boolean vitaminA = r.getVitaminA();
			 
			 if(vitaminA == true) {
				 vitaminADoneResidentListByAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("vitaminADoneResidentListByAdmin",vitaminADoneResidentListByAdmin);
		 return "admin/AdminviewVitaminADonePage";
	}
	
	@RequestMapping("/viewVitaminANotYet")
	public String viewVitaminANotYet(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Resident> residentListByAdmin = rdao. getResidentListByAdmin();
		List<Resident> vitaminANotYetResidentListByAdmin = new ArrayList<Resident>();
		 int size = residentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentListByAdmin.get(i);
			 boolean vitaminA = r.getVitaminA();
			 
			 if(vitaminA == false) {
				 vitaminANotYetResidentListByAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("vitaminANotYetResidentListByAdmin",vitaminANotYetResidentListByAdmin);
		 return "admin/AdminviewVitaminANotYetPage";
	}
	
	@RequestMapping("/view9MonthsList") 
	 public String view9MonthsList(Model m,HttpSession session){ 
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 List<Resident> residentListByAdmin = rdao. getResidentListByAdmin();
		 List<Resident> nineMonthsListByAdmin = new ArrayList<Resident>();
		 int size = residentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentListByAdmin.get(i);
			 Date dob = r.getDob();
			 LocalDate dob1 = dob.toLocalDate();
			 int ageInMonth = r.calculateAgeInMonth(dob1);
			 if(ageInMonth==9) {
				 nineMonthsListByAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("nineMonthsListByAdmin",nineMonthsListByAdmin); 
		 return "admin/AdminView9MonthsListPage"; 
	 }
	
	@RequestMapping("/viewHPVVaccineDone")
	public String viewHPVVaccineDone(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Resident> residentListByAdmin = rdao. getFemaleResidentListByAdmin();
		List<Resident> hpvVaccineDoneResidentListByAdmin = new ArrayList<Resident>();
		 int size = residentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentListByAdmin.get(i);
			 boolean hpv = r.getHpvVaccine();
			 
			 if(hpv == true) {
				 hpvVaccineDoneResidentListByAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("hpvVaccineDoneResidentListByAdmin",hpvVaccineDoneResidentListByAdmin);
		 return "admin/AdminviewHPVVaccineDonePage";
	}
	
	@RequestMapping("/viewHPVVaccineNotYet")
	public String viewHPVVaccineNotYet(Model m,HttpSession session) {
		if(session.getAttribute("admin")==null) {
	 		return "redirect:/staffLogin";
	 	}
		List<Resident> residentListByAdmin = rdao. getFemaleResidentListByAdmin();
		List<Resident> hpvVaccineNotYetResidentListByAdmin = new ArrayList<Resident>();
		 int size = residentListByAdmin.size();
		 int i =0;
		 while(i<size) {
			 Resident r = residentListByAdmin.get(i);
			 boolean hpv = r.getHpvVaccine();
			 
			 if(hpv == false) {
				 hpvVaccineNotYetResidentListByAdmin.add(r);
			 }
			 i++;
		 }
		 m.addAttribute("hpvVaccineNotYetResidentListByAdmin",hpvVaccineNotYetResidentListByAdmin);
		 return "admin/AdminviewHPVVaccineNotYetPage";
	}
}
