package net.itsplace.admin.controller;


import java.util.List;
import java.util.Locale;

import net.itsplace.domain.Bascd;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.Bascd.AddBascd;
import net.itsplace.domain.Bascd.EditBascd;
import net.itsplace.domain.DataTable;
import net.itsplace.service.IBaseService;
import net.itsplace.user.User;
import net.itsplace.user.User.AddUser;
import net.itsplace.util.PagingManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/base")
public class AdminBasecontroller {
	private static final Logger logger = LoggerFactory.getLogger(AdminBasecontroller.class);
	@Autowired
	private IBaseService adminBaseService;
	@Autowired
	private PagingManager pagingManaer;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String base_list(
			@RequestParam (value = "grpcd", required = false, defaultValue = "") String grpcd,
			Model model
			) {
		model.addAttribute("grpBasCdList",adminBaseService.getGrpBascdList());
		model.addAttribute("basCdList", adminBaseService.getBascdList(grpcd));
		return "admin/base/list";
	}
	
	/**
	 * 기초코드 생성폼<br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  add.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("bascd",new Bascd());	
		model.addAttribute("grpBasCdList",adminBaseService.getGrpBascdList());
		return "admin/base/add";
	}
	/**
	 * 기초코드  생성 <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  list.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
 	public String addSubmit(@Validated({AddBascd.class}) Bascd bascd, BindingResult result, Model model) {
		if (result.hasErrors()) {
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			
			return "admin/base/add";
		} else {	
			adminBaseService.saveBascd(bascd);
			return "redirect:/admin/base/list";
		}
	
	}
	/**
	 * 기초코드 생성폼<br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  edit.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required=true) Integer no, Model model)  {
		Bascd b = adminBaseService.getBascd(no);
		logger.info("dd:"+b.getBasName());
		model.addAttribute("bascd",adminBaseService.getBascd(no));	
		model.addAttribute("grpBasCdList",adminBaseService.getGrpBascdList());
		return "admin/base/edit";
	}
	/**
	 * 회원 생성 <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  list.jsp
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
 	public String editSubmit(@Validated({EditBascd.class}) Bascd bascd, BindingResult result, Model model) {
		if (result.hasErrors()) {
			logger.info(result.getObjectName() +": "+ result.getFieldError().getDefaultMessage() +"------------발생");
			return "admin/base/edit";
		} else {	
			adminBaseService.editBascd(bascd);
			return "redirect:/admin/base/list";
		}
	
	}
	/**
	 * 기초코드 삭제   <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param user
	 * @return JsonResponse
	 * @throws 
	 * @see 
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody JsonResponse delete(@RequestParam(required=true) String email)  {
		logger.info("email:{}",email);
		JsonResponse json = new JsonResponse();		
		User user = new User();
		user.setEmail(email);
		//adminUserService.deleteUser(user); 
			
		json.setResult(user);
		json.setStatus("SUCCESS");
		return json;
	}
	/*@RequestMapping(value="/lest")
    @ResponseBody
    public DataTable<Bascd> list(@RequestParam(required=false) String grpCd,
    				@RequestParam(required=false) Integer iDisplayStart, 
    				@RequestParam(required=false) Integer iDisplayLength,  Pagination 
                    @RequestParam(required=false) Integer iSortCol_0, 
                    @RequestParam(required=false) String sSortDir_0,  Sorting 
                    @RequestParam(required=false, defaultValue="") String sSearch  Search 
                    ) {

                    System.out.println("iDisplayStart:"+ iDisplayStart.toString());
                    System.out.println("sSortDir_0:"+ sSortDir_0.toString());
                    System.out.println("iSortCol_0:"+ iSortCol_0.toString());
                    System.out.println("iDisplayLength:"+ iDisplayLength.toString());
                    System.out.println("sSearch:"+ sSearch.toString());
                    System.out.println("grpCd:"+ grpCd.toString());
                    
                    
                    String columns[] = new String[]{"grpCd", "grpName", "basName", "baseCd"};
                    List<Bascd> list = adminBaseService.getBascdList("");
                    DataTable<Bascd> table = iDisplayLength != null ?
                                    new DataTable<Bascd>(columns, sSortDir_0, iDisplayStart, iDisplayLength) :
                                    new DataTable<Bascd>(columns, sSortDir_0, iDisplayStart);
                    table.setRows(adminBaseService.getBascdList(grpCd)); // TODO add filter params to the service method, like in organizations.
                    table.setiTotalDisplayRecords( 100);
                    table.setiTotalDisplayRecords(pagingManaer.getTotalCount());
                   
                    return table;
           
                   
    }    */   
}
