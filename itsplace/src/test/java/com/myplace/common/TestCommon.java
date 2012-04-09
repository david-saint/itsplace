package com.myplace.common;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import com.myplace.TestApplicationContext;
import com.myplace.common.*;
import java.awt.Color; 
import java.awt.Graphics2D; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 
import javax.imageio.ImageIO; 
import com.google.zxing.ReaderException; 
import com.google.zxing.WriterException; 
import com.google.zxing.common.BitMatrix; 
import com.google.zxing.common.DecoderResult; 
import com.google.zxing.qrcode.decoder.Decoder; 
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel; 
import com.google.zxing.qrcode.encoder.Encoder; 
import com.google.zxing.qrcode.encoder.QRCode; 
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.myplace.user.TestRestUser;
import com.myplace.user.UserController;

public class TestCommon extends TestApplicationContext {
	  
	private static final Logger logger = Logger.getLogger(TestCommon.class);

	@Autowired
	CommonService cService;
	
	Address address;
	
	public void getAddress() {
		ArrayList<String> temp = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer("진천동 5255"," ");
	   
		while (st.hasMoreTokens()) {																
			temp.add(st.nextToken());				
		}
		
		for(int i = 0 ; i<temp.size();i++){
			if(i==0){
				//param.put("bupname", temp.get(0));
				logger.info("지번주소 동이름:" +temp.get(0));
			}
			if(i==1){
			//	param.put("jimain",  temp.get(1));
				logger.info("지번주소 번지:" +temp.get(1));
				if(temp.get(1).length()>1){
					StringTokenizer bunji = new StringTokenizer(temp.get(1),"-");
					while (bunji.hasMoreTokens()) {																
						logger.info("번지:" +bunji.nextToken());				
					}
				}
			}
		}
	} 
	@Test
	public void test() throws Exception{
	/*	final Geocoder geocoder = new Geocoder();
	    //GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress("Rijnsburgstraat 9-11, Amsterdam, The Netherlands").getGeocoderRequest();
		LatLng location = new LatLng("35.8570479","128.5444278");
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setLocation(location).getGeocoderRequest();
	    GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
	    List<GeocoderResult> results = geocoderResponse.getResults();
	    float latitude = results.get(0).getGeometry().getLocation().getLat().floatValue();
	    float longitude = results.get(0).getGeometry().getLocation().getLng().floatValue();
	    logger.info(latitude+"");
	    logger.info(latitude+"");
	   
	    QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String text = "테스트";
        text = new String(text.getBytes("UTF-8"), "ISO-8859-1");
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE,100, 100);
        MatrixToImageWriter.writeToFile(bitMatrix, "png",
                new File("c:/qrcode.png"));
	   // assertEquals(52.347797f, latitude);
	   // assertEquals(4.8507648f, longitude);*/
	}
	

}
