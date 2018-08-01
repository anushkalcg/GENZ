package com.genz.server.service.user;

import com.genz.server.model.User;
import com.genz.server.service.CommonService;


/**
 * @author Nikos.Toulios
 */
public interface UserService extends CommonService<User> {
    User login(String username, String password);
    void play(Long UserId);
}
