package com.mincoms.exception;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import android.content.Context;
import android.content.Intent;

public class ExceptionReport {
	
	private Context CurContext;
	
	public void SendErrorMail(Context _context, String ErrorContent) {
		  Intent sendIntent = new Intent(Intent.ACTION_SEND);
		  String subject = ("Crash Report - Android ErrorReporter");
		  String body = ErrorContent + "\n\n";
		  sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"faye12005@gmail.com"});
		  sendIntent.putExtra(Intent.EXTRA_TEXT, body);
		  sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		  sendIntent.setType("message/rfc822");
		  _context.startActivity(Intent.createChooser(sendIntent, "Title:"));
		 }
		 private void SaveAsFile(String ErrorContent) {
		  try {
		   Date CurDate = new Date();
		   String FileName = "stack-" + CurDate.toString() + ".stacktrace";
		   FileOutputStream trace = CurContext.openFileOutput(FileName,
		     Context.MODE_PRIVATE);
		   trace.write(ErrorContent.getBytes());
		   trace.close();
		  } catch (IOException ioe) {
		   // ...
		  }
		 }
}
