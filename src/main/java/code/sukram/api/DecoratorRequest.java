package code.sukram.api;

import code.sukram.model.PropertyType;

import java.util.List;

public record DecoratorRequest(List<PropertyType> properties) {
}
