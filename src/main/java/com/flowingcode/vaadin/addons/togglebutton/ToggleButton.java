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

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.HasAriaLabel;
import com.vaadin.flow.component.HasHelper;
import com.vaadin.flow.component.HasLabel;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.checkbox.Switch;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.shared.HasThemeVariant;
import com.vaadin.flow.component.shared.HasTooltip;
import com.vaadin.flow.component.shared.HasValidationProperties;
import com.vaadin.flow.component.shared.Tooltip;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A toggle button component built on the core Vaadin {@link Switch}, with support for customizable
 * labels and icons on both sides.
 *
 * <p>The switch itself (value, keyboard, focus, ARIA role and state, read-only and disabled
 * behavior) is provided by an inner {@link Switch}. This component adds the field label, the side
 * labels and icons, label highlighting, helper text, error message and theme variants around it.
 * All of them are rendered as regular (light DOM) elements, so the switch can reference them for
 * accessibility and they can be styled from the application.
 *
 * @since 1.0.0
 */
@Tag("fc-toggle-button")
@JsModule("./fc-toggle-button.js")
@CssImport("./styles/fc-toggle-button.css")
public class ToggleButton extends AbstractField<ToggleButton, Boolean>
    implements HasSize,
        HasLabel,
        HasAriaLabel,
        HasTooltip,
        HasHelper,
        HasValidationProperties,
        Focusable<ToggleButton>,
        HasThemeVariant<ToggleButtonVariant> {

    private static final AtomicInteger ID_SEQUENCE = new AtomicInteger();

    private final String idPrefix = "fc-toggle-button-" + ID_SEQUENCE.incrementAndGet();

    private final Switch toggle = new Switch();
    private final Span label = part(new Span(), "label");
    private final Div row = part(new Div(), "row");
    private final Span leftLabel = part(new Span(), "left-label");
    private final Span rightLabel = part(new Span(), "right-label");
    private final Div helper = part(new Div(), "helper");
    private final Span error = part(new Span(), "error");

    private Component leftIcon;
    private Component rightIcon;
    private Component helperComponent;
    private String ariaLabel;
    private String ariaLabelledBy;
    private String errorMessage;

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
        super(false);
        // The wrapper owns validation; the inner switch only reflects the invalid state
        toggle.setManualValidation(true);
        toggle.addValueChangeListener(e -> {
            if (e.isFromClient()) {
                setModelValue(e.getValue(), true);
            }
        });

        // Side labels are read through aria-labelledby when needed, not in reading order
        leftLabel.getElement().setAttribute("aria-hidden", "true");
        rightLabel.getElement().setAttribute("aria-hidden", "true");
        error.getElement().setAttribute("aria-live", "assertive");
        Stream.of(label, leftLabel, rightLabel, helper, error).forEach(c -> c.setVisible(false));

        row.add(leftLabel, toggle, rightLabel);
        getElement().appendChild(label.getElement(), row.getElement(), helper.getElement(),
            error.getElement());
        setValue(initialValue);
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

    private <C extends Component> C part(C component, String name) {
        component.setId(idPrefix + "-" + name);
        component.getElement().getClassList().add("fc-toggle-button-" + name);
        return component;
    }

    private static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }

    @Override
    protected void setPresentationValue(Boolean value) {
        toggle.setValue(Boolean.TRUE.equals(value));
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
        toggle.setReadOnly(readOnly);
    }

    @Override
    public void setRequiredIndicatorVisible(boolean requiredIndicatorVisible) {
        super.setRequiredIndicatorVisible(requiredIndicatorVisible);
        // Sets aria-required on the native input; the inner indicator is hidden by CSS
        toggle.setRequiredIndicatorVisible(requiredIndicatorVisible);
    }

    @Override
    public void setLabel(String label) {
        this.label.setText(label);
        this.label.setVisible(!isBlank(label));
        updateAccessibleName();
    }

    @Override
    public String getLabel() {
        return label.getText();
    }

    @Override
    public void setAriaLabel(String ariaLabel) {
        this.ariaLabel = ariaLabel;
        updateAccessibleName();
    }

    @Override
    public Optional<String> getAriaLabel() {
        return Optional.ofNullable(ariaLabel);
    }

    @Override
    public void setAriaLabelledBy(String ariaLabelledBy) {
        this.ariaLabelledBy = ariaLabelledBy;
        updateAccessibleName();
    }

    @Override
    public Optional<String> getAriaLabelledBy() {
        return Optional.ofNullable(ariaLabelledBy);
    }

    /**
     * The accessible name goes on the inner switch: the ARIA labelledby set by the caller, otherwise
     * the ARIA label, otherwise the field label, otherwise the side labels. Labels are referenced by
     * id, so they stay in sync.
     */
    private void updateAccessibleName() {
        if (!isBlank(ariaLabelledBy)) {
            toggle.setAriaLabel(null);
            toggle.setAriaLabelledBy(ariaLabelledBy);
            return;
        }
        if (!isBlank(ariaLabel)) {
            toggle.setAriaLabelledBy((String) null);
            toggle.setAriaLabel(ariaLabel);
            return;
        }
        String ids = (label.isVisible() ? Stream.of(label) : Stream.of(leftLabel, rightLabel))
            .filter(Component::isVisible)
            .map(c -> c.getId().orElseThrow())
            .collect(Collectors.joining(" "));
        toggle.setAriaLabel(null);
        toggle.setAriaLabelledBy(ids.isEmpty() ? null : ids);
    }

    @Override
    public void focus() {
        toggle.focus();
    }

    @Override
    public void blur() {
        toggle.blur();
    }

    @Override
    public Tooltip setTooltipText(String text) {
        return toggle.setTooltipText(text);
    }

    @Override
    public Tooltip getTooltip() {
        return toggle.getTooltip();
    }

    @Override
    public void setHelperText(String helperText) {
        setHelperComponent(isBlank(helperText) ? null : new Span(helperText));
    }

    @Override
    public String getHelperText() {
        return helperComponent instanceof Span span ? span.getText() : null;
    }

    @Override
    public void setHelperComponent(Component component) {
        helper.removeAll();
        helperComponent = component;
        if (component != null) {
            helper.add(component);
        }
        helper.setVisible(component != null);
        updateDescribedBy();
    }

    @Override
    public Component getHelperComponent() {
        return helperComponent;
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        error.setText(errorMessage);
        updateError();
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void setInvalid(boolean invalid) {
        getElement().setProperty("invalid", invalid);
        toggle.setInvalid(invalid);
        updateError();
    }

    @Override
    public boolean isInvalid() {
        return getElement().getProperty("invalid", false);
    }

    private void updateError() {
        error.setVisible(isInvalid() && !isBlank(errorMessage));
        updateDescribedBy();
    }

    /** Helper and error message are linked to the switch by id. */
    private void updateDescribedBy() {
        String ids = Stream.of(helper, error)
            .filter(Component::isVisible)
            .map(c -> c.getId().orElseThrow())
            .collect(Collectors.joining(" "));
        toggle.setAriaDescribedBy(ids.isEmpty() ? null : ids);
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
        Objects.requireNonNull(itemLabelGenerator);
        setLeftLabel(itemLabelGenerator.apply(false));
        setRightLabel(itemLabelGenerator.apply(true));
        return this;
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
        getElement().setAttribute("highlight-label", true);
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
        getElement().setAttribute("icons-inside", true);
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
        getElement().setAttribute("icons-inside", false);
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
        getElement().setAttribute("highlight-label", false);
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
        leftLabel.setText(label);
        leftLabel.setVisible(!isBlank(label));
        updateAccessibleName();
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
        rightLabel.setText(label);
        rightLabel.setVisible(!isBlank(label));
        updateAccessibleName();
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
        leftIcon = replaceIcon(leftIcon, icon, "left-icon");
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
        rightIcon = replaceIcon(rightIcon, icon, "right-icon");
        return this;
    }

    private Component replaceIcon(Component oldIcon, Component icon, String className) {
        if (oldIcon != null) {
            row.remove(oldIcon);
        }
        if (icon != null) {
            icon.getElement().getClassList().add("fc-toggle-button-" + className);
            icon.getElement().setAttribute("aria-hidden", "true");
            row.add(icon);
        }
        return icon;
    }
}
