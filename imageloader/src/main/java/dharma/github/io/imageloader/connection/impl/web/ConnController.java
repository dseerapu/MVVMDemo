package dharma.github.io.imageloader.connection.impl.web;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/**
 * URL connection management
 */
public class ConnController {

    /**
     * HTTP type address
     */
    public static final int TYPE_HTTP = 1;

    /**
     * HTTPS type address
     */
    public static final int TYPE_HTTPS = 2;

    /**
     * Connection timeout
     */
    private static final int CONNECT_TIMEOUT = 10000;

    /**
     * Read timeout
     */
    private static final int READ_TIMEOUT = 15000;

    /**
     * Automatically determine HTTP or HTTPS and get HttpURLConnection
     *
     * @param url address
     * @return Return null if the address is illegal
     */
    public static HttpURLConnection getURLConnection(String url)
            throws Exception {

        int type = 0;

        // HTTPS type
        if (url.startsWith("https://")) {
            type = TYPE_HTTPS;
        }

        // HTTP type
        else if (url.startsWith("http://")) {
            type = TYPE_HTTP;
        }

        HttpURLConnection connection = null;

        switch (type) {
            default:// Illegal address
                return null;

            case TYPE_HTTP:// Create HttpURLConnection

                connection = (HttpURLConnection) new URL(url).openConnection();

                break;
            case TYPE_HTTPS:// Create HttpsURLConnection

                // Trust all HTTPS connections
                HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                    public boolean verify(String string, SSLSession ssls) {
                        return true;
                    }
                });

                connection = (HttpsURLConnection) new URL(url).openConnection();
                break;
        }

        // Connection timeout
        connection.setConnectTimeout(CONNECT_TIMEOUT);

        // Read timeout
        connection.setReadTimeout(READ_TIMEOUT);

        // User-Agent
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; U; Android ;) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");

        return connection;

    }

}