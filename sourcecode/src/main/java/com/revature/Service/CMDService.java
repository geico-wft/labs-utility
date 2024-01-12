package com.revature.Service;

import com.revature.Util.LoggerSingleton;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A quick utility for executing console commands on your machine.
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
    public String executeCommand(String command) throws IOException, InterruptedException {
        log.info("Running command: "+command);

        Process process = Runtime.getRuntime().exec(command);
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

        return result;
    }
}
