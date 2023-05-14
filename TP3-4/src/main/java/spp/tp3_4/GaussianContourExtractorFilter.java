package spp.tp3_4;

import java.awt.image.BufferedImage;
import java.lang.Math;

/**
 * Class that implements a Gaussian contour extractor filter
 */
public class GaussianContourExtractorFilter implements IFilter {

    /**
     * Margin of the filter
     */
    private final int margin;

    /**
     * Constructor, we fix the margin to 5 in it
     */
    public GaussianContourExtractorFilter() {
        this.margin = 5;
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
        int blue = (int) Math.max(0, 255 - norm * 0.5);
        int newRgb = blue << 16 | blue << 8 | blue;
        imgOut.setRGB(x - getMargin(), y - getMargin(), newRgb);
    }
}
