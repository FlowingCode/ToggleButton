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
import { LitElement, html, css } from 'lit';

/**
 * Custom Toggle Button component.
 * Supports left and right labels, and is fully stylable.
 */
class ToggleButton extends LitElement {
  static properties = {
    checked: { type: Boolean, reflect: true },
    label: { type: String },
    leftLabel: { type: String },
    rightLabel: { type: String },
    disabled: { type: Boolean, reflect: true },
    readonly: { type: Boolean, reflect: true },
    highlightLabel: { type: Boolean, reflect: true },
    iconsInside: { type: Boolean }
  };

  static styles = css`
    :host {
      display: inline-flex;
      flex-direction: column;
      align-items: flex-start;
      gap: var(--lumo-space-xs, var(--vaadin-gap-xs));
      font-family: var(--lumo-font-family, var(--aura-font-family));
      color: var(--lumo-body-text-color, var(--vaadin-text-color));
      cursor: pointer;
      user-select: none;
      transition: opacity 0.2s;
    }

    .field-label {
      font-size: var(--lumo-font-size-s, var(--aura-font-size-s));
      font-weight: 500;
      color: var(--lumo-secondary-text-color, var(--vaadin-text-color-secondary));
      line-height: 1;
    }

    :host([disabled]) {
      opacity: 0.5;
      cursor: not-allowed;
      pointer-events: none;
    }

    :host([readonly]) {
      cursor: default;
      pointer-events: none;
    }

    .container {
      display: flex;
      align-items: center;
      gap: var(--lumo-space-s, var(--vaadin-gap-s));
    }

    .label {
      font-size: var(--lumo-font-size-s, var(--aura-font-size-s));
      font-weight: 500;
      transition: color 0.3s;
    }

    .label.active {
      color: var(--lumo-primary-text-color, var(--aura-blue-text));
    }

    :host([theme~="success"]) .label.active {
      color: var(--lumo-success-text-color, var(--aura-green-text));
    }

    :host([theme~="error"]) .label.active {
      color: var(--lumo-error-text-color, var(--aura-red-text));
    }

    :host([theme~="warning"]) .label.active {
      color: var(--lumo-warning-text-color, var(--aura-orange-text));
    }

    :host([theme~="contrast"]) .label.active {
      color: var(--lumo-contrast, var(--aura-neutral));
    }

    .label.inactive {
      color: var(--lumo-secondary-text-color, var(--vaadin-text-color-secondary));
      opacity: 0.7;
    }

    .switch {
      position: relative;
      width: 44px;
      height: 24px;
      background-color: var(--lumo-contrast-20pct, #e0e0e0);
      border-radius: 12px;
      transition: background-color 0.3s cubic-bezier(0.4, 0, 0.2, 1);
      display: inline-block;
    }

    :host([checked]) .switch {
      background-color: var(--lumo-primary-color, var(--aura-blue));
    }

    /* Theme Variants */
    :host([theme~="success"][checked]) .switch {
      background-color: var(--lumo-success-color, var(--aura-green));
    }

    :host([theme~="error"][checked]) .switch {
      background-color: var(--lumo-error-color, var(--aura-red));
    }

    :host([theme~="warning"][checked]) .switch {
      background-color: var(--lumo-warning-color, var(--aura-yellow));
      color: rgba(0, 0, 0, 0.85);
    }

    :host([theme~="primary"][checked]) .switch {
      background-color: var(--lumo-primary-color, var(--aura-blue));
    }

    :host([theme~="contrast"][checked]) .switch {
      background-color: var(--lumo-contrast, var(--aura-neutral));
    }

    :host([theme~="contrast"]) .slider {
      background-color: var(--lumo-base-color, var(--vaadin-background-color));
    }

    /* Slider colors for variants if needed */
    :host([theme~="warning"]) .slider {
      background-color: #fff;
    }

    .slider {
      position: absolute;
      top: 3px;
      left: 3px;
      width: 18px;
      height: 18px;
      background-color: var(--lumo-base-color, var(--vaadin-background-color));
      border-radius: 50%;
      box-shadow: var(--lumo-box-shadow-s, var(--aura-shadow-xs));
      transition: transform 0.3s cubic-bezier(0.4, 0, 0.2, 1), width 0.3s cubic-bezier(0.4, 0, 0.2, 1);
    }

    :host([checked]) .slider {
      transform: translateX(20px);
    }

    /* Hover effects */
    :host(:hover:not([disabled])) .switch {
      filter: brightness(0.95);
    }

    :host([checked]:hover:not([disabled])) .switch {
      filter: brightness(1.1);
    }

    .switch:active .slider {
      width: 22px;
    }

    :host([checked]) .switch:active .slider {
      transform: translateX(16px);
    }

    :host([theme~="medium"]) .switch:active .slider {
      width: 22px;
    }

    :host([theme~="medium"][checked]) .switch:active .slider {
      transform: translateX(16px);
    }

    :host([theme~="small"]) .switch:active .slider {
      width: 17px;
    }

    :host([theme~="small"][checked]) .switch:active .slider {
      transform: translateX(11px);
    }

    :host([theme~="large"]) .switch:active .slider {
      width: 30px;
    }

    :host([theme~="large"][checked]) .switch:active .slider {
      transform: translateX(18px);
    }

    /* Size Variants */
    :host([theme~="medium"]) .switch {
      width: 44px;
      height: 24px;
    }

    :host([theme~="medium"]) .slider {
      width: 18px;
      height: 18px;
      top: 3px;
      left: 3px;
    }

    :host([theme~="medium"][checked]) .slider {
      transform: translateX(20px);
    }

    :host([theme~="medium"]) .label {
      font-size: var(--lumo-font-size-s, var(--aura-font-size-s));
    }

    :host([theme~="small"]) .switch {
      width: 32px;
      height: 18px;
    }

    :host([theme~="small"]) .slider {
      width: 14px;
      height: 14px;
      top: 2px;
      left: 2px;
    }

    :host([theme~="small"][checked]) .slider {
      transform: translateX(14px);
    }

    :host([theme~="small"]) .label {
      font-size: var(--lumo-font-size-xs, var(--aura-font-size-xs));
    }

    :host([theme~="large"]) .switch {
      width: 56px;
      height: 32px;
      border-radius: 16px;
    }

    :host([theme~="large"]) .slider {
      width: 24px;
      height: 24px;
      top: 4px;
      left: 4px;
    }

    :host([theme~="large"][checked]) .slider {
      transform: translateX(24px);
    }

    :host([theme~="large"]) .label {
      font-size: var(--lumo-font-size-l, var(--aura-font-size-l));
    }

    :host([theme~="longswipe"]) .switch {
      width: 72px;
      height: 24px;
    }

    :host([theme~="longswipe"]) .slider {
      width: 18px;
      height: 18px;
      top: 3px;
      left: 3px;
    }

    :host([theme~="longswipe"][checked]) .slider {
      transform: translateX(48px);
    }

    :host([theme~="longswipe"]) .switch:active .slider {
      width: 22px;
    }

    :host([theme~="longswipe"][checked]) .switch:active .slider {
      transform: translateX(44px);
    }

    :host([theme~="longswipe"]) .label {
      font-size: var(--lumo-font-size-s, var(--aura-font-size-s));
    }

    :host([theme~="longswipe"][theme~="small"]) .switch {
      width: 60px;
      height: 18px;
    }

    :host([theme~="longswipe"][theme~="small"]) .slider {
      width: 14px;
      height: 14px;
      top: 2px;
      left: 2px;
    }

    :host([theme~="longswipe"][theme~="small"][checked]) .slider {
      transform: translateX(42px);
    }

    :host([theme~="longswipe"][theme~="small"]) .switch:active .slider {
      width: 17px;
    }

    :host([theme~="longswipe"][theme~="small"][checked]) .switch:active .slider {
      transform: translateX(39px);
    }

    :host([theme~="longswipe"][theme~="large"]) .switch {
      width: 84px;
      height: 32px;
      border-radius: 16px;
    }

    :host([theme~="longswipe"][theme~="large"]) .slider {
      width: 24px;
      height: 24px;
      top: 4px;
      left: 4px;
    }

    :host([theme~="longswipe"][theme~="large"][checked]) .slider {
      transform: translateX(52px);
    }

    :host([theme~="longswipe"][theme~="large"]) .switch:active .slider {
      width: 30px;
    }

    :host([theme~="longswipe"][theme~="large"][checked]) .switch:active .slider {
      transform: translateX(46px);
    }

    /* Readonly Styles: Unify the look for both checked/unchecked and variants */
    :host([readonly]) .switch {
      background-color: var(--lumo-contrast-10pct, var(--vaadin-background-container)) !important;
      border: 3px dotted var(--lumo-contrast-30pct, var(--vaadin-border-color)) !important;
      box-sizing: border-box;
    }

    :host([readonly]) .slider {
      top: 0 !important;
      left: 0 !important;
      box-shadow: none !important;
      background-color: var(--lumo-contrast-40pct, var(--vaadin-background-container-strong)) !important;
    }
  `;

