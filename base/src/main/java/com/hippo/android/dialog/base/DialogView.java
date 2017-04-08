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
 * Created by Hippo on 4/4/2017.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * The view of dialog. Use {@link DialogViewBuilder} to create it.
 */
public class DialogView extends LinearLayoutCompat {

  private DialogInterface dialog;

  public DialogView(Context context) {
    super(context);
  }

  public DialogView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public DialogView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  /**
   * Gets the title view used in the dialog.
   * Returns {@code null} if it does not exist.
   */
  @Nullable
  public TextView getTitleView() {
    return (TextView) findViewById(R.id.andialog_title);
  }

  /**
   * Gets the scroll view used in the dialog.
   * Returns {@code null} if it does not exist.
   */
  @Nullable
  public ScrollView getScrollView() {
    return (ScrollView) findViewById(R.id.andialog_scroll);
  }

  /**
   * Gets the message view used in the dialog.
   * Returns {@code null} if it does not exist.
   */
  @Nullable
  public TextView getMessageView() {
    return (TextView) findViewById(R.id.andialog_message);
  }

  /**
   * Gets the list view used in the dialog.
   * Returns {@code null} if it does not exist.
   */
  @Nullable
  public ListView getListView() {
    return (ListView) findViewById(R.id.andialog_list);
  }

  /**
   * Gets one of the buttons used in the dialog.
   * Returns {@code null} if the specified button does not exist.
   */
  @Nullable
  public Button getButton(int which) {
    int id;
    switch (which) {
      case DialogInterface.BUTTON_POSITIVE:
        id = R.id.andialog_positive_button;
        break;
      case DialogInterface.BUTTON_NEGATIVE:
        id = R.id.andialog_negative_button;
        break;
      case DialogInterface.BUTTON_NEUTRAL:
        id = R.id.andialog_neutral_button;
        break;
      default:
        return null;
    }
    return (Button) findViewById(id);
  }

  /**
   * Attach this {@code DialogView} to a dialog.
   * <p>
   * Must be called after creating it.
   */
  public void setDialog(DialogInterface dialog) {
    this.dialog = dialog;
  }

  DialogInterface getDialog() {
    if (dialog == null) {
      throw new IllegalStateException();
    }
    return dialog;
  }
}
