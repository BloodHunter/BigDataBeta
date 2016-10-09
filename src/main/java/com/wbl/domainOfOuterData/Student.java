package com.wbl.domainOfOuterData;

/**
 * Created with Simple_love
 * Date: 2016/4/26.
 * Time: 15:11
 */
public class Student {
        private String sId;
        private String name;
        private int startYear;
        private String className;
        private String nation;
        private String sex;
        private String academy;
        private int type;
        private int isInSchool;

        public Student() {
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

        public int getStartYear() {
                return startYear;
        }

        public void setStartYear(int startYear) {
                this.startYear = startYear;
        }

        public String getClassName() {
                return className;
        }

        public void setClassName(String className) {
                this.className = className;
        }

        public String getNation() {
                return nation;
        }

        public void setNation(String nation) {
                this.nation = nation;
        }

        public String getSex() {
                return sex;
        }

        public void setSex(String sex) {
                this.sex = sex;
        }

        public String getAcademy() {
                return academy;
        }

        public void setAcademy(String academy) {
                this.academy = academy;
        }

        public int getType() {
                return type;
        }

        public void setType(int type) {
                this.type = type;
        }

        public int getIsInSchool() {
                return isInSchool;
        }

        public void setIsInSchool(int isInSchool) {
                this.isInSchool = isInSchool;
        }

        @Override
        public String toString() {
                return "Student{" +
                        "sId='" + sId + '\'' +
                        ", name='" + name + '\'' +
                        ", sex='" + sex + '\'' +
                        ", academy='" + academy + '\'' +
                        '}';
        }
}
