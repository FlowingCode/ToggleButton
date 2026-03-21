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
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@DemoSource
@PageTitle("Events and Accessibility")
@SuppressWarnings("serial")
@Route(value = "events", layout = FCToggleButtonDemoView.class)
public class FCToggleButtonEventsDemo extends Div {

  public FCToggleButtonEventsDemo() {

    FCToggleButton withValueChange = new FCToggleButton()
        .setLeftLabel("Off")
        .setRightLabel("On");
    withValueChange.addValueChangeListener(e ->
        Notification.show("Value changed to: " + e.getValue()));

    FCToggleButton withAriaLabel = new FCToggleButton()
        .setLeftLabel("Off")
        .setRightLabel("On");
    withAriaLabel.setAriaLabel("Enable notifications");

    FCToggleButton withTooltip = new FCToggleButton()
        .setLeftLabel("Off")
        .setRightLabel("On");
    withTooltip.setTooltipText("Toggle to enable or disable this feature");

    add(new VerticalLayout(
        new H3("Value change event"),
        withValueChange,
        new H3("Aria label"),
        withAriaLabel,
        new H3("Tooltip"),
        withTooltip));
  }
}
