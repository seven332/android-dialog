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

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.hippo.android.dialog.base.DialogView;
import com.hippo.android.dialog.base.DialogViewBuilder;
import com.hippo.android.dialog.conductor.AnDialogController;
import com.hippo.android.dialog.demo.Constants;

public class StackButtonDialog extends AnDialogController {

  @NonNull
  @Override
  protected DialogView onCreateDialogView(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup container) {
    DialogView view = new DialogViewBuilder()
        .title(Constants.MESSAGE_TITLE)
        .message(Constants.MESSAGE)
        .positiveButton("观止矣！若有他文，吾不敢请已", null)
        .negativeButton("美哉轮焉！美哉奂焉！", null)
        .stackButtons(true)
        .build(inflater, container);

    view.getTitleView().setTextColor(Color.RED);
    view.getMessageView().setTextColor(Color.BLUE);
    view.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.GREEN);

    return view;
  }
}
