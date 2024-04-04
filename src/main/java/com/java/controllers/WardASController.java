package com.java.controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.beans.Birth;
import com.java.beans.Death;
import com.java.beans.Receiver;
import com.java.beans.Resident;
import com.java.beans.Sender;
import com.java.beans.StateOrRegion;
import com.java.beans.Township;
import com.java.beans.WardAS;
import com.java.beans.WardOrVillage;

import com.java.dao.AdminDAO;
import com.java.dao.BirthDAO;
import com.java.dao.DeathDAO;
import com.java.dao.GASDAO;
import com.java.dao.PoliceOfficerDAO;
import com.java.dao.ReceiverDAO;
import com.java.dao.ResidentDAO;
import com.java.dao.SAdminDAO;
import com.java.dao.SenderDAO;
import com.java.dao.StateOrRegionDAO;
import com.java.dao.TownshipDAO;
import com.java.dao.WardASDAO;
import com.java.dao.WardOrVillageDAO;
import com.java.model.CommunicationModel;
import com.java.model.ProfileModel;


@Controller 
@RequestMapping("/wardas")
public class WardASController {
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
	SenderDAO sddao;
	@Autowired 
	ReceiverDAO rvdao;
	
	@RequestMapping("/home") 
	public String viewWardASHomePage(Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		else {	
			WardAS wardas= (WardAS) session.getAttribute("wardAS");
			m.addAttribute("wardAS",wardas); 
	    	int residentCountByWardAS = rdao.getResidentCountByWardAS(wardas.getWardOrVillageId());
	    	m.addAttribute("residentCountByWardAS",residentCountByWardAS);
	    	int femaleResidentCountByWardAS = rdao.getFemaleResidentCountByWardAS(wardas.getWardOrVillageId());
	    	m.addAttribute("femaleResidentCountByWardAS",femaleResidentCountByWardAS);
	    	int maleResidentCountByWardAS = rdao.getMaleResidentCountByWardAS(wardas.getWardOrVillageId());
	    	m.addAttribute("maleResidentCountByWardAS",maleResidentCountByWardAS);
		    return "wardas/WardASHomePage";
		}
	}
	
	@RequestMapping("/logout") 
	 public String logout(Model m, HttpSession session){ 
		 session.invalidate();
		 return "redirect:/staffLogin"; 
	 } 
	//**************************************Edit WardAS's Email and Password Start**********************************************//
	@RequestMapping("/getEditProfile")
	public String getEditProfile(Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("error", false);
		WardAS wardas = (WardAS)session.getAttribute("wardAS");
		ProfileModel wardASProfile = new ProfileModel(wardas.getWardASId(),wardas.getWardASEmail(),wardas.getWardASPassword());
		m.addAttribute("command",wardASProfile);
		return "wardas/WardASEditProfilePage";
	}
	
	@RequestMapping(value="editProfile",method = RequestMethod.POST) 
	 public String editProfile( @ModelAttribute("r") @Valid ProfileModel wardASProfile,BindingResult br,Model m,HttpSession session){ 
		WardAS wardas = (WardAS)session.getAttribute("wardAS");
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		
		if(br.hasErrors()) { 
			session.setAttribute("error",true);
			ProfileModel wardASProfile1 = new ProfileModel(wardas.getWardASId(),wardas.getWardASEmail(),wardas.getWardASPassword());
			m.addAttribute("command",wardASProfile1);
			return "wardas/WardASEditProfilePage"; 
		} 
		else {session.setAttribute("error", false);}

		if(wardASProfile.getPassword().equals(wardASProfile.getNewPassword())) {
			wardas.setWardASEmail(wardASProfile.getEmail());
			wardas.setWardASPassword(wardASProfile.getPassword());
			wdao.updateWardAS(wardas); 
			session.setAttribute("wardAS", wardas);
			session.setAttribute("passwordNotMatch",false);
			return "redirect:/wardas/home";
			
		}
		else {
			session.setAttribute("passwordNotMatch",true);
			return "redirect:/wardas/getEditProfile";
		}
	 } 
	//**************************************Edit WardAS's Email and Password Finish**********************************************//
	
