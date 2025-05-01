import subprocess
import sys
import os


JAVA_FILES = [
    "arbitraryarithmetic/AInteger.java",
    "arbitraryarithmetic/AFloat.java",
    "MyInfArith.java"
]

def compile_java():
    needs_compile = False
    for java_file in JAVA_FILES:
        class_file = java_file.replace(".java", ".class")
        if not os.path.exists(class_file) or \
           os.path.getmtime(java_file) > os.path.getmtime(class_file):
            needs_compile = True
            break
    if needs_compile:
        print("Compiling Java files...")
        result = subprocess.run(["javac"] + JAVA_FILES, capture_output=True, text=True)
        if result.returncode != 0:
            print("Compilation failed:\n", result.stderr)
            sys.exit(1)
    else:
        print("Java files already compiled.")

def run_java(args):
    cmd = ["java", "-cp", ".", "MyInfArith"] + args
    result = subprocess.run(cmd, capture_output=True, text=True)
    print(result.stdout.strip())
    if result.returncode != 0:
        print("Error running Java program:", result.stderr.strip())

if __name__ == "__main__":
    if len(sys.argv) != 5:
        print("Usage: python run_myinfarith.py <int/float> <add/sub/mul/div> <operand1> <operand2>")
        sys.exit(1)
    compile_java()
    run_java(sys.argv[1:])
