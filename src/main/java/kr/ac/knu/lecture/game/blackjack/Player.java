package kr.ac.knu.lecture.game.blackjack;

import kr.ac.knu.lecture.exception.NoMoreCardException;
import kr.ac.knu.lecture.exception.NotEnoughBalanceException;
import lombok.Getter;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Player {
    @Getter
    private long balance;
    @Getter
    private long currentBet;
    @Getter
    private boolean isPlaying;
    @Getter
    private Hand hand;
    @Getter
    private boolean isDoubleDown;
    private boolean isBlackJack;

    public Player(long seedMoney, Hand hand) {
        this.balance = seedMoney;
        this.hand = hand;
        isPlaying = false;
    }

    public void reset() {
        hand.reset();
        isPlaying = false;
    }

    public void placeBet(long bet) {
        if (balance < bet) {
            throw new NotEnoughBalanceException();
        }
        currentBet = bet;

        isPlaying = true;
    }

    public void deal() {
        balance -= currentBet;
        hand.drawCard();
        hand.drawCard();
    }

    public void win() {
        balance += currentBet * 2;
    }

    public void tie() {
        balance += currentBet;
    }

    public void lost() {
        balance+=0;
    }

    public Card hitCard() {
        if(isDoubleDown == true)
            return null;
        return hand.drawCard();
    }

    public void stand() {
        this.isPlaying = false;
        this.isDoubleDown = false;
        this.isBlackJack = false;
    }

    public void doubleDown(){
        if(hand.getCardList().size() > 2)
            throw new NoMoreCardException();
        if (balance < currentBet) {
            throw new NotEnoughBalanceException();
        }
        balance -= currentBet;
        currentBet = currentBet * 2;

        hand.drawCard();
        this.isDoubleDown = true;
    }
    public boolean isBlackJack(){
        if(hand.getCardSum() == 21)
            isBlackJack = true;
        return isBlackJack;
    }
    public void settingBet(){
        if(currentBet>balance){
            currentBet=balance;
        }
        balance-=currentBet;
    }





}
