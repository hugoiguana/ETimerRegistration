package br.com.hugo.etimerregistration.config.orika.mapper;

import br.com.hugo.etimerregistration.controller.dto.TimerRegisterDayDto;
import br.com.hugo.etimerregistration.domain.TimerRegisterDay;
import br.com.hugo.etimerregistration.util.DateUtil;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.MappingDirection;
import org.springframework.stereotype.Component;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static br.com.hugo.etimerregistration.util.DateUtil.DT_FORMAT_DD_MM_YYYY;
import static br.com.hugo.etimerregistration.util.DateUtil.toHourMinunteString;

@Component
public class TimerRegisterDayDtoMapper extends CustomMapper<TimerRegisterDay, TimerRegisterDayDto> {

    public TimerRegisterDayDtoMapper(MapperFactory mapperFactory) {
        mapperFactory.classMap(TimerRegisterDay.class, TimerRegisterDayDto.class)
                .customize(this)
                .byDefault(MappingDirection.A_TO_B)
                .register();
    }

    @Override
    public void mapAtoB(TimerRegisterDay tdDay, TimerRegisterDayDto dto, MappingContext context) {

        dto.setTimeWorked(toHourMinunteString(tdDay.getHoursWorked()));
        dto.setTimeRest(toHourMinunteString(tdDay.getHoursRested()));
        dto.setTimeBalance(toHourMinunteString(tdDay.getHoursBalance()));
        dto.setTimeRequiredLeftToRest(toHourMinunteString(tdDay.getHoursRequiredLeftToRest()));

        if (tdDay.getDateDay() != null) {
            String dtDayStr = String.format("%s - %s", tdDay.getDateDay().format(DT_FORMAT_DD_MM_YYYY)
                    , tdDay.getDateDay().getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
            dto.setDateDay(dtDayStr);
        }

        if (tdDay.getDateTimePointList() != null) {
            List<String> pointList = tdDay.getDateTimePointList().stream().sorted()
                    .map(p -> p.format(DateUtil.DT_FORMAT_HH_MM))
                    .collect(Collectors.toList());

            dto.setPointList(pointList);
        }
    }
}
