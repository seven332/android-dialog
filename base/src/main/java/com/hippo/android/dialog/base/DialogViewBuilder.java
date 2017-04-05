/*
 * Copyright 2017 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.android.dialog.base;

/*
 * Created by Hippo on 4/3/2017.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.method.MovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A builder to build {@link DialogView}.
 */
public class DialogViewBuilder {

  private int titleResId;
  private CharSequence title;

  private int messageResId;
  private CharSequence message;
  private MovementMethod messageMovementMethod;

  private boolean stackButtons;

  private int positiveButtonResId;
  private CharSequence positiveButtonText;
  private DialogInterface.OnClickListener positiveButtonListener;

  private int negativeButtonResId;
  private CharSequence negativeButtonText;
  private DialogInterface.OnClickListener negativeButtonListener;

  private int neutralButtonResId;
  private CharSequence neutralButtonText;
  private DialogInterface.OnClickListener neutralButtonListener;

  private View customHeader;
  private View customContent;
  private View customFooter;

  /**
   * Sets the title to display as header.
   *
   * @see #setTitle(CharSequence)
   */
  public DialogViewBuilder setTitle(@StringRes int resId) {
    titleResId = resId;
    return this;
  }

  /**
   * Sets the title to display as header.
   *
   * @see #setTitle(int)
   */
  public DialogViewBuilder setTitle(CharSequence text) {
    title = text;
    return this;
  }

  /**
   * Sets the message to display as content.
   *
   * @see #setMessage(CharSequence)
   * @see #setMessageMovementMethod(MovementMethod)
   */
  public DialogViewBuilder setMessage(int resId) {
    messageResId = resId;
    return this;
  }

  /**
   * Sets the message to display as content.
   *
   * @see #setMessage(int)
   * @see #setMessageMovementMethod(MovementMethod)
   */
  public DialogViewBuilder setMessage(CharSequence text) {
    message = text;
    return this;
  }

  /**
   * Sets a {@link MovementMethod} for the message view.
   *
   * @see #setMessage(int)
   * @see #setMessage(CharSequence)
   */
  public DialogViewBuilder setMessageMovementMethod(MovementMethod method) {
    messageMovementMethod = method;
    return this;
  }

  /**
   * Shows stacked full-width buttons instead of side-by-side buttons.
   */
  public DialogViewBuilder setStackButtons(boolean stack) {
    stackButtons = stack;
    return this;
  }

  /**
   * Sets positive button text and listener to be invoked
   * when the positive button is pressed.
   * If the listener is {@code null}, the dialog dismissed instead
   * when the positive button is pressed.
   *
   * @see #setPositiveButton(CharSequence, DialogInterface.OnClickListener)
   */
  public DialogViewBuilder setPositiveButton(
      @StringRes int resId, DialogInterface.OnClickListener listener) {
    positiveButtonResId = resId;
    positiveButtonListener = listener;
    return this;
  }

  /**
   * Sets positive button text and listener to be invoked
   * when the positive button is pressed.
   * If the listener is {@code null}, the dialog dismissed instead
   * when the positive button is pressed.
   *
   * @see #setPositiveButton(int, DialogInterface.OnClickListener)
   */
  public DialogViewBuilder setPositiveButton(
      CharSequence text, DialogInterface.OnClickListener listener) {
    positiveButtonText = text;
    positiveButtonListener = listener;
    return this;
  }

  /**
   * Sets negative button text and listener to be invoked
   * when the negative button is pressed.
   * If the listener is {@code null}, the dialog dismissed instead
   * when the negative button is pressed.
   *
   * @see #setNegativeButton(CharSequence, DialogInterface.OnClickListener)
   */
  public DialogViewBuilder setNegativeButton(
      @StringRes int resId, DialogInterface.OnClickListener listener) {
    negativeButtonResId = resId;
    negativeButtonListener = listener;
    return this;
  }

  /**
   * Sets negative button text and listener to be invoked
   * when the negative button is pressed.
   * If the listener is {@code null}, the dialog dismissed instead
   * when the negative button is pressed.
   *
   * @see #setNegativeButton(int, DialogInterface.OnClickListener)
   */
  public DialogViewBuilder setNegativeButton(
      CharSequence text, DialogInterface.OnClickListener listener) {
    negativeButtonText = text;
    negativeButtonListener = listener;
    return this;
  }

  /**
   * Sets neutral button text and listener to be invoked
   * when the neutral button is pressed.
   * If the listener is {@code null}, the dialog dismissed instead
   * when the neutral button is pressed.
   *
   * @see #setNeutralButton(CharSequence, DialogInterface.OnClickListener)
   */
  public DialogViewBuilder setNeutralButton(
      @StringRes int resId, DialogInterface.OnClickListener listener) {
    neutralButtonResId = resId;
    neutralButtonListener = listener;
    return this;
  }

  /**
   * Sets neutral button text and listener to be invoked
   * when the neutral button is pressed.
   * If the listener is {@code null}, the dialog dismissed instead
   * when the neutral button is pressed.
   *
   * @see #setNeutralButton(int, DialogInterface.OnClickListener)
   */
  public DialogViewBuilder setNeutralButton(
      CharSequence text, DialogInterface.OnClickListener listener) {
    neutralButtonText = text;
    neutralButtonListener = listener;
    return this;
  }

