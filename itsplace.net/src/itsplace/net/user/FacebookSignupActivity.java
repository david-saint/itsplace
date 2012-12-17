package itsplace.net.user;

import itsplace.library.restful.AsyncClient;
import itsplace.net.R;

import org.apache.cordova.DroidGap;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FacebookSignupActivity  extends Activity{
	private  AsyncHttpClient client = AsyncClient.getInstance();
	protected static final String TAG = FacebookSignupActivity.class.getSimpleName();
	
	EditText emailEditText;
	EditText nameEditText;
	EditText passwordEditText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.social_signup);
		String email = getIntent().getStringExtra("email");
		String name = getIntent().getStringExtra("name");
		final String token = getIntent().getStringExtra("token");

		emailEditText = (EditText) findViewById(R.id.email);
		passwordEditText = (EditText) findViewById(R.id.password);
		nameEditText = (EditText) findViewById(R.id.name);
		emailEditText.setText(email);
		nameEditText.setText(name);

		Button btnSignUp = (Button) findViewById(R.id.btnSignUp);
		btnSignUp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				signup(emailEditText.getText().toString(), nameEditText.getText().toString(), passwordEditText.getText().toString(), token);

			}
		});
	}

	private void signup(final String email, final String name, String password, final String token) {
		String url = getString(R.string.base_uri) + "/user/register";
		RequestParams params = new RequestParams();
		params.put("email", email);
		params.put("name", name);
		params.put("password", password);
		client.post(url, params, new AsyncHttpResponseHandler() {
			@Override
			public void onStart() {
				Log.i(TAG, "onStart");
			}

			@Override
			public void onSuccess(String response) {
				Log.i(TAG, "onSuccess:" + response);
				try {
					JSONObject json = new JSONObject(response);
					String status = json.get("status").toString();
					if(status.equals("SUCCESS")){
						Log.i(TAG,"회원가입 성공");
						Intent intent = new Intent();
						intent.putExtra("email", email);
						intent.putExtra("token", token);
						intent.putExtra("name", name);
						setResult(RESULT_OK,intent);
					}else{
						Log.i(TAG,"회원가입 실패");
						setResult(RESULT_CANCELED);
					}
					finish();
					//socialLogin(email, name, token);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Throwable e, String response) {
				Log.i(TAG, "onFailure" + response);
				Log.i(TAG, e.getMessage());
			}

			@Override
			public void onFinish() {
				Log.i(TAG, "onFinish");
			}
		});

	}
}
