import 'package:camerax/camerax.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class CameraPreview extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    WidgetsBinding.instance!.addPostFrameCallback((_) => Camerax.openCamera);
    // This is used in the platform side to register the view.
    final String viewType = 'camerasauce';
    // Pass parameters to the platform side.
    final Map<String, dynamic> creationParams = <String, dynamic>{};

    return AndroidView(
      viewType: viewType,
      layoutDirection: TextDirection.ltr,
      creationParams: creationParams,
      creationParamsCodec: const StandardMessageCodec(),
    );
  }
}
