# arithmetic-java
You can build and run this project on your machine via two approaches : Ant
Python-Script<br>
Ant Approach<br>
• Run Ant jar on your command line.<br>
• To divide 3227.0 555.0, run:<br>
java -cp arbitraryarithmetic/aarithmetic.jar MyInfArith float div 3227 555<br>
• Replace float div 3227 555 with your desired operation and operands.<br>
 Python Script approach<br>
• To div 3227 555, run:<br>
python3 myScript.py float div 3227 555<br>
• Replace float div 3227 555 with your desired operation and operands.<br>
Code Organization<br>
• arbitraryarithmetic/AInteger.java: Integer operations.<br>
• arbitraryarithmetic/AFloat.java: Floating-point operations.<br>
• MyInfArith.java: Main class with main() method.<br>
• build.xml: Ant build file.<br>
• myScript.py: Python script for building/running.<br>
AInteger Class<br>
• The methods addString, subString, mulString, and divString per-
form basic addition, subtraction, multiplication, and division on numeric
strings. These methods focus on the core arithmetic logic and do not
handle all edge cases, such as differing signs or invalid inputs.<br>
• The methods add, sub, mul, and div serve as wrappers that handle input
validation and all cases involving different signs. They ensure that the
final result of the operation is correct, even for negative numbers or invalid
input formats.<br>
