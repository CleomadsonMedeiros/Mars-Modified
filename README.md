# MARS Modified

This project is an enhanced version of the MARS (MIPS Assembler and Runtime Simulator). In this modification, a new syscall has been added that allows users to operate a simple text editor. Users can write, save, and edit text files directly within the MARS environment.

## Features

- **Text Input**: Users can enter text through a user-friendly interface.
- **Save Text to File**: Save the written text to a specified file.
- **Edit Existing Files**: Load and modify previously saved text files.

## Usage

To use the text editor functionality:

1. **Run MARS**: Open the modified MARS simulator.
2. **Access the Text Editor**: Use the new syscall implemented for text editing.
3. **Follow the Prompts**: Input your text, and choose to save or edit files as needed.

### Example Syscall Usage

An example of how to use the syscall is provided in the `exemplo.asm` file included in the repository. This file demonstrates the basic functionality of the text editor syscall.

To see the example:

1. Open `exemplo.asm` in the MARS simulator.
2. Review the comments and code to understand how to implement the syscall.
