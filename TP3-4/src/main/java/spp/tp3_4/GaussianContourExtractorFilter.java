package spp.tp3_4;

import java.awt.image.BufferedImage;

public class GaussianContourExtractorFilter implements IFilter {
    private final int margin;

    public GaussianContourExtractorFilter() {
        this.margin = 3;
    }

    @Override
    public int getMargin() {
        return margin;
    }

    @Override
    public void applyFilterAtPoint(int x, int y, BufferedImage imgIn, BufferedImage imgOut) {
        int width = imgIn.getWidth();
        int height = imgIn.getHeight();

        // Make sure the x and y coordinates are within the image bounds
        if (x < margin || x >= width - margin || y < margin || y >= height - margin) {
            return;
        }

        // Compute the weighted sum of pixel values in the neighborhood
        double sum = 0.0;
        double weightSum = 0.0;
        for (int j = -margin; j <= margin; j++) {
            for (int i = -margin; i <= margin; i++) {
                int pixel = imgIn.getRGB(x + i, y + j);
                double weight = getGaussianWeight(i, j, margin);
                sum += weight * getGrayLevel(pixel);
                weightSum += weight;
            }
        }

        // Compute the weighted average of pixel values
        int grayLevel = (int) Math.round(sum / weightSum);

        // Set the pixel value in the output image
        int pixel = getRGB(grayLevel);
        imgOut.setRGB(x, y, pixel);
    }
    private double getGaussianWeight(int x, int y, int sigma) {
        double sigmaSq = sigma * sigma;
        double distanceSq = x * x + y * y;
        double exponent = -distanceSq / (2 * sigmaSq);
        double weight = Math.exp(exponent);
        return weight;
    }

    private int getGrayLevel(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = pixel & 0xff;
        int grayLevel = (red + green + blue) / 3;
        return grayLevel;
    }

    private int getRGB(int grayLevel) {
        int alpha = 0xff << 24;
        int red = grayLevel << 16;
        int green = grayLevel << 8;
        int blue = grayLevel;
        int rgb = alpha | red | green | blue;
        return rgb;
    }
}
