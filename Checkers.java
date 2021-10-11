import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.Map.Entry.comparingByValue;
import java.util.Vector;
import static java.util.stream.Collectors.toMap;
public class Checkers {
    int value;
    int initialPlayer;
    int otherPlayer;
    int basis = 4;
    static int[] Xpoints = {0,10,20,100,Integer.MAX_VALUE};
    static int[] Opoints = {0,10,20,100,Integer.MAX_VALUE};
    double pointsPiece = 8;
    double pointsKing = 12;
    double pointsBackRow = 4;
    double pointsMidBox = 2.5;
    double pointsMidRows = 0.5;
    double pointsVulnerable  = -3;
    double pointsProtected = 3;
    double weightKings = 1.55;
    int timeThres = 30;
    String aState;
  
    void setInitial(int p){
        System.err.println("Player: "+ p);
        this.initialPlayer = p;
        this.otherPlayer = p == 2 ? 1 : 2;  
    }

    int alphaBeta(GameState state, int depth, double alpha, double beta,Deadline pDue, HashMap<SortedHashmap,Integer> repeatedStates ){
        Vector<GameState> nextStates = new Vector<>();
        state.findPossibleMoves(nextStates);
          //    repeatedStates = new HashMap<>();
        //if(value == 1000) return value;
        if(depth == 0 || nextStates.isEmpty() || pDue.timeUntil()  < timeThres){
            value = eval(state);  
            //System.err.println(initialPlayer);
            repeatedStates.put(new SortedHashmap(state,depth),value);
            return value;
        }
     
        
        HashMap<GameState, Integer> a = new HashMap<>();
        for(GameState child : nextStates){
            a.put(child,eval(child));
        }
        HashMapSorting ab = new HashMapSorting();
        Vector<GameState> sortedStates = ab.orderedStates(a, true);
        Vector<GameState> unsortedStates = ab.orderedStates(a, false);
        repeatedStates.put(new SortedHashmap(state,depth),value);
         if(initialPlayer == 1){
        if(state.getNextPlayer() == 1){
            value = Integer.MIN_VALUE;
 
            for(GameState child : sortedStates){
                for(int i = 11; i >= depth ; i--){
                if(repeatedStates.containsKey(new SortedHashmap(child,i))){
                    //System.err.println("HEEEEEEEE");
                    value = Math.max(value,repeatedStates.get(new SortedHashmap(child,i)));
                   // value = repeatedStates.get(new SortedHashmap(child));
                    alpha = Math.max(alpha,value);
                    if(beta<=alpha){
                        return value;
                    }
                }
                }
                {
              
                value = Math.max(value, alphaBeta(child,depth-1,alpha,beta,pDue,repeatedStates ));
             repeatedStates.put(new SortedHashmap(child,depth),value);
                alpha = Math.max(alpha,value);
                if(beta<=alpha){
                   //  repeatedStates.put(new SortedHashmap(child,depth),value);
                    return value;
                }
                }
            }
         

        }
        else{
            value = Integer.MAX_VALUE;
            for(GameState child : unsortedStates){
                 for(int i = 11; i >= depth ; i--){
                if(repeatedStates.containsKey(new SortedHashmap(child,i))){
                   //  System.err.println("HEEEEEEEE");
                value = Math.min(value, repeatedStates.get(new SortedHashmap(child,i)));
                 //value = repeatedStates.get(new SortedHashmap(child));
                beta = Math.min(beta, value);
                if(beta<=alpha){
                    return value;
                }
                }
                 }
                {
               
                value = Math.min(value, alphaBeta(child,depth-1,alpha,beta,pDue,repeatedStates));
                repeatedStates.put(new SortedHashmap(child,depth),value);
                beta = Math.min(beta,value);
                if(beta<=alpha){
                   //  repeatedStates.put(new SortedHashmap(child,depth),value);
                    return value;
                }
                }
            }


        }
        repeatedStates.put(new SortedHashmap(state,depth),value);
        return value;
        }
        else{
        if(state.getNextPlayer() == 2){
            value = Integer.MIN_VALUE;
 
            for(GameState child : sortedStates){
                                
                for(int i = 11; i >= depth ; i--){
                if(repeatedStates.containsKey(new SortedHashmap(child,i))){
                   //  System.err.println("HEEEEEEEE");
                    value = Math.max(value,repeatedStates.get(new SortedHashmap(child,i)));
                    // value = repeatedStates.get(new SortedHashmap(child));
                    alpha = Math.max(alpha,value);
                    if(beta<=alpha){
                        return value;
                    }
                }
                }
                {

                value = Math.max(value, alphaBeta(child,depth-1,alpha,beta,pDue,repeatedStates));
                repeatedStates.put(new SortedHashmap(child,depth),value);
                alpha = Math.max(alpha,value);
                if(beta<=alpha){
                     //repeatedStates.put(new SortedHashmap(child,depth),value);
                    return value;
                }
                }
            }
         

        }
        else{
            value = Integer.MAX_VALUE;
            for(GameState child : unsortedStates){
                for(int i = 11; i >= depth ; i--){
                if(repeatedStates.containsKey(new SortedHashmap(child,i))){
                   //  System.err.println("HEEEEEEEE");
                value = Math.min(value, repeatedStates.get(new SortedHashmap(child,i)));
               // value = repeatedStates.get(new SortedHashmap(child));
                beta = Math.min(beta, value);
                if(beta<=alpha){
                    return value;
                }
                }
                }
                {
                   
                value = Math.min(value, alphaBeta(child,depth-1,alpha,beta,pDue,repeatedStates));
             repeatedStates.put(new SortedHashmap(child,depth),value);
                beta = Math.min(beta,value);
                if(beta<=alpha){
                     //repeatedStates.put(new SortedHashmap(child,depth),value);
                    return value;
                }
                }
            }


        }
         repeatedStates.put(new SortedHashmap(state,depth),value);
        
            return value;
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        /*
        
        
        
        if(initialPlayer == 1){
        if(state.getNextPlayer() == 1){
            value = Integer.MIN_VALUE;
            
            for(GameState child : nextStates){
                value = Math.max(value, alphaBeta(child,depth-1,alpha,beta,pDue));
                alpha = Math.max(alpha,value);
                if(beta<=alpha){
                    return value;
                }
            }


        }
        else{
            value = Integer.MAX_VALUE;
            for(GameState child : nextStates){
                value = Math.min(value, alphaBeta(child,depth-1,alpha,beta,pDue));
                beta = Math.min(beta, value);
                if(beta<=alpha){
                    return value;
                }
            }


        }
        return value;
        }
        else{
        if(state.getNextPlayer() == 2){
            value = Integer.MIN_VALUE;
            for(GameState child : nextStates){
                value = Math.max(value, alphaBeta(child,depth-1,alpha,beta,pDue));
                alpha = Math.max(alpha,value);
                if(beta<=alpha){
                    return value;
                }
            }


        }
        else{
            value = Integer.MAX_VALUE;
            for(GameState child : nextStates){
                value = Math.min(value, alphaBeta(child,depth-1,alpha,beta,pDue));
                beta = Math.min(beta, value);
                if(beta<=alpha){
                    return value;
                }
            }


        }
        
        
            return value;
        }
        

        */
    }
    int eval(GameState state){
        value = 0;
        //assume initialplayer is W, otherwise change sign
        //1<<0 = red, 1<<1 = white, 1<<2 = king
        int[] red= new int[32];
        int[] white= new int[32];
        int redpieces=0;
        int redkings=0;
        int whitepieces =0;
        int whitekings = 0;
        for(int i = 0; i<32; i++){
            
            //Back rows
            /*
            if((i == 0 || i == 1 || i == 2 || i == 3) && state.get(i) == 1<<0) value -= pointsBackRow;
            if((i == 28 || i == 29 || i == 30 || i == 31) && state.get(i) == 1<<1) value += pointsBackRow;
            
            if((i == 0 || i == 1 || i == 2 || i == 3) && state.get(i) == (1<<0 | 1<<2)) value -= pointsBackRow;
            if((i == 28 || i == 29 || i == 30 || i == 31) && state.get(i) == (1<<1 | 1<<2)) value += pointsBackRow;
            //mid box
            if((i == 13 || i == 14 || i == 17 || i == 18) && state.get(i) == 1<<0){ value -= pointsMidBox;}
            if((i == 13 || i == 14 || i == 17 || i == 18) && state.get(i) == 1<<1) value += pointsMidBox;
            
            if((i == 13 || i == 14 || i == 17 || i == 18) && state.get(i) == (1<<0 | 1<<2)) value -= pointsMidBox;
            if((i == 13 || i == 14 || i == 17 || i == 18) && state.get(i) == (1<<1 | 1<<2)) value += pointsMidBox;
            //mid rows
            if((i == 12 || i == 15 || i == 16 || i == 19) && state.get(i) == 1 <<0) value -= pointsMidRows;
            if((i == 12 || i == 15 || i == 16 || i == 19) && state.get(i) == 1 <<1) value += pointsMidRows;
            
            if((i == 12 || i == 15 || i == 16 || i == 19) && state.get(i) == (1<<0 | 1<<2)) value -= pointsMidRows;
            if((i == 12 || i == 15 || i == 16 || i == 19) && state.get(i) == (1<<1 | 1<<2)) value += pointsMidRows;
            
            //vulnerable
            
                    
            if(state.get(i) == 1<<0 && (state.get(state.cellToRow(i)+1,state.cellToCol(i)+1) == 1<<1 || state.get(state.cellToRow(i)+1,state.cellToCol(i)-1) == 1<<1)){ value+=pointsVulnerable;}
            if(state.get(i) == 1<<1 && (state.get(state.cellToRow(i)-1,state.cellToCol(i)+1) == 1<<0 || state.get(state.cellToRow(i)-1,state.cellToCol(i)-1) == 1<<0)){ value-=pointsVulnerable;}
            
            if(state.get(i) == (1<<0|1<<2) && (state.get(state.cellToRow(i)+1,state.cellToCol(i)+1) == 1<<1 || state.get(state.cellToRow(i)+1,state.cellToCol(i)-1) == 1<<1)){ value+=weightKings*pointsVulnerable;}
            if(state.get(i) == (1<<1|1<<2) && (state.get(state.cellToRow(i)-1,state.cellToCol(i)+1) == 1<<0 || state.get(state.cellToRow(i)-1,state.cellToCol(i)-1) == 1<<0)){ value-=weightKings*pointsVulnerable;}
            
            if(state.get(i) == 1<<0 && (state.get(state.cellToRow(i)-1,state.cellToCol(i)+1) == (1<<1 | 1<<2) || state.get(state.cellToRow(i)-1,state.cellToCol(i)-1) == (1<<1 | 1<<2))) value+=pointsVulnerable;
            if(state.get(i) == 1<<1 && (state.get(state.cellToRow(i)+1,state.cellToCol(i)+1) == (1<<0 | 1<<2 )|| state.get(state.cellToRow(i)+1,state.cellToCol(i)-1) == (1<<0 | 1<<2))) value-=pointsVulnerable;
            
            if(state.get(i) == (1<<0|1<<2) && (state.get(state.cellToRow(i)-1,state.cellToCol(i)+1) == (1<<1 | 1<<2) || state.get(state.cellToRow(i)-1,state.cellToCol(i)-1) == (1<<1 | 1<<2))) value+=weightKings*pointsVulnerable;
            if(state.get(i) == (1<<1|1<<2) && (state.get(state.cellToRow(i)+1,state.cellToCol(i)+1) == (1<<0 | 1<<2 )|| state.get(state.cellToRow(i)+1,state.cellToCol(i)-1) == (1<<0 | 1<<2))) value-=weightKings*pointsVulnerable;
            
            
            //protected
            if(state.get(i) == 1<<0 && !(state.get(state.cellToRow(i)-1,state.cellToCol(i)+1) == (1<<1| 1<<2) || state.get(state.cellToRow(i)-1,state.cellToCol(i)-1) == (1<<1 | 1<<2))) value-=pointsProtected;
            if(state.get(i) == 1<<1 && !(state.get(state.cellToRow(i)+1,state.cellToCol(i)+1) == (1<<0| 1<<2) || state.get(state.cellToRow(i)+1,state.cellToCol(i)-1) == (1<<0 | 1<<2))) value+=pointsProtected;
            
            if(state.get(i) == (1<<0|1<<2) && !(state.get(state.cellToRow(i)-1,state.cellToCol(i)+1) == (1<<1| 1<<2) || state.get(state.cellToRow(i)-1,state.cellToCol(i)-1) == (1<<1 | 1<<2))) value-=weightKings*pointsProtected;
            if(state.get(i) == (1<<1|1<<2) && !(state.get(state.cellToRow(i)+1,state.cellToCol(i)+1) == (1<<0| 1<<2) || state.get(state.cellToRow(i)+1,state.cellToCol(i)-1) == (1<<0 | 1<<2))) value+=weightKings*pointsProtected;
            
            if(state.get(i) == 1<<0 && !(state.get(state.cellToRow(i)+1,state.cellToCol(i)+1) == 1<<1 || state.get(state.cellToRow(i)+1,state.cellToCol(i)-1) == 1<<1)) value-=pointsProtected;
            if(state.get(i) == 1<<1 && !(state.get(state.cellToRow(i)-1,state.cellToCol(i)+1) == 1<<0 || state.get(state.cellToRow(i)-1,state.cellToCol(i)-1) == 1<<0)) value+=pointsProtected;
            
            if(state.get(i) == (1<<0|1<<2) && !(state.get(state.cellToRow(i)+1,state.cellToCol(i)+1) == 1<<1 || state.get(state.cellToRow(i)+1,state.cellToCol(i)-1) == 1<<1)) value-=weightKings*pointsProtected;
            if(state.get(i) == (1<<1|1<<2) && !(state.get(state.cellToRow(i)-1,state.cellToCol(i)+1) == 1<<0 || state.get(state.cellToRow(i)-1,state.cellToCol(i)-1) == 1<<0)) value+=weightKings*pointsProtected;
            */
            
            
            if(state.get(i) == 1<<1 ){ value += pointsPiece; white[i] = 1; whitepieces++;}
            if(state.get(i) == 1<<0 ){value -= pointsPiece; red[i] = 1; redpieces++;}
            
            if(state.get(i) == (1<<1 | 1<<2)) {value += pointsKing; white[i] = 1;whitekings++;}
            if(state.get(i) == (1<<0 | 1<<2)) {value -= pointsKing; red[i] = 1; redkings++;}
            
           //7,-8,11,-12
        }

    if(state.isWhiteWin()) value = 10000;
    if(state.isRedWin()) value = -10000;
  
    if(initialPlayer == 1) value *= -1;
    return value;
    
    }
    
    
    
}
 