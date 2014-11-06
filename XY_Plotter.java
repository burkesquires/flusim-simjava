//
/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA.  
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * ---------------------------
 * XY_Plotter.java
 * ---------------------------
 * (C) Copyright 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Burke Squires;
 *
 * Changes
 * -------
 * 02-Feb-2007 : Version 1 (DG);
 * 09-Apr-2008 : Added summation code and plot
 *
 */
package flusim;

import java.awt.Color;
import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.PrintStream;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.time.*;
import org.jfree.data.xy.*;
import org.jfree.ui.*;
import org.jfree.ui.RefineryUtilities;

/**
 * An example of a time series chart.  For the most part, default settings are 
 * used, except that the renderer is modified to show filled shapes (as well as 
 * lines) at each data point.
 */
public class XY_Plotter extends ApplicationFrame {

    /**
     * A demonstration application showing how to create a simple time series 
     * chart.  This example uses monthly data.
     *
     * @param title  the frame title.
     */
    public XY_Plotter(String title, java.util.List[] data, String[] desc) {
        super(title);

        ChartPanel chartPanel = (ChartPanel) createPanel(data, title, desc);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setMouseZoomable(true, false);
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart.
     * 
     * @param dataset  a dataset.
     * 
     * @return A chart.
     */
    private static JFreeChart createChart(XYDataset dataset, String title) {


        JFreeChart chart = ChartFactory.createXYLineChart(
                title, // Title
                "Seconds Post Infection", // x-axis Label
                "Count", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
                );
        try {

            ChartUtilities.saveChartAsPNG(new File(title + "-" + getDateTime() + ".png"), chart, 800, 640);

        } catch (IOException e) {
            System.err.println("Problem occurred creating chart.");
        }

        chart.setBackgroundPaint(Color.white);

        return chart;

    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return The dataset.
     */
    private static XYDataset createDataset(java.util.List[] data, String[] desc) {

        //data is an arraylist of arraylists


        int count = data.length;

        XYSeriesCollection dataset = new XYSeriesCollection();

        for (int i = 0; i < count; i++) {

            XYSeries xys = new XYSeries(desc[i]);

            java.util.List Data = data[i];
            int elements = Data.size();

            for (int j = 0; j < elements; j++) {

                double[] timepoint = (double[]) Data.get(j);

                xys.add(timepoint[0], timepoint[1]);

            }
            dataset.addSeries(xys);
        }

        return dataset;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * 
     * @return A panel.
     */
    public static JPanel createPanel(java.util.List[] data, String title, String[] desc) {
        JFreeChart chart = createChart(createDataset(data, desc), title);
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void PlotXYChart(String title, java.util.List[] data, String[] desc) {

        XY_Plotter plot = new XY_Plotter(title, data, desc);

        plot.pack();

        RefineryUtilities.centerFrameOnScreen(plot);

        plot.setVisible(true);

    }

    private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
