import 'package:flutter/material.dart';

import 'add_thing_tab.dart';

class StockTab extends StatefulWidget {
  const StockTab({super.key});

  @override
  State<StatefulWidget> createState() {
    return _StockTabState();
  }
}

class _StockTabState extends State<StockTab> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        toolbarHeight: 80,
        title: const TextField(
          decoration: InputDecoration(
              enabledBorder: OutlineInputBorder(
                borderSide: BorderSide(color: Colors.blueAccent, width: 2.0),
                borderRadius: BorderRadius.all(Radius.circular(14.0)),
              ),
              focusedBorder: OutlineInputBorder(
                  borderSide: BorderSide(color: Colors.blueAccent, width: 2.0),
                  borderRadius: BorderRadius.all(Radius.circular(14.0))),
              hintText: 'Enter a search term',
              hintStyle: TextStyle(
                color: Colors.lightBlue,
                fontWeight: FontWeight.normal,
                fontSize: 26,
              ),
              contentPadding:
                  EdgeInsets.symmetric(vertical: 10, horizontal: 20)),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
              context,
              MaterialPageRoute(
                builder: (context) => const AddThingTab(),
              ));
        },
        foregroundColor: Colors.blue,
        child: const Icon(Icons.add),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.endFloat,
      body: ListView(
        children: <Widget>[
          ListTile(
            title: const Text(
              '小豆泥玩偶',
              style: TextStyle(fontWeight: FontWeight.bold),
            ),
            trailing: IconButton(
              color: Colors.blueAccent,
              onPressed: () {},
              icon: const Icon(Icons.create_outlined),
            ),
            subtitle: const Text('床上'),
          ),
          ListTile(
            title: const Text(
              '北海巨妖耳机',
              style: TextStyle(fontWeight: FontWeight.bold),
            ),
            trailing: IconButton(
              color: Colors.blueAccent,
              onPressed: () {},
              icon: const Icon(Icons.create_outlined),
            ),
            subtitle: const Text('桌上'),
          ),
          ListTile(
            title: const Text(
              '棉签',
              style: TextStyle(fontWeight: FontWeight.bold),
            ),
            trailing: IconButton(
              color: Colors.blueAccent,
              onPressed: () {},
              icon: const Icon(Icons.create_outlined),
            ),
            subtitle: const Text('桌上'),
          ),
        ],
      ),
    );
  }
}