  constructor() {
    super();
    this.checked = false;
    this.label = '';
    this.leftLabel = '';
    this.rightLabel = '';
    this.disabled = false;
    this.readonly = false;
    this.highlightLabel = false;
    this.iconsInside = false;
    this._touchStartX = null;
    this._touchStartY = null;
    this._isSwiping = false;
    this._swipeHandled = false;
    this.addEventListener('click', this._onClick.bind(this));
    this.addEventListener('touchstart', this._onTouchStart.bind(this), { passive: true });
    this.addEventListener('touchmove', this._onTouchMove.bind(this), { passive: false });
    this.addEventListener('touchend', this._onTouchEnd.bind(this));
  }

  _fireChange() {
    this.dispatchEvent(new CustomEvent('checked-changed', {
      detail: { value: this.checked },
      bubbles: true,
      composed: true
    }));
  }

  _onClick(e) {
    if (this._swipeHandled) {
      this._swipeHandled = false;
      return;
    }
    if (this.disabled || this.readonly) return;
    this.checked = !this.checked;
    this._fireChange();
  }

  _onTouchStart(e) {
    if (this.disabled || this.readonly) return;
    const touch = e.touches[0];
    this._touchStartX = touch.clientX;
    this._touchStartY = touch.clientY;
    this._isSwiping = false;
    this._swipeHandled = false;

    const slider = this.shadowRoot.querySelector('.slider');
    if (slider) {
      slider.style.transition = 'none';
    }
  }

