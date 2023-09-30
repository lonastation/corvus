import 'package:flutter/material.dart';

class TagTab extends StatelessWidget {
  const TagTab({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: ListView(
          children: const <Widget>[
            Text('tag type'),
            Text('tag list'),
            Text('tag add')
          ],
        )
    );
  }
}