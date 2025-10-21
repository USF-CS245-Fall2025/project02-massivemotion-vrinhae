[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/J_c8sizy)

# MassiveMotion
**Author:** Valarie Trinh
**Course:** CS 245 - Fall 2025
**Project:** Project 02 - Massive Motion

A Java simulation of celestial bodies moving across a 2D canvas. Bodies are generated randomly from the edges and move according to their velocities until they leave the screen.

## Demo
[Watch the simulation in action](https://github.com/user-attachments/assets/1eef884d-1c3d-47c7-b3bf-df5897129ace)

## Project Structure
```
MassiveMotion/
│
├── MassiveMotion.java          # Main simulation class with GUI and animation logic
├── CelestialBody.java          # Represents individual celestial bodies (star/comets)
│
├── List.java                   # List interface defining core operations
├── ArrayList.java              # Array-based list implementation with dynamic resizing
├── LinkedList.java             # Singly-linked list implementation
├── DoublyLinkedList.java       # Doubly-linked list with prev/next pointers
├── DummyHeadLinkedList.java    # Linked list with sentinel dummy head node
│
├── MassiveMotion.txt           # Configuration file for simulation parameters
└── README.md                   # Project documentation
```

## Architecture

### Data Structures
- **List Interface**: Defines the API for all list implementations (add, get, remove, size)
- **Four List Implementations**: Demonstrate different approaches to storing/managing collections
  - ArrayList: O(1) access, O(n) insertion/removal
  - LinkedList: O(n) access, O(1) insertion at head
  - DoublyLinkedList: Bidirectional traversal, O(n) access
  - DummyHeadLinkedList: Simplified edge case handling

### Simulation Components
- **MassiveMotion**: Main controller handling rendering, timing, and body management
- **CelestialBody**: Encapsulates position, velocity, size, and color of each body
- **Configuration System**: Properties file for runtime customization

## How It Works

### Initialization
1. **Configuration Loading**: Reads properties from the specified .txt file
2. **List Selection**: Instantiates the appropriate List implementation based on config
3. **Star Creation**: Creates the initial red star at the specified position
4. **Timer Setup**: Initializes Swing Timer with the configured delay

### Simulation Loop (actionPerformed)
Each timer tick executes the following:

1. **Body Generation**
   - With probability `genX`: Generate a body from top or bottom edge
   - With probability `genY`: Generate a body from left or right edge
   - Each new body gets random velocity in range `[-bodyVelocity, +bodyVelocity]` (excluding 0)
   - Bodies are colored black (comets) vs. red (star)

2. **Movement**
   - Each body's position is updated based on its velocity
   - Uses the `move()` method: `x += velocityX`, `y += velocityY`

3. **Cleanup**
   - Bodies that move outside the window boundaries are removed from the list
   - Prevents memory buildup from off-screen objects

4. **Rendering**
   - `repaint()` triggers `paintComponent()`
   - All bodies in the list are drawn as filled ovals

### Polymorphism in Action
The simulation uses the `List<CelestialBody>` interface type, allowing seamless switching between different list implementations without changing the simulation logic. This demonstrates "coding to an API."

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
