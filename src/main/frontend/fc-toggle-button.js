import { LitElement, html, css } from 'lit';

/**
 * Custom Toggle Button component.
 * Supports left and right labels, and is fully stylable.
 */
class FCToggleButton extends LitElement {
  static properties = {
    checked: { type: Boolean, reflect: true },
    leftLabel: { type: String },
    rightLabel: { type: String },
    disabled: { type: Boolean, reflect: true },
    readonly: { type: Boolean, reflect: true }
  };

  static styles = css`
    :host {
      display: inline-flex;
      align-items: center;
      gap: var(--lumo-space-s, 8px);
      font-family: var(--lumo-font-family);
      color: var(--lumo-body-text-color);
      cursor: pointer;
      user-select: none;
      transition: opacity 0.2s;
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
      gap: var(--lumo-space-s, 8px);
    }

    .label {
      font-size: var(--lumo-font-size-s, 14px);
      font-weight: 500;
      transition: color 0.3s;
    }

    .label.active {
      color: var(--lumo-primary-text-color);
    }

    .label.inactive {
      color: var(--lumo-secondary-text-color);
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
      background-color: var(--lumo-primary-color, #007bff);
    }

    /* Theme Variants */
    :host([theme~="success"][checked]) .switch {
      background-color: var(--lumo-success-color, #28a745);
    }

    :host([theme~="error"][checked]) .switch {
      background-color: var(--lumo-error-color, #dc3545);
    }

    :host([theme~="warning"][checked]) .switch {
      background-color: #ffc107; /* Custom warning color if Lumo doesn't provide one */
      color: rgba(0, 0, 0, 0.85);
    }

    :host([theme~="primary"][checked]) .switch {
      background-color: var(--lumo-primary-color, #007bff);
    }

    :host([theme~="contrast"][checked]) .switch {
      background-color: var(--lumo-contrast-color, #000);
    }

    :host([theme~="contrast"]) .slider {
      background-color: var(--lumo-base-color, #fff);
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
      background-color: var(--lumo-base-color, #fff);
      border-radius: 50%;
      box-shadow: var(--lumo-box-shadow-s, 0 2px 4px rgba(0,0,0,0.2));
      transition: transform 0.3s cubic-bezier(0.4, 0, 0.2, 1);
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
      font-size: var(--lumo-font-size-s, 14px);
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
      font-size: var(--lumo-font-size-xs, 12px);
    }

    :host([theme~="large"]) .switch {
      width: 56px;
      height: 32px;
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
      font-size: var(--lumo-font-size-l, 18px);
    }

    /* Readonly Styles: Unify the look for both checked/unchecked and variants */
    :host([readonly]) .switch {
      background-color: var(--lumo-contrast-10pct, #f0f0f0) !important;
      border: 3px dotted var(--lumo-contrast-30pct, #ccc) !important;
      box-sizing: border-box;
    }

    :host([readonly]) .slider {
      top: 0 !important;
      left: 0 !important;
      box-shadow: none !important;
      background-color: var(--lumo-contrast-40pct, #999) !important;
    }
  `;

  constructor() {
    super();
    this.checked = false;
    this.leftLabel = '';
    this.rightLabel = '';
    this.disabled = false;
    this.readonly = false;
    this.addEventListener('click', this._toggle);
  }

  _toggle(e) {
    if (this.disabled || this.readonly) return;
    this.checked = !this.checked;
    this.dispatchEvent(new CustomEvent('checked-changed', {
      detail: { value: this.checked },
      bubbles: true,
      composed: true
    }));
  }

  render() {
    return html`
      <div class="container">
        <slot name="left"></slot>
        ${this.leftLabel ? html`<span class="label ${!this.checked ? 'active' : 'inactive'}">${this.leftLabel}</span>` : ''}
        <div class="switch">
          <div class="slider"></div>
        </div>
        ${this.rightLabel ? html`<span class="label ${this.checked ? 'active' : 'inactive'}">${this.rightLabel}</span>` : ''}
        <slot name="right"></slot>
      </div>
    `;
  }
}

customElements.define('fc-toggle-button', FCToggleButton);
export { FCToggleButton };
