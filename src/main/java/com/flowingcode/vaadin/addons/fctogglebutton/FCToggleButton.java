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
