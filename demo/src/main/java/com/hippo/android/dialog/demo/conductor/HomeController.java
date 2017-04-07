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
 * Created by Hippo on 4/3/2017.
 */

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.hippo.android.dialog.demo.R;

public class HomeController extends Controller {

  @NonNull
  @Override
  protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    ListView list = (ListView) inflater.inflate(R.layout.controller_home, container, false);
    list.setAdapter(new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1,
        new String[] {
            "Message",
            "Message without Header",
            "Message without Footer",
            "Message without Header and Footer",
            "Long Message",
            "Long Message without Header",
            "Long Message without Footer",
            "Long Message without Header and Footer",
            "List",
            "List without Header",
            "List without Footer",
            "List without Header and Footer",
            "Long List",
            "Long List without Header",
            "Long List without Footer",
            "Long List without Header and Footer",
            "Stack Buttons",
            "Custom Content",
            "Custom Content in ScrollView",
        }));
    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Router router = getRouter();
        if (router == null) {
          return;
        }

        Controller controller;
        switch (position) {
          case 0:
            controller = new MessageDialog(true, true);
            break;
          case 1:
            controller = new MessageDialog(false, true);
            break;
          case 2:
            controller = new MessageDialog(true, false);
            break;
          case 3:
            controller = new MessageDialog(false, false);
            break;
          case 4:
            controller = new LongMessageDialog(true, true);
            break;
          case 5:
            controller = new LongMessageDialog(false, true);
            break;
          case 6:
            controller = new LongMessageDialog(true, false);
            break;
          case 7:
            controller = new LongMessageDialog(false, false);
            break;
          case 8:
            controller = new ListDialog(true, true);
            break;
          case 9:
            controller = new ListDialog(false, true);
            break;
          case 10:
            controller = new ListDialog(true, false);
            break;
          case 11:
            controller = new ListDialog(false, false);
            break;
          case 12:
            controller = new LongListDialog(true, true);
            break;
          case 13:
            controller = new LongListDialog(false, true);
            break;
          case 14:
            controller = new LongListDialog(true, false);
            break;
          case 15:
            controller = new LongListDialog(false, false);
            break;
          case 16:
            controller = new StackButtonDialog();
            break;
          case 17:
            controller = new CustomContentDialog(false);
            break;
          case 18:
            controller = new CustomContentDialog(true);
            break;
          default:
            throw new IllegalStateException();
        }

        router.pushController(RouterTransaction.with(controller));
      }
    });
    return list;
  }
}