	//********************Message Sent Function Start******************************************/
	
	@RequestMapping(value="/getSend/{wardASId}") 
	public String viewSend(Model m,HttpSession session,@PathVariable int wardASId){
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		session.setAttribute("nrcError", false);
		m.addAttribute("communication",new CommunicationModel());
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<WardOrVillage> wardOrVillages = vdao.getWardOrVillageListByAdmin();
		 List<Township> townships = tdao.getTownshipListByAdmin();
		 List<StateOrRegion> stateOrRegions = srdao.getStateOrRegionList();
		 m.addAttribute("wardOrVillages",wardOrVillages);
		 m.addAttribute("townships",townships);
		 m.addAttribute("stateOrRegions",stateOrRegions);
		 return "wardas/WardASSendPage";
	}
	
	@RequestMapping(value="/send/{wardASId}", method = RequestMethod.POST) 
	public String send(@ModelAttribute("communication") @Valid CommunicationModel c,BindingResult br,Model m,
			HttpSession session,@PathVariable int wardASId)throws DataAccessException, IOException{
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		if(br.hasErrors()) { 
			m.addAttribute("communication",new CommunicationModel());
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			 List<WardOrVillage> wardOrVillages = vdao.getWardOrVillageListByAdmin();
			 List<Township> townships = tdao.getTownshipListByAdmin();
			 List<StateOrRegion> stateOrRegions = srdao.getStateOrRegionList();
			 m.addAttribute("wardOrVillages",wardOrVillages);
			 m.addAttribute("townships",townships);
			 m.addAttribute("stateOrRegions",stateOrRegions);
			 session.setAttribute("nrcError", true);
			return "wardas/WardASSendPage"; 
		} 
			Sender s = new Sender();
			 long millis=System.currentTimeMillis(); 
				java.sql.Date date = new java.sql.Date(millis); 
				s.setSentDate(date);
				s.setResidentNrcNo(c.getResidentNrcNo());
				s.setWardASId(wardASId);
			   sddao.saveSender(s);

			Sender sender = sddao.getLatestSenderByWardAS(wardASId);
			Receiver r = new Receiver();
			r.setConfirm(3);
			r.setSenderId(sender.getSenderId());
			WardAS wardas = wdao.getWardAS(c.getWardOrVillageName(), c.getTownshipName(), c.getStateOrRegionName());
			r.setWardASId(wardas.getWardASId());
			rvdao.saveReciever(r);
		
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		return "redirect:/wardas/viewSentHistory/{wardASId}";
	}
	
	
	@RequestMapping(value="/receive/{wardASId}", method = RequestMethod.GET) 
	public String receive(Model m,HttpSession session,@PathVariable int wardASId){
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<Receiver> notConfirmedList= rvdao.getNotConfirmedList(wardASId);
		 m.addAttribute("notConfirmedList",notConfirmedList);
		 return "wardas/WardASReceivePage";
	}
	
	@RequestMapping(value="/receiveDetails/{wardASId}/{receiverId}", method = RequestMethod.GET) 
	public String receiveDetails(Model m,HttpSession session,@PathVariable int wardASId,@PathVariable int receiverId){
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 Receiver receiver = rvdao.getReceiver(receiverId);
		 m.addAttribute("receiver",receiver);
		 m.addAttribute("confirm",new Receiver());
		 return "wardas/WardASReceiveDetailsPage";
	}
	
	@RequestMapping(value="/confirm/{wardASId}/{receiverId}", method = RequestMethod.POST) 
	public String confirm(@ModelAttribute("confirm") Receiver r,Model m,HttpSession session,@PathVariable int wardASId,@PathVariable int receiverId){
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 rvdao.updateReceiver(r, receiverId);
		 if(r.getConfirm()==1) {
			 System.out.println("Inside confirm method: receiver id: "+receiverId);
			 Receiver receiver = rvdao.getReceiver(receiverId);
			 String nrc = receiver.getSender().getResidentNrcNo();
			 Resident resident = rdao.getResident(nrc);
			 
			 resident.setWardOrVillageId(receiver.getSender().getWardAS().getWardOrVillage().getWardOrVillageId());
			 System.out.println(resident.getWardOrVillageId());
			 rdao.updateResident(resident);
		 }
		 return "redirect:/wardas/receive/{wardASId}";
	}
	
