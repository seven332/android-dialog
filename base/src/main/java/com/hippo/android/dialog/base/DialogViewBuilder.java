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
 * Created by Hippo on 4/3/2017.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.method.MovementMethod;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A builder to build {@link DialogView}.
 * <p>
 * A {@link DialogView} has three parts: header, content and footer.
 * Each part could be preset view or custom view.
 */
public class DialogViewBuilder {

  private int titleResId;
  private CharSequence title;

  private int messageResId;
  private CharSequence message;
  private MovementMethod messageMovementMethod;

  private int itemsResId;
  private CharSequence[] items;
  private int checkedItem;
  private SparseBooleanArray checkStates;
  private boolean singleChoice;
  private boolean multiChoice;
  private ListAdapter itemsAdapter;
  private DialogInterface.OnClickListener itemsListener;

  private boolean stackButtons;

  private int positiveButtonResId;
  private CharSequence positiveButtonText;
  private DialogInterface.OnClickListener positiveButtonListener;

  private int negativeButtonResId;
  private CharSequence negativeButtonText;
  private DialogInterface.OnClickListener negativeButtonListener;

  private int neutralButtonResId;
  private CharSequence neutralButtonText;
  private DialogInterface.OnClickListener neutralButtonListener;

  private View customHeader;
  private View customContent;
  private boolean customContentInScrollView;
  private View customFooter;

  /**
   * Sets the title to display as header.
   *
   * @see #title(CharSequence)
   */
  public DialogViewBuilder title(@StringRes int resId) {
    titleResId = resId;
    return this;
  }

  /**
   * Sets the title to display as header.
   *
   * @see #title(int)
   */
  public DialogViewBuilder title(CharSequence text) {
    title = text;
    return this;
  }

  /**
   * Sets the message to display as content.
   *
   * @see #message(CharSequence)
   * @see #messageMovementMethod(MovementMethod)
   */
  public DialogViewBuilder message(int resId) {
    messageResId = resId;
    return this;
  }

  /**
   * Sets the message to display as content.
   *
   * @see #message(int)
   * @see #messageMovementMethod(MovementMethod)
   */
  public DialogViewBuilder message(CharSequence text) {
    message = text;
    return this;
  }

  /**
   * Sets a {@link MovementMethod} for the message view.
   *
   * @see #message(int)
   * @see #message(CharSequence)
   */
  public DialogViewBuilder messageMovementMethod(MovementMethod method) {
    messageMovementMethod = method;
    return this;
  }

  /**
   * Set a list of items to be displayed in the dialog as the content, you will be notified of the
   * selected item via the supplied listener.
   */
  public DialogViewBuilder items(@ArrayRes int resId, DialogInterface.OnClickListener listener) {
    itemsResId = resId;
    itemsListener = listener;
    return this;
  }

  /**
   * Set a list of items to be displayed in the dialog as the content, you will be notified of the
   * selected item via the supplied listener.
   */
  public DialogViewBuilder items(CharSequence[] items, DialogInterface.OnClickListener listener) {
    this.items = items;
    this.itemsListener = listener;
    return this;
  }

  /**
   * Set a list of items, which are supplied by the given {@link ListAdapter}, to be
   * displayed in the dialog as the content, you will be notified of the
   * selected item via the supplied listener.
   */
  public DialogViewBuilder adapter(ListAdapter adapter, DialogInterface.OnClickListener listener) {
    itemsAdapter = adapter;
    itemsListener = listener;
    return this;
  }

  /**
   * Set a list of items to be displayed in the dialog as the content, you will be notified of
   * the selected item via the supplied listener. This should be an array type i.e.
   * R.array.foo The list will have a check mark displayed to the right of the text for the
   * checked item.
   */
  public DialogViewBuilder singleChoice(
      @ArrayRes int resId, int checkedItem, DialogInterface.OnClickListener listener) {
    this.itemsResId = resId;
    this.checkedItem = checkedItem;
    this.itemsListener = listener;
    this.singleChoice = true;
    return this;
  }

  /**
   * Set a list of items to be displayed in the dialog as the content, you will be notified of
   * the selected item via the supplied listener. The list will have a check mark displayed to
   * the right of the text for the checked item.
   */
  public DialogViewBuilder singleChoice(
      CharSequence[] items, int checkedItem, DialogInterface.OnClickListener listener) {
    this.items = items;
    this.checkedItem = checkedItem;
    this.itemsListener = listener;
    this.singleChoice = true;
    return this;
  }

