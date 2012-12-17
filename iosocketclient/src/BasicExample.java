
import java.util.Arrays;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BasicExample implements IOCallback {
	private SocketIO socket;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new BasicExample();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BasicExample() throws Exception {
		socket = new SocketIO();
//		socket.connect("http://127.0.0.1:8070/", this);
	//	socket.connect("http://211.229.107.73:8070/", this);
		socket.connect("http://nodeplace.cloudfoundry.com", this);

		// Sends a string to the server.
		//socket.send("Hello Server");

		// Sends a JSON object to the server.
		//socket.send(new JSONObject().put("name", "자바").put("room",
		///		"waitRoom"));

		// Emits an event to the server.
		socket.emit("PlaceOn", new JSONObject().put("name", "자바").put("room","waitRoom"));
				///		"waitRoom"));
		//socket.disconnect();
	}

	@Override
	public void onMessage(JSONObject json, IOAcknowledge ack) {
		try {
			System.out.println("Server said:" + json.toString(2));
			System.out.println("Server said:" + json.getString("name")+":" + json.getString("comment"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
//socket.json.send({ room: $('#currentRoom').val(), name:$('#userName').val(), data: $('#message').val() });
	@Override
	public void onMessage(String data, IOAcknowledge ack) {
		//System.out.println("Server saidㄴㄴㄴㄴㄴㄴ: " + data);
		//JSONArray	array = new JSONArray(Arrays.asList(data));
		//JSONArray array = json.getJSONArray(args[i].toString());
		 // String room = (String) json.get("room");
		/* 
		try {
			JSONObject json = new JSONObject();
			JSONObject obj = json.getJSONObject(data);
			System.out.println(obj.get("name"));
			 System.out.println(obj.get("comment"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println("오류");
		}*/
		 
	}

	@Override
	public void onError(SocketIOException socketIOException) {
		System.out.println("an Error occured");
		socketIOException.printStackTrace();
	}

	@Override
	public void onDisconnect() {
		System.out.println("Connection terminated.");
	}

	@Override
	public void onConnect() {
		System.out.println("Connection established");
	}

	@Override
	public void on(String event, IOAcknowledge ack, Object... args) {
		System.out.println("Server triggered event '" + event + "'");
		if(event.equals("SetUserList")){
			JSONObject json = new JSONObject();
			for(int i=0;i<args.length;i++){
				System.out.println(args[i].toString());
				try {
					
					JSONArray	array = new JSONArray(Arrays.asList(args));
					//JSONArray array = json.getJSONArray(args[i].toString());
					 // String room = (String) json.get("room");
					 JSONObject obj = (JSONObject) array.get(0);
					 System.out.println(obj.get("room"));
					
					 JSONArray userList = (JSONArray) obj.get("userList");
					 for(int j=0;j<userList.length();j++){
						 System.out.println( userList.get(j));
					 }
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
