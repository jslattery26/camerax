package com.example.camerax

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result



/** CameraxPlugin */
class CameraxPlugin: FlutterPlugin, MethodCallHandler{
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private lateinit var context : Context
  private lateinit var binder : FlutterPlugin.FlutterPluginBinding

  override fun onAttachedToEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    binder = binding;
    channel = MethodChannel(binding.binaryMessenger, "camerax")
    channel.setMethodCallHandler(this)
    context = binding.applicationContext;

    binding.platformViewRegistry.registerViewFactory("camerasauce", NativeViewFactory())
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else if(call.method == "openCamera"){

      val intent = Intent(this.context, CameraActivity::class.java)
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      binder.applicationContext.startActivity(intent)
    }
    else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

}
