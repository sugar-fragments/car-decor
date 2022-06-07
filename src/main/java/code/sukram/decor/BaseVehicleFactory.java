package code.sukram.decor;

import code.sukram.api.DecoratorResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BaseVehicleFactory implements Function<DecoratorResponse, DecoratorResponse> {

    public DecoratorResponse create() {
        DecoratorResponse dto = new DecoratorResponse();
        dto.setColor("Gray");
        dto.setModel("Opel");
        dto.setManualTransmission(true);
        return dto;
    }

    @Override
    public DecoratorResponse apply(DecoratorResponse dto) {
        return dto;
    }
}
