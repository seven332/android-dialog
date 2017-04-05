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
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import com.hippo.android.dialog.base.DialogView;
import com.hippo.android.dialog.base.DialogViewBuilder;
import com.hippo.android.dialog.conductor.AnDialogController;

public class StackButtonDialog extends AnDialogController {

  @NonNull
  @Override
  protected DialogView onCreateDialogView(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup container) {
    return new DialogViewBuilder()
        .setTitle("Use Google's location service?")
        .setMessage("Let Google help apps determine location. "
            + "This means sending anonymous location data to Google, "
            + "even when no apps are running.")
        .setPositiveButton("turn on speed boost", new OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getActivity(), "Don't turn it on", Toast.LENGTH_SHORT).show();
          }
        })
        .setNegativeButton("no thanks", null)
        .setStackButtons(true)
        .build(inflater, container);
  }
}
