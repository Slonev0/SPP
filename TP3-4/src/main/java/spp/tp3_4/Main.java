package spp.tp3_4;

/**
 * Class that show how to use the image filtering engine
 */
public class Main {
    static public void main(String[] args) throws Exception {
        IImageFilteringEngine im = new MyImageFilteringEngine();
        im.loadImage("./TEST_IMAGES/15226222451_5fd668d81a_c.jpg");
        im.applyFilter(new GrayLevelFilter());
        im.applyFilter(new GaussianContourExtractorFilter());
        im.writeOutPngImage("./output_image/15226222451_5fd668d81a_c_gaussian_contour1.png");

        IImageFilteringEngine im2 = new MultiThreadedImageFilteringEngine(2);
        im2.loadImage("./TEST_IMAGES/15226222451_5fd668d81a_c.jpg");
        im2.applyFilter(new GrayLevelFilter());
        System.out.println("GrayLevelFilter done");
        im2.writeOutPngImage("./output_image/15226222451_5fd668d81a_c_gray_filter_multiple_thread.png");
        im2.loadImage("./output_image/15226222451_5fd668d81a_c_gray_filter_multiple_thread.png");
        im2.applyFilter(new GaussianContourExtractorFilter());
        System.out.println("GaussianContourExtractorFilter done");
        im2.writeOutPngImage("./output_image/15226222451_5fd668d81a_c_gaussian_contour_multiple_thread.png");
    }
}
