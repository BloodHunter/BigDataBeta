package com.wbl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created with Simple_love
 * Date: 2016/4/6.
 * Time: 11:24
 */
@Entity
@Table(name = "userinfo")
public class UserInfo {
        @Id
        @Column(name = "userId")
        private int userId;

        @Column(name = "userName")
        private String userName;

        @Column(name = "password")
        private String password;

        @Column(name = "email")
        private String email;

        @Column(name = "hobby")
        private String hobby;

        @Column(name = "profession")
        private String profession;

        @Column(name = "telephone")
        private String telephone;

        @Column(name = "url")
        private String url;
        @Column(name = "insertDate")
        private Timestamp insertDate;

        public UserInfo() {
        }

        public UserInfo(String userName,String email,String password,String hobby){
                this(userName,password,email,hobby,null,null,null,null);
        }

        public UserInfo(String userName, String password,
                        String email, String hobby, String profession, String telephone, String url,Timestamp insertDate) {
                this.userName = userName;
                this.password = password;
                this.email = email;
                this.hobby = hobby;
                this.profession = profession;
                this.telephone = telephone;
                this.url = url;
                this.insertDate = insertDate;
        }

        public int getUserId() {
                return userId;
        }

        public void setUserId(int userId) {
                this.userId = userId;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getHobby() {
                return hobby;
        }

        public void setHobby(String hobby) {
                this.hobby = hobby;
        }

        public String getProfession() {
                return profession;
        }

        public void setProfession(String profession) {
                this.profession = profession;
        }

        public String getTelephone() {
                return telephone;
        }

        public void setTelephone(String telephone) {
                this.telephone = telephone;
        }

        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }

        public Timestamp getInsertDate() {
                return insertDate;
        }

        public void setInsertDate(Timestamp insertDate) {
                this.insertDate = insertDate;
        }

        @Override
        public String toString() {
                return "UserInfo{" +
                        "userId=" + userId +
                        ", userName='" + userName + '\'' +
                        ", password='" + password + '\'' +
                        ", email='" + email + '\'' +
                        ", hobby='" + hobby + '\'' +
                        ", profession='" + profession + '\'' +
                        ", telephone='" + telephone + '\'' +
                        ", url='" + url + '\'' +
                        ", insertDate=" + insertDate +
                        '}';
        }
}
