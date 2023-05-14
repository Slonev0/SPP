package spp.tp3_4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.data.statistics.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Class that contains the experiment about the execution time of the filters with different number of threads
 */
public class Experiment {
    public static void main(String[] args) throws Exception {
        Object[] result = experiment_one_gray();
        plotExperiment((double[][]) result[0], (String[]) result[1], "Graphique de l'execution du filtre gray selon le " +
                "nombre de threads");
        System.out.println("Experiment one gray done");
        result = experiment_one_gaussian();
        plotExperiment((double[][]) result[0], (String[]) result[1], "Graphique de l'execution du filtre gaussian selon le " +
                "nombre de threads");
        System.out.println("Experiment one gaussian done");
        result = experiment_size_grey();
        plotExperiment((double[][]) result[0], (String[]) result[1], "Graphique de la taille d'execution du filtre " +
                "gray selon le nombre de pixel pour 4 threads");
        System.out.println("Experiment size grey done");
        result = experiment_size_gaussian();
        plotExperiment((double[][]) result[0], (String[]) result[1], "Graphique de la taille d'execution du filtre " +
                "gaussian selon le nombre de pixel pour 4 threads");
        System.out.println("Experiment size gaussian done");
    }

    /**
     * Experiment about the execution time of the gray filter with different number of threads
     * @return Object[] containing the data to plot, Object[0] is a double[][] containing the data,
     * Object[1] is a String[] containing the labels
     * @throws Exception
     */
    private static Object[] experiment_one_gray() throws Exception {
        double[][] tab = new double[10][11];
        String[] xLabel = new String[14];
        xLabel[12] = "Nombre de threads";
        xLabel[13] = "Temps en ms";

        BufferedImage inputImage = ImageIO.read(new File("./TEST_IMAGES/15226222451_75d515f540_o.jpg"));

        for(int i = 0; i < 10; i++) {
            MyImageFilteringEngine engine_solo = new MyImageFilteringEngine();
            engine_solo.setImg(inputImage);
            double start_solo = System.nanoTime();
            engine_solo.applyFilter(new GrayLevelFilter());
            double end_solo = System.nanoTime();
            tab[i][0] = (end_solo - start_solo) / 1000000;
            xLabel[0] = "0 Threads";
            for (int k = 1; k < 11; k++) {
                xLabel[k] = k + " Threads";
                MultiThreadedImageFilteringEngine engine = new MultiThreadedImageFilteringEngine(k);
                engine.setImg(inputImage);
                double start = System.nanoTime();
                engine.applyFilter(new GrayLevelFilter());
                double end = System.nanoTime();
                tab[i][k] = (end - start) / 1000000;
            }
        }
        Object[] result = new Object[2];
        result[0] = tab;
        result[1] = xLabel;
        return result;
    }

    /**
     * Experiment about the execution time of the gaussian filter with different number of threads
     * @return Object[] containing the data to plot, Object[0] is a double[][] containing the data,
     * Object[1] is a String[] containing the labels
     * @throws Exception
     */
    private static Object[] experiment_one_gaussian() throws Exception {
        double[][] tab = new double[10][11];
        BufferedImage inputImage = ImageIO.read(new File("./TEST_IMAGES/15226222451_75d515f540_o.jpg"));
        String[] xLabel = new String[14];
        xLabel[12] = "Nombre de threads";
        xLabel[13] = "Temps en ms";

        for(int i = 0; i < 10; i++) {
            MyImageFilteringEngine engine_solo = new MyImageFilteringEngine();
            engine_solo.setImg(inputImage);
            double start_solo = System.nanoTime();
            engine_solo.applyFilter(new GaussianContourExtractorFilter());
            double end_solo = System.nanoTime();
            tab[i][0] = (end_solo - start_solo) / 1000000;
            xLabel[0] ="0 Threads";
            for (int k = 1; k < 11; k++) {
                xLabel[k] = k + " Threads";
                MultiThreadedImageFilteringEngine engine = new MultiThreadedImageFilteringEngine(k);
                engine.setImg(inputImage);
                double start = System.nanoTime();
                engine.applyFilter(new GaussianContourExtractorFilter());
                double end = System.nanoTime();
                tab[i][k] = (end - start) / 1000000;
            }
        }
        Object[] result = new Object[2];
        result[0] = tab;
        result[1] = xLabel;
        return result;
    }

