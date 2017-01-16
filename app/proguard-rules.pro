# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Dudaizhong\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
################gson##################
#-libraryjars libs/gson-2.3.1.jar
#-keep class com.google.gson.** {*;}
#-keep class com.google.**{*;}
#-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }
#-keep class com.google.gson.examples.android.model.** { *; }
#-keep class com.google.** {
#    <fields>;
#    <methods>;
#}

-ignorewarnings                     # 忽略警告，避免打包时某些警告出现
-optimizationpasses 5               # 指定代码的压缩级别
-dontusemixedcaseclassnames         # 是否使用大小写混合
-dontskipnonpubliclibraryclasses    # 是否混淆第三方jar
-dontpreverify                      # 混淆时是否做预校验
-verbose                            # 混淆时是否记录日志

-keepattributes EnclosingMethod

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

-keep public class * extends cn.owen.consultant.bean.BaseBean
-keep public class cn.owen.consultant.bean.** {*;}
-keep public class cn.owen.consultant.R$*{
    public static final int *;
}

-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keep class * implements java.io.Serializable{
    public protected private *;
}

-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Dialog
-keep public class * extends android.view

-keepattributes *Annotation*
-dontwarn com.google.gson.**
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

# 自己应用下的domain包下所有类都要过滤
-keep class com.dudaizhong.news.modules.gank.domain.**{*;}
-keep class com.dudaizhong.news.modules.login.domain.**{*;}
-keep class com.dudaizhong.news.modules.main.domain.**{*;}
-keep class com.dudaizhong.news.modules.zhihu.domain.**{*;}

-keep class com.google.gson.** { *;}
#这句非常重要，主要是滤掉 com.bgb.scan.model包下的所有.class文件不进行混淆编译
-keep class com.bgb.scan.model.** {*;}

#############leancloud###################
-keepattributes Signature
-dontwarn com.jcraft.jzlib.**
-keep class com.jcraft.jzlib.**  { *;}

-dontwarn sun.misc.**
-keep class sun.misc.** { *;}

-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *;}

-dontwarn sun.security.**
-keep class sun.security.** { *; }

-dontwarn com.google.**
-keep class com.google.** { *;}

-dontwarn com.avos.**
-keep class com.avos.** { *;}

-keep public class android.net.http.SslError
-keep public class android.webkit.WebViewClient

-dontwarn android.webkit.WebView
-dontwarn android.net.http.SslError
-dontwarn android.webkit.WebViewClient

-dontwarn android.support.**

-dontwarn org.apache.**
-keep class org.apache.** { *;}

-dontwarn org.jivesoftware.smack.**
-keep class org.jivesoftware.smack.** { *;}

-dontwarn com.loopj.**
-keep class com.loopj.** { *;}

-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *;}
-keep interface com.squareup.okhttp.** { *; }

-dontwarn okio.**

-dontwarn org.xbill.**
-keep class org.xbill.** { *;}

-keepattributes *Annotation*

################BugTags##################
-keepattributes LineNumberTable,SourceFile
-keep class com.bugtags.library.** {*;}
-dontwarn org.apache.http.**
-dontwarn android.net.http.AndroidHttpClient
-dontwarn com.bugtags.library.**
-dontwarn com.bugtags.library.vender.**
-keepattributes LineNumberTable,SourceFile

################APPSEE##################
-keep class com.appsee.** { *; }
-dontwarn com.appsee.**

-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# ========== support-v4 ==========
#-libraryjars libs/android-support-v4.jar
-dontwarn android.support.v4.**
-keep class android.support.v4.** {*;}
-keep interface android.support.v4.app.** {*;}
# ================================


# 第三方 jar 包
#-dontwarn retrofit.**
#-keep class retrofit.** { *; }
#-keepattributes Signature
#-keepattributes Exceptions
#
#-dontwarn com.squareup.okhttp.**
#-keep class com.squareup.okhttp.** { *;}
-dontwarn okio.**

-dontwarn retrofit2.**
-keep class retrofit2.**{*;}
-keepattributes Signature
-keepattributes Exceptions

-dontwarn okhttp3.**
-keep class okhhtp3.**{*;}

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

-keep class com.github.mikephil.charting.** { *; }

# ================================

-keep class com.dudaizhong.news.common.widget.LoadMoreRecyclerView.**{*;}
-keep class com.dudaizhong.news.common.widget.AutoPlayViewPager.**{*;}
-keep class com.dudaizhong.news.common.widget.CharAvatarView.**{*;}
-dontwarn com.readystatesoftware.systembartint.SystemBarTintManager.**
-keep class com.readystatesoftware.systembartint.SystemBarTintManager.**{*;}

# webview + js
-keepattributes *JavascriptInterface*
# keep 使用 webview 的类
-keepclassmembers class  com.veidy.activity.WebViewActivity {
   public *;
}
# keep 使用 webview 的类的所有的内部类
-keepclassmembers  class  com.veidy.activity.WebViewActivity$*{
    *;
}
# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-dontwarn com.bumptech.glide.**