	@RequestMapping(value="/viewSentHistory/{wardASId}") 
	public String viewSentHistory(Model m,HttpSession session,@PathVariable int wardASId){
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<Receiver> sentHistory = rvdao.getSentHistory(wardASId);
		 m.addAttribute("sentHistory",sentHistory);
		 return "wardas/WardASViewSentHistoryPage";
	}
	
	//********************Message Sent Function End******************************************/
	
	@RequestMapping("/viewStateOrRegion/{wardASId}") 
	 public String viewStateOrRegion(@PathVariable int wardASId, Model m,HttpSession session){ 
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<StateOrRegion> stateOrRegionList = srdao.getStateOrRegionList();
		 m.addAttribute("stateOrRegionList",stateOrRegionList); 
		 return "wardas/WardASViewStateOrRegionPage"; 
	 }
	 
	@RequestMapping("/viewTownship/{wardASId}") 
	 public String viewTownship(@PathVariable int wardASId, Model m,HttpSession session){ 
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<Township> townshipList = tdao.getTownshipListByAdmin();
		 m.addAttribute("townshipList",townshipList); 
		 return "wardas/WardASViewTownshipPage"; 
	 } 
	
	@RequestMapping("/viewWardOrVillage/{wardASId}") 
	 public String viewWardOrVillage(@PathVariable int wardASId,Model m,HttpSession session){ 
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<WardOrVillage> wardOrVillageList = vdao.getWardOrVillageListByAdmin();
		 m.addAttribute("wardOrVillageList",wardOrVillageList); 
		 return "wardas/WardASViewWardOrVillagePage"; 
	 } 
	
	 @RequestMapping("/viewWard/{wardASId}") 
	 public String viewWard(@PathVariable int wardASId,Model m,HttpSession session){ 
		 if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<WardOrVillage> wardList = vdao.getWardListByAdmin();
		 m.addAttribute("wardList",wardList); 
		 return "wardas/WardASViewWardPage"; 
	 } 
	 
	 @RequestMapping("/viewVillage/{wardASId}") 
	 public String viewVillage(@PathVariable int wardASId,Model m,HttpSession session){ 
		 if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<WardOrVillage> villageList = vdao.getVillageListByAdmin();
		 m.addAttribute("villageList",villageList); 
		 return "wardas/WardASViewVillagePage"; 
	 }
	 
	 @RequestMapping("/viewFemaleResidentList/{wardASId}") 
	 public String viewFemaleResidentList(@PathVariable int wardASId,Model m,HttpSession session){ 
		 if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<Resident> femaleResidentList = rdao.getFemaleResidentListByWardAS(w.getWardOrVillageId());		
		 m.addAttribute("femaleResidentList",femaleResidentList); 
		 return "wardas/WardASViewFemaleResidentListPage"; 
	 }
	
	@RequestMapping("/viewMaleResidentList/{wardASId}") 
	 public String viewMaleResidentList(@PathVariable int wardASId,Model m,HttpSession session){ 
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<Resident> maleResidentList = rdao.getMaleResidentListByWardAS(w.getWardOrVillageId());		
		 m.addAttribute("maleResidentList",maleResidentList); 
		 return "wardas/WardASViewMaleResidentListPage"; 
	 }
	
	
	
	@RequestMapping("/viewOver18List/{wardASId}") 
	 public String viewOver18List(@PathVariable int wardASId,Model m,HttpSession session){ 
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<Resident> residentList = rdao.getResidentListByWardAS(w.getWardOrVillageId());	
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
		 return "wardas/WardASViewOver18ListPage"; 
	 }
	
	@RequestMapping("/viewOver18MaleList/{wardASId}") 
	 public String viewOver18MaleList(@PathVariable int wardASId,Model m,HttpSession session){ 
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<Resident> maleResidentList = rdao.getMaleResidentListByWardAS(w.getWardOrVillageId());
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
		 return "wardas/WardASViewOver18MaleListPage"; 
	 }
	
