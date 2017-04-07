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
 * Created by Hippo on 4/5/2017.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.view.View;

class Indicator {

  private View view;
  private int indicatorHeight;
  private final Paint paint = new Paint();
  private final Rect rect = new Rect();
  private boolean topIndicatorEnabled = true;
  private boolean bottomIndicatorEnabled = true;

  public Indicator(Context context, View view) {
    this.view = view;
    indicatorHeight = dipToPixelSize(context, 1.0f);
    paint.setColor(ResourcesUtils.getAttrColor(context, R.attr.colorControlHighlight));
    paint.setStyle(Paint.Style.FILL);
  }

  private static int dipToPixelSize(Context context, float dip) {
    final float value = dip * context.getResources().getDisplayMetrics().density;
    final int res = (int) (value +0.5f);
    if (res != 0) return res;
    if (value == 0) return 0;
    if (value > 0) return 1;
    return -1;
  }

  private void fillTopIndicatorDrawRect() {
    rect.set(0, 0, view.getWidth(), indicatorHeight);
  }

  private void fillBottomIndicatorDrawRect() {
    rect.set(0, view.getHeight() - indicatorHeight, view.getWidth(), view.getHeight());
  }

  public void setTopIndicatorEnabled(boolean enabled) {
    if (topIndicatorEnabled != enabled) {
      topIndicatorEnabled = enabled;
      view.invalidate(0, 0, view.getWidth(), indicatorHeight);
    }
  }

  public void setBottomIndicatorEnabled(boolean enabled) {
    if (bottomIndicatorEnabled != enabled) {
      bottomIndicatorEnabled = enabled;
      view.invalidate(0, view.getHeight() - indicatorHeight, view.getWidth(), view.getHeight());
    }
  }

  private boolean needShowTopIndicator() {
    return topIndicatorEnabled && ViewCompat.canScrollVertically(view, -1);
  }

  private boolean needShowBottomIndicator() {
    return bottomIndicatorEnabled && ViewCompat.canScrollVertically(view, 1);
  }

  public void drawIndicators(@NonNull Canvas canvas) {
    boolean drawTop = needShowTopIndicator();
    boolean drawBottom = needShowBottomIndicator();
    if (drawTop || drawBottom) {
      final int restoreCount = canvas.save();
      canvas.translate(view.getScrollX(), view.getScrollY());

      // Draw top indicator
      if (drawTop) {
        fillTopIndicatorDrawRect();
        canvas.drawRect(rect, paint);
      }
      // Draw bottom indicator
      if (drawBottom) {
        fillBottomIndicatorDrawRect();
        canvas.drawRect(rect, paint);
      }

      canvas.restoreToCount(restoreCount);
    }
  }
}
