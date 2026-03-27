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

import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasAriaLabel;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasLabel;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.shared.HasThemeVariant;
import com.vaadin.flow.component.shared.HasTooltip;
import com.vaadin.flow.component.shared.Tooltip;

/**
 * A toggle button component built on Vaadin with support for customizable labels and icons.
 *
 * <p>The component displays a switch with optional left and right labels or icons, and fires a
 * value-change event when toggled.
 *
 * @since 1.0.0
 */
@Tag("fc-toggle-button")
@JsModule("./toggle-button.js")
public class ToggleButton extends AbstractSinglePropertyField<ToggleButton, Boolean>
    implements HasSize,
        HasComponents,
        HasLabel,
        HasAriaLabel,
        HasTooltip,
        HasThemeVariant<ToggleButtonVariant> {

    private ItemLabelGenerator<Boolean> itemLabelGenerator = b -> "";

    /** Creates a new toggle button with an initial value of {@code false}. */
    public ToggleButton() {
        this(false);
    }

    /**
     * Creates a new toggle button with the given initial value.
     *
     * @param initialValue the initial checked state
     * @since 1.0.0
     */
    public ToggleButton(boolean initialValue) {
        super("checked", initialValue, false);
        if (initialValue) {
            getElement().setAttribute("checked", "");
        }
        setSynchronizedEvent("checked-changed");
    }

    /**
     * Creates a new toggle button with the given label and an initial value of {@code false}.
     *
     * @param label the label text shown above the toggle
     * @since 1.0.0
     */
    public ToggleButton(String label) {
        this(false);
        setLabel(label);
    }

    /**
     * Creates a new toggle button with the given label and initial value.
     *
     * @param label the label text shown above the toggle
     * @param initialValue the initial checked state
     * @since 1.0.0
     */
    public ToggleButton(String label, boolean initialValue) {
        this(initialValue);
        setLabel(label);
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        getElement().setProperty("readonly", readOnly);
    }

    @Override
    public boolean isReadOnly() {
        return getElement().getProperty("readonly", false);
    }

    @Override
    public Tooltip setTooltipText(String text) {
        Tooltip tooltip = Tooltip.forComponent(this);
        tooltip.setText(text);
        return tooltip;
    }

    /**
     * Sets a generator that provides labels for the checked ({@code true}) and unchecked
     * ({@code false}) states. The generator is called with the state value and its result is used
     * as the right label for {@code true} and the left label for {@code false}.
     *
     * @param itemLabelGenerator the label generator; must not be {@code null}
     * @return this instance for method chaining
     * @since 1.0.0
     */
    public ToggleButton setItemLabelGenerator(ItemLabelGenerator<Boolean> itemLabelGenerator) {
        this.itemLabelGenerator = itemLabelGenerator;
        updateLabels();
        return this;
    }

    private void updateLabels() {
        getElement().setProperty("leftLabel", itemLabelGenerator.apply(false));
        getElement().setProperty("rightLabel", itemLabelGenerator.apply(true));
    }

    /**
     * Enables label highlighting: the label on the active side is shown using the color of the
     * active theme variant ({@link ToggleButtonVariant#PRIMARY PRIMARY},
     * {@link ToggleButtonVariant#SUCCESS SUCCESS}, {@link ToggleButtonVariant#WARNING WARNING},
     * {@link ToggleButtonVariant#ERROR ERROR}, or {@link ToggleButtonVariant#CONTRAST
     * CONTRAST}), falling back to the primary color when no color variant is set. The inactive-side
     * label is dimmed.
     *
     * @return this instance for method chaining
     * @since 1.0.0
     */
    public ToggleButton withHighlightLabel() {
        getElement().setProperty("highlightLabel", true);
        return this;
    }

    /**
     * Places icons adjacent to the switch and labels on the outer edges, producing the layout
     * {@code [left-label] [left-icon] [switch] [right-icon] [right-label]}.
     *
     * <p>By default the order is {@code [left-icon] [left-label] [switch] [right-label]
     * [right-icon]}.
     *
     * @return this instance for method chaining
     * @since 1.0.0
     */
    public ToggleButton withIconsInside() {
        getElement().setProperty("iconsInside", true);
        return this;
    }

    /**
     * Restores the default layout where icons are on the outer edges and labels are adjacent to the
     * switch: {@code [left-icon] [left-label] [switch] [right-label] [right-icon]}.
     *
     * @return this instance for method chaining
     * @since 1.0.0
     */
    public ToggleButton withIconsOutside() {
        getElement().setProperty("iconsInside", false);
        return this;
    }

    /**
     * Disables label highlighting so both labels are rendered with the same color regardless of the
     * toggle state.
     *
     * @return this instance for method chaining
     * @since 1.0.0
     */
    public ToggleButton withoutHighlightLabel() {
        getElement().setProperty("highlightLabel", false);
        return this;
    }

    /**
     * Sets the label displayed on the left side of the toggle switch.
     *
     * @param label the left label text
     * @return this instance for method chaining
     * @since 1.0.0
     */
    public ToggleButton setLeftLabel(String label) {
        getElement().setProperty("leftLabel", label);
        return this;
    }

    /**
     * Sets the label displayed on the right side of the toggle switch.
     *
     * @param label the right label text
     * @return this instance for method chaining
     * @since 1.0.0
     */
    public ToggleButton setRightLabel(String label) {
        getElement().setProperty("rightLabel", label);
        return this;
    }

    /**
     * Sets the icon displayed on the left side of the toggle switch.
     *
     * @param icon the component to use as the left icon
     * @return this instance for method chaining
     * @since 1.0.0
     */
    public ToggleButton setLeftIcon(Component icon) {
        icon.getElement().setAttribute("slot", "left");
        add(icon);
        return this;
    }

    /**
     * Sets the icon displayed on the right side of the toggle switch.
     *
     * @param icon the component to use as the right icon
     * @return this instance for method chaining
     * @since 1.0.0
     */
    public ToggleButton setRightIcon(Component icon) {
        icon.getElement().setAttribute("slot", "right");
        add(icon);
        return this;
    }
}
