
import java.util.Vector;

class SortedHashmap {
    //private int value;
    private GameState state;
    private int i;
//    private HashMap<GameState,Integer> sorted = new HashMap<>();
    SortedHashmap(GameState state, Integer i) {
  //      this.value = value;
        this.state = state;
        this.i=i;
        
    }

    @Override
    public int hashCode() {
        return (state.toMessage().substring(0,32)+Integer.toString(state.getNextPlayer())+Integer.toString(i)).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        //return obj instanceof SortedHashmap && state.toMessage().substring(0,32)+state.getNextPlayer() == ((SortedHashmap) obj).state.toMessage().substring(0,32)+state.getNextPlayer();'
        return obj instanceof SortedHashmap && this.hashCode() == obj.hashCode();
    }
    GameState getState(){
        return this.state;
    }
/*
    Map<GameState,Integer> sorted(HashMap<GameState,Integer> inputMap){
        Map<GameState, Integer> sorted = inputMap
        .entrySet()
        .stream()
        .sorted(comparingByValue())
        .collect(
            toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                LinkedHashMap::new));
        return sorted;
    }
*/

}