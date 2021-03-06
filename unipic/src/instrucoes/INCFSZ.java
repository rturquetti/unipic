package instrucoes;
import unipic.CPU;
import unipic.Instrucao;
import unipic.Memoria;

public class INCFSZ extends Instrucao{
	
	/***
	 *  Inicia a instrucao 
	 * @param comando 0011 11df ffff
	 */
	
	@Override
	public void setup(String comando){
		this.setD(Integer.parseInt(comando.substring(6,7),2));
		this.setF(Integer.parseInt(comando.substring(7),2));
	}
	
	public void run(Memoria mem, CPU cpu){
		//Armazena valor de W
		@SuppressWarnings("unused")
		byte w = cpu.getW();
		//Armazena valor de f
		byte valorEmF = mem.get(this.f);
		//Incrementa um no valorEmF
		byte result = (byte) (valorEmF + 1);
		//Se result for igual a zero, pula uma instrucao
		if(result == 0){
			//Esta instrucao serve apenas para pular um ciclo
			Instrucao nop = new NOP();
			nop.setup("000000000000");
			nop.run(mem, cpu);
		}
		//Se o valor em D for igual a zero, o resultado da operacao eh armazenado em W
		if(this.d==0){
			cpu.setW(result);
		} else {
		//Senao o resultado eh armazenado em f
			mem.set(this.f,result);
		}
		//Incrementa PCL
		mem.setPCL((byte) (mem.getPCL() + 1));
	}
}

