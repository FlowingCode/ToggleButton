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
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;

import com.flowingcode.vaadin.addons.togglebutton.ToggleButton;
import com.vaadin.flow.component.BlurNotifier.BlurEvent;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.FocusNotifier.FocusEvent;
import com.vaadin.flow.component.checkbox.Switch;
import com.vaadin.flow.dom.Element;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;

public class ToggleButtonTest {

  private static Switch switchOf(ToggleButton toggle) {
    return (Switch) find(toggle.getElement()).flatMap(Element::getComponent).orElseThrow();
  }

  private static Optional<Element> find(Element element) {
    if (element.isTextNode()) {
      return Optional.empty();
    }
    if ("vaadin-switch".equals(element.getTag())) {
      return Optional.of(element);
    }
    return element.getChildren().map(ToggleButtonTest::find).flatMap(Optional::stream).findFirst();
  }

  @Test
  public void nullValueIsRejected() {
    ToggleButton toggle = new ToggleButton(true);
    assertThrows(NullPointerException.class, () -> toggle.setValue(null));
    assertEquals(Boolean.TRUE, toggle.getValue());
  }

  @Test
  public void labelIsNullUntilSet() {
    ToggleButton toggle = new ToggleButton();
    assertNull(toggle.getLabel());
    toggle.setLabel("Notifications");
    assertEquals("Notifications", toggle.getLabel());
    toggle.setLabel(null);
    assertNull(toggle.getLabel());
  }

  @Test
  public void tabIndexGoesToTheSwitch() {
    ToggleButton toggle = new ToggleButton();
    toggle.setTabIndex(-1);
    assertEquals(-1, toggle.getTabIndex());
    assertEquals("-1", switchOf(toggle).getElement().getAttribute("tabindex"));
    assertFalse(toggle.getElement().hasAttribute("tabindex"));
  }

  @Test
  public void focusAndBlurListenersFireFromTheSwitch() {
    ToggleButton toggle = new ToggleButton();
    List<Object> sources = new ArrayList<>();
    toggle.addFocusListener(e -> sources.add(e.getSource()));
    toggle.addBlurListener(e -> sources.add(e.getSource()));

    Switch inner = switchOf(toggle);
    ComponentUtil.fireEvent(inner, new FocusEvent<>(inner, true));
    ComponentUtil.fireEvent(inner, new BlurEvent<>(inner, true));

    assertEquals(2, sources.size());
    assertSame(toggle, sources.get(0));
    assertSame(toggle, sources.get(1));
  }
}
