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
@Route(value = "variants", layout = FCToggleButtonDemoView.class)
public class FCToggleButtonVariantsDemo extends Div {

  public FCToggleButtonVariantsDemo() {

    // Color variants
    FCToggleButton primary = new FCToggleButton(true).setLeftLabel("Primary");
    primary.addThemeVariants(FCToggleButtonVariant.PRIMARY);

    FCToggleButton success = new FCToggleButton(true).setLeftLabel("Success");
    success.addThemeVariants(FCToggleButtonVariant.SUCCESS);

    FCToggleButton error = new FCToggleButton(true).setLeftLabel("Error");
    error.addThemeVariants(FCToggleButtonVariant.ERROR);

    FCToggleButton warning = new FCToggleButton(true).setLeftLabel("Warning");
    warning.addThemeVariants(FCToggleButtonVariant.WARNING);

    FCToggleButton contrast = new FCToggleButton(true).setLeftLabel("Contrast");
    contrast.addThemeVariants(FCToggleButtonVariant.CONTRAST);

    // Size variants
    FCToggleButton small = new FCToggleButton().setRightLabel("Small");
    small.addThemeVariants(FCToggleButtonVariant.SMALL);

    FCToggleButton medium = new FCToggleButton().setRightLabel("Medium");
    medium.addThemeVariants(FCToggleButtonVariant.MEDIUM);

    FCToggleButton large = new FCToggleButton().setRightLabel("Large");
    large.addThemeVariants(FCToggleButtonVariant.LARGE);

    // States
    FCToggleButton disabled = new FCToggleButton().setRightLabel("Disabled");
    disabled.setEnabled(false);

    FCToggleButton readOnly = new FCToggleButton(true).setRightLabel("Read-only");
    readOnly.setReadOnly(true);

    add(new VerticalLayout(
        new H3("Color variants"),
        new HorizontalLayout(primary, success, error, warning, contrast),
        new H3("Size variants"),
        new HorizontalLayout(small, medium, large),
        new H3("States"),
        new HorizontalLayout(disabled, readOnly)));
  }
}
