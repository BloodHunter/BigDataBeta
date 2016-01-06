package com.wbl.modal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simple_love on 2015/11/25.
 */
public class PlatformNode {
        private String platfornName;
        private List<String> link = new ArrayList<>();

        public PlatformNode() {
        }

        public PlatformNode(String platfornName, List<String> link) {
                this.platfornName = platfornName;
                this.link = link;
        }

        public String getPlatfornName() {
                return platfornName;
        }

        public void setPlatfornName(String platfornName) {
                this.platfornName = platfornName;
        }

        public List<String> getLink() {
                return link;
        }

        public void setLink(List<String> link) {
                this.link = link;
        }

        @Override
        public String toString() {
                return "PlatformNode{" +
                        "platfornName='" + platfornName + '\'' +
                        ", link=" + link +
                        '}';
        }
}
