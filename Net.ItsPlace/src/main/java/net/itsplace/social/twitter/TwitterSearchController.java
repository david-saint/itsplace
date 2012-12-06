/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.itsplace.social.twitter;

import javax.inject.Inject;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TwitterSearchController {

	private final Twitter twitter;
	  
	private static final Logger logger = LoggerFactory.getLogger(TwitterSearchController.class);
	
	@Inject
	public TwitterSearchController(Twitter twitter) {
		this.twitter = twitter;
	}

	@RequestMapping(value="/twitter/search", method=RequestMethod.GET)
	public String showTrends(@RequestParam("query") String query, Model model) {
		model.addAttribute("timeline", twitter.searchOperations().search(query).getTweets());
		
		SearchResults results = twitter.searchOperations().search("안녕");
		for(int i=0;i<results.getTweets().size();i++){
			logger.info(results.getTweets().get(i).getText());
		}
		return "twitter/timeline";
	}
	
}
