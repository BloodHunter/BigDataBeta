package com.wbl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with Simple_love
 * Date: 2016/4/1.
 * Time: 11:08
 */
@Entity
@Table(name = "category")
public class Category {
        private String code;
        private String name;
        public Category(){}

        public Category(String code, String name) {
                this.code = code;
                this.name = name;
        }

        @Id
        @Column(name = "code")
        public String getCode() {
                return code;
        }

        public void setCode(String code) {
                this.code = code;
        }

        @Column(name = "name")
        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        @Override
        public String toString() {
                return "{" +
                        "code:'" + code + '\'' +
                        ", name:'" + name + '\'' +
                        '}';
        }
}
