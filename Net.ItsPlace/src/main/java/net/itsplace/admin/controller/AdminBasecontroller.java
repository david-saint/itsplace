package net.itsplace.admin.controller;


import java.util.List;
import java.util.Locale;

import net.itsplace.admin.service.AdminBaseService;
import net.itsplace.domain.BasCd;
import net.itsplace.user.User;

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
@RequestMapping("/admin/base")
public class AdminBasecontroller {
	private static final Logger logger = LoggerFactory.getLogger(AdminBasecontroller.class);
	@Autowired
	private AdminBaseService adminBaseService;
	vv
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String base_list(
			@RequestParam (value = "grpCd", required = false, defaultValue = "") String grpCd,
			Model model
			) {
		model.addAttribute("grpBasCdList",adminBaseService.getGrpBascdList());
		model.addAttribute("basCdList", adminBaseService.getBascdList(grpCd));
		return "admin/base/list";
	}
	@RequestMapping(value="/table")
    @ResponseBody
    public DataTable2<BasCd> table3(@RequestParam(required=false) String grpCd,@RequestParam(required=false) Integer iDisplayStart, @RequestParam(required=false) Integer iDisplayLength, /* Pagination */
                    @RequestParam(required=false) Integer iSortCol_0, @RequestParam(required=false) String sSortDir_0, /* Sorting */
                    @RequestParam(required=false, defaultValue="") String sSearch /* Search */) {

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
                    
                    String columns[] = new String[]{"grpCd", "grpName", "basName"};
                    List<BasCd> list = adminBaseService.getBascdList("");
                    DataTable2<BasCd> table = iDisplayLength != null ?
                                    new DataTable2<BasCd>(columns, sSortDir_0, iDisplayStart, iDisplayLength) :
                                    new DataTable2<BasCd>(columns, sSortDir_0, iDisplayStart);
                    table.setRows(adminBaseService.getBascdList(grpCd)); // TODO add filter params to the service method, like in organizations.
                    table.setiTotalDisplayRecords((long) 100);
                    System.out.println("order:"+table.getOrdering(iSortCol_0));
                    return table;
           
                   
    }       
}