import java.util.*;

public class Player {
    /**
     * Performs a move
     *
     * @param pState
     *            the current state of the board
     * @param pDue
     *            time before which we must have returned
     * @return the next state the board is in after our move
     */
        int timeThres = 100;
    int valueThres;
    int argmax = 0;
    int minimax = 0;
     
    public GameState play(final GameState pState, final Deadline pDue) {

        Vector<GameState> lNextStates = new Vector<GameState>();
        pState.findPossibleMoves(lNextStates);

        if (lNextStates.size() == 0) {
            // Must play "pass" move if there are no other moves possible.
            return new GameState(pState, new Move());
        }

        /**
         * Here you should write your algorithms to get the best next move, i.e.
         * the best next state. This skeleton returns a random move instead.
         */
        double best = Integer.MIN_VALUE;
        int argmax = -1;
        //int depth = 5; //3  
        Checkers mm = new Checkers();
        mm.setInitial(pState.getNextPlayer());
        valueThres=mm.eval(pState)+30;
        System.err.println(pState.toMessage());
                         HashMap<SortedHashmap,Integer> repeatedStates = new HashMap<>(); 
        for (int depth=5;depth<11;depth++){   
                                
            for (int state = 0;state<lNextStates.size();state++) {

                if (pDue.timeUntil()<timeThres){
                    //return lNextStates.elementAt(argmax);
                }

                minimax = mm.alphaBeta(lNextStates.elementAt(state), depth, Integer.MIN_VALUE, Integer.MAX_VALUE, pDue,repeatedStates);
                repeatedStates.put(new SortedHashmap(lNextStates.elementAt(state),depth),minimax);
                // System.err.println(minimax);
                /*if (Math.abs(minimax)>valueThres){
                    return lNextStates.elementAt(state);
                }*/
                
                if (minimax>=valueThres){
                    argmax = state;
                    return lNextStates.elementAt(argmax);
                }

                
                if (minimax > best) {

                    best = minimax;
                    argmax = state;
                    //	System.err.print("BYTER TILL ");
                    //	System.err.println(argmax);

                }

            }
        }
        //System.err.println("arg: "+argmax);
        return lNextStates.elementAt(argmax);
        
    }
}