    /**
     * Experiment about the execution time of the gray filter with different number of pixels
     * @return Object[] containing the data to plot, Object[0] is a double[][] containing the data,
     * Object[1] is a String[] containing the labels
     * @throws Exception
     */
    private static Object[] experiment_size_grey() throws Exception{
        String[] pictures = {"5fd668d81a_t", "5fd668d81a_m", "5fd668d81a_n", "5fd668d81a", "5fd668d81a_z",
                "5fd668d81a_c", "a49b1a624b_h", "75d515f540_o"};
        String[] xLabel = new String[11];
        xLabel[9] = "Nombre de pixel contenu dans l'image";
        xLabel[10] = "Temps en ms";

        double[][] tab = new double[10][8];
        MultiThreadedImageFilteringEngine engine = new MultiThreadedImageFilteringEngine(4);
        for (int i = 0; i<pictures.length; i++){
            BufferedImage inputImage = ImageIO.read(new File("./TEST_IMAGES/15226222451_"+pictures[i]+".jpg"));
            xLabel[i] = String.valueOf(inputImage.getWidth() * inputImage.getHeight());
            for (int j = 0; j<10; j++){
                engine.setImg(inputImage);
                double start = System.nanoTime();
                engine.applyFilter(new GrayLevelFilter());
                double end = System.nanoTime();
                tab[j][i] = (end - start) / 1000000;
            }
        }
        Object[] result = new Object[2];
        result[0] = tab;
        result[1] = xLabel;
        return result;
    }

    /**
     * Experiment about the execution time of the gaussian filter with different number of pixels
     * @return Object[] containing the data to plot, Object[0] is a double[][] containing the data,
     * Object[1] is a String[] containing the labels
     * @throws Exception
     */
    private static Object[] experiment_size_gaussian() throws Exception{
        String[] pictures = {"5fd668d81a_t", "5fd668d81a_m", "5fd668d81a_n", "5fd668d81a", "5fd668d81a_z",
                "5fd668d81a_c", "a49b1a624b_h", "75d515f540_o"};
        String[] xLabel = new String[11];
        xLabel[9] = "Nombre de pixel contenu dans l'image";
        xLabel[10] = "Temps en ms";

        double[][] tab = new double[10][8];
        MultiThreadedImageFilteringEngine engine = new MultiThreadedImageFilteringEngine(4);
        for (int i = 0; i<pictures.length; i++){
            BufferedImage inputImage = ImageIO.read(new File("./TEST_IMAGES/15226222451_"+pictures[i]+".jpg"));
            xLabel[i] = String.valueOf(inputImage.getWidth() * inputImage.getHeight());
            for (int j = 0; j < 10; j++){
                engine.setImg(inputImage);
                double start = System.nanoTime();
                engine.applyFilter(new GaussianContourExtractorFilter());
                double end = System.nanoTime();
                tab[j][i] = (end - start) / 1000000;
            }
        }
        Object[] result = new Object[2];
        result[0] = tab;
        result[1] = xLabel;
        return result;
    }

    /**
     * Method to plot the result of an experiment
     * @param tab the data to plot
     * @param xLabel the labels of the x-axis (and the title of yLabel)
     * @param title the title of the plot
     */
    private static void plotExperiment(double[][] tab, String[] xLabel,  String title){
        double[] means = calculateMeans(tab);
        double[] ecartType = getEcartType(tab, means);
        final double z = 1.96;

        double[][] intervalleConfiance = new double[ecartType.length][2];
        double[] marginOfError = new double[ecartType.length];
        for (int i = 0; i < ecartType.length; i++) {
            double margeErreur = z * ecartType[i] / Math.sqrt(tab.length);
            marginOfError[i] = margeErreur;
            intervalleConfiance[i][0] = means[i] - margeErreur;
            intervalleConfiance[i][1] = means[i] + margeErreur;
        }

        // Création du dataset à partir du tableau de valeurs
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < tab[0].length; i++) {
            dataset.addValue(means[i], "Boîte à moustache", "Catégorie " + i);
        }

