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
 * Created by Hippo on 4/6/2017.
 */

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import com.hippo.android.dialog.base.DialogView;
import com.hippo.android.dialog.base.DialogViewBuilder;
import com.hippo.android.dialog.conductor.AnDialogController;
import com.hippo.android.dialog.demo.Constants;

public class ListDialog extends AnDialogController {

  private static final String KEY_HEADER = "ListDialog:header";
  private static final String KEY_FOOTER = "ListDialog:footer";

  private boolean header;
  private boolean footer;

  public ListDialog(boolean header, boolean footer) {
    super();

    // Put args
    Bundle args = getArgs();
    args.putBoolean(KEY_HEADER, header);
    args.putBoolean(KEY_FOOTER, footer);

    this.header = header;
    this.footer = footer;
  }

  @Keep
  public ListDialog(Bundle bundle) {
    super(bundle);
    header = bundle.getBoolean(KEY_HEADER);
    footer = bundle.getBoolean(KEY_FOOTER);
  }

  protected String getTitle() {
    return Constants.LIST_TITLE;
  }

  protected String[] getItems() {
    return Constants.LIST;
  }

  @NonNull
  @Override
  protected DialogView onCreateDialogView(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup container) {
    DialogViewBuilder builder = new DialogViewBuilder();
    if (header) {
      builder.title(getTitle());
    }
    builder.items(getItems(), new OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(getActivity(), "Click " + which, Toast.LENGTH_SHORT).show();
      }
    });
    if (footer) {
      builder.positiveButton(android.R.string.ok, null)
          .negativeButton(android.R.string.cancel, null);
    }
    return builder.build(inflater, container);
  }
}
