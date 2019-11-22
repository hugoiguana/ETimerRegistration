package br.com.hugo.etimerregistration.controller;

import br.com.hugo.etimerregistration.domain.TimerRegister;
import br.com.hugo.etimerregistration.dto.TimerRegisterDTO;
import br.com.hugo.etimerregistration.service.TimerRegisterService;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static br.com.hugo.etimerregistration.service.TimerRegisterService.DATE_TIME_FORMAT_HH_MM;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class TimerRegisterControllerTest {

    @Mock
    private TimerRegisterService service;

    @Mock
    private MapperFacade mapper;

    @InjectMocks
    @Spy
    private TimerRegisterController controller = new TimerRegisterController();

    private String pis = "123456789";
    private LocalDateTime dateTimePointNow = LocalDateTime.now();
    private TimerRegister timerRegister;
    private TimerRegisterDTO dto;

    @Before
    public void before() {
        timerRegister = TimerRegister.builder().dateTimePoint(dateTimePointNow).build();
        timerRegister.setId(Long.valueOf(1));
        dto = TimerRegisterDTO.builder().pis(pis).dateTimePoint(dateTimePointNow.format(DATE_TIME_FORMAT_HH_MM)).build();
    }

    @Test
    public void insert_return_timerRegister_when_sucess() {
        doReturn(timerRegister).when(service).registerPoint(anyString(), any(LocalDateTime.class));
        doReturn(dto).when(mapper).map(any(TimerRegister.class), eq(TimerRegisterDTO.class));
        ResponseEntity<TimerRegisterDTO> responseEntityReturn = controller.insert(pis);
        assertEquals(responseEntityReturn.getBody(), dto);
    }


}
