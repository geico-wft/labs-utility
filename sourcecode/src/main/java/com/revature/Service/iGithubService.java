package com.revature.Service;

import com.revature.Exception.GitConfigException;
import com.revature.Exception.LabOpenException;
import com.revature.Exception.LabSaveException;

/**
 * An interface defining the expected behavior of a GithubService. This allows us to
 * easily plan out our application and also easily change or swap implementing classes
 * in a future refactor.
 * Interfaces provide abstract (meaning, no-implementation) methods which must be implemented
 * by a future class. They help us achieve abstraction by allowing us to define behavior without
 * implementation, making our code simpler for a human to interpret and use.
 * Interfaces are also referred to as a 'contractual guarantee of behavior', allowing the
 * compiler to know that any class which is also of type iFileService is guaranteed to
 * exhibit certain behaviors.
 */
public interface iGithubService {

    void open(String labName) throws LabOpenException, GitConfigException;

    void save() throws LabSaveException, GitConfigException;

    String getUsername() throws GitConfigException;
}
