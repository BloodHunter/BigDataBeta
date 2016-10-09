package com.wbl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 * Created by Simple_love on 2015/11/25.
 */
public class GraphvizUtil {
        public static final String LINKSIGN = "->";
        private static final String DOT_PATH = "D:\\Graphviz2.38\\bin\\dot.exe";
        private static final String NEWLINE = "\n";
        private static final String TEMP_DIR = "C:\\Users\\Lulala\\IdeaProjects\\BigDataBeta\\src\\main\\webapp\\WEB-INF\\provImage";
        private StringBuffer graph = new StringBuffer();

        public GraphvizUtil(){}

        public String start_graph(){
                return "digraph G {\n" + "concentrate=true\n node[shape=circle,color=\"#2ecc71\",style=filled]\n";
        }

        public String end_graph(){
                return "}";
        }

        public void add(String line){
                graph.append(line).append(NEWLINE);
        }
        /**
         * Returns the graph's source description in dot language.
         * @return Source of the graph in dot language.
         */
        public String getDotSource() {
                return graph.toString();
        }

        private byte[] get_img_stream(File dot, String type)
        {
                File img;
                byte[] img_stream = null;

                try {
                        img = File.createTempFile("graph_", "."+type, new File(TEMP_DIR));
                        Runtime rt = Runtime.getRuntime();

                        // patch by Mike Chenault
                        String[] args = {DOT_PATH, "-T"+type, dot.getAbsolutePath(), "-o", img.getAbsolutePath()};
                        Process p = rt.exec(args);

                        p.waitFor();

                        FileInputStream in = new FileInputStream(img.getAbsolutePath());
                        img_stream = new byte[in.available()];
                        in.read(img_stream);
                        // Close it if we need to
                        if( in != null ) in.close();

                        if (img.delete() == false)
                                System.err.println("Warning: " + img.getAbsolutePath() + " could not be deleted!");
                }
                catch (java.io.IOException ioe) {
                        System.err.println("Error:    in I/O processing of tempfile in dir " + TEMP_DIR+"\n");
                        System.err.println("       or in calling external command");
                        ioe.printStackTrace();
                }
                catch (java.lang.InterruptedException ie) {
                        System.err.println("Error: the execution of the external program was interrupted");
                        ie.printStackTrace();
                }

                return img_stream;
        }

        /**
         * Writes the source of the graph in a file, and returns the written file
         * as a File object.
         * @param str Source of the graph (in dot language).
         * @return The file (as a File object) that contains the source of the graph.
         */
        public File writeDotSourceToFile(String str) throws java.io.IOException
        {
                File temp;
                try {
                        temp = File.createTempFile("graph_", ".dot.tmp", new File(TEMP_DIR));
                        FileWriter fout = new FileWriter(temp);
                        fout.write(str);
                        fout.close();
                }
                catch (Exception e) {
                        System.err.println("Error: I/O error while writing the dot source to temp file!");
                        return null;
                }
                return temp;
        }

        /**
         * Returns the graph as an image in binary format.
         * @param dot_source Source of the graph to be drawn.
         * @param type Type of the output image to be produced, e.g.: gif, dot, fig, pdf, ps, svg, png.
         * @return A byte array containing the image of the graph.
         */
        public byte[] getGraph(String dot_source, String type)
        {
                File dot;
                byte[] img_stream = null;

                try {
                        dot = writeDotSourceToFile(dot_source);
                        if (dot != null)
                        {
                                img_stream = get_img_stream(dot, type);
                                if (dot.delete() == false)
                                        System.err.println("Warning: " + dot.getAbsolutePath() + " could not be deleted!");
                                return img_stream;
                        }
                        return null;
                } catch (java.io.IOException ioe) { return null; }
        }

        /**
         * Writes the graph's image in a file.
         * @param img   A byte array containing the image of the graph.
         * @param file  Name of the file to where we want to write.
         * @return Success: 1, Failure: -1
         */
        public int writeGraphToFile(byte[] img, String file)
        {
                File to = new File(file);
                return writeGraphToFile(img, to);
        }

        /**
         * Writes the graph's image in a file.
         * @param img   A byte array containing the image of the graph.
         * @param to    A File object to where we want to write.
         * @return Success: 1, Failure: -1
         */
        public int writeGraphToFile(byte[] img, File to)
        {
                try {
                        FileOutputStream fos = new FileOutputStream(to);
                        fos.write(img);
                        fos.close();
                } catch (java.io.IOException ioe) {
                        ioe.printStackTrace();
                        return -1;
                }
                return 1;
        }

        public static void main(String[] args) {
                GraphvizUtil gv = new GraphvizUtil();
                gv.add(gv.start_graph());
                //gv.add("platA[URL=\"http://10.103.241.73:8090/BigDataBeta/prov/produceSvgByDotString\"]");
                /*gv.add("platA->platB");
                gv.add("platB->platC");
                gv.add("platB->platD");*/
                gv.add("studentBookLend");
                /*gv.add("CPI->Trend");
                gv.add("Estate->Trend");*/
                //gv.add("Trend");
                gv.add(gv.end_graph());
                gv.writeGraphToFile(gv.getGraph(gv.getDotSource(),"svg"),"F:\\provtest\\test.svg");
                /*GraphvizUtil gv = new GraphvizUtil();
                gv.add(gv.start_graph());
                gv.add("data1->data3");
                gv.add("data2->data3");
                gv.add(gv.end_graph());
                gv.writeGraphToFile(gv.getGraph(gv.getDotSource(),"svg"),"F:\\provtest\\test2.svg");*/
        }
}
