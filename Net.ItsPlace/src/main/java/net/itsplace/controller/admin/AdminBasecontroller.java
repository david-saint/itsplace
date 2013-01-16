package net.itsplace.controller.admin;


import java.util.List;
import java.util.Locale;

import net.itsplace.domain.Address;
import net.itsplace.domain.Bascd;
import net.itsplace.domain.JsonResponse;
import net.itsplace.domain.User;
import net.itsplace.domain.Bascd.AddBascd;
import net.itsplace.domain.Bascd.EditBascd;
import net.itsplace.domain.User.AddUser;
import net.itsplace.domain.DataTable;
import net.itsplace.service.BaseService;
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
	private BaseService baseService;
	@Autowired
	private PagingManager pagingManaer;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String base_list(
			@RequestParam (value = "grpcd", required = false, defaultValue = "") String grpcd,
			Model model
			) {
		model.addAttribute("grpBasCdList",baseService.getGrpBascdList());
		model.addAttribute("basCdList", baseService.getBascdList(grpcd));
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
		model.addAttribute("grpBasCdList",baseService.getGrpBascdList());
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
			baseService.saveBascd(bascd);
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
		Bascd b = baseService.getBascd(no);
		logger.info("dd:"+b.getBasName());
		model.addAttribute("bascd",baseService.getBascd(no));	
		model.addAttribute("grpBasCdList",baseService.getGrpBascdList());
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
			baseService.editBascd(bascd);
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
                    List<Bascd> list = baseService.getBascdList("");
                    DataTable<Bascd> table = iDisplayLength != null ?
                                    new DataTable<Bascd>(columns, sSortDir_0, iDisplayStart, iDisplayLength) :
                                    new DataTable<Bascd>(columns, sSortDir_0, iDisplayStart);
                    table.setRows(baseService.getBascdList(grpCd)); // TODO add filter params to the service method, like in organizations.
                    table.setiTotalDisplayRecords( 100);
                    table.setiTotalDisplayRecords(pagingManaer.getTotalCount());
                   
                    return table;
           
                   
    }    */   
	
	@RequestMapping(value = "/address/search", method = RequestMethod.GET)
	public String search() {
		
		return "common/address/localapi";
	}
	
	/**
	 *  주소검색  <br />
	 *  지번검색 : 동명 번지수 또는 도로명검색
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param iDisplayStart 페이지 번
	 * @param iDisplayLength 페이지 로우수 (한페이지에 보여줄 로우수)
	 * @param iSortCol_0 sort할 컬럼 번호 
	 * @param sSortDir_0 sort할 방향(asc/desc)
	 * @param sSearch 검색
	 * @return DataTables
	 * @throws 
	 * @see 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/address/list",method = RequestMethod.GET)
    @ResponseBody
    public DataTable<Address> list(
    								@RequestParam(required=false, defaultValue="1") Integer iDisplayStart,
    								@RequestParam(required=false, defaultValue="10") Integer iDisplayLength,
    								@RequestParam(required=false, defaultValue="1") Integer iSortCol_0, 
    								@RequestParam(required=false, defaultValue="DESC" ) String sSortDir_0, 
                                    @RequestParam(required=false, defaultValue="") String sSearch ) {

       String columns[] = new String[]{"sido", "gugun", "bupname"};
       
       return baseService.getAddressList(columns, iDisplayStart, iDisplayLength, iSortCol_0, sSortDir_0, sSearch);
           
                   
    }   
}
