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
import java.lang.reflect.Constructor;

public class HomeController extends Controller {

  private static final String[] TITLES = {
      "Message",
      "Long Message",
      "List",
      "Long List",
      "Single Choice",
      "Long Single Choice",
      "Multi Choice",
      "Long Multi Choice",
  };

  @SuppressWarnings("unchecked")
  private static final Class<? extends Controller>[] CLASSES =
      (Class<? extends Controller>[]) new Class<?>[TITLES.length];
  static {
    CLASSES[0] = MessageDialog.class;
    CLASSES[1] = LongMessageDialog.class;
    CLASSES[2] = ListDialog.class;
    CLASSES[3] = LongListDialog.class;
    CLASSES[4] = SingleChoiceDialog.class;
    CLASSES[5] = LongSingleChoiceDialog.class;
    CLASSES[6] = MultiChoiceDialog.class;
    CLASSES[7] = LongMultiChoiceDialog.class;
  }

  private static final String[] SINGLE_TITLE = {
      "Stack Buttons",
      "Custom Header",
      "Custom Content",
      "Custom Footer",
  };

  @SuppressWarnings("unchecked")
  private static final Class<? extends Controller>[] SINGLE_CLASSES =
      (Class<? extends Controller>[]) new Class<?>[SINGLE_TITLE.length];
  static {
    SINGLE_CLASSES[0] = StackButtonDialog.class;
    SINGLE_CLASSES[1] = CustomHeaderDialog.class;
    SINGLE_CLASSES[2] = CustomContentDialog.class;
    SINGLE_CLASSES[3] = CustomFooterDialog.class;
  }

  private String[] getItems() {
    String[] titles = new String[TITLES.length * 4 + SINGLE_TITLE.length];
    int index = 0;
    for (String title : TITLES) {
      titles[index++] = title;
      titles[index++] = title + " without Header";
      titles[index++] = title + " without Footer";
      titles[index++] = title + " without Header and Footer";
    }
    for (String title : SINGLE_TITLE) {
      titles[index++] = title;
    }
    return titles;
  }

  private Controller getController(int index) {
    if (index < TITLES.length * 4) {
      int i = index / 4;
      int j = index % 4;
      Class<? extends Controller> clazz = CLASSES[i];
      try {
        Constructor<? extends Controller> constructor = clazz.getConstructor(boolean.class, boolean.class);
        switch (j) {
          case 0:
            return constructor.newInstance(true, true);
          case 1:
            return constructor.newInstance(false, true);
          case 2:
            return constructor.newInstance(true, false);
          case 3:
            return constructor.newInstance(false, false);
        }
        throw new IllegalStateException();
      } catch (Exception e) {
        throw new IllegalStateException(e);
      }
    } else {
      index = index - TITLES.length * 4;
      Class<? extends Controller> clazz = SINGLE_CLASSES[index];
      try {
        Constructor<? extends Controller> constructor = clazz.getConstructor();
        return constructor.newInstance();
      } catch (Exception e) {
        throw new IllegalStateException(e);
      }
    }
  }

  @NonNull
  @Override
  protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    ListView list = (ListView) inflater.inflate(R.layout.controller_home, container, false);
    list.setAdapter(new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1, getItems()));
    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Router router = getRouter();
        if (router == null) {
          return;
        }
        Controller controller = getController(position);
        router.pushController(RouterTransaction.with(controller));
      }
    });
    return list;
  }
}
