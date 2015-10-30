package com.wbl.dao.impl;

import com.wbl.dao.ProvDao;
import com.wbl.domain.Prov;
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

        public boolean saveProv(Prov prov) {
                try {
                        template.save(prov);
                }catch (Exception e){
                        e.printStackTrace();
                        return false;
                }
                return true;
        }

        public boolean updateProv(Prov prov) {
                try {
                        template.update(prov);
                }catch (Exception e){
                        e.printStackTrace();
                        return false;
                }
                return true;
        }
}
