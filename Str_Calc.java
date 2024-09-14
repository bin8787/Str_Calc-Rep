//java Str_Calc.java
                                   
import java.util.Scanner;    
                               
public class Str_Calc {     
                      
  public static void main(String[] args) { 
    try{
      Scanner in = new Scanner(System.in);
                              
      System.out.println("¬ведите выражение:");
      String str = in.nextLine();
                        
      in.close(); 
                          
      var Operation_1 = new TOperation(str); 
              
      Operation_1.Print_Result();         
                                  
    }                  
    catch(TOperation_Except ex){ 
                                  
      System.out.println("Exception:  \"" + ex.getMessage() + "\""); 
    }        
                                       
  }                                            
                                    
}       



public class TOperation {

  private final String s_InputFormat = "\"[^\"]{0,10}\"\\s*((([*]|[/])\\s*([1-9]|10))|(([+]|[-])\\s*\"[^\"]{0,10}\"))";

  private final int i_MaxLengthToPrint = 40; 
                      
                           
  private String sResult = null; 
                           
  private char cOperationSign; 
                               
  private String sOperand1;
                    
  private String sOperand2;        
                                         
                                           
  TOperation(String sExp) throws TOperation_Except { 
          
    sExp = sExp.trim(); 
           
    if(!sExp.matches(s_InputFormat))   
      throw new TOperation_Except("¬ыражение не соответствует формату."); 
                               
    sOperand1 = sExp.substring(1, 1 + sExp.substring(1).indexOf("\"")); 
                                       
    sExp = sExp.substring(sOperand1.length() + 2).trim();  
                                                  
    cOperationSign = sExp.charAt(0);  
                                                  
    sOperand2 = sExp.substring(1).trim();  
                                                 
    if(sOperand2.startsWith("\""))     
      sOperand2 = sOperand2.substring(1, sOperand2.length() - 1); 
  }                         
                                   
                                     
  private void Calculate_Result() throws TOperation_Except {  
                                                       
    switch(cOperationSign){                     
                                                            
      case '+':                                        
        sResult = TLibrary.Str_Add_Str(sOperand1, sOperand2); 
        break;                                                  
      case '-':                                                 
        sResult = TLibrary.Str_Subtr_Str(sOperand1, sOperand2);  
        break;                                                   
      case '*':                                                
        sResult = TLibrary.Str_Mult_Num(sOperand1, Integer.parseInt(sOperand2)); 
        break;                                                                   
      case '/':                                                               
        sResult = TLibrary.Str_Div_Num(sOperand1, Integer.parseInt(sOperand2));  
        break;                                                                  
      default:                          
                                                              
        throw new TOperation_Except("Ќедопустимый знак операции."); 
           
        //ѕри данном конструкторе никогда не выбрасываетс€  
        //т.к. проверка происходит в конструкторе.        
    }                                                          
  }        
                           
                                 
  public void Print_Result() throws TOperation_Except {  
                               
    if(sResult == null) Calculate_Result();  
                                         
    System.out.println(sResult.length() <= i_MaxLengthToPrint ? sResult : 
      sResult.substring(0, i_MaxLengthToPrint) + "...");      
  }                                                          
                          
}                 
                                           
                                                  
                                                
class TOperation_Except extends Exception {  
                                              
  public TOperation_Except(String sMsg) { super(sMsg); } 
                   
}                             
                                   
                                           
                                    
class TLibrary {    
                           
  static String Str_Add_Str(String s1, String s2) {  
                                          
    return s1 + s2;                     
  }                           
                           
                                                      
  static String Str_Subtr_Str(String s1, String s2) {  
                                         
    return s1.replaceAll(s2, "");     
  }                                 
                                           
                                                
  static String Str_Mult_Num(String str, int iX) { 
                                 
    if(iX < 1) iX = 1;           
                               
    return str.repeat(iX);                       
  }                                    
                                         
                             
  static String Str_Div_Num(String str, int iX) { 
                                   
    if(iX < 1) iX = 1;           
                                     
    int iNewLen = str.length()/iX;    
                                      
    return str.substring(0, iNewLen); 
  }                                     
                                      
}                               
                            
                            