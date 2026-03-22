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
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@DemoSource
@PageTitle("Labels and Icons")
@SuppressWarnings("serial")
@Route(value = "labels", layout = FCToggleButtonDemoView.class)
public class FCToggleButtonDemo extends Div {

  public FCToggleButtonDemo() {

    FCToggleButton basic = new FCToggleButton();

    FCToggleButton withLabel = new FCToggleButton("Notifications");

    FCToggleButton withLabelAndInitialValue = new FCToggleButton("Dark mode", true)
        .setLeftLabel("Off")
        .setRightLabel("On");

    FCToggleButton withLeftLabel = new FCToggleButton()
        .setLeftLabel("Off");

    FCToggleButton withRightLabel = new FCToggleButton()
        .setRightLabel("On");

    FCToggleButton withBothLabels = new FCToggleButton()
        .setLeftLabel("Off")
        .setRightLabel("On");

    FCToggleButton withLeftIcon = new FCToggleButton()
        .setLeftIcon(new Icon(VaadinIcon.MOON));

    FCToggleButton withRightIcon = new FCToggleButton()
        .setRightIcon(new Icon(VaadinIcon.SUN_O));

    FCToggleButton withBothIcons = new FCToggleButton()
        .setLeftIcon(new Icon(VaadinIcon.MOON))
        .setRightIcon(new Icon(VaadinIcon.SUN_O));

    FCToggleButton withLabelsAndIcons = new FCToggleButton()
        .setLeftIcon(new Icon(VaadinIcon.MOON))
        .setLeftLabel("Dark")
        .setRightLabel("Light")
        .setRightIcon(new Icon(VaadinIcon.SUN_O));

    FCToggleButton withIconsInside = new FCToggleButton()
        .setLeftIcon(new Icon(VaadinIcon.MOON))
        .setLeftLabel("Dark")
        .setRightLabel("Light")
        .setRightIcon(new Icon(VaadinIcon.SUN_O))
        .withIconsInside();

    FCToggleButton highlightPrimary = new FCToggleButton(true).setLeftLabel("Off").setRightLabel("On").withHighlightLabel();
    highlightPrimary.addThemeVariants(FCToggleButtonVariant.PRIMARY);

    FCToggleButton highlightSuccess = new FCToggleButton(true).setLeftLabel("Off").setRightLabel("On").withHighlightLabel();
    highlightSuccess.addThemeVariants(FCToggleButtonVariant.SUCCESS);

    FCToggleButton highlightWarning = new FCToggleButton(true).setLeftLabel("Off").setRightLabel("On").withHighlightLabel();
    highlightWarning.addThemeVariants(FCToggleButtonVariant.WARNING);

    FCToggleButton highlightError = new FCToggleButton(true).setLeftLabel("Off").setRightLabel("On").withHighlightLabel();
    highlightError.addThemeVariants(FCToggleButtonVariant.ERROR);

    FCToggleButton highlightContrast = new FCToggleButton(true).setLeftLabel("Off").setRightLabel("On").withHighlightLabel();
    highlightContrast.addThemeVariants(FCToggleButtonVariant.CONTRAST);

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