	@RequestMapping("/viewOver18FemaleList/{wardASId}") 
	 public String viewOver18FemaleList(@PathVariable int wardASId,Model m,HttpSession session){
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		List<Resident> femaleResidentList = rdao.getFemaleResidentListByWardAS(w.getWardOrVillageId());		
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
		 return "wardas/WardASViewOver18FemaleListPage"; 
	 }
	
	@RequestMapping("/viewOver85List/{wardASId}") 
	 public String viewOver85List(@PathVariable int wardASId,Model m,HttpSession session){ 
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<Resident> residentList = rdao.getResidentListByWardAS(w.getWardOrVillageId());	
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
		 return "wardas/WardASViewOver85ListPage"; 
	 }
	
	@RequestMapping("/viewOver85MaleList/{wardASId}") 
	 public String viewOver85MaleList(@PathVariable int wardASId,Model m,HttpSession session){ 
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<Resident> maleResidentList = rdao.getMaleResidentListByWardAS(w.getWardOrVillageId());
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
		 return "wardas/WardASViewOver85MaleListPage"; 
	 }
	
	@RequestMapping("/viewOver85FemaleList/{wardASId}") 
	 public String viewOver85FemaleList(@PathVariable int wardASId,Model m,HttpSession session){ 
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		List<Resident> femaleResidentList = rdao.getFemaleResidentListByWardAS(w.getWardOrVillageId());		
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
		 return "wardas/WardASViewOver85FemaleListPage"; 
	 }
	@RequestMapping("/viewFemaleBirthList/{wardASId}")
	public String viewFemaleBirthList(@PathVariable int wardASId, Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		List<Birth> birthList = bdao.getFemaleBirthListByWardAS(w.getWardOrVillageId());
		m.addAttribute("birthList",birthList);
		return "wardas/WardASViewFemaleBirthListPage";
	}
	
	@RequestMapping("/viewMaleBirthList/{wardASId}")
	public String viewMaleBirthList(@PathVariable int wardASId, Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		List<Birth> birthList = bdao.getMaleBirthListByWardAS(w.getWardOrVillageId());
		m.addAttribute("birthList",birthList);
		return "wardas/WardASViewMaleBirthListPage";
	}
	@RequestMapping("/viewOver10NonNRC/{wardASId}")
	public String viewOver10NonNRC(@PathVariable int wardASId,Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<Resident> residentList = rdao. getResidentListByWardAS(w.getWardOrVillageId());
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
		 return "wardas/WardASViewOver10NonNRCPage";
	}
	
	@RequestMapping("/viewOver10MaleNonNRC/{wardASId}")
	public String viewMaleOver10NonNRC(@PathVariable int wardASId,Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		List<Resident> maleResidentList = rdao. getMaleResidentListByWardAS(w.getWardOrVillageId());
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
		 return "wardas/WardASViewOver10MaleNonNRCPage";
	}
	
	@RequestMapping("/viewOver10FemaleNonNRC/{wardASId}")
	public String viewFemaleOver10NonNRC(@PathVariable int wardASId,Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		List<Resident> femaleResidentList = rdao. getFemaleResidentListByWardAS(w.getWardOrVillageId());
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
		 return "wardas/WardASViewOver10FemaleNonNRCPage";
	}
	
	@RequestMapping("/viewBelow10NonNRC/{wardASId}")
	public String viewBelow10NonNRC(@PathVariable int wardASId,Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<Resident> residentList = rdao. getResidentListByWardAS(w.getWardOrVillageId());
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
		 return "wardas/WardASViewBelow10NonNRCPage";
	}
	
	@RequestMapping("/viewBelow10MaleNonNRC/{wardASId}")
	public String viewMaleBelow10NonNRC(@PathVariable int wardASId,Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		List<Resident> maleResidentList = rdao. getMaleResidentListByWardAS(w.getWardOrVillageId());
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
		 return "wardas/WardASViewBelow10MaleNonNRCPage";
	}
	
