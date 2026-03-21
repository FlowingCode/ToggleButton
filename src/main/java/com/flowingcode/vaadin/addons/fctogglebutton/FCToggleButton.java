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

/** Java bridge for the fc-toggle-button LitElement component. */
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

    public FCToggleButton() {
        this(false);
    }

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

    public void setLeftLabel(String label) {
        getElement().setProperty("leftLabel", label);
    }

    public void setRightLabel(String label) {
        getElement().setProperty("rightLabel", label);
    }

    public void setLeftIcon(Component icon) {
        icon.getElement().setAttribute("slot", "left");
        add(icon);
    }

    public void setRightIcon(Component icon) {
        icon.getElement().setAttribute("slot", "right");
        add(icon);
    }
}
