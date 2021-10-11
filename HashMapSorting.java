
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

public class HashMapSorting{

    Vector<GameState> orderedStates(HashMap<GameState,Integer> inStates, boolean maximizing){
        Set<Entry<GameState, Integer>> entries = inStates.entrySet();
        Comparator<Entry<GameState, Integer>> valueComparator = new Comparator<Entry<GameState,Integer>>() {
            
            @Override
            public int compare(Entry<GameState, Integer> e1, Entry<GameState, Integer> e2) {
                Integer v1 = e1.getValue();
                Integer v2 = e2.getValue();
                return v1.compareTo(v2);
            }
        };
        
        // Sort method needs a List, so let's first convert Set to List in Java
        List<Entry<GameState, Integer>> listOfEntries = new ArrayList<Entry<GameState, Integer>>(entries);
        
        // sorting HashMap by values using comparator
       
        Collections.sort(listOfEntries, valueComparator);
        if(maximizing){
            Collections.reverse(listOfEntries);
        }
        LinkedHashMap<GameState, Integer> sortedByValue = new LinkedHashMap<GameState, Integer>(listOfEntries.size());
        
        // copying entries from List to Map
        for(Entry<GameState, Integer> entry : listOfEntries){
            sortedByValue.put(entry.getKey(), entry.getValue());
        }

        Set<Entry<GameState, Integer>> entrySetSortedByValue = sortedByValue.entrySet();

          Vector<GameState> theStates = new Vector<>();
        for(Entry<GameState, Integer> mapping : entrySetSortedByValue){
            theStates.add(mapping.getKey());
        
    }
        return theStates;

    }
    
}