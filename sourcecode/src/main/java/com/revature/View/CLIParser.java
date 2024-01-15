package com.revature.View;

import com.revature.Exception.*;
import com.revature.Service.iFileService;
import com.revature.Service.iGithubService;

/**
 * A class used to interpret the user input obtained from the main method.
 * Extracting this menu behavior away from the main method's scanner input potentially allows
 * for the creation of automated unit tests for menu input.
 */
public class CLIParser {

    iFileService fileService;
    iGithubService githubService;

    public CLIParser(iFileService fileService, iGithubService githubService) {

        this.fileService = fileService;
        this.githubService = githubService;
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
                fileService.clear();
                githubService.save();
                githubService.open(labName);
                return getRetrievedMessage(labName);
            }
        }else if(tokens[0].equals("save")){
            githubService.save();
            return getSavedMessage();
        }else{
            throw new BadCommandException("the command provided did not match help/clear/open.");
        }
    }
    public String getHelpMessage(){
        return "You may use the commands 'clear' to clear the workspace's current labs, \n" +
                "or 'open [labname]' (eg open java-helloworld) to open a lab.";
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
}