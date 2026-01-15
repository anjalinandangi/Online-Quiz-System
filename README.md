📝 Online Quiz System (Java Swing)

An interactive Online Quiz System developed using Java Swing that allows users to attempt multiple-choice questions (MCQs), navigate between them, and view their final score in a visually appealing desktop application.

This project demonstrates core Java concepts, Swing UI design, and object-oriented programming principles.

✨ Features

🎯 Multiple-choice questions (MCQs)

🔘 Radio button–based answer selection

▶️ Next button navigation

📊 Automatic score calculation

🔁 Quiz reset after completion

🖼️ Custom background image support

🪟 Clean and user-friendly Swing GUI

📦 OOP-based design using a Question class

🛠️ Technologies Used

Java (JDK 8+)

Java Swing

AWT

GridBagLayout

ImageIO

Object-Oriented Programming (OOP)

📂 Project Structure
src/
 ├── DemoQuiz.java
 ├── Question.java
 └── images/
     └── backgroung.jpg


⚠️ Important Note
The background image filename is intentionally spelled as backgroung.jpg
(same spelling is used in the source code).

🚀 How to Run the Project
1️⃣ Prerequisites

Java JDK 8 or above

Any IDE (IntelliJ IDEA / Eclipse / NetBeans) or Command Line

2️⃣ Compile the Program
javac DemoQuiz.java

3️⃣ Run the Program
java DemoQuiz

🖼️ Background Image Setup

Place the background image inside the project as shown below:

src/images/backgroung.jpg


The image is loaded using:

DemoQuiz.class.getResource("/images/backgroung.jpg");


This ensures the image works:

Inside IDE

After exporting as a JAR

On different systems

🧠 Quiz Flow

Application launches with background UI

A question is displayed with four options

User selects an option

Clicks Next to proceed

After the last question, the final score is shown

Quiz resets automatically
