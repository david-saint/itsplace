package net.itsplace.admin.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.itsplace.admin.service.AdminUserService;
import net.itsplace.domain.BasCd;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.User;
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
    public DataTable<User> getUserList(@RequestParam(required=false) String grpCd,
    								@RequestParam(required=false, defaultValue="1") Integer iDisplayStart,
    								@RequestParam(required=false) Integer iDisplayLength,
    								@RequestParam(required=false) Integer iSortCol_0, 
    								@RequestParam(required=false) String sSortDir_0, 
                                    @RequestParam(required=false, defaultValue="") String sSearch ) {

                    System.out.println("iDisplayStart:"+ iDisplayStart.toString());
                    System.out.println("sSortDir_0:"+ sSortDir_0.toString());
                    System.out.println("iSortCol_0:"+ iSortCol_0.toString());
                    System.out.println("iDisplayLength:"+ iDisplayLength.toString());
                    System.out.println("sSearch:"+ sSearch.toString());
                    System.out.println("grpCd:"+ grpCd.toString());
                    // Filter 
                    // TODO Now its difficult to filter by columns. So there is a general filter and here we have to set the default column to filter.
                    String filterBy = "name";
                    String filterValue = sSearch;
                    // End filter
                    
                    
                		
            		Map<String, Object> param  = pagingManaer.createDataTableLimit(iDisplayStart, iDisplayLength);            
            		List<User> userList= adminUserService.getUserList(param);
            		pagingManaer.setTotalCount(pagingManaer.getFoundRows());
            		
            		
                    String columns[] = new String[]{"profileImageUrl", "email", "mobile"};                
                    DataTable<User> table = iDisplayLength != null ?
                                    new DataTable<User>(columns, sSortDir_0, iDisplayStart, iDisplayLength) :
                                    new DataTable<User>(columns, sSortDir_0, iDisplayStart);
                    table.setRows(userList); // TODO add filter params to the service method, like in organizations.
                    table.setiTotalDisplayRecords(pagingManaer.getTotalCount());
                   
                    return table;
           
                   
    }       
}
