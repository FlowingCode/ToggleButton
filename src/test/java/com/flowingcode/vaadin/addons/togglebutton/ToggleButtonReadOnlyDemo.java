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
@PageTitle("Read-only")
@SuppressWarnings("serial")
@Route(value = "togglebutton/readonly", layout = ToggleButtonDemoView.class)
public class ToggleButtonReadOnlyDemo extends Div {

    public ToggleButtonReadOnlyDemo() {

        // Sizes — unchecked
        ToggleButton smallOff = readOnly(new ToggleButton().setRightLabel("Small"),
                ToggleButtonVariant.SMALL);
        ToggleButton mediumOff = readOnly(new ToggleButton().setRightLabel("Medium"),
                ToggleButtonVariant.MEDIUM);
        ToggleButton largeOff = readOnly(new ToggleButton().setRightLabel("Large"),
                ToggleButtonVariant.LARGE);

        // Sizes — checked
        ToggleButton smallOn = readOnly(new ToggleButton(true).setRightLabel("Small"),
                ToggleButtonVariant.SMALL);
        ToggleButton mediumOn = readOnly(new ToggleButton(true).setRightLabel("Medium"),
                ToggleButtonVariant.MEDIUM);
        ToggleButton largeOn = readOnly(new ToggleButton(true).setRightLabel("Large"),
                ToggleButtonVariant.LARGE);

        // Long swipe — unchecked
        ToggleButton lsSmallOff = readOnly(new ToggleButton().setRightLabel("Small"),
                ToggleButtonVariant.LONGSWIPE, ToggleButtonVariant.SMALL);
        ToggleButton lsMediumOff = readOnly(new ToggleButton().setRightLabel("Medium"),
                ToggleButtonVariant.LONGSWIPE, ToggleButtonVariant.MEDIUM);
        ToggleButton lsLargeOff = readOnly(new ToggleButton().setRightLabel("Large"),
                ToggleButtonVariant.LONGSWIPE, ToggleButtonVariant.LARGE);

        // Long swipe — checked
        ToggleButton lsSmallOn = readOnly(new ToggleButton(true).setRightLabel("Small"),
                ToggleButtonVariant.LONGSWIPE, ToggleButtonVariant.SMALL);
        ToggleButton lsMediumOn = readOnly(new ToggleButton(true).setRightLabel("Medium"),
                ToggleButtonVariant.LONGSWIPE, ToggleButtonVariant.MEDIUM);
        ToggleButton lsLargeOn = readOnly(new ToggleButton(true).setRightLabel("Large"),
                ToggleButtonVariant.LONGSWIPE, ToggleButtonVariant.LARGE);

        // Colors — unchecked
        ToggleButton primaryOff = readOnly(new ToggleButton().setLeftLabel("Primary"),
                ToggleButtonVariant.PRIMARY);
        ToggleButton successOff = readOnly(new ToggleButton().setLeftLabel("Success"),
                ToggleButtonVariant.SUCCESS);
        ToggleButton errorOff = readOnly(new ToggleButton().setLeftLabel("Error"),
                ToggleButtonVariant.ERROR);
        ToggleButton warningOff = readOnly(new ToggleButton().setLeftLabel("Warning"),
                ToggleButtonVariant.WARNING);
        ToggleButton contrastOff = readOnly(new ToggleButton().setLeftLabel("Contrast"),
                ToggleButtonVariant.CONTRAST);

        // Colors — checked (border tint visible)
        ToggleButton primaryOn = readOnly(new ToggleButton(true).setLeftLabel("Primary"),
                ToggleButtonVariant.PRIMARY);
        ToggleButton successOn = readOnly(new ToggleButton(true).setLeftLabel("Success"),
                ToggleButtonVariant.SUCCESS);
        ToggleButton errorOn = readOnly(new ToggleButton(true).setLeftLabel("Error"),
                ToggleButtonVariant.ERROR);
        ToggleButton warningOn = readOnly(new ToggleButton(true).setLeftLabel("Warning"),
                ToggleButtonVariant.WARNING);
        ToggleButton contrastOn = readOnly(new ToggleButton(true).setLeftLabel("Contrast"),
                ToggleButtonVariant.CONTRAST);

        HorizontalLayout sizesOff = row(smallOff, mediumOff, largeOff);
        HorizontalLayout sizesOn = row(smallOn, mediumOn, largeOn);
        HorizontalLayout longswipeOff = row(lsSmallOff, lsMediumOff, lsLargeOff);
        HorizontalLayout longswipeOn = row(lsSmallOn, lsMediumOn, lsLargeOn);
        HorizontalLayout colorsOff = row(primaryOff, successOff, errorOff, warningOff, contrastOff);
        HorizontalLayout colorsOn = row(primaryOn, successOn, errorOn, warningOn, contrastOn);

        add(new VerticalLayout(
                new H3("Sizes - unchecked"), sizesOff,
                new H3("Sizes - checked"), sizesOn,
                new H3("Long swipe - unchecked"), longswipeOff,
                new H3("Long swipe - checked"), longswipeOn,
                new H3("Colors - unchecked"), colorsOff,
                new H3("Colors - checked"), colorsOn));
    }

    private static ToggleButton readOnly(ToggleButton tb, ToggleButtonVariant... variants) {
        tb.addThemeVariants(variants);
        tb.setReadOnly(true);
        return tb;
    }

    private static HorizontalLayout row(ToggleButton... buttons) {
        HorizontalLayout row = new HorizontalLayout(buttons);
        row.getStyle().set("gap", "var(--lumo-space-l, var(--vaadin-gap-l))");
        return row;
    }
}
