package com.alura.conversor.logica;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Hashtable;

public class Conversor {

	private Hashtable<String, Double> tasaMXN = new Hashtable<String, Double>();
	private Hashtable<String, Double> gradoC = new Hashtable<String, Double>();
	private BigDecimal valor;
	private MathContext m = new MathContext(4);
	private BigDecimal resultado=new BigDecimal("0.0");
	private BigDecimal divisaDestino, divisaOrigen, baseDivisa, raizDivisa, subtotal, total;

	public Conversor() {
		// Carga de parametros para conversion

		this.tasaMXN.put("MXN-Peso Mexicano", 1.0);
		this.tasaMXN.put("USD-Dolares Americanos", .05868);
		this.tasaMXN.put("EUR-Euro", 0.05330);
		this.tasaMXN.put("GBP-Libra Esterlina", 0.04537);
		this.tasaMXN.put("YPJ-Yen Japonés", 8.23);
		this.tasaMXN.put("KRW-Won Sur-Coreano", 75.71);

	}

	public String ConvertirDivisa(String origen, String destino, String valor) {

		this.valor = new BigDecimal(valor);
		if (origen == "MXN-Peso Mexicano") {
			BigDecimal tasa = new BigDecimal(tasaMXN.get(destino));
			System.out.println("Antes de respuesta: " + this.valor.multiply(tasa, m));
			return this.valor.multiply(tasa, m)+" "+destino.substring(0,3);
		} else {
			this.divisaDestino = new BigDecimal(tasaMXN.get(destino));
			this.divisaOrigen = new BigDecimal(tasaMXN.get(origen));
			this.baseDivisa = new BigDecimal(1.0);
			this.raizDivisa = baseDivisa.divide(divisaOrigen, m);
			this.subtotal = raizDivisa.multiply(divisaDestino, m);
			this.total = this.valor.multiply(subtotal, m);
			this.resultado=total;
			System.out.println("Respuesta " + total);
			return total+" "+destino.substring(0,3);
		}
		
	}

	public String ConvertirGrados(String origen, String destino, String valor) {
		BigDecimal const1=new BigDecimal("1.8");
		BigDecimal const2=new BigDecimal("273.15");
		BigDecimal const3=new BigDecimal("-273.15");
		BigDecimal const4=new BigDecimal("32");
		BigDecimal const5=new BigDecimal("-32");
		
		if(origen=="Celcius" && destino=="Farenheit") {
			BigDecimal valorIngreso= new BigDecimal(valor);
			
			
			resultado=valorIngreso.multiply(const1);
			resultado=resultado.add(const4);
			System.out.println("Resultado "+resultado);
		}else if(origen=="Celcius" && destino=="Kelvin") {
			BigDecimal valorIngreso= new BigDecimal(valor);
			
			resultado=valorIngreso.add(const2);
			System.out.println("Resultado "+resultado);
		}else if(origen=="Farenheit" && destino=="Celcius") {
			BigDecimal valorIngreso= new BigDecimal(valor);
			
			
			resultado=valorIngreso.add(const5);
			
			System.out.println(resultado.signum()<0);
			resultado = resultado.divide(const1,m);
			
			System.out.println("Resultado "+resultado);
		}else if(origen=="Farenheit" && destino=="Kelvin") {
			BigDecimal valorIngreso= new BigDecimal(valor);
			BigDecimal x= new BigDecimal(".55555",m);
			BigDecimal y= valorIngreso.add(const5);
			BigDecimal z= x.multiply(y,m);
			
			resultado=z.add(const2);
			
			System.out.println("Resultado "+resultado);
		}else if(origen=="Kelvin" && destino=="Farenheit") {
			BigDecimal valorIngreso= new BigDecimal(valor);
			
			resultado=valorIngreso.add(const3);
			resultado=resultado.multiply(const1);
			resultado=resultado.add(const4);
			System.out.println("Resultado "+resultado);
		}else if(origen=="Kelvin" && destino=="Celcius") {
			BigDecimal valorIngreso= new BigDecimal(valor);
			resultado=valorIngreso.add(const3);
			
			System.out.println("Resultado "+resultado);
		}
		return resultado+" "+destino.substring(0,1);
	}
}
