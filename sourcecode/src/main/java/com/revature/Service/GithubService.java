package com.revature.Service;

import com.revature.Exception.LabOpenException;
import com.revature.Util.LoggerSingleton;
import com.revature.Util.URLUtil;
import org.slf4j.Logger;

import java.io.IOException;

/**
 * Service class that manages interaction with Github repositories by leveraging
 * the local machine's git install & configuration.
 */
public class GithubService implements iGithubService{
    iCMDService cmdService;
    String baseUrl;
    Logger log;
    public GithubService(iCMDService cmdService) {
        this.cmdService = cmdService;
        this.baseUrl = URLUtil.BASE_URL;
        this.log = LoggerSingleton.getLogger();
    }

    @Override
    public void open(String labName) throws LabOpenException {
        try{
            cmdService.executeCommand("git clone "+baseUrl+labName + " lab");
        }catch(IOException | InterruptedException ex){
            log.warn("Exception while opening lab: "+labName);
            throw new LabOpenException("Exception during attempted lab opening");
        }
    }

    @Override
    public void save() {

    }
}
