jave java -jar labs.jar# LabLoader2 Source Code

This folder contains LabLoader2's source code, in case you
are interested in looking at a functioning app following some
good practices. First of all, why LabLoader2? Well... it seems
that my original LabLoader, which is a large project leveraging
a Spring backend and Azure cloud resources, is blocked on Geico's
network.

There's no need to modify the source code, or to run the main
method directly here. You should run the project's jarfile
using the instructions provided in the root level's readme to
run labs.

## Some things to notice

Notice a few things:

 - Javadocs have been written where appropriate

   - Represented by the green block comments, which are comments
   your editor may associate with their related variables,
   methods, classes.

 - Classes have been organized by their role within the program.
   A similar set of divisions would exist for a typical Java web
   API.

   - The com.revature.App class, containing the main method, is special. It 
   is the entry point of the program.
   - Classes within the com.revature.View package manage how input & output
   from the program is interpreted and formatted.
   - Classes within the com.revature.Service package manage programming logic,
   written in Java, that is custom to the specific tasks that the 
   program needs to perform.
   - Classes within the com.revature.Exception package represent the possible
   custom exceptions that may arise during program execution.
   - Classes within the com.revature.Util package represent utility classes,
   which contain static methods and variables shared across the
   entire program.
   
 - Interfaces have been written for some classes, allowing for
   better planning & abstraction of the project.

 - Some tests have been written for the program (todo.. more tests).
 
   - Unit tests test a single, atomic piece of behavior. Usually,
   this is a single method, which is also fully isolated by using
   Mockito to mock the behavior of any classes which the class
   being tested depends on.
   - Integration tests test multiple layers of the project
   working in tandem.
   - End-to-end tests test the entire project.
   
 - A Logger is used to generate information about the state of 
   the program during runtime.

 - Maven has provided some metadata configuration and external
   dependencies (eg JUnit). It's also used to package the project
   into a .jar in the root directory of this workspace, so that
   you can run the project as an easy-to-use CLI.