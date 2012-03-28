package com.myplace.partner.franchiser.image;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myplace.partner.franchiser.FranchiserController;
import com.myplace.partner.franchiser.FranchiserMember;
import com.myplace.partner.franchiser.FranchiserService;
import com.myplace.partner.franchiser.stamp.Stamp;


@Controller
public class FranchiserImageController {

	private static final Logger logger =  LoggerFactory.getLogger(FranchiserImageController.class);
	@Autowired
	private FranchiserImageService fService;
	
	
	@RequestMapping(value = "/admin/franchiserImageUpload/{fid}", method = RequestMethod.GET)
	public String franchiserImageUpload(Model model,
			 @PathVariable int fid) {
		
		FranchiserImage franchiserImage = new FranchiserImage();
		franchiserImage.setFid(fid);
		
		List<FranchiserImage> franchiserImageList  = fService.getFranchiserImageList(franchiserImage);
		
		model.addAttribute("franchiserImageList", franchiserImageList);
		model.addAttribute("franchiserImage", franchiserImage);
		
		
		
		return "franchiser/franchiserImageUpload";
		
	}
	
	@RequestMapping(value = "/admin/franchiserImageUpload", method = RequestMethod.POST)
	public String franchiserImageUpload(FranchiserImage franchiserImage) {
		
		
	
		franchiserImage = fService.saveFranchiserImage(franchiserImage);
		
	
		return "redirect:/admin/franchiserImageUpload/"+franchiserImage.getFid();
		
	}
}
