package co.aurasphere.botmill.kik.webhook;

import java.io.File;
import java.net.ServerSocket;
import java.util.HashMap;

import co.aurasphere.botmill.kik.model.KikApi;

/**
 * The listener interface for receiving threadWebhook events. The class that is
 * interested in processing a threadWebhook event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addThreadWebhookListener</code> method. When the
 * threadWebhook event occurs, that object's appropriate method is invoked.
 *
 */
public class ThreadWebhookListener extends Thread {

	/** The kik api. */
	private KikApi kikApi;

	/** The file map. */
	private HashMap<String, File> fileMap = new HashMap<String, File>();

	/**
	 * Instantiates a new thread webhook listener.
	 *
	 * @param kikApi
	 *            the kik api
	 */
	public ThreadWebhookListener(KikApi kikApi) {
		this.kikApi = kikApi;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(kikApi.getPort());
			ss.setReuseAddress(true);
			System.out.println("Starting webhook listener on port " + kikApi.getPort());

			while (ss.isBound()) {
				new ThreadConnection(ss.accept(), kikApi).start();
			}

			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to start webhook server!");
		}
	}

	/**
	 * Gets the file map.
	 *
	 * @return the file map
	 */
	public HashMap<String, File> getFileMap() {
		return fileMap;
	}
}