	@RequestMapping("/viewBelow10FemaleNonNRC/{wardASId}")
	public String viewFemaleBelow10NonNRC(@PathVariable int wardASId,Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		List<Resident> femaleResidentList = rdao. getFemaleResidentListByWardAS(w.getWardOrVillageId());
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
		 return "wardas/WardASViewBelow10FemaleNonNRCPage";
	}
	@RequestMapping("/viewBCGDone/{wardASId}")
	public String viewBCGDone(@PathVariable int wardASId,Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		List<Resident> residentList = rdao. getResidentListByWardAS(w.getWardOrVillageId());
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
		 return "wardas/WardASviewBCGDonePage";
	}
	
	@RequestMapping("/viewBCGNotYet/{wardASId}")
	public String viewBCGNotYet(@PathVariable int wardASId,Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		List<Resident> residentList = rdao. getResidentListByWardAS(w.getWardOrVillageId());
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
		 return "wardas/WardASviewBCGNotYetPage";
	}
	
	@RequestMapping("/viewFiveVaccinesDone/{wardASId}")
	public String viewFiveVaccinesDone(@PathVariable int wardASId,Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		List<Resident> residentList = rdao. getResidentListByWardAS(w.getWardOrVillageId());
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
		 return "wardas/WardASviewFiveVaccinesDonePage";
	}
	
	@RequestMapping("/viewFiveVaccinesNotYet/{wardASId}")
	public String viewFiveVaccinesNotYet(@PathVariable int wardASId,Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		List<Resident> residentList = rdao. getResidentListByWardAS(w.getWardOrVillageId());
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
		 return "wardas/WardASviewFiveVaccinesNotYetPage";
	}

	@RequestMapping("/viewVitaminADone/{wardASId}")
	public String viewVitaminADone(@PathVariable int wardASId,Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		List<Resident> residentList = rdao. getResidentListByWardAS(w.getWardOrVillageId());
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
		 return "wardas/WardASviewVitaminADonePage";
	}
	
	@RequestMapping("/viewVitaminANotYet/{wardASId}")
	public String viewVitaminANotYet(@PathVariable int wardASId,Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<Resident> residentList = rdao. getResidentListByWardAS(w.getWardOrVillageId());
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
		 return "wardas/WardASviewVitaminANotYetPage";
	}
	
	@RequestMapping("/view9MonthsList/{wardASId}") 
	 public String view9MonthsList(@PathVariable int wardASId,Model m,HttpSession session){ 
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<Resident> residentList = rdao. getResidentListByWardAS(w.getWardOrVillageId());
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
		 return "wardas/WardASView9MonthsListPage"; 
	 }
	
	@RequestMapping("/viewHPVVaccineDone/{wardASId}")
	public String viewHPVVaccineDone(@PathVariable int wardASId,Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<Resident> residentList = rdao. getFemaleResidentListByWardAS(w.getWardOrVillageId());
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
		 return "wardas/WardASviewHPVVaccineDonePage";
	}
	
	@RequestMapping("/viewHPVVaccineNotYet/{wardASId}")
	public String viewHPVVaccineNotYet(@PathVariable int wardASId,Model m,HttpSession session) {
		if(session.getAttribute("wardAS")==null) {
	 		return "redirect:/staffLogin";
	 	}
		 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
		 m.addAttribute("wardAS",w); // To use wardAS in the next page
		 List<Resident> residentList = rdao. getFemaleResidentListByWardAS(w.getWardOrVillageId());
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
		 return "wardas/WardASviewHPVVaccineNotYetPage";
	}
	//**********************Resident Management Start**************************************//
		@RequestMapping("/manageResidentList/{wardASId}") 
		 public String viewResidentList(@PathVariable int wardASId,Model m,HttpSession session){ 
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			 List<Resident> residentList = rdao.getResidentListByWardAS(w.getWardOrVillageId());		
			 m.addAttribute("residentList",residentList); 
			 return "wardas/WardASManageResidentPage"; 
		 }
		
