package Factory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageFactory {
    public ImageView imagePath(String path){
        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream(path)));
        icon.setFitWidth(35);
        icon.setPreserveRatio(true);
        return icon;
    }
}
