package br.com.hugo.etimerregistration.config.orika.mapper;

import br.com.hugo.etimerregistration.domain.TimerRegister;
import br.com.hugo.etimerregistration.controller.dto.TimerRegisterDTO;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.MappingDirection;
import org.springframework.stereotype.Component;

import static br.com.hugo.etimerregistration.util.DateUtil.DT_FORMAT_DD_MM_YYYY_HH_MM;

@Component
public class TimerRegisterDtoMapper extends CustomMapper<TimerRegister, TimerRegisterDTO> {

    public TimerRegisterDtoMapper(MapperFactory mapperFactory) {
        mapperFactory.classMap(TimerRegister.class, TimerRegisterDTO.class)
                .customize(this)
                .byDefault(MappingDirection.A_TO_B)
                .register();
    }

    @Override
    public void mapAtoB(TimerRegister timerRegister, TimerRegisterDTO dto, MappingContext context) {
        if (timerRegister.getDateTimePoint() != null) {
            dto.setDateTimePoint(timerRegister.getDateTimePoint().format(DT_FORMAT_DD_MM_YYYY_HH_MM));
        }
        if (timerRegister.getEmployee() != null) {
            dto.setPis(timerRegister.getEmployee().getPis());
        }
    }
}