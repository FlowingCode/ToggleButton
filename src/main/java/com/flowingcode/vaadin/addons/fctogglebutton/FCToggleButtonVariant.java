package com.flowingcode.vaadin.addons.fctogglebutton;

import com.vaadin.flow.component.shared.ThemeVariant;

/** Enum for theme variants supported by the {@link FCToggleButton} component. */
public enum FCToggleButtonVariant implements ThemeVariant {
    SMALL("small"),
    //TODO: añadir medium
    LARGE("large"),
    PRIMARY("primary"),
    SUCCESS("success"),
    WARNING("warning"),
    ERROR("error");

    private final String variant;

    FCToggleButtonVariant(String variant) {
        this.variant = variant;
    }

    @Override
    public String getVariantName() {
        return variant;
    }
}
