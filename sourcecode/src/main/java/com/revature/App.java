package com.revature;

import com.revature.Exception.BadCommandException;
import com.revature.Exception.GitConfigException;
import com.revature.Exception.LabException;
import com.revature.Exception.LabOpenException;
import com.revature.Service.*;
import com.revature.View.CLIParser;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        iCMDService cmdService = new CMDService();
        iFileService fileService = new FileService(cmdService);
        iGithubService githubService = new GithubService(cmdService);
        CLIParser cliParser = new CLIParser(fileService, githubService);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input command: ");
        String input = scanner.nextLine();

        try{
            cliParser.parse(input);
        }catch (BadCommandException ex){
            System.out.println("There was an exception while interpreting your command!\n" +
                    "This may be because you did not format your command properly.\n" +
                    "Here is the stack trace: ");
            ex.printStackTrace();
        }catch (LabException ex){
            System.out.println("There was an exception while attempting to open your lab! \n" +
                    "This may be because you do not have your GitHub credentials set on your machine, \n" +
                    "or the requested lab may not exist. \n" +
                    "Here is the stack trace: ");
            ex.printStackTrace();
        }catch (GitConfigException ex){
            System.out.println("There was an exception while attempting to retrieve Git config info! \n" +
                    "This may be because Git is not properly installed and configured. \n" +
                    "Here is the stack trace: ");
            ex.printStackTrace();
        }

    }

}
