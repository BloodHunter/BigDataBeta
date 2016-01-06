package com.wbl.util;

import com.wbl.domain.Prov;
import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.model.Document;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.List;

/**
 * Created by Simple_love on 2015/10/26.
 */
public class DrawProvImage {
        private String path = null;
        private List<Prov> provs;
        public DrawProvImage(String path,List<Prov> provs){
                //this.path = getPath() + path;
                this.path = path;
                this.provs = provs;
        }
        public DrawProvImage(List<Prov> provs){
                path = getPath();
                this.provs = provs;
        }

        public void draw(){
                ProvRecord record=new ProvRecord(InteropFramework.newXMLProvFactory());
                record.openingBanner();
                Document document = null;
                try {
                        document = record.makeDocument(provs);
                } catch (DatatypeConfigurationException e) {
                        e.printStackTrace();
                }
                System.out.println(path);
                record.doConversions(document, path);
                record.closingBanner();
        }

        private static String getPath(){
                String path = DrawProvImage.class.getClassLoader().getResource("").getPath().substring(1);
                return path.substring(0,path.indexOf("classes")) + "provImage/";
        }

        public static void main(String[] args){
                String path = DrawProvImage.class.getClassLoader().getResource("").getPath();
                System.out.println(path.substring(0,path.indexOf("classes")));

        }
}
