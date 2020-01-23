/**
 * DiskHandler.java
 * Created by Nic Kristiansson
 * For CSCE-315 Project-2 Group 54
 *
 * Handles writing to and reading from the disk.
 **/
 
import java.io.*;
import java.util.*;
import java.lang.*;

/** 
 * DiskHandler class:
 * Reads/Writes from/to disk.
 **/
public class DiskHandler{
	
	// Writes relation to disk.
	public static void writeDisk(Relation relation) throws FileNotFoundException{
		File db = new File(relation.name+".db");
		try{
			PrintWriter writer = new PrintWriter(db);
			relationWrite(writer, relation);
			writer.close();
		}
		catch(FileNotFoundException e){
			System.err.println("FileNotFoundException: " + e.getMessage());
		}
	}
	
	// Writes relation to disk (abstraction).
	public static void closeDisk(Relation relation) throws FileNotFoundException{
		writeDisk(relation);
	}
	
	// Tries to read from disk based off potential relation name.
	public static Relation readDisk(String name) throws FileNotFoundException{
		Attribute ta = new Attribute("!!!",0);
		Attribute[] tas = {ta};
		String[] pas = {ta.name};
		// Default relation.
		Relation relation = new Relation("!!!", tas, pas);
		try(Scanner in = new Scanner(new File(name+".db"))) {
			String rName = "";
			Attribute[] orderedAttributes = new Attribute[0];
			Attribute[] primaryAttributes = new Attribute[0];
			ArrayList<String[]> typelessTuples = new ArrayList();
			// Reads in line by line.
			while(in.hasNextLine()) {
				String line = in.nextLine();
				char id = line.charAt(0);
				line = line.substring(1,line.length()).trim();
				switch(id){
					case 'R' :
						rName = relationRead(line);
						break;
					case 'O' :
						orderedAttributes = attributesRead(line);
						break;
					case 'P' :
						primaryAttributes = attributesRead(line);
						break;
					case 'T' :
						typelessTuples.add(typelessTupleRead(line));
						break;
					default:
				}
			}
			String[] primaryKey = new String[primaryAttributes.length];
			for(int k = 0; k < primaryAttributes.length; k++){
				primaryKey[k] = primaryAttributes[k].name;
			}
			// Relation creation.
			relation = new Relation(rName,orderedAttributes,primaryKey);
			for(int k = 0; k < typelessTuples.size(); k++){
				Literal[] lits = new Literal[typelessTuples.get(k).length];
				for(int j = 0; j < typelessTuples.get(k).length; j++){
					lits[j] = new Literal(orderedAttributes[j], typelessTuples.get(k)[j]);
				}
				relation.addRow(lits);
			}
			// If relation is empty, set to default.
			if(relation.orderedAttributes.size() == 0){
				relation = new Relation("!!!", tas, pas);
			}	
		}
		catch(FileNotFoundException e){
			System.err.println("FileNotFoundException: " + e.getMessage());
			relation = new Relation("!!!", tas, pas);
		}
		catch(NullPointerException e){
			System.err.println("NullPointerException: " + e.getMessage());
			relation = new Relation("!!!", tas, pas);
		}
		return relation;
	}
	
	// Abstracted relation reading.
	public static String relationRead(String s){
		return s.trim();
	}
	
	// Abstracted attribute reading.
	public static Attribute[] attributesRead(String s){
		String[] split = s.split("#");
		Attribute[] attributes = new Attribute[split.length];
		for(int k = 0; k < split.length; k++){
			String[] param = split[k].split(",");
			attributes[k] = new Attribute(param[0], Integer.parseInt(param[1]));
		}
		return attributes;
	}
	
	// Abstracted typeless tuple reading.
	public static String[] typelessTupleRead(String s){
		String[] tuple = s.split("#");
		return tuple;
	}

	// Abstracted relation writing.
	public static void relationWrite(PrintWriter w, Relation r){
		w.println("R\t"+r.name);
		w.print("O\t");
		for(int k = 0; k < r.orderedAttributes.size(); k++){
			attributeWrite(w, r.orderedAttributes.get(k));
		}
		w.print("\nP\t");
		for(int k = 0; k < r.primaryKey.size(); k++){
			attributeWrite(w, r.primaryKey.get(k));
		}
		w.println("");
		for(int k = 0; k < r.rows.size(); k++){
			tupleWrite(w, r.rows.get(k));
		}
	}
	
	// Abstracted attribute writing.
	public static void attributeWrite(PrintWriter w, Attribute a){
		String attStr = a.name + "," + a.domain + "#";
		w.print(attStr);
	}
	
	// Abstracted tuple writing.
	public static void tupleWrite(PrintWriter w, ArrayList<Literal> t){
		w.print("T\t");
		for(int k = 0; k < t.size(); k++){
			literalWrite(w, t.get(k));
		}
		w.println("");
	}
	
	// Abstracted literal writing.
	public static void literalWrite(PrintWriter w, Literal l){
		w.print(l.literal+"#");
	}
}

/** 
 * HandlerUseExample class:
 * Demonstrates the functions of the DiskHandler.
 **/
class HandlerUseExample{
	
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
		Attribute att = new Attribute("alphabet",26);
		Attribute[] att2 = {att,num};
		String[] prim2 = {att.name,num.name};
		Relation rel2 = new Relation("Rel2", att2, prim2);
		for(int k = 0; k < 10; k++){
			Literal[] tuple = new Literal[2];
			String s = "";
			char count = 'a';
			for(int j = 0; j < k*k; j++){
				s += (""+count++);
			}
			tuple[0] = new Literal(att,s);
			tuple[1] = new Literal(num,Integer.toString(k*k));
			rel2.addRow(tuple);
		}
		data.addRelation(rel2);
		
		data.testPrint();
		
		System.out.println("Adding relations from file\n");
		try{
			DiskHandler.writeDisk(rel);
			DiskHandler.writeDisk(rel2);
			Relation rel3 = DiskHandler.readDisk(rel2.name);
			Relation rel4 = DiskHandler.readDisk("written");
			Relation relu = DiskHandler.readDisk("I don't exist");
			Attribute[] atts3 = new Attribute[100];
			String[] p = {"ora"};
			rel3.name = "Rel3";
			DiskHandler.writeDisk(rel3);
			data.addRelation(rel3);
			data.addRelation(rel4);
			data.addRelation(relu);
			data.testPrint();
		}
		catch(FileNotFoundException e){
			System.err.println("FileNotFoundException: " + e.getMessage());
		}
		
	}
	
	
	
	
	
}