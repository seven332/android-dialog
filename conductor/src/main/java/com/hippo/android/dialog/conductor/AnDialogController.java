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

package com.hippo.android.dialog.conductor;

/*
 * Created by Hippo on 4/3/2017.
 */

import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hippo.android.dialog.base.DialogView;
import com.hippo.conductor.dialog.DialogController;

/**
 * A dialog {@link com.bluelinelabs.conductor.Controller} with
 * {@code alertDialogTheme} and {@link DialogView}.
 */
public abstract class AnDialogController extends DialogController {

  public AnDialogController() {}

  public AnDialogController(@StyleRes int themeId) {
    super(themeId);
  }

  /**
   * Do <b>NOT</b> call it.
   */
  @Keep
  public AnDialogController(Bundle bundle) {
    super(bundle);
  }

  @Override
  public int getActualThemeId() {
    final int themeId = getThemeId();
    if (themeId == 0) {
      final TypedValue outValue = new TypedValue();
      getActivity().getTheme().resolveAttribute(R.attr.alertDialogTheme, outValue, true);
      return outValue.resourceId;
    } else {
      return themeId;
    }
  }

  @Nullable
  @Override
  protected final View onCreateContentView
      (@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    DialogView view = onCreateDialogView(inflater, container);
    view.setDialog(this);
    return view;
  }

  /**
   * Create a {@link DialogView} for this dialog.
   */
  @NonNull
  protected abstract DialogView onCreateDialogView(
      @NonNull LayoutInflater inflater, @NonNull ViewGroup container);
}
