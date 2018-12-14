package testwork2larina;

import java.util.Comparator;


public class TelegramLengthComparator implements Comparator<Telegram> {
    @Override
    public int compare(Telegram telegram_1, Telegram telegram_2){
        return telegram_1.lengthOfTelegram() - telegram_2.lengthOfTelegram();
    }
}
