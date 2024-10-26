package snippet;

public class Snippet {
	  # Carrega o endereço do buffer de entrada em $a0
	    la $a0, buffer_entrada
	
	    # Carrega o endereço do buffer do arquivo em $a1
	    la $a1, buffer_arquivo
	
	    # Define o tamanho máximo do buffer
	    li $a2, 256
	
	    # Chama a syscall
	    li $v0, 60
	    syscall
}