		/*@RequestMapping(value="deleteResident/{residentId}/{wardASId}",method = RequestMethod.GET)
		public String deleteResident(@PathVariable int residentId,@PathVariable int wardASId, Model m,HttpSession session) {
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			 rdao.deleteResident(residentId);
			return "redirect:/wardas/manageResidentList/{wardASId}";
		}*/
		
		@RequestMapping("/getAddResident/{wardASId}")
		public String getAddResident(@PathVariable int wardASId,Model m,HttpSession session) {
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			session.setAttribute("error", false);
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			m.addAttribute("resident",new Resident());
			return "wardas/WardASAddResidentPage";
		}
		
		@RequestMapping(value="addResident/{wardASId}", method = RequestMethod.POST)
		public String addResident(@PathVariable int wardASId, @ModelAttribute("resident") @Valid Resident r,BindingResult br,Model m,HttpSession session) {
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			if(br.hasErrors()) { 
				 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
				 m.addAttribute("wardAS",w); // To use wardAS in the next page
				m.addAttribute("resident",new Resident());
				session.setAttribute("error", true);
				return "wardas/WardASAddResidentPage"; 
			} 
			
			if(rdao.checkNrcDuplication(r.getNrcNo())==1) {
				m.addAttribute("nrcDuplicateError",true);
				 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
				 m.addAttribute("wardAS",w); // To use wardAS in the next page
				m.addAttribute("resident",new Resident());
				return "wardas/WardASAddResidentPage";
			}
			
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			 r.setDeceased(false);
			rdao.saveResident(r);
			return "redirect:/wardas/manageResidentList/{wardASId}";
		}
		
		@RequestMapping("/getEditResident/{residentId}/{wardASId}")
		public String getEditResident(@PathVariable int wardASId, @PathVariable int residentId,Model m,HttpSession session) {
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			session.setAttribute("nrcError", false);
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			Resident r = rdao.getResident(residentId);
			m.addAttribute("command",r);
			return "wardas/WardASEditResidentPage";
		}
		
		@RequestMapping(value="editResident/{wardASId}",method = RequestMethod.POST) 
		 public String editResident(@PathVariable int wardASId, @ModelAttribute("r") @Valid Resident r,BindingResult br,Model m,HttpSession session){ 
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			Resident resident = rdao.getResident(r.getResidentId());
			if(br.hasErrors()) { 
				 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
				 m.addAttribute("wardAS",w); // To use wardAS in the next page
				m.addAttribute("command",resident);
				session.setAttribute("nrcError", true);
				return "wardas/WardASEditResidentPage"; 
			} 
			if(rdao.checkNrcDuplication(r.getNrcNo())==1 && (r.getNrcNo().equals(resident.getNrcNo())==false)) {
				m.addAttribute("nrcDuplicateError",true);
				 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
				 m.addAttribute("wardAS",w); // To use wardAS in the next page
				m.addAttribute("command",resident);
				return "wardas/WardASEditResidentPage";
			}
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			rdao.updateResident(r); 
			return "redirect:/wardas/manageResidentList/{wardASId}";
		 } 
		//**********************Resident Management Finish**************************************//
		
		//*******************************Birth List Management Start*************************************//
		@RequestMapping("/manageBirthList/{wardASId}")
		public String viewBirthList(@PathVariable int wardASId,Model m,HttpSession session) {
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			List<Birth> birthList = bdao.getBirthListByWardAS(w.getWardOrVillageId());
			m.addAttribute("birthList",birthList);
			return "wardas/WardASManageBirthPage";
		}
		
		/*@RequestMapping(value="deleteBirth/{birthId}/{wardASId}",method = RequestMethod.GET)
		public String deleteBirth(@PathVariable int birthId,@PathVariable int wardASId, Model m,HttpSession session) {
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			bdao.deleteBirth(birthId);
			return "redirect:/wardas/manageBirthList/{wardASId}";
		}*/
		
		@RequestMapping("/getAddBirth/{wardASId}")
		public String getAddBirth(@PathVariable int wardASId,Model m,HttpSession session) {
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			m.addAttribute("birth",new Birth());
			return "wardas/WardASAddBirthPage";
		}
		
