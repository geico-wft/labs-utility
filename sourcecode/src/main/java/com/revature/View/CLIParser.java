package com.revature.View;

import com.revature.Exception.*;
import com.revature.Service.CMDService;
import com.revature.Service.iCMDService;
import com.revature.Service.iFileService;
import com.revature.Service.iGithubService;

import java.io.File;
import java.io.IOException;

/**
 * A class used to interpret the user input obtained from the main method.
 * Extracting this menu behavior away from the main method's scanner input potentially allows
 * for the creation of automated unit tests for menu input.
 */
public class CLIParser {

    iFileService fileService;
    iGithubService githubService;
    iCMDService cmdService;

    public CLIParser(iFileService fileService, iGithubService githubService, iCMDService cmdService) {

        this.fileService = fileService;
        this.githubService = githubService;
        this.cmdService = cmdService;
    }

    public String parse(String input) throws BadCommandException, LabException, GitConfigException {
//        standardize the user's input and split into an array containing the separate words
        input = input.toLowerCase();
        input = input.trim();
        String tokens[] = input.split(" ");
//        main switch for the requested command
        if(tokens[0].equals("help")){
            return getHelpMessage();
        }else if(tokens[0].equals("clear")){
            fileService.clear();
            return getClearedMessage();
        }else if(tokens[0].equals("open")){
//            check that the user has actually provided a lab name.
            if(tokens.length < 2){
                throw new BadCommandException("open command requires an argument containing the name of the command.");
            }else{
//                clear the user's workspace, then add the requested lab to the workspace.
                String labName = tokens[1];
                if(fileService.labDirectoryExists()){
                    githubService.save();
                    fileService.clear();
                }
                githubService.open(labName);
                return getRetrievedMessage(labName);
            }
        }else if(tokens[0].equals("save")){
            if(fileService.labDirectoryExists()){
                githubService.save();
            }else{
                throw new LabSaveException("No lab directory is present for saving.");
            }
            return getSavedMessage();
        }else if(tokens[0].equals("check")){
            return checkEnvironment();
        }else{
            throw new BadCommandException("the command provided did not match help/clear/open/save.");
        }
    }
    public String getHelpMessage(){
        return "You may use the commands 'clear' to clear the workspace's current labs without saving, \n" +
                "or 'open [labname]' (eg open java-helloworld) to open a lab (this will auto-save the current lab)," +
                "or 'save' to manually save the current lab.";
    }
    public String getSavedMessage(){
        return "Saved the lab progress.";
    }
    public String getClearedMessage(){
        return "Workspace cleared.";
    }
    public String getRetrievedMessage(String labName){
        return "Retrieved: "+labName;
    }
    public String checkEnvironment() throws LabClearException {
        try {
            String gitCheck = cmdService.executeCommand("git -v");
            if (gitCheck == null || gitCheck.equals("") || !gitCheck.contains("git version")) {
                throw new GitConfigException("git config exception");
            }
        }catch(IOException | InterruptedException | GitConfigException e) {
            return "Git install was not found on your computer - please install the git CLI.";
        }
        try {
            fileService.clear();
            githubService.open("public-repo");
            if (!fileService.labDirectoryExists()) {
                throw new LabException("lab exception");
            }
        }catch(LabException | GitConfigException e) {
            return "There was an issue cloning a public repo from our org. Most likely a security/privileges issue.";
        }finally {
            fileService.clear();
        }
        try{
            githubService.open("private-repo");
            if(!fileService.labDirectoryExists()) {
                throw new LabException("lab during test of opening of private repo");
            }
        }catch(LabException | GitConfigException ex) {
            return "There was an issue cloning a private repo from our org. Either you are not a part of the \n" +
                    "required github organization, or, you have not accepted the organization invite, or, you \n" +
                    "have not configured your git login on your computer - this may require you to configure a \n" +
                    "personal access token taken from the github site. You may refer to this guide for creating \n" +
                    "a personal access token (classic) and then performing a sensitive action (such as cloning \n" +
                    "a private repo), and using your access token in place of your password on login.";
        }
        try {
            File testFile = new File("lab/test.txt");
            testFile.createNewFile();
            githubService.save();
            fileService.clear();
            githubService.open("private-repo");
            File fileShouldExist = new File("lab/test.txt");
            if (fileShouldExist.exists()) {
                return "It looks like everything is working properly, happy coding.";
            }else{
                throw new LabException("could not detect push to private repo");
            }
        }catch (LabException | GitConfigException | IOException e){
            return "You are able to access private repos, but it seems you can not push to them. Most like, this \n" +
                    "is an issue with security/privileges on an enterprise account. Please begin using a personal \n" +
                    "github account, and reconfigure your git config on your machine to the new account, and also \n" +
                    "ask for an invite to our organizations for the new account.";
        }
    }
}