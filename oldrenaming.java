public static void renamingQuery(String relationBeingRenamed, String attributesFromRelation, ArrayList<String> attributeNames) {
  // rename attribute names from a relation
  
  // check if relation exist in database
  if (view.getRelationIndex(attributesFromRelation) == -1) {
    System.out.println("InvalidDBException: Relation Does not Exist");
    return;
  }
  
  // check if target exists but is different from source
  if (view.getRelationIndex(attributesFromRelation) != -1 && view.getRelationIndex(relationBeingRenamed) != -1 && !(relationBeingRenamed.equals(attributesFromRelation))) {
    System.out.println("InvalidDBException: Target relation can't be different from source if both exist");
    return;
  }
  
  Relation relationFrom = view.getRelation(attributesFromRelation);
  
  // check to make sure there are the same number of attributes being renamed
  if(attributeNames.size() > relationFrom.orderedAttributes.size()) {
    System.out.println("InvalidDBException: Renaming more attributes then exist in relation");
    return;
  }
  
  ArrayList<Attribute> attributes = new ArrayList();
  
  /* 3. The attribute names always come from the right operand */
  for(int i=0; i < relationFrom.orderedAttributes.size(); i++) {
    attributes.add(relationFrom.orderedAttributes.get(i));
  }
  
  // check if renaming and creating new relation, or renaming original relationName
  if(view.getRelationIndex(relationBeingRenamed) == -1) {
    Attribute[] attributeArray = attributes.toArray(new Attribute[attributes.size()]);
    Relation relationTo = new Relation(relationBeingRenamed, attributeArray);
    
    // also need to copy the data into the new relation
    for(int i=0; i < relationFrom.rows.size(); i++) {
      relationTo.addRow(relationFrom.rows.get(i).toArray(new Literal[relationFrom.rows.get(i).size()]));
    }
    
    // rename the attributes
    for(int i=0; i < attributeNames.size(); i++) {
      Attribute temp = new Attribute(attributeNames.get(i), attributes.get(i).domain);
      attributes.set(i, temp);
    }
    relationTo.orderedAttributes = attributes;
    
    // rename teh attributes in each literal
    for(int i=0; i < relationTo.rows.size(); i++) {
      ArrayList<Literal> literalsTemp = new ArrayList();
      
      for(int j=0; j < relationTo.rows.get(i).size(); j++) {
        Literal temp = new Literal(attributes.get(j), relationFrom.rows.get(i).get(j).literal);
        literalsTemp.add(temp);
      }
      relationTo.rows.set(i, literalsTemp);
      literalsTemp = new ArrayList();
    }
    
    // add relation to database
    view.addRelation(relationTo);
    System.out.println("Renaming Successful! \n");
  } else {
    //rename attributes of existing relation
    for(int i=0; i < attributeNames.size(); i++) {
      Attribute temp = new Attribute(attributeNames.get(i), relationFrom.orderedAttributes.get(i).domain);
      relationFrom.orderedAttributes.set(i, temp);
    }
    
    // rename the attributes variable
    for(int i=0; i < attributeNames.size(); i++) {
      Attribute temp = new Attribute(attributeNames.get(i), attributes.get(i).domain);
      attributes.set(i, temp);
    }
    
    // rename the primary keys
    ArrayList<Attribute> newPrimaryKeys = attributes;
    relationFrom.primaryKey = newPrimaryKeys;
    
    // rename teh attributes in each literal
    for(int i=0; i < relationFrom.rows.size(); i++) {
      ArrayList<Literal> literalsTemp = new ArrayList();
      
      for(int j=0; j < relationFrom.rows.get(i).size(); j++) {
        Literal temp = new Literal(attributes.get(j), relationFrom.rows.get(i).get(j).literal);
        literalsTemp.add(temp);
      }
      relationFrom.rows.set(i, literalsTemp);
      literalsTemp = new ArrayList();
    }
    System.out.println("Renaming Successful! \n");
  }
}