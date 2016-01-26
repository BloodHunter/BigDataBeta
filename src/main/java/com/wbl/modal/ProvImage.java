package com.wbl.modal;

import com.wbl.domain.Prov;
import com.wbl.util.DrawProvImage;

import static com.wbl.modal.Enum.Activity.*;
import java.util.*;

/**
 * Created by Simple_love on 2015/10/27.
 */
public class ProvImage {
        private List<Prov> provs = new ArrayList<Prov>();
        private List<Platform> roots = new ArrayList<Platform>();
        private Map<String,Platform> nodes = new HashMap<String, Platform>();
        private static int num = 5;
        private  int totalImageNum = 0;
        public ProvImage(){}
        public ProvImage(List<Prov> provs){
                this.provs = provs;
                groupByPlatform();
        }
        public ProvImage(List<Prov> provs,int num){
                this.num = num;
                this.provs = provs;
                groupByPlatform();
        }

        public int getNum() {
                return num;
        }

        public void setNum(int num) {
                this.num = num;
        }

        public  int getTotalImageNum() {
                return totalImageNum;
        }


        public class Platform{
                private String name;
                private List<Platform> next = new LinkedList<Platform>();
                private List<Prov> download = new LinkedList<Prov>();
                private List<Prov> upload = new LinkedList<Prov>();
                private List<Prov> aggragetion = new LinkedList<Prov>();
                private List<Prov> importFrom = new LinkedList<Prov>();
                private List<Prov> exportTo = new LinkedList<Prov>();
                private List<Prov> other = new LinkedList<Prov>();
                private int count = 0;

                public Platform(){}
                public Platform(String name){
                        this.name = name;
                }

                @Override
                public boolean equals(Object o) {
                        if (this == o) return true;
                        if (o == null || getClass() != o.getClass()) return false;

                        Platform platform = (Platform) o;

                        return !(name != null ? !name.equals(platform.name) : platform.name != null);

                }

                @Override
                public int hashCode() {
                        return name != null ? name.hashCode() : 0;
                }
        }

        public void groupByPlatform(){
                for (Prov prov : this.provs){

                        //平台是否已加入节点中
                        if (!nodes.containsKey(prov.getPrefix())){
                                nodes.put(prov.getPrefix(),new Platform(prov.getPrefix()));
                        }

                        //平台是否有上游节点
                        if (prov.getUsed() != null && !prov.getUsed().isEmpty()){
                                String[] upstream = prov.getUsed().split(":");
                                String source = upstream[0];
                                if (!source.equals(prov.getPrefix()) && nodes.get(source) != null &&
                                        !nodes.get(source).next.contains(nodes.get(prov.getPrefix()))){
                                        nodes.get(source).next.add(nodes.get(prov.getPrefix()));
                                }
                        }

                        switch (prov.getActivity()){
                                case "DOWNLOAD":
                                        nodes.get(prov.getPrefix()).download.add(prov);
                                        break;
                                case "UPLOAD":
                                        if (!roots.contains(nodes.get(prov.getPrefix()))){
                                                roots.add(nodes.get(prov.getPrefix()));
                                        }
                                        nodes.get(prov.getPrefix()).upload.add(prov);
                                        break;
                                case "AGGREGATION":
                                        nodes.get(prov.getPrefix()).aggragetion.add(prov);
                                        break;
                                case "IMPORT":
                                        nodes.get(prov.getPrefix()).importFrom.add(prov);
                                        break;
                                case "EXPORT":
                                        nodes.get(prov.getPrefix()).exportTo.add(prov);
                                        break;
                                default:
                                        nodes.get(prov.getPrefix()).other.add(prov);
                        }
                        nodes.get(prov.getPrefix()).count ++;
                }
        }

        public void draw(String imageName){
                int sigal = 0;
                Queue<Platform> queue = new LinkedList<>(roots);
                List<Prov>records = new ArrayList<>();
                while (!queue.isEmpty()){
                        Platform temp = queue.poll();
                        if (nodes.containsKey(temp.name)){
                                records.addAll(temp.upload.size() > num ? temp.upload.subList(0,num):temp.upload);
                                records.addAll(temp.download.size() > num ? temp.download.subList(0,num):temp.download);
                                records.addAll(temp.aggragetion.size() > num ? temp.aggragetion.subList(0,num):temp.aggragetion);
                                records.addAll(temp.importFrom.size() > num ? temp.importFrom.subList(0,num):temp.importFrom);
                                records.addAll(temp.exportTo.size() > num ? temp.exportTo.subList(0, num) : temp.exportTo);
                                for (Platform platform:temp.next){
                                        if (!queue.contains(platform))
                                                queue.offer(platform);
                                }
                                nodes.remove(temp.name);
                                sigal++;
                                if (sigal % num == 0){
                                        new DrawProvImage(imageName +"-" + totalImageNum + ".jpg",records).draw();
                                        totalImageNum++;
                                        records.clear();
                                }
                        }
                }
                if (!records.isEmpty()){
                        System.out.println(records.size());
                        new DrawProvImage(imageName +"-" + totalImageNum + ".jpg",records).draw();
                        totalImageNum++;
                }
        }

        public static void main(String[] args){
               /* List<Prov> provs = new ArrayList<Prov>();
                String time = "2015-10-27 09:32:00";
                *//*Platform A*//*
                provs.add(new Prov("platA","platA-1","userA-1",UPLOAD.name(),null,time));
                *//*Platform B*//*
                provs.add(new Prov("platB","platB-1","userB-1",UPLOAD.name(),null,time));
                provs.add(new Prov("platB","platA-1","platB",DOWNLOAD.name(),"platA:platA-1",time));
                provs.add(new Prov("platB","platB-2","userB-2",AGGREGATION.name(),"platA:platA-1",time));
                provs.add(new Prov("platB","platB-2","userB-2",AGGREGATION.name(),"platB:platB-1",time));
                provs.add(new Prov("platB","platB-3","platC",EXPORT.name(),"platB:platB-2",time));
                provs.add(new Prov("platB","platB-4","platD",EXPORT.name(),"platB:platB-2",time));
                provs.add(new Prov("platB","platB-5","platE",EXPORT.name(),"platB:platB-2",time));

                *//*Platform C*//*
                provs.add(new Prov("platC","platB-3","platC",IMPORT.name(),"platB:platB-3",time));

                *//*Platform D*//*
                provs.add(new Prov("platD","platB-4","platD",IMPORT.name(),"platB:platB-4",time));

                *//*Platform E*//*
                provs.add(new Prov("platE","platB-5","platE",IMPORT.name(),"platB:platB-5",time));

                *//*Platform F*//*
                provs.add(new Prov("platF","platA-1","platF",DOWNLOAD.name(),"platA:platA-1",time));
                provs.add(new Prov("platF","platB-3","platF",DOWNLOAD.name(),"platC:platB-3",time));
                provs.add(new Prov("platF","platF-2","userF-1",AGGREGATION.name(),"platF:platB-3",time));
                provs.add(new Prov("platF","platF-2","userF-1",AGGREGATION.name(),"platF:platA-1",time));

                ProvImage image = new ProvImage(provs,10);
                image.draw("other1");*/
        }

}
