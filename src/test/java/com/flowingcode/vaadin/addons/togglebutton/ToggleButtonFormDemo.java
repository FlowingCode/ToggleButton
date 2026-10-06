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
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@DemoSource
@PageTitle("Form Integration")
@SuppressWarnings("serial")
@Route(value = "togglebutton/form", layout = ToggleButtonDemoView.class)
public class ToggleButtonFormDemo extends Div {

  public static class Account {
    private boolean deleteOnExit;
    private boolean betaFeatures;

    public boolean isDeleteOnExit() {
      return deleteOnExit;
    }

    public void setDeleteOnExit(boolean deleteOnExit) {
      this.deleteOnExit = deleteOnExit;
    }

    public boolean isBetaFeatures() {
      return betaFeatures;
    }

    public void setBetaFeatures(boolean betaFeatures) {
      this.betaFeatures = betaFeatures;
    }
  }

  public static class Booking {
    private boolean confirmed;

    public boolean isConfirmed() {
      return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
      this.confirmed = confirmed;
    }
  }

  public ToggleButtonFormDemo() {

    ToggleButton withHelper = new ToggleButton("Audit log retention", true)
        .setLeftLabel("Off")
        .setRightLabel("On");
    withHelper.setId("with-helper");
    withHelper.setHelperText("Included on the Business plan.");
    withHelper.setReadOnly(true);

    ToggleButton required = new ToggleButton("I confirm the trip details are correct")
        .setLeftLabel("No")
        .setRightLabel("Yes")
        .withHighlightLabel();
    required.setId("required");
    required.setHelperText("Required to submit the booking.");

    Binder<Booking> binder = new Binder<>(Booking.class);
    binder.forField(required)
        .asRequired("You must confirm the trip details to continue")
        .bind(Booking::isConfirmed, Booking::setConfirmed);
    binder.setBean(new Booking());

    Button submit = new Button("Submit", e -> {
      if (binder.validate().isOk()) {
        Notification.show("Booking submitted");
      }
    });
    submit.setId("submit");

    ToggleButton swipe = new ToggleButton("Swipe me on a touch device")
        .setLeftLabel("Off")
        .setRightLabel("On");
    swipe.addThemeVariants(ToggleButtonVariant.LONGSWIPE, ToggleButtonVariant.SUCCESS);
    swipe.addValueChangeListener(e ->
        Notification.show("Value: " + e.getValue() + " (from client: " + e.isFromClient() + ")"));

    // Color variants on fields bound with Binder (no validators)
    ToggleButton deleteOnExit = new ToggleButton("Delete account on exit")
        .setLeftLabel("Off")
        .setRightLabel("On")
        .withHighlightLabel();
    deleteOnExit.setId("bound-error");
    deleteOnExit.addThemeVariants(ToggleButtonVariant.ERROR);

    ToggleButton betaFeatures = new ToggleButton("Beta features")
        .setLeftLabel("Off")
        .setRightLabel("On")
        .withHighlightLabel();
    betaFeatures.setId("bound-warning");
    betaFeatures.addThemeVariants(ToggleButtonVariant.WARNING);

    Binder<Account> accountBinder = new Binder<>(Account.class);
    accountBinder.forField(deleteOnExit).bind(Account::isDeleteOnExit, Account::setDeleteOnExit);
    accountBinder.forField(betaFeatures).bind(Account::isBetaFeatures, Account::setBetaFeatures);
    accountBinder.setBean(new Account());

    add(new VerticalLayout(
        new H3("Color variants with Binder"),
        deleteOnExit,
        betaFeatures,
        new H3("Helper text (read-only)"),
        withHelper,
        new H3("Required with Binder"),
        required,
        submit,
        new H3("Swipe"),
        swipe));
  }
}