  /**
   * Set a list of items to be displayed in the dialog as the content,
   * you will be notified of the selected item via the supplied listener.
   * This should be an array type, e.g. R.array.foo. The list will have
   * a check mark displayed to the right of the text for each checked
   * item.
   */
  public DialogViewBuilder multiChoice(
      @ArrayRes int resId, SparseBooleanArray checkStates,
      DialogInterface.OnClickListener listener) {
    this.itemsResId = resId;
    this.checkStates = checkStates;
    this.itemsListener = listener;
    this.multiChoice = true;
    return this;
  }

  /**
   * Set a list of items to be displayed in the dialog as the content,
   * you will be notified of the selected item via the supplied listener.
   * The list will have a check mark displayed to the right of the text
   * for each checked item.
   */
  public DialogViewBuilder multiChoice(
      CharSequence[] items, SparseBooleanArray checkStates,
      DialogInterface.OnClickListener listener) {
    this.items = items;
    this.checkStates = checkStates;
    this.itemsListener = listener;
    this.multiChoice = true;
    return this;
  }

  /**
   * Shows stacked full-width buttons instead of side-by-side buttons.
   */
  public DialogViewBuilder stackButtons(boolean stack) {
    stackButtons = stack;
    return this;
  }

  /**
   * Sets positive button text and listener to be invoked
   * when the positive button is pressed.
   * If the listener is {@code null}, the dialog dismissed instead
   * when the positive button is pressed.
   *
   * @see #positiveButton(CharSequence, DialogInterface.OnClickListener)
   */
  public DialogViewBuilder positiveButton(
      @StringRes int resId, DialogInterface.OnClickListener listener) {
    positiveButtonResId = resId;
    positiveButtonListener = listener;
    return this;
  }

  /**
   * Sets positive button text and listener to be invoked
   * when the positive button is pressed.
   * If the listener is {@code null}, the dialog dismissed instead
   * when the positive button is pressed.
   *
   * @see #positiveButton(int, DialogInterface.OnClickListener)
   */
  public DialogViewBuilder positiveButton(
      CharSequence text, DialogInterface.OnClickListener listener) {
    positiveButtonText = text;
    positiveButtonListener = listener;
    return this;
  }

  /**
   * Sets negative button text and listener to be invoked
   * when the negative button is pressed.
   * If the listener is {@code null}, the dialog dismissed instead
   * when the negative button is pressed.
   *
   * @see #negativeButton(CharSequence, DialogInterface.OnClickListener)
   */
  public DialogViewBuilder negativeButton(
      @StringRes int resId, DialogInterface.OnClickListener listener) {
    negativeButtonResId = resId;
    negativeButtonListener = listener;
    return this;
  }

  /**
   * Sets negative button text and listener to be invoked
   * when the negative button is pressed.
   * If the listener is {@code null}, the dialog dismissed instead
   * when the negative button is pressed.
   *
   * @see #negativeButton(int, DialogInterface.OnClickListener)
   */
  public DialogViewBuilder negativeButton(
      CharSequence text, DialogInterface.OnClickListener listener) {
    negativeButtonText = text;
    negativeButtonListener = listener;
    return this;
  }

  /**
   * Sets neutral button text and listener to be invoked
   * when the neutral button is pressed.
   * If the listener is {@code null}, the dialog dismissed instead
   * when the neutral button is pressed.
   *
   * @see #neutralButton(CharSequence, DialogInterface.OnClickListener)
   */
  public DialogViewBuilder neutralButton(
      @StringRes int resId, DialogInterface.OnClickListener listener) {
    neutralButtonResId = resId;
    neutralButtonListener = listener;
    return this;
  }

  /**
   * Sets neutral button text and listener to be invoked
   * when the neutral button is pressed.
   * If the listener is {@code null}, the dialog dismissed instead
   * when the neutral button is pressed.
   *
   * @see #neutralButton(int, DialogInterface.OnClickListener)
   */
  public DialogViewBuilder neutralButton(
      CharSequence text, DialogInterface.OnClickListener listener) {
    neutralButtonText = text;
    neutralButtonListener = listener;
    return this;
  }

  /**
   * Sets custom header view.
   */
  public DialogViewBuilder customHeader(View header) {
    customHeader = header;
    return this;
  }

