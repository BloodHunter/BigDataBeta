package com.wbl.modal.time;

import org.apache.log4j.Logger;

import java.io.File;

/**
 * Created withSimple_love
 * Date: 2016/1/7.
 * Time: 16:30
 *
 * Delete the file in dir(provImage) in time(24:00)
 */
public class DeleteFileJob {
        Logger logger = Logger.getLogger(DeleteFileJob.class);
        public void deleteFile(){
                File dir = new File(getPath());
                File[] files = dir.listFiles();
                if (files != null){
                        for (File file : files){
                                logger.info("It's time to delete file[" + file.getName() +"]");
                                file.delete();
                        }
                }
        }

        private String getPath(){
                String path = DeleteFileJob.class.getClassLoader().getResource("").getPath();
                int index = path.indexOf("classes");
                path = path.substring(0,index) + "provImage";
                path = path.substring(1);
                return path;
        }
}
