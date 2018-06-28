import java.io.File;
/**
Classe para Filtrar apenas arquivos com a extens�o .pas
@ Rog�rio Cortina
*/
public class FileFilter extends javax.swing.filechooser.FileFilter 
{
  
  public boolean accept(File f) 
  {
    //se for diret�rio
    
    if (f.isDirectory()) 
      return true;
  
    //Retorna a extens�o do arquivo

    String extension = getExtension(f);
    //verifica se a extens�o � igual a pas
    if (extension.equals("pas"))
       return true; 
    
    return false;
  }
    
  public String getDescription() 
  {
      return "*.pas";
  }

  private String getExtension(File f) 
  {
    String s = f.getName();
    int i = s.lastIndexOf('.');
    if (i > 0 &&  i < s.length() - 1) 
      return s.substring(i+1).toLowerCase();
    return "";
  }
}