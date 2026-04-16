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
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@DemoSource
@PageTitle("Labels and Icons")
@SuppressWarnings("serial")
@Route(value = "togglebutton/labels", layout = ToggleButtonDemoView.class)
public class ToggleButtonDemo extends Div {

  public ToggleButtonDemo() {

    ToggleButton basic = new ToggleButton();
    basic.setId("basic");

    ToggleButton withLabel = new ToggleButton("Notifications");

    ToggleButton withLabelAndInitialValue = new ToggleButton("Dark mode", true)
        .setLeftLabel("Off")
        .setRightLabel("On");

    ToggleButton withLeftLabel = new ToggleButton()
        .setLeftLabel("Off");
    withLeftLabel.setId("with-left-label");

    ToggleButton withRightLabel = new ToggleButton()
        .setRightLabel("On");
    withRightLabel.setId("with-right-label");

    ToggleButton withBothLabels = new ToggleButton()
        .setLeftLabel("Off")
        .setRightLabel("On");
    withBothLabels.setId("with-both-labels");

    ToggleButton withLeftIcon = new ToggleButton()
        .setLeftIcon(new Icon(VaadinIcon.MOON));

    ToggleButton withRightIcon = new ToggleButton()
        .setRightIcon(new Icon(VaadinIcon.SUN_O));

    ToggleButton withBothIcons = new ToggleButton()
        .setLeftIcon(new Icon(VaadinIcon.MOON))
        .setRightIcon(new Icon(VaadinIcon.SUN_O));

    ToggleButton withLabelsAndIcons = new ToggleButton()
        .setLeftIcon(new Icon(VaadinIcon.MOON))
        .setLeftLabel("Dark")
        .setRightLabel("Light")
        .setRightIcon(new Icon(VaadinIcon.SUN_O));

    ToggleButton withIconsInside = new ToggleButton()
        .setLeftIcon(new Icon(VaadinIcon.MOON))
        .setLeftLabel("Dark")
        .setRightLabel("Light")
        .setRightIcon(new Icon(VaadinIcon.SUN_O))
        .withIconsInside();

    ToggleButton highlightPrimary = new ToggleButton(true).setLeftLabel("Off").setRightLabel("On").withHighlightLabel();
    highlightPrimary.setId("highlight-primary");
    highlightPrimary.addThemeVariants(ToggleButtonVariant.PRIMARY);

    ToggleButton highlightSuccess = new ToggleButton(true).setLeftLabel("Off").setRightLabel("On").withHighlightLabel();
    highlightSuccess.addThemeVariants(ToggleButtonVariant.SUCCESS);

    ToggleButton highlightWarning = new ToggleButton(true).setLeftLabel("Off").setRightLabel("On").withHighlightLabel();
    highlightWarning.addThemeVariants(ToggleButtonVariant.WARNING);

    ToggleButton highlightError = new ToggleButton(true).setLeftLabel("Off").setRightLabel("On").withHighlightLabel();
    highlightError.addThemeVariants(ToggleButtonVariant.ERROR);

    ToggleButton highlightContrast = new ToggleButton(true).setLeftLabel("Off").setRightLabel("On").withHighlightLabel();
    highlightContrast.addThemeVariants(ToggleButtonVariant.CONTRAST);

    HorizontalLayout fieldLabelRow = new HorizontalLayout(withLabel, withLabelAndInitialValue);
    HorizontalLayout labelsRow = new HorizontalLayout(withLeftLabel, withRightLabel, withBothLabels);
    HorizontalLayout iconsRow = new HorizontalLayout(withLeftIcon, withRightIcon, withBothIcons);
    HorizontalLayout labelsAndIconsRow = new HorizontalLayout(withLabelsAndIcons, withIconsInside);
    HorizontalLayout highlightRow = new HorizontalLayout(highlightPrimary, highlightSuccess, highlightWarning, highlightError, highlightContrast);
    fieldLabelRow.getStyle().set("gap", "var(--lumo-space-l)");
    labelsRow.getStyle().set("gap", "var(--lumo-space-l)");
    iconsRow.getStyle().set("gap", "var(--lumo-space-l)");
    labelsAndIconsRow.getStyle().set("gap", "var(--lumo-space-l)");
    highlightRow.getStyle().set("gap", "var(--lumo-space-l)");

    add(new VerticalLayout(
        new H3("Basic"),
        basic,
        new H3("With field label"),
        fieldLabelRow,
        new H3("With labels"),
        labelsRow,
        new H3("With icons"),
        iconsRow,
        new H3("With labels and icons"),
        labelsAndIconsRow,
        new H3("With label highlighting"),
        highlightRow));
  }
}
