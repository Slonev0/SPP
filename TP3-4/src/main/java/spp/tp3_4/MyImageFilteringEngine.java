package spp.tp3_4;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class MyImageFilteringEngine implements IImageFilteringEngine {
    BufferedImage MyImg;
    BufferedImage outputImg;

    @Override
    public void loadImage(String inputImage) throws Exception {
        MyImg = ImageIO.read(new File(inputImage));
        outputImg = ImageIO.read(new File(inputImage));
    }

    @Override
    public void writeOutPngImage(String outFile) throws Exception {
        File f = new File(outFile);
        ImageIO.write(outputImg, "png", f);
    }

    @Override
    public void setImg(BufferedImage newImg) {
        MyImg = newImg;
        outputImg = MyImg;
    }

    @Override
    public BufferedImage getImg() {
        return MyImg;
    }

    @Override
    public void applyFilter(IFilter someFilter) {
        setOutImg(someFilter.getMargin());
        //this part is here to be sure the output image is in the bounds
        int min = someFilter.getMargin();
        int maxX = MyImg.getWidth() - someFilter.getMargin();
        int maxY = MyImg.getHeight() - someFilter.getMargin();

        for(int x = min; x < maxX; x++){
            for(int y = min; y < maxY;y++){
                someFilter.applyFilterAtPoint(x,y,MyImg,outputImg);
            }
        }
    }

    private void setOutImg(int margin) {
        outputImg = new BufferedImage(MyImg.getWidth() - (margin * 2),
                MyImg.getHeight() - (margin * 2),
                BufferedImage.TYPE_INT_RGB);

    }
}
