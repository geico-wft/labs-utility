package com.revature.Service;

import com.revature.Exception.GitConfigException;
import com.revature.Exception.LabOpenException;
import com.revature.Exception.LabSaveException;
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
    public void open(String labName) throws LabOpenException, GitConfigException {
        try{
            cmdService.executeCommand("git clone "+baseUrl+labName + " lab");
            String name = getUsername();
            cmdService.executeCommand("git branch "+name, "lab");
            cmdService.executeCommand("git checkout "+name, "lab");
        }catch(IOException | InterruptedException ex){
            log.warn("Exception while opening lab: "+labName);
            throw new LabOpenException("Exception during attempted lab opening");
        }
    }



    @Override
    public void save() throws LabSaveException, GitConfigException {
        try{
            cmdService.executeCommand("git add .");
            cmdService.executeCommand("git commit -m \"saving lab progress\"");
            String name = getUsername();
            cmdService.executeCommand("git push -u origin "+name, "lab");
        }catch(IOException | InterruptedException ex){
            log.warn("Exception while saving lab");
            throw new LabSaveException("Exception during attempted lab saving");
        }
    }

    @Override
    public String getUsername() throws GitConfigException {
        try {
            return cmdService.executeCommand("git config user.name").trim();
        }catch (IOException | InterruptedException ex){
            log.warn("Exception while attempting to retrieve user git info");
            throw new GitConfigException("Exception during git username retrieval");
        }
    }
}
