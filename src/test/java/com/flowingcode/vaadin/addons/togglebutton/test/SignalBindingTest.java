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
package com.flowingcode.vaadin.addons.togglebutton.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.flowingcode.vaadin.addons.togglebutton.ToggleButton;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Switch;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.signals.local.ValueSignal;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** The bind* defaults inherited from Flow must reach the inner switch and the rendered parts. */
public class SignalBindingTest {

  private UI ui;
  private ToggleButton toggle;

  @Before
  public void setUp() {
    ui = new UI();
    UI.setCurrent(ui);
    toggle = new ToggleButton("Notifications");
    ui.add(toggle);
  }

  @After
  public void tearDown() {
    UI.setCurrent(null);
  }

  private Switch switchOf() {
    return (Switch) find(toggle.getElement()).flatMap(Element::getComponent).orElseThrow();
  }

  private static Optional<Element> find(Element element) {
    if (element.isTextNode()) {
      return Optional.empty();
    }
    if ("vaadin-switch".equals(element.getTag())) {
      return Optional.of(element);
    }
    return element.getChildren().map(SignalBindingTest::find).flatMap(Optional::stream).findFirst();
  }

  private Element helper() {
    return toggle.getElement().getChildren()
        .filter(e -> e.getClassList().contains("fc-toggle-button-helper"))
        .findFirst().orElseThrow();
  }

  @Test
  public void bindReadOnlyReachesTheSwitch() {
    ValueSignal<Boolean> readOnly = new ValueSignal<>(true);
    toggle.bindReadOnly(readOnly);
    assertTrue(toggle.isReadOnly());
    assertTrue(switchOf().isReadOnly());

    readOnly.set(false);
    assertFalse(toggle.isReadOnly());
    assertFalse(switchOf().isReadOnly());
  }

  @Test
  public void bindRequiredIndicatorVisibleReachesTheSwitch() {
    ValueSignal<Boolean> required = new ValueSignal<>(true);
    toggle.bindRequiredIndicatorVisible(required);
    assertTrue(toggle.isRequiredIndicatorVisible());
    assertTrue(switchOf().isRequiredIndicatorVisible());

    required.set(false);
    assertFalse(switchOf().isRequiredIndicatorVisible());
  }

  @Test
  public void bindHelperTextRendersAndDescribesTheSwitch() {
    ValueSignal<String> helperText = new ValueSignal<>("Included on the Business plan.");
    toggle.bindHelperText(helperText);
    String helperId = helper().getAttribute("id");

    assertEquals("Included on the Business plan.", toggle.getHelperText());
    assertTrue(helper().isVisible());
    assertEquals(helperId, switchOf().getAriaDescribedBy().orElse(null));

    helperText.set("Not included.");
    assertEquals("Not included.", toggle.getHelperText());

    helperText.set("");
    assertFalse(helper().isVisible());
    assertNull(switchOf().getAriaDescribedBy().orElse(null));
  }
}
