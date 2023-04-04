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
        Color c = new Color(imgIn.getRGB(x, y));
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        //int gray = (int) (0.299 * r + 0.587 * g + 0.114 * b); // grayscale formula
        int gray = (int) (0.3 * r + 0.3 * g + 0.3 * b);
        Color newColor = new Color(gray, gray, gray);
        imgOut.setRGB(x - getMargin(), y - getMargin(), newColor.getRGB());
    }
}
