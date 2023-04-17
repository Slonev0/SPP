

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spp.tp3_4.GrayLevelFilter;
import spp.tp3_4.MyImageFilteringEngine;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyImageFilteringEngineTest {

    @Test
    public void testGrayLevelFilterWhiteRectangle() {
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
        BufferedImage outputImg = engine.getImg();

        // assert that output image is grayscale
        for (int x = 0; x < outputImg.getWidth(); x++) {
            for (int y = 0; y < outputImg.getHeight(); y++) {
                Color color = new Color(outputImg.getRGB(x, y));
                assertEquals(color.getRed(), color.getGreen());
                assertEquals(color.getRed(), color.getBlue());
            }
        }
    }

    @Test
    public void testGrayLevelFilterBlackRectangle() {
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
        BufferedImage outputImg = engine.getImg();

        // assert that output image is grayscale
        for (int x = 0; x < outputImg.getWidth(); x++) {
            for (int y = 0; y < outputImg.getHeight(); y++) {
                Color color = new Color(outputImg.getRGB(x, y));
                assertEquals(color.getRed(), color.getGreen());
                assertEquals(color.getRed(), color.getBlue());
            }
        }
    }

    @Test
    public void testGrayLevelFilterColorRectangles() {
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
        BufferedImage outputImg = engine.getImg();

        // assert that output image is grayscale
        for (int x = 0; x < outputImg.getWidth(); x++) {
            for (int y = 0; y < outputImg.getHeight(); y++) {
                Color color = new Color(outputImg.getRGB(x, y));
                assertEquals(color.getRed(), color.getGreen());
                assertEquals(color.getRed(), color.getBlue());
            }
        }
    }
/*
    @Test
    public void testFourCirclesFilters() {
        try {
            String inputImage = "FourCircles.png";
            String expectedOutputGrayImage = "FourCircles_Gray.png";
            String expectedOutputContourImage = "FourCircles_Gray_Contour.png";

            // Load input image and expected output images
            BufferedImage inputImg = ImageIO.read(new File(inputImage));
            BufferedImage expectedGrayImg = ImageIO.read(new File(expectedOutputGrayImage));
            BufferedImage expectedContourImg = ImageIO.read(new File(expectedOutputContourImage));

            // Create image filtering engine
            IImageFilteringEngine engine = new MyImageFilteringEngine();

            // Test GrayLevelFilter
            engine.setImg(inputImg);
            engine.applyFilter(new GrayLevelFilter());
            BufferedImage outputGrayImg = engine.getImg();
            assertEquals("GrayLevelFilter output does not match expected output", expectedGrayImg, outputGrayImg);

            // Test GaussianContourExtractorFilter
            engine.setImg(inputImg);
            engine.applyFilter(new GaussianContourExtractorFilter());
            BufferedImage outputContourImg = engine.getImg();
            assertEquals("GaussianContourExtractorFilter output does not match expected output", expectedContourImg, outputContourImg);

        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("Exception occurred", "", e.getMessage());
        }
    }

    @Test
    public void test15226222451Filters() {
        try {
            String inputImage = "15226222451_5fd668d81a_c.jpg";
            String expectedOutputGrayImage = "15226222451_5fd668d81a_c_Gray.png";
            String expectedOutputContourImage = "15226222451_5fd668d81a_c_Gray_Contour.png";

            // Load input image and expected output images
            BufferedImage inputImg = ImageIO.read(new File(inputImage));
            BufferedImage expectedGrayImg = ImageIO.read(new File(expectedOutputGrayImage));
            BufferedImage expectedContourImg = ImageIO.read(new File(expectedOutputContourImage));

            // Create image filtering engine
            IImageFilteringEngine engine = new MyImageFilteringEngine();

            // Test GrayLevelFilter
            engine.setImg(inputImg);
            engine.applyFilter(new GrayLevelFilter());
            BufferedImage outputGrayImg = engine.getImg();
            assertEquals("GrayLevelFilter output does not match expected output", expectedGrayImg, outputGrayImg);

            // Test GaussianContourExtractorFilter
            engine.setImg(inputImg);
            engine.applyFilter(new GaussianContourExtractorFilter());
            BufferedImage outputContourImg = engine.getImg();
            assertEquals("GaussianContourExtractorFilter output does not match expected output", expectedContourImg, outputContourImg);

        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("Exception occurred", "", e.getMessage());
        }
    }*/

}
