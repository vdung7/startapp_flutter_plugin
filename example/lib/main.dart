import 'package:flutter/material.dart';
import 'package:startapp/banner.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "StartApp Example",
        home: Scaffold(
            appBar: AppBar(title: const Text('StartApp Example')),
            body: Center(
              child: Column(
                children: <Widget>[
                  Text('Banner sample'),
                  AdBanner(),
                ],
              )
            )
        )
    );
  }
}
