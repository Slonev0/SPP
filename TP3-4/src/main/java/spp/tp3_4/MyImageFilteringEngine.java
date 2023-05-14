package spp.tp3_4;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Class that implements a simple image filtering engine
 */
public class MyImageFilteringEngine implements IImageFilteringEngine {
    /**
     * Input image
     */
    BufferedImage MyImg;
    /**
     * Output image
     */
    BufferedImage outputImg;

    /**
     * Method to load an image and set the input and output image the parameter image
     * @param inputImage the path to the image to read
     * @throws Exception if the image cannot be read
     */
    @Override
    public void loadImage(String inputImage) throws Exception {
        MyImg = ImageIO.read(new File(inputImage));
        outputImg = ImageIO.read(new File(inputImage));
    }

    /**
     * Method to write the output image to a file
     * @param outFile the path to the file to write
     * @throws Exception if the image cannot be written
     */
    @Override
    public void writeOutPngImage(String outFile) throws Exception {
        File f = new File(outFile);
        ImageIO.write(outputImg, "png", f);
    }

    /**
     * Method to set the input image
     * @param newImg the new input image
     */
    @Override
    public void setImg(BufferedImage newImg) {
        MyImg = newImg;
        outputImg = MyImg;
    }

    /**
     * Method to get the input image
     * @return the input image
     */
    @Override
    public BufferedImage getImg() {
        return MyImg;
    }

    /**
     * Method to apply a filter to the input image and set the output image
     * @param someFilter the filter to apply
     */
    @Override
    public void applyFilter(IFilter someFilter) {
        setOutImg(someFilter.getMargin());

        int min = someFilter.getMargin();
        int maxX = MyImg.getWidth() - someFilter.getMargin();
        int maxY = MyImg.getHeight() - someFilter.getMargin();

        for(int x = min; x < maxX; x++){
            for(int y = min; y < maxY;y++){
                someFilter.applyFilterAtPoint(x,y,MyImg,outputImg);
            }
        }
    }

    /**
     * Method to set the output image to a new image with the same size as the input image
     * @param margin the margin of the filter
     */
    private void setOutImg(int margin) {
        outputImg = new BufferedImage(MyImg.getWidth() - (margin * 2),
                MyImg.getHeight() - (margin * 2),
                BufferedImage.TYPE_INT_RGB);

    }
}
