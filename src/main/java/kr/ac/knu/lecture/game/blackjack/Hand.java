package kr.ac.knu.lecture.game.blackjack;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by rokim on 2018. 5. 26..
 */
@Slf4j
public class Hand {

    private AtomicInteger cardSum = new AtomicInteger();
    private Deck deck;
    @Getter
    private List<Card> cardList = new ArrayList<>();

    private boolean isNumberOver;
    private int CHECK_COUNT = 2;

    public Hand(Deck deck) {
        this.deck = deck;
        this.isNumberOver = false;
    }

    public Card drawCard() {
        Card card = deck.drawCard();
        cardList.add(card);
        return card;
    }
    public void rankingAddition() {
        cardSum.getAndSet(0);
        cardList.stream().forEach(card -> {
            int rank = card.getRank();
            if (rank > 10) {
                cardSum.addAndGet(10);
            } else if (rank == 1) {
                if (isNumberOver) {
                    cardSum.addAndGet(1);
                } else if (cardSum.get() > 10) {
                    cardSum.addAndGet(1);
                } else {
                    cardSum.addAndGet(11);
                }
            } else {
                cardSum.addAndGet(rank);
            }
        });
    }

    public int getCardSum() {
        for (int i = 0; i < CHECK_COUNT; i++) {
            rankingAddition();
            if (cardSum.get() > 21) {
                isNumberOver = true;
                continue;
            } else {
                isNumberOver = false;
                break;
            }
        }
        return cardSum.get();
    }


    public void reset() {
        cardList.clear();
    }

}
