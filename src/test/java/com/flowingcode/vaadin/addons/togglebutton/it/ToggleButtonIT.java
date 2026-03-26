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
    super("labels");
  }

  private TestBenchElement getToggle(String id) {
    return $("toggle-button").id(id);
  }

  @Test
  public void initialValueIsFalse() {
    assertNull(getToggle("basic").getAttribute("checked"));
  }

  @Test
  public void clickTogglesChecked() {
    TestBenchElement toggle = getToggle("basic");
    toggle.click();
    assertNotNull(toggle.getAttribute("checked"));
  }

  @Test
  public void clickAgainTogglesBack() {
    TestBenchElement toggle = getToggle("basic");
    toggle.click();
    toggle.click();
    assertNull(toggle.getAttribute("checked"));
  }

  @Test
  public void leftLabelIsRendered() {
    TestBenchElement toggle = getToggle("with-left-label");
    String text =
        (String)
            toggle
                .getCommandExecutor()
                .executeScript(
                    "return arguments[0].shadowRoot.querySelector('.label').textContent", toggle);
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
                    "return arguments[0].shadowRoot.querySelector('.label').textContent", toggle);
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
                    "return Array.from(arguments[0].shadowRoot.querySelectorAll('.label'))"
                        + ".map(l => l.textContent).join(',')",
                    toggle);
    assertEquals("Off,On", texts);
  }

  @Test
  public void highlightLabelAttributeIsReflected() {
    assertNotNull(getToggle("highlight-primary").getAttribute("highlightlabel"));
  }

  @Test
  public void noHighlightLabelByDefault() {
    assertNull(getToggle("basic").getAttribute("highlightlabel"));
  }
}
