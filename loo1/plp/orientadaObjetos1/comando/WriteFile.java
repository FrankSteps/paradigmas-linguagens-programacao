

// package loo1.plp.orientadaObjetos1.comando;

// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.ObjectOutputStream;

// import loo1.plp.expressions2.memory.VariavelJaDeclaradaException;
// import loo1.plp.expressions2.memory.VariavelNaoDeclaradaException;
// import loo1.plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
// import loo1.plp.orientadaObjetos1.excecao.declaracao.ObjetoNaoDeclaradoException;
// import loo1.plp.orientadaObjetos1.expressao.Expressao;
// import loo1.plp.orientadaObjetos1.expressao.leftExpression.Id;
// import loo1.plp.orientadaObjetos1.expressao.valor.ValorRef;
// import loo1.plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
// import loo1.plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
// import loo1.plp.orientadaObjetos1.memoria.Objeto;
// import serializable.AppendingObjectOutputStream;

// /**
//  * Comando de escrita.
//  */
// public class WriteFile implements IO {
	
// 	/**
// 	 * O identificador ao qual sera lido.
// 	 */
//     private Id id;
    
// 	/**
// 	 * Express�o a ser escrita.
// 	 */
//     private Expressao dir;
// 	/**
// 	 * Construtor.
// 	 * @param express�o Express�o a ser escrita.
// 	 */
//     public WriteFile(Id id, Expressao dir){
//     	this.id = id;
//         this.dir = dir;
//     }

//     /**
//      * Escreve na saida padr�o.
//      * @param ambiente o ambiente de execu��o.
//      * @return o ambiente depois de modificado pela execu��o
//      * do comando <code>write</code>.
//      * @throws ClasseNaoDeclaradaException 
//      * @throws  
//      * @throws FileNotFoundException 
//      */
//     public AmbienteExecucaoOO1 executar(AmbienteExecucaoOO1 ambiente)
//         throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
//         ObjetoNaoDeclaradoException, ClasseNaoDeclaradaException {
    	        
//         String path = this.dir.avaliar(ambiente).toString();

// 		Objeto object = ambiente.getObjeto((ValorRef)id.avaliar(ambiente));
		
// 		ObjectOutputStream objGravar;
		
//         try{        	
        	
//         	//Gera o arquivo para armazenar o objeto
// 			FileOutputStream arquivoGrav = new FileOutputStream(path, true);
			
// 			File file = new File(path);
			
// 			if(file.exists() && file.isFile() && file.length() != 0.0){
// 				objGravar = new AppendingObjectOutputStream(arquivoGrav);
// 			}else{
// 				objGravar = new ObjectOutputStream (arquivoGrav);
// 			}
			
// 			objGravar.writeObject(object);
			
// 			objGravar.flush();
// 			objGravar.close();
			
// 			arquivoGrav.flush();
// 			arquivoGrav.close();
			
//         }catch (IOException exc){
//         	exc.printStackTrace();
//         }
        
//         return ambiente;
//     }

//     /**
//      * Realiza a verificacao de tipos da express�o a ser escrita na
//      * pelo comando <code>write</code>
//      * @param ambiente o ambiente de compila��o.
//      * @return <code>true</code> se a express�o a ser escrita est� bem tipada;
//      *          <code>false</code> caso contrario.
//      */
//     public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)
//         throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
//         ClasseNaoDeclaradaException {
//         return id.checaTipo(ambiente);
//     }
// }
