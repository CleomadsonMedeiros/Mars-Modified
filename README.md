Apresentação para a disciplina de Organização e Arquitetura de Computadores:
[Video](https://drive.google.com/file/d/1q5gduE2DVHz8avXWNhcraBIpIScmfFUr/view?usp=sharing)

# MARS Modified

Este projeto é uma versão aprimorada do MARS (MIPS Assembler and Runtime Simulator). Nesta modificação, uma nova syscall foi adicionada, permitindo que os usuários operem um editor de texto simples. Com ele, é possível escrever, salvar e editar arquivos de texto diretamente no ambiente MARS.

## Funcionalidades

- **Entrada de Texto**: Os usuários podem digitar texto por meio de uma interface amigável.
- **Salvar Texto em Arquivo**: Salve o texto digitado em um arquivo especificado.
- **Editar Arquivos Existentes**: Carregue e modifique arquivos de texto previamente salvos.

## Uso

Para utilizar a funcionalidade do editor de texto:

1. **Execute o MARS**: Abra o simulador MARS modificado.
2. **Acesse o Editor de Texto**: Utilize a nova syscall implementada para edição de texto.
3. **Siga as Instruções**: Digite seu texto e escolha salvar ou editar arquivos conforme necessário.

### Exemplo de Uso da Syscall

Um exemplo de como usar a syscall está fornecido no arquivo `exemplo.asm` incluído no repositório. Esse arquivo demonstra a funcionalidade básica da syscall do editor de texto.

Para ver o exemplo:

1. Abra `exemplo.asm` no simulador MARS.
2. Revise os comentários e o código para entender como implementar a syscall.

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
