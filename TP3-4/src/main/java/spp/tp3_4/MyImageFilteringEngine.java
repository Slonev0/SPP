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
    }

    @Override
    public void writeOutPngImage(String outFile) throws Exception {
        File f = new File(outFile);
        ImageIO.write(outputImg, "png", f);
    }

    @Override
    public void setImg(BufferedImage newImg) {
        MyImg = newImg;
    }

    @Override
    public BufferedImage getImg() {
        return MyImg;
    }

    @Override
    public void applyFilter(IFilter someFilter) {
        //someFilter.applyFilterAtPoint(MyImg.getWidth(),MyImg.getHeight(),MyImg,outputImg);
    }
}
