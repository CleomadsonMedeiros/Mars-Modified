.data
mensagem: .asciiz "Editor de Texto - MARS"
msg_erro: .asciiz "\nErro ao abrir o arquivo"
buffer_entrada: .space 50 #Observação: É espaço - 1 (por conta do caractere nulo no final da string)
nome_arquivo: .space 256 #Limite do path file

.text
.globl main

main:
li $v0, 60

la $a0, mensagem

la $a1, buffer_entrada

li $a2, 50 #Tamanho buffer

la $a3, nome_arquivo

syscall 

beq $a1, 0, entrada_valida
beq $a1, -1, fim_programa
beq $a1, -2, main

entrada_valida:

#Abre o arquivo
li $v0, 13
la $a0, nome_arquivo
li $a1, 1
syscall
#Guarda o descritor do arquivo
move $t3, $v0

#Erro ao abrir o arquivo
blt $v0, 0, erro

#Syscall para escrita em um arquivo
li $v0, 15
move $a0, $t3
la $a1, buffer_entrada
move $a2, $t0 #t0 é atualizado no código do MARS.
syscall

#Fechar o arquivo
li $v0, 16
move $a0, $t3
syscall
j fim_programa

erro:
# Caso de erro ao abrir o arquivo
li $v0, 4
la $a0, msg_erro
syscall

fim_programa:
li $v0, 10
syscall
