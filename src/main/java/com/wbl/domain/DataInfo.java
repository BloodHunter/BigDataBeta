package com.wbl.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Simple_love on 2016/1/4.
 */
@Entity
@Table(name = "dataInfo")
public class DataInfo {
        private String dataId;
        private String dataName;
        private int owner;
        private String type;
        private long dataSize;
        private String description;
        private String category;
        private Integer status;
        private Integer relation;
        private Timestamp time;

        public DataInfo() {
        }

        public DataInfo( String dataName,int owner, String type, long dataSize, String description,String category) {
               this(null,dataName,owner,type,dataSize,description,category,0,0,null);
        }

        public DataInfo(String dataId, String dataName, int owner,String type, long dataSize,String description, String category,Integer status, Integer relation, Timestamp time) {
                this.dataId = dataId;
                this.dataName = dataName;
                this.owner = owner;
                this.type = type;
                this.dataSize = dataSize;
                this.description = description;
                this.category = category;
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

        @Column(name = "owner")
        public int getOwner() {
                return owner;
        }

        public void setOwner(int owner) {
                this.owner = owner;
        }

        @Basic
        @Column(name = "type")
        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        @Column(name = "dataSize")
        public long getDataSize() {
                return dataSize;
        }

        public void setDataSize(long dataSize) {
                this.dataSize = dataSize;
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
        @Column(name = "category")
        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
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

                if (owner != dataInfo.owner) return false;
                if (dataSize != dataInfo.dataSize) return false;
                if (dataId != null ? !dataId.equals(dataInfo.dataId) : dataInfo.dataId != null) return false;
                if (dataName != null ? !dataName.equals(dataInfo.dataName) : dataInfo.dataName != null) return false;
                if (type != null ? !type.equals(dataInfo.type) : dataInfo.type != null) return false;
                if (description != null ? !description.equals(dataInfo.description) : dataInfo.description != null)
                        return false;
                if (category != null ? !category.equals(dataInfo.category) : dataInfo.category != null) return false;
                if (status != null ? !status.equals(dataInfo.status) : dataInfo.status != null) return false;
                if (relation != null ? !relation.equals(dataInfo.relation) : dataInfo.relation != null) return false;
                return !(time != null ? !time.equals(dataInfo.time) : dataInfo.time != null);

        }

        @Override
        public int hashCode() {
                int result = dataId != null ? dataId.hashCode() : 0;
                result = 31 * result + (dataName != null ? dataName.hashCode() : 0);
                result = 31 * result + owner;
                result = 31 * result + (type != null ? type.hashCode() : 0);
                result = 31 * result + (int) (dataSize ^ (dataSize >>> 32));
                result = 31 * result + (description != null ? description.hashCode() : 0);
                result = 31 * result + (category != null ? category.hashCode() : 0);
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
                        ", owner ='" + owner + '\'' +
                        ", type='" + type + '\'' +
                        ", dataSize='" + dataSize +'\'' +
                        ", description='" + description + '\'' +
                        ", category='" + category +'\'' +
                        ", status=" + status +
                        ", relation=" + relation +
                        ", time=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time) +
                        '}';
        }
}
