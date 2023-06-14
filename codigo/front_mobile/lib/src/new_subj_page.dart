import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter/src/widgets/placeholder.dart';
import 'package:webview_flutter/webview_flutter.dart';

import 'package:webview_flutter_android/webview_flutter_android.dart';

class NewSubjPage extends StatefulWidget {
  const NewSubjPage({super.key});

  @override
  State<NewSubjPage> createState() => _NewSubjPageState();
}

class _NewSubjPageState extends State<NewSubjPage> {
  late final WebViewController controller;
   String url = 'https://docs.google.com/forms/d/e/1FAIpQLSeJitM_c1Ks3DPLX4F16gmEk3cJxfzCDTNoYfP_BURzvaJJBw/viewform?usp=sf_link';

  @override
  void initState() {
    controller = WebViewController()
      ..setJavaScriptMode(JavaScriptMode.unrestricted)
      ..loadRequest(Uri.parse(url));
    super.initState();
  }



  

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Requisitar Disciplina'), automaticallyImplyLeading: false),
      body: WebViewWidget(controller: controller)
    );
  }
}