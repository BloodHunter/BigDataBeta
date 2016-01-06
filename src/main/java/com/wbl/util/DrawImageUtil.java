package com.wbl.util;

import com.wbl.modal.PlatformNode;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Simple_love on 2015/11/25.
 */
public class DrawImageUtil {
        private static final String CHAR_FOR_JOIN_NODE = "->";
        private static final String NEWLINE = "\n";
        private static final String IMAGE_TYPE = "svg";
        private static String createNode(List<PlatformNode> platformNodes){
                StringBuffer buffer = new StringBuffer();
                for (PlatformNode platformNode:platformNodes){
                        String platformName = platformNode.getPlatfornName();
                        for (String node:platformNode.getLink()){
                                buffer.append(platformName).append(CHAR_FOR_JOIN_NODE).append(node).append(NEWLINE);
                        }
                }
                return buffer.toString();
        }

        /*public static void draw(List<PlatformNode>platformNodes,String filePath){
                File out = new File(filePath);
                draw(platformNodes,out);
        }*/

        public static void draw(List<String> relations,String fileName){
                draw(relations,getPath(fileName));
        }

        /*public static void draw(List<PlatformNode>platformNodes,File out){
                GraphvizUtil gv = new GraphvizUtil();
                gv.add(gv.start_graph());
                gv.add(createNode(platformNodes));
                gv.add(gv.end_graph());
                gv.writeGraphToFile(gv.getGraph(gv.getDotSource(),IMAGE_TYPE),out);
        }*/

        public static void draw(List<String> relations,File out){
                GraphvizUtil gv = new GraphvizUtil();
                gv.add(gv.start_graph());
                for (String line:relations)
                        gv.add(line);
                gv.add(gv.end_graph());
                gv.writeGraphToFile(gv.getGraph(gv.getDotSource(),IMAGE_TYPE),out);
        }

        private static String getPath(String fileName){
                String path = DrawImageUtil.class.getClassLoader().getResource("").getPath();
                int index = path.indexOf("classes");
                path = path.substring(0,index) + "provImg/" + fileName + "." +IMAGE_TYPE;
                path = path.substring(1);
                return path;
        }

        public static void main(String[] args) {
                PlatformNode nodeA = new PlatformNode("platA", Arrays.asList("platB","platF"));
                PlatformNode nodeB = new PlatformNode("platB", Arrays.asList("platC","platD","platE"));
                PlatformNode nodeC = new PlatformNode("platC", Arrays.asList("platF"));
                //draw(Arrays.asList(nodeA,nodeB,nodeC),"C:\\Users\\Lulala\\IdeaProjects\\BigDataBeta\\src\\main\\webapp\\WEB-INF\\provImage\\test1.svg");
        }
}