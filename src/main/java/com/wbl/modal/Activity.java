package com.wbl.modal;

/**
 * Created by Simple_love on 2015/10/27.
 */
public enum Activity {
        DOWNLOAD("DOWNLOAD"),UPLOAD("UPLOAD"),
        AGGREGATION("AGGREGATION"),IMPORT("IMPORT"),EXPORT("EXPORT");
        private String name;
        private Activity(String name){
                this.name = name;
        }
        public String toString(){
                return this.name();
        }
}
