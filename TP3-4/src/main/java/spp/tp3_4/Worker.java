package spp.tp3_4;

import java.awt.image.BufferedImage;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Worker extends Thread{
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int width;
    private int height;
    private int[] pixels;
    private IFilter filter;
    private BufferedImage MyImg;
    private BufferedImage outputImg;
    private CyclicBarrier barrier;


    public Worker(int nb_Thread, CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        for(int x = startX; x < endX; x++){
            for(int y = startY; y < endY;y++){
                System.out.println("filter apply at point ("+x+","+y+")");
                filter.applyFilterAtPoint(x,y,MyImg,outputImg);
            }
        }
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int end) {
        this.endX = end;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int end) {
        this.endY = end;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[] getPixels() {
        return pixels;
    }

    public void setPixels(int[] pixels) {
        this.pixels = pixels;
    }

    public IFilter getFilter() {
        return filter;
    }

    public void setFilter(IFilter filter) {
        this.filter = filter;
    }

    public BufferedImage getMyImg() {
        return MyImg;
    }

    public void setMyImg(BufferedImage myImg) {
        MyImg = myImg;
    }

    public BufferedImage getOutputImg() {
        return outputImg;
    }

    public void setOutputImg(BufferedImage outputImg) {
        this.outputImg = outputImg;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int start) {
        this.startX = start;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int start) {
        this.startY = start;
    }
}
