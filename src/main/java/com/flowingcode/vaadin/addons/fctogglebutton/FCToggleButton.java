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

import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasAriaLabel;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
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
@JsModule("./fc-toggle-button.js")
@NpmPackage(value = "lit", version = "3.3.2")
public class FCToggleButton extends AbstractSinglePropertyField<FCToggleButton, Boolean>
    implements HasSize,
        HasComponents,
        HasAriaLabel,
        HasTooltip,
        HasThemeVariant<FCToggleButtonVariant> {

    private ItemLabelGenerator<Boolean> itemLabelGenerator = b -> "";

    /** Creates a new toggle button with an initial value of {@code false}. */
    public FCToggleButton() {
        this(false);
    }

    /**
     * Creates a new toggle button with the given initial value.
     *
     * @param initialValue the initial checked state
     */
    public FCToggleButton(boolean initialValue) {
        super("checked", initialValue, false);
        if (initialValue) {
            getElement().setAttribute("checked", "");
        }
        setSynchronizedEvent("checked-changed");
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
     * @since 1.0.0
     */
    public void setItemLabelGenerator(ItemLabelGenerator<Boolean> itemLabelGenerator) {
        this.itemLabelGenerator = itemLabelGenerator;
        updateLabels();
    }

    @Override
    public void setValue(Boolean value) {
        super.setValue(value);
        updateLabels();
    }

    private void updateLabels() {
        getElement().setProperty("leftLabel", itemLabelGenerator.apply(false));
        getElement().setProperty("rightLabel", itemLabelGenerator.apply(true));
    }

    /**
     * Sets the label displayed on the left side of the toggle switch.
     *
     * @param label the left label text
     * @since 1.0.0
     */
    public void setLeftLabel(String label) {
        getElement().setProperty("leftLabel", label);
    }

    /**
     * Sets the label displayed on the right side of the toggle switch.
     *
     * @param label the right label text
     * @since 1.0.0
     */
    public void setRightLabel(String label) {
        getElement().setProperty("rightLabel", label);
    }

    /**
     * Sets the icon displayed on the left side of the toggle switch.
     *
     * @param icon the component to use as the left icon
     * @since 1.0.0
     */
    public void setLeftIcon(Component icon) {
        icon.getElement().setAttribute("slot", "left");
        add(icon);
    }

    /**
     * Sets the icon displayed on the right side of the toggle switch.
     *
     * @param icon the component to use as the right icon
     * @since 1.0.0
     */
    public void setRightIcon(Component icon) {
        icon.getElement().setAttribute("slot", "right");
        add(icon);
    }
}
