package com.revature.Service;

import com.revature.Exception.LabClearException;
import com.revature.Util.LoggerSingleton;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;

public class FileService implements iFileService{
    iCMDService cmdService;
    Logger log;
    public FileService(iCMDService cmdService){
        this.cmdService = cmdService;
        this.log = LoggerSingleton.getLogger();
    }

    @Override
    public void clear() throws LabClearException {
        File labDirectory = new File("lab");
        if(!labDirectory.exists()){
            log.warn("No lab directory found on lab clear.");
        }else{
            log.info("Attempting clear of existing lab directory.");
            deleteDirectoryHelper(labDirectory);
        }
    }

    public boolean deleteDirectoryHelper(File directoryToBeDeleted){
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                log.info("Deleting file: "+file.getName());
                deleteDirectoryHelper(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
}
