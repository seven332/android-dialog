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
   * Attach this {@code DialogView} to a dialog.
   * <p>
   * Must call after creating it.
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
