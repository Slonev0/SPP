package spp.tp3_4;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.Math;

public class GaussianContourExtractorFilter implements IFilter {
    private final int margin;

    public GaussianContourExtractorFilter() {
        this.margin = 5;
    }

    @Override
    public int getMargin() {
        return margin;
    }

    private int sign(int value){
        if (value < 0){
            return -1;
        } else if (value > 0) {
            return +1;
        }
        return 0;
    }
    @Override
    public void applyFilterAtPoint(int x, int y, BufferedImage imgIn, BufferedImage imgOut) {
        double gradx = 0;
        double grady = 0;

        for (int dx = -margin; dx < margin; dx++){
            for (int dy = -margin; dy < margin; dy++){
                int xNew = x + dx;
                int yNew = y + dy;
                if (xNew >= 0 && xNew < imgIn.getWidth() && yNew >= 0 && yNew < imgIn.getHeight()) {
                    //❤️
                    Color c = new Color(imgIn.getRGB(x, y));
                    gradx += sign(dx)*c.getBlue()*Math.exp( (-1/4) * ( Math.pow(dx,2) + Math.pow(dy,2) ) );
                    grady += sign(dy)*c.getBlue()*Math.exp( (-1/4) * ( Math.pow(dx,2) + Math.pow(dy,2) ) );
                }
            }
        }
        double norm = Math.sqrt(Math.pow(gradx, 2) + Math.pow(grady, 2));
        int pixel = (int) Math.max(0, 255 - (0.1*norm));
        Color newColor = new Color(pixel, pixel, pixel);
        imgOut.setRGB(x, y, newColor.getRGB());
    }
}
