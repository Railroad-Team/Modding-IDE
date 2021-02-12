package io.github.railroad.utility;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.concurrent.Task;

public class DownloadUtils {

	public void fetch(String url) {
		Task<Void> task = new DownloadTask(url);
//         ProgressBar progressBar = new ProgressBar();
//         progressBar.setPrefWidth(350);
//         progressBar.progressProperty().bind(task.progressProperty());
//         root.getChildren().add(progressBar);

		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}

	private static class DownloadTask extends Task<Void> {

		private final String url;

		public DownloadTask(String url) {
			this.url = url;
		}

		@Override
		protected Void call() throws Exception {
			String ext = url.substring(url.lastIndexOf("."));
			URLConnection connection = new URL(url).openConnection();
			long fileLength = connection.getContentLengthLong();

			try (InputStream is = connection.getInputStream();
				 OutputStream os = Files.newOutputStream(Paths.get("downloadedfile" + ext))) {

				long nread = 0L;
				byte[] buf = new byte[8192];
				int n;
				while ((n = is.read(buf)) > 0) {
					os.write(buf, 0, n);
					nread += n;
					updateProgress(nread, fileLength);
				}
			}

			return null;
		}

		@Override
		protected void failed() {
			System.out.println("failed");
		}

		@Override
		protected void succeeded() {
			System.out.println("downloaded");
		}
	}

}
