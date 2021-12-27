package com.mycompany.a4;

import java.util.ArrayList;


public class GameCollection implements ICollection {

    private ArrayList<GameObject> theCollection;

    public GameCollection() {
        theCollection = new ArrayList<GameObject>();
    }
    

    public void add(GameObject newObject) {
        theCollection.add(newObject);
    }

    public ArrayList<GameObject> getObjects(){
        return theCollection;
    }
    

    public IIterator getIterator() {
        return new GameIterator();
    }
    
    

private class GameIterator implements IIterator{

        private int currElementIndex;
      
        public GameIterator() {
            currElementIndex = -1;
        }    
      
        @Override
        public boolean hasNext() {
            if(theCollection.size() <= 0) return false;
            if(currElementIndex == theCollection.size()-1) {
                return false;
            }
            return true;
        }

        
        @Override
        public GameObject getNext() {
            currElementIndex++;
            return(theCollection.get(currElementIndex));
        }
        
        
    } 
} 