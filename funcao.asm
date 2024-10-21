.data
mensagem: .asciiz "Editor de Texto - MARS"
msg_erro: .asciiz "\nErro ao abrir o arquivo"
buffer_entrada: .space 50
buffer_arquivo: .space 256 #Limite do path file

.text
.globl main

main:
li $v0, 60

la $a0, mensagem

la $a1, buffer_entrada

li $a2, 50

la $a3, buffer_arquivo

syscall 

beq $a1, 0, entrada_valida
beq $a1, -1, fim_programa
beq $a1, -2, main

entrada_valida:

li $t0, 0          # Inicializa o contador em $t0

count_loop:
    lb $t1, 0($a0)     # Carrega um byte do buffer em $t1
    beqz $t1, end_count  # Se o byte for nulo, sai do loop
    addi $t0, $t0, 1   # Incrementa o contador
    addi $a0, $a0, 1   # Move para o próximo byte
    j count_loop       # Volta ao início do loop
    
end_count:

#Abre o arquivo
li $v0, 13
la $a0, buffer_arquivo
li $a1, 1
syscall

#Erro ao abrir o arquivo
blt $v0, 0, erro

#Guarda o descritor do arquivo em $t3
move $t3, $v0

#Syscall para escrita em um arquivo
li $v0, 15
move $a0, $t3
la $a1, buffer_entrada
move $a2, $t0
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
