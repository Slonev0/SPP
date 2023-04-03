package spp.tp3_4;

public class Main {
    static public void main(String[] args) throws Exception {
        IImageFilteringEngine im = new MyImageFilteringEngine();
        im.loadImage("./TEST_IMAGES/15226222451_5fd668d81a_c.jpg");
        im.applyFilter(new GrayLevelFilter());
        im.applyFilter(new GaussianContourExtractorFilter());
        im.writeOutPngImage("./output_image/15226222451_5fd668d81a_c_gaussian_contour.png");
    }
}