  /**
   * Sets custom content view.
   *
   * @param inScrollView {@code true} to wrap the custom content view
   *                     in a {@link android.widget.ScrollView}.
   */
  public DialogViewBuilder customContent(View content, boolean inScrollView) {
    customContent = content;
    customContentInScrollView = inScrollView;
    return this;
  }

  /**
   * Sets custom footer view.
   */
  public DialogViewBuilder customFooter(View footer) {
    customFooter = footer;
    return this;
  }

  private void resolveResources(Context context) {
    if (titleResId != 0) {
      title = context.getString(titleResId);
    }
    if (messageResId != 0) {
      message = context.getString(messageResId);
    }
    if (itemsResId != 0) {
      items = context.getResources().getTextArray(itemsResId);
    }
    if (items != null) {
      int itemLayoutResId;
      if (singleChoice) {
        itemLayoutResId = R.layout.andialog_item_single;
      } else if (multiChoice) {
        itemLayoutResId = R.layout.andialog_item_multi;
      } else {
        itemLayoutResId = R.layout.andialog_item;
      }
      itemsAdapter = new ArrayAdapter<>(context, itemLayoutResId, items);
    }
    if (positiveButtonResId != 0) {
      positiveButtonText = context.getString(positiveButtonResId);
    }
    if (negativeButtonResId != 0) {
      negativeButtonText = context.getString(negativeButtonResId);
    }
    if (neutralButtonResId != 0) {
      neutralButtonText = context.getString(neutralButtonResId);
    }
  }

