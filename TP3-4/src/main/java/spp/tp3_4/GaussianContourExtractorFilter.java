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

    @Override
    public void applyFilterAtPoint(int x, int y, BufferedImage imgIn, BufferedImage imgOut) {
        double gradx = 0;
        double grady = 0;

        int xNew;
        int yNew;

        for (int dx = -margin; dx < margin; dx++){
            for (int dy = -margin; dy < margin; dy++){
                xNew = x + dx;
                yNew = y + dy;

                if (xNew >= 0 && xNew < imgIn.getWidth() && yNew >= 0 && yNew < imgIn.getHeight()) {
                    int newRgb = imgIn.getRGB(xNew, yNew);
                    int newBlue = newRgb & 0x000000FF;

                    double gaussianWeight = Math.exp(-0.25 * ((dx * dx) + (dy * dy)));

                    gradx += Math.signum(dx) * newBlue * gaussianWeight;
                    grady += Math.signum(dy) * newBlue * gaussianWeight;
                }
            }
        }
        double norm = Math.sqrt(Math.pow(gradx, 2) + Math.pow(grady, 2));
        int blue = (int) Math.max(0, 255 - 0.5 * norm);

        int newRgb = (blue << 16) | (blue << 8) | blue;
        imgOut.setRGB(x, y, newRgb);
    }



}
