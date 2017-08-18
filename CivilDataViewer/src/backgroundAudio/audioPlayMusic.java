package backgroundAudio;

import java.net.URL;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class audioPlayMusic {
	
	public audioPlayMusic() {
	    final URL resource = getClass().getResource("backgroundMusic.mp3");
	    final Media media = new Media(resource.toString());
	    final MediaPlayer mediaPlayer = new MediaPlayer(media);
	    mediaPlayer.play();
	}
}
