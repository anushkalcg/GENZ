package com.genz.server.service.user;

import com.genz.server.model.Group;
import com.genz.server.model.User;
import com.genz.server.service.CommonService;

import java.util.List;

public interface UserService extends CommonService<User> {
    User login(String username, String password);
    void play(Long UserId);
}
