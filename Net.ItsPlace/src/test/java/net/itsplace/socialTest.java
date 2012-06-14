package net.itsplace;

import javax.inject.Inject;

import net.itsplace.admin.controller.AdminEventControllerTest;
import net.itsplace.init.TestApplicationContext;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

public class socialTest  extends TestApplicationContext {
	  
	private static final Logger logger = LoggerFactory.getLogger(socialTest.class);

	//private final Twitter twitter;
	
	

	@Test
	public void testAdd() throws Exception {
		Twitter twitter = new TwitterTemplate();
		SearchResults results = twitter.searchOperations().search("chbfvgghgvgh");
		for(int i=0;i<results.getTweets().size();i++){
			logger.info(results.getTweets().get(i).getText());
		}
	}
}
