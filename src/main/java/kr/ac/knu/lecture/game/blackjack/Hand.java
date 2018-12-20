package kr.ac.knu.lecture.game.blackjack;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Hand {
    private Deck deck;
    @Getter
    private List<Card> cardList = new ArrayList<>();

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        Card card = deck.drawCard();
        cardList.add(card);
        return card;
    }

    public int getCardSum() {
        final int[] cardSum = {0};
        cardList.stream().forEach(card -> {
            int rank = card.getRank();
            if (rank > 10) {
                cardSum[0] +=10;
            }
            else if(cardSum[0] >10 && rank ==1){
                cardSum[0] +=1;
            }
            else if (rank==1)
                cardSum[0] +=11;
            else
                cardSum[0] +=rank;
        });
        return cardSum[0];
        //return cardSum[0] +=cardList.stream().mapToInt(card -> {
        ////            int rank = card.getRank();
        ////
        ////            return rank;
        ////        }).sum();
        //

    }

    public void reset() {
        cardList.clear();
    }

}
