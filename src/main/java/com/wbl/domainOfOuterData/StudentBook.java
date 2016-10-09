package com.wbl.domainOfOuterData;

/**
 * Created with Simple_love
 * Date: 2016/4/28.
 * Time: 16:03
 */
public class StudentBook {
        private String sId;
        private String name;
        private String bookName;
        private String academy;

        public StudentBook() {
        }

        public String getsId() {
                return sId;
        }

        public void setsId(String sId) {
                this.sId = sId;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getBookName() {
                return bookName;
        }

        public void setBookName(String bookName) {
                this.bookName = bookName;
        }

        public String getAcademy() {
                return academy;
        }

        public void setAcademy(String academy) {
                this.academy = academy;
        }

        @Override
        public String toString() {
                return "StudentBook{" +
                        "sId='" + sId + '\'' +
                        ", name='" + name + '\'' +
                        ", bookName='" + bookName + '\'' +
                        ", academy='" + academy + '\'' +
                        '}';
        }
}
