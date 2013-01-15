package net.itsplace.common;

import static org.junit.Assert.*;

import java.util.List;

import net.itsplace.admin.controller.AdminEventController;
import net.itsplace.admin.controller.AdminEventControllerTest;
import net.itsplace.basecode.Host;
import net.itsplace.basecode.ImageSize;
import net.itsplace.basecode.MediaType;
import net.itsplace.domain.Address;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.init.TestApplicationContext;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonControllerTest{
	@Test
	public void testList() throws Exception {
		System.out.println(Host.Image.getUrl());
		System.out.println(Host.Movie);
		System.out.println(MediaType.Image);
			 
	}

}
