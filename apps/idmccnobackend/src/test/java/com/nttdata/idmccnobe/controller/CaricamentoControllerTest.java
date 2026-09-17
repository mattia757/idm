package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.TestReflection;
import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.service.CaricamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

public class CaricamentoControllerTest {

    @Test
    public void rejectsFileWithUnsupportedExtensionBeforeProcessingIt() throws Exception {
        CaricamentoService caricamentoService = mock(CaricamentoService.class);
        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("utenti.txt");
        CaricamentoController controller = new CaricamentoController();
        TestReflection.setField(controller, "caricamentoService", caricamentoService);

        ModelAndView result = controller.pushNotificationLoad(new UserProfile(), file);

        assertEquals("Sono ammessi solo file Excel (.xls, .xlsx)", result.getModel().get("errorMessage"));
        verifyNoInteractions(caricamentoService);
    }
}
