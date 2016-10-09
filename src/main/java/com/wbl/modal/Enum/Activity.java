package com.wbl.modal.Enum;

/**
 * Created by Simple_love on 2015/10/27.
 */
public enum Activity {
        DOWNLOAD("DOWNLOAD"),UPLOAD("UPLOAD"),QUERY("QUERY"),LOGIN("LOGIN"),
        AGGREGATION("AGGREGATION"),IMPORT("IMPORT"),EXPORT("EXPORT"),SPLIT("SPLIT");
        private String name;
        private Activity(String name){
                this.name = name;
        }
        public String toString(){
                return this.name();
        }
}
