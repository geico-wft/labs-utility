package com.revature.Service;

import com.revature.Util.LoggerSingleton;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A service for executing console commands on your machine.
 */
public class CMDService implements iCMDService{

    Logger log;
    public CMDService(){
        this.log = LoggerSingleton.getLogger();
    }

    /**
     * Method executes provided command and returns the full String output, if it
     * exists.
     */
    public String executeCommand(String command, String directory) throws IOException, InterruptedException {
        log.info("Running command: "+command);
        Process process;
        if(directory == null){
            process = Runtime.getRuntime().exec(command, null, null);
        }else{
            process = Runtime.getRuntime().exec(command, null, new File(directory));
        }
        process.waitFor();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));
        String result = "";
        String outputLine = "";
        do{
            outputLine = reader.readLine();
            if(outputLine!=null)
                result += outputLine + "\n";
        }while(outputLine!=null);
        process.waitFor();
        log.info("Command output: "+result);
        return result;
    }

    /**
     * Good example of method overloading - if we don't care for a specific directory, we can provide an
     * overloaded method that takes in no directory argument and chain the other executeCommand method for a
     * shorter method signature & maintaining DRY (don't repeat yourself) principles.
     * @param command
     * @return
     */
    public String executeCommand(String command) throws IOException, InterruptedException {

        return executeCommand(command, null);
    }

}
