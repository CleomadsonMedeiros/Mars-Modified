package mars.mips.instructions.syscalls;

import mars.mips.hardware.*;
import mars.*;
import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.awt.*;

//Syscall baseada no arquivo SyscallInputDialogString

public class SyscallInputText extends AbstractSyscall{
    public SyscallInputText() {
        super(60, "InputText");
    }
    
    void carregarArquivo(JTextArea areaDeTexto) {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(null);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();
            try {
                String conteudo = new String(Files.readAllBytes(arquivoSelecionado.toPath()));
                areaDeTexto.setText(conteudo);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar o arquivo: " + e.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Syscall
    public void simulate(ProgramStatement statement) throws ProcessingException 
    {
        String mensagem = new String(); 
        int enderecoByte = RegisterFile.getValue(4);
        char caractere[] = { ' '};
        try 
        {
            caractere[0] = (char) Globals.memory.getByte(enderecoByte);
            while (caractere[0] != 0) 
            {
                mensagem = mensagem.concat(new String(caractere)); 
                enderecoByte++;
                caractere[0] = (char) Globals.memory.getByte(enderecoByte);
            }
        } 
        catch (AddressErrorException e) 
        {
            throw new ProcessingException(statement, e);
        }
        
        Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        int telaLargura = tamanhoTela.width;
        int telaAltura = tamanhoTela.height;
        
        int linhas = telaAltura / 50;
        int colunas = telaLargura / 30;

        JTextArea areaDeTexto = new JTextArea(linhas, colunas);
        areaDeTexto.setLineWrap(true); 
        areaDeTexto.setWrapStyleWord(true); 

        JScrollPane barraRolagem = new JScrollPane(areaDeTexto);
        
        while (true) {
            String[] opcoes = { "Salvar", "Editar", "Cancelar" };

            int opcao = JOptionPane.showOptionDialog(null, barraRolagem, mensagem,
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

            // Cancelar
            if (opcao == 2) 
            {
                RegisterFile.updateRegister(5, -1);
                break;
            }
            
            // Editar
            if (opcao == 1)
                carregarArquivo(areaDeTexto);

            String textoEntrada = areaDeTexto.getText(); // Captura o texto digitado
            RegisterFile.updateRegister(8, textoEntrada.length());
            enderecoByte = RegisterFile.getValue(5); // Endereço do buffer em $a1
            int comprimentoMaximo = RegisterFile.getValue(6); // Tamanho máximo do buffer em $a2
            final int comprimentoArquivo = 256;
            
            try {
            	if (opcao == 0)
            	{	
            		//Ultrapassou o limite do buffer
            		if (textoEntrada.length() >= comprimentoMaximo) 
                    { 
                        RegisterFile.updateRegister(5, -2);
                        JOptionPane.showMessageDialog(null, "Não é possível transferir seu texto para buffer, "
                        		+ "ele está cheio!\nTamanho do buffer: " + comprimentoMaximo);
                        break;
                    }
            		
                    // Copia o conteúdo do JTextArea para o buffer
                    for (int indice = 0; (indice < textoEntrada.length()); indice++) 
                        Globals.memory.setByte(enderecoByte + indice, textoEntrada.charAt(indice));
                    
                    // Adiciona o caractere nulo ('\0') para o fim da string
                    Globals.memory.setByte(enderecoByte + (int)Math.min((textoEntrada.length() + 1), comprimentoMaximo - 1), 0);

                    RegisterFile.updateRegister(5, 0);
                    enderecoByte = RegisterFile.getValue(7);
                    String textoArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo");
                   
                    for (int indice = 0; (indice < textoArquivo.length()) && (indice < comprimentoArquivo - 1); indice++) 
                        Globals.memory.setByte(enderecoByte + indice, textoArquivo.charAt(indice));
                    break;               
            	}   
            } 
            catch (AddressErrorException e) 
            {
                throw new ProcessingException(statement, e);
            }
        }
    }
}
