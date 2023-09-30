import 'package:flutter/material.dart';

class AddThingTab extends StatelessWidget {
  const AddThingTab({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('Add Thing'),
        ),
        body: Center(
          child: ListView(
            children: <Widget>[
              const Text('name'),
              const Text('code'),
              ElevatedButton(
                child: const Text('Back'),
                onPressed: () {
                  Navigator.pop(context);
                },
              ),
            ],
          ),
        )
    );
  }
}