  private boolean buildHeader(LayoutInflater inflater, DialogView root) {
    if (customHeader != null) {
      // Custom header
      root.addView(customHeader,
          ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      return true;

    } else if (title != null) {
      // Title
      inflater.inflate(R.layout.andialog_title, root);
      TextView titleView = (TextView) root.findViewById(R.id.andialog_title);
      titleView.setText(title);
      return true;
    }

    return false;
  }

  private DialogScrollView inflaterScroll(LayoutInflater inflater, ViewGroup container) {
    inflater.inflate(R.layout.andialog_scroll, container);
    return (DialogScrollView) container.findViewById(R.id.andialog_scroll);
  }

  private DialogContent addCustomContent(DialogView root) {
    root.addView(customContent,
        ViewGroup.LayoutParams.MATCH_PARENT, 0);
    ((DialogView.LayoutParams) customContent.getLayoutParams()).weight = 1;
    // Check custom content
    if (customContent instanceof DialogContent) {
      return (DialogContent) customContent;
    } else {
      return null;
    }
  }

  private DialogContent addCustomContentInScrollView(LayoutInflater inflater, DialogView root) {
    DialogScrollView scrollView = inflaterScroll(inflater, root);
    scrollView.addView(customContent,
        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    return scrollView;
  }

  private DialogContent buildContent(LayoutInflater inflater, DialogView root) {
    if (customContent != null) {
      if (customContentInScrollView) {
        return addCustomContentInScrollView(inflater, root);
      } else {
        return addCustomContent(root);
      }

    } else if (message != null) {
      // Message
      DialogScrollView scrollView = inflaterScroll(inflater, root);
      inflater.inflate(R.layout.andialog_message, scrollView);
      TextView messageView = (TextView) scrollView.findViewById(R.id.andialog_message);
      messageView.setText(message);
      if (messageMovementMethod != null) {
        messageView.setMovementMethod(messageMovementMethod);
      }
      return scrollView;

    } else if (itemsAdapter != null) {
      // Items list
      inflater.inflate(R.layout.andialog_list, root);
      DialogListView list = (DialogListView) root.findViewById(R.id.andialog_list);
      // Adapter
      if (multiChoice && checkStates != null) {
        itemsAdapter = new MultiChoiceAdapter(itemsAdapter, list, checkStates);
      }
      list.setAdapter(itemsAdapter);
      // Listener
      ListView.OnItemClickListener listener = null;
      if (multiChoice && checkStates != null) {
        listener = new MultiChoiceListener(root, list, checkStates, itemsListener);
      } else if (itemsListener != null) {
        listener = new ItemClickListener(root, itemsListener);
      }
      if (listener != null) {
        list.setOnItemClickListener(listener);
      }
      // Choice mode
      if (singleChoice) {
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        if (checkedItem >= 0 && checkedItem < itemsAdapter.getCount()) {
          list.setItemChecked(checkedItem, true);
          list.setSelection(checkedItem);
        }
      } else if (multiChoice) {
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
      }
      return list;
    }

    return null;
  }

  private boolean buildFooter(LayoutInflater inflater, DialogView root) {
    if (customFooter != null) {
      // Custom footer
      root.addView(customFooter,
          ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      return true;

    } else if (positiveButtonText != null || negativeButtonText != null
        || neutralButtonText != null) {
      // Side buttons
      inflater.inflate(
          stackButtons ? R.layout.andialog_stack_buttons : R.layout.andialog_side_buttons,
          root);

      Button positive = (Button) root.findViewById(R.id.andialog_positive_button);
      if (positiveButtonText != null) {
        positive.setText(positiveButtonText);
        positive.setOnClickListener(
            new ButtonClickListener(root, DialogInterface.BUTTON_POSITIVE, positiveButtonListener));
      } else {
        positive.setVisibility(View.GONE);
      }

      Button negative = (Button) root.findViewById(R.id.andialog_negative_button);
      if (negativeButtonText != null) {
        negative.setText(negativeButtonText);
        negative.setOnClickListener(
            new ButtonClickListener(root, DialogInterface.BUTTON_NEGATIVE, negativeButtonListener));
      } else {
        negative.setVisibility(View.GONE);
      }

      Button neutral = (Button) root.findViewById(R.id.andialog_neutral_button);
      if (neutralButtonText != null) {
        neutral.setText(neutralButtonText);
        neutral.setOnClickListener(
            new ButtonClickListener(root, DialogInterface.BUTTON_NEUTRAL, neutralButtonListener));
      } else {
        neutral.setVisibility(View.GONE);
      }

      return true;
    }

    return false;
  }

  /**
   * Builds a {@link DialogView}.
   * <p>
   * Should call {@link DialogView#setDialog(DialogInterface)} later.
   */
  public DialogView build(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
    resolveResources(inflater.getContext());

    DialogView root = (DialogView) inflater.inflate(R.layout.andialog_base, container, false);
    boolean hasHeader = buildHeader(inflater, root);
    DialogContent content = buildContent(inflater, root);
    boolean hasFooter = buildFooter(inflater, root);

    // Configures indicator
    if (content != null) {
      content.setHasHeader(hasHeader);
      content.setHasFooter(hasFooter);
    }

    return root;
  }

  private static final class ButtonClickListener implements View.OnClickListener {

    private DialogView view;
    private int which;
    private DialogInterface.OnClickListener listener;

    public ButtonClickListener(
        DialogView view, int which, DialogInterface.OnClickListener listener) {
      this.view = view;
      this.which = which;
      this.listener = listener;
    }

    @Override
    public void onClick(View v) {
      if (listener != null) {
        listener.onClick(view.getDialog(), which);
      } else {
        // Dismiss the dialog if no listener
        DialogInterface dialog = view.getDialog();
        if (dialog != null) {
          dialog.dismiss();
        }
      }
    }
  }

  private static final class ItemClickListener implements ListView.OnItemClickListener {

    private DialogView dialogView;
    private DialogInterface.OnClickListener listener;

    public ItemClickListener(DialogView dialogView, DialogInterface.OnClickListener listener) {
      this.dialogView = dialogView;
      this.listener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      listener.onClick(dialogView.getDialog(), position);
    }
  }

  private static final class MultiChoiceAdapter extends WrapperListAdapter {

    private ListView listView;
    private SparseBooleanArray checkStates;

    public MultiChoiceAdapter(ListAdapter adapter, ListView listView, SparseBooleanArray checkStates) {
      super(adapter);
      this.listView = listView;
      this.checkStates = checkStates;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View view = super.getView(position, convertView, parent);
      boolean isItemChecked = checkStates.get(position);
      if (isItemChecked) {
        listView.setItemChecked(position, true);
      }
      return view;
    }
  }

  private static final class MultiChoiceListener implements ListView.OnItemClickListener {

    private DialogView dialogView;
    private ListView listView;
    private SparseBooleanArray checkStates;
    private DialogInterface.OnClickListener listener;

    public MultiChoiceListener(
        DialogView dialogView, ListView listView, SparseBooleanArray checkStates,
        DialogInterface.OnClickListener listener) {
      this.dialogView = dialogView;
      this.listView = listView;
      this.checkStates = checkStates;
      this.listener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
      checkStates.put(position, listView.isItemChecked(position));
      if (listener != null) {
        listener.onClick(dialogView.getDialog(), position);
      }
    }
  }
}
