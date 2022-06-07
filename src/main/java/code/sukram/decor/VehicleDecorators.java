package code.sukram.decor;

import code.sukram.api.DecoratorResponse;

public class VehicleDecorators {

    private VehicleDecorators() {
    }

    public static DecoratorResponse addModel(DecoratorResponse dto) {
        dto.setModel(dto.getModel() + " Vectra");
        return dto;
    }

    public static DecoratorResponse improveColor(DecoratorResponse dto) {
        dto.setColor(dto.getColor() + " Metallic");
        return dto;
    }

    public static DecoratorResponse automaticTransmission(DecoratorResponse dto) {
        dto.setManualTransmission(false);
        return dto;
    }
}
