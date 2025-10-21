[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/J_c8sizy)

# MassiveMotion
**Author:** Valarie Trinh
**Course:** CS 245 - Fall 2025
**Project:** Project 02 - Massive Motion

A Java simulation of celestial bodies moving across a 2D canvas. Bodies are generated randomly from the edges and move according to their velocities until they leave the screen.

## Demo
[Watch the simulation in action]()

## Features
- Configurable simulation parameters via properties file
- Four different List implementations (ArrayList, LinkedList, DoublyLinkedList, DummyHeadLinkedList)
- Dynamic body generation with random velocities
- Visual rendering using Java Swing

## How to Compile
```bash
javac *.java
```

## How to Run
```bash
java MassiveMotion 
```

Example:
```bash
java MassiveMotion MassiveMotion.txt
```

## Configuration
Edit `MassiveMotion.txt` to customize:
- Window size
- Body generation probability
- Body size and velocity
- Star properties
- List implementation type (arraylist, single,double, dummyhead)

## Acknowledgements
- Starter code provided by EJ Jung
- List interface and simulation framework based on CS245 Project 02 specifications