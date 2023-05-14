

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spp.tp3_4.GaussianContourExtractorFilter;
import spp.tp3_4.GrayLevelFilter;
import spp.tp3_4.MyImageFilteringEngine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyImageFilteringEngineTest {

    @Test
    public void testGrayLevelFilterWhiteRectangle() throws IOException {
        // create white rectangle image
        BufferedImage img = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
        g.dispose();

        // apply GrayLevelFilter
        MyImageFilteringEngine engine = new MyImageFilteringEngine();
        engine.setImg(img);
        engine.applyFilter(new GrayLevelFilter());
        try {
            engine.writeOutPngImage("./TEST_IMAGES/WhiteRectangleGray.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedImage outputImage = ImageIO.read(
                new File("./TEST_IMAGES/WhiteRectangleGray.png"));

        // assert that output image is grayscale
        for (int x = 0; x < outputImage.getWidth(); x++) {
            for (int y = 0; y < outputImage.getHeight(); y++) {
                Color color = new Color(outputImage.getRGB(x, y));
                assertEquals(color.getRed(), color.getGreen());
                assertEquals(color.getRed(), color.getBlue());
            }
        }
    }

    @Test
    public void testGrayLevelFilterBlackRectangle() throws IOException {
        // create black rectangle image
        BufferedImage img = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
        g.dispose();

        // apply GrayLevelFilter
        MyImageFilteringEngine engine = new MyImageFilteringEngine();
        engine.setImg(img);
        engine.applyFilter(new GrayLevelFilter());
        try {
            engine.writeOutPngImage("./TEST_IMAGES/BlackRectangleGray.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedImage outputImage = ImageIO.read(
                new File("./TEST_IMAGES/BlackRectangleGray.png"));

        // assert that output image is grayscale
        for (int x = 0; x < outputImage.getWidth(); x++) {
            for (int y = 0; y < outputImage.getHeight(); y++) {
                Color color = new Color(outputImage.getRGB(x, y));
                assertEquals(color.getRed(), color.getGreen());
                assertEquals(color.getRed(), color.getBlue());
            }
        }
    }

    @Test
    public void testGrayLevelFilterColorRectangles() throws IOException {
        // create image with color rectangles
        BufferedImage img = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.RED);
        g.fillRect(0, 0, 64, 256);
        g.setColor(Color.GREEN);
        g.fillRect(64, 0, 64, 256);
        g.setColor(Color.BLUE);
        g.fillRect(128, 0, 64, 256);
        g.dispose();

        // apply GrayLevelFilter
        MyImageFilteringEngine engine = new MyImageFilteringEngine();
        engine.setImg(img);
        engine.applyFilter(new GrayLevelFilter());
        try {
            engine.writeOutPngImage("./TEST_IMAGES/ColoredRectangesGrayTest.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedImage outputImage = ImageIO.read(
                new File("./TEST_IMAGES/ColoredRectangesGrayTest.png"));

        // assert that output image is grayscale
        for (int x = 0; x < outputImage.getWidth(); x++) {
            for (int y = 0; y < outputImage.getHeight(); y++) {
                Color color = new Color(outputImage.getRGB(x, y));
                Assert.assertEquals("look on "+ x +", "+ y,color.getRed(), color.getGreen());
                Assert.assertEquals("look on "+ x +", "+ y,color.getRed(), color.getBlue());
            }
        }
    }

    @org.junit.Test
    public void testGrayLevelFilter_FourCircles() throws IOException {

        BufferedImage inputImage = ImageIO.read(new File("./TEST_IMAGES/FourCircles.png"));
        BufferedImage expectedOutputImage = ImageIO.read(
                new File("./TEST_IMAGES/FourCircles_gray.png"));
        MyImageFilteringEngine engine = new MyImageFilteringEngine();
        engine.setImg(inputImage);
        engine.applyFilter(new GrayLevelFilter());

        try {
            engine.writeOutPngImage("./TEST_IMAGES/FourCircles_gray_TEST.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedImage actualOutputImage = ImageIO.read(
                new File("./TEST_IMAGES/FourCircles_gray_TEST.png"));

        assert_equal(expectedOutputImage, actualOutputImage);
    }

    @Test
    public void testGrayLevelFilter_SecondImage() throws IOException {

        BufferedImage inputImage = ImageIO.read(new File("./TEST_IMAGES/15226222451_5fd668d81a_c.jpg"));
        BufferedImage expectedOutputImage = ImageIO.read(new File("./TEST_IMAGES/15226222451_5fd668d81a_c_Gray.png"));
        MyImageFilteringEngine engine = new MyImageFilteringEngine();
        engine.setImg(inputImage);
        engine.applyFilter(new GrayLevelFilter());

        try {
            engine.writeOutPngImage("./TEST_IMAGES/15226222451_5fd668d81a_c_Gray_TEST.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedImage actualOutputImage = ImageIO.read(
                new File("./TEST_IMAGES/15226222451_5fd668d81a_c_Gray_TEST.png"));

        assert_equal(expectedOutputImage, actualOutputImage);
    }


    @Test
    public void testGaussianContour_FourCircles() throws IOException {

        BufferedImage inputImage = ImageIO.read(new File("./TEST_IMAGES/FourCircles.png"));
        BufferedImage expectedOutputImage = ImageIO.read(new File("./TEST_IMAGES/FourCircles_gaussian_contour.png"));
        MyImageFilteringEngine engine = new MyImageFilteringEngine();
        engine.setImg(inputImage);
        engine.applyFilter(new GrayLevelFilter());
        engine.applyFilter(new GaussianContourExtractorFilter());

        try {
            engine.writeOutPngImage("./TEST_IMAGES/FourCircles_Gaussian_Contour_TEST.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedImage actualOutputImage = ImageIO.read(
                new File("./TEST_IMAGES/FourCircles_Gaussian_Contour_TEST.png"));

        assert_equal(expectedOutputImage, actualOutputImage);
    }

    @Test
    public void testGaussianContour_SecondImage() throws IOException {

        BufferedImage inputImage = ImageIO.read(new File("./TEST_IMAGES/15226222451_5fd668d81a_c.jpg"));
        BufferedImage expectedOutputImage = ImageIO.read(
                new File("./TEST_IMAGES/15226222451_5fd668d81a_c_Gaussian_Contour.png"));
        MyImageFilteringEngine engine = new MyImageFilteringEngine();
        engine.setImg(inputImage);
        engine.applyFilter(new GrayLevelFilter());
        engine.applyFilter(new GaussianContourExtractorFilter());

        try {
            engine.writeOutPngImage("./TEST_IMAGES/15226222451_5fd668d81a_c_Gray_Contour_TEST.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedImage actualOutputImage = ImageIO.read(
                new File("./TEST_IMAGES/15226222451_5fd668d81a_c_Gray_Contour_TEST.png"));

        assert_equal(expectedOutputImage, actualOutputImage);
    }

    private void assert_equal(BufferedImage expected, BufferedImage actual) {

        for (int y = 0; y < expected.getHeight(); y++) {
            for (int x = 0; x < expected.getWidth(); x++) {
                Assert.assertEquals("look on "+ x +", "+ y,Integer.toHexString(expected.getRGB(x, y)), Integer.toHexString(actual.getRGB(x, y)));
            }
        }
    }
}


