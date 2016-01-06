package com.wbl.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Simple_love on 2016/1/4.
 */
@Entity
public class RelationInfo {
        private int id;
        private String ancestor;
        private String successor;

        public RelationInfo() {
        }

        public RelationInfo(String ancestor, String successor) {
                this.ancestor = ancestor;
                this.successor = successor;
        }

        @Id
        @Column(name = "id")
        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        @Basic
        @Column(name = "ancestor")
        public String getAncestor() {
                return ancestor;
        }

        public void setAncestor(String ancestor) {
                this.ancestor = ancestor;
        }

        @Basic
        @Column(name = "successor")
        public String getSuccessor() {
                return successor;
        }

        public void setSuccessor(String successor) {
                this.successor = successor;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                RelationInfo that = (RelationInfo) o;

                if (id != that.id) return false;
                if (ancestor != null ? !ancestor.equals(that.ancestor) : that.ancestor != null) return false;
                if (successor != null ? !successor.equals(that.successor) : that.successor != null) return false;

                return true;
        }

        @Override
        public int hashCode() {
                int result = id;
                result = 31 * result + (ancestor != null ? ancestor.hashCode() : 0);
                result = 31 * result + (successor != null ? successor.hashCode() : 0);
                return result;
        }

        @Override
        public String toString() {
                return "RelationInfo{" +
                        "id=" + id +
                        ", ancestor='" + ancestor + '\'' +
                        ", successor='" + successor + '\'' +
                        '}';
        }
}
