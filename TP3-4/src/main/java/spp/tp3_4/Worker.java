package spp.tp3_4;

import java.awt.image.BufferedImage;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Class that implements a worker (thread) for the multithreaded image filtering engine
 */
public class Worker extends Thread{
    /**
     * Start X coordinate of the worker
     */
    private int startX;
    /**
     * Start Y coordinate of the worker
     */
    private int startY;
    /**
     * End X coordinate of the worker
     */
    private int endX;
    /**
     * End Y coordinate of the worker
     */
    private int endY;
    /**
     * Width of the image
     */
    private int width;
    /**
     * Height of the image
     */
    private int height;
    /**
     * Filter to apply
     */
    private IFilter filter;
    /**
     * Input image
     */
    private BufferedImage MyImg;
    /**
     * Output image
     */
    private BufferedImage outputImg;
    /**
     * Start barrier
     */
    private CyclicBarrier startBarrier;
    /**
     * End barrier
     */
    private CyclicBarrier endBarrier;

    /**
     * Constructor for the worker
     * @param startBarrier the start barrier
     * @param endBarrier the end barrier
     */
    public Worker(CyclicBarrier startBarrier, CyclicBarrier endBarrier) {
        this.startBarrier = startBarrier;
        this.endBarrier = endBarrier;
    }

    /**
     * Method that runs the worker
     */
    @Override
    public void run() {
        int min = filter.getMargin();
        if (startX < min) {
            startX = min;
        }
        if (startY < min) {
            startY = min;
        }
        int maxX = MyImg.getWidth() - filter.getMargin();
        if (endX > maxX) {
            endX = maxX;
        }
        int maxY = MyImg.getHeight() - filter.getMargin();
        if (endY > maxY) {
            endY = maxY;
        }

        try {
            startBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        for(int x = startX; x < endX; x++){
            for(int y = startY; y < endY;y++) {
                filter.applyFilterAtPoint(x, y, MyImg, outputImg);
            }
        }

        try {
            endBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that returns the end X coordinate
     * @return the end X coordinate
     */
    public int getEndX() {
        return endX;
    }

    /**
     * Method that sets the end X coordinate
     * @param end the end X coordinate
     */
    public void setEndX(int end) {
        this.endX = end;
    }

    /**
     * Method that returns the end Y coordinate
     * @return the end Y coordinate
     */
    public int getEndY() {
        return endY;
    }

    /**
     * Method that sets the end Y coordinate
     * @param end the end Y coordinate
     */
    public void setEndY(int end) {
        this.endY = end;
    }

    /**
     * method that returns the width of the image to filter
     * @return the width of the image to filter
     */
    public int getWidth() {
        return width;
    }

    /**
     * Method that sets the width of the image to filter
     * @param width the width of the image to filter
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Method that returns the height of the image to filter
     * @return the height of the image to filter
     */
    public int getHeight() {
        return height;
    }

    /**
     * Method that sets the height of the image to filter
     * @param height the height of the image to filter
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Method that returns the filter to apply
     * @return the filter to apply
     */
    public IFilter getFilter() {
        return filter;
    }

    /**
     * Method that sets the filter to apply
     * @param filter the filter to apply
     */
    public void setFilter(IFilter filter) {
        this.filter = filter;
    }

    /**
     * Method that returns the input image
     * @return the input image
     */
    public BufferedImage getMyImg() {
        return MyImg;
    }

    /**
     * Method that sets the input image
     * @param myImg the input image
     */
    public void setMyImg(BufferedImage myImg) {
        MyImg = myImg;
    }

    /**
     * Method that returns the output image
     * @return the output image
     */
    public BufferedImage getOutputImg() {
        return outputImg;
    }

    /**
     * Method that sets the output image
     * @param outputImg the output image
     */
    public void setOutputImg(BufferedImage outputImg) {
        this.outputImg = outputImg;
    }

    /**
     * Method that returns the start X coordinate
     * @return the start X coordinate
     */
    public int getStartX() {
        return startX;
    }

    /**
     * Method that sets the start X coordinate
     * @param start the start X coordinate
     */
    public void setStartX(int start) {
        this.startX = start;
    }

    /**
     * Method that returns the start Y coordinate
     * @return the start Y coordinate
     */
    public int getStartY() {
        return startY;
    }

    /**
     * Method that sets the start Y coordinate
     * @param start the start Y coordinate
     */
    public void setStartY(int start) {
        this.startY = start;
    }
}
