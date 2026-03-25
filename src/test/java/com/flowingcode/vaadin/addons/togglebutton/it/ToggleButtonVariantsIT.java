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

/**
 * Integration tests for {@code ToggleButton} theme variants and component states.
 *
 * <p>Tests navigate to the "Theme Variants" demo (route {@code variants}).
 * Component index mapping:
 * <ol start="0">
 *   <li>primary (checked=true, theme=primary)</li>
 *   <li>success (checked=true, theme=success)</li>
 *   <li>error (checked=true, theme=error)</li>
 *   <li>warning (checked=true, theme=warning)</li>
 *   <li>contrast (checked=true, theme=contrast)</li>
 *   <li>small (checked=false, theme=small)</li>
 *   <li>medium (checked=false, theme=medium)</li>
 *   <li>large (checked=false, theme=large)</li>
 *   <li>disabled (checked=false, disabled)</li>
 *   <li>readOnly (checked=true, readonly)</li>
 * </ol>
 */
public class ToggleButtonVariantsIT extends AbstractViewTest {

  public ToggleButtonVariantsIT() {
    super("variants");
  }

  private TestBenchElement getToggle(int index) {
    return $("toggle-button").get(index);
  }

  // --- Color variants ---

  @Test
  public void primaryVariantHasThemeAttribute() {
    assertTrue(getToggle(0).getAttribute("theme").contains("primary"));
  }

  @Test
  public void successVariantHasThemeAttribute() {
    assertTrue(getToggle(1).getAttribute("theme").contains("success"));
  }

  @Test
  public void errorVariantHasThemeAttribute() {
    assertTrue(getToggle(2).getAttribute("theme").contains("error"));
  }

  @Test
  public void warningVariantHasThemeAttribute() {
    assertTrue(getToggle(3).getAttribute("theme").contains("warning"));
  }

  @Test
  public void contrastVariantHasThemeAttribute() {
    assertTrue(getToggle(4).getAttribute("theme").contains("contrast"));
  }

  // --- Size variants ---

  @Test
  public void smallVariantHasThemeAttribute() {
    assertTrue(getToggle(5).getAttribute("theme").contains("small"));
  }

  @Test
  public void mediumVariantHasThemeAttribute() {
    assertTrue(getToggle(6).getAttribute("theme").contains("medium"));
  }

  @Test
  public void largeVariantHasThemeAttribute() {
    assertTrue(getToggle(7).getAttribute("theme").contains("large"));
  }

  // --- Initial values ---

  @Test
  public void colorVariantsInitializedChecked() {
    // All color variants (indices 0-4) are created with initialValue=true
    for (int i = 0; i < 5; i++) {
      assertNotNull("Expected toggle " + i + " to be checked", getToggle(i).getAttribute("checked"));
    }
  }

  @Test
  public void sizeVariantsInitializedUnchecked() {
    // Size variants (indices 5-7) are created with default initialValue=false
    for (int i = 5; i < 8; i++) {
      assertNull("Expected toggle " + i + " to be unchecked", getToggle(i).getAttribute("checked"));
    }
  }

  // --- States ---

  @Test
  public void disabledButtonCannotBeToggled() {
    TestBenchElement disabled = getToggle(8);
    assertNull(disabled.getAttribute("checked"));
    disabled.click();
    assertNull(disabled.getAttribute("checked"));
  }

  @Test
  public void readonlyButtonCannotBeToggled() {
    TestBenchElement readOnly = getToggle(9);
    assertNotNull(readOnly.getAttribute("checked"));
    readOnly.click();
    assertNotNull(readOnly.getAttribute("checked"));
  }
}
