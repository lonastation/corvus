import 'package:flutter/material.dart';

class BottomNavigationBox extends StatefulWidget {
  const BottomNavigationBox({super.key});

  @override
  State<StatefulWidget> createState() {
    return _BottomNavigationBarBoxState();
  }
}

class _BottomNavigationBarBoxState extends State<BottomNavigationBox> {
  int _selectedIndex = 0;
  static const TextStyle optionStyle =
      TextStyle(fontSize: 30, fontWeight: FontWeight.normal);
  static const List<Widget> _widgetOptions = <Widget> [
    Text(
      'Index 0: everything',
      style: optionStyle,
    ),
    Text(
      'Index 1: expire',
      style: optionStyle,
    ),
    Text(
      'Index 2: Tag/Category',
      style: optionStyle,
    ),
    Text(
      'Index 3: Log',
      style: optionStyle,
    ),
    Text(
      'Index 4: Setting',
      style: optionStyle,
    ),
  ];
  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Everything is here'),
      ),
      body: Center(
        child: _widgetOptions.elementAt(_selectedIndex),
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.diamond),
            label: 'Everything',
            backgroundColor: Colors.blue,
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.local_grocery_store),
            label: 'Expire',
            backgroundColor: Colors.green,
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.loyalty),
            label: 'Category',
            backgroundColor: Colors.pinkAccent,
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.history),
            label: 'Log',
            backgroundColor: Colors.cyan,
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.settings),
            label: 'Setting',
            backgroundColor: Colors.teal,
          ),
        ],
        currentIndex: _selectedIndex,
        selectedItemColor: Colors.white,
        onTap: _onItemTapped,
      ),
    );
  }
}
