package game.tools;

import net.coobird.thumbnailator.Thumbnails;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tools {

    private Tools() {}

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        try {
            return Thumbnails.of(img).forceSize(newW, newH).asBufferedImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Point interpolationByDistance(Point p1, Point p2, double d) {
        double len = p1.distance(p2);
        double ratio = d/len;
        int x = (int) (ratio*p2.x + (1.0 - ratio)*p1.x);
        int y = (int) (ratio*p2.y + (1.0 - ratio)*p1.y);
        return new Point(x, y);
    }
}
