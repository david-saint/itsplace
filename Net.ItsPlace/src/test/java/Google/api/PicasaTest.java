package Google.api;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.data.photos.UserFeed;
import com.google.gdata.util.ServiceException;

public class PicasaTest {
//ref https://developers.google.com/picasa-web/docs/2.0/reference?hl=ko#media_reference
	//imgmax=94, 110, 128, 200, 220, 288, 320, 400, 512, 576, 640, 720, 800, 912, 1024, 1152, 1280, 1440, 1600
			//thumbsize=	32, 48, 64, 72, 104, 144, 150, 160	
	@Test
	public void test() throws Exception{
		PicasawebService myService = new PicasawebService("exampleCo-exampleApp-1");
		myService.setUserCredentials("faye12005@gmail.com", "love1014");
		String username ="faye12005";
		URL feedUrl = new URL("https://picasaweb.google.com/data/feed/api/user/"+username+"?kind=album");

		UserFeed myUserFeed = myService.getFeed(feedUrl, UserFeed.class);

		for (AlbumEntry myAlbum : myUserFeed.getAlbumEntries()) {
		    System.out.println(myAlbum.getId() + myAlbum.getTitle().getPlainText());
		    System.out.println();
		}
		String albumid = "5817687017877875345";
		URL feedUrl2 = new URL("https://picasaweb.google.com/data/feed/api/user/faye12005/albumid/"+albumid+"?imgmax=1600&thumbsize=160");
		//URL feedUrl2 = new URL("https://picasaweb.google.com/data/entry/user/102726922121983316049/albumid/57717004979955963692011");

		AlbumFeed feed = myService.getFeed(feedUrl2, AlbumFeed.class);

		for(PhotoEntry photo : feed.getPhotoEntries()) {
		    //System.out.println("섬네일:"+ photo.getMediaThumbnails().get(0).getUrl());
		    System.out.println("d:"+ photo.getMediaContents().get(0).getUrl());
		}
	}
	@Test
	public void upload(){
		//MediaFileSource myMedia = new MediaFileSource(mediaFile, mediaType)
	}
}
