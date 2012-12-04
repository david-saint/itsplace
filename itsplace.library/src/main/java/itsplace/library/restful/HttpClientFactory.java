package itsplace.library.restful;
import org.apache.http.HttpVersion;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
public class HttpClientFactory {
	private static DefaultHttpClient client;

	public synchronized static DefaultHttpClient getThreadSafeClient() {
		if (client != null)
			return client;
		// client = new DefaultHttpClient();
		// ClientConnectionManager mgr = client.getConnectionManager();
		// HttpParams params = client.getParams();
		// client = new DefaultHttpClient(new
		// ThreadSafeClientConnManager(params,
		// mgr.getSchemeRegistry()), params);
		final HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, "UTF-8");

		final SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 8080));

		final ThreadSafeClientConnManager manager = new ThreadSafeClientConnManager(
				params, registry);
		client = new DefaultHttpClient(manager, params);
		return client;

	}
}

