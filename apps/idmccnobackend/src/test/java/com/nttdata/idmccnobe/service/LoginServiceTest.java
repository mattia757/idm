package com.nttdata.idmccnobe.service;

import com.nttdata.idmccnobe.TestReflection;
import com.nttdata.idmccnobe.dao.UserBeDao;
import com.nttdata.idmccnobe.model.UserBe;
import com.nttdata.idmccnobe.util.PasswordUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServiceTest {

    @Test
    public void authenticatesOnlyWithTheCorrectBcryptPassword() throws Exception {
        UserBeDao userBeDao = mock(UserBeDao.class);
        UserBe user = new UserBe();
        user.setUsername("operatore");
        user.setPassword(PasswordUtils.encode("password-corretta"));
        when(userBeDao.findByUsername("operatore")).thenReturn(user);

        LoginService service = new LoginService();
        TestReflection.setField(service, "userBeDao", userBeDao);

        assertSame(user, service.findByUsernameAndPassword("operatore", "password-corretta"));
        assertNull(service.findByUsernameAndPassword("operatore", "password-errata"));
    }
}
