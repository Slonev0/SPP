package spp.tp3_4;

import java.awt.image.BufferedImage;

/**
 * Class that implements a gray level filter
 */
public class GrayLevelFilter implements IFilter {
    /**
     * Margin of the filter
     */
    private int margin;

    /**
     * Constructor, we fix the margin to 0 in it
     */
    public GrayLevelFilter() {
        this.margin = 0;
    }

    /**
     * Getter for the margin
     * @return the margin
     */
    @Override
    public int getMargin() {
        return margin;
    }

    /**
     * Method that applies the filter at a given point
     * @param x the x coordinate
     * @param y the y coordinate
     * @param imgIn the input image
     * @param imgOut the output image
     */
    @Override
    public void applyFilterAtPoint(int x, int y, BufferedImage imgIn, BufferedImage imgOut) {
        int rgb = imgIn.getRGB(x, y);

        int red = (rgb >> 16) & 0x000000FF;
        int green = (rgb >> 8) & 0x000000FF;
        int blue = (rgb) & 0x000000FF;

        int newRgb = (red + green + blue) / 3;

        newRgb = (newRgb << 16) | (newRgb << 8) | newRgb;

        imgOut.setRGB(x, y, newRgb);
    }
}
