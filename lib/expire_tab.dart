import 'package:flutter/material.dart';

class ExpireTab extends StatelessWidget {
  const ExpireTab({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: ListView(
          children: const <Widget>[
            Text('expire things'),
            Text('update days'),
            Text('update date')
          ],
        )
    );
  }
}