  _onTouchMove(e) {
    if (this._touchStartX === null || this.disabled || this.readonly) return;
    const touch = e.touches[0];
    const dx = touch.clientX - this._touchStartX;
    const dy = touch.clientY - this._touchStartY;

    // Only capture horizontal swipes
    if (!this._isSwiping && Math.abs(dx) < Math.abs(dy)) return;
    this._isSwiping = true;
    e.preventDefault();

    const switchEl = this.shadowRoot.querySelector('.switch');
    const slider = this.shadowRoot.querySelector('.slider');
    if (!switchEl || !slider) return;

    const switchWidth = switchEl.offsetWidth;
    const sliderWidth = slider.offsetWidth;
    const gap = parseInt(getComputedStyle(slider).left);
    const maxTranslate = switchWidth - sliderWidth - gap * 2;
    const baseTranslate = this.checked ? maxTranslate : 0;
    const clamped = Math.max(0, Math.min(maxTranslate, baseTranslate + dx));
    slider.style.transform = `translateX(${clamped}px)`;
  }

  _onTouchEnd(e) {
    if (this._touchStartX === null || this.disabled || this.readonly) return;
    const slider = this.shadowRoot.querySelector('.slider');
    const switchEl = this.shadowRoot.querySelector('.switch');

    if (!this._isSwiping) {
      if (slider) slider.style.transition = '';
      this._touchStartX = null;
      this._touchStartY = null;
      return;
    }

    const touch = e.changedTouches[0];
    const dx = touch.clientX - this._touchStartX;

    // Threshold: 50% of the slider's actual travel range
    const sliderWidth = slider ? slider.offsetWidth : 18;
    const gap = slider ? parseInt(getComputedStyle(slider).left) : 3;
    const maxTranslate = switchEl ? switchEl.offsetWidth - sliderWidth - gap * 2 : 20;
    const threshold = maxTranslate * 0.5;

    const newChecked = dx > 0;
    if (Math.abs(dx) >= threshold && newChecked !== this.checked) {
      this.checked = newChecked;
      this._fireChange();
      this._swipeHandled = true;
    }

    // Re-enable transition, then on the next frame clear the inline transform so
    // the CSS-driven position animates smoothly from the current mid-swipe position.
    if (slider) slider.style.transition = '';
    requestAnimationFrame(() => {
      if (slider) slider.style.transform = '';
    });

    this._touchStartX = null;
    this._touchStartY = null;
    this._isSwiping = false;
  }

  render() {
    const leftLabelClass = this.highlightLabel && !this.checked ? 'active' : this.highlightLabel ? 'inactive' : '';
    const rightLabelClass = this.highlightLabel && this.checked ? 'active' : this.highlightLabel ? 'inactive' : '';
    const leftLabelEl = this.leftLabel ? html`<span class="label ${leftLabelClass}">${this.leftLabel}</span>` : '';
    const rightLabelEl = this.rightLabel ? html`<span class="label ${rightLabelClass}">${this.rightLabel}</span>` : '';
    const leftSlot = html`<slot name="left"></slot>`;
    const rightSlot = html`<slot name="right"></slot>`;
    return html`
      ${this.label ? html`<span class="field-label">${this.label}</span>` : ''}
      <div class="container">
        ${this.iconsInside ? leftLabelEl : leftSlot}
        ${this.iconsInside ? leftSlot : leftLabelEl}
        <div class="switch">
          <div class="slider"></div>
        </div>
        ${this.iconsInside ? rightSlot : rightLabelEl}
        ${this.iconsInside ? rightLabelEl : rightSlot}
      </div>
    `;
  }
}

customElements.define('fc-toggle-button', ToggleButton);
export { ToggleButton };
