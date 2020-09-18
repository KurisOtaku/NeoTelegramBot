/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.kuris.telegrambot.neo;

/**
 *
 * @author cristiano.rosa
 */
public enum DiceEmoji {

    /**
     * 🎲
     */
    DICE,
    /**
     * 🎯
     */
    DART,
    /**
     * 🏀
     */
    BASKET;

    static String stringOf(DiceEmoji dice_emoji) {
        switch (dice_emoji) {
            case DART:
                return "🎯";
            case BASKET:
                return "🏀";
            default:
                return "🎲";
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case DART:
                return "🎯";
            case BASKET:
                return "🏀";
            default:
                return "🎲";
        }
    }
}
