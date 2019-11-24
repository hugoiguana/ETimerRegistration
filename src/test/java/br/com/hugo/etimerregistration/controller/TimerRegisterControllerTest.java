package br.com.hugo.etimerregistration.controller;

import br.com.hugo.etimerregistration.domain.TimerRegister;
import br.com.hugo.etimerregistration.controller.dto.TimerRegisterDTO;
import br.com.hugo.etimerregistration.controller.dto.TimerRegisterDayDto;
import br.com.hugo.etimerregistration.controller.dto.TimerResgisterMonthSheetResource;
import br.com.hugo.etimerregistration.service.TimerRegisterMonthService;
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

import java.time.LocalDate;
import java.time.LocalDateTime;

import static br.com.hugo.etimerregistration.util.DateUtil.DT_FORMAT_DD_MM_YYYY_HH_MM;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class TimerRegisterControllerTest {

    @Mock
    private TimerRegisterService service;

    @Mock
    private TimerRegisterMonthService timerRegisterMonthService;

    @Mock
    private MapperFacade mapper;

    @InjectMocks
    @Spy
    private TimerRegisterController controller = new TimerRegisterController();

    private String pis = "123456789";
    private LocalDateTime dateTimePointNow = LocalDateTime.now();
    private TimerRegister timerRegister;
    private TimerRegisterDTO dto;

    private int day = 1;
    private int month = 10;
    private int year = 2019;
    private String dateStr = "01/10/2019";
    private LocalDate period = LocalDate.of(year, month, day);
    private String employeeName = "hugo";

    private TimerRegisterDayDto trdDay1;

    @Before
    public void before() {
        timerRegister = TimerRegister.builder().dateTimePoint(dateTimePointNow).build();
        timerRegister.setId(Long.valueOf(1));
        dto = TimerRegisterDTO.builder().pis(pis).dateTimePoint(dateTimePointNow.format(DT_FORMAT_DD_MM_YYYY_HH_MM)).build();

        trdDay1 = TimerRegisterDayDto.builder().dateDay("01/10/2019").timeWorked("08:00").pointList(asList("07:00", "11:00", "12:00", "16:00")).build();
    }

    @Test
    public void insert_return_timerRegister_when_sucess() {
        doReturn(timerRegister).when(service).registerPoint(anyString(), any(LocalDateTime.class));
        doReturn(dto).when(mapper).map(any(TimerRegister.class), eq(TimerRegisterDTO.class));
        ResponseEntity<TimerRegisterDTO> responseEntityReturn = controller.insert(pis);
        assertEquals(responseEntityReturn.getBody(), dto);
    }

    @Test
    public void getSheet() {

        TimerResgisterMonthSheetResource resource = TimerResgisterMonthSheetResource.builder()
                .employeeName(employeeName)
                .timeBalance("")
                .employeePis(pis)
                .build();

        resource.addTimerRegisterDay(trdDay1);

        doReturn(resource).when(timerRegisterMonthService).createTimerRegisterMonthSheet(pis, period);
        ResponseEntity<TimerResgisterMonthSheetResource> responseEntity = controller.getSheet(pis, dateStr);
        TimerResgisterMonthSheetResource resourceReturned = responseEntity.getBody();

        assertEquals(resource.getEmployeeName(), resourceReturned.getEmployeeName());
        assertEquals(resource.getEmployeePis(), resourceReturned.getEmployeePis());
        assertEquals(resource.getTimeBalance(), resourceReturned.getTimeBalance());
        assertEquals(resource.getTimerRegisterDayList().get(0).getDateDay(), resourceReturned.getTimerRegisterDayList().get(0).getDateDay());
        assertEquals(resource.getTimerRegisterDayList().get(0).getTimeWorked(), resourceReturned.getTimerRegisterDayList().get(0).getTimeWorked());
    }

}