  public DialogViewBuilder customHeader(View header) {
    customHeader = header;
    return this;
  }

  public DialogViewBuilder customContent(View content) {
    customContent = content;
    return this;
  }

  public DialogViewBuilder customFooter(View footer) {
    customFooter = footer;
    return this;
  }

  private void resolveResIds(Context context) {
    if (titleResId != 0) {
      title = context.getString(titleResId);
    }
    if (messageResId != 0) {
      message = context.getString(messageResId);
    }
    if (positiveButtonResId != 0) {
      positiveButtonText = context.getString(positiveButtonResId);
    }
    if (negativeButtonResId != 0) {
      negativeButtonText = context.getString(negativeButtonResId);
    }
    if (neutralButtonResId != 0) {
      neutralButtonText = context.getString(neutralButtonResId);
    }
  }

  private boolean buildHeader(LayoutInflater inflater, DialogView root) {
    if (customHeader != null) {
      // Custom header
      root.addView(customHeader,
          ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      return true;

    } else if (title != null) {
      // Title
      inflater.inflate(R.layout.andialog_title, root);
      TextView titleView = (TextView) root.findViewById(R.id.andialog_title);
      titleView.setText(title);
      return true;
    }

    return false;
  }

  private IndicatingScrollView inflaterScroll(LayoutInflater inflater, ViewGroup container) {
    inflater.inflate(R.layout.andialog_scroll, container);
    return (IndicatingScrollView) container.findViewById(R.id.andialog_scroll);
  }

  private Indicator buildContent(LayoutInflater inflater, DialogView root) {
    if (customContent != null) {
      // Custom content
      root.addView(customContent,
          ViewGroup.LayoutParams.MATCH_PARENT, 0);
      ((DialogView.LayoutParams) customContent.getLayoutParams()).weight = 1;

      if (customContent instanceof Indicator) {
        return (Indicator) customContent;
      } else {
        return null;
      }

    } else if (message != null) {
      // Message
      IndicatingScrollView scrollView = inflaterScroll(inflater, root);
      inflater.inflate(R.layout.andialog_message, scrollView);
      TextView messageView = (TextView) scrollView.findViewById(R.id.andialog_message);
      messageView.setText(message);
      if (messageMovementMethod != null) {
        messageView.setMovementMethod(messageMovementMethod);
      }
      return scrollView;
    }

    return null;
  }

  private boolean buildFooter(LayoutInflater inflater, DialogView root) {
    if (customFooter != null) {
      // Custom footer
      root.addView(customFooter,
          ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      return true;

    } else if (positiveButtonText != null || negativeButtonText != null
        || neutralButtonText != null) {
      // Side buttons
      inflater.inflate(
          stackButtons ? R.layout.andialog_stack_buttons : R.layout.andialog_side_buttons,
          root);

      Button positive = (Button) root.findViewById(R.id.andialog_positive_button);
      if (positiveButtonText != null) {
        positive.setText(positiveButtonText);
        positive.setOnClickListener(
            new ButtonClickListener(root, DialogInterface.BUTTON_POSITIVE, positiveButtonListener));
      } else {
        positive.setVisibility(View.GONE);
      }

      Button negative = (Button) root.findViewById(R.id.andialog_negative_button);
      if (negativeButtonText != null) {
        negative.setText(negativeButtonText);
        negative.setOnClickListener(
            new ButtonClickListener(root, DialogInterface.BUTTON_NEGATIVE, negativeButtonListener));
      } else {
        negative.setVisibility(View.GONE);
      }

      Button neutral = (Button) root.findViewById(R.id.andialog_neutral_button);
      if (neutralButtonText != null) {
        neutral.setText(neutralButtonText);
        neutral.setOnClickListener(
            new ButtonClickListener(root, DialogInterface.BUTTON_NEUTRAL, neutralButtonListener));
      } else {
        neutral.setVisibility(View.GONE);
      }

      return true;
    }

    return false;
  }

  /**
   * Builds a {@link DialogView}.
   * <p>
   * Must call {@link DialogView#setDialog(DialogInterface)} later.
   */
  public DialogView build(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
    resolveResIds(inflater.getContext());

    DialogView root = (DialogView) inflater.inflate(R.layout.andialog_base, container, false);
    boolean hasHeader = buildHeader(inflater, root);
    Indicator indicator = buildContent(inflater, root);
    boolean hasFooter = buildFooter(inflater, root);

    // Configures indicator
    if (indicator != null) {
      indicator.setTopIndicatorEnabled(hasHeader);
      indicator.setBottomIndicatorEnabled(hasFooter);
    }

    return root;
  }

  private static final class ButtonClickListener implements View.OnClickListener {

    private DialogView view;
    private int which;
    private DialogInterface.OnClickListener listener;

    public ButtonClickListener(
        DialogView view, int which, DialogInterface.OnClickListener listener) {
      this.view = view;
      this.which = which;
      this.listener = listener;
    }

    @Override
    public void onClick(View v) {
      if (listener != null) {
        listener.onClick(view.getDialog(), which);
      } else {
        // Dismiss the dialog if no listener
        view.getDialog().dismiss();
      }
    }
  }
}
