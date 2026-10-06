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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.flowingcode.vaadin.addons.togglebutton.ToggleButton;
import com.flowingcode.vaadin.addons.togglebutton.ToggleButtonVariant;
import com.vaadin.flow.data.binder.Binder;
import org.junit.Test;

/**
 * Binder adds a theme named after the error level ("error", "warning", ...) to invalid fields, and
 * removes all of those names when the field becomes valid. Color variants must not use those names,
 * or Binder would remove them.
 */
public class BinderThemeTest {

  public static class Bean {
    private boolean value;

    public boolean isValue() {
      return value;
    }

    public void setValue(boolean value) {
      this.value = value;
    }
  }

  private static ToggleButton bind(ToggleButtonVariant variant) {
    ToggleButton toggle = new ToggleButton();
    toggle.addThemeVariants(variant);
    Binder<Bean> binder = new Binder<>(Bean.class);
    binder.forField(toggle).bind(Bean::isValue, Bean::setValue);
    // setBean validates, and a valid field gets the error-level theme names removed
    binder.setBean(new Bean());
    toggle.setValue(true);
    return toggle;
  }

  @Test
  public void errorVariantSurvivesBinderValidation() {
    assertTrue(bind(ToggleButtonVariant.ERROR).hasThemeName("color-error"));
  }

  @Test
  public void warningVariantSurvivesBinderValidation() {
    assertTrue(bind(ToggleButtonVariant.WARNING).hasThemeName("color-warning"));
  }

  @Test
  public void invalidFieldDoesNotLookLikeErrorVariant() {
    ToggleButton toggle = new ToggleButton();
    Binder<Bean> binder = new Binder<>(Bean.class);
    binder.forField(toggle).asRequired("Required").bind(Bean::isValue, Bean::setValue);
    binder.setBean(new Bean());
    binder.validate();
    assertTrue(toggle.isInvalid());
    assertFalse(toggle.hasThemeName("color-error"));
  }
}
