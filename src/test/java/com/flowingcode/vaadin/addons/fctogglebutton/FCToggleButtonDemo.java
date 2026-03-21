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

    FCToggleButton highlightLabels = new FCToggleButton()
        .setLeftLabel("Off")
        .setRightLabel("On")
        .withHighlightLabel();

    FCToggleButton highlightWithIcons = new FCToggleButton()
        .setLeftIcon(new Icon(VaadinIcon.MOON))
        .setLeftLabel("Dark")
        .setRightLabel("Light")
        .setRightIcon(new Icon(VaadinIcon.SUN_O))
        .withHighlightLabel();

    add(new VerticalLayout(
        new H3("Basic"),
        basic,
        new H3("With labels"),
        new HorizontalLayout(withLeftLabel, withRightLabel, withBothLabels),
        new H3("With icons"),
        new HorizontalLayout(withLeftIcon, withRightIcon, withBothIcons),
        new H3("With labels and icons"),
        withLabelsAndIcons,
        new H3("With label highlighting"),
        new HorizontalLayout(highlightLabels, highlightWithIcons)));
  }
}
