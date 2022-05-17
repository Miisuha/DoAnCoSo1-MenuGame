package snake;

import java.awt.Image;
import java.util.ArrayList;
public class Animation {
    private ArrayList<Image> images;
    int currentImage;
    
    public Animation() {
        images = new ArrayList<Image>();
        currentImage = 0;
    }
    
    public void Update() {
        currentImage++;
        if (currentImage >= images.size()) currentImage = 0;
    }
    
    public Image getCurrentImage() {
        return images.get(currentImage);
    }
    
    public void addImage(Image img) {
        images.add(img);
    }
}
