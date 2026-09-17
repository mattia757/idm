package com.nttdata.idmccnobe.service;

import com.nttdata.idmccnobe.dao.UserBeDao;
import com.nttdata.idmccnobe.model.UserBe;
import com.nttdata.idmccnobe.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DelorenziVa
 */
@Service("LoginService")
@Transactional
public class LoginService extends AbstractService{
    
    @Autowired 
    private UserBeDao userBeDao;
    

    public UserBe findByUsernameAndPassword(String username, String password) {
        UserBe user = userBeDao.findByUsername(username);
        if (user == null || !PasswordUtils.matches(password, user.getPassword())) {
            return null;
        }
        if (PasswordUtils.needsRehash(user.getPassword())) {
            user.setPassword(PasswordUtils.encode(password));
            userBeDao.saveOrUpdate(user);
        }
        return user;
    }
}
