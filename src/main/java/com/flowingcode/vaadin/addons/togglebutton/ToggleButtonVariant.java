/*-
 * #%L
 * Toggle Button Add-On
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
package com.flowingcode.vaadin.addons.togglebutton;

import com.vaadin.flow.component.shared.ThemeVariant;

/**
 * Theme variants for the {@link ToggleButton} component.
 *
 * <p>Size variants ({@link #SMALL}, {@link #MEDIUM}, {@link #LARGE}) and {@link #LONGSWIPE} can be
 * combined (e.g. {@code LONGSWIPE} + {@code LARGE}).
 *
 * @since 1.0.0
 */
public enum ToggleButtonVariant implements ThemeVariant {
    /** Renders the toggle at a smaller size (32×18 px track). */
    SMALL("small"),
    /** Renders the toggle at the default medium size (44×24 px track). */
    MEDIUM("medium"),
    /** Renders the toggle at a larger size (56×32 px track). */
    LARGE("large"),
    /**
     * Renders a wider switch track optimized for touch interaction. Can be combined with size
     * variants: the track width is increased by 28 px while preserving the height of the chosen
     * size.
     */
    LONGSWIPE("longswipe"),
    /** Applies the primary color to the checked state. */
    PRIMARY("primary"),
    /** Applies the success color to the checked state. */
    SUCCESS("success"),
    /** Applies the warning color to the checked state. */
    WARNING("warning"),
    /** Applies the error color to the checked state. */
    ERROR("error"),
    /** Applies the contrast color to the checked state. */
    CONTRAST("contrast");

    private final String variant;

    ToggleButtonVariant(String variant) {
        this.variant = variant;
    }

    @Override
    public String getVariantName() {
        return variant;
    }
}
