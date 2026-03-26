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
package com.flowingcode.vaadin.addons.togglebutton.it;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.vaadin.testbench.TestBenchElement;
import org.junit.Test;

/** Integration tests for {@code ToggleButton} theme variants and component states. */
public class ToggleButtonVariantsIT extends AbstractViewTest {

  public ToggleButtonVariantsIT() {
    super("variants");
  }

  private TestBenchElement getToggle(String id) {
    return $("toggle-button").id(id);
  }

  // --- Color variants ---

  @Test
  public void primaryVariantHasThemeAttribute() {
    assertTrue(getToggle("primary").getAttribute("theme").contains("primary"));
  }

  @Test
  public void successVariantHasThemeAttribute() {
    assertTrue(getToggle("success").getAttribute("theme").contains("success"));
  }

  @Test
  public void errorVariantHasThemeAttribute() {
    assertTrue(getToggle("error").getAttribute("theme").contains("error"));
  }

  @Test
  public void warningVariantHasThemeAttribute() {
    assertTrue(getToggle("warning").getAttribute("theme").contains("warning"));
  }

  @Test
  public void contrastVariantHasThemeAttribute() {
    assertTrue(getToggle("contrast").getAttribute("theme").contains("contrast"));
  }

  // --- Size variants ---

  @Test
  public void smallVariantHasThemeAttribute() {
    assertTrue(getToggle("small").getAttribute("theme").contains("small"));
  }

  @Test
  public void mediumVariantHasThemeAttribute() {
    assertTrue(getToggle("medium").getAttribute("theme").contains("medium"));
  }

  @Test
  public void largeVariantHasThemeAttribute() {
    assertTrue(getToggle("large").getAttribute("theme").contains("large"));
  }

  // --- Initial values ---

  @Test
  public void colorVariantsInitializedChecked() {
    for (String id : new String[] {"primary", "success", "error", "warning", "contrast"}) {
      assertNotNull("Expected " + id + " to be checked", getToggle(id).getAttribute("checked"));
    }
  }

  @Test
  public void sizeVariantsInitializedUnchecked() {
    for (String id : new String[] {"small", "medium", "large"}) {
      assertNull("Expected " + id + " to be unchecked", getToggle(id).getAttribute("checked"));
    }
  }

  // --- States ---

  @Test
  public void disabledButtonCannotBeToggled() {
    TestBenchElement disabled = getToggle("disabled");
    assertNull(disabled.getAttribute("checked"));
    disabled.click();
    assertNull(disabled.getAttribute("checked"));
  }

  @Test
  public void readonlyButtonCannotBeToggled() {
    TestBenchElement readOnly = getToggle("read-only");
    assertNotNull(readOnly.getAttribute("checked"));
    readOnly.click();
    assertNotNull(readOnly.getAttribute("checked"));
  }
}
