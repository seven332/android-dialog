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
            "Example",
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
            controller = new CustomDialog();
            break;
          case 1:
            controller = new StackButtonDialog();
            break;
          case 2:
            controller = new CustomContentDialog(false);
            break;
          case 3:
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
