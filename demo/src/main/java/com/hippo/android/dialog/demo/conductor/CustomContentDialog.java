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

package com.hippo.android.dialog.demo.conductor;

/*
 * Created by Hippo on 4/5/2017.
 */

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hippo.android.dialog.base.DialogView;
import com.hippo.android.dialog.base.DialogViewBuilder;
import com.hippo.android.dialog.conductor.AnDialogController;
import com.hippo.android.dialog.demo.R;

public class CustomContentDialog extends AnDialogController {

  private static final String KEY_IN_SCROLL_VIEW = "CustomContentDialog:in_scroll_view";

  private boolean inScrollView;

  public CustomContentDialog() {
    this(true);
  }

  public CustomContentDialog(boolean inScrollView) {
    this.inScrollView = inScrollView;
  }

  @Keep
  public CustomContentDialog(Bundle bundle) {
    super(bundle);
    inScrollView = bundle.getBoolean(KEY_IN_SCROLL_VIEW);
  }

  @SuppressLint("InflateParams")
  @NonNull
  @Override
  protected DialogView onCreateDialogView(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup container) {
    View content = inflater.inflate(R.layout.dialog_content_custom, null);
    return new DialogViewBuilder()
        .title("Custom Title")
        .customContent(content, inScrollView)
        .build(inflater, container);
  }
}
