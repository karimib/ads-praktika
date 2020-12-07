import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.regex.Pattern;

public class RegexSearchServer {

	private static String DEFAULT_URL = "https://tel.search.ch/?was=Meier";

	private static final String SWISS_PHONE_NO_REGEX = "(?:(\\b(0041|0)|\\B\\+41)(\\s?\\(0\\))?(\\s)?[1-9]{2}(\\s)?[0-9]{3}(\\s)?[0-9]{2}(\\s)?[0-9]{2}\\b)|(?:0800\\s?[0-9]{3}\\s?[0-9]{3})";

	public static void main(String[] args) {
		if (args.length == 0) {
			new RegexSearchServer().testIt(DEFAULT_URL);
		} else {
			String https_url = args[0];
			new RegexSearchServer().testIt(https_url);
		}
	}

	private void testIt(String https_url) {
		URL url;
		try {
			url = new URL(https_url);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			// dumpl all cert info
			print_https_cert(con);
			// dump all the content
			print_search_content(con, SWISS_PHONE_NO_REGEX);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void print_search_content(HttpsURLConnection con, String regex) throws IOException {
		Pattern pattern = Pattern.compile(regex);
		if (con != null) {
			BufferedReader br = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
			String input;
			System.out.println("****** Filtered Content of the URL :" + DEFAULT_URL + "  ********");
			br.lines()
				.map(pattern::matcher)
				.forEach(matcher -> {
					while (matcher.find()) {
						System.out.println(matcher.group());
					}
				});
			br.close();
		}
	}

	private void print_https_cert(HttpsURLConnection con) {
		if (con != null) {
			try {
				System.out.println("Response Code : " + con.getResponseCode());
				System.out.println("Cipher Suite : " + con.getCipherSuite());
				System.out.println("\n");

				Certificate[] certs = con.getServerCertificates();
				for (Certificate cert : certs) {
					System.out.println("Cert Type : " + cert.getType());
					System.out.println("Cert Hash Code : " + cert.hashCode());
					System.out.println(
						"Cert Public Key Algorithm : "
							+ cert.getPublicKey().getAlgorithm());
					System.out.println(
						"Cert Public Key Format : "
							+ cert.getPublicKey().getFormat());
					System.out.println("\n");
				}

			} catch (SSLPeerUnverifiedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}

