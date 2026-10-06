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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.flowingcode.vaadin.addons.togglebutton.ToggleButton;
import com.vaadin.flow.dom.Element;
import java.util.Optional;
import org.junit.Test;

/** Accessible name of the inner switch: aria-labelledby, then aria-label, then the labels. */
public class AccessibleNameTest {

  private static Element switchOf(ToggleButton toggle) {
    return find(toggle.getElement()).orElseThrow();
  }

  private static Optional<Element> find(Element element) {
    if (element.isTextNode()) {
      return Optional.empty();
    }
    if ("vaadin-switch".equals(element.getTag())) {
      return Optional.of(element);
    }
    return element.getChildren().map(AccessibleNameTest::find).flatMap(Optional::stream).findFirst();
  }

  private static String labelledBy(ToggleButton toggle) {
    return switchOf(toggle).getProperty("accessibleNameRef");
  }

  @Test
  public void ariaLabelledByIsKeptWhenLabelsChange() {
    ToggleButton toggle = new ToggleButton();
    toggle.setAriaLabelledBy("heading");
    toggle.setLeftLabel("Off");
    toggle.setRightLabel("On");
    toggle.setLabel("Notifications");
    toggle.setAriaLabel("Enable notifications");

    assertEquals("heading", labelledBy(toggle));
    assertNull(switchOf(toggle).getProperty("accessibleName"));
    assertEquals(Optional.of("heading"), toggle.getAriaLabelledBy());
  }

  @Test
  public void ariaLabelledByIsEmptyUnlessSetByTheCaller() {
    ToggleButton toggle = new ToggleButton("Notifications");
    // The switch references the internal field label, but that is not exposed as the caller's value
    assertTrue(labelledBy(toggle).endsWith("-label"));
    assertEquals(Optional.empty(), toggle.getAriaLabelledBy());
  }

  @Test
  public void clearingAriaLabelledByRestoresTheFieldLabel() {
    ToggleButton toggle = new ToggleButton("Notifications");
    String fieldLabel = labelledBy(toggle);
    toggle.setAriaLabelledBy("heading");
    toggle.setAriaLabelledBy((String) null);
    assertEquals(fieldLabel, labelledBy(toggle));
  }
}
