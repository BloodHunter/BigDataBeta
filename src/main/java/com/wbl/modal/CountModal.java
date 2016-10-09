package com.wbl.modal;

/**
 * Created with Simple_love
 * Date: 2016/3/29.
 * Time: 11:21
 */
public class CountModal {
        private String name;
        private int count;

        public CountModal(){}
        public CountModal(String name, int count) {
                this.name = name;
                this.count = count;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getCount() {
                return count;
        }

        public void setCount(int count) {
                this.count = count;
        }

        @Override
        public String toString() {
                return "CountModal{" +
                        "name='" + name + '\'' +
                        ", count=" + count +
                        '}';
        }
}
