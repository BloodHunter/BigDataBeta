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
        private String path = "provImage.jpg";
        private List<Prov> provs;
        public DrawProvImage(String path,List<Prov> provs){
                this.path = path;
                this.provs = provs;
        }
        public DrawProvImage(List<Prov> provs){
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
                record.doConversions(document, path);
                record.closingBanner();
        }

        public static void main(String[] args){

        }
}
