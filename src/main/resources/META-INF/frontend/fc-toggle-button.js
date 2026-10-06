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

/**
 * Client-side behavior for `<fc-toggle-button>`.
 *
 * The layout is rendered by the server as light DOM around a core `<vaadin-switch>`, and styled
 * by `styles/fc-toggle-button.css`. This element only adds what needs to run in the browser:
 * toggling when the labels or icons are clicked, and toggling with a horizontal swipe.
 *
 * Toggling always goes through a click on the switch's native input, so read-only, disabled and
 * the user `change` event behave exactly as when clicking the switch itself.
 */
class ToggleButton extends HTMLElement {
  constructor() {
    super();
    this._touchStartX = null;
    this._touchStartY = null;
    this._isSwiping = false;
    this._suppressClick = false;

    // Swallows the click a browser may synthesize after a swipe, so the switch doesn't toggle twice
    this.addEventListener('click', (e) => {
      if (this._suppressClick) {
        this._suppressClick = false;
        e.preventDefault();
        e.stopPropagation();
      }
    }, true);
    this.addEventListener('click', (e) => this._onClick(e));
    this.addEventListener('touchstart', (e) => this._onTouchStart(e), { passive: true });
    this.addEventListener('touchmove', (e) => this._onTouchMove(e), { passive: false });
    this.addEventListener('touchend', (e) => this._onTouchEnd(e));
  }

  get _switch() {
    return this.querySelector('vaadin-switch');
  }

  _toggle() {
    const sw = this._switch;
    (sw?.inputElement || sw?.querySelector('input'))?.click();
  }

  _onClick(e) {
    // The switch handles its own clicks; links, helper text and error message don't toggle
    if (e.target.closest('vaadin-switch, a, .fc-toggle-button-helper, .fc-toggle-button-error')) return;
    this._toggle();
  }

  _onTouchStart(e) {
    if (!e.target.closest('.fc-toggle-button-row')) return;
    const touch = e.touches[0];
    this._touchStartX = touch.clientX;
    this._touchStartY = touch.clientY;
    this._isSwiping = false;
  }

  _onTouchMove(e) {
    if (this._touchStartX === null) return;
    const touch = e.touches[0];
    const dx = touch.clientX - this._touchStartX;
    const dy = touch.clientY - this._touchStartY;
    // Only capture horizontal swipes
    if (!this._isSwiping && (Math.abs(dx) < 10 || Math.abs(dx) < Math.abs(dy))) return;
    this._isSwiping = true;
    e.preventDefault();
  }

  _onTouchEnd(e) {
    if (this._touchStartX === null) return;
    const dx = e.changedTouches[0].clientX - this._touchStartX;
    const wasSwiping = this._isSwiping;
    this._touchStartX = null;
    this._touchStartY = null;
    this._isSwiping = false;
    if (!wasSwiping) return;

    // Threshold: half of the track width
    const sw = this._switch;
    const threshold = (sw?.offsetWidth || 40) * 0.5;
    if (Math.abs(dx) >= threshold && (dx > 0) !== !!sw?.checked) {
      this._toggle();
    }
    this._suppressClick = true;
    setTimeout(() => {
      this._suppressClick = false;
    }, 400);
  }
}

customElements.define('fc-toggle-button', ToggleButton);
export { ToggleButton };
