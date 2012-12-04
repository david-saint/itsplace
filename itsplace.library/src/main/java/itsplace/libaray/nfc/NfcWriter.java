package itsplace.libaray.nfc;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;

import android.widget.EditText;

public class NfcWriter  {
	private static final String TAG = "stickynotes";
    private boolean mResumed = false;
    private boolean mWriteMode = false;
    NfcAdapter mNfcAdapter;
    EditText mNote;

    PendingIntent mNfcPendingIntent;
    IntentFilter[] mWriteTagFilters;
    IntentFilter[] mNdefExchangeFilters;
    
    public NfcWriter(PendingIntent pendingIntent,Context context){
    	 mNfcAdapter = NfcAdapter.getDefaultAdapter(context);

    	 mNfcPendingIntent = PendingIntent.getActivity(context, 0,
                 new Intent(context, context.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
       
         // Intent filters for reading a note from a tag or exchanging over p2p.
         IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
         try {
             ndefDetected.addDataType("text/plain");
         } catch (MalformedMimeTypeException e) { }
         mNdefExchangeFilters = new IntentFilter[] { ndefDetected };

         // Intent filters for writing to a tag
         IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
         mWriteTagFilters = new IntentFilter[] { tagDetected };
    }
}
