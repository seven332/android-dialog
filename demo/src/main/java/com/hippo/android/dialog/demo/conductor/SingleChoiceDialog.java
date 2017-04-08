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
 * Created by Hippo on 4/7/2017.
 */

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
import com.hippo.android.dialog.base.DialogViewBuilder;
import com.hippo.android.dialog.demo.Constants;

public class SingleChoiceDialog extends BaseDialog {

  public SingleChoiceDialog(boolean header, boolean footer) {
    super(header, footer);
  }

  public SingleChoiceDialog(Bundle bundle) {
    super(bundle);
  }

  @Override
  protected String getTitle() {
    return Constants.LIST_TITLE;
  }

  protected String[] getItems() {
    return Constants.LIST;
  }

  @Override
  protected void onAddDialogContent(DialogViewBuilder builder) {
    int checkedItem = -1;
    DialogInterface.OnClickListener listener = null;
    if (getHeader()) {
      checkedItem = getItems().length - 1;
    }
    if (getFooter()) {
      listener = new OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          Toast.makeText(getActivity(), "Click " + which, Toast.LENGTH_SHORT).show();
        }
      };
    }
    builder.singleChoice(getItems(), checkedItem, listener);
  }
}
