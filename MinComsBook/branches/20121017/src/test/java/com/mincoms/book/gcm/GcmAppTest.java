package com.mincoms.book.gcm;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.mincoms.test.TestApplicationContext;

public class GcmAppTest   {

	@Test
	public void test() throws IOException {
		String registrationId = "APA91bGqfoV52YG0prnAxjfUO_RSgtGFYNB3SVWhWN--G72P9GJHjorr8NyjcTxYpeW7sMGhwSx1SOV9cd2IdAFNABBVomyvp6t2710e_Ic1Ez65IBSuJxLNb8PB-E570y6_fzzh1R5c";
		Sender sender = new Sender("AIzaSyBpTW07vNhjvRuDgsg3qZvdNQW89aOYgac");
        Message message = new Message.Builder()
        .addData("message","uhu!!!!").build();
        Result result = sender.send(message, registrationId, 5);
       String status = "Sent message to one device: " + result;
        System.out.println(status);
	}

}