        // Création du dataset à partir des valeurs et des intervalles de confiance
        DefaultBoxAndWhiskerCategoryDataset dataset_moustache = new DefaultBoxAndWhiskerCategoryDataset();
        for (int i = 0; i < means.length; i++) {
            List<Double> valuesList = new ArrayList<>();
            for (int j = 0; j < tab.length; j++) {
                valuesList.add(tab[j][i]);
            }
            List<Double> outliers = new ArrayList<>(); // Ajoutez les valeurs des éventuels outliers ici
            dataset_moustache.add(valuesList, "Boîte à moustache",  xLabel[i]);
            dataset_moustache.add(BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(valuesList), "Boîte à moustache", xLabel[i]);
        }
        // Création du graphique à partir du dataset
        JFreeChart chart2 = ChartFactory.createBoxAndWhiskerChart(title, xLabel[xLabel.length-2], xLabel[xLabel.length-1],
                dataset_moustache, true);

        CategoryPlot plot = chart2.getCategoryPlot();
        for(int i = 0; i< means.length; i++){
            // Création de l'annotation
            BoxAndWhiskerItem item = dataset_moustache.getItem(0, i);
            CategoryTextAnnotation annotation = new CategoryTextAnnotation(
                    "Mean: " + means[i] + "\n" +
                            "Confidence Interval: [" + intervalleConfiance[i][0] + ", " + intervalleConfiance[i][0] + "]\n" +
                            "Margin of Error: " + marginOfError[i], dataset.getColumnKey(0), means[i]);
            //annotation.setFont(new Font("SansSerif", Font.PLAIN, 10));
            //plot.addAnnotation(annotation);
        }
        // Affichage du graphique dans une fenêtre
        JFrame frame = new JFrame("Graphique à barres avec intervalles de confiance");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChartPanel chartPanel = new ChartPanel(chart2);
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
    /**
     * Calcule le tableau moyen de plusieurs tableaux de valeurs.
     *
     * @param values un tableau de tableaux de valeurs
     * @return le tableau moyen
     */
    private static double[] calculateMeans(double[][] values) {
        double[] means = new double[values[0].length];
        for (int i = 0; i < values[0].length; i++) {
            means[i] = mean_multiplevalues(values, i);
        }
        return means;
    }

    /**
     * Calcule la moyenne d'une colonne d'un tableau de valeurs.
     * @param values un tableau de tableaux de valeurs
     * @param i l'indice de la colonne
     * @return
     */
    private static double mean_multiplevalues(double[][] values, int i) {
        double sum = 0;
        for (int j = 0; j < values.length; j++) {
            sum += values[j][i];
        }
        return sum / values.length;
    }

    /**
     * Calcule le tableau des écarts-types de plusieurs tableaux de valeurs.
     * @param values un tableau de tableaux de valeurs
     * @param mean le tableau moyen
     * @return le tableau des écarts-types
     */
    private static double[] getEcartType(double[][] values, double[] mean){
        double[] ecartType = new double[values[0].length];
        for (int i = 0; i < values[0].length; i++) {
            ecartType[i] = ecartType_multiplevalues(values, i, mean[i]);
        }
        return ecartType;
    }

    /**
     * Calcule l'écart-type d'une colonne d'un tableau de valeurs.
     * @param values un tableau de tableaux de valeurs
     * @param i l'indice de la colonne
     * @param mean la moyenne de la colonne
     * @return l'écart-type de la colonne
     */
    private static double ecartType_multiplevalues(double[][] values, int i, double mean) {
        double sum = 0;
        for (int j = 0; j < values.length; j++) {
            sum += Math.pow(values[j][i] - mean, 2);
        }
        return Math.sqrt(sum / values.length);
    }

}
