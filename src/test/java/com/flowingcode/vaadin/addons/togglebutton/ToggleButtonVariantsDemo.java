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

import com.flowingcode.vaadin.addons.demo.DemoSource;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@DemoSource
@PageTitle("Theme Variants")
@SuppressWarnings("serial")
@Route(value = "variants", layout = ToggleButtonDemoView.class)
public class ToggleButtonVariantsDemo extends Div {

  public ToggleButtonVariantsDemo() {

    // Color variants
    ToggleButton primary = new ToggleButton(true).setLeftLabel("Primary");
    primary.setId("primary");
    primary.addThemeVariants(ToggleButtonVariant.PRIMARY);

    ToggleButton success = new ToggleButton(true).setLeftLabel("Success");
    success.setId("success");
    success.addThemeVariants(ToggleButtonVariant.SUCCESS);

    ToggleButton error = new ToggleButton(true).setLeftLabel("Error");
    error.setId("error");
    error.addThemeVariants(ToggleButtonVariant.ERROR);

    ToggleButton warning = new ToggleButton(true).setLeftLabel("Warning");
    warning.setId("warning");
    warning.addThemeVariants(ToggleButtonVariant.WARNING);

    ToggleButton contrast = new ToggleButton(true).setLeftLabel("Contrast");
    contrast.setId("contrast");
    contrast.addThemeVariants(ToggleButtonVariant.CONTRAST);

    // Size variants
    ToggleButton small = new ToggleButton().setRightLabel("Small");
    small.setId("small");
    small.addThemeVariants(ToggleButtonVariant.SMALL);

    ToggleButton medium = new ToggleButton().setRightLabel("Medium");
    medium.setId("medium");
    medium.addThemeVariants(ToggleButtonVariant.MEDIUM);

    ToggleButton large = new ToggleButton().setRightLabel("Large");
    large.setId("large");
    large.addThemeVariants(ToggleButtonVariant.LARGE);

    // Long swipe variants
    ToggleButton longswipeSmall = new ToggleButton().setRightLabel("Small");
    longswipeSmall.addThemeVariants(ToggleButtonVariant.LONGSWIPE, ToggleButtonVariant.SMALL);

    ToggleButton longswipeMedium = new ToggleButton().setRightLabel("Medium");
    longswipeMedium.addThemeVariants(ToggleButtonVariant.LONGSWIPE, ToggleButtonVariant.MEDIUM);

    ToggleButton longswipeLarge = new ToggleButton().setRightLabel("Large");
    longswipeLarge.addThemeVariants(ToggleButtonVariant.LONGSWIPE, ToggleButtonVariant.LARGE);

    // States
    ToggleButton disabled = new ToggleButton().setRightLabel("Disabled");
    disabled.setId("disabled");
    disabled.setEnabled(false);

    ToggleButton readOnly = new ToggleButton(true).setRightLabel("Read-only");
    readOnly.setId("read-only");
    readOnly.setReadOnly(true);

    HorizontalLayout colorRow = new HorizontalLayout(primary, success, error, warning, contrast);
    HorizontalLayout sizeRow = new HorizontalLayout(small, medium, large);
    HorizontalLayout longswipeRow = new HorizontalLayout(longswipeSmall, longswipeMedium, longswipeLarge);
    HorizontalLayout statesRow = new HorizontalLayout(disabled, readOnly);
    colorRow.getStyle().set("gap", "var(--lumo-space-l)");
    sizeRow.getStyle().set("gap", "var(--lumo-space-l)");
    longswipeRow.getStyle().set("gap", "var(--lumo-space-l)");
    statesRow.getStyle().set("gap", "var(--lumo-space-l)");

    add(new VerticalLayout(
        new H3("Color variants"),
        colorRow,
        new H3("Size variants"),
        sizeRow,
        new H3("Long swipe"),
        longswipeRow,
        new H3("States"),
        statesRow));
  }
}
