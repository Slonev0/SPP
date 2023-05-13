package spp.tp3_4;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GrayLevelFilter implements IFilter {
    private int margin;

    public GrayLevelFilter() {
        this.margin = 0;
    }
    @Override
    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin){
        this.margin = margin;
    }

    @Override
    public void applyFilterAtPoint(int x, int y, BufferedImage imgIn, BufferedImage imgOut) {
        int rgb = imgIn.getRGB(x, y);
        // extracting red, green and blue components from rgb integer
        int red = (rgb >> 16) & 0x000000FF;
        int green = (rgb >> 8) & 0x000000FF;
        int blue = (rgb) & 0x000000FF;
        // computing new color from extracted components

        int newRgb = (red + green + blue) / 3;

        newRgb = (newRgb << 16) | (newRgb << 8) | newRgb;

        imgOut.setRGB(x, y, newRgb);
    }
}
