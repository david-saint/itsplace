package net.itsplace.common;

import java.util.Map;

public class Basecd {

	private String mediaImage;
	private String mediaMovie;
	private String mediaLarge;
	private String mediaMedium;
	private String mediaThumbnail;
	private String mediaImageHost;
	private String mediaMovieHost;
	
	private Map<String, String> baseMap;
	
	public Basecd(Map<String, String> baseMap) {
		super();
		this.baseMap = baseMap;
	}

	
	
	public String getMediaImageHost() {
		mediaImageHost = baseMap.get("MEDIA,IMAGEHOST");
		return mediaImageHost;
	}



	public String getMediaMovieHost() {
		mediaMovieHost = baseMap.get("MEDIA,MOVIEHOST");
		return mediaMovieHost;
	}



	public String getMediaImage() {
		mediaImage = baseMap.get("MEDIA,IMAGE");
		return mediaImage;
	}

	public String getMediaMovie() {
		mediaMovie = baseMap.get("MEDIA,MOVIE");
		return mediaMovie;
	}

	public String getMediaLarge() {
		mediaLarge = baseMap.get("MEDIA,LARGE");
		return mediaLarge;
	}

	public String getMediaMedium() {
		mediaMedium = baseMap.get("MEDIA,MEDIUM");
		return mediaMedium;
	}

	public String getMediaThumbnail() {
		mediaThumbnail = baseMap.get("MEDIA,THUMBNAIL");
		return mediaThumbnail;
	}

}
