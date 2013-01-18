package net.itsplace.basecode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class BaseCodeService {

	  @Autowired
	  MessageSource messageSource;
	  
	  public  Collection getPlaceTypes(){
		  Collection<PlaceType> types = new ArrayList<PlaceType>();
		  for(PlaceType placeType : PlaceType.values()) {
			  String description = messageSource.getMessage(placeType.name(), null, Locale.getDefault());
			  placeType.setDescription(description);
			  types.add(placeType);
		  }
		  return types;
	 } 
	  
	 public  Collection getServiceTypes(){
		  Collection<ServiceType> types = new ArrayList<ServiceType>();
		  for(ServiceType serviceType : ServiceType.values()) {
			  String description = messageSource.getMessage(serviceType.name(), null, Locale.getDefault());
			  serviceType.setDescription(description);
			  types.add(serviceType);
		  }
		  return types;
	 } 
}
