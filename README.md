# android-dialog

[![Release](https://jitpack.io/v/seven332/android-dialog.svg)](https://jitpack.io/#seven332/android-dialog)

Android-Dialog provides `DialogView` which could be used to build something like dialog.

## Usage

```
repositories {
    maven { url 'https://jitpack.io' }
}

compile 'com.github.seven332.android-dialog:base:{version}'
```

### DialogViewBuilder

That's the builder to build a `DialogView`. It's based on `v7 appcompat library`.

```java
DialogView dialogView = new DialogViewBuilder()
    .title("This is TITLE")
    .message("This is MESSAGE")
    .positiveButton("positive", null)
    .build(inflater, container);
```

DialogView consists of three parts, header, content and footer. `DialogViewBuilder.title()` is header. `DialogViewBuilder.message()` is content. `DialogViewBuilder.positiveButton()` is footer. Custom header/content/footer is supported. Custom content could implement `DialogContent` which is notified about header and footer states.

### DialogView

`DialogView.setDialog()` should be called after being created or some callback may not work fine.

DialogView can get some preset widgets, but they are null if they were not set in `DialogViewBuilder`.

## Add-ons

### conductor

```
repositories {
    maven { url 'https://jitpack.io' }
}

compile 'com.github.seven332.android-dialog:conductor:{version}'
```

`AnDialogController` is a [`DialogController`](https://github.com/seven332/conductor-dialog) which uses `DialogView` as content and uses `v7 appcompat library`'s `R.attr.alertDialogTheme` as default theme.

```java
public class CustomDialog extends AnDialogController {
  @Override
  protected DialogView onCreateDialogView(LayoutInflater inflater, ViewGroup container) {
    return new DialogViewBuilder()
        .title("This is TITLE")
        .message("This is MESSAGE")
        .positiveButton("positive", null)
        .build(inflater, container);
  }
}
```

## License

```
Copyright 2017 Hippo Seven

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
