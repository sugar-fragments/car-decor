package code.sukram.api;

import code.sukram.decor.BaseVehicleFactory;
import code.sukram.decor.VehicleDecorators;
import code.sukram.model.PropertyType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

@RestController
public class DecoratorController {

    private static final Map<PropertyType, Function<DecoratorResponse, DecoratorResponse>> PROPERTIES = Map.of(
            PropertyType.COLOR, VehicleDecorators::improveColor,
            PropertyType.MODEL, VehicleDecorators::addModel,
            PropertyType.TRANSMISSION, VehicleDecorators::automaticTransmission
    );

    private final BaseVehicleFactory baseVehicleFactory;

    public DecoratorController(BaseVehicleFactory baseVehicleFactory) {
        this.baseVehicleFactory = baseVehicleFactory;
    }

    @PostMapping(value = "/decorator", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DecoratorResponse> decorateVehicle(@RequestBody DecoratorRequest request) {
        AtomicReference<DecoratorResponse> baseVehicle = new AtomicReference<>(baseVehicleFactory.create());
        request.properties()
                .forEach(property -> baseVehicle.set(baseVehicleFactory.andThen(PROPERTIES.get(property)).apply(baseVehicle.get())));
        return new ResponseEntity<>(baseVehicle.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/decorator", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DecoratorResponse> fullyDecoratedVehicle() {
        DecoratorResponse vehicle = baseVehicleFactory
                .andThen(VehicleDecorators::addModel)
                .andThen(VehicleDecorators::improveColor)
                .andThen(VehicleDecorators::automaticTransmission)
                .apply(baseVehicleFactory.create());
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }
}
