/**
 * Database.java
 * Created by Nic Kristiansson
 * For CSCE-315 Project-2 Group 54
 *
 * Handles database, realtion, attribute, and literal creation and access.
 **/

import java.io.*;
import java.util.*;
import java.lang.*;

/** 
 * Database class:
 * Contains a list of relations.
 * Effectively acts as the view during runtime.
 **/
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
	
	// Deletes a relation by name.
	public void delRelation(String relation_name){
		for(int k = 0; k < relations.size(); k++){
			if(relations.get(k).name == relation_name){
				relations.remove(0);
				break;
			}
		}
	}
	
	public Relation getRelation(String n){
		return relations.get(getRelationIndex(n));
	}
	
	// Gets a relation's index by name.
	public int getRelationIndex(String n){
		try{
			for(int k = 0; k < relations.size(); k++){
				if(relations.get(k).name == n){
					return k;
				}
			}
			// Throw if no relation n exists in the Database.
			throw new InvalidDBException("NO SUCH RELATION");
		}
		catch(InvalidDBException e){
			 System.err.println("InvalidDBException: " + e.getMessage());
		}
		return -1;
	}
	
	// Print's information about the Database for diagnositc purposes.
	public void testPrint(){
		System.out.print("|Database: " + name+"\n");
		for(int k = 0; k < relations.size(); k++){
			System.out.println("Relation["+k+"]: ");
			relations.get(k).testPrint();
		}
		System.out.println("");
	}
	
}

/** 
 * Relation class:
 * Contains a list of realtional attributes, the primary key, and the literals of the relaiton.
 **/
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
				if(orderedAttributes.get(k).name.equals(primary[k])){
					primaryKey.add(orderedAttributes.get(k));
				}
				else{
					// Throw if primary key does not match the ordered attributes in either name or size.
					throw new InvalidDBException("INVALID PRIMARY KEY");
				}
			}
		}
		catch(InvalidDBException e){
			 System.err.println("InvalidDBException: " + e.getMessage());
		}
	}
	
	// Adds tuple of Literals to the Relation.
	public void addRow(Literal[] tuple){
		ArrayList<Literal> rTemp = new ArrayList();
		try{
			if(tuple.length < primaryKey.size()){
				// Throw if there are not enough elements for the primary key.
				throw new InvalidDBException("INSEFFICENT PRIMARY KEY PARAMETERS");
			}
			for(int k = 0; k < tuple.length; k++){
				if(tuple[k].attribute.name.equals(orderedAttributes.get(k).name)){
					rTemp.add(tuple[k]);
				}
				else{
					if(k < primaryKey.size()){
						// Throw if there are not enough elements for the primary key.
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
	
	// Print's information about the Relation for diagnositc purposes.
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

/** 
 * Attribute class:
 * Contains the domain of the attribute;
 * Domain type classification:
 *	- Domain = 0: Type = INTEGER
 *	- Domain = n: Type = VARCHAR(n)
 **/
class Attribute{
	
	public int domain; // 0 = INTEGER, n>0 = VARCHAR size n 
	public String name;
	
	public Attribute(String n, int d){
		name = n;
		domain = d;
	}
	
	// Print's information about the Attribute for diagnositc purposes.
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

/** 
 * Literal class:
 * Contains the attribute type and a String representation of the literal.
 **/
class Literal{
	
	public Attribute attribute;
	public String literal;
	
	public Literal(Attribute a, String l){
		try{
			attribute = new Attribute(a.name,a.domain);
			// Check type of attribute. (see Attribute class)
			if(attribute.domain > 0){
				if(attribute.domain < l.length()){
					literal = l.substring(0,attribute.domain);
				}
				else{
					literal = l;
				}
			}
			else{
				// Throws error if l is not integer-able.
				int lInt = Integer.parseInt(l);
				literal = Integer.toString(lInt);
			}
		}
		catch(NumberFormatException e){
			 System.err.println("NumberFormatException: " + e.getMessage());
		}
	}
	
	// Print's information about the Literal for diagnositc purposes.
	public void testPrint(){
		System.out.print("|Literal: " + literal + " Type: ");
		attribute.testPrint();
	}
}

// Custom exception class
class InvalidDBException extends Exception{

  public InvalidDBException(String message){
     super(message);
  }

}