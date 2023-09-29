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
  static const List<Widget> _widgetOptions = <Widget>[
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
      'Index 3: Setting',
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
            icon: Icon(
              Icons.diamond_outlined,
            ),
            activeIcon: Icon(
              Icons.diamond,
            ),
            label: '库存',
            // backgroundColor: Colors.blue,
          ),
          BottomNavigationBarItem(
            icon: Icon(
              Icons.local_grocery_store_outlined,
            ),
            activeIcon: Icon(
              Icons.local_grocery_store,
            ),
            label: '补货',
            // backgroundColor: Colors.green,
          ),
          BottomNavigationBarItem(
            icon: Icon(
              Icons.loyalty_outlined,
            ),
            activeIcon: Icon(
              Icons.loyalty,
            ),
            label: '标签',
            // backgroundColor: Colors.pinkAccent,
          ),
          BottomNavigationBarItem(
            icon: Icon(
              Icons.settings_outlined,
            ),
            activeIcon: Icon(
              Icons.settings
            ),
            label: '设置',
            // backgroundColor: Colors.teal,
          ),
        ],
        currentIndex: _selectedIndex,
        selectedItemColor: Colors.blue,
        unselectedItemColor: Colors.grey,
        onTap: _onItemTapped,
        selectedLabelStyle: const TextStyle(fontSize: 14),
        unselectedLabelStyle: const TextStyle(fontSize: 0),
      ),
    );
  }
}
