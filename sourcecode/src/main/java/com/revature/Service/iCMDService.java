package com.revature.Service;

import java.io.IOException;

public interface iCMDService {
    String executeCommand(String command, String directory) throws IOException, InterruptedException;

    String executeCommand(String command) throws IOException, InterruptedException;
}
