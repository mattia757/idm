package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.TestReflection;
import com.nttdata.idmccnobe.dto.ExportUserExcelRequest;
import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.dto.VerifyLoyaltyResDto;
import com.nttdata.idmccnobe.service.ExportUsersService;
import com.nttdata.idmccnobe.service.LoyaltyService;
import com.nttdata.idmccnobe.service.UtilsService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

public class CooperativaAuthorizationTest {

    @Test
    public void deniesLoyaltyOperationOutsideTheUsersCooperativa() throws Exception {
        LoyaltyService loyaltyService = mock(LoyaltyService.class);
        LoyaltyRestController controller = new LoyaltyRestController();
        TestReflection.setField(controller, "loyaltyService", loyaltyService);

        VerifyLoyaltyResDto result = controller.verifyLoyaltyInfo(user("OPERATORE", 1), "123", "01/01/1980", "2");

        assertEquals("KO", result.getHeader().getResult());
        verifyNoInteractions(loyaltyService);
    }

    @Test
    public void allowsAdminToUseTheRequestedCooperativa() throws Exception {
        LoyaltyService loyaltyService = mock(LoyaltyService.class);
        VerifyLoyaltyResDto expected = new VerifyLoyaltyResDto();
        when(loyaltyService.verifyLoyaltyInfo("123", "01/01/1980", "2")).thenReturn(expected);
        LoyaltyRestController controller = new LoyaltyRestController();
        TestReflection.setField(controller, "loyaltyService", loyaltyService);

        assertSame(expected, controller.verifyLoyaltyInfo(user("ADMIN", 0), "123", "01/01/1980", "2"));
    }

    @Test
    public void deniesExportOutsideTheUsersCooperativa() throws Exception {
        ExportUsersService exportUsersService = mock(ExportUsersService.class);
        ExportExcelController controller = new ExportExcelController();
        TestReflection.setField(controller, "exportUsersService", exportUsersService);
        TestReflection.setField(controller, "utilsService", mock(UtilsService.class));
        ExportUserExcelRequest request = new ExportUserExcelRequest("2", "1", "1");

        HttpServletRequest httpRequest = mock(HttpServletRequest.class);
        when(httpRequest.getContextPath()).thenReturn("/IdmCCNOBackend");
        ModelAndView result = controller.exportUtenti(user("OPERATORE", 1), request, mock(HttpServletResponse.class), httpRequest);

        assertEquals("Operazione non autorizzata per la cooperativa selezionata", result.getModel().get("errorMessage"));
        verifyNoInteractions(exportUsersService);
    }

    private UserProfile user(String role, int cooperativa) {
        UserProfile user = new UserProfile();
        user.setUsername("utente-test");
        user.setRole(role);
        user.setIdCooperativa(cooperativa);
        return user;
    }
}
