package com.mincoms.book.gcm;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that adds a new message to all registered devices.
 * <p>
 * This servlet is used just by the browser (i.e., not device).
 */
@SuppressWarnings("serial")
public class SendAllMessagesServlet extends BaseServlet {

  private static final int MULTICAST_SIZE = 1000;

  private Sender sender;

  private static final Executor threadPool = Executors.newFixedThreadPool(5);

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    sender = newSender(config);
  }

  /**
   * Creates the {@link Sender} based on the servlet settings.
   */
  protected Sender newSender(ServletConfig config) {
    String key = (String) config.getServletContext().getAttribute(ApiKeyInitializer.ATTRIBUTE_ACCESS_KEY);
    
    return new Sender(key);
  }

  /**
   * Processes the request to add a new message.
   */
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {
  //  List<String> devices = Datastore.getDevices();
    String status;
   
        // send a single message using plain post
        String registrationId = "APA91bGqfoV52YG0prnAxjfUO_RSgtGFYNB3SVWhWN--G72P9GJHjorr8NyjcTxYpeW7sMGhwSx1SOV9cd2IdAFNABBVomyvp6t2710e_Ic1Ez65IBSuJxLNb8PB-E570y6_fzzh1R5c";
        Message message = new Message.Builder()
        .addData("message","PUSH!!!!54야후11").build();
        Result result = sender.send(message, registrationId, 5);
        status = "Sent message to one device: " + result;
        System.out.println(status);
  }

 

}
