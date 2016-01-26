package com.wbl.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Simple_love on 2015/10/26.
 */
@Entity
@Table(name = "prov")
public class Prov implements Serializable{
        private int pid;

        private String prefix;

        private String entity;

        private String agent;

        private String activity;

        private String used;

        private Timestamp time;

        public Prov(){}

        public Prov(String prefix, String entity, String agent, String activity, String used, Timestamp time) {
                this.prefix = prefix;
                this.entity = entity;
                this.agent = agent;
                this.activity = activity;
                this.used = used;
                this.time = time;
        }


        @Id
        @Column(name = "pid")
        public int getPid() {
                return pid;
        }

        public void setPid(int pid) {
                this.pid = pid;
        }

        @Basic
        @Column(name = "prefix")
        public String getPrefix() {
                return prefix;
        }

        public void setPrefix(String prefix) {
                this.prefix = prefix;
        }

        @Basic
        @Column(name = "entity")
        public String getEntity() {
                return entity;
        }

        public void setEntity(String entity) {
                this.entity = entity;
        }

        @Basic
        @Column(name = "agent")
        public String getAgent() {
                return agent;
        }

        public void setAgent(String agent) {
                this.agent = agent;
        }

        @Basic
        @Column(name = "activity")
        public String getActivity() {
                return activity;
        }

        public void setActivity(String activity) {
                this.activity = activity;
        }

        @Basic
        @Column(name = "used")
        public String getUsed() {
                return used;
        }

        public void setUsed(String used) {
                this.used = used;
        }

        @Basic
        @Column(name = "time")
        public Timestamp getTime() {
                return time;
        }

        public void setTime(Timestamp time) {
                this.time = time;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Prov prov = (Prov) o;

                if (pid != prov.pid) return false;
                if (prefix != null ? !prefix.equals(prov.prefix) : prov.prefix != null) return false;
                if (entity != null ? !entity.equals(prov.entity) : prov.entity != null) return false;
                if (agent != null ? !agent.equals(prov.agent) : prov.agent != null) return false;
                if (activity != null ? !activity.equals(prov.activity) : prov.activity != null) return false;
                return !(used != null ? !used.equals(prov.used) : prov.used != null);

        }

        @Override
        public int hashCode() {
                int result = pid;
                result = 31 * result + (prefix != null ? prefix.hashCode() : 0);
                result = 31 * result + (entity != null ? entity.hashCode() : 0);
                result = 31 * result + (agent != null ? agent.hashCode() : 0);
                result = 31 * result + (activity != null ? activity.hashCode() : 0);
                result = 31 * result + (used != null ? used.hashCode() : 0);
                return result;
        }

        @Override
        public String toString() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return "{" +
                        "pid=" + pid +
                        ", prefix='" + prefix + '\'' +
                        ", entity='" + entity + '\'' +
                        ", agent='" + agent + '\'' +
                        ", activity='" + activity + '\'' +
                        ", used='" + used + '\'' +
                        ", time='" + sdf.format(time) + '\'' +
                        '}';
        }
}
