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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.vaadin.testbench.TestBenchElement;
import org.junit.Test;

/** Integration tests for {@code ToggleButton} label and value-change behavior. */
public class ToggleButtonIT extends AbstractViewTest {

  public ToggleButtonIT() {
    super("togglebutton/labels");
  }

  private TestBenchElement getToggle(String id) {
    return $("fc-toggle-button").id(id);
  }

  @Test
  public void initialValueIsFalse() {
    assertNull(checkedOf(getToggle("basic")));
  }

  @Test
  public void clickTogglesChecked() {
    TestBenchElement toggle = getToggle("basic");
    toggle.click();
    assertNotNull(checkedOf(toggle));
  }

  @Test
  public void clickAgainTogglesBack() {
    TestBenchElement toggle = getToggle("basic");
    toggle.click();
    toggle.click();
    assertNull(checkedOf(toggle));
  }

  @Test
  public void leftLabelIsRendered() {
    TestBenchElement toggle = getToggle("with-left-label");
    String text =
        (String)
            toggle
                .getCommandExecutor()
                .executeScript(
                    "return arguments[0].querySelector('.fc-toggle-button-left-label:not([hidden]), .fc-toggle-button-right-label:not([hidden])').textContent", toggle);
    assertEquals("Off", text);
  }

  @Test
  public void rightLabelIsRendered() {
    TestBenchElement toggle = getToggle("with-right-label");
    String text =
        (String)
            toggle
                .getCommandExecutor()
                .executeScript(
                    "return arguments[0].querySelector('.fc-toggle-button-left-label:not([hidden]), .fc-toggle-button-right-label:not([hidden])').textContent", toggle);
    assertEquals("On", text);
  }

  @Test
  public void bothLabelsAreRendered() {
    TestBenchElement toggle = getToggle("with-both-labels");
    String texts =
        (String)
            toggle
                .getCommandExecutor()
                .executeScript(
                    "return Array.from(arguments[0].querySelectorAll('.fc-toggle-button-left-label:not([hidden]), .fc-toggle-button-right-label:not([hidden])'))"
                        + ".map(l => l.textContent).join(',')",
                    toggle);
    assertEquals("Off,On", texts);
  }

  @Test
  public void highlightLabelAttributeIsReflected() {
    assertNotNull(getToggle("highlight-primary").getAttribute("highlight-label"));
  }

  @Test
  public void switchIsNamedBySideLabelsWhenThereIsNoFieldLabel() {
    TestBenchElement toggle = getToggle("with-both-labels");
    String name =
        (String)
            toggle
                .getCommandExecutor()
                .executeScript(
                    "const input = arguments[0].querySelector('vaadin-switch input');"
                        + "return input.getAttribute('role') + ':' + input.getAttribute('aria-labelledby')"
                        + ".split(' ').map(id => document.getElementById(id).textContent).join(' ')",
                    toggle);
    assertEquals("switch:Off On", name);
  }

  @Test
  public void noHighlightLabelByDefault() {
    assertNull(getToggle("basic").getAttribute("highlight-label"));
  }
}
