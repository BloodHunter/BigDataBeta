package com.wbl.modal.Enum;

/**
 * Created by Simple_love on 2015/11/1.
 */
public enum ResultType {
        SUCCESS("SUCCESS"),ERROR("ERROR"),RESULT("RESULT");
        private final String text;
        private ResultType(String text){
                this.text = text;
        }

        @Override
        public String toString() {
                return this.text;
        }

        public boolean equals(String text){
                return this.text.equals(text);
        }
}
