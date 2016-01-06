package com.wbl.dao.impl;

import com.wbl.dao.ProvDao;
import com.wbl.domain.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simple_love on 2015/10/26.
 */
@Repository("ProvDao")
public class ProvDaoImpl implements ProvDao {

        private Logger logger = Logger.getLogger(this.getClass());

        @Autowired
        private HibernateTemplate template;
        public List<Prov> getProv() {
                List<Prov> provs = new ArrayList<Prov>();
                provs = (List<Prov>) template.find("from Prov");
                return provs;
        }

        public List<Prov> getProv(String agent) {
                List<Prov> provs = new ArrayList<Prov>();
                provs = (List<Prov>) template.find("from Prov where agent = ?",agent);
                return provs;
        }

        public List<Prov> getProv(int start ,int end){
                List<Prov> provs = new ArrayList<Prov>();
                provs = (List<Prov>)template.find("from Prov where pid >= ? and pid <= ?",start,end);
                return provs;
        }

        public boolean save(Prov prov) {
                try {
                        template.save(prov);
                }catch (Exception e){
                        logger.error("Save Prov error " + e );
                        return false;
                }
                return true;
        }

        public boolean update(Prov prov) {
                try {
                        template.update(prov);
                }catch (Exception e){
                        logger.error("Update Prov error " + e);
                        return false;
                }
                return true;
        }

        @Override
        public boolean save(DataInfo dataInfo) {
                try {
                        template.save(dataInfo);
                }catch (Exception e){
                        logger.error("Save DataInfo error " + e);
                        return false;
                }
                return true;
        }

        @Override
        public boolean update(DataInfo dataInfo) {
                try {
                        template.update(dataInfo);
                }catch (Exception e){
                        logger.error("Update DataInfo error " + e);
                        return false;
                }
                return true;
        }

        @Override
        public boolean save(Next next) {
                if (!isExist(next)){
                        template.save(next);
                        return true;
                }
                return false;
        }

        @Override
        public boolean save(Source source) {
                if (!isExist(source)){
                        template.save(source);
                        return true;
                }
                return false;
        }

        @Override
        public boolean save(RelationInfo relationInfo) {
                if (!isExist(relationInfo)){
                        template.save(relationInfo);
                        return true;
                }
                return false;
        }

        @Override
        public boolean save(ReceivedParam receivedParam) {
                try {
                        template.save(receivedParam);
                        return true;
                }catch (Exception e){
                        logger.error("Save ReceivedParam error: " + e);
                        return false;
                }
        }

        @Override
        public boolean save(SendParam sendParam) {
                try {
                        template.save(sendParam);
                        return true;
                }catch (Exception e){
                        logger.error("Save SendParam error: " + e);
                        return false;
                }
        }

        @Override
        public List<Next> getNext(String dataID) {
                String query = "From Next where dataId = ?";
                return (List<Next>) template.find(query,dataID);
        }

        @Override
        public List<Source> getSource(String dataID) {
                String query = "From Source where dataId = ?";
                return (List<Source>) template.find(query,dataID);
        }

        @Override
        public List<RelationInfo> getAncestor(String dataID) {
                String query = "From RelationInfo where successor = ? ";
                return (List<RelationInfo>) template.find(query,dataID);
        }


        @Override
        public List<RelationInfo> getSuccessor(String dataID) {
                String query = "From RelationInfo where ancestor = ?";
                return (List<RelationInfo>) template.find(query,dataID);
        }

        @Override
        public DataInfo getDataInfoByDataId(String dataId) {
                List<DataInfo> result = (List<DataInfo>) template.find("from DataInfo where dataId = ?",dataId);
                if (result.isEmpty()){
                        logger.warn("DataInfo with dataId[" + dataId + "] is not exist");
                        return null;
                }
                else
                        return result.get(0);
        }

        @Override
        public DataInfo getDataInfoByName(String dataName) {
                List<DataInfo> result = (List<DataInfo>) template.find("from DataInfo where dataName = ?",dataName);
                if (result.isEmpty()){
                        logger.warn("DataInfo with name[" + dataName  +"] is not exist");
                        return null;
                }
                else
                        return result.get(0);
        }

        @Override
        public ReceivedParam getReceivedParam(ReceivedParam param) {
                String query = "from ReceivedParam where queryFrom = ? and queryFor = ?" +
                        " and requestId = ? and dataId = ?";
                List result = template.find(query,param.getQueryFrom(),param.getQueryFor(),param.getRequestId(),param.getDataId());
                if (result.isEmpty()){
                        logger.info("ReceivedParam queryFrom = " + param.getQueryFrom() + " queryFor = " + param.getQueryFor() +
                                " requestId = " + param.getRequestId() + " dataId = " + param.getDataId() + " is not exist");
                        return null;
                }else
                        return (ReceivedParam) result.get(0);
        }

        @Override
        public SendParam getSendParam(SendParam param) {
                String query = "from SendParam  where queryFrom = ? and queryFor = ?" +
                        " and requestId = ? and dataId = ?";
                List result = template.find(query,param.getQueryFrom(),param.getQueryFor(),param.getRequestId(),param.getDataId());
                if (result.isEmpty()){
                        logger.info("SendParam queryFrom = " + param.getQueryFrom() + " queryFor = " + param.getQueryFor() +
                                " requestId = " + param.getRequestId() + " dataId = " + param.getDataId() + " is not exist");
                        return null;
                }else
                        return (SendParam) result.get(0);
        }

        private boolean isExist(Next next){
                String query = "From Next where dataId = ? and next = ?";
                return !template.find(query,next.getDataId(),next.getNext()).isEmpty();
        }

        private boolean isExist(Source source){
                String query = "From Source where dataId = ? and source = ?";
                return !template.find(query,source.getDataId(),source.getSource()).isEmpty();
        }

        private boolean isExist(RelationInfo relationInfo){
                String query = "From RelationInfo where ancestor = ? and successor = ?";
                return !template.find(query,relationInfo.getAncestor(),relationInfo.getSuccessor()).isEmpty();
        }
}
