package net.itsplace.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.itsplace.web.service.IndexService;
import net.itsplace.web.service.PlaceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlaceController {
	private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);
	@Autowired
	private PlaceService placeService;
	
	/**
	 * 가맹점 상세보기  <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2012. 5. 18.
	 * @param fid
	 * @param model
	 * @return view.jsp
	 * @throws 
	 * @see 
	 */

	@RequestMapping(value = "/place/view/{fid}", method = RequestMethod.GET)
	public String view( @PathVariable Integer fid, Model model) {
		model.addAttribute("place", placeService.getPlace(fid));
		return "web/place/view";
	}
}