		@RequestMapping(value="addBirth/{wardASId}", method = RequestMethod.POST)
		public String addBirth(@PathVariable int wardASId, @ModelAttribute("birth") Birth b,Model m,HttpSession session) {
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
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
			return "redirect:/wardas/manageBirthList/{wardASId}";
		}
		
		@RequestMapping("/getEditBirth/{birthId}/{wardASId}")
		public String getEditBirth(@PathVariable int wardASId, @PathVariable int birthId,Model m,HttpSession session) {
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			Birth b= bdao.getBirth(birthId);
			m.addAttribute("command",b);
			return "wardas/WardASEditBirthPage";
		}
		
		@RequestMapping(value="editBirth/{wardASId}",method = RequestMethod.POST) 
		 public String editBirth(@PathVariable int wardASId, @ModelAttribute("b") Birth b,Model m,HttpSession session){ 
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			bdao.updateBirth(b); 
			return "redirect:/wardas/manageBirthList/{wardASId}";
		 } 
		
		//*******************************Birth List Management Finish*************************************//
		//*******************************Death List Management Start*************************************//
		@RequestMapping("/manageDeathList/{wardASId}")
		public String viewDeathList(@PathVariable int wardASId, Model m,HttpSession session) {
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			List<Death> deathList = ddao.getDeathListByWardAS(w.getWardOrVillageId());
			m.addAttribute("deathList",deathList);
			return "wardas/WardASManageDeathPage";
		}
		
		/*@RequestMapping(value="deleteDeath/{deathId}/{wardASId}",method = RequestMethod.GET)
		public String deleteDeath(@PathVariable int wardASId, @PathVariable int deathId, Model m,HttpSession session) {
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			ddao.deleteDeath(deathId);
			return "redirect:/wardas/manageDeathList/{wardASId}";
		}*/
		
		@RequestMapping("/getAddDeath/{wardASId}")
		public String getAddDeath(@PathVariable int wardASId, Model m,HttpSession session) {
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			 List<Resident> residentList = rdao.getResidentListByWardAS(w.getWardOrVillageId());
			 m.addAttribute("residentList",residentList);
			m.addAttribute("death",new Death());
			return "wardas/WardASAddDeathPage";
		}
		
		@RequestMapping(value="addDeath/{wardASId}", method = RequestMethod.POST)
		public String addDeath(@PathVariable int wardASId, @ModelAttribute("death") Death d,Model m,HttpSession session) {
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			 ddao.saveDeath(d);
			Resident r = rdao.getResident(d.getResidentId());
			r.setDeceased(true);
			rdao.updateResident(r);
			return "redirect:/wardas/manageDeathList/{wardASId}";
		}
		
		@RequestMapping("/getEditDeath/{deathId}/{wardASId}")
		public String getEditDeath(@PathVariable int wardASId, @PathVariable int deathId,Model m,HttpSession session) {
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			 List<Resident> residentList = rdao.getDeceasedAliveResidentListByWardAS(w.getWardOrVillageId());
				m.addAttribute("residentList",residentList);
				Death d= ddao.getDeath(deathId);
				int residentId = d.getResidentId();
				Resident r = rdao.getDeceasedAliveResident(residentId);
				r.setDeceased(false);
				rdao.updateResident(r);
				m.addAttribute("command",d);
			return "wardas/WardASEditDeathPage";
		}
		
		@RequestMapping(value="editDeath/{wardASId}",method = RequestMethod.POST) 
		 public String editDeath(@PathVariable int wardASId, @ModelAttribute("d") Death d,Model m,HttpSession session){ 
			if(session.getAttribute("wardAS")==null) {
		 		return "redirect:/staffLogin";
		 	}
			 WardAS w = wdao.getWardAS(wardASId); // To use wardAS in the next page
			 m.addAttribute("wardAS",w); // To use wardAS in the next page
			ddao.updateDeath(d); 
			Resident r = rdao.getResident(d.getResidentId());
			r.setDeceased(true);
			rdao.updateResident(r);
			return "redirect:/wardas/manageDeathList/{wardASId}";
		 } 
		
		//*******************************Death List Management Finish************************************//
}
