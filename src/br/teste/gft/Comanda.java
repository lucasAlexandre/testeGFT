package br.teste.gft;

import java.util.HashMap;

public class Comanda {

	public int qtdEntre;
	public int qtdSide;
	public int qtdDrink;
	public int qtdDessert;
	public int qtdError;
	
	public String entre;
	public String side;
	public String drink;
	public String dessert;
	public Integer errorPosicao;
	public Boolean error;
	
	public Boolean morning;
	public HashMap<String, Integer> total;
	
	public Boolean paraImpressao;
	
	private void preparaComanda(String time) {
		
		this.morning = time.equalsIgnoreCase("morning");
		
		if (this.morning) {
			this.setEntre("Eggs");
			this.setSide("Toast");
			this.setDrink("coffee");
			this.setDessert("NotApp");
		} else {
			this.setEntre("steak");
			this.setSide("potato");
			this.setDrink("wine");
			this.setDessert("cake");
		}
	}
	
	public void separaPedido(String entrada){
		
		String[] arrayEntrada = entrada.split(",");
		this.preparaComanda(arrayEntrada[0]);
		
		HashMap<String, Integer> total = new HashMap<>();
		this.setTotal(total);
		this.total.put("entrée", 0);
		this.total.put("side", 0);
		this.total.put("drink", 0);
		this.total.put("dessert", 0);
		
		HashMap<String, String> numberToValue = new HashMap<>();
		numberToValue.put("1", "entrée");
		numberToValue.put("2", "side");
		numberToValue.put("3", "drink");
		numberToValue.put("4", "dessert");
		
		for (int i = 1; i < arrayEntrada.length; i++) {
			String pedido = "";
			
			if (numberToValue.get(arrayEntrada[i].trim()) != null) {
				pedido = numberToValue.get(arrayEntrada[i].trim());
				total.put(pedido, total.get(pedido) + 1);
				this.errorPosicao = 0;
				this.error = false;
			} else {
				this.errorPosicao = i;
				this.error = true;
				break;
			}
		}
	}
	
	public String imprimeComanda() {
		String saida = "";
		this.setParaImpressao(false);
		
		HashMap<String, String> numberToValue = new HashMap<>();
		numberToValue.put("1", "entrée");
		numberToValue.put("2", "side");
		numberToValue.put("3", "drink");
		numberToValue.put("4", "dessert");
		
		if(!(total.get(numberToValue.get("1")) > 1)) {
			this.setQtdSide(this.total.get(numberToValue.get("2")));
			this.setQtdDrink(this.total.get(numberToValue.get("3")));
			this.setQtdDessert(this.total.get(numberToValue.get("4")));
		}else{
			this.setErrorPosicao(0);
			this.setError(true);
		}
		
		this.setQtdEntre(total.get(numberToValue.get("1")));
		
		if(this.getQtdEntre()!=0 && this.getQtdEntre()==1) {
			saida = saida+this.getEntre();
		}else if((this.error && this.errorPosicao == 0) || this.getQtdEntre() > 1) {
			saida += this.getEntre()+",";
			saida += "err";
			this.paraImpressao = true;
		}
		
		/*	==============================*/		
		
		if(this.getQtdSide()!=0 && !(this.error && this.errorPosicao==1) && !this.paraImpressao) {
			if(this.getQtdSide()==1 && !(this.error && this.errorPosicao==1)) {
				saida += ",";
				saida += this.getSide();
			}
			
			if(this.getQtdSide() > 1 && !this.morning && !(this.error && this.errorPosicao==1)) {
				saida += ",";
				saida += this.getSide()+"(x"+ this.getQtdSide()+")";
			}

		}else if((this.error && this.errorPosicao == 1)||(this.getQtdSide() > 1 && this.morning)) {
			saida += ",";
			saida += "err";
			this.setParaImpressao(true);
		}
		
		/*	==============================*/
		
		if(this.getQtdDrink()!=0 && !(this.error && this.errorPosicao==2) && !this.paraImpressao) {
			if(this.getQtdDrink()==1 && !(this.error && this.errorPosicao==2)) {
				saida += ",";
				saida += this.getDrink();
			}
			if(this.getQtdDrink() > 1 && this.morning && !(this.error && this.errorPosicao==2)) {
				saida += ",";
				saida += this.getDrink()+"(x"+this.getQtdDrink()+")";
			}
			
		}else if((this.error && this.errorPosicao == 2) || (!this.morning && this.getQtdDrink() > 1)) {
			saida += ",";
			saida += "err";
			this.setParaImpressao(true);
		}
		
		
		/*	==============================*/
		
		if((this.getQtdDessert()!=0 && this.getQtdDessert()==1) && !(this.error && this.errorPosicao==3) && !this.morning && !this.paraImpressao) {
			saida += ",";
			saida += this.getDessert();
		}else if((this.error && this.errorPosicao == 3) || (this.morning && this.getQtdDessert() == 1) || (this.morning && this.getQtdDessert() > 1)){
			saida += ",";
			saida += "err";
			this.setParaImpressao(true);
		}
		
		/*	==============================*/
		
		if(this.error && this.errorPosicao > 3 && !this.paraImpressao) {
			saida += ",";
			saida += "err";
		}
		
		return saida;
	}
	
	public int getQtdEntre() {
		return qtdEntre;
	}
	public void setQtdEntre(int qtdEntre) {
		this.qtdEntre = qtdEntre;
	}
	public int getQtdSide() {
		return qtdSide;
	}
	public void setQtdSide(int qtdSide) {
		this.qtdSide = qtdSide;
	}
	public int getQtdDrink() {
		return qtdDrink;
	}
	public void setQtdDrink(int qtdDrink) {
		this.qtdDrink = qtdDrink;
	}
	public int getQtdDessert() {
		return qtdDessert;
	}
	public void setQtdDessert(int qtdDessert) {
		this.qtdDessert = qtdDessert;
	}
	public int getQtdError() {
		return qtdError;
	}
	public void setQtdError(int qtdError) {
		this.qtdError = qtdError;
	}
	public String getEntre() {
		return entre;
	}
	public void setEntre(String entre) {
		this.entre = entre;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public String getDrink() {
		return drink;
	}
	public void setDrink(String drink) {
		this.drink = drink;
	}
	public String getDessert() {
		return dessert;
	}
	public void setDessert(String dessert) {
		this.dessert = dessert;
	}
	public Integer getErrorPosicao() {
		return errorPosicao;
	}
	public void setErrorPosicao(Integer errorPosicao) {
		this.errorPosicao = errorPosicao;
	}
	public Boolean getError() {
		return error;
	}
	public void setError(Boolean error) {
		this.error = error;
	}

	public Boolean getMorning() {
		return morning;
	}

	public void setMorning(Boolean morning) {
		this.morning = morning;
	}

	public HashMap<String, Integer> getTotal() {
		return total;
	}

	public void setTotal(HashMap<String, Integer> total) {
		this.total = total;
	}

	public Boolean getParaImpressao() {
		return paraImpressao;
	}

	public void setParaImpressao(Boolean paraImpressao) {
		this.paraImpressao = paraImpressao;
	}
	
}
