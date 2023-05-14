package spp.tp3_4;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Class that implements a multithreaded image filtering engine
 */
public class MultiThreadedImageFilteringEngine implements IImageFilteringEngine {

    /**
     * Number of threads to use
     */
    private final int numThreads;
    /**
     * Input image
     */
    private BufferedImage inputImage;
    /**
     * Output image
     */
    private BufferedImage outputImage;
    /**
     * List of workers
     */
    private List<Worker> workers;
    /**
     * Start barriers
     */
    public CyclicBarrier startBarrier;
    /**
     * End barriers
     */
    public CyclicBarrier endBarrier;

    /**
     * Constructor for the multithreaded image filtering engine
     * @param numThreads the number of threads to use
     */
    public MultiThreadedImageFilteringEngine(int numThreads) {
        super();
        this.numThreads = numThreads;
        startBarrier = new CyclicBarrier(numThreads + 1);
        endBarrier = new CyclicBarrier(numThreads + 1);
    }

    /**
     * Method that loads the image to read and set the output image to the same image
     * @param inputImage the path to the image to read
     * @throws Exception if the image cannot be read
     */
    @Override
    public void loadImage(String inputImage) throws Exception {
        this.inputImage = ImageIO.read(new File(inputImage));
        outputImage = this.inputImage;
    }

    /**
     * Method that sets the output image to a new image
     * @param outFile the path to the image to write
     * @throws Exception if the image cannot be written
     */
    @Override
    public void writeOutPngImage(String outFile) throws Exception {
        File f = new File(outFile);
        ImageIO.write(outputImage, "png", f);
    }

    /**
     * Method that set the input image to a new image and set the output image to a new image with the same size
     * @param newImg the new image
     */
    @Override
    public void setImg(BufferedImage newImg) {
        inputImage = newImg;
        outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    /**
     * Getter of the input image
     * @return the input image
     */
    @Override
    public BufferedImage getImg() {
        return inputImage;
    }

    /**
     * Method that splits the image for the workers and set the filter and the start and end coordinates for each worker
     * @param someFilter the filter to apply
     * @throws InterruptedException if the thread is interrupted
     */
    private void setUpWorkers(IFilter someFilter) throws InterruptedException {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        int pixelsPerWorker = width / numThreads;
        int extraPixels = width % numThreads;

        int startX = 0;
        int startY = 0;

        for (Worker worker : workers){
            worker.setFilter(someFilter);
            worker.setMyImg(inputImage);
            worker.setOutputImg(outputImage);

            worker.setStartX(startX);
            worker.setStartY(startY);


            int endX = startX + pixelsPerWorker;
            if (extraPixels > 0) {
                worker.setEndX(Math.min(endX + 1, width));
            }
            else {
                worker.setEndX(Math.min(endX, width));
            }

            int endY = startY + height;
            worker.setEndY(endY);

            extraPixels--;
            startX = endX;
            if (startX >= width) {
                startX = 0;
                startY += height;
            }
        }
        workers.get(workers.size()-1).setEndX(width);
        workers.get(workers.size()-1).setEndY(height);
    }


    /**
     * Method that applies the filter to the image
     * @param someFilter the filter to apply
     * @throws InterruptedException if the thread is interrupted
     */
    @Override
    public void applyFilter(IFilter someFilter) throws InterruptedException {
        setOutImg(someFilter.getMargin());

        initWorkers();
        setUpWorkers(someFilter);

        for (Thread worker : workers) {
            worker.start();
        }
        try {
            startBarrier.await();
            endBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that initializes the list of workers
     */
    private void initWorkers(){
        workers = new ArrayList<>(numThreads);
        for (int i = 0; i < numThreads; i++) {
            workers.add(new Worker(startBarrier, endBarrier));
        }
    }

    /**
     * Method that sets the output image to a new image with a margin
     * @param margin the margin to apply
     */
    private void setOutImg(int margin) {
        outputImage = new BufferedImage(inputImage.getWidth() - (margin * 2),
                inputImage.getHeight() - (margin * 2),
                BufferedImage.TYPE_INT_RGB);

    }
}
