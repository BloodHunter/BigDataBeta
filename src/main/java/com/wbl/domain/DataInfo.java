package com.wbl.domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Simple_love on 2016/1/4.
 */
@Entity
@Table(name = "dataInfo")
public class DataInfo {
        private String dataId;
        private String dataName;
        private String type;
        private String description;
        private Integer status;
        private Integer relation;
        private Timestamp time;

        public DataInfo() {
        }

        public DataInfo(String dataId, String dataName, String type, String description) {
               this(dataId,dataName,type,description,0,0,null);
        }

        public DataInfo(String dataId, String dataName, String type, String description, Integer status, Integer relation, Timestamp time) {
                this.dataId = dataId;
                this.dataName = dataName;
                this.type = type;
                this.description = description;
                this.status = status;
                this.relation = relation;
                this.time = time;
        }

        @Id
        @Column(name = "dataID")
        public String getDataId() {
                return dataId;
        }

        public void setDataId(String dataId) {
                this.dataId = dataId;
        }

        @Basic
        @Column(name = "dataName")
        public String getDataName() {
                return dataName;
        }

        public void setDataName(String dataName) {
                this.dataName = dataName;
        }

        @Basic
        @Column(name = "type")
        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        @Basic
        @Column(name = "description")
        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        @Basic
        @Column(name = "status")
        public Integer getStatus() {
                return status;
        }

        public void setStatus(Integer status) {
                this.status = status;
        }

        @Basic
        @Column(name = "relation")
        public Integer getRelation() {
                return relation;
        }

        public void setRelation(Integer relation) {
                this.relation = relation;
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

                DataInfo dataInfo = (DataInfo) o;

                if (dataId != null ? !dataId.equals(dataInfo.dataId) : dataInfo.dataId != null) return false;
                if (dataName != null ? !dataName.equals(dataInfo.dataName) : dataInfo.dataName != null) return false;
                if (type != null ? !type.equals(dataInfo.type) : dataInfo.type != null) return false;
                if (description != null ? !description.equals(dataInfo.description) : dataInfo.description != null)
                        return false;
                if (status != null ? !status.equals(dataInfo.status) : dataInfo.status != null) return false;
                if (relation != null ? !relation.equals(dataInfo.relation) : dataInfo.relation != null) return false;
                if (time != null ? !time.equals(dataInfo.time) : dataInfo.time != null) return false;

                return true;
        }

        @Override
        public int hashCode() {
                int result = dataId != null ? dataId.hashCode() : 0;
                result = 31 * result + (dataName != null ? dataName.hashCode() : 0);
                result = 31 * result + (type != null ? type.hashCode() : 0);
                result = 31 * result + (description != null ? description.hashCode() : 0);
                result = 31 * result + (status != null ? status.hashCode() : 0);
                result = 31 * result + (relation != null ? relation.hashCode() : 0);
                result = 31 * result + (time != null ? time.hashCode() : 0);
                return result;
        }

        @Override
        public String toString() {
                return "DataInfo{" +
                        "dataId='" + dataId + '\'' +
                        ", dataName='" + dataName + '\'' +
                        ", type='" + type + '\'' +
                        ", description='" + description + '\'' +
                        ", status=" + status +
                        ", relation=" + relation +
                        ", time=" + time +
                        '}';
        }
}
