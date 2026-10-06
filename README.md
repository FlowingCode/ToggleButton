[![Published on Vaadin Directory](https://img.shields.io/badge/Vaadin%20Directory-published-00b4f0.svg)](https://vaadin.com/directory/component/toggle-button-addon)
[![Stars on vaadin.com/directory](https://img.shields.io/vaadin-directory/star/toggle-button-addon.svg)](https://vaadin.com/directory/component/toggle-button-addon)
[![Build Status](https://jenkins.flowingcode.com/job/ToggleButton-addon/badge/icon)](https://jenkins.flowingcode.com/job/ToggleButton-addon)
[![Maven Central](https://img.shields.io/maven-central/v/com.flowingcode.vaadin.addons/toggle-button-addon)](https://mvnrepository.com/artifact/com.flowingcode.vaadin.addons/toggle-button-addon)
[![Javadoc](https://img.shields.io/badge/javadoc-00b4f0)](https://javadoc.flowingcode.com/artifact/com.flowingcode.vaadin.addons/toggle-button-addon)

# Toggle Button Add-On

A toggle button component for Vaadin Flow that supports customizable labels and icons on both sides of the toggle.

Starting with version 2.0, the add-on is built on top of the core Vaadin [Switch](https://vaadin.com/docs/latest/components/switch) component. The switch itself (keyboard, focus, accessibility, read-only and disabled states) comes from Vaadin, and the add-on adds side labels, icons, label highlighting, theme variants and touch swipe around it.

## Features

* Toggle between two states with a click, a tap, a swipe, or the Space key
* Customizable left and right labels
* Support for icons on both sides via slots
* Optional label highlighting: active-side label uses the theme variant color (primary, success, warning, error, or contrast), inactive side is dimmed
* Optional icons-inside layout: icons adjacent to the switch, labels on the outer edges
* Theme variants: `SMALL`, `MEDIUM`, `LARGE`, `LONGSWIPE`, `PRIMARY`, `SUCCESS`, `WARNING`, `ERROR`, `CONTRAST`
* `LONGSWIPE` variant produces a wider switch track, optimized for touch interaction; can be combined with size variants
* Accessible out of the box: announced as a switch with its on/off state, focusable, and operable with the keyboard
* Form support: helper text, required indicator, error message, and `Binder` validation
* Works with both the Lumo and Aura themes
* Fluent API for easy configuration
* Full integration with Vaadin's `HasValue`, `HasSize`, `HasLabel`, `HasAriaLabel`, `HasTooltip`, `HasHelper`, `HasValidationProperties`, and `Focusable`

## Compatibility

| Add-on version | Vaadin version | Java version |
|---|---|---|
| 2.x | 25.3 or newer | 21 |
| 1.x | 24, and 25.0 to 25.2 | 17 (Vaadin 24), 21 (Vaadin 25) |

Version 2.x requires Vaadin 25.3 or newer, because it is built on the core `Switch` introduced in that release. Version 1.x is in maintenance mode for older Vaadin versions.

## Online demo

[Online demo here](http://addonsv25.flowingcode.com/togglebutton)

## Download release

[Available in Vaadin Directory](https://vaadin.com/directory/component/toggle-button-addon)

### Maven install

Add the following dependencies in your pom.xml file:

```xml
<dependency>
   <groupId>com.flowingcode.vaadin.addons</groupId>
   <artifactId>toggle-button-addon</artifactId>
   <version>X.Y.Z</version>
</dependency>
```
<!-- the above dependency should be updated with latest released version information -->

Release versions are available from Maven Central repository. For SNAPSHOT versions see [here](https://maven.flowingcode.com/snapshots/).

## Building and running demo

- git clone repository
- mvn clean install jetty:run

To see the demo, navigate to http://localhost:8080/

## Release notes

See [here](https://github.com/FlowingCode/ToggleButton/releases)

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated.

## Contributions

Contributions are welcome. There are two primary ways you can contribute: by reporting issues or by submitting code changes through pull requests. To ensure a smooth and effective process for everyone, please follow the guidelines below for the type of contribution you are making.

#### 1. Reporting Bugs and Requesting Features

Creating an issue is a highly valuable contribution. If you've found a bug or have an idea for a new feature, this is the place to start.

* Before creating an issue, please check the existing issues to see if your topic is already being discussed.
* If not, create a new issue, choosing the right option: "Bug Report" or "Feature Request". Try to keep the scope minimal but as detailed as possible.

> **A Note on Bug Reports**
>
> Please complete all the requested fields to the best of your ability. Each piece of information, like the environment versions and a clear description, helps us understand the context of the issue.
>
> While all details are important, the **[minimal, reproducible example](https://stackoverflow.com/help/minimal-reproducible-example)** is the most critical part of your report. It's essential because it removes ambiguity and allows our team to observe the problem firsthand, exactly as you are experiencing it.

#### 2. Contributing Code via Pull Requests

As a first step, please refer to our [Development Conventions](https://github.com/FlowingCode/DevelopmentConventions) page to find information about Conventional Commits & Code Style requirements.

Then, follow these steps for creating a contribution:

- Fork this project.
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- For commit message, use [Conventional Commits](https://github.com/FlowingCode/DevelopmentConventions/blob/main/conventional-commits.md) to describe your change.
- Send a pull request for the original project.
- Comment on the original issue that you have implemented a fix for it.

## License & Author

This add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

Toggle Button Add-On is written by Flowing Code S.A.

# Developer Guide

## Getting started

```java
// Basic toggle button
ToggleButton toggle = new ToggleButton();

// With a field label (shown above the toggle)
ToggleButton toggle = new ToggleButton("Notifications");

// With a field label and initial value
ToggleButton toggle = new ToggleButton("Dark mode", true);

// With labels
ToggleButton toggle = new ToggleButton()
    .setLeftLabel("Off")
    .setRightLabel("On");

// With icons
ToggleButton toggle = new ToggleButton()
    .setLeftLabel("Dark")
    .setRightLabel("Light")
    .setLeftIcon(new Icon(VaadinIcon.MOON))
    .setRightIcon(new Icon(VaadinIcon.SUN_O));

// Listen to value changes
toggle.addValueChangeListener(e ->
    Notification.show("Toggle is now: " + (e.getValue() ? "on" : "off")));

// Enable label highlighting (active side uses the theme variant color, inactive side is dimmed)
ToggleButton toggle = new ToggleButton()
    .setLeftLabel("Off")
    .setRightLabel("On")
    .withHighlightLabel();

// Icons inside: [label] [icon] [switch] [icon] [label] (default is [icon] [label] [switch] [label] [icon])
ToggleButton toggle = new ToggleButton()
    .setLeftIcon(new Icon(VaadinIcon.MOON))
    .setLeftLabel("Dark")
    .setRightLabel("Light")
    .setRightIcon(new Icon(VaadinIcon.SUN_O))
    .withIconsInside();

// Apply theme variants
toggle.addThemeVariants(ToggleButtonVariant.PRIMARY);
toggle.addThemeVariants(ToggleButtonVariant.CONTRAST);

// Long swipe: wider track, optimized for touch (can be combined with size variants)
toggle.addThemeVariants(ToggleButtonVariant.LONGSWIPE);
toggle.addThemeVariants(ToggleButtonVariant.LONGSWIPE, ToggleButtonVariant.LARGE);

// Helper text, shown below the toggle and read by screen readers
ToggleButton toggle = new ToggleButton("Audit log retention", true);
toggle.setHelperText("Included on the Business plan.");
toggle.setReadOnly(true);
```

### Forms and validation

`ToggleButton` works with `Binder` like any other Vaadin field. A required toggle is valid when it is on:

```java
ToggleButton confirm = new ToggleButton("I confirm the trip details are correct")
    .setLeftLabel("No")
    .setRightLabel("Yes");

binder.forField(confirm)
    .asRequired("You must confirm the trip details to continue")
    .bind(Booking::isConfirmed, Booking::setConfirmed);
```

The error message can also be set manually with `setErrorMessage(String)` and `setInvalid(boolean)`.

### Accessibility

The toggle is announced by screen readers as a switch with its on/off state. Its accessible name is taken from, in this order:

1. The ARIA label, if set with `setAriaLabel(String)`
2. The field label
3. The side labels, joined with " / " (for example, "Off / On")

The side labels and icons are decorative for screen readers. When they carry meaning that "on" and "off" don't convey (for example, "Dark" and "Light"), include it in the field label or the ARIA label.

### Styling

Color variants can be customized with these CSS custom properties on `fc-toggle-button`: `--toggle-button-primary-color`, `--toggle-button-success-color`, `--toggle-button-warning-color`, `--toggle-button-error-color`, and `--toggle-button-contrast-color`.

The switch inside the component is a regular `vaadin-switch`, so it can also be styled with the core [Switch styling properties](https://vaadin.com/docs/latest/components/switch/styling), such as `--vaadin-switch-width` or `--vaadin-switch-background`:

```css
fc-toggle-button vaadin-switch {
  --vaadin-switch-width: 48px;
}
```

The rest of the component is rendered as regular elements that can be styled with these class names: `fc-toggle-button-label`, `fc-toggle-button-row`, `fc-toggle-button-left-label`, `fc-toggle-button-right-label`, `fc-toggle-button-left-icon`, `fc-toggle-button-right-icon`, `fc-toggle-button-helper`, and `fc-toggle-button-error`.

## Migrating from 1.x

Version 2.0 keeps the 1.x fluent API and value change events, but it has some breaking changes:

* It requires Vaadin 25.3 or newer and Java 21.
* `ToggleButton` extends `AbstractField` instead of `AbstractSinglePropertyField`. Value change listeners keep working, and the event type is still `ComponentValueChangeEvent<ToggleButton, Boolean>`.
* `ToggleButton` no longer implements `HasComponents`, so `add(...)` and `remove(...)` aren't available. Use `setLeftIcon(...)` and `setRightIcon(...)` for icons.
* Without a size variant, the toggle uses the native switch size of the active theme. Previously, the default was the same as `MEDIUM`. Add `ToggleButtonVariant.MEDIUM` to keep the 1.x size.
* The color variants write different values to the `theme` attribute: `color-primary`, `color-success`, `color-warning`, `color-error`, and `color-contrast` (previously `primary`, `success`, `warning`, `error`, and `contrast`). The Java constants in `ToggleButtonVariant` don't change. This only affects custom CSS that targets the attribute, such as `fc-toggle-button[theme~="error"]`. The old values collided with the theme names that `Binder` adds and removes for validation errors, so a toggle bound with `Binder` lost its `ERROR` or `WARNING` variant.
* Read-only and disabled states use the core Switch look. In read-only mode, the track border is solid instead of dashed. A checked read-only toggle with a color variant still shows the variant color, as a tint on the track border.
* The tooltip is attached to the switch, so it opens when hovering or focusing the switch, not the side labels.
* When swiping, the marker no longer follows the finger. The toggle changes state when the swipe passes half the track width.

## Special configuration when using Spring

By default, Vaadin Flow only includes `com/vaadin/flow/component` to be always scanned for UI components and views. For this reason, the add-on might need to be allowed in order to display correctly.

To do so, just add `com.flowingcode` to the `vaadin.allowed-packages` property in `src/main/resources/application.properties`, like:

```
vaadin.allowed-packages = com.vaadin,org.vaadin,dev.hilla,com.flowingcode
```

More information on Spring scanning configuration [here](https://vaadin.com/docs/latest/integrations/spring/configuration/#configure-the-scanning-of-packages).
