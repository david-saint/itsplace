
import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
 
import com.itsplace.partner.place.PlaceComment;
import com.itsplace.partner.place.PlaceService;
import com.myplace.common.CommonService;
import com.myplace.common.Notice;
import com.myplace.partner.franchiser.FranchiserMember;
import com.myplace.partner.franchiser.FranchiserService;
import com.myplace.partner.franchiser.stamp.Stamp;
import com.myplace.partner.franchiser.stamp.StampService;
import com.myplace.user.User;
import com.myplace.user.UserController;
import com.myplace.user.UserService;
import com.myplace.util.PagingManager;
import com.myplace.util.StandardOrMobile;

/**
 * Handles and retrieves the common or admin page depending on the URI template.
 * A user must be log-in first he can access these pages.  Only the admin can see
 * the adminpage, however.
 */
@Controller
public class TestController {
	private static final Logger logger =  LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private FranchiserService franchiserService;
	@Autowired
	private PlaceService placeService;
	@Autowired
	private StampService stampService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private Facebook facebook;
	
	
	
    @RequestMapping(value = "/blankPage2", method = RequestMethod.GET)
    public String getBlankPage(Principal principal) {
    	logger.debug("Received request to show admin page");
    	
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/adminpage.jsp
    	return "blankPage";
    }
    
	
	

}
