import 'package:flutter/material.dart';

class UpdateThingTab extends StatelessWidget {
  const UpdateThingTab({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: ListView(
          children: const <Widget>[
            Text('update'),
            Text('name'),
            Text('code')
          ],
        )
    );
  }
}