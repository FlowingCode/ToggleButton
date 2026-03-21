/*-
 * #%L
 * FC Toggle Button Add-On
 * %%
 * Copyright (C) 2026 Flowing Code
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.flowingcode.vaadin.addons.fctogglebutton;

import com.vaadin.flow.component.shared.ThemeVariant;

/**
 * Theme variants for the {@link FCToggleButton} component.
 *
 * @since 1.0.0
 */
public enum FCToggleButtonVariant implements ThemeVariant {
    SMALL("small"),
    //TODO: añadir medium
    LARGE("large"),
    PRIMARY("primary"),
    SUCCESS("success"),
    WARNING("warning"),
    ERROR("error"),
    CONTRAST("contrast");

    private final String variant;

    FCToggleButtonVariant(String variant) {
        this.variant = variant;
    }

    @Override
    public String getVariantName() {
        return variant;
    }
}
