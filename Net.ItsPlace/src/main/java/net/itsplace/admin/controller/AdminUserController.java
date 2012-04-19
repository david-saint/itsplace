package net.itsplace.admin.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.itsplace.admin.service.AdminUserService;
import net.itsplace.domain.BasCd;
import net.itsplace.domain.DataTable;
import net.itsplace.user.User;
import net.itsplace.util.PagingManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
	private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);
	@Autowired
	private AdminUserService adminUserService;
	
	@Autowired
	private PagingManager pagingManaer;
	
	/**
	 * 회원리스트
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
	
		return "admin/user/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
	
		return "admin/user/add";
	}
	/**
	 * 회원리스트
	 * @param locale
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required=true) String email, Model model)  {
	
		model.addAttribute("user",adminUserService.getUser(email));
		User user = null;
		//user.setEmail("dd");
		//int i = 66666*10343434000;		
		
		return "admin/user/edit";
	}
	@RequestMapping(value="/getUserList")
    @ResponseBody
    public DataTable<User> getUserList(
    								@RequestParam(required=false, defaultValue="1") Integer iDisplayStart,
    								@RequestParam(required=false) Integer iDisplayLength,
    								@RequestParam(required=false) Integer iSortCol_0, 
    								@RequestParam(required=false) String sSortDir_0, 
                                    @RequestParam(required=false, defaultValue="") String sSearch ) {

                    logger.info("iDisplayStart:{}", iDisplayStart.toString());
                    logger.info("sSortDir_0:{}", sSortDir_0.toString());
                    logger.info("iSortCol_0:{}", iSortCol_0.toString());
                    logger.info("iDisplayLength:{}", iDisplayLength.toString());
                    logger.info("sSearch:{}", sSearch.toString());
                   
                    String columns[] = new String[]{"profileImageUrl", "email", "name","role", "mobile", "useyn", "emailyn"};
                    //도메인이랑 대소문자 일치해야
                    DataTable<User> table = iDisplayLength != null ?
                                    new DataTable<User>(columns, sSortDir_0, iDisplayStart, iDisplayLength) :
                                    new DataTable<User>(columns, sSortDir_0, iDisplayStart);
                    
                    logger.info("getOrderColumn:{}"+ table.getOrderColumn(iSortCol_0));
                   
            		Map<String, Object> param  = pagingManaer.createDataTableLimit(iDisplayStart, iDisplayLength);
                    param.put("search", sSearch);
                    param.put("sortDirection", sSortDir_0);
                    param.put("sortColumn", table.getOrderColumn(iSortCol_0));
            		
                    List<User> userList= adminUserService.getUserList(param);
            		pagingManaer.setTotalCount(pagingManaer.getFoundRows());
            		
            		
                   
                    table.setRows(userList); // TODO add filter params to the service method, like in organizations.
                    table.setiTotalDisplayRecords(pagingManaer.getTotalCount());
                   
                    return table;
           
                   
    }       
}
