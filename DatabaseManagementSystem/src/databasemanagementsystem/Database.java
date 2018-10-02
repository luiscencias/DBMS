import java.io.*;
import java.util.*;
import java.lang.*;

public class Database{
	
	public String name;
	public ArrayList<Relation> relations;
	
	public Database(String n){
		name = n;
		relations = new ArrayList();
	}
	
	public void addRelation(Relation r){
		relations.add(r);
	}
	
	public void delRelation(String relation_name){
		for(int k = 0; k < relations.size(); k++){
			if(relations.get(k).name == relation_name){
				relations.remove(0);
				break;
			}
		}
	}
	
	public int getRelationIndex(String n){
		try{
			for(int k = 0; k < relations.size(); k++){
				if(relations.get(k).name == n){
					return k;
				}
			}
			throw new InvalidDBException("NO SUCH RELATION");
		}
		catch(InvalidDBException e){
			 System.err.println("InvalidDBException: " + e.getMessage());
		}
		return -1;
	}
	
	public static void main(String args[]){
		
		System.out.println("\nJust a test of some of the Database class functions");
		
		Database data = new Database("data");
		
		Attribute num = new Attribute("num",0);
		Attribute chara = new Attribute("chara",1);
		Attribute word = new Attribute("word",5);
		
		Attribute[] atts = {num,chara,word};
		String[] prim = {num.name};
		Relation rel = new Relation("Rel", atts, prim);
		
		for(int k = 0; k < 10; k++){
			Literal[] tuple;
			if(k < 5){
				tuple = new Literal[3];
			}
			else{
				tuple = new Literal[2];
			}
			tuple[0] = new Literal(num,Integer.toString(k));
			tuple[1] = new Literal(chara,Integer.toString(k+5));
			if(k < 5){
				tuple[2] = new Literal(word,Integer.toString((int)Math.pow(k,k)));
			}
			rel.addRow(tuple);
		}
		data.addRelation(rel);
		Attribute att = new Attribute("att",25);
		Attribute[] att2 = {att};
		String[] prim2 = {att.name};
		Relation rel2 = new Relation("Rel2", att2, prim2);
		for(int k = 0; k < 3; k++){
			Literal[] tuple = new Literal[1];
			tuple[0] = new Literal(att,Integer.toString(k*k+k));
			rel2.addRow(tuple);
		}
		data.addRelation(rel2);
		
		data.testPrint();
	}
	
	public void testPrint(){
		System.out.print("|Database: " + name+"\n");
		for(int k = 0; k < relations.size(); k++){
			System.out.println("Relation["+k+"]: ");
			relations.get(k).testPrint();
		}
		System.out.println("");
	}
	
}

class Relation{
	
	public String name;
	public ArrayList<Attribute> orderedAttributes;
	public ArrayList<Attribute> primaryKey;
	public ArrayList<ArrayList<Literal> > rows;
	
	public Relation(String n, Attribute[] atts, String[] primary){
		name = n;
		orderedAttributes = new ArrayList();
		primaryKey = new ArrayList();
		rows = new ArrayList();
		for(int k = 0; k < atts.length; k++){
			orderedAttributes.add(atts[k]);
		}
		try{
			for(int k = 0; k < primary.length; k++){
				if(orderedAttributes.get(k).name == primary[k]){
					primaryKey.add(orderedAttributes.get(k));
				}
				else{
					throw new InvalidDBException("INVALID PRIMARY KEY");
				}
			}
		}
		catch(InvalidDBException e){
			 System.err.println("InvalidDBException: " + e.getMessage());
		}
	}
	
	public void addRow(Literal[] tuple){
		ArrayList<Literal> rTemp = new ArrayList();
		try{
			if(tuple.length < primaryKey.size()){
				throw new InvalidDBException("INSEFFICENT PRIMARY KEY PARAMETERS");
			}
			for(int k = 0; k < tuple.length; k++){
				if(tuple[k].attribute.name == orderedAttributes.get(k).name){
					rTemp.add(tuple[k]);
				}
				else{
					if(k < primaryKey.size()){
						throw new InvalidDBException("INSEFFICENT PRIMARY KEY PARAMETERS");
					}
				}
			}
			rows.add(rTemp);
		}
		catch(InvalidDBException e){
			 System.err.println("InvalidDBException: " + e.getMessage());
		}

	}
	
	public void testPrint(){
		System.out.print("#Relation: " + name+"\n  ");
		for(int k = 0; k < orderedAttributes.size(); k++){
			orderedAttributes.get(k).testPrint();
		}
		System.out.print("\n  Primary Key: ");
		for(int k = 0; k < primaryKey.size(); k++){
			primaryKey.get(k).testPrint();
		}
		for(int k = 0; k < rows.size(); k++){
			System.out.print("\n  Row["+k+"]: ");
			for(int j = 0; j < rows.get(k).size(); j++){
				rows.get(k).get(j).testPrint();
			}
		}
		System.out.println("");
	}
	
}

class Attribute{
	
	public int domain; // 0 = INTEGER, n>0 = VARCHAR size n 
	public String name;
	
	public Attribute(String n, int d){
		name = n;
		domain = d;
	}
	
	public void testPrint(){
		System.out.print("|Attribute: " + name);
		if(domain > 0){
			System.out.print(" VARCHAR("+domain+") ");
		}
		else{
			System.out.print(" INTEGER ");
		}
	}
}

class Literal{
	
	public Attribute attribute;
	public String literal;
	
	public Literal(Attribute a, String l){
		try{
			attribute = new Attribute(a.name,a.domain);
			if(attribute.domain > 0){
				if(attribute.domain < l.length()){
					literal = l.substring(0,attribute.domain);
				}
				else{
					literal = l;
				}
			}
			else{
				int lInt = Integer.parseInt(l);
				literal = Integer.toString(lInt);
			}
		}
		catch(NumberFormatException e){
			 System.err.println("NumberFormatException: " + e.getMessage());
		}
	}
	
	public void testPrint(){
		System.out.print("|Literal: " + literal + " Type: ");
		attribute.testPrint();
	}
}

class InvalidDBException extends Exception{

  public InvalidDBException(String message){
     super(message);
  }

}