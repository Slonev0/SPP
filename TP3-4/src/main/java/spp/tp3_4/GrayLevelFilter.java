package spp.tp3_4;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GrayLevelFilter implements IFilter {
    private final int margin;

    public GrayLevelFilter() {
        this.margin = 0;
    }
    @Override
    public int getMargin() {
        return margin;
    }

    @Override
    public void applyFilterAtPoint(int x, int y, BufferedImage imgIn, BufferedImage imgOut) {
        Color c = new Color(imgIn.getRGB(x, y));
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        int gray = (int) (0.299 * r + 0.587 * g + 0.114 * b); // grayscale formula
        Color newColor = new Color(gray, gray, gray);
        imgOut.setRGB(x, y, newColor.getRGB());
    }